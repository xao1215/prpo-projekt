package si.fri.prpo.projekt.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.projekt.Postaja;
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
    public Response vrniPostaje(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Postaja> postaje = postajeZrno.getPostaje(query);

        //List<Postaja> p = postajeZrno.getPostaje();// pridobi uporabnike
        return Response.status(Response.Status.OK).header("X-Total-Count", postajeZrno.getCount(query)).entity( postaje ).build();
    }

    @GET
    @Path("{id}")
    public Response vrniPostajo( @PathParam("id") Integer id){
        Postaja p = postajeZrno.getPostajaById( id );
        return Response.status(Response.Status.OK).entity( p ).build();
    }

    @DELETE
    @Path("{id}")
    public Response zbrisiPostajo( @PathParam("id") Integer id){
        postajeZrno.deletePostaja((id));
        return Response.status(Response.Status.OK).build();
    }

    @POST
    public Response ustvariPostajo(DtoPostaja p){
        Postaja nov = upz.dodajPostajo( p );
        if( nov == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
            return Response.status(Response.Status.OK).entity(nov).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response posodobiPostajo( Postaja p, @PathParam("id") Integer id ){
        p.setId( id );
        return Response.status(Response.Status.OK).entity( postajeZrno.updatePostaja(( p )) ).build();
    }
}
