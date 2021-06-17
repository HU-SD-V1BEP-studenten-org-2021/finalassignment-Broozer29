package model;

import java.util.ArrayList;

public final class AquariumManager {
  private static AquariumManager INSTANCE;
  private String installatieNaam;
  private ArrayList<Toebehoren> toebehorenLijst = new ArrayList<>();
  private ArrayList<User> UserLijst = new ArrayList<>();
  private ArrayList<Bewoner> bewonerLijst = new ArrayList<>();
  private ArrayList<Aquarium> aquariumLijst = new ArrayList<>();

  private AquariumManager() {
  }

  public static AquariumManager getInstance() {
    if (INSTANCE == null) {
      User beheerderUser = new User("Bruus", "Password", "Beheerder");
      User testUserToPromote = new User("Dummy", "DummyPassword", "User");
      INSTANCE = new AquariumManager();
      INSTANCE.voegUserToe(beheerderUser);
      User.registerUser(beheerderUser);
      User.registerUser(testUserToPromote);
    }
    return INSTANCE;
  }

  public ArrayList<User> getUserLijst() {
    return this.UserLijst;
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

  public void voegUserToe(User eigenaar) {
    boolean dubbel = false;

    for (User e : UserLijst) {
      if (e.getUserName().equals(eigenaar.getUserName())) {
        dubbel = true;
      }
    }
    if (!dubbel) {
      this.UserLijst.add(eigenaar);
    }
  }

  public void verwijderUser(User userToRemove) {
    User userToRemoveV = null;
    boolean removeUser = false;

    for (User e : UserLijst) {
      if (e.getUserName().equals(userToRemoveV.getUserName())) {
        removeUser = true;
        userToRemoveV = e;
      }
    }

    if (removeUser) {
      UserLijst.remove(userToRemoveV);
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
