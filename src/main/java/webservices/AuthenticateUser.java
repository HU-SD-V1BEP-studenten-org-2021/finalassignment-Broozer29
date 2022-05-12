package webservices;

import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import model.AquariumManager;
import model.User;

@Path("/login")
public class AuthenticateUser {
  final static public Key key = MacProvider.generateKey();

  @POST
  
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
    try {
      AquariumManager am = AquariumManager.getInstance();
      String role = User.validateLogin(username, password);
      if (role == null)
        throw new IllegalArgumentException("No user found!");
      String token = createToken(username, role);
      SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
      return Response.ok(JWT).build();

    } catch (JwtException | IllegalArgumentException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @POST
  @Path("/register")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response registerUser(@FormParam("username") String username, @FormParam("password") String password) {
    AquariumManager am = AquariumManager.getInstance();
    try{
      User newUser = new User(username, password, "User");
      boolean registeredUser = User.registerUser(newUser);
      if (registeredUser) {
      return Response.ok("User gemaakt").build();
      } else {
        return Response.status(Response.Status.UNAUTHORIZED).build();
      }
  } catch (JwtException | IllegalArgumentException e) {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }
  }

  

  private String createToken(String username, String role) throws JwtException {
    Calendar expiration = Calendar.getInstance();
    expiration.add(Calendar.MINUTE, 30);

    return Jwts.builder().setSubject(username).setExpiration(expiration.getTime()).claim("role", role).signWith(SignatureAlgorithm.HS512, key).compact();
  }

  @Path("/deleteOwner")
  @RolesAllowed("Beheerder")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response deleteOwner(@FormParam("username") String username) {
    try {
      User userToRemove = User.getSpecificUser(username);
      AquariumManager am = AquariumManager.getInstance();
      am.verwijderUser(userToRemove);
      return Response.ok("Owner removed").build();
    } catch (JwtException | IllegalArgumentException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

}
