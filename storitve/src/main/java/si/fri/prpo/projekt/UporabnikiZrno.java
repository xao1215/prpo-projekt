package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikiZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za uporabnike");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za uporabnike");
    }

    public List<Uporabnik> getUporabniki() {
        return em.createNamedQuery("Uporabnik.getAll").getResultList();
    }
    public Uporabnik getUporabnikiById(Integer id) {
        return (Uporabnik) em.createNamedQuery("Uporabnik.getId").setParameter("id", id).getResultList().get(0);
    }
    public List<Uporabnik> getUporabnikiByName(String name) {
        return em.createNamedQuery("Uporabnik.getName").setParameter("name", name).getResultList();
    }
    public List<Uporabnik> getUporabnikiByUsername(String username) {
        return em.createNamedQuery("Uporabnik.getUsername").setParameter("username", username).getResultList();
    }









    //iz 2. nal
    public List<Uporabnik> getUporabnikiCriteria(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> q = cb.createQuery(Uporabnik.class);
        Root<Uporabnik> u = q.from(Uporabnik.class);
        q.select(u);
        return em.createQuery(q).getResultList();
    }
}
