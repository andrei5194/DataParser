import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParseJSON {

    public Path path;

    public ParseJSON(Path path){
        this.path = path;
    }

    public String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public void parseJsonFile() {
        JSONParser parser = new JSONParser();
        String pathString = String.valueOf(path);
        if (pathString.contains("date")) {
            try {
                JSONArray stationsArray = (JSONArray) parser.parse(getJsonFile());
                stationsArray.forEach(stationObject -> {
                    JSONObject stationJsonObject = (JSONObject) stationObject;
                    DataCollector.station2date.put(
                            (String) stationJsonObject.get("name"),
                            (String) stationJsonObject.get("date")
                    );
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (pathString.contains("depth")) {
            try {
                JSONArray stationsArray = (JSONArray) parser.parse(getJsonFile());
                stationsArray.forEach(stationObject -> {
                    JSONObject stationJsonObject = (JSONObject) stationObject;
                    if (stationJsonObject.keySet().contains("station_name")) {
                        DataCollector.station2depth.put(
                                (String) stationJsonObject.get("station_name"),
                                stationJsonObject.get("depth_meters").toString()
                        );
                    }else {
                        DataCollector.station2depth.put(
                                (String) stationJsonObject.get("name"),
                                stationJsonObject.get("depth").toString()
                        );
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
