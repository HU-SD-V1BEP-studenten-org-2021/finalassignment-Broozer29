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
import model.Ornament;

@Path("/ornament")
public class OrnamentService {

  @POST
  @Produces("application/json")
  public Response createOrnament(String jsonBody) {
    AquariumManager am = AquariumManager.getInstance();
    Ornament newOrnament = null;
    try {
      StringReader strReader = new StringReader(jsonBody);
      JsonReader jsonReader = Json.createReader(strReader);
      JsonObject jsonObject = jsonReader.readObject();

      String oNaam = jsonObject.getString("oNaam");
      String oOmschrijving = jsonObject.getString("oOmschrijving");
      String oKleur = jsonObject.getString("oKleur");
      Boolean oKanopluchtpomp = jsonObject.getBoolean("oKanopluchtpomp");
      String aqNaam = jsonObject.getString("aqNaam");

      newOrnament = new Ornament(oNaam, oOmschrijving, oKleur, oKanopluchtpomp);
      for (Aquarium a : am.getAquariumLijst()) {
        if (a.getNaam().equals(aqNaam)) {
          a.voegOrnamentToe(newOrnament);
        }
      }
    } catch (Exception e) {
      return Response.status(409).entity("Ornament niet aangemaakt!").build();
    }
    return Response.ok(newOrnament.toString()).build();
  }

}
