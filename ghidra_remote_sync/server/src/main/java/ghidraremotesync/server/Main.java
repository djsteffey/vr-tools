package ghidraremotesync.server;

import ghidra.GhidraJarApplicationLayout;
import ghidra.base.project.GhidraProject;
import ghidra.framework.Application;
import ghidra.framework.HeadlessGhidraApplicationConfiguration;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Main {
    public static void main(String[] args) throws Exception{
        // init ghidra
        Application.initializeApplication(
                new GhidraJarApplicationLayout(),
                new HeadlessGhidraApplicationConfiguration()
        );

        // load the ghidra project
        GhidraProject project = GhidraProject.openProject(
                "C:\\Users\\djsteffey\\Desktop\\ghidra\\projects",
                "testtesttest.gpr"
        );

        // grpc server
        Server server = ServerBuilder.forPort(50051)
                .addService(new RemoteProgramListingService())
                .build()
                .start();
        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }
}
