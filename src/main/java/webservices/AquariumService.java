package webservices;


import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;
import model.Ornament;
import model.Toebehoren;
import security.MySecurityContext;

@Path("/aquarium")
public class AquariumService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAquariums() {
    AquariumManager am = AquariumManager.getInstance();
    JsonArrayBuilder jab = Json.createArrayBuilder();
    JsonObjectBuilder job = Json.createObjectBuilder();

    for (Aquarium a : am.getAquariumLijst()) {
      job.add("Naam", a.getNaam());
      
      String bewonerNamen = "";
      String toebehorenNamen = "";
      String ornamentNamen = "";
      for (Bewoner b : a.getBewonerLijst()) {
        bewonerNamen += b.getKleurNaam();
      }
      job.add("Bewoner", bewonerNamen);
      
      for (Toebehoren t : a.getToebehorenLijst()) {
        toebehorenNamen += t.getModel();
      }
      job.add("Toebehoren", toebehorenNamen);

      for (Ornament o : a.getOrnamentLijst()) {
        ornamentNamen += o.getNaam();
      }
      job.add("Ornament", ornamentNamen);
      jab.add(job);
    }

    JsonArray array = jab.build();
    return Response.ok(array.toString()).build();
  }

  @POST
  @RolesAllowed("Beheerder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAquarium(@Context MySecurityContext sc, @FormParam("aqNaam") String aqNaam, @FormParam("aqLengte") int aqLengte, @FormParam("aqBreedte") int aqBreedte,
      @FormParam("aqHoogte") int aqHoogte, @FormParam("aqBodemsoort") String aqBodemsoort, @FormParam("aqWatersoort") String aqWatersoort) {
    AquariumManager am = AquariumManager.getInstance();
    Aquarium newAquarium = null;
    try {
      if (aqLengte < 1 || aqBreedte < 1 || aqHoogte < 1) {
        return Response.status(409).entity("Aquarium niet aangemaakt, alsjeblieft alleen positieve getallen").build();
      }
      newAquarium = new Aquarium(aqNaam, aqLengte, aqBreedte, aqHoogte, aqBodemsoort, aqWatersoort);
      am.voegAquariumToe(newAquarium);
    } catch (Exception e) {
      return Response.status(409).entity("Aquarium niet aangemaakt!").build();
    }
    return Response.ok(newAquarium.toString()).build();
  }

  @DELETE
  @RolesAllowed("Beheerder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteAquarium(@Context MySecurityContext sc, @FormParam("aqNaam") String aqNaam) {
    AquariumManager am = AquariumManager.getInstance();
    try {
      am.verwijderAquarium(aqNaam);
    } catch (Exception e) {
      return Response.status(409).entity("Aquarium kon niet verwijderd worden!").build();
    }
    return Response.ok("Aquarium succesvol verwijderd!").build();
  }
}
