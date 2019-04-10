package si.fri.apartment.customersupport.api.v1;

import si.fri.apartment.customersupport.model.FaqEntity;
import si.fri.apartment.customersupport.services.SupportBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/support")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupportResource {

    @Inject
    private SupportBean supportBean;

    @GET
    @Path("/test")
    public Response getTest() {
        return Response.ok("to je test support").build();
    }

    @GET
    @Path("/faq")
    public Response getFaq() {
        try {
            return Response.status(Response.Status.OK).entity(supportBean.getFaqs()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no FAQs found.").build();
        }
    }

    @GET
    @Path("/complains")
    public Response getComplains() {
        try {
            return Response.status(Response.Status.OK).entity(supportBean.getComplains()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no FAQs found.").build();
        }
    }

    @POST
    public Response addComplain(FaqEntity complain) {
        complain = supportBean.addComplain(complain);
        if (complain.getId() != 0)
            return Response.status(Response.Status.CREATED).entity(complain).build();
        return Response.status(Response.Status.CONFLICT).entity(complain).build();
    }
}
