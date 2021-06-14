package webservices;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Aquarium;
import model.AquariumManager;
import model.Toebehoren;

@Path("/toebehoren")
public class ToebehorenService {

  @POST
  @Produces("application/json")
  public Response createToebehoren(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    Toebehoren newToebehoren = null;
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();
      
      String tModel = jsonObject.getString("tModel");
      int tSerienummer = jsonObject.getInt("tSerienummer");
      String tOmschrijving = jsonObject.getString("tOmschrijving");
      String aqNaam = jsonObject.getString("aqNaam");
      
      newToebehoren = new Toebehoren(tModel, tSerienummer, tOmschrijving);
      
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegToebehorenToe(newToebehoren);
          am.voegToebehorenToe(newToebehoren);
        }
      }
    } catch (Exception e) {
      return Response.status(409).entity("Toebehoren niet aangemaakt!").build();
    }
    return Response.ok(newToebehoren.toString()).build();
  }

}
