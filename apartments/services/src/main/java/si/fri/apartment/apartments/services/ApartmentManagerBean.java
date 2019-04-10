package si.fri.apartment.apartments.services;


import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.apartment.apartments.models.ApartmentEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ApartmentManagerBean {

    @PersistenceContext
    private EntityManager em;

    private Client httpClient;

    @Inject
    @DiscoverService("weather")
    private Optional<String> baseUrlWeather;

    @Inject
    @DiscoverService("apartmentsanalysis")
    private Optional<String> baseUrlApartmentsAnalysis;

    @Inject
    @DiscoverService("transport")
    private Optional<String> baseUrlTransport;

    @Inject
    @DiscoverService("attractions")
    private Optional<String> baseUrlAttractions;

    public Response getWeather(String city) throws InternalServerErrorException {
        if (baseUrlWeather.isPresent()) {
            httpClient = ClientBuilder.newClient();
            System.out.println("accessing /v1/weather" + city);
            return httpClient.target(baseUrlWeather.get() + "/v1/weather/" + city).request().get();
        }
        return null;
    }

    public Response getTransport(String location) throws InternalServerErrorException {
        if (baseUrlTransport.isPresent()) {
            httpClient = ClientBuilder.newClient();
            return httpClient.target(baseUrlTransport.get() + "/v1/transport/" + location).request().get();
        }
        return null;
    }

    public Response getAttractions(String city) throws InternalServerErrorException {
        if (baseUrlAttractions.isPresent()) {
            httpClient = ClientBuilder.newClient();
            return httpClient.target(baseUrlAttractions.get() + "/v1/attractions/" + city).request().get();
        }
        return null;
    }

    public Response getApartmentsAnalysis () {
        if (baseUrlApartmentsAnalysis.isPresent()) {
            try {
                System.out.println("Accessing /v1/apartmentsanalysis ");
                return httpClient.target(baseUrlApartmentsAnalysis.get() + "/v1/apartmentsanalysis").request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);

            }
        }
        return null;
    }


    public ApartmentEntity getApartment(long id) {
        ApartmentEntity apartment = em.find(ApartmentEntity.class, id);
        if(apartment == null)
            throw new NotFoundException();
        return apartment;
    }

    public List<ApartmentEntity> getApartments() {
        Query q = em.createNamedQuery("Apartments.getAll");
        if (q.getResultList() == null)
            throw new NotFoundException();
        return (List<ApartmentEntity>) q.getResultList();
    }

    public ApartmentEntity addApartment(ApartmentEntity apartment) {
        try {
            beginTx();
            em.persist(apartment);
            em.flush();
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return apartment;
    }

    public ApartmentEntity updateApartment(ApartmentEntity apartment, long id) {
        ApartmentEntity u = em.find(ApartmentEntity.class, id);

        if(u == null)
            return null;
        try {
            beginTx();
            apartment.setId(u.getId());
            apartment = em.merge(apartment);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return apartment;
    }

    public boolean deleteApartment(long id) {
        ApartmentEntity apartment = em.find(ApartmentEntity.class, id);
        if (apartment != null) {
            try {
                beginTx();
                em.remove(apartment);
                commitTx();
                return true;
            } catch (Exception e) {
                rollbackTx();
                return false;
            }
        }
        return false;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
