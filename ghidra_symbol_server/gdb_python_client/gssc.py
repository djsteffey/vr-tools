import grpc
import gss_pb2
import gss_pb2_grpc

def run(address):
    # Connect to the server (make sure the server is running)
    channel = grpc.insecure_channel('localhost:50051')
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # Create a request
    request = gss_pb2.UnsignedLong(value=address)

    # Call the server's method
    response = stub.GetSymbolAtAddress(request)

    # Print the response
    print(f"Server response: {response.value}")


def run2(name):
    # Connect to the server (make sure the server is running)
    channel = grpc.insecure_channel('localhost:50051')
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # Create a request
    request = gss_pb2.String(value=name)

    # Call the server's method
    response = stub.GetAddressOfSymbol(request)

    # Print the response
    print(f"Server response: {hex(response.value)}")

if __name__ == '__main__':
    run(0x110cdc - 0x100000)
    run2('entry')
