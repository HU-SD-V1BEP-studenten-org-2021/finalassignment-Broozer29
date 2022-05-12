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
import model.Ornament;
import security.MySecurityContext;

@Path("/ornament")
public class OrnamentService {

  @POST
  @RolesAllowed("Beheerder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createOrnament(@Context MySecurityContext sc, @FormParam("oNaam") String oNaam, @FormParam("oOmschrijving") String oOmschrijving, @FormParam("oKleur") String oKleur,
      @FormParam("oKanopluchtpomp") String oKanopluchtpompString, @FormParam("aqNaam") String aqNaam) {
    AquariumManager am = AquariumManager.getInstance();
    Ornament newOrnament = null;
    try {
      boolean oKanopluchtpomp = false;
      if (oKanopluchtpompString.equals("true")) {
        oKanopluchtpomp = true;
      }
      newOrnament = new Ornament(oNaam, oOmschrijving, oKleur, oKanopluchtpomp);
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegOrnamentToe(newOrnament);
        } else {
          return Response.status(409).entity("Toebehoren niet aangemaakt, er bestaat geen aquarium met die naam!").build();
        }
      }
    } catch (Exception e) {
      return Response.status(409).entity("Ornament niet aangemaakt!").build();
    }
    return Response.ok(newOrnament.toString()).build();
  }

}
