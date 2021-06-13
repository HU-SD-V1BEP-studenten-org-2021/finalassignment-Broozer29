package model;

public class Eigenaar {
  private String voornaam;
  private String achternaam;
  private Aquarium aquarium;
  
  public Eigenaar(String voornaam, String achternaam) {
    this.voornaam = voornaam;
    this.achternaam = achternaam;
  }

  public String getVoornaam() {
    return voornaam;
  }

  public void setVoornaam(String voornaam) {
    this.voornaam = voornaam;
  }

  public String getAchternaam() {
    return achternaam;
  }

  public void setAchternaam(String achternaam) {
    this.achternaam = achternaam;
  }

  public Aquarium getAquarium() {
    return aquarium;
  }

  public void setAquarium(Aquarium aquarium) {
    this.aquarium = aquarium;
  }

  @Override
  public String toString() {
    return "Eigenaar [voornaam=" + voornaam + ", achternaam=" + achternaam + ", aquarium=" + aquarium + "]";
  }
  
  
}
