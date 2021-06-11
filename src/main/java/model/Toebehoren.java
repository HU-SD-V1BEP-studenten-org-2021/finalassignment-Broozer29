package model;

public class Toebehoren {
  private String model;
  private int serienummer;
  private String omschrijving;

  public Toebehoren(String model, int serienummer, String omschrijving) {
    this.model = model;
    this.serienummer = serienummer;
    this.omschrijving = omschrijving;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getSerienummer() {
    return serienummer;
  }

  public void setSerienummer(int serienummer) {
    this.serienummer = serienummer;
  }

  public String getOmschrijving() {
    return omschrijving;
  }

  public void setOmschrijving(String omschrijving) {
    this.omschrijving = omschrijving;
  }

}
