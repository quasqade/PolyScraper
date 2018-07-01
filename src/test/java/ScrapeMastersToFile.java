//this is an example that will scrape masters applications to a .csv file

import core.data.Entry;
import core.pages.ApplicationsPage;
import core.selectors.Category;
import core.selectors.Department;
import core.selectors.EducationForm;
import core.selectors.Funding;
import core.selectors.Level;
import core.selectors.Original;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrapeMastersToFile {

  private WebDriver driver;
  private String fileName;

  public ScrapeMastersToFile(String fileName) {
    try {
      this.fileName = fileName;
      File outputFile = new File(fileName);
      if (outputFile.exists()) {
        outputFile.delete();
      }
      outputFile.createNewFile();
      System.setProperty("webdriver.chrome.driver",
          "C:\\Program Files\\Java\\lib\\ChromeDriver\\chromedriver.exe"); //set this to your actual chromedriver location
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
      driver.get("http://www.spbstu.ru/abit/admission-campaign/general/abiturients/");

      //search settings
      ApplicationsPage applicationsPage = new ApplicationsPage(driver);
      applicationsPage.selectLevel(Level.MASTER);
      applicationsPage.selectEducationForm(EducationForm.FULLTIME);
      applicationsPage.selectFunding(Funding.STATE);
      applicationsPage.selectCategory(Category.GENERAL);
      applicationsPage.selectOriginal(Original.ALL);

      //iterate over all departments and groups
      List<Entry> entries = new ArrayList<>();
      for (Department department : Department.values()
          ) {
        applicationsPage.selectDepartment(department);
        applicationsPage.selectFirstGroup();
        do {
          entries.addAll(applicationsPage.search());
        } while (applicationsPage.selectNextGroup());
      }

      System.out.println("Найдено " + entries.size() + " записей");

      FileOutputStream fos = new FileOutputStream(outputFile);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(entries);
      oos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.quit();
    }
  }


}
