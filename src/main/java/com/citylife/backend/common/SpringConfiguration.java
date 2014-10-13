package com.citylife.backend.common;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;

import java.util.LinkedHashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月8日 下午4:18:18
 */
@Configuration
public class SpringConfiguration {

	public @Bean
	ClientConfig clientConfig() {

		String connectionUrl = "http://192.168.40.184:9200";

		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(connectionUrl);
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);
		clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED, false);
		return clientConfig;
	}

	public @Bean
	JestClient jestClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig());
		return factory.getObject();
	}
}
