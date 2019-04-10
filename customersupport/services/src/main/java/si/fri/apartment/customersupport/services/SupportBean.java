package si.fri.apartment.customersupport.services;

import si.fri.apartment.customersupport.model.FaqEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SupportBean {

    @PersistenceContext
    private EntityManager em;

    public List<FaqEntity> getFaqs() {
        Query q = em.createNamedQuery("FAQ.getAll");
        if (q.getResultList() == null)
            throw new NotFoundException();
        List<FaqEntity> faqs = new ArrayList<>();
        for (FaqEntity faq : (List<FaqEntity>) q.getResultList()) {
            if (!faq.getQuestion().equals("") && !faq.getAnswer().equals(""))
                faqs.add(faq);
        }
        return faqs;
    }

    public List<String> getComplains() {
        Query q = em.createNamedQuery("FAQ.getAll");
        if (q.getResultList() == null)
            throw new NotFoundException();
        List<String> complains = new ArrayList<>();
        for (FaqEntity faq : (List<FaqEntity>) q.getResultList()) {
            if (faq.getComplain() != null && !faq.getComplain().equals(""))
                complains.add(faq.getComplain());
        }
        return complains;
    }

    public FaqEntity addComplain(FaqEntity complain) {
        try {
            beginTx();
            em.persist(complain);
            em.flush();
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return complain;
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

