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

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;
import security.MySecurityContext;

@Path("/bewoner")
public class BewonerService {

  @POST
  @RolesAllowed("Beheerder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createBewoner(@Context MySecurityContext sc, @FormParam("bSoortnaam") String bSoortnaam, @FormParam("bKleurnaam") String bKleurnaam, @FormParam("bAantal") int bAantal,
      @FormParam("bGroepsdier") String bGroepsdierString, @FormParam("bType") String bType, @FormParam("aqNaam") String aquariumNaam) {
    boolean bGroepsdier = false;
    if (bGroepsdierString.equals("true")) {
      bGroepsdier = true;
    }
    Bewoner newBewoner = null;
    try {
      if (bAantal < 1) {
        return Response.status(409).entity("Bewoner niet aangemaakt, alsjeblieft een positief getal").build();
      }
      newBewoner = new Bewoner(bSoortnaam, bKleurnaam, bAantal, bGroepsdier, bType);
      AquariumManager am = AquariumManager.getInstance();
      for (Aquarium aq : am.getAquariumLijst()) {
        if (aq.getNaam().equals(aquariumNaam)) {
          aq.voegBewonerToe(newBewoner);
          am.voegBewonerToe(newBewoner);
        } else {
          return Response.status(409).entity("Bewoner niet aangemaakt, er bestaat geen aquarium met die naam!").build();
        }
      }
    } catch (Exception e) {
      return Response.status(409).entity("Bewoner niet aangemaakt!").build();
    }
    return Response.ok(newBewoner.toString()).build();
  }
}
