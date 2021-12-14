package si.fri.prpo.projekt.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.security.annotations.Secure;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.zrno.UporabnikiZrno;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Secure
public class UporabnikiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Pridobi uporabnike", description = "Vrne uporabnike aplikacije.")
    @APIResponses({
            @APIResponse(description = "Uporabniki vrnjeni",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Uporabnik.class)),
                    headers = @Header(name = "X-Total-Count", description = "Pojasni koliko je vseh uporabnikov v bazi", schema = @Schema(type = SchemaType.NUMBER)))
    })
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        //List<Uporabnik> uporabniki = uporabnikiZrno.pridobiUporabnike(query);

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki(query);// pridobi uporabnike
        return Response.status(Response.Status.OK).header("X-Total-Count", uporabnikiZrno.getCount(query)).entity( uporabniki ).build();
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Pridobi uporabnika", description = "Vrne uporabnika.")
    @APIResponses({
            @APIResponse(description = "Uporabnik vrnjen", responseCode = "200", content = @Content(schema = @Schema(implementation = Uporabnik.class))),
            @APIResponse(description = "Uporabnik ne obstaja", responseCode = "404")
    })
    @Path("{id}")
    public Response vrniUporabnika( @PathParam("id") Integer id){
        Uporabnik uporabnik = uporabnikiZrno.getUporabnikiById( id );
        return Response.status(Response.Status.OK).entity( uporabnik ).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Operation(summary = "Zbrisi uporabnika", description = "Zbrise uporabnika.")
    @APIResponses({
            @APIResponse(description = "Uporabnik zbrisan", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Uporabnik.class))),
    })
    @Path("{id}")
    public Response zbrisiUporabnika( @PathParam("id") Integer id){
        uporabnikiZrno.deleteUporabnik((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Operation(summary = "Ustvari uporabnika", description = "Ustvari uporabnika.")
    @APIResponses({
            @APIResponse(description = "Uporabnik ustvarjen", responseCode = "200", content = @Content(schema = @Schema(implementation = Uporabnik.class))),
    })
    public Response ustvariUporabnika(Uporabnik uporabnik){
        return Response.status(Response.Status.OK).entity( uporabnikiZrno.saveUporabnik(uporabnik) ).build();
    }

    @PUT
    @Operation(summary = "Posodobi Uporabnika", description = "Posodobi uporabnika.")
    @APIResponses({
            @APIResponse(description = "Uporabnik posodobljen", responseCode = "200", content = @Content(schema = @Schema(implementation = Uporabnik.class))),
    })
    @Path("{id}")
    public Response posodobiUporabnika( Uporabnik uporabnik, @PathParam("id") Integer id ){
        uporabnik.setId( id );
        return Response.status(Response.Status.OK).entity( uporabnikiZrno.updateUporabnik(( uporabnik )) ).build();
    }


}