package ghidraremotesync.client;

import docking.ActionContext;
import docking.action.DockingAction;
import docking.action.ToolBarData;
import ghidra.app.CorePluginPackage;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.framework.plugintool.Plugin;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.util.Msg;
import ghidraremotesync.Grs;
import ghidraremotesync.RemoteProgramListingGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.concurrent.Executors;

@PluginInfo(
        status = PluginStatus.STABLE,
        packageName = CorePluginPackage.NAME,
        category = PluginCategoryNames.COMMON,
        shortDescription = "Manage Remote Synced Programs",
        description = "Manages remote server and programs for synchronization between multiple users simultaneously"
)
public class GhidraRemoteSyncPlugin extends Plugin {
    private JPanel panel;
    private JTree tree;

    public GhidraRemoteSyncPlugin(PluginTool tool) {
        super(tool);
        createButton(tool);
        createTreeView(tool);
        this.getRemoteProgramInfoAsync();
    }


    private void createButton(PluginTool tool) {
        DockingAction action = new DockingAction("ToolChest Icon", getName()) {
            @Override
            public void actionPerformed(ActionContext context) {
                Msg.showInfo(getClass(), null, "Action Triggered", "Icon button clicked!");
            }
        };

        action.setToolBarData(new ToolBarData(
                null
        ));
        tool.addAction(action);
    }

    private void createTreeView(PluginTool tool) {
        panel = new JPanel(new BorderLayout());
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
        root.add(child1);
        root.add(child2);

        tree = new JTree(new DefaultTreeModel(root));
        JScrollPane scrollPane = new JScrollPane(tree);
        panel.add(scrollPane, BorderLayout.CENTER);

        tool.getToolFrame().add(panel, BorderLayout.WEST);
        tool.getToolFrame().validate();
        tool.getToolFrame().repaint();
    }








    public static class GrpcClient {
        public static void doit(JTree tree) {
            // connect
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                    .usePlaintext()
                    .build();

            // get the stub
            RemoteProgramListingGrpc.RemoteProgramListingBlockingStub stub2 = RemoteProgramListingGrpc.newBlockingStub(channel);

            // send the request
            Grs.ListRemoteProgramsRequest request2 = Grs.ListRemoteProgramsRequest.newBuilder().build();

            // get the response
            Grs.ListRemoteProgramsResponse response2 = stub2.listRemotePrograms(request2);

            // disconnect
            channel.shutdown();

            // parse the response
            for (Grs.RemoteProgramInfo file : response2.getFilesList()) {
                parseIntoTree(file, (DefaultMutableTreeNode) tree.getModel().getRoot());
            }

            // update ui
            ((DefaultTreeModel)tree.getModel()).reload();
        }

        private static void parseIntoTree(Grs.RemoteProgramInfo file, DefaultMutableTreeNode node) {
            if (file.getIsDirectory()){
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.getName());
                node.add(child);
                for (Grs.RemoteProgramInfo subFile : file.getSubFilesList()) {
                    parseIntoTree(subFile, child);
                }
            }
            else{
                node.add(new DefaultMutableTreeNode(file.getName()));
            }

            /*
            String prefix = "  ".repeat(indent);
            System.out.println(prefix + (file.getIsDirectory() ? "[D] " : "[F] ") + file.getName());

            for (Grs.RemoteProgramInfo subFile : file.getSubFilesList()) {
                printFileInfo(subFile, indent + 1); // Recursively print subfiles with indentation
            }*/
        }
    }

    private void getRemoteProgramInfoAsync(){
        Executors.newSingleThreadExecutor().execute(() -> {
            GrpcClient.doit(this.tree);
        });
    }
}
