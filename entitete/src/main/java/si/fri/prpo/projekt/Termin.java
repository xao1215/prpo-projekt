package si.fri.prpo.projekt;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "termin")
@NamedQueries(value =
        {
                @NamedQuery(name = "Termin.getAll", query = "SELECT t FROM termin t"),
                @NamedQuery(name = "Termin.getAllFromPostaja", query = "SELECT t FROM termin t WHERE t.id_postaja = :postaja"),
                @NamedQuery(name = "Termin.getAllDay", query = "SELECT t FROM termin t WHERE t.datum = :datum"),
                @NamedQuery(name = "Termin.getAllDayTime", query = "SELECT t FROM termin t WHERE t.datum = :datum AND ((t.od_ura BETWEEN :od AND :do) OR (t.do_ura BETWEEN :od AND :do)) "),
        })
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean prosto;
    private Date dan;
    private Time od_ura;
    private Time do_ura;
    @OneToOne
    //@JoinColumn(name = "id_uporabnik")
    private Uporabnik uporabnik;
    @OneToOne
    @JoinColumn(name = "id_postaja")
    private Postaja postaja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isProsto() {
        return prosto;
    }

    public void setProsto(boolean prosto) {
        this.prosto = prosto;
    }

    public Date getDatum() {
        return dan;
    }

    public void setDatum(Date dan) {
        this.dan = dan;
    }

    public Time getOd_ura() {
        return od_ura;
    }

    public void setOd_ura(Time od_ura) {
        this.od_ura = od_ura;
    }

    public Time getDo_ura() {
        return do_ura;
    }

    public void setDo_ura(Time do_ura) {
        this.do_ura = do_ura;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Postaja getPostaja() {
        return postaja;
    }

    public void setPostaja(Postaja postaja) {
        this.postaja = postaja;
    }
}
