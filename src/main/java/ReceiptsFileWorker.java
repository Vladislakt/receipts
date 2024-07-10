import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
public class ReceiptsFileWorker {
    ArrayList<String> months; //считанные мес€цы
    ArrayList<String> services; //считанные услуги
    ArrayList<ArrayList<String>> connection = new ArrayList<>(); // считанные услуги дл€ каждого мес€ца
    String text;
    /**
     * —читывание файла в заданном заданием формате
     * @param path путь к файлу
     */
    private void ReadFile(String path) throws IOException {
        HashSet<String> months = new HashSet<>(); //считанные мес€цы
        HashSet<String> services = new HashSet<>(); //считанные услуги
        HashSet<String[]> pairs = new HashSet<>(); // считанные пары услуга-мес€ц
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
    }

    /**
     * «апись результата работы
     * @param path путь к изначальному файлу
     */
    public void WriteFile(String path) {
        try {
            ReadFile(path);
            Analyse();
        }
        catch (IOException e) {
            String error_text = "ѕроизошла ошибка чтени€ файла\n";
            text = error_text + Arrays.toString(e.getStackTrace());
        }
        String new_path = path.replace(".txt", "_по_папкам.txt");
        File file = new File(new_path);
        try(FileWriter writer = new FileWriter(file))
        {
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * јнализ считанной информации и формирование текста ответа
     */
    private void Analyse() {
        StringBuilder textPaid = new StringBuilder();
        StringBuilder textNoPaid = new StringBuilder();
        for (String month : months) {
            int pos = months.indexOf(month);
            if(!connection.get(pos).containsAll(services)) {
                textNoPaid.append(month).append(":\n");
            }
            for(String service : services) {
                if(connection.get(pos).contains(service)) {
                    textPaid.append("/").append(month).append("/").append(service).append("_").append(month).append(".pdf\n");
                }
                else {
                    textNoPaid.append(service).append("\n");
                }
            }
        }
        text = new String(textPaid.toString().getBytes(), UTF_8) + "Ќе оплачено:\n" + new String(textNoPaid.toString().getBytes(), UTF_8);
    }
}
