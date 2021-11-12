package si.fri.prpo.projekt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeTerminovZrno{

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;

    @Inject
    private PostajeZrno postajeZrno;

    @Inject
    private TerminiZrno terminiZrno;

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za upravljanje terminov");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za upravljanje terminov");
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    public Termin dodajTermin( DtoTermin termin ){
        Termin nov = new Termin();
        if( checkIfValid(termin) && terminiZrno.getTerminByExactDayTime( termin.getPostaja_id(), termin.getDan(), termin.getOd_ura(), termin.getDo_ura() ) == null ){
           // nov.setOznaka( termin.getOznaka() );
            nov.setDan( termin.getDan() );
            nov.setOd_ura( termin.getOd_ura() );
            nov.setDo_ura( termin.getDo_ura() );
            nov.setPostaja( postajeZrno.getPostajaById(termin.getPostaja_id()) );

            return terminiZrno.saveTermin( nov );
        }
        log.info("napaka pri dodajanju termina");
        return null;
    }

    public Termin spremeniUporabnikaTermina( DtoTermin termin ){

        // ce uporabnik null -> set id, ce uporabnik !null, check if dto.id = termin.uporabnik.id, if not dont chagne
        return new Termin();
    }

    public boolean checkIfValid( DtoTermin termin ){
        if(termin.getDan() != null && termin.getDo_ura() != null && termin.getOd_ura() != null /*&& termin.getOznaka() != null*/ && postajeZrno.getPostajaById(termin.getPostaja_id()) != null){
            LocalTime doo = LocalTime.parse( termin.getDo_ura().toString() );
            LocalTime odd = LocalTime.parse( termin.getOd_ura().toString() );
            if(odd.isBefore(doo)){
                return true;
            }
        }
        return false;
    }



}
