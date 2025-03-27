package ghidraremotesync.client;

import db.DBHandle;
import ghidra.framework.data.DomainObjectAdapterDB;
import java.io.IOException;

public class RemoteProgramDomainObject extends DomainObjectAdapterDB {
    // constants
    private static final int EVENT_NOTIFICATION_DELAY = 500;

    // variables
    private String m_serverHost;
    private int m_serverPort;
    private String m_programName;
    private String m_programHash;

    // methods
    public RemoteProgramDomainObject(String serverHost, int serverPort, String programName, String programHash, Object consumer) throws IOException {
        super(new DBHandle(), programName, EVENT_NOTIFICATION_DELAY, consumer);

        this.m_serverHost = serverHost;
        this.m_serverPort = serverPort;
        this.m_programName = programName;
        this.m_programHash = programHash;
    }

    @Override
    public boolean isChangeable() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Remote Server Program";
    }
}