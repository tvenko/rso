package si.fri.apartment.hosts.services;

import si.fri.apartment.hosts.models.Hosting;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.apartment.hosts.models.UserEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class HostingBean {

    @Inject
    private EntityManager em;

    private Client httpClient;

    @Inject
    @DiscoverService("users")
    private Optional<String> baseUrlUsers;

    @Inject
    @DiscoverService("hostinganalysis")
    private Optional<String> baseUrlHostingAnalysis;

    @Inject
    @DiscoverService("recommendations")
    private Optional<String> baseUrlRecommended;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public Response getRecommendedApartment(int idUser) {
        if (baseUrlRecommended.isPresent()) {
            try {
                System.out.println("Accessing /v1/recommended/" + idUser);
                return httpClient.target(baseUrlRecommended.get() + "/v1/recommended/" + idUser).request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public List<Hosting> getHostings() {
        Query q = em.createNamedQuery("Hosts.getAll");
        if (q.getResultList() == null)
            throw new NotFoundException();
        // TODO remove this logic
        List<Hosting> hostings = (List<Hosting>) q.getResultList();
        for(Hosting hosting : hostings)
            hosting.setOwner(getUser(hosting.getId()));

        return hostings;
    }

    public Response getHostingAnalysis () {
        if (baseUrlHostingAnalysis.isPresent()) {
            try {
                System.out.println("Accessing /v1/hostinganalysis ");
                return httpClient.target(baseUrlHostingAnalysis.get() + "/v1/hostinganalysis").request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public UserEntity getUser(String userId) {
        if(baseUrlUsers.isPresent()) {
            try {
                return httpClient
                        .target(baseUrlUsers.get() + "/v1/users/" + userId)
                        .request().get(new GenericType<UserEntity>() {
                        });
            } catch (WebApplicationException | ProcessingException e) {
                System.out.println(e);
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public Hosting createHosting(Hosting hosting) {
        try {
            beginTx();
            em.persist(hosting);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return hosting;
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
