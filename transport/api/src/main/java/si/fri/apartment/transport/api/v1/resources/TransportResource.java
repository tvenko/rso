package si.fri.apartment.transport.api.v1.resources;

import si.fri.apartment.transport.api.v1.Transport;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
@Path("/transport")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransportResource {
    private List<Transport> transport;
    public TransportResource() {
        this.transport = new ArrayList<>();
        transport.add(new Transport("Ljubljana","Glavna zelezniska postaja","Bavarski Dvor",3,2));
        transport.add(new Transport("London","Paddington","Edgware Road",10,1));
        transport.add(new Transport("Berlin","Alexanderplatz Bahnhof","Karolinenstrasse - Waidmannsluster Damm",30,4));
        transport.add(new Transport("New York","Yonkers Station","Port Authority Bus Terminal",15,2));
    }

    @GET
    @Path("/test")
    public Response test() {
        return Response.status(Response.Status.OK).entity("test").build();
    }


    @GET
    @Path("/{location}")
    public Response getTransport(@PathParam("location") String location) {
        for(Transport transport : transport)
            if (transport.getApartmentLocation().toUpperCase().equals(location.toUpperCase()))
                return Response.ok(transport).build();
        return null;
    }
}
