import core.data.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ScrapedData {

  private List<Entry> entries;

  public boolean load(String fileName) {
    try {
      FileInputStream fis = new FileInputStream(fileName);
      ObjectInputStream ois = new ObjectInputStream(fis);
      entries = (ArrayList) ois.readObject();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public int size() {
    return entries.size();
  }

  public void toCSV(String fileName) {
    try {
      File file = new File(fileName);
      Files.deleteIfExists(file.toPath());
      Writer fw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
      String header = "Программа\tФорма обучения\tФинансирование\tИнститут\tНаправление\tФИО,\tБаллы\tОригинал\tСогласие\n";
      fw.write(header);
      for (Entry entry : entries
          ) {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.level).append("\t");
        sb.append(entry.educationForm).append("\t");
        sb.append(entry.funding).append("\t");
        sb.append(entry.department).append("\t");
        sb.append(entry.group).append("\t");
        sb.append(entry.fullName).append("\t");
        sb.append(entry.score).append("\t");
        if (entry.original) {
          sb.append("Оригинал").append("\t");
        } else {
          sb.append("Копия").append("\t");
        }
        if (entry.agreed) {
          sb.append("Да");
        } else {
          sb.append("Нет");
        }
        sb.append("\n");
        fw.write(sb.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
