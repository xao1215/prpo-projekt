package si.fri.prpo.projekt;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM uporabnik u"),
                @NamedQuery(name = "Uporabnik.getName", query = "SELECT u FROM uporabnik u WHERE u.ime = :name"),
                @NamedQuery(name = "Uporabnik.getId", query = "SELECT u FROM uporabnik u WHERE u.id = :id"),
                @NamedQuery(name = "Uporabnik.getUsername", query = "SELECT u FROM uporabnik u WHERE u.username  = :username"),
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uporabnik_id")
    private Integer id;
    private String ime;
    private String priimek;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "uporabnik")
    private List<Termin> termini;

    @JsonbTransient
    @OneToMany(mappedBy = "lastnik")
    private List<Postaja> postaje;

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

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }

    public List<Postaja> getPostaje() {
        return postaje;
    }

    public void setPostaje(List<Postaja> postaje) {
        this.postaje = postaje;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo(){
        return String.format("id = %d, %s %s, username = %s, email = %s",this.id,this.ime,this.priimek,this.username,this.email);
    }
}
