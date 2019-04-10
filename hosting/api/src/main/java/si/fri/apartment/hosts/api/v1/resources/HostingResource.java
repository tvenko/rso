package si.fri.apartment.hosts.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.apartment.hosts.api.v1.configuration.RestProperties;
import si.fri.apartment.hosts.models.UserEntity;
import si.fri.apartment.hosts.services.HostingBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;

@RequestScoped
@Path("/hosts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class HostingResource {

    private Logger log = LogManager.getLogger(HostingResource.class.getName());
    @Context
    private UriInfo uriInfo;

    @Inject
    private RestProperties restProperties;

    @Inject
    private HostingBean hostingBean;

    @GET
    public Response getHosts() {
        try {
            return Response.status(Response.Status.OK).entity(hostingBean.getHostings()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no users found.").build();
        }
    }

    @GET
    @Path("/{userId}/recommended")
    public Response getRecommendedApartment(@PathParam("userId") int userId) {
        try {
            return Response.status(Response.Status.OK).entity(hostingBean.getRecommendedApartment(userId)).build();
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Api can't be reached \n" + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{hostingId}")
    public Response getHosting(@PathParam("hostingId") String hostingId) {
//        Hosting hosting = hostingBean.getHosting(hostingId);
//        if(hosting == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        return Response.status(Response.Status.OK).entity(hosting).build();
        UserEntity user = hostingBean.getUser(hostingId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(user).build();
        //return Response.status(Response.Status.OK).build();
    }


    @POST
    @Path("/healthy")
    public Response setHealth(Boolean healthy) {
        log.info("CHECK HEALTH");
        restProperties.setHealthy(healthy);
        //log.info("Setting health to " + healthy);
        return Response.ok().build();
    }


    @GET
    @Path("/analysis")
    public Response getAnalysis() {
        return hostingBean.getHostingAnalysis();
    }
}
