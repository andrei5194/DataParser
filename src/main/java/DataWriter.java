import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    public void writeMapJSON() {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        JSONObject metroMap = new JSONObject();
        JSONArray linesJSON = new JSONArray();
        JSONObject stationsJSON = new JSONObject();
        DataCollector.lines.forEach(line -> {
            JSONObject lineObject = new JSONObject();
            JSONArray stationsOnLine = new JSONArray();
            lineObject.put("number", line.getNumber());
            lineObject.put("name", line.getName());
            linesJSON.add(lineObject);
            DataCollector.stations.forEach(station -> {
                if(line.getNumber().equals(station.getLine())){
                    stationsOnLine.add(station.getName());
                }
                stationsJSON.put(line.getNumber(), stationsOnLine);
            });
        });
        metroMap.put("stations", stationsJSON);
        metroMap.put("lines", linesJSON);
    try (FileWriter writer = new FileWriter("src/main/resources/map.json")){
        writer.write(GSON.toJson(metroMap));
        writer.flush();
        System.out.println("Файл \"map.json\" успешно записан.");
    }
    catch (IOException ex) {
        ex.printStackTrace();
        }
    }

    public void writeStationsJSON() {
        Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        JSONObject stationsINFO = new JSONObject();
        JSONArray stationsARRAY = new JSONArray();

        DataCollector.station2line.keySet().forEach(station -> {
            JSONObject stationObject = new JSONObject();
            stationObject.put("name", station);
            stationObject.put("line", DataCollector.station2line.get(station));
            stationObject.put("date", DataCollector.station2date.getOrDefault(station, "-"));
            if (!DataCollector.station2depth.containsKey(station) ||
                    DataCollector.station2depth.get(station).trim().equals("?")){
                stationObject.put("depth", "-");
            } else{
                stationObject.put("depth", DataCollector.station2depth.get(station));
            }
            stationObject.put("hasConnection", DataCollector.station2connection.get(station));
            stationsARRAY.add(stationObject);
            });

        stationsINFO.put("stations", stationsARRAY);
        try (FileWriter writer = new FileWriter("src/main/resources/stations.json")){
            writer.write(GSON.toJson(stationsINFO));
            writer.flush();
            System.out.println("Файл \"stations.json\" успешно записан.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
