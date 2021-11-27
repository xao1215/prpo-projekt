package si.fri.prpo.projekt.zrno;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());
    private UUID identifikator;
    private HashMap<String,Integer> tabelaKlicev = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za belezenje klicov");
        identifikator = UUID.randomUUID();
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    @PreDestroy
    public void preDestroy() {
        log.info("unicenje zrna za belezenje klicov");
        log.info("IDENTIFIKATOR: " + identifikator.toString());
    }

    public void zabelezi( String ime ){
        tabelaKlicev.put( ime, tabelaKlicev.get(ime) == null ? 1 : tabelaKlicev.get(ime) + 1 );
        log.info( ime + ": " + tabelaKlicev.get( ime ) );
    }
}
