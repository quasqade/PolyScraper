//contains some useful methods for page interactions

package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

  public static final int DEFAULT_TIMEOUT = 5; //seconds

  protected WebDriver driver;

  public PageBase(WebDriver driver) {
    this.driver = driver;
    init();
  }

  protected abstract void init();

  //explicit wait
  protected void wait(By by) {
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }


  protected void selectByText(By by, String text) {
    wait(by);
    Select select = new Select(driver.findElement(by));
    select.selectByVisibleText(text);
  }

  //not actually first in select, but with value "1"
  protected void selectFirstNonEmpty(By by) {
    wait(by);
    Select select = new Select(driver.findElement(by));
    select.selectByValue("1");
  }

  protected void selectFirst(By by) {
    wait(by);
    Select select = new Select(driver.findElement(by));
    select.selectByIndex(0);
  }

  protected void click(By by) {
    WebElement element = driver.findElement(by);
    WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
  }

  protected String getCurrentSelectionValue(By by) {
    Select select = new Select(driver.findElement(by));
    WebElement selection = select.getFirstSelectedOption();
    return selection.getText();
  }

}
