package si.fri.apartment.attractions.api.v1.resource;

import si.fri.apartment.attractions.api.v1.Attraction;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/attractions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttractionResource {
    private List<Attraction> attractions;
    public AttractionResource() {
        this.attractions = new ArrayList<>();

        Attraction ljAttraction = new Attraction();
        ljAttraction.setArea("163.8 km²");
        ljAttraction.setPopulation(278853);
        ljAttraction.setCityName("Ljubljana");
        ljAttraction.setId(1);
        ljAttraction.setListOfAttractions(new String[]{"Cankarjevo nabrezje",
        "Gornji Trg", "Ljubljana Old Town", "Triple Bridge"});

        Attraction londonAttraction = new Attraction();
        londonAttraction.setArea("1,572 km²");
        londonAttraction.setId(2);
        londonAttraction.setCityName("London");
        londonAttraction.setPopulation(8788000);
        londonAttraction.setListOfAttractions(new String[]{"Stonehenge, Windsor Castle, and Bath from London",
        "Oxford, Cotswolds, Stratford-upon-Avon and Warwick Castle Day Trip from London", "Imperial War Museum"});

        attractions.add(ljAttraction);
        attractions.add(londonAttraction);
    }

    @GET
    @Path("/{city}")
    public Response getAttraction(@PathParam("city") String city) {
        for(Attraction attraction : attractions)
            if (attraction.getCityName().toUpperCase().equals(city.toUpperCase()))
                return Response.ok(attraction).build();
        return null;
    }
}
