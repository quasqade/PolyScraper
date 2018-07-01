package core.data;

import java.io.Serializable;

public class Entry implements Serializable {

  public final String level;
  public final String educationForm;
  public final String funding;
  public final String department;
  public final String group;
  public final String fullName;
  public final String score;
  public final boolean original;
  public final boolean agreed;

  public Entry(String level, String educationForm, String funding, String department,
      String group, String fullName, String score, boolean original, boolean agreed) {
    this.level = level;
    this.educationForm = educationForm;
    this.funding = funding;
    this.department = department;
    this.group = group;
    this.fullName = fullName;
    this.score = score;
    this.original = original;
    this.agreed = agreed;
  }
}
