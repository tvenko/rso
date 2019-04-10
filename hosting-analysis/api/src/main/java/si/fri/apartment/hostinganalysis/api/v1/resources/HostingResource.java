package si.fri.apartment.hostinganalysis.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.apartment.hostinganalysis.api.v1.Analysis;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/hostinganalysis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class HostingResource {
    @GET
    public Response getAnalysis() {
        Analysis analysis = new Analysis(15, 122, 4012);
        return Response.ok(analysis).build();
    }
}
