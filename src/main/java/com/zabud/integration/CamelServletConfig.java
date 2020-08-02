package com.zabud.integration;

import javax.servlet.http.HttpServlet;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import lombok.val;

@Component
public class CamelServletConfig extends RouteBuilder {

  @Value("${integration.camel.path}")
  private String contextPath;

  @Value("${server.port}")
  private String serverPort;

  @Override
  public void configure() throws Exception {
    configureRest();
  }

  private void configureRest() {
    restConfiguration().contextPath(contextPath).port(serverPort).enableCORS(true)
        .apiContextPath("/api-doc").apiProperty("api.title", "Integration REST API")
        .apiProperty("api.version", "v1").apiContextRouteId("doc-api").component("servlet")
        .bindingMode(RestBindingMode.json);
  }



}
