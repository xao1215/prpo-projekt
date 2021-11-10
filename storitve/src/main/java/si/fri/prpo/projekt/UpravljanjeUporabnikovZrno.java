package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeUporabnikovZrno {


    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za upravljanje uporabnikov");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za upravljanje uporabnikov");
    }

}
