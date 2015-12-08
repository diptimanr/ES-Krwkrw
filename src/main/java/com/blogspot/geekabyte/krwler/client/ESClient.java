package com.blogspot.geekabyte.krwler.client;

import java.io.IOException;
import java.net.URISyntaxException;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.blogspot.geekabyte.krwler.Krwkrw;
import com.blogspot.geekabyte.krwler.interfaces.callbacks.ExitCallbackImpl;
import com.blogspot.geekabyte.krwler.util.ElasticSearchAction;

public class ESClient {

	public static void main(String[] args) {
		
		try{
			Node node = NodeBuilder.nodeBuilder().node();
			Client client = node.client();
			
			ElasticSearchAction action = ElasticSearchAction.builder()
					.convertToPlainText(true)
					.setClient(client)
					.setClusterName("elasticsearch")
					.setDocumentType("page")
					.setHost("localhost")
					.setIndexName("mycstutorial")
					.setPort(9200)
					.buildAction();
		Krwkrw crawler = new Krwkrw(action);
		crawler.setDelay(5);
		
		//Set<String> strings = crawler.crawl("http://www.mycstutorials.com/articles/");
		ExitCallbackImpl exitCallbackImpl = new ExitCallbackImpl();
		exitCallbackImpl.callBack(crawler.crawl("http://www.mycstutorials.com/articles/"));
		crawler.onExit(exitCallbackImpl);
		
		System.exit(0);
		
		}catch(URISyntaxException use){
			System.out.println(use.getMessage());
		}catch(InterruptedException ie){
			System.out.println(ie.getMessage());
		}catch(IOException io){
			System.out.println(io.getMessage());
		}
	}
}
