package com.zabud.integration.prescripciones.jms.client;

import java.util.Arrays;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class GuardarPrescripciones extends RouteBuilder {
  
 

  @Override
  public void configure() throws Exception {
    from("direct:guardarPrescripciones").split(body()).parallelProcessing()
    .to("jolt:jolt/spec/prescripcion.json").log(LoggingLevel.INFO, "Procesar: ${in.body}")
    .process(new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        System.out.println("aqui sucede algo de magia con cada prescripcion");
      }
    }).convertBodyTo(String.class).to("google-pubsub://crested-idiom-271717:camel-poc").end();
  }
  
}
