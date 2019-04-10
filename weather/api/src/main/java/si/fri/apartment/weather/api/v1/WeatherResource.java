package si.fri.apartment.weather.api.v1;

import net.aksingh.owmjapis.api.APIException;
import si.fri.apartment.weather.services.WeatherBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherResource {

    @Inject
    private WeatherBean weatherBean;

    @GET
    @Path("/{city}")
    public Response getWeather(@PathParam("city") String city) {
        try {
            return weatherBean.getWeather(city);
        } catch (APIException e) {
            return Response.status(Response.Status.OK).entity("Ni bilo mogoce dostopati do APIja").build();
        }
    }

    @GET
    @Path("/test")
    public Response getWeather() {
        return Response.status(Response.Status.OK).entity(weatherBean.getTest()).build();
    }

}
