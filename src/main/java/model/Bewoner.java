package model;

public class Bewoner {
  private String soortNaam;
  private String kleurNaam;
  private int aantal;
  private boolean groepsDier;
  private String type;

  public Bewoner(String soortNaam, String kleurNaam, int aantal, boolean groepsDier, String type) {
    this.soortNaam = soortNaam;
    this.kleurNaam = kleurNaam;
    this.aantal = aantal;
    this.groepsDier = groepsDier;
    this.type = type;
  }

  public String getSoortNaam() {
    return soortNaam;
  }

  public void setSoortNaam(String soortNaam) {
    this.soortNaam = soortNaam;
  }

  public String getKleurNaam() {
    return kleurNaam;
  }

  public void setKleurNaam(String kleurNaam) {
    this.kleurNaam = kleurNaam;
  }

  public int getAantal() {
    return aantal;
  }

  public void setAantal(int aantal) {
    this.aantal = aantal;
  }

  public boolean isGroepsDier() {
    return groepsDier;
  }

  public void setGroepsDier(boolean groepsDier) {
    this.groepsDier = groepsDier;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Bewoner [soortNaam=" + soortNaam + ", kleurNaam=" + kleurNaam + ", aantal=" + aantal + ", groepsDier=" + groepsDier + ", type=" + type + "]";
  }

}
