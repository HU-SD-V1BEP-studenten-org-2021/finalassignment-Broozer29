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
import model.Toebehoren;

@Path("/toebehoren")
public class ToebehorenDAO {

  @POST
  @Produces("application/json")
  public String createToebehoren(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    JsonObjectBuilder responseObject = Json.createObjectBuilder();
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();
      
      String tModel = jsonObject.getString("tModel");
      int tSerienummer = jsonObject.getInt("tSerienummer");
      String tOmschrijving = jsonObject.getString("tOmschrijving");
      String aqNaam = jsonObject.getString("aqNaam");
      
      Toebehoren newToebehoren = new Toebehoren(tModel, tSerienummer, tOmschrijving);
      
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegToebehorenToe(newToebehoren);
          am.voegToebehorenToe(newToebehoren);
        }
      }
      responseObject.add("message", "Toebehoren aangemaakt en toegevoegd!");
    } catch (Exception e) {
      responseObject.add("message", "Error: " + e.getMessage());
    }
    return responseObject.build().toString();
  }

}
