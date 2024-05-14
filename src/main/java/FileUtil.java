import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void writeToFile(String filename, String stringToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(stringToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
