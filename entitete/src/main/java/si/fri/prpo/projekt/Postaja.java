package si.fri.prpo.projekt;

import javax.persistence.*;

import java.util.List;

@Entity(name = "postaja")
@NamedQueries(value =
        {
                @NamedQuery(name = "Postaja.getAll", query = "SELECT p FROM postaja p"),
                @NamedQuery(name = "Postaja.getAllLokacija", query = "SELECT p FROM postaja p WHERE p.lokacija = :lokacija"),
                @NamedQuery(name = "Postaja.getLessThanCena", query = "SELECT p FROM postaja p WHERE p.cena_polnjenja >= :cena ORDER BY p.cena_polnjenja DESC"),
                @NamedQuery(name = "Postaja.getTermini", query = "SELECT p FROM postaja p WHERE p.id = :postaja"),
        })
public class Postaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String specifikacije;
    private String lokacija;
    private float cena_polnjenja;
    private String obratovalni_cas;

    @OneToOne(mappedBy = "uporabnik")
    private Uporabnik lastnik;

    @OneToMany(mappedBy = "termin")
    private List<Termin> termin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Uporabnik getLastnik() {
        return lastnik;
    }

    public void setLastnik(Uporabnik lastnik) {
        this.lastnik = lastnik;
    }

    public List<Termin> getTermin() {
        return termin;
    }

    public void setTermin(List<Termin> termin) {
        this.termin = termin;
    }




}
