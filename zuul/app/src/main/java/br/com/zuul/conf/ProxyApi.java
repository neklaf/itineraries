package br.com.zuul.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Configuration
@AllArgsConstructor
public class ProxyApi {

  private ZuulProperties properties;

  @Primary
  @Bean
  public SwaggerResourcesProvider swaggerResourcesProvider() {
    return () -> properties
        .getRoutes()
        .values()
        .stream()
        .map(route -> createResource(route.getServiceId(), route.getId(), "2.0"))
        .collect(Collectors.toList());
  }

  private SwaggerResource createResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation("/" + location + "/v2/api-docs");
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }
}
