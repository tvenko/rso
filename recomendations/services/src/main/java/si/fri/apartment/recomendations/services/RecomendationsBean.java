package si.fri.apartment.recomendations.services;

import com.kumuluz.ee.discovery.annotations.DiscoverService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Random;

public class RecomendationsBean {

    private Client httpClient;

    @Inject
    @DiscoverService("apartments")
    private Optional<String> baseUrlApartments;


    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public Response getRecomendetApartments(int userId) {
        Random rnd = new Random();
        int idApartment = rnd.nextInt(5);
        System.out.println("rand id apartment: " + idApartment);
        if (baseUrlApartments.isPresent()) {
            try {
                System.out.println("Accessing /v1/apartments ");
                return httpClient.target(baseUrlApartments.get() + "/v1/apartments/" + idApartment).request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);

            }
        }
        return null;
    }

    public String getTest() {
        return "to je test poslan by recommendations";
    }

}
