package si.fri.prpo.projekt.izjema;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NeveljavenDtoTerminIzjemaPreslikovalec implements ExceptionMapper<NeveljavenDtoTerminIzjema> {

    @Override
    public Response toResponse(NeveljavenDtoTerminIzjema exception){
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}")
                .build();
    }

}
