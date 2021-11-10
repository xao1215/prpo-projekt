package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeUporabnikovZrno {


    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za upravljanje uporabnikov");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za upravljanje uporabnikov");
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    // na splosno, ne neceseraly za tukaj
    // nastavi uporabnika kot lastnika( dodaj postajo ),
    // dodaj termin za postajo / zbrisi termin / spremeni termin,,,, dodaj termin uporabniku / remove termin za uporabnika
    // preveri kasna polja? username? email? dates? ura? date?

    // dodaj postajo , dodaj termin postaji ( ce si lastnik postsaje), dodaj termin uporabniku
    // CHECK if oznaka v ustvari termin je

}
