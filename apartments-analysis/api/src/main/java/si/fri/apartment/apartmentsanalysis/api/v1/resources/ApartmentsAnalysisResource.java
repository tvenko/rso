package si.fri.apartment.apartmentsanalysis.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/apartmentsanalysis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class ApartmentsAnalysisResource {
    @GET
    public Response getAnalysis() {
        return Response.ok("Analysis results: There are a lot of unused apartments.").build();
    }
}
