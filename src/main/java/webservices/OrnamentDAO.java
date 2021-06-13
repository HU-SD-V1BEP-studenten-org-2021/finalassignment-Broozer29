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
import model.Ornament;

@Path("/ornament")
public class OrnamentDAO {

  @POST
  @Produces("application/json")
  public String createOrnament(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    JsonObjectBuilder responseObject = Json.createObjectBuilder();
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();

      String oNaam = jsonObject.getString("oNaam");
      String oOmschrijving = jsonObject.getString("oOmschrijving");
      String oKleur = jsonObject.getString("oKleur");
      Boolean oKanopluchtpomp = jsonObject.getBoolean("oKanopluchtpomp");
      String aqNaam = jsonObject.getString("aqNaam");

      Ornament newOrnament = new Ornament(oNaam, oOmschrijving, oKleur, oKanopluchtpomp);
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegOrnamentToe(newOrnament);
        }
      }
      
      responseObject.add("message", "Ornament aangemaakt en toegevoegd!");
    } catch (Exception e) {
      responseObject.add("message", "Error: " + e.getMessage());
    }
    return responseObject.build().toString();
  }

}
