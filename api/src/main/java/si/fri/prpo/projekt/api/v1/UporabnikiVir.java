package si.fri.prpo.projekt.api.v1;

import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.zrno.UporabnikiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @GET
    public Response vrniUporabnike(){
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();// pridobi uporabnike
        return Response.status(Response.Status.OK).entity( uporabniki ).build();
    }

    @GET
    @Path("{id}")
    public Response vrniUporabnika( @PathParam("id") Integer id){
        Uporabnik uporabnik = uporabnikiZrno.getUporabnikiById( id );
        return Response.status(Response.Status.OK).entity( uporabnik ).build();
    }

    @DELETE
    @Path("{id}")
    public Response zbrisiUporabnika( @PathParam("id") Integer id){
        uporabnikiZrno.deleteUporabnik((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    public Response ustvariUporabnika(Uporabnik uporabnik){
        return Response.status(Response.Status.OK).entity( uporabnikiZrno.saveUporabnik(uporabnik) ).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiUporabnika( Uporabnik uporabnik, @PathParam("id") Integer id ){
        uporabnik.setId( id );
        return Response.status(Response.Status.OK).entity( uporabnikiZrno.updateUporabnik(( uporabnik )) ).build();
    }


}