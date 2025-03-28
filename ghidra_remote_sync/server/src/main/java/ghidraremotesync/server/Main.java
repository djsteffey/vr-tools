package ghidraremotesync.server;

import ghidra.base.project.GhidraProject;
import ghidra.framework.model.Project;
import ghidra.framework.model.ProjectLocator;
import ghidra.framework.project.DefaultProject;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Main {
    public static void main(String[] args) throws Exception{
        GhidraProject project = GhidraProject.openProject(
                "C:\\Users\\djsteffey\\Desktop\\ghidra\\projects",
                "testtesttest"
        );

        Server server = ServerBuilder.forPort(50051)
                .addService(new RemoteProgramListingService())
                .build()
                .start();
        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }
}
