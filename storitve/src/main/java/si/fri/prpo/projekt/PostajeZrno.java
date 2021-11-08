package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class PostajeZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za postaje");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za postaje");
    }

    public List<Postaja> getPostaje() {
        return em.createNamedQuery("Postaja.getAll").getResultList();
    }
    public List<Postaja> getPostajeByLokacija(String lokacija) {
        return em.createNamedQuery("Postaja.getAllLokacija").setParameter("lokacija", lokacija).getResultList();
    }
    public List<Postaja> getPostajeByCena(float cena) {
        return em.createNamedQuery("Postaja.getLessThanCena").setParameter("cena", cena).getResultList();
    }
    public List<Termin> getPostajaTermini(Integer id) {
        return em.createNamedQuery("Postaja.getTermini").setParameter("postaja", id).getResultList();
    }

    @Transactional
    public Postaja savePostaja(Postaja p) {
        em.persist(p);
        return p;
    }

    @Transactional
    public Postaja updatePostaja(Postaja p) {
        em.merge(p);
        return p;
    }

    @Transactional
    public void deletePostaja(Integer id) {
        Postaja old;
        if ((old = em.find(Postaja.class, id)) != null) {
            for( Termin t : old.getTermini() ){
                em.remove(t);
            }
            em.remove(old);
        }
    }

}
