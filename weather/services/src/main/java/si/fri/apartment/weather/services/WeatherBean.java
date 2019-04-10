package si.fri.apartment.weather.services;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class WeatherBean {

    private static final String API_KEY = "bcd1a26c4d949ad71fac6356a1eba52c";
    private static final String BASE_URL = "api.openweathermap.org/data/2.5/weather";

    public Response getWeather (String city) throws APIException {
        OWM owm = new OWM(API_KEY);
        CurrentWeather cwd = owm.currentWeatherByCityName(city);
        return Response.status(Response.Status.OK).entity(cwd).build();
    }

    public String getTest() {
        return "test";
    }

}