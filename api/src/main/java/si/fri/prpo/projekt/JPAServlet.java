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

        out.println();
        out.println( "after deletion");
        out.println();

        printaj(out);

        out.println();
        out.println( "after insertion");
        out.println();

        List<Postaja> postaje = postajeZrno.getPostaje();
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        DtoTermin novtermin = new DtoTermin(Date.valueOf("2020-12-1"), new Time(1,0,0), new Time(2,0,0),-1, postaje.get(0).getId());
        utp.dodajTermin( novtermin );
        utp.dodajTermin( novtermin );

        printaj(out);

        out.println();
        out.println( "after insertion postaja");
        out.println();
        upp.dodajPostajo( new DtoPostaja("wow","omg","mlaka",4,"vikend",uporabniki.get(0).getId()));

        printaj(out);

    }
    public void printaj( PrintWriter out ){
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();
        for( Uporabnik user : uporabniki ){
            out.println( user.getInfo() );
        }
        List<Termin> termini = terminiZrno.getTermini();
        for( Termin t : termini ){
            out.println( t.getInfo() );
        }
        List<Postaja> postaje = postajeZrno.getPostaje();
        for( Postaja p : postaje ){
            out.println( p.getInfo() );
        }
    }
}
