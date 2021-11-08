package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
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

    @Transactional
    public Uporabnik saveUporabnik(Uporabnik u) {
        em.persist(u);
        return u;
    }

    @Transactional
    public Uporabnik updateUporabnik(Uporabnik u) {
        em.merge(u);
        return u;
    }

    @Transactional
    public void deleteUporabnik(Integer id) {
        Uporabnik old;
        if ((old = em.find(Uporabnik.class, id)) != null) {
            Postaja p = old.getPostaja();
            if( p != null){
                for( Termin t : p.getTermini() ){
                    em.remove(t);
                }
                em.remove(p);
            }
            em.remove(old);
        }
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
