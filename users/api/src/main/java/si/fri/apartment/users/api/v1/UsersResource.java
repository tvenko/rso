package si.fri.apartment.users.api.v1;

import si.fri.apartment.users.models.UserEntity;
import si.fri.apartment.users.services.UserManagerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.regex.Pattern;

@ApplicationScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    private UserManagerBean userManager;

    @Inject
    private RestProperties restProperties;

    @GET
    public Response getUsers() {
        try {
            return Response.status(Response.Status.OK).entity(userManager.getUsers()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no users found.").build();
        }
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") long userId) {
        try {
            return Response.status(Response.Status.OK).entity(userManager.getUser(userId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("User with id: " + userId + " not found.").build();
        }
    }



    @GET
    @Path("/{userId}/recommended")
    public Response getRecommendedApartment(@PathParam("userId") int userId) {
        try {
            return userManager.getRecommendedApartment(userId);
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Api can't be reached \n" + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{userId}/payments")
    public Response getPayments(@PathParam("userId") int userId) {
        try {
            return userManager.getPayments(userId);
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Api can't be reached \n" + e.getMessage()).build();
        }
    }

    @GET
    @Path("/fib/{num}")
    public Response getFibonnachi(@PathParam("num") int num) {
        return Response.status(Response.Status.OK).entity(userManager.getFib(num)).build();
    }

    @GET
    @Path("/test")
    public Response test() {
        String testString = userManager.getTestString();
        return Response.status(Response.Status.OK).entity(testString).build();
    }

    @GET
    @Path("/test/payment")
    public Response testPayment() {
        return userManager.getPaymentsTest();
    }

    @GET
    @Path("/test/analysis")
    public Response testAnalysis() {
        return userManager.getAnalysisTest();
    }

    @POST
    public Response createUser(UserEntity user) {
        if (validateNewUser(user.getUsername(), user.getEmail(), user.getPasswd(), user.getFirstname(), user.getLastname()))
            return Response.status(Response.Status.BAD_GATEWAY).entity("Data not valid").build();
        user = userManager.addUser(user);
        if (user.getId() != 0)
            return Response.status(Response.Status.CREATED).entity(user).build();
        return Response.status(Response.Status.CONFLICT).entity(user).build();
    }

//  Cross-Origin Resource Sharing (CORS) Filter: Unsupported HTTP method ERROR
    @PUT
    @Path("{id}")
    public Response updateUser(UserEntity user, @PathParam("id") long id) {
        user = userManager.updateUser(user, id);

        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(user.getId() != 0)
            return Response.status(Response.Status.OK).entity(user).build();
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    //  Cross-Origin Resource Sharing (CORS) Filter: Unsupported HTTP method ERROR
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") long id) {
        if (userManager.deleteUser(id))
            return Response.status(Response.Status.GONE).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("health")
    public Response getHealth() {
        return Response.ok(restProperties.isHealthy()).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealth(healthy);
        System.out.println("Setting health to " + healthy);
        return Response.ok().build();
    }

    @GET
    @Path("/analysis")
    public Response getAnalysis() {
        return userManager.getUserAnalysis();
    }

    private boolean validateNewUser(String username, String email, String password, String firstname, String lastname) {
        if (username == null || username.equals(""))
            return true;

        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (email == null || email.equals("") || !VALID_EMAIL_ADDRESS_REGEX .matcher(email).find())
            return true;

        if (password.length() < 6)
            return true;

        if (firstname == null || firstname.equals(""))
            return true;

        if (lastname == null || lastname.equals(""))
            return true;

        return false;
    }
}
