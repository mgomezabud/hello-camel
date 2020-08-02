package com.zabud.integration.prescripciones.api.shared;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;

public abstract class ApiEndpointBase extends RouteBuilder {

  private static final List<String> HEADERS = Arrays.asList("regimen", "nit", "token", "fecha");

  private final String postOperation;
  private final String path;
  
  public ApiEndpointBase(String postOperation, String path) {
    super();
    this.postOperation = postOperation;
    this.path = path;
    
  }

  @Override
  public void configure() throws Exception {
    configureApi();
  }

  private void configureApi() {
    rest()
    .post(path)
    .produces(MediaType.APPLICATION_JSON_VALUE).route()
    .log(LoggingLevel.INFO, "Init: ${in.body}")
    .split(body())
    .parallelProcessing()
    .process(new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        Map<?, ?> peticion = (Map<?, ?>) exchange.getMessage().getBody();
        setHeaders(exchange, peticion);
      }
    }).log(LoggingLevel.INFO, "Final Api Response: ${in.body}")
    .to(postOperation)
    .endRest();
  }

  protected void setHeaders(Exchange exchange, Map<?, ?> peticion) {
    HEADERS.forEach(e -> setValue(exchange, peticion, e));
  }

  private void setValue(Exchange exchange, Map<?, ?> peticion, String value) {
    exchange.getMessage().setHeader(value, peticion.get(value));
  }

}
