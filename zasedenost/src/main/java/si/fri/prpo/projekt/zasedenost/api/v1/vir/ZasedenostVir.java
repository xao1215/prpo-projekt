package si.fri.prpo.projekt.zasedenost.api.v1.vir;

import si.fri.prpo.projekt.zasedenost.api.v1.dto.DtoPodatki;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.logging.Logger;

@Path("zasedenost")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ZasedenostVir {

    private Logger log = Logger.getLogger(ZasedenostVir.class.getName());
    private HashMap<Integer, int[][]> postajeZasedenost = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        log.info("inicializacija zrna za zasedenost terminov");
    }

    @GET
    @Path("{id}")
    public Response vrniZasedenost( @PathParam("id") Integer postaja_id ){
        return Response.status(Response.Status.OK).entity( postajeZasedenost.get(postaja_id) ).build();
    }

    @GET
    @Path("{id}/dnevi")
    public Response vrniZasedenostDnevi( @PathParam("id") Integer postaja_id ){
        if(postajeZasedenost.get( postaja_id ) == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        int[][] arr = postajeZasedenost.get( postaja_id );
        int[] res = new int[7];
        for(int i = 0; i < 7; i++){
            int total = 0;
            for(int j = 0; j < 24; j++){
                total += arr[i][j];
            }
            res[i] = total;
        }

        return Response.status(Response.Status.OK).entity( res ).build();
    }

    @GET
    @Path("{id}/ure")
    public Response vrniZasedenostUre( @PathParam("id") Integer postaja_id ){
        if(postajeZasedenost.get( postaja_id ) == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        int[][] arr = postajeZasedenost.get( postaja_id );
        int[] res = new int[24];
        for(int i = 0; i < 24; i++){
            int total = 0;
            for(int j = 0; j < 7; j++){
                total += arr[j][i];
            }
            res[i] = total;
        }

        return Response.status(Response.Status.OK).entity( res ).build();
    }

    @POST
    @Path("dodaj")
    public Response dodajZasedenost(DtoPodatki t){
        Integer pid = t.getPostaja_id();

        if(postajeZasedenost.get( pid ) == null){
            postajeZasedenost.put( pid, new int[7][24] );
        }

        int day = t.getDan() == 0 ? 6 : t.getDan() - 1;
        int[][] arr = postajeZasedenost.get( pid );
        for(int i = t.getOd_ura(); i < t.getDo_ura() + 1; i++){
            log.info( i + "+" );
            arr[day][i] += 1;
        }
        postajeZasedenost.put( pid , arr );

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("odstrani")
    public Response odstraniZasedenost(DtoPodatki t){
        Integer pid = t.getPostaja_id();

        int day = t.getDan() == 0 ? 6 : t.getDan() - 1;
        int[][] arr = postajeZasedenost.get( pid );
        for(int i = t.getOd_ura(); i < t.getDo_ura() + 1; i++){
            log.info( i + "-" );
            arr[day][i] -= 1;
        }
        postajeZasedenost.put( pid , arr );

        return Response.status(Response.Status.OK).build();
    }

}
