package si.fri.prpo.projekt.zrno;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.projekt.Postaja;
import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.anotacija.BeleziKlice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@BeleziKlice
@ApplicationScoped
public class PostajeZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za postaje");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za postaje");
        log.info("IDENTIFIKATOR: " + identifikator.toString());

    }

    public List<Postaja> getPostaje(QueryParameters query) {
        List<Postaja> postaje = JPAUtils.queryEntities(em, Postaja.class, query);
        return postaje;
        //return em.createNamedQuery("Postaja.getAll").getResultList();
    }
    public List<Postaja> getPostajeByLokacija(String lokacija) {
        return em.createNamedQuery("Postaja.getAllLokacija").setParameter("lokacija", lokacija).getResultList();
    }
    public List<Postaja> getPostajeByCena(float cena) {
        return em.createNamedQuery("Postaja.getLessThanCena").setParameter("cena", cena).getResultList();
    }
    public Postaja getPostajaById(Integer id) {
        return (Postaja) em.createNamedQuery("Postaja.getId").setParameter("postaja", id).getResultList().get(0);
    }

    public Long getCount(QueryParameters query){
        return JPAUtils.queryEntitiesCount(em, Postaja.class, query);
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
