package si.fri.prpo.projekt;

import javax.persistence.*;
import java.util.List;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getName", query = "SELECT u FROM uporabnik u WHERE u.name = :name"),
                @NamedQuery(name = "Uporabnik.getId", query = "SELECT u FROM uporabnik u WHERE u.id = :id"),
                @NamedQuery(name = "Uporabnik.getUsername", query = "SELECT u FROM uporabnik u WHERE u.username  = :username"),
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String priimek;
    private String username;
    private String email;
    @OneToMany
    @JoinColumn(name = "id_uporabnik")
    private List<Termin> termin;
    @OneToOne
    @JoinColumn(name = "id_lastnik")
    private Postaja postaja;

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

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Termin> getTermin() {
        return termin;
    }

    public void setTermin(List<Termin> termin) {
        this.termin = termin;
    }

    public Postaja getPostaja() {
        return postaja;
    }

    public void setPostaja(Postaja postaja) {
        this.postaja = postaja;
    }
}
