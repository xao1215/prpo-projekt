package si.fri.prpo.projekt.api.v1;


import org.eclipse.jetty.util.StringUtil;
import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.dto.DtoTermin;
import si.fri.prpo.projekt.zrno.TerminiZrno;
import si.fri.prpo.projekt.zrno.UpravljanjeTerminovZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Path("termini")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TerminiVir {

    @Inject
    private TerminiZrno terminiZrno;

    @Inject
    private UpravljanjeTerminovZrno utz;

    @GET
    public Response vrniTermine(){
        List<Termin> termini = terminiZrno.getTermini();
        return Response.status(Response.Status.OK).entity( termini ).build();
    }

    @GET
    @Path("{id}")
    public Response vrniTermin( @PathParam("id") Integer id){
        Termin t = terminiZrno.getTerminById( id );
        return Response.status(Response.Status.OK).entity( t ).build();
    }

    @DELETE
    @Path("{id}")
    public Response zbrisiTermin( @PathParam("id") Integer id){
        terminiZrno.deleteTermin((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    public Response ustvariTermin(String s){

        //System.out.println(nov.getDan().toString() + nov.getDo_ura().toString());
        //return Response.status(Response.Status.OK).entity( terminiZrno.saveTermin(deserialize(s))).build();
        Termin t = utz.dodajTermin(deserializeDto(s));
        if( t == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
            return Response.status(Response.Status.OK).entity(t).build();
        }
        /*{
            "dan": "2000-01-05",
                "od_ura": "23:00:00",
                "do_ura": "23:01:00",
                "postaja_id": 2
        }*/
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnikaTermina( String s, @PathParam("id") Integer id ){
        Termin nov = utz.spremeniUporabnikaTermina( deserializeDto(s), id );
        //System.out.println( nov.getUporabnik() == null ? "null" : "ni nul" );
        if( nov == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
            return Response.status(Response.Status.OK).entity(nov).build();
        }
        //nov.setId( id );
        //return Response.status(Response.Status.OK).entity( terminiZrno.updateTermin(( nov )) ).build();
    }

    /*
    @PUT
    @Path("{id}")
    public Response posodobiTermin( String s, @PathParam("id") Integer id ){
        Termin nov = deserialize(s);

        nov.setId( id );
        return Response.status(Response.Status.OK).entity( terminiZrno.updateTermin(( nov )) ).build();
    }*/

    public Termin deserialize( String s ){
        s = s.substring(s.indexOf(":") + 2);
        s = s.substring(0, s.indexOf("}"));
        String date = s.substring(1,s.indexOf(',')-1);

        s = s.substring( s.indexOf(":") + 2 );
        String odd = s.substring(1,s.indexOf(',')-1);

        s = s.substring(s.indexOf("a") + 5 );
        String doo = s.substring(0,s.indexOf('"'));

        Termin nov = new Termin();
        nov.setDan( new Date(
                Integer.parseInt(date.substring(0,4)) - 1900,
                Integer.parseInt(date.substring(5,7)) -1 ,
                Integer.parseInt(date.substring(8))+1
        ));
        nov.setOd_ura( new Time(
                Integer.parseInt(odd.substring(0,2))+1,
                Integer.parseInt(odd.substring(3,5)),
                Integer.parseInt(odd.substring(6))
        ));
        nov.setDo_ura( new Time(
                Integer.parseInt(doo.substring(0,2))+1,
                Integer.parseInt(doo.substring(3,5)),
                Integer.parseInt(doo.substring(6))
        ));
        return nov;
    }

    public DtoTermin deserializeDto( String s ){
        s = s.substring(s.indexOf(":") + 2);
        s = s.substring(0, s.indexOf("}"));
        String date = s.substring(1,s.indexOf(',')-1);

        s = s.substring( s.indexOf(":") + 2 );
        String odd = s.substring(1,s.indexOf(',')-1);

        s = s.substring(s.indexOf("a") + 5 );
        String doo = s.substring(0,s.indexOf('"'));

        s = s.substring(s.indexOf("d") + 5 );
        String postaja_id = s.substring(0, s.indexOf('"'));

        s = s.substring(s.indexOf("d") + 5 );
        String uporabnik_id = s.substring(0, s.indexOf('"'));

        DtoTermin nov = new DtoTermin();
        nov.setDan( new Date(
                Integer.parseInt(date.substring(0,4)) - 1900,
                Integer.parseInt(date.substring(5,7)) -1 ,
                Integer.parseInt(date.substring(8))+1
        ));
        nov.setOd_ura( new Time(
                Integer.parseInt(odd.substring(0,2))+1,
                Integer.parseInt(odd.substring(3,5)),
                Integer.parseInt(odd.substring(6))
        ));
        nov.setDo_ura( new Time(
                Integer.parseInt(doo.substring(0,2))+1,
                Integer.parseInt(doo.substring(3,5)),
                Integer.parseInt(doo.substring(6))
        ));
        nov.setPostaja_id( Integer.parseInt(postaja_id));
        nov.setUporabnik_id( Integer.parseInt(uporabnik_id));

        System.out.println( nov.getUporabnik_id() );
        return nov;
    }
}
