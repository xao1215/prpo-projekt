package si.fri.prpo.projekt.zrno;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.anotacija.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@BeleziKlice
@ApplicationScoped
public class TerminiZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za termine");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za termine");
        log.info("IDENTIFIKATOR: " + identifikator.toString());

    }

    public List<Termin> getTermini(QueryParameters query) {
        //return em.createNamedQuery("Termin.getAll").getResultList();
        List<Termin> termini = JPAUtils.queryEntities(em, Termin.class, query);
        return termini;
    }
    public Termin getTerminById(Integer id) {
        return (Termin) em.createNamedQuery("Termin.getId").setParameter("id", id).getResultList().get(0);
    }
    public Uporabnik getUporabnikOfTermin(Integer id) {
        return (Uporabnik) em.createNamedQuery("Termin.getUporabnik").setParameter("uporabniktermina", id).getResultList().get(0);
    }
    public List<Termin> getTerminiByDayTime(Date dan, Time od_, Time do_) {
        return em.createNamedQuery("Termin.getAllDayTime").setParameter("dan", dan).setParameter("od", od_).setParameter("do", do_).getResultList();
    }
    public Termin getTerminByExactDayTime(Integer id, Date dan, Time od_, Time do_) {
        List <Termin> l =  em.createNamedQuery("Termin.getExactDayTime").setParameter("postaja", id).setParameter("dan", dan).setParameter("od", od_).setParameter("do", do_).getResultList();
        if( l.size() == 0 ){return null;}
        return l.get(0);
    }

    public Long getCount(QueryParameters query){
        return JPAUtils.queryEntitiesCount(em, Termin.class, query);
    }

    @Transactional
    public Termin saveTermin(Termin t) {
        em.persist(t);
        return t;
    }

    @Transactional
    public Termin updateTermin(Termin t) {
        return em.merge(t);
    }

    @Transactional
    public void deleteTermin(Integer id) {
        Termin old;
        if ((old = em.find(Termin.class, id)) != null) {
            em.remove(old);
        }
    }


}
