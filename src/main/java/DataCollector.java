import java.util.*;

public class DataCollector {
    protected static List<Line> lines = new ArrayList<>();
    protected static List<Station> stations = new ArrayList<>();
    protected static TreeMap<String, String> station2line = new TreeMap<>();
    protected static TreeMap<String, Boolean> station2connection = new TreeMap<>();
    protected static TreeMap<String, String> station2date = new TreeMap<>();
    protected static TreeMap<String, String> station2depth = new TreeMap<>();

    public void getStations() {
        for(Station station : stations){
            String stationFullName = station.getLine() + " " + station.getName();
            System.out.println(stationFullName);
        }
    }
    public void getLines() {
        for(Line line : lines){
            String lineFullName = line.getNumber() + " " + line.getName();
            System.out.println(lineFullName);
        }
    }
    public void printStationLine() {
        System.out.println("Количество станций: " + station2line.size());
        for (Map.Entry<String, String> entry : station2line.entrySet()) {
            String station = entry.getKey();
            String line = entry.getValue();
            System.out.println(station + " - " + line);
        }
    }
    public void printStationConnection() {
        System.out.println("Количество станций: " + station2connection.size());
        for (Map.Entry<String, Boolean> entry : station2connection.entrySet()) {
            String station = entry.getKey();
            boolean connection = entry.getValue();
            System.out.println(station + " - " + connection);
        }
    }
    public void printStationDate() {
        System.out.println("Количество станций: " + station2date.size());
        for (Map.Entry<String, String> entry : station2date.entrySet()) {
            String station = entry.getKey();
            String date = entry.getValue();
            System.out.println(station + " - " + date);
        }
    }
    public void printStationDepth() {
        System.out.println("Количество станций: " + station2depth.size());
        for (Map.Entry<String, String> entry : station2depth.entrySet()) {
            String station = entry.getKey();
            String depth = entry.getValue();
            System.out.println(station + " - " + depth);
        }
    }
}
