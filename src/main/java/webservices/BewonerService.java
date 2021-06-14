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
import model.Bewoner;

@Path("/bewoner")
public class BewonerService {

  @POST
  @Produces("application/json")
  public Response createBewoner(String jsonBody) {
    Bewoner newBewoner = null;
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

      newBewoner = new Bewoner(bSoortnaam, bKleurnaam, bAantal, bGroepsdier, bType);
      AquariumManager am = AquariumManager.getInstance();
      for (Aquarium aq : am.getAquariumLijst()) {
        if (aq.getNaam().equals(aquariumNaam)) {
          aq.voegBewonerToe(newBewoner);
          am.voegBewonerToe(newBewoner);
        }
      }

    } catch (Exception e) {
      return Response.status(409).entity("Bewoner niet aangemaakt!").build();
    }
    return Response.ok(newBewoner.toString()).build();
  }
}
