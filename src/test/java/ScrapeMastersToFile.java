//this is an example that will scrape masters applications into a file

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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrapeMastersToFile {

  private WebDriver driver;

  public ScrapeMastersToFile(String fileName) {
    try {
      File outputFile = new File(fileName);
      if (outputFile.exists()) {
        Files.deleteIfExists(outputFile.toPath());
      }
      Files.createFile(outputFile.toPath());
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

      System.out.println("Found " + entries.size() + " entries");

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
