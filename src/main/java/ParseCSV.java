import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParseCSV {
    public Path path;

    public ParseCSV(Path path){
        this.path = path;
    }

    public void parseCsvFile(){
        String pathString = String.valueOf(path);
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
            if (pathString.contains("depth")) {
                for(int i = 1; i < lines.size(); i++){
                    String line = lines.get(i).trim();
                    String [] text = line.replaceAll("\"","").split(",");
                    DataCollector.station2depth.put(
                            text[0],
                            text[1]
                    );
                }
            }else if (pathString.contains("date")) {
                for(int i = 1; i < lines.size(); i++){
                    String line = lines.get(i).trim();
                    String [] text = line.split(",");
                    DataCollector.station2date.put(
                            text[0],
                            text[1]
                    );
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
