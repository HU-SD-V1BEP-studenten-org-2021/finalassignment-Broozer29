package webservices;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;

@Path("/bewoner")
public class BewonerDAO {

  @POST
  @Produces("application/json")
  public String createBewoner(String jsonBody) {
    JsonObjectBuilder responseObject = Json.createObjectBuilder();
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();

      String bSoortnaam = jsonObject.getString("bSoortnaam");
      String bKleurnaam = jsonObject.getString("bKleurnaam");
      int bAantal = jsonObject.getInt("bAantal");
      boolean bGroepsdier = jsonObject.getBoolean("bGroepsdier");
      String bType = jsonObject.getString("bType");
      String aquariumNaam = jsonObject.getString("aqNaam");

      Bewoner bewoner = new Bewoner(bSoortnaam, bKleurnaam, bAantal, bGroepsdier, bType);
      AquariumManager am = AquariumManager.getInstance();
      for (Aquarium aq : am.getAquariumLijst()) {
        if (aq.getNaam().equals(aquariumNaam)) {
          aq.voegBewonerToe(bewoner);
          am.voegBewonerToe(bewoner);
        }
      }
      
      responseObject.add("message", "Bewoner aangemaakt en toegevoegd!");
    } catch (Exception e) {
      responseObject.add("message", "Error: " + e.getMessage());
    }
    return responseObject.build().toString();
  }
}
