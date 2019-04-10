package si.fri.apartments.payments.resources;

import si.fri.apartments.payments.Payment;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentsResource {

    @GET
    @Path("/{userId}")
    public Response getPayments(@PathParam("userId") int userId) {
        List<Payment> paymentList = new ArrayList<Payment>();

        Payment payment1 = new Payment();
        payment1.setId(1);
        payment1.setAmaount(55);
        payment1.setCurrency("EUR");
        payment1.setPaymentDate(new Date());
        payment1.setPaymentType("Credit Card");
        payment1.setUserId(userId);

        Payment payment2 = new Payment();
        payment2.setId(2);
        payment2.setAmaount(79);
        payment2.setCurrency("USD");
        payment2.setPaymentDate(new Date());
        payment2.setPaymentType("Cash");
        payment2.setUserId(userId);

        paymentList.add(payment1);
        paymentList.add(payment2);

        return Response.ok(paymentList).build();
    }

    @GET
    @Path("/test")
    public Response getTest() {
        return Response.status(Response.Status.OK).entity("to je test user analysis").build();
    }
}
