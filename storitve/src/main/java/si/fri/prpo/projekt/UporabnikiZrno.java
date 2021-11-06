package si.fri.prpo.projekt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class UporabnikiZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {
        return em.createNamedQuery("Uporabnik.getAll").getResultList();
    }

    public List<Uporabnik> getUporabnikiCriteria(){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Uporabnik> q = cb.createQuery(Uporabnik.class);
        Root<Uporabnik> u = q.from(Uporabnik.class);
        q.select(u);

        return em.createQuery(q).getResultList();
    }
}
