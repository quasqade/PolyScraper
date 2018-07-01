package core.selectors;

public enum Funding {
  STATE("Бюджет"),
  PRIVATE("Контракт");

  private String value;

  private Funding(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
