package core.selectors;

public enum Funding {
  STATE("Бюджет"),
  PRIVATE("Контракт");

  private String value;
  public String getValue(){
    return value;
  }
  private Funding(String value){
    this.value=value;
  }
}
