package ghidraremotesync.server;

import ghidraremotesync.Grs;
import ghidraremotesync.RemoteProgramListingGrpc;
import io.grpc.stub.StreamObserver;
import java.io.File;

public class RemoteProgramListingService extends  RemoteProgramListingGrpc.RemoteProgramListingImplBase{
    @Override
    public void listRemotePrograms(Grs.ListRemoteProgramsRequest request, StreamObserver<Grs.ListRemoteProgramsResponse> responseObserver) {
        String directoryPath = "C:\\Users\\djsteffey\\Desktop\\court";
        File directory = new File(directoryPath);

        Grs.ListRemoteProgramsResponse.Builder responseBuilder = Grs.ListRemoteProgramsResponse.newBuilder();

        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                responseBuilder.addFiles(getFileInfo(file));
            }
        } else {
            responseObserver.onError(new RuntimeException("Directory not found"));
            return;
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private Grs.RemoteProgramInfo getFileInfo(File file) {
        Grs.RemoteProgramInfo.Builder fileInfoBuilder = Grs.RemoteProgramInfo.newBuilder()
                .setName(file.getName())
                .setIsDirectory(file.isDirectory());

        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                fileInfoBuilder.addSubFiles(getFileInfo(subFile));
            }
        }

        return fileInfoBuilder.build();
    }
}

