package si.fri.prpo.projekt;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();

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

        uporabnikiZrno.deleteUporabnik(1);
        out.println( "after deletion");

        uporabniki = uporabnikiZrno.getUporabniki();
        for( Uporabnik user : uporabniki ){
            out.println( user.getInfo() );
        }
        termini = terminiZrno.getTermini();
        for( Termin t : termini ){
            out.println( t.getInfo() );
        }
        postaje = postajeZrno.getPostaje();
        for( Postaja p : postaje ){
            out.println( p.getInfo() );
        }

    }
}
