package ghidrasymbolserver;

import ghidra.app.services.ConsoleService;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressFactory;
import ghidra.program.model.address.AddressSpace;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Program;
import ghidra.program.model.symbol.Symbol;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class GhidraSymbolServerService extends GhidraSymbolServerGrpc.GhidraSymbolServerImplBase{
    // variables
    private Program m_program;
    private IMessageLogger m_logger;

    // methods
    public GhidraSymbolServerService(Program program, IMessageLogger logger){
        this.m_program = program;
        this.m_logger = logger;
    }

    public void setProgram(Program program){
        this.m_program = program;
    }

    @Override
    public void getSymbolAtAddress(Gss.UnsignedLong address, StreamObserver<Gss.String> responseObserver){
        // address comes in with a base offset of 0; convert to our program base offset
        long addressValue = address.getValue() + this.m_program.getImageBase().getOffset();

        // log
        this.m_logger.addMessage(
                String.format(
                        "received request for symbol at address 0x%016X",
                        addressValue
                )
        );

        // incoming address should be with a base of 0
        AddressFactory af = this.m_program.getAddressFactory();
        AddressSpace as = af.getDefaultAddressSpace();
        Address a = as.getAddress(addressValue);
        Symbol s = this.m_program.getSymbolTable().getPrimarySymbol(a);

        // build the response
        String responseValue = s == null ? "" : s.getName();
        Gss.String response = Gss.String.newBuilder().setValue(responseValue).build();

        // send the response
        responseObserver.onNext(response);

        // done
        responseObserver.onCompleted();

        this.m_logger.addMessage("responded with symbol: " + responseValue);
    }

    @Override
    public void getAddressOfSymbol(Gss.String symbolName, StreamObserver<Gss.UnsignedLong> responseObserver){
        this.m_logger.addMessage("received request for address of symbol " + symbolName.getValue());

        // ask ghidra
        List<Symbol> allSymbols = this.m_program.getSymbolTable().getGlobalSymbols(symbolName.getValue());
        if (allSymbols.isEmpty()){

        }
        else{

        }

        // build response
        long responseValue = allSymbols.isEmpty() ? -1 : allSymbols.getFirst().getAddress().getOffset() - this.m_program.getImageBase().getOffset();
        Gss.UnsignedLong response = Gss.UnsignedLong.newBuilder().setValue(responseValue).build();

        // send response
        responseObserver.onNext(response);

        // done
        responseObserver.onCompleted();

        this.m_logger.addMessage(
                String.format(
                        "responded with address 0x%016X",
                        responseValue == -1 ? responseValue : responseValue + this.m_program.getImageBase().getOffset()
                )
        );
    }

    /*
    @Override
    public void getAllSymbols(Gss.EmptyRequest request, StreamObserver<Gss.AllSymbolsResponse> responseObserver) {
        this.m_logger.addMessage("received request for all symbols");

        // start the response
        Gss.AllSymbolsResponse.Builder builder = Gss.AllSymbolsResponse.newBuilder();

        // ask ghidra
        int count = 0;
        for (Symbol symbol : this.m_program.getSymbolTable().getAllSymbols(false)){
            count += 1;
            String name = symbol.getName();
            long address = symbol.getAddress().getOffset() - this.m_program.getImageBase().getOffset();
            builder.addSymbols(
                    Gss.Symbol.newBuilder().setName(name).setAddress(address).build()
            );
        }

        // send it
        responseObserver.onNext(builder.build());

        // done
        responseObserver.onCompleted();

        // log
        this.m_logger.addMessage("all symbols sent (" + count + ")");
    }*/

    @Override
    public void getAllSymbols(Gss.EmptyRequest request, StreamObserver<Gss.AllSymbolsResponse> responseObserver) {
        this.m_logger.addMessage("received request for all symbols");

        // start the response
        Gss.AllSymbolsResponse.Builder builder = Gss.AllSymbolsResponse.newBuilder();

        // ask ghidra
        int count = 0;
        // Get the listing from the current program
        FunctionIterator functionIterator = this.m_program.getFunctionManager().getFunctionsNoStubs(true);

        // Loop through all functions
        while (functionIterator.hasNext()) {
            count += 1;

            Function function = functionIterator.next();
            String name = function.getName();
            long address = function.getEntryPoint().getOffset() - this.m_program.getImageBase().getOffset();

            builder.addSymbols(
                    Gss.Symbol.newBuilder().setName(name).setAddress(address).build()
            );
        }

        // send it
        responseObserver.onNext(builder.build());

        // done
        responseObserver.onCompleted();

        // log
        this.m_logger.addMessage("all symbols sent (" + count + ")");
    }
}