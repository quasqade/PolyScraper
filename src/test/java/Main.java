import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    while (true) {
      System.out.println("Select an option:");
      System.out.println("1. Scrape all Masters applications into a file");
      System.out.println("2. Load scraped data from file");
      System.out.println("3. Quit");
      System.out.print("Option: ");
      Scanner scanner = new Scanner(System.in);
      int answer = Integer.parseInt(scanner.nextLine());
      switch (answer) {
        case 1:
          System.out.print("Enter a file name: ");
          String fileName = scanner.nextLine();
          new ScrapeMastersToFile(fileName);
          break;
        case 2:
          System.out.print("Enter a file name: ");
          String inputFileName = scanner.nextLine();
          ScrapedData data = new ScrapedData();
          if (data.load(inputFileName)) {
            System.out.println("Successfully loaded " + data.size() + " entries!");
            dataMenu:
            while (true) {
              System.out.println("Select an option:");
              System.out.println("1. Export data to CSV format");
              System.out.println("2. Back");
              answer = Integer.parseInt(scanner.nextLine());
              switch (answer) {
                case 1:
                  System.out.print("Enter a file name: ");
                  fileName = scanner.nextLine();
                  data.toCSV(fileName);
                  break;
                case 2:
                  break dataMenu;
              }
            }
          } else {
            System.out.println("Cannot load specified file!");
          }
          break;
        case 3:
          System.exit(0);
      }
    }
  }
}
