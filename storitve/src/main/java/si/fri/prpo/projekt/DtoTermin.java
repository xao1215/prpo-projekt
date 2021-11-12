package si.fri.prpo.projekt;

import java.sql.Date;
import java.sql.Time;

public class DtoTermin {

    private Date dan;
    private Time od_ura;
    private Time do_ura;
    private Integer uporabnik_id;
    private Integer postaja_id;

    public DtoTermin(Date dan, Time od_ura, Time do_ura, Integer uporabnik_id, Integer postaja_id){
        this.dan = dan;
        this.od_ura = od_ura;
        this.do_ura = do_ura;
        this.uporabnik_id = uporabnik_id;
        this.postaja_id = postaja_id;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
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

    public Integer getUporabnik_id() {
        return uporabnik_id;
    }

    public void setUporabnik_id(Integer uporabnik_id) {
        this.uporabnik_id = uporabnik_id;
    }

    public Integer getPostaja_id() {
        return postaja_id;
    }

    public void setPostaja_id(Integer postaja_id) {
        this.postaja_id = postaja_id;
    }

}
