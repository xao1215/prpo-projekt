package si.fri.prpo.projekt;

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


        List<Postaja> postaje = postajeZrno.getPostaje();
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        DtoTermin novtermin = new DtoTermin(Date.valueOf("2020-12-1"), new Time(1,0,0), new Time(2,0,0),-1, postaje.get(0).getId());
        utp.dodajTermin( novtermin );
        utp.dodajTermin( novtermin );
        out.println( "\nafter insertion termin\n");
        printaj(out);


        upp.dodajPostajo( new DtoPostaja("wow","omg","mlaka",4,"vikend",uporabniki.get(0).getId()));
        out.println( "\nafter insertion postaja\n");
        printaj(out);

        utp.spremeniUporabnikaTermina(3,2);
        out.println( "\nafter change user of termin\n");
        printaj(out);

        utp.spremeniUporabnikaTermina(3,3);
        out.println( "\nafter change user of termin, but zasedeno\n");
        printaj(out);

        utp.spremeniUporabnikaTermina(3,2);
        out.println( "\nafter change user of termin ( same user )\n");
        printaj(out);

    }
    public void printaj( PrintWriter out ){
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();
        for( Uporabnik user : uporabniki ){
            out.println( user.getInfo() );
        }
        List<Termin> termini = terminiZrno.getTermini();
        for( Termin t : termini ){
            Uporabnik temp = null;
            if(t.getUporabnik() != null){
                temp = terminiZrno.getUporabnikOfTermin( t.getUporabnik().getId() );
            }
            out.println( t.getInfo() + " uporabnik --> " + (temp == null ? "/" : temp.getId()));
        }
        List<Postaja> postaje = postajeZrno.getPostaje();
        for( Postaja p : postaje ){
            out.println( p.getInfo() );
        }

    }
}
