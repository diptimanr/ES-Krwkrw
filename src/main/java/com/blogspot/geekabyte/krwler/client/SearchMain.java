package com.blogspot.geekabyte.krwler.client;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

public class SearchMain {

	public static void main(String[] args) throws Exception{
		
		Node node = NodeBuilder.nodeBuilder().clusterName("elasticsearch").node();
		Client client = node.client();
		
		searchDocument(client, "mycstutorial", "page", "html", "insertionsort");
		
		if(node != null){
			node.close();
		}
	}
	
	public static void searchDocument(Client client, String index, String type, String field, String value){
		
		SearchResponse searchResponse = client.prepareSearch(index)
										.setTypes(type)
										.setSearchType(SearchType.QUERY_AND_FETCH)
										.setQuery(QueryBuilders.termQuery(field, value))
										.setFrom(0).setSize(5).setExplain(true)
										.execute()
										.actionGet();
		SearchHit[] results = searchResponse.getHits().getHits();
		for(SearchHit hit : results){
			Map<String, Object> result = hit.getSource();
			System.out.println(result);
		}
	}
}
