package core.selectors;

public enum Level {
  BACHELOR("Бакалавриат/Специалитет"),
  MASTER("Магистратура");

  private String value;

  private Level(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
