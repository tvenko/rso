package si.fri.apartment.apartments.api.v1.resources;

import si.fri.apartment.apartments.api.v1.configuration.RestProperties;
import si.fri.apartment.apartments.models.ApartmentEntity;
import si.fri.apartment.apartments.services.ApartmentManagerBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.regex.Pattern;

@ApplicationScoped
@Path("/apartments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApartmentsResource {

    @Inject
    private ApartmentManagerBean apartmentManager;

    @GET
    public Response getapartments() {
        try {
            return Response.status(Response.Status.OK).entity(apartmentManager.getApartments()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("no apartments found.").build();
        }
    }

    @GET
    @Path("/{apartmentId}")
    public Response getApartment(@PathParam("apartmentId") long apartmentId) {
        try {
            return Response.status(Response.Status.OK).entity(apartmentManager.getApartment(apartmentId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Apartment with id: " + apartmentId + " not found.").build();
        }
    }

    @GET
    @Path("/{apartmentId}/weather")
    public Response getWeather(@PathParam("apartmentId") long apartmentId) {
        try {
            ApartmentEntity apartment = apartmentManager.getApartment(apartmentId);
            return apartmentManager.getWeather(apartment.getLocation());
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Apartment with id: " + apartmentId + " not found.").build();
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Klic na weather ni uspel").build();
        }
    }

    @GET
    @Path("/{apartmentId}/transport")
    public Response getTransport(@PathParam("apartmentId") long apartmentId) {
        try {
            ApartmentEntity apartment = apartmentManager.getApartment(apartmentId);
            return apartmentManager.getTransport(apartment.getLocation());
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Apartment with id: " + apartmentId + " not found.").build();
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Klic na transport ni uspel").build();
        }
    }

    @GET
    @Path("/{apartmentId}/attractions")
    public Response getAttractions(@PathParam("apartmentId") long apartmentId) {
        try {
            ApartmentEntity apartment = apartmentManager.getApartment(apartmentId);
            return apartmentManager.getAttractions(apartment.getLocation());
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Apartment with id: " + apartmentId + " not found.").build();
        } catch (InternalServerErrorException e) {
            return Response.status(Response.Status.OK).entity("Klic na attractions ni uspel").build();
        }
    }

    @GET
    @Path("/analysis")
    public Response getAnalysis() {
        return apartmentManager.getApartmentsAnalysis();
    }

    @POST
    public Response createApartment(ApartmentEntity apartment) {
        if (validateNewApartment(apartment))
            return Response.status(Response.Status.BAD_GATEWAY).entity("Data not valid").build();
        apartment = apartmentManager.addApartment(apartment);
        if (apartment.getId() != 0)
            return Response.status(Response.Status.CREATED).entity(apartment).build();
        return Response.status(Response.Status.CONFLICT).entity(apartment).build();
    }

    //  Cross-Origin Resource Sharing (CORS) Filter: Unsupported HTTP method ERROR
    @PUT
    @Path("{id}")
    public Response updateApartment(ApartmentEntity apartment, @PathParam("id") long id) {
        apartment = apartmentManager.updateApartment(apartment, id);

        if(apartment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(apartment.getId() != 0)
            return Response.status(Response.Status.OK).entity(apartment).build();
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    //  Cross-Origin Resource Sharing (CORS) Filter: Unsupported HTTP method ERROR
    @DELETE
    @Path("{id}")
    public Response deleteApartment(@PathParam("id") long id) {
        if (apartmentManager.deleteApartment(id))
            return Response.status(Response.Status.GONE).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private boolean validateNewApartment(ApartmentEntity apartment) {
        if(apartment.getLocation().length()>128)
            return true;
        if(apartment.getDescription().length()>128)
            return true;
        if(apartment.getPersons()<1)
            return true;
        if(apartment.getSquareMeters()<1)
            return true;

        return false;
    }
}