package core.pages;

import core.data.Entry;
import core.selectors.Category;
import core.selectors.Department;
import core.selectors.EducationForm;
import core.selectors.Funding;
import core.selectors.Level;
import core.selectors.Original;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ApplicationsPage extends PageBase {

  public static final By SEARCH_BUTTON = By.xpath("//button[@name='set_filter']");
  public static final By LEVEL = By.name("vprogram");
  public static final By EDUCATION_FORM = By.name("edform");
  public static final By FUNDING = By.name("finance");
  public static final By DEPARTMENT = By.name("department");
  public static final By GROUP = By.name("group");
  public static final By CATEGORY = By.name("zcat");
  public static final By ORIGINAL = By.name("original");
  public static final By TABLE = By.className("table-abit-list");
  public static final By TABLE_ROW = By.xpath("//tbody/tr");
  public static final By TABLE_FIELD = By.xpath("//td");


  public ApplicationsPage(WebDriver driver) {
    super(driver);
  }

  protected void init() {
    wait(SEARCH_BUTTON);
  }

  public void selectLevel(Level level) {
    selectByText(LEVEL, level.getValue());
  }

  public void selectEducationForm(EducationForm form) {
    selectByText(EDUCATION_FORM, form.getValue());
  }

  public void selectFunding(Funding funding) {
    selectByText(FUNDING, funding.getValue());
  }

  public void selectDepartment(Department department) {
    selectByText(DEPARTMENT, department.getValue());
  }

  public void selectCategory(Category category) {
    selectByText(CATEGORY, category.getValue());
  }

  public void selectOriginal(Original original) {
    selectByText(ORIGINAL, original.getValue());
  }

  public void selectFirstDepartment() {
    selectFirstNonEmpty(DEPARTMENT);
  }

  public void selectFirstGroup() {
    selectFirst(GROUP);
  }

  //probably could be better
  public boolean selectNextGroup() {
    Select select = new Select(driver.findElement(GROUP));
    List<WebElement> options = select.getOptions();
    String current = select.getFirstSelectedOption().getText();
    boolean foundCurrent = false;
    for (WebElement option : options
        ) {
      if (foundCurrent) {
        select.selectByValue(option.getAttribute("value"));
        return true;
      }
      if (current.equals(option.getText())) {
        foundCurrent = true;
      }
    }
    return false;
  }

  public List<Entry> search() {

    click(SEARCH_BUTTON);
    wait(TABLE);

    reloadingLoop:
    while (true) {
      Set<Entry> entries = new HashSet<>();

      String level = getCurrentSelectionValue(LEVEL);
      String educationForm = getCurrentSelectionValue(EDUCATION_FORM);
      String funding = getCurrentSelectionValue(FUNDING);
      String department = getCurrentSelectionValue(DEPARTMENT);
      String group = getCurrentSelectionValue(GROUP);

      String source =
          "<table>" + driver.findElement(TABLE).getAttribute("innerHTML") + "</table>";
      Document doc = Jsoup.parse(source, "UTF-8");
      for (Element rowElement : doc.getElementsByTag("tr")
          ) {
        Elements cols = rowElement.getElementsByTag("td");
        if (cols.size() == 0) {
          continue;
        }
        String fullName = cols.get(1).text();
        String score = cols.get(3).text();
        boolean original = false;
        boolean agreed = false;

        String originalText = cols.get(6).text();
        String agreedText = cols.get(7).text();

        if (originalText.equals(Original.ORIGINAL.getValue())) {
          original = true;
        }

        if (agreedText.equals("Да")) {
          agreed = true;
        }

        Entry entry = new Entry(level, educationForm, funding, department, group, fullName,
            score,
            original, agreed);
        if (!entries.add(entry)) {
          continue reloadingLoop;
        }
      }
      return new ArrayList<>(entries);
    }

  }
}
