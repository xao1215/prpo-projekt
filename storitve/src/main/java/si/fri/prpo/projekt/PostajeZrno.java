package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
