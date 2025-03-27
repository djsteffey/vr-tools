package ghidraremotesync;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: grs.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RemoteProgramListingGrpc {

  private RemoteProgramListingGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ghidraremotesync.RemoteProgramListing";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ghidraremotesync.Grs.ListRemoteProgramsRequest,
      ghidraremotesync.Grs.ListRemoteProgramsResponse> getListRemoteProgramsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListRemotePrograms",
      requestType = ghidraremotesync.Grs.ListRemoteProgramsRequest.class,
      responseType = ghidraremotesync.Grs.ListRemoteProgramsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ghidraremotesync.Grs.ListRemoteProgramsRequest,
      ghidraremotesync.Grs.ListRemoteProgramsResponse> getListRemoteProgramsMethod() {
    io.grpc.MethodDescriptor<ghidraremotesync.Grs.ListRemoteProgramsRequest, ghidraremotesync.Grs.ListRemoteProgramsResponse> getListRemoteProgramsMethod;
    if ((getListRemoteProgramsMethod = RemoteProgramListingGrpc.getListRemoteProgramsMethod) == null) {
      synchronized (RemoteProgramListingGrpc.class) {
        if ((getListRemoteProgramsMethod = RemoteProgramListingGrpc.getListRemoteProgramsMethod) == null) {
          RemoteProgramListingGrpc.getListRemoteProgramsMethod = getListRemoteProgramsMethod =
              io.grpc.MethodDescriptor.<ghidraremotesync.Grs.ListRemoteProgramsRequest, ghidraremotesync.Grs.ListRemoteProgramsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListRemotePrograms"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ghidraremotesync.Grs.ListRemoteProgramsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ghidraremotesync.Grs.ListRemoteProgramsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RemoteProgramListingMethodDescriptorSupplier("ListRemotePrograms"))
              .build();
        }
      }
    }
    return getListRemoteProgramsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RemoteProgramListingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingStub>() {
        @java.lang.Override
        public RemoteProgramListingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteProgramListingStub(channel, callOptions);
        }
      };
    return RemoteProgramListingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RemoteProgramListingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingBlockingStub>() {
        @java.lang.Override
        public RemoteProgramListingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteProgramListingBlockingStub(channel, callOptions);
        }
      };
    return RemoteProgramListingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RemoteProgramListingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RemoteProgramListingFutureStub>() {
        @java.lang.Override
        public RemoteProgramListingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RemoteProgramListingFutureStub(channel, callOptions);
        }
      };
    return RemoteProgramListingFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void listRemotePrograms(ghidraremotesync.Grs.ListRemoteProgramsRequest request,
        io.grpc.stub.StreamObserver<ghidraremotesync.Grs.ListRemoteProgramsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListRemoteProgramsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RemoteProgramListing.
   */
  public static abstract class RemoteProgramListingImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RemoteProgramListingGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RemoteProgramListing.
   */
  public static final class RemoteProgramListingStub
      extends io.grpc.stub.AbstractAsyncStub<RemoteProgramListingStub> {
    private RemoteProgramListingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteProgramListingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteProgramListingStub(channel, callOptions);
    }

    /**
     */
    public void listRemotePrograms(ghidraremotesync.Grs.ListRemoteProgramsRequest request,
        io.grpc.stub.StreamObserver<ghidraremotesync.Grs.ListRemoteProgramsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListRemoteProgramsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RemoteProgramListing.
   */
  public static final class RemoteProgramListingBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RemoteProgramListingBlockingStub> {
    private RemoteProgramListingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteProgramListingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteProgramListingBlockingStub(channel, callOptions);
    }

    /**
     */
    public ghidraremotesync.Grs.ListRemoteProgramsResponse listRemotePrograms(ghidraremotesync.Grs.ListRemoteProgramsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListRemoteProgramsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RemoteProgramListing.
   */
  public static final class RemoteProgramListingFutureStub
      extends io.grpc.stub.AbstractFutureStub<RemoteProgramListingFutureStub> {
    private RemoteProgramListingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RemoteProgramListingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RemoteProgramListingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ghidraremotesync.Grs.ListRemoteProgramsResponse> listRemotePrograms(
        ghidraremotesync.Grs.ListRemoteProgramsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListRemoteProgramsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_REMOTE_PROGRAMS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_REMOTE_PROGRAMS:
          serviceImpl.listRemotePrograms((ghidraremotesync.Grs.ListRemoteProgramsRequest) request,
              (io.grpc.stub.StreamObserver<ghidraremotesync.Grs.ListRemoteProgramsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getListRemoteProgramsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              ghidraremotesync.Grs.ListRemoteProgramsRequest,
              ghidraremotesync.Grs.ListRemoteProgramsResponse>(
                service, METHODID_LIST_REMOTE_PROGRAMS)))
        .build();
  }

  private static abstract class RemoteProgramListingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RemoteProgramListingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ghidraremotesync.Grs.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RemoteProgramListing");
    }
  }

  private static final class RemoteProgramListingFileDescriptorSupplier
      extends RemoteProgramListingBaseDescriptorSupplier {
    RemoteProgramListingFileDescriptorSupplier() {}
  }

  private static final class RemoteProgramListingMethodDescriptorSupplier
      extends RemoteProgramListingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RemoteProgramListingMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RemoteProgramListingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RemoteProgramListingFileDescriptorSupplier())
              .addMethod(getListRemoteProgramsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
