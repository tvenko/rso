package si.fri.apartment.users.services;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.apartment.users.models.UserEntity;

import javax.annotation.PostConstruct;
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
public class UserManagerBean {

    private Client httpClient;

    @Inject
    private ConfigurationBean configurationBean;

    @PersistenceContext
    private EntityManager em;

    @Inject
    @DiscoverService("recommendations")
    private Optional<String> baseUrlRecommended;

    @Inject
    @DiscoverService("payments")
    private Optional<String> baseUrlPayments;

    @Inject
    @DiscoverService("usersanalysis")
    private Optional<String> baseUrlUserAnalysis;

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

    public Response getPayments(int idUser) {
        if (baseUrlPayments.isPresent()) {
            try {
                String url = baseUrlPayments.get() + "/v1/payments/" + idUser;
                System.out.println("Accessing: " + url );
                return httpClient.target(url).request().get();
            } catch (WebApplicationException | ProcessingException e) {
                System.out.println("Error: " + e.getMessage() );
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public Response getPaymentsTest() {
        if (baseUrlPayments.isPresent()) {
            try {
                String url = baseUrlPayments.get() + "/v1/payments/test";
                return httpClient.target(url).request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public Response getAnalysisTest() {
        if (baseUrlUserAnalysis.isPresent()) {
            try {
                String url = baseUrlPayments.get() + "/v1/useranalysis/test";
                return httpClient.target(url).request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public UserEntity getUser(long id) {
        UserEntity user = em.find(UserEntity.class, id);
        if(user == null)
            throw new NotFoundException();
        return user;
    }

    public String getTestString() {
        return configurationBean.getTestString();
    }

    public List<UserEntity> getUsers() {
        Query q = em.createNamedQuery("Users.getAll");
        if (q.getResultList() == null)
            throw new NotFoundException();
        return (List<UserEntity>) q.getResultList();
    }

    public Response getUserAnalysis () {
        if (baseUrlUserAnalysis.isPresent()) {
            try {
                System.out.println("Accessing /v1/useranalysis ");
                return httpClient.target(baseUrlUserAnalysis.get() + "/v1/useranalysis").request().get();
            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    }

    public UserEntity addUser(UserEntity user) {
        try {
            beginTx();
            em.persist(user);
            em.flush();
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return user;
    }

    public UserEntity updateUser(UserEntity user, long id) {
        UserEntity u = em.find(UserEntity.class, id);

        if(u == null)
            return null;
        try {
            beginTx();
            user.setId(u.getId());
            user = em.merge(user);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return user;
    }

    public boolean deleteUser(long id) {
        UserEntity user = em.find(UserEntity.class, id);
        if (user != null) {
            try {
                beginTx();
                em.remove(user);
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

    public int getFib(int n) {
        if (n <= 1) return n;
        else return getFib(n-1) + getFib(n-2);
    }
}
