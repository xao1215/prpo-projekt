package si.fri.prpo.projekt.dto;

public class DtoUporabnik {
    private String ime;
    private String priimek;
    private String username;
    private String email;
    private String password;

    public DtoUporabnik(String ime, String priimek, String username, String email, String password){
        this.ime = ime;
        this.priimek = priimek;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
