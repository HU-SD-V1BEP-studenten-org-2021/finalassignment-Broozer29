package model;

import java.util.List;
import java.util.ArrayList;

public class Aquarium {
  private String naam;
  private int lengte;
  private int breedte;
  private int hoogte;
  private String bodemsoort;
  private String watersoort;
  private List<Ornament> ornamentenLijst = new ArrayList<Ornament>();
  private List<Bewoner> bewonerLijst = new ArrayList<Bewoner>();
  private List<Toebehoren> toebehorenLijst = new ArrayList<Toebehoren>();


  public Aquarium(String naam, int lengte, int breedte, int hoogte, String bodemsoort, String watersoort) {
    this.naam = naam;
    this.lengte = lengte;
    this.breedte = breedte;
    this.hoogte = hoogte;
    this.bodemsoort = bodemsoort;
    this.watersoort = watersoort;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }

  public float getLengte() {
    return lengte;
  }

  public void setLengte(int lengte) {
    this.lengte = lengte;
  }

  public float getBreedte() {
    return breedte;
  }

  public void setBreedte(int breedte) {
    this.breedte = breedte;
  }

  public float getHoogte() {
    return hoogte;
  }

  public void setHoogte(int hoogte) {
    this.hoogte = hoogte;
  }

  public String getBodemsoort() {
    return bodemsoort;
  }

  public void setBodemsoort(String bodemsoort) {
    this.bodemsoort = bodemsoort;
  }

  public String getWatersoort() {
    return watersoort;
  }

  public void setWatersoort(String watersoort) {
    this.watersoort = watersoort;
  }

  public void voegOrnamentToe(Ornament ornament) {
    boolean dubbel = false;
    for (Ornament o : ornamentenLijst) {
      if (o.getNaam().equals(ornament.getNaam())) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.ornamentenLijst.add(ornament);
    }
  }
  
  public List<Ornament> getOrnamentLijst(){
    return ornamentenLijst;
  }

  public List<Bewoner> getBewonerLijst() {
    return bewonerLijst;
  }

  public void voegBewonerToe(Bewoner bewoner) {
    boolean dubbel = false;
    for (Bewoner b : bewonerLijst) {
      if (b.getSoortNaam().equals(bewoner.getSoortNaam()) && b.getKleurNaam().equals(bewoner.getKleurNaam())) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.bewonerLijst.add(bewoner);
    }
  }
  

  public List<Toebehoren> getToebehorenLijst() {
    return toebehorenLijst;
  }

  public void voegToebehorenToe(Toebehoren toebehoren) {
    boolean dubbel = false;
    for (Toebehoren t : toebehorenLijst) {
      if (t.getSerienummer() == toebehoren.getSerienummer()) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.toebehorenLijst.add(toebehoren);
    }
  }

  @Override
  public String toString() {
    return "Aquarium [naam=" + naam + ", lengte=" + lengte + ", breedte=" + breedte + ", hoogte=" + hoogte + ", bodemsoort=" + bodemsoort + ", watersoort="
        + watersoort + ", ornamentenLijst=" + ornamentenLijst + ", bewonerLijst=" + bewonerLijst + ", toebehorenLijst=" + toebehorenLijst + "]";
  }

  
}
