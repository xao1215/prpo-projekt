package si.fri.prpo.projekt.dto;

import java.util.List;

public class DtoPostaja {

    private String ime;
    private String specifikacije;
    private String lokacija;
    private float cena_polnjenja;
    private String obratovalni_cas;
    private Integer lastnik_id;

    public DtoPostaja(String ime, String specifikacije, String lokacija, float cena_polnjenja, String obratovalni_cas, Integer lastnik_id){
        this.ime = ime;
        this.specifikacije = specifikacije;
        this.lokacija = lokacija;
        this.cena_polnjenja = cena_polnjenja;
        this.obratovalni_cas = obratovalni_cas;
        this.lastnik_id = lastnik_id;
    }
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSpecifikacije() {
        return specifikacije;
    }

    public void setSpecifikacije(String specifikacije) {
        this.specifikacije = specifikacije;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public float getCena_polnjenja() {
        return cena_polnjenja;
    }

    public void setCena_polnjenja(float cena_polnjenja) {
        this.cena_polnjenja = cena_polnjenja;
    }

    public String getObratovalni_cas() {
        return obratovalni_cas;
    }

    public void setObratovalni_cas(String obratovalni_cas) {
        this.obratovalni_cas = obratovalni_cas;
    }

    public Integer getLastnik_id() {
        return lastnik_id;
    }

    public void setLastnik_id(Integer lastnik_id) {
        this.lastnik_id = lastnik_id;
    }
}
