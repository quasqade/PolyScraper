package core.selectors;

public enum Category {
  GENERAL("Общий конкурс"),
  TARGETED("Целевые места"),
  PRIVILEGED("Особое право");

  private String value;
  public String getValue(){
    return value;
  }
  private Category(String value){
    this.value=value;
  }

}