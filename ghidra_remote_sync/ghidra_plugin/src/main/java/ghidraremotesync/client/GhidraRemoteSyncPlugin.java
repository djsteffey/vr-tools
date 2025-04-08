package ghidraremotesync.client;

import db.DBHandle;
import docking.ActionContext;
import docking.action.DockingAction;
import docking.action.ToolBarData;
import ghidra.app.CorePluginPackage;
import ghidra.app.events.OpenProgramPluginEvent;
import ghidra.app.events.ProgramActivatedPluginEvent;
import ghidra.app.events.ProgramOpenedPluginEvent;
import ghidra.app.plugin.PluginCategoryNames;
import ghidra.app.plugin.core.codebrowser.CodeBrowserPlugin;
import ghidra.app.plugin.core.progmgr.ProgramLocator;
import ghidra.app.plugin.core.progmgr.ProgramManagerPlugin;
import ghidra.app.services.GoToService;
import ghidra.app.services.ProgramManager;
import ghidra.app.util.task.OpenProgramTask;
import ghidra.framework.main.AppInfo;
import ghidra.framework.model.DomainFile;
import ghidra.framework.plugintool.Plugin;
import ghidra.framework.plugintool.PluginInfo;
import ghidra.framework.plugintool.PluginTool;
import ghidra.framework.plugintool.util.PluginException;
import ghidra.framework.plugintool.util.PluginStatus;
import ghidra.program.database.ProgramDB;
import ghidra.program.model.listing.Program;
import ghidra.util.Msg;
import ghidraremotesync.client.remote.RemoteProgram;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.concurrent.Executors;

@PluginInfo(
        status = PluginStatus.STABLE,
        packageName = CorePluginPackage.NAME,
        category = PluginCategoryNames.COMMON,
        shortDescription = "Manage Remote Synced Programs",
        description = "Manages remote server and programs for synchronization between multiple users simultaneously",
        servicesRequired = {

        },
        eventsProduced ={
                ProgramActivatedPluginEvent.class
        }
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


        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detect double-click
                    DomainFile df = tool.getProject()
                            .getProjectData()
                            .getRootFolder()
                            .getFile("notepad++.exe");

                    /*
                    tool.getProject()
                            .getToolServices()
                            .launchTool("CodeBrowser", Arrays.asList(df));
                     */

                    RemoteProgram_v2 program = new RemoteProgram_v2(tool);

                    PluginTool cb = tool.getProject()
                            .getToolServices()
                            .launchTool("CodeBrowser", null);
                    cb.getService(ProgramManager.class).openProgram(program);

                    /*
                    cb.firePluginEvent(new ProgramActivatedPluginEvent(
                            "GhidraRemoteSyncPlugin", program.m_real
                    ));
                    cb.firePluginEvent(new ProgramOpenedPluginEvent(
                            "GhidraRemoteSyncPlugin", program.m_real
                    ));*/

                    int y = 0;
                    /*
                    PluginTool tool = GhidraRemoteSyncPlugin.this.getTool();

                    RemoteProgram_v2 program = new RemoteProgram_v2(tool);

                    ProgramManager programManager = tool.getService(ProgramManager.class);

                    if (programManager != null) {
                        programManager.openProgram(
                                tool.getProject()
                                        .getProjectData()
                                        .getRootFolder()
                                        .getFile("notepad++.exe")
                        );
                        programManager.openProgram(program.m_real); // Open the ProgramDB in the Code Browser
                    } else {
                        Msg.showError(this, null, "Error", "ProgramManager service not available!");
                    }*/

                    /*
                    tool.firePluginEvent(
                            new ProgramOpenedPluginEvent(
                                    GhidraRemoteSyncPlugin.this.getName(),
                                    program.m_real
                            )
                    );*/
                }
            }
        });
    }







    /*
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
        }
    }*/

    private void getRemoteProgramInfoAsync(){
        Executors.newSingleThreadExecutor().execute(() -> {
            //GrpcClient.doit(this.tree);
        });
    }
}
