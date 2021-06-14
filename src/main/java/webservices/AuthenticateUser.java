package webservices;

import java.io.StringReader;

import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import model.User;

@Path("/login")

public class AuthenticateUser {
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public Response authenticateUser(String jsonBody) {
    try {

      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();

      if (jsonObject.getBoolean("register") == true) {
        User newUser = new User(jsonObject.getString("username"), jsonObject.getString("password"), "user");
        newUser.registerUser(newUser);
      }
      String role = User.validateLogin(jsonObject.getString("username"), jsonObject.getString("password"));

      if (role == null)
        throw new IllegalArgumentException("No user found!");

      String token = createToken(jsonObject.getString("username"), role);
      SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
      return Response.ok(JWT).build();

    } catch (JwtException | IllegalArgumentException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @Path("/registerOwner")
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public Response registerOwner(String jsonBody) {
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();
      
      User userToPromote = User.getSpecificUser(jsonObject.getString("username"));
      userToPromote.setRole("Eigenaar");
      System.out.println(userToPromote.getUserName() + " Heeft nu de rol van: " + userToPromote.getRole());
      return Response.ok().build();
    } catch (JwtException | IllegalArgumentException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  final static public Key key = MacProvider.generateKey();

  private String createToken(String username, String role) throws JwtException {
    Calendar expiration = Calendar.getInstance();
    expiration.add(Calendar.MINUTE, 30);

    return Jwts.builder().setSubject(username).setExpiration(expiration.getTime()).claim("role", role).signWith(SignatureAlgorithm.HS512, key).compact();
  }

}
