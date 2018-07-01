package core.selectors;

public enum Funding {
  STATE("Бюджет"),
  PRIVATE("Контракт");

  private final String value;

  Funding(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
