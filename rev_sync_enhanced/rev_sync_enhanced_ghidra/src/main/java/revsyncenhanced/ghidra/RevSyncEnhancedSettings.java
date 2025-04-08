package revsyncenhanced.ghidra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;

public class RevSyncEnhancedSettings {
    private static final String FILENAME = System.getProperty("user.home") + "/.rev_sync_enhanced.config";

    // variables
    private IMessageLogger m_messageLogger;
    private String m_username;
    private String m_host;
    private int m_port;
    private String m_credentials;

    // methods
    public RevSyncEnhancedSettings(IMessageLogger messageLogger){
        this.m_messageLogger = messageLogger;
        this.m_username = "";
        this.m_host = "";
        this.m_port = 0;
        this.m_credentials = "";
    }

    public boolean load(){
        try {
            this.m_messageLogger.addMessage("loading settings from file");

            // get the settings from the config file
            FileReader fileReader = new FileReader(FILENAME);
            JsonObject root = JsonParser.parseReader(fileReader).getAsJsonObject();
            this.m_username = root.has("username") ? root.get("username").getAsString() : "";
            this.m_host = root.has("host") ? root.get("host").getAsString() : "";
            this.m_port = root.has("port") ? root.get("port").getAsInt() : 0;
            this.m_credentials = root.has("credentials") ? root.get("credentials").getAsString() : "";

            // done
            return true;
        }
        catch (Exception ex){
            this.m_messageLogger.addMessage("unable to load settings from file");
            return false;
        }
    }

    public void save(){
        this.m_messageLogger.addMessage("saving settings to file");

        // construct the json object
        JsonObject root = new JsonObject();
        root.addProperty("username", this.m_username);
        root.addProperty("host", this.m_host);
        root.addProperty("port", this.m_port);
        root.addProperty("credentials", this.m_credentials);

        // write it to the file
        try (FileWriter fileWriter = new FileWriter(FILENAME)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(root, fileWriter);
        }
        catch (Exception ex){
            this.m_messageLogger.addMessage("unable to save settings to file");
        }
    }

    public String getUsername(){
        return this.m_username;
    }

    public void setUsername(String username){
        this.m_username = username;
    }

    public String getHost(){
        return this.m_host;
    }

    public void setHost(String host){
        this.m_host = host;
    }

    public int getPort(){
        return this.m_port;
    }

    public void setPort(int port){
        this.m_port = port;
    }

    public String getCredentials(){
        return this.m_credentials;
    }

    public void setCredentials(String credentials){
        this.m_credentials = credentials;
    }
}
