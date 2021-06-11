package model;

public class Ornament {
  private String naam;
  private String omschrijving;
  private String kleur;
  private boolean kanOpLuchtPomp;

  public Ornament(String naam, String omschrijving, String kleur, boolean kanUpLuchtPomp) {
    this.naam = naam;
    this.omschrijving = omschrijving;
    this.kleur = kleur;
    this.kanOpLuchtPomp = kanUpLuchtPomp;
  }

  public String getNaam() {
    return naam;
  }

  public void setNaam(String naam) {
    this.naam = naam;
  }

  public String getOmschrijving() {
    return omschrijving;
  }

  public void setOmschrijving(String omschrijving) {
    this.omschrijving = omschrijving;
  }

  public String getKleur() {
    return kleur;
  }

  public void setKleur(String kleur) {
    this.kleur = kleur;
  }

  public boolean isKanUpLuchtPomp() {
    return kanOpLuchtPomp;
  }

  public void setKanUpLuchtPomp(boolean kanUpLuchtPomp) {
    this.kanOpLuchtPomp = kanUpLuchtPomp;
  }

}
