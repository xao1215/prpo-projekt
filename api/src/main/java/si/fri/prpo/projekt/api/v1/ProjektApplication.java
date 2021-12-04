package si.fri.prpo.projekt.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@SecurityScheme(securitySchemeName = "openid-connect",
        type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "http://auth-server-url/.well-known/openid-configuration")
@ApplicationPath("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(title = "PolnilnePostajeApi",
                version = "v1.0.0",
                contact = @Contact(name = "Jaz Jaz",
                        email = "jaz.jaz@kumuluz.com",
                        url = "http://api.kumuluz.com" ),
                license = @License(name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0")),
        servers = @Server(url = "http://localhost:8080"),
        security = @SecurityRequirement(name = "openid-connect"))
public class ProjektApplication extends Application {
}

// /openapi
// /api-specs/ui