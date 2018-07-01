package core.selectors;

public enum Level {
  BACHELOR("Бакалавриат/Специалитет"),
  MASTER("Магистратура");

  private final String value;

  Level(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
