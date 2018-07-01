package core.selectors;

public enum Category {
  GENERAL("Общий конкурс"),
  TARGETED("Целевые места"),
  PRIVILEGED("Особое право");

  private final String value;

  Category(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
