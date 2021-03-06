package com.example.MegaTravel_XML.soap;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean(name = "accommodation")
	public DefaultWsdl11Definition defaultWsdl11DefinitionAccommodation (XsdSchema accomodationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccommodationPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://megatravel.com/soap");
		wsdl11Definition.setSchema(accomodationSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema accomodationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap.xsd"));
	}
	
	@Bean(name = "message")
	public DefaultWsdl11Definition defaultWsdl11DefinitionMessage (XsdSchema messageSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MessagePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://megatravel.com/soap");
		wsdl11Definition.setSchema(messageSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema messageSchema() {
		return new SimpleXsdSchema(new ClassPathResource("schema_soap.xsd"));
	}
	
	@Bean(name = "test")
	public DefaultWsdl11Definition defaultWsdl11DefinitionTest (XsdSchema testSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("TestPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://megatravel.com/test");
		wsdl11Definition.setSchema(testSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema testSchema() {
		return new SimpleXsdSchema(new ClassPathResource("test.xsd"));
	}
	
	@Bean(name = "accommodationtype")
	public DefaultWsdl11Definition defaultWsdl11DefinitionAccommodationType (XsdSchema accommodationTypeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccommodationTypePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://megatravel.com/soap");
		wsdl11Definition.setSchema(accommodationTypeSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema accommodationTypeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("schema_soap.xsd"));
	}
	
}
