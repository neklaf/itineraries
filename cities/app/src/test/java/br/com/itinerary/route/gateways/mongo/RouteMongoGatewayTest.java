package br.com.itinerary.route.gateways.mongo;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.mongo.repositories.RouteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.MockitoAnnotations.initMocks;

public class RouteMongoGatewayTest {

  @InjectMocks private RouteMongoGateway routeMongoGateway;

  @Mock private RouteRepository routeRepository;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void should_return_all_routes() {

    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.findAll()).thenReturn(Flux.just(route));

    StepVerifier.create(routeMongoGateway.getAll())
        .assertNext(routeReturn -> Assert.assertEquals(route.getId(), routeReturn.getId()))
        .verifyComplete();
  }

  @Test
  public void given_route_name_should_return_route() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.findByCity(Mockito.anyString())).thenReturn(Mono.just(route));

    StepVerifier.create(routeMongoGateway.getByCityName("Sao Paulo"))
        .assertNext(routeReturn -> Assert.assertEquals(route.getId(), routeReturn.getId()))
        .verifyComplete();
  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeRepository.findByCity(Mockito.anyString())).thenReturn(Mono.empty());

    StepVerifier.create(routeMongoGateway.getByCityName("Sao Paulo")).verifyComplete();
  }
}