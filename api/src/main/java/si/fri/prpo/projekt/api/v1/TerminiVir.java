package si.fri.prpo.projekt.api.v1;


import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.dto.DtoTermin;
import si.fri.prpo.projekt.zrno.TerminiZrno;
import si.fri.prpo.projekt.zrno.UpravljanjeTerminovZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Path("termini")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TerminiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private TerminiZrno terminiZrno;

    @Inject
    private UpravljanjeTerminovZrno utz;

    @GET
    @Operation(summary = "Pridobi termine", description = "Vrne termine na voljo.")
    @APIResponses({
            @APIResponse(description = "Termini vrnjeni",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Termin.class)),
                    headers = @Header(name = "X-Total-Count", description = "Pojasni koliko je vseh terminov v bazi", schema = @Schema(type = SchemaType.NUMBER)))
    })
    public Response vrniTermine(){
        //List<Termin> termini = terminiZrno.getTermini();

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Termin> termini = terminiZrno.getTermini(query);
        return Response.status(Response.Status.OK).header("X-Total-Count", terminiZrno.getCount(query)).entity( termini ).build();
    }

    @GET
    @Operation(summary = "Pridobi termin", description = "Vrne termin.")
    @APIResponses({
            @APIResponse(description = "Termin pridobljen", responseCode = "200", content = @Content(schema = @Schema(implementation = Termin.class))),
            @APIResponse(description = "Termin ne obstaja", responseCode = "404")
    })
    @Path("{id}")
    public Response vrniTermin( @PathParam("id") Integer id){
        Termin t = terminiZrno.getTerminById( id );
        return Response.status(Response.Status.OK).entity( t ).build();
    }

    @DELETE
    @Operation(summary = "Zbrisi termin", description = "Zbrise termin.")
    @APIResponses({
            @APIResponse(description = "Termin zbrisan", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Termin.class))),
    })
    @Path("{id}")
    public Response zbrisiTermin( @PathParam("id") Integer id){
        terminiZrno.deleteTermin((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Operation(summary = "Ustvari termin", description = "Ustvari termin")
    @APIResponses({
            @APIResponse(description = "Termin uspesno ustvarjen", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DtoTermin.class))),
            @APIResponse(description = "Napaka pri dodajanju termina, neustrezni podatki", responseCode = "406")
    })
    public Response ustvariTermin(String s){

        //System.out.println(nov.getDan().toString() + nov.getDo_ura().toString());
        //return Response.status(Response.Status.OK).entity( terminiZrno.saveTermin(deserialize(s))).build();
        Termin t = utz.dodajTermin(deserializeDto(s));
        if( t == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
            return Response.status(Response.Status.OK).entity(t).build();
        }
    }

    @PUT
    @Operation(summary = "Posodobi uporabnika termina", description = "Posodobi uporabnika termina")
    @APIResponses({
            @APIResponse(description = "Uspesno posodobljen uporabnik termina", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DtoTermin.class))),
            @APIResponse(description = "Napaka pri posodabljanju termina, neustrezni podatki", responseCode = "406")
    })
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
