package si.fri.prpo.projekt;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.projekt.dto.DtoPostaja;
import si.fri.prpo.projekt.dto.DtoTermin;
import si.fri.prpo.projekt.zrno.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    //docker run -d --name postgres-jdbc -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=projekt -p 5432:5432 postgres:13
    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @Inject
    private TerminiZrno terminiZrno;

    @Inject
    private PostajeZrno postajeZrno;

    @Inject
    private UpravljanjeTerminovZrno utp;

    @Inject
    private UpravljanjePostajZrno upp;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        printaj( out );

        uporabnikiZrno.deleteUporabnik(1);
        out.println( "\nafter deletion\n");
        printaj(out);


        List<Postaja> postaje = postajeZrno.getPostaje(new QueryParameters());
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki( new QueryParameters() );

        DtoTermin novtermin = new DtoTermin();
        novtermin.setDan(Date.valueOf("2020-12-1"));
        novtermin.setOd_ura(new Time(1,0,0));
        novtermin.setDo_ura(new Time(2,0,0));
        novtermin.setPostaja_id( postaje.get(0).getId() );
        novtermin.setUporabnik_id(-1);
        //utp.dodajTermin( novtermin );
        utp.dodajTermin( novtermin );
        out.println( "\nafter insertion termin\n");
        printaj(out);

        DtoPostaja dp = new DtoPostaja();
        dp.setIme("wow");
        dp.setCena_polnjenja(10);
        dp.setLokacija("Maribor");
        dp.setSpecifikacije("owfeowifj");
        //dp.setLastnik_id(uporabniki.get(0).getId());
        dp.setLastnik_id(2);
        upp.dodajPostajo( dp );
        out.println( "\nafter insertion postaja\n");
        printaj(out);

        novtermin.setUporabnik_id(2);
        utp.spremeniUporabnikaTermina(novtermin,2);
        out.println( "\nafter change user of termin\n");
        printaj(out);

        novtermin.setUporabnik_id(3);
        utp.spremeniUporabnikaTermina(novtermin,2);
        out.println( "\nafter change user of termin, but zasedeno\n");
        printaj(out);

        novtermin.setUporabnik_id(2);
        utp.spremeniUporabnikaTermina(novtermin,2);
        out.println( "\nafter change user of termin ( same user )\n");
        printaj(out);

    }
    public void printaj( PrintWriter out ){
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki( new QueryParameters() );
        for( Uporabnik user : uporabniki ){
            out.println( user.getInfo() );
        }
        List<Termin> termini = terminiZrno.getTermini(new QueryParameters());
        for( Termin t : termini ){
            Uporabnik temp = null;
            if(t.getUporabnik() != null){
                temp = terminiZrno.getUporabnikOfTermin( t.getUporabnik().getId() );
            }
            out.println( t.getInfo() + " uporabnik --> " + (temp == null ? "/" : temp.getId()));
        }
        List<Postaja> postaje = postajeZrno.getPostaje(new QueryParameters());
        for( Postaja p : postaje ){
            out.println( p.getInfo() );
        }

    }
}
