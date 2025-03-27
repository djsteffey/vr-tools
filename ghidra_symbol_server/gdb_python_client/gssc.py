# standard imports
import gdb
import sys
import os
import grpc


# so we can do imports from the same directory as this script
sys.path.append(os.path.dirname(os.path.abspath(__file__)))
import gss_pb2
import gss_pb2_grpc


# global base offset for our queries
gssc_image_base = 0


# grpc to get the symbol from an address
def grpc_get_symbol_at_address(address):
    global gssc_image_base

    # comms channel
    channel = grpc.insecure_channel('192.168.230.1:50051')

    # stub
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # create request
    request = gss_pb2.UnsignedLong(value=(address - gssc_image_base))

    # call the method
    response = stub.GetSymbolAtAddress(request)

    # done
    return response.value


# grp to get an address from a symbol
def grpc_get_address_from_symbol(symbol):
    global gssc_image_base

    # comms channel
    channel = grpc.insecure_channel('192.168.230.1:50051')

    # stub
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # create request
    request = gss_pb2.String(value=symbol)

    # call the method
    response = stub.GetAddressOfSymbol(request)

    # done
    if response.value == -1:
        return -1
    return response.value + gssc_image_base



class GSSCSetBase(gdb.Command):
    def __init__(self):
        super(GSSCSetBase, self).__init__('gssc_setbase', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # split args into tokens
        args = args.split(' ')

        # must have one arg
        if len(args) != 1:
            print('gssc_setbase must have at least one argument')
            return

        # convert it to an int
        gssc_image_base = int(args[0], 16)

        # done
        print(f'gssc_setbase set to {gssc_image_base}')



class GSSCGetSymbolAtAddress(gdb.Command):
    def __init__(self):
        super(GSSCGetSymbolAtAddress, self).__init__('gssc_getsymbol', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # split args into tokens
        args = args.split(' ')

        # must have one arg
        if len(args) != 1:
            print('gssc_getsymbol must have at least one argument')
            return

        # convert it to an int
        address = int(args[0], 16)

        # do it big
        symbol = grpc_get_symbol_at_address(address)

        # done
        print(f'symbol => {symbol}')




class GSSCGetAddressFromSymbol(gdb.Command):
    def __init__(self):
        super(GSSCGetAddressFromSymbol, self).__init__('gssc_getaddress', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # split args into tokens
        args = args.split(' ')

        # must have one arg
        if len(args) != 1:
            print('gssc_getaddress must have at least one argument')
            return

        # do it big
        symbol = grpc_get_address_from_symbol(args[0])

        # done
        print(f'address => {hex(symbol)}')




GSSCSetBase()
GSSCGetSymbolAtAddress()
GSSCGetAddressFromSymbol()
