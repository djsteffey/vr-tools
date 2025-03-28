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


# ask gdb for the value of a symbol or reg
def get_symbol_or_reg_value(sym):
    val = gdb.parse_and_eval(f'${sym}')
    return val


# grpc to set the address in ghidra
def grpc_set_current_address(address):
    global gssc_image_base

    # comms channel
    channel = grpc.insecure_channel('192.168.230.1:50051')

    # stub
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # create request
    request = gss_pb2.UInt64(value=(address - gssc_image_base))

    # call the method
    response = stub.SetCurrentAddress(request)

    # done
    return


# grpc to get the symbol from an address
def grpc_get_symbol_at_address(address):
    global gssc_image_base

    # comms channel
    channel = grpc.insecure_channel('192.168.230.1:50051')

    # stub
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # create request
    request = gss_pb2.UInt64(value=(address - gssc_image_base))

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
    print(response.value)
    print(hex(response.value))
    if response.value == 0xffffffffffffffff:
        return -1
    return response.value + gssc_image_base



# grp to get an address from a symbol
def grpc_get_all_symbols():
    global gssc_image_base

    # comms channel
    channel = grpc.insecure_channel('192.168.230.1:50051')

    # stub
    stub = gss_pb2_grpc.GhidraSymbolServerStub(channel)

    # create request
    request = gss_pb2.Empty()

    # call the method
    response = stub.GetAllSymbols(request)

    # done
    for item in response.symbols:
        item.address += gssc_image_base
    return response.symbols





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



class GSSCGetAllSymbols(gdb.Command):
    def __init__(self):
        super(GSSCGetAllSymbols, self).__init__('gssc_getallsymbols', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # do it big
        allsymbols = grpc_get_all_symbols()

        # done
        for item in allsymbols:
            if item.address == 0xffffffffffffffff:
                pass
            else:
                print(f'{hex(item.address)} => {item.name}')



class GSSCApplyAllSymbols(gdb.Command):
    def __init__(self):
        super(GSSCApplyAllSymbols, self).__init__('gssc_applyallsymbols', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # get all the symbols
        allsymbols = grpc_get_all_symbols()

        # loop over symbols
        for item in allsymbols:
            if item.address == 0xffffffffffffffff:
                pass
            else:
                # make a gdb symbol for it
                print(f'{hex(item.address)} => {item.name}')
                gdb.execute(f'set ${item.name.replace('.', '_')} = {hex(item.address)}')
                


class GSSCSetCurrentAddress(gdb.Command):
    def __init__(self):
        super(GSSCSetCurrentAddress, self).__init__('gssc_setcurrentaddress', gdb.COMMAND_USER)

    
    def invoke(self, args, from_tty):
        # bring in the global
        global gssc_image_base

        # split args into tokens
        args = args.split(' ')

        # must have one arg
        if len(args) != 1:
            print('gssc_setcurrentaddress must have at least one argument')
            return

        # get the value
        val = get_symbol_or_reg_value(args[0])

        # rpc it





        # do it big
        allsymbols = grpc_get_all_symbols()

        # done
        for item in allsymbols:
            if item.address == 0xffffffffffffffff:
                pass
            else:
                print(f'{hex(item.address)} => {item.name}')



GSSCSetBase()
GSSCGetSymbolAtAddress()
GSSCGetAddressFromSymbol()
GSSCGetAllSymbols()
GSSCApplyAllSymbols()
GSSCSetCurrentAddress()
