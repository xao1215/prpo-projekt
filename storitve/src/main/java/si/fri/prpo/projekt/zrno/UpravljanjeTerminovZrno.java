package si.fri.prpo.projekt.zrno;

import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.anotacija.BeleziKlice;
import si.fri.prpo.projekt.dto.DtoTermin;
import si.fri.prpo.projekt.izjema.NeveljavenDtoTerminIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Inject
    private UporabnikiZrno uporabnikiZrno;

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

    public Termin dodajTermin(DtoTermin termin ){
        Termin nov = new Termin();
        if( checkIfValid(termin) && terminiZrno.getTerminByExactDayTime( termin.getPostaja_id(), termin.getDan(), termin.getOd_ura(), termin.getDo_ura() ) == null ){
            nov.setDan( termin.getDan() );
            nov.setOd_ura( termin.getOd_ura() );
            nov.setDo_ura( termin.getDo_ura() );
            nov.setPostaja( postajeZrno.getPostajaById(termin.getPostaja_id()) );

            return terminiZrno.saveTermin( nov );
        }
        throw new NeveljavenDtoTerminIzjema("napaka pri dodajanju termina, neveljaven vnos");
        //return null;
    }

    public Termin spremeniUporabnikaTermina( DtoTermin termin, Integer termin_id ){
        Termin t = terminiZrno.getTerminById( termin_id );
        Uporabnik u = uporabnikiZrno.getUporabnikiById( termin.getUporabnik_id() );
        if( t.getUporabnik() == null ){
            if( u != null){
                t.setUporabnik( u );
                return terminiZrno.updateTermin( t );
            }
            log.info("uporabnik ne obstaja");
            //throw new NeveljavenDtoTerminIzjema("napaka pri spremembi uporabnika termina, uporabnik ne obstaja");
        }else{
            if(t.getUporabnik().getId() != termin.getUporabnik_id()){
                log.info("termin je zaseden");
                //throw new NeveljavenDtoTerminIzjema("napaka pri spremembi uporabnika termina, termin je ze zaseden");
            }else{
                // isti uporabnik
                t.setUporabnik(null);
                return terminiZrno.updateTermin( t );
            }
        }
        return null;
    }

    public boolean checkIfValid( DtoTermin termin ){
        if(termin.getDan() != null && termin.getDo_ura() != null && termin.getOd_ura() != null /*&& termin.getOznaka() != null*/ && postajeZrno.getPostajaById(termin.getPostaja_id()) != null){


            LocalTime doo = LocalTime.parse( termin.getDo_ura().toString() );
            LocalTime odd = LocalTime.parse( termin.getOd_ura().toString() );
            System.out.println( odd.toString() + doo.toString() );

            if(odd.isBefore(doo)){

                return true;
            }
        }

        return false;
    }

}
