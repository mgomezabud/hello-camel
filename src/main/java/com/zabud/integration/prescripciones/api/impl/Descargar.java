package com.zabud.integration.prescripciones.api.impl;


import org.springframework.stereotype.Component;
import com.zabud.integration.prescripciones.api.shared.ApiEndpointBase;

@Component
public class Descargar extends ApiEndpointBase {

  public Descargar () {
    super("direct:getPrescripciones", "/prescripciones/indexar");
  }

}
