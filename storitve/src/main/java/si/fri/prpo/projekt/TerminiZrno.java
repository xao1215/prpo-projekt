package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class TerminiZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za termine");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za termine");
    }

    public List<Termin> getTermini() {
        return em.createNamedQuery("Termin.getAll").getResultList();
    }
    public List<Termin> getTerminiByPostaja(Integer id) {
        return em.createNamedQuery("Termin.getAllFromPostaja").setParameter("postaja", id).getResultList();
    }
    public List<Termin> getTerminiByUporabnik(Integer id) {
        return em.createNamedQuery("Termin.getAllFromUporabnik").setParameter("uporabnik", id).getResultList();
    }
    public List<Termin> getTerminiByDayTime(Date dan, Time od_, Time do_) {
        return em.createNamedQuery("Termin.getAllDayTime").setParameter("dan", dan).setParameter("od", od_).setParameter("do", do_).getResultList();
    }


}
