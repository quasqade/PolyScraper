package core.selectors;

public enum Original {
  ALL("Все"),
  ORIGINAL("Оригинал"),
  COPY("Копия");

  private final String value;

  Original(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
