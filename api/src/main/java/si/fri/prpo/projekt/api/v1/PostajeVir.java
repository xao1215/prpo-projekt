package si.fri.prpo.projekt.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.projekt.Postaja;
import si.fri.prpo.projekt.Termin;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.dto.DtoPostaja;
import si.fri.prpo.projekt.zrno.PostajeZrno;
import si.fri.prpo.projekt.zrno.UpravljanjePostajZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("postaje")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PostajeVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private PostajeZrno postajeZrno;

    @Inject
    private UpravljanjePostajZrno upz;

    @GET
    @Operation(summary = "Pridobi postaje", description = "Vrne vse postaje aplikacije.")
    @APIResponses({
            @APIResponse(description = "Postaje vrnjene",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Postaja.class)),
                    headers = @Header(name = "X-Total-Count", description = "Pojasni koliko je vseh postaj v bazi", schema = @Schema(type = SchemaType.NUMBER)))
    })
    public Response vrniPostaje(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Postaja> postaje = postajeZrno.getPostaje(query);

        //List<Postaja> p = postajeZrno.getPostaje();// pridobi uporabnike
        return Response.status(Response.Status.OK).header("X-Total-Count", postajeZrno.getCount(query)).entity( postaje ).build();
    }

    @GET
    @Operation(summary = "Pridobi postajo", description = "Vrne postajo.")
    @APIResponses({
            @APIResponse(description = "Postaja najdena", responseCode = "200", content = @Content(schema = @Schema(implementation = Postaja.class))),
            @APIResponse(description = "Postaja ne obstaja", responseCode = "404")
    })
    @Path("{id}")
    public Response vrniPostajo( @PathParam("id") Integer id){
        Postaja p = postajeZrno.getPostajaById( id );
        return Response.status(Response.Status.OK).entity( p ).build();
    }

    @DELETE
    @Operation(summary = "Zbrisi postajo", description = "Zbrise postajo.")
    @APIResponses({
            @APIResponse(description = "Postaja zbrisana", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Postaja.class))),
    })
    @Path("{id}")
    public Response zbrisiPostajo( @PathParam("id") Integer id){
        postajeZrno.deletePostaja((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Operation(summary = "Ustvari postajo", description = "Ustvari postajo")
    @APIResponses({
            @APIResponse(description = "Postaja ustvarjena", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DtoPostaja.class))),
            @APIResponse(description = "Napaka pri posodabljanju termina, neustrezni podatki", responseCode = "406")
    })
    public Response ustvariPostajo(DtoPostaja p){
        Postaja nov = upz.dodajPostajo( p );
        if( nov == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
            return Response.status(Response.Status.OK).entity(nov).build();
        }
    }

    @PUT
    @Operation(summary = "Posodobi postajo", description = "Posodobi postajo.")
    @APIResponses({
            @APIResponse(description = "Postaja posodobljena", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Postaja.class))),
    })
    @Path("{id}")
    public Response posodobiPostajo( Postaja p, @PathParam("id") Integer id ){
        p.setId( id );
        return Response.status(Response.Status.OK).entity( postajeZrno.updatePostaja(( p )) ).build();
    }
}
