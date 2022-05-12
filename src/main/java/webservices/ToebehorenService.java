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
import model.Toebehoren;
import security.MySecurityContext;

@Path("/toebehoren")
public class ToebehorenService {

  @POST
  @RolesAllowed("Beheerder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createToebehoren(@Context MySecurityContext sc, @FormParam("tModel") String tModel,
      @FormParam("tSerienummer") int tSerienummer,
      @FormParam("tOmschrijving") String tOmschrijving,
      @FormParam("aqNaam") String aqNaam
      ) {
    AquariumManager am = AquariumManager.getInstance();
    Toebehoren newToebehoren = null;
    try {
      if (tSerienummer < 1) {
        return Response.status(409).entity("Toebehoren niet aangemaakt, alsjeblieft alleen een getal boven de 0").build();
      }
      newToebehoren = new Toebehoren(tModel, tSerienummer, tOmschrijving);
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegToebehorenToe(newToebehoren);
          am.voegToebehorenToe(newToebehoren);
        } else {
          return Response.status(409).entity("Toebehoren niet aangemaakt, er bestaat geen aquarium met die naam!").build();
        }
      }
    } catch (Exception e) {
      return Response.status(409).entity("Toebehoren niet aangemaakt!").build();
    }
    return Response.ok(newToebehoren.toString()).build();
  }

}
