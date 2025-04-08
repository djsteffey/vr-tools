package revsyncenhanced.ghidra;

import com.google.gson.JsonObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class RevSyncBackendEnhancedRedisImpl extends JedisPubSub implements IRevSyncEnhancedBackend {
    // variables
    private String m_username;
    private String m_programHash;
    private Jedis m_jedisRequests;
    private Jedis m_jedisSubscribe;
    private String m_redisMessageListKey;
    private String m_redisSubscribeChannel;
    private IListener m_listener;
    private final ReentrantLock m_incomingMessagesLock = new ReentrantLock(true);

    // methods
    public RevSyncBackendEnhancedRedisImpl(RevSyncEnhancedSettings settings, String programHash, IListener listener) throws Exception{
        // check settings
        if (settings.getUsername().isEmpty()){
            throw new Exception("invalid username");
        }
        if (settings.getHost().isEmpty()){
            throw new Exception("invalid host");
        }
        if (settings.getPort() <= 0){
            throw new Exception("invalid port");
        }
        this.m_username = settings.getUsername();
        this.m_programHash = programHash;
        this.m_redisMessageListKey = programHash;
        this.m_redisSubscribeChannel = programHash;
        this.m_listener = listener;

        try {
            // make the jedis which also connects
            this.m_jedisRequests = new Jedis(settings.getHost(), settings.getPort(), 5000, 5000);
            this.m_jedisSubscribe = new Jedis(settings.getHost(), settings.getPort(), 5000, 5000);
        }
        catch (Exception ex) {
            throw new Exception("unable to connect to redis server");
        }

        // auth
        if (settings.getCredentials().isEmpty() == false) {
            try {
                this.m_jedisRequests.auth(settings.getCredentials());
                this.m_jedisSubscribe.auth(settings.getCredentials());
            }
            catch (Exception ex) {
                throw new Exception("unable to authenticate to redis server");
            }
        }

        // lock messages so we are not yet processing
        this.m_incomingMessagesLock.lock();

        // subscribe to events channel
        // do so in another thread as this call will block
        Executors.newSingleThreadExecutor().execute(() -> {
                this.m_jedisSubscribe.subscribe(this, this.m_redisSubscribeChannel);
        });

        // get existing events
        List<String> messages = this.m_jedisRequests.lrange(this.m_redisMessageListKey, 0, -1);
        for (String m : messages){
            this.onMessage(this.m_redisMessageListKey, m);
        }

        // unlock messages so subscribe can process messages
        this.m_incomingMessagesLock.unlock();

        // publish join
        this.publishJoined();
    }

    @Override
    public void stop() {
        // stop subscription
        this.unsubscribe();

        // close jedis
        if (this.m_jedisRequests != null) {
            this.m_jedisRequests.close();
            this.m_jedisRequests = null;
        }
        if (this.m_jedisSubscribe != null) {
            this.m_jedisSubscribe.close();
            this.m_jedisSubscribe = null;
        }
    }

    @Override
    public void publishLocalCommentChanged(long address, String comment){
        // make the object
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "comment_changed");
        jsonObject.addProperty("address", address);
        jsonObject.addProperty("comment", comment);

        // send it
        this.publish(jsonObject, false);
    }

    @Override
    public void publishLocalSymbolRenamedGlobal(long address, String oldName, String newName) {

    }

    @Override
    public void publishLocalSymbolRenamedLocal(String functionName, String oldName, String newName) {

    }

    private void publishJoined(){
        // make the object
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "joined");
        jsonObject.addProperty("username", this.m_username);

        // send it
        this.publish(jsonObject, false);
    }

    private void publish(JsonObject jsonObject, boolean permanent){
        // add the rev sync enhanced version tag
        jsonObject.addProperty("version", "rse_1.0");

        // add the username source tag
        jsonObject.addProperty("source", this.m_username);

        // add program hash tag
        jsonObject.addProperty("program_hash", this.m_programHash);

        // publish to the channel
        this.m_jedisSubscribe.publish(this.m_redisSubscribeChannel, jsonObject.getAsString());

        // add to the list
        if (permanent) {
            this.m_jedisRequests.rpush(this.m_redisMessageListKey, jsonObject.getAsString());
        }
    }

    // JedisPubSub
    @Override
    public void onMessage(String channel, String message) {
        this.m_incomingMessagesLock.lock();
        super.onMessage(channel, message);
        this.m_listener.onInfoMessage("received message from redis channel: " + channel + " => " + message);

        // todo parse it and apply to the program


        this.m_incomingMessagesLock.unlock();
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
        this.m_listener.onInfoMessage("subscribed to redis channel " + channel);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
        this.m_listener.onInfoMessage("unsubscribed from redis channel " + channel);
    }
}
