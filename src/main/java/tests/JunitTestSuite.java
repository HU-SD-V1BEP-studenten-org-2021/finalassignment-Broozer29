package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Aquarium;
import model.AquariumManager;
import model.Bewoner;
import model.Ornament;
import model.Toebehoren;
import model.User;

public class JunitTestSuite {


  @Test
  void testEigenaar() {
    //Test het maken van een eigenaar. Voeg hem 2x toe en controleer of hij maar 1x in de lijst staat.
    User nieuweEigenaar = new User("Bruus", "Riezebos", "Eigenaar");
    AquariumManager am = AquariumManager.getInstance();
    
    am.voegUserToe(nieuweEigenaar);
    am.voegUserToe(nieuweEigenaar);
    assertEquals(am.getUserLijst().size(), 1);
    
    //Test het verwijderen van de eigenaar
    am.verwijderUser(nieuweEigenaar);
    assertEquals(am.getUserLijst().size(), 0);
  }
  
  @Test
  void testBewoner() {
    //Test het aanmaken van een bewoner. Voeg hem 2x toe en controleer of hij maar 1x in de lijst staat met een aantal van 2
    Bewoner nieuweBewoner = new Bewoner("Soortnaam", "Kleurnaam", 1, true, "type");
    AquariumManager am = AquariumManager.getInstance();
    
    am.voegBewonerToe(nieuweBewoner);
    am.voegBewonerToe(nieuweBewoner);
    assertEquals(am.getBewonerLijst().size(), 1);
    assertEquals(nieuweBewoner.getAantal(), 2);
    
    //Test het maken van een nieuwe bewoner om te controleren dat de bewonerlijst goed aangepast wordt.
    Bewoner nieuweBewonerTwee = new Bewoner("NaamSoort", "NaamKleur", 1, true, "type");
    am.voegBewonerToe(nieuweBewonerTwee);
    assertEquals(am.getBewonerLijst().size(), 2);
  }
  
  @Test
  void testToebehoren() {
    //Test het aanmaken van een toebehoren. Voeg hem 2x toe en controleer of hij maar 1x in de lijst staat.
    Toebehoren nieuweToebehoren = new Toebehoren("Model", 1, "Omschrijving");
    AquariumManager am = AquariumManager.getInstance();
    
    am.voegToebehorenToe(nieuweToebehoren);
    am.voegToebehorenToe(nieuweToebehoren);
    assertEquals(am.getToebehorenLijst().size(), 1);
  }
  
  @Test
  void testOrnament() {
    //Test het aanmaken van een ornament. Voeg hem 2x toe en controleer of hij maar 1x in de lijst staat.
    Aquarium nieuwAquarium = new Aquarium("Naam", 10, 10, 10, "bodemsoort", "watersoort");
    Ornament nieuwOrnament = new Ornament("Naam", "Omschrijving", "Kleur", true);
    
    nieuwAquarium.voegOrnamentToe(nieuwOrnament);
    nieuwAquarium.voegOrnamentToe(nieuwOrnament);
    assertEquals(nieuwAquarium.getOrnamentLijst().size(), 1);
  }
  
  @Test 
  void testAquarium(){
    //Test het aanmaken van een aquarium. Voeg hem 2x toe en controleer of hij maar 1x in de lijst staat.
    Aquarium nieuwAquarium = new Aquarium("Naam", 10, 10, 10, "bodemsoort", "watersoort");
    AquariumManager am = AquariumManager.getInstance();
    
    am.voegAquariumToe(nieuwAquarium);
    am.voegAquariumToe(nieuwAquarium);
    assertEquals(am.getAquariumLijst().size(), 1);
    
    //Test het verwijderen van een aquarium
    Aquarium nieuwAquariumTwee = new Aquarium("NaamTwee", 10, 10, 10, "bodemsoort", "watersoort");
    am.voegAquariumToe(nieuwAquariumTwee);
    assertEquals(am.getAquariumLijst().size(), 2);
    am.verwijderAquarium("NaamTwee");
    assertEquals(am.getAquariumLijst().size(), 1);
  }
  
  @Test
  void testAquariumBekijken() {
    Aquarium nieuwAquarium = new Aquarium("Naam", 10, 10, 10, "bodemsoort", "watersoort");
    AquariumManager am = AquariumManager.getInstance();
    am.voegAquariumToe(nieuwAquarium);
    
    Bewoner nieuweBewoner = new Bewoner("Soortnaam", "Kleurnaam", 1, true, "type");
    Toebehoren nieuweToebehoren = new Toebehoren("Model", 1, "Omschrijving");
    Ornament nieuwOrnament = new Ornament("Naam", "Omschrijving", "Kleur", true);
    
    nieuwAquarium.voegBewonerToe(nieuweBewoner);
    nieuwAquarium.voegOrnamentToe(nieuwOrnament);
    nieuwAquarium.voegToebehorenToe(nieuweToebehoren);
    
    assertEquals(am.getAquariumLijst().size(), 1);
    assertEquals(nieuwAquarium.getBewonerLijst().size(), 1);
    assertEquals(nieuwAquarium.getOrnamentLijst().size(), 1);
    assertEquals(nieuwAquarium.getToebehorenLijst().size(), 1);
  }
}
