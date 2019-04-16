package com.example.agent.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

@RestController
@RequestMapping(value="api/agent")
@CrossOrigin(origins = "http://localhost:4201")
public class AgentController {
	
	private KeyStore keyStore;
	private String certPath;
	private String keyStorePass;
	private String uri;
	
	@PostConstruct
	public void init(){
		certPath = "C:\\Users\\Makaric\\Desktop\\globalKeyStore.p12";
		uri = "https://localhost:8443/api/users/communication";
		keyStorePass = "certificatePass1";
		try {
			keyStore = KeyStore.getInstance("PKCS12");
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(
			value = "/communicate",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> communicate(@RequestBody String message) throws Exception{
		System.out.println("Usao u communicate "+ message);
		
		RequestEntity<Object> requestEntity = null;
		
        RestTemplate template = new RestTemplate();
        File file = new File(certPath);
        InputStream is = new FileInputStream(file);
        keyStore.load(is, keyStorePass.toCharArray());
        
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(file, keyStorePass.toCharArray()).loadKeyMaterial(keyStore, keyStorePass.toCharArray()).build();
		HttpClient httpClient = HttpClients.custom().setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).setSSLContext(sslContext).build();
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        
        return template.exchange(uri, HttpMethod.GET, requestEntity, String.class);
	}

}
