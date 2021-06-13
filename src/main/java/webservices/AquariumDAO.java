package webservices;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;
import model.Eigenaar;
import model.Ornament;
import model.Toebehoren;

@Path("/aquarium")
public class AquariumDAO {

  @GET
  @Produces("application/json")
  public String getOrders() {
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
    return array.toString();
  }

  @POST
  @Produces("application/json")
  public String createAquarium(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    JsonObjectBuilder responseObject = Json.createObjectBuilder();
    System.out.println("Ontvangen!");
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
      Aquarium newAquarium = new Aquarium(aqNaam, aqLengte, aqBreedte, aqHoogte, aqBodemsoort, aqWatersoort);
      am.voegAquariumToe(newAquarium);
      responseObject.add("message", "Aquarium gemaakt!");
    } catch (Exception e) {
      responseObject.add("message", "Error: " + e.getMessage());
    }
    return responseObject.build().toString();
  }

}

/*
 * @POST
 * @Produces("application/json") public String createAquarium(String jsonBody) { JsonObjectBuilder responseObject = Json.createObjectBuilder(); try {
 * StringReader strReader = new StringReader(jsonBody); JsonReader jsonReader = Json.createReader(strReader); JsonObject jsonObject = jsonReader.readObject(); }
 * catch (Exception e) { responseObject.add("message", "Error: " + e.getMessage()); } return responseObject.build().toString(); }
 */
