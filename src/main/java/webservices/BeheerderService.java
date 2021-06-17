package webservices;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import model.User;
import security.MySecurityContext;

@Path("/ownerservice")
@RolesAllowed("Beheerder")
public class BeheerderService {
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @RolesAllowed("Beheerder")
  public Response registerOwner(@Context MySecurityContext sc, @FormParam("username") String username) {
    System.out.println("Aangekomen");
    try {
      System.out.println("Aangekomen");
      User userToPromote = User.getSpecificUser(username);
      userToPromote.setRole("Eigenaar");
      return Response.ok().build();
    } catch (JwtException | IllegalArgumentException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }
}
