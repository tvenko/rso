package si.fri.apartment.hosts.api.v1.resources;

import si.fri.apartment.hosts.api.v1.configuration.RestProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfigResource {

    @Inject
    private RestProperties properties;

    @GET
    @Path("/config")
    public Response test() {
        String response = "{\"booleanprop\": " +properties.getHealthy()+ "}";

        return Response.ok(response).build();
    }
}