package com.example.agent.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;


@Configuration
public class ConnectorConfig {
	
	 @Value("${server.port.http}")
     private int serverPortHttp;
	
	 @Value("${server.port}")
	 private int serverPortHttps;
	 
	 @Bean
	 public ServletWebServerFactory  servletContainer() {
		 TomcatServletWebServerFactory  tomcat = new TomcatServletWebServerFactory() {
		 @Override
		 protected void postProcessContext(Context context) {
			 SecurityConstraint securityConstraint = new SecurityConstraint();
			 securityConstraint.setUserConstraint("CONFIDENTIAL");
			 SecurityCollection collection = new SecurityCollection();
			 collection.addPattern("/*");
			 securityConstraint.addCollection(collection);
			 context.addConstraint(securityConstraint);
			 }
			 };
		 tomcat.addAdditionalTomcatConnectors(getHttpConnector());
		 return tomcat;
	 }
	 
	 
	 private Connector getHttpConnector() {
		 Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		 connector.setScheme("http");
		 connector.setPort(serverPortHttp);
		 connector.setSecure(false);
		 connector.setRedirectPort(serverPortHttps);
		 return connector;
	}
}
