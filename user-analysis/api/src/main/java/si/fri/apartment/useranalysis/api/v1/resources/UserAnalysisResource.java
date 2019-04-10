package si.fri.apartment.useranalysis.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/useranalysis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class UserAnalysisResource {

    @GET
    public Response getAnalysis() {
        return Response.ok("Analysis results.").build();
    }

    @GET
    @Path("/test")
    public Response getTest() {
        return Response.status(Response.Status.OK).entity("to je test user analysis").build();
    }
}