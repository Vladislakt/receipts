import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class ReceiptsFileWorker {
    HashSet<String> months = new HashSet<>(); //��������� ������
    HashSet<String> services = new HashSet<>(); //��������� ������
    HashSet<String[]> pairs = new HashSet<>(); // ��������� ���� ������-�����
    /**
     * ���������� ����� � �������� �������� �������
     * @param path ���� � �����
     */
    public void ReadFile(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split("\\.|_");
                services.add(words[0]);
                months.add(words[1]);
                pairs.add(new String[]{words[0], words[1]});
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ������ ���������� ������
     * @param path ���� � ������������ �����
     * @param text ���������, ������� ����� ������� � ����
     */
    public static void WriteFile(String path, String text) {

    }
}
