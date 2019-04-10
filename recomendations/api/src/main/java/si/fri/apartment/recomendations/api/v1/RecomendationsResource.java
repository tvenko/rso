package si.fri.apartment.recomendations.api.v1;

import si.fri.apartment.recomendations.services.RecomendationsBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/recommended")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecomendationsResource {

    @Inject
    private RecomendationsBean recomendationsBean;

    @GET
    @Path("/{idUser}")
    public Response getRecommendedApartment(@PathParam("idUser") int idUser) {
        return recomendationsBean.getRecomendetApartments(idUser);
    }

    @GET
    @Path("/test")
    public Response getTest() {
        return Response.ok("to je test 0").build();
    }

    @GET
    @Path("/test1")
    public Response getTest1() {
        return Response.ok(recomendationsBean.getTest()).build();
    }

}
