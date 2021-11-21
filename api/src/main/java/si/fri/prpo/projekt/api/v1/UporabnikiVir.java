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



}