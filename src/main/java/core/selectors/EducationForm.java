package core.selectors;

public enum EducationForm {
  FULLTIME("Очная"),
  PARTTIME("Очно-заочная"),
  CORRESPONDENCE("Заочная");


  private String value;
  public String getValue(){
    return value;
  }
  private EducationForm(String value){
    this.value=value;
  }
}
