package si.fri.prpo.projekt.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.projekt.Uporabnik;
import si.fri.prpo.projekt.zrno.UporabnikiZrno;

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
public class UporabnikiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @GET
    public Response vrniUporabnike(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        //List<Uporabnik> uporabniki = uporabnikiZrno.pridobiUporabnike(query);

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki(query);// pridobi uporabnike
        return Response.status(Response.Status.OK).header("X-Total-Count", uporabnikiZrno.getCount(query)).entity( uporabniki ).build();
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