package core.selectors;

public enum Original {
  ALL("Все"),
  ORIGINAL("Оригинал"),
  COPY("Копия");

  private String value;

  private Original(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
