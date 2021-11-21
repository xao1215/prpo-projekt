package si.fri.prpo.projekt.zrno;

import si.fri.prpo.projekt.Postaja;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.dto.DtoPostaja;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjePostajZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;

    @Inject
    private PostajeZrno postajeZrno;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za upravljanje postaj");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za upravljanje postaj");
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }


    public Postaja dodajPostajo(DtoPostaja postaja ){
        Postaja nova = new Postaja();

        boolean valid = true;
        valid = valid && postaja.getIme().length() < 50 && postaja.getIme().length() > 0;
        valid = valid && postaja.getCena_polnjenja() >= 0;
        valid = valid && postaja.getLokacija().length() > 0;
        valid = valid && postaja.getSpecifikacije().length() < 1000;
        Uporabnik lastnik = uporabnikiZrno.getUporabnikiById(postaja.getLastnik_id());
        valid = valid && lastnik != null;

        if(valid){
            nova.setCena_polnjenja( postaja.getCena_polnjenja() );
            nova.setIme( postaja.getIme() );
            nova.setLokacija( postaja.getLokacija() );
            nova.setSpecifikacije( postaja.getSpecifikacije() );
            nova.setLastnik( lastnik );
            nova.setObratovalni_cas( postaja.getObratovalni_cas() );
            postajeZrno.savePostaja( nova );

            return nova;
        }

        log.info("napaka pri dodajanju postaje");
        return null;

    }
}
