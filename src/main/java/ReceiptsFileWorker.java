import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class ReceiptsFileWorker {
    ArrayList<String> months; //��������� ������
    ArrayList<String> services; //��������� ������
    ArrayList<ArrayList<String>> connection = new ArrayList<>(); // ��������� ������ ��� ������� ������
    /**
     * ���������� ����� � �������� �������� �������
     * @param path ���� � �����
     */
    public void ReadFile(String path) {
        HashSet<String> months = new HashSet<>(); //��������� ������
        HashSet<String> services = new HashSet<>(); //��������� ������
        HashSet<String[]> pairs = new HashSet<>(); // ��������� ���� ������-�����
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
            this.months = new ArrayList<>(months);
            this.services = new ArrayList<>(services);
            ArrayList<String[]> pairs_arr = new ArrayList<>(pairs);
            for(int i= 0;i < months.size();i++) {
                connection.add(new ArrayList<>());
            }
            for(String[] i: pairs_arr) {
                int pos = this.months.indexOf(i[1]);
                connection.get(pos).add(i[0]);
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
