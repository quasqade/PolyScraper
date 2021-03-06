package core.selectors;

public enum EducationForm {
  FULLTIME("Очная"),
  PARTTIME("Очно-заочная"),
  CORRESPONDENCE("Заочная");


  private final String value;

  EducationForm(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
