package br.com.route.gateways.http.jsons;

import br.com.route.domain.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResource {

  @NotEmpty(message = "{field.not.null}")
  private String from;

  @NotEmpty(message = "{field.not.null}")
  private String destiny;

  @NotEmpty(message = "{field.not.null}")
  private String departureTime;

  @NotEmpty(message = "{field.not.null}")
  private String arrivalTime;

  public RouteResource(final Route route) {
    this.from = route.getFrom();
    this.destiny = route.getDestiny();
    this.departureTime = route.getDepartureTime();
    this.arrivalTime = route.getArrivalTime();
  }

  public Route toDomain() {
    return new Route(from, destiny, departureTime, arrivalTime);
  }
}
