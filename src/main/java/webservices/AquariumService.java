package webservices;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;
import model.Ornament;
import model.Toebehoren;

@Path("/aquarium")
public class AquariumService {

  @GET
  @Produces("application/json")
  public Response getAquariums() {
    AquariumManager am = AquariumManager.getInstance();
    JsonArrayBuilder jab = Json.createArrayBuilder();
    JsonObjectBuilder job = Json.createObjectBuilder();

    for (Aquarium a : am.getAquariumLijst()) {
      job.add("Naam", a.getNaam());

      for (Bewoner b : a.getBewonerLijst()) {
        job.add("Bewoner", b.toString());
      }

      for (Ornament o : a.getOrnamentLijst()) {
        job.add("Ornament", o.toString());
      }

      for (Toebehoren t : a.getToebehorenLijst()) {
        job.add("Toebehoren", t.toString());
      }
      jab.add(job);
    }

    JsonArray array = jab.build();
    return Response.ok(array.toString()).build();
  }

  @POST
  @Produces("application/json")
  public Response createAquarium(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    Aquarium newAquarium = null;
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();
      String aqNaam = jsonObject.getString("aqNaam");
      int aqLengte = jsonObject.getInt("aqLengte");
      int aqBreedte = jsonObject.getInt("aqBreedte");
      int aqHoogte = jsonObject.getInt("aqHoogte");
      String aqBodemsoort = jsonObject.getString("aqBodemsoort");
      String aqWatersoort = jsonObject.getString("aqWatersoort");
      newAquarium = new Aquarium(aqNaam, aqLengte, aqBreedte, aqHoogte, aqBodemsoort, aqWatersoort);
      am.voegAquariumToe(newAquarium);
    } catch (Exception e) {
      return Response.status(409).entity("Aquarium niet aangemaakt!").build();
    }
    return Response.ok(newAquarium.toString()).build();
  }

  @DELETE
  @Produces("application/json")
  public Response deleteAquarium(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();
      String aqNaam = jsonObject.getString("aqNaam");
      
      am.verwijderAquarium(aqNaam);
    } catch (Exception e) {
      return Response.status(409).entity("Aquarium kon niet verwijderd worden!").build();
    }
    return Response.ok("Aquarium succesvol verwijderd!").build();
  }
}

/*
 * @POST
 * @Produces("application/json") public String createAquarium(String jsonBody) { JsonObjectBuilder responseObject = Json.createObjectBuilder(); try {
 * StringReader strReader = new StringReader(jsonBody); JsonReader jsonReader = Json.createReader(strReader); JsonObject jsonObject = jsonReader.readObject(); }
 * catch (Exception e) { responseObject.add("message", "Error: " + e.getMessage()); } return responseObject.build().toString(); }
 */
