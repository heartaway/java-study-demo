package com.java.demo.elasticssearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.List;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/7/7
 * Time: 下午9:26
 */
public class ESClient {

    private static org.elasticsearch.client.Client esClient;

    private static String clusterName = "dev-es-search";
    private static String transportAddress = "localhost";
    private static int transportPort = 9300;

    public static Client getClient() throws Exception{
        if (esClient == null) {
            synchronized (ESClient.class) {
                if (esClient == null) {
                    Settings settings = Settings.settingsBuilder()
                            .put("cluster.name", clusterName)
                            .build();

                    TransportClient transportClient = TransportClient.builder().settings(settings).build();
                    transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(transportAddress), transportPort));
                    List<DiscoveryNode> nodes = transportClient.connectedNodes();
                    if (nodes.isEmpty()) {
                        throw new IllegalStateException("DiscoveryNode node list is empty, it's impossible, quit now");
                    }else{
                        System.out.println("EsClient init success");
                    }
                    esClient = transportClient;
                }
            }
        }

        return esClient;
    }
}
