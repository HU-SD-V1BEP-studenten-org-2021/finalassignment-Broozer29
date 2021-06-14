package model;

import java.util.ArrayList;

public final class AquariumManager {
  private static AquariumManager INSTANCE;
  private String installatieNaam;
  private ArrayList<Toebehoren> toebehorenLijst = new ArrayList<>();
  private ArrayList<Eigenaar> eigenaarLijst = new ArrayList<>();
  private ArrayList<Bewoner> bewonerLijst = new ArrayList<>();
  private ArrayList<Aquarium> aquariumLijst = new ArrayList<>();

  private AquariumManager() {
  }

  public static AquariumManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AquariumManager();
    }

    return INSTANCE;
  }

  public ArrayList<Eigenaar> getEigenaarLijst() {
    return this.eigenaarLijst;
  }

  public ArrayList<Bewoner> getBewonerLijst() {
    return this.bewonerLijst;
  }

  public ArrayList<Toebehoren> getToebehorenLijst() {
    return this.toebehorenLijst;
  }

  public String getInstallatieNaam() {
    return installatieNaam;
  }

  public void setInstallatieNaam(String installatieNaam) {
    this.installatieNaam = installatieNaam;
  }

  public ArrayList<Aquarium> getAquariumLijst() {
    return aquariumLijst;
  }

  public void voegAquariumToe(Aquarium aquarium) {
    boolean dubbel = false;
    for (Aquarium a : aquariumLijst) {
      if (a.getNaam().equals(aquarium.getNaam())) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.aquariumLijst.add(aquarium);
    }
  }

  public void verwijderAquarium(String aquarium) {
    Aquarium aquariumToRemove = null;
    boolean removeAquarium = false;

    for (Aquarium a : aquariumLijst) {
      if (a.getNaam().equals(aquarium)) {
        removeAquarium = true;
        aquariumToRemove = a;
      }
    }
    if (removeAquarium) {
      aquariumLijst.remove(aquariumToRemove);
    }
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

  public void verwijderToebehoren(Toebehoren toebehoren) {
    Toebehoren toebehorenToRemove = null;
    boolean removeToebehoren = false;

    for (Toebehoren t : toebehorenLijst) {
      if (t.getSerienummer() == toebehoren.getSerienummer()) {
        removeToebehoren = true;
        toebehorenToRemove = t;
      }
    }

    if (removeToebehoren) {
      toebehorenLijst.remove(toebehorenToRemove);
    }
  }

  public void voegEigenaarToe(Eigenaar eigenaar) {
    boolean dubbel = false;

    for (Eigenaar e : eigenaarLijst) {
      if (e.getVoornaam().equals(eigenaar.getVoornaam()) || e.getAchternaam().equals(eigenaar.getAchternaam())) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.eigenaarLijst.add(eigenaar);
    }
  }

  public void verwijderEigenaar(Eigenaar eigenaar) {
    Eigenaar eigenaarToRemove = null;
    boolean removeEigenaar = false;

    for (Eigenaar e : eigenaarLijst) {
      if (e.getVoornaam().equals(eigenaar.getVoornaam()) && e.getAchternaam().equals(eigenaar.getAchternaam())) {
        removeEigenaar = true;
        eigenaarToRemove = e;
      }
    }

    if (removeEigenaar) {
      eigenaarLijst.remove(eigenaarToRemove);
    }
  }

  public void voegBewonerToe(Bewoner bewoner) {
    boolean dubbel = false;
    for (Bewoner b : bewonerLijst) {
      if (b.getSoortNaam().equals(bewoner.getSoortNaam()) && b.getKleurNaam().equals(bewoner.getKleurNaam())) {
        dubbel = true;
        verhoogBewonerAantal(b);
      }
    }
    if (!dubbel) {
      this.bewonerLijst.add(bewoner);
    }
  }

  public void verwijderBewoner(Bewoner bewoner) {
    Bewoner bewonerToRemove = null;
    boolean removeBewoner = false;

    for (Bewoner b : bewonerLijst) {
      if (b.getSoortNaam().equals(bewoner.getSoortNaam())) {
        removeBewoner = true;
        bewonerToRemove = b;
      }
    }

    if (removeBewoner) {
      bewonerLijst.remove(bewonerToRemove);
    }
  }

  private void verhoogBewonerAantal(Bewoner bewoner) {
    int aantal = bewoner.getAantal();
    bewoner.setAantal(aantal + 1);
  }

}
