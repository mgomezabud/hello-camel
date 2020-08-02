package com.zabud.integration.prescripciones.rest.client;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class MipresDescargarPrescripciones extends RouteBuilder {

  private final String URL = "http://127.0.0.1:8887/mipres/";

  @Override
  public void configure() throws Exception {
    from("direct:getPrescripciones").marshal().json(JsonLibrary.Jackson)
        .setHeader("Content-Type", constant("application/json"))
        .setHeader("Accept", constant("application/json"))
        .setHeader(Exchange.HTTP_METHOD, constant("GET")).removeHeader(Exchange.HTTP_PATH)
        .recipientList(simple(URL
            + "${header.nit}/${header.token}/${header.fecha}/prescripciones.json?&bridgeEndpoint=true"))
        .parallelProcessing().unmarshal().json(JsonLibrary.Jackson).end()
        .to("direct:guardarPrescripciones");
  }

}
