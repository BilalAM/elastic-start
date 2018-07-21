package prac;

import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {

        try {
            Settings settings = Settings.builder().put("client.transport.sniff", true).build();
            TransportClient client = new PreBuiltTransportClient(settings).
                    addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                    .field("aNumber", 100)
                    .field("aString", "hello")
                    .field("an object", new String("a"))
                    .endObject();

            IndexResponse response = client.prepareIndex("myindex", "myType", "myId")
                    .setSource(builder).get();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
