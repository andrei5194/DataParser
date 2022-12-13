import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HtmlParser {

    private Elements linesNumbers;
    private Elements linesNames;
    private Elements stationsNames;
    private String path;

    public HtmlParser(String path){
        this.path = path;
    }

    public void parseFile(){
        try
        {
            Document doc = Jsoup.connect(path).get();
            linesNumbers = doc.select("div[data-line]");
            linesNames = doc.select("span.js-metro-line");
            stationsNames = doc.select("div.js-metro-stations");
        }
        catch (IOException ex)
        {
            throw new RuntimeException();
        }
    }

    public void parseLines() {
        for(int i = 0; i < linesNumbers.size(); i++){
            Line line = new Line(
                    (linesNumbers.get(i).attr("data-line")),
                    linesNames.get(i).text()
            );
            if (!DataCollector.lines.contains(line)) DataCollector.lines.add(line);
        }
    }

    public void parseStations() {
        for(int i = 0; i < linesNumbers.size(); i++){
            for(int j = 0; j < stationsNames.get(i).select("span.name").size(); j++){
                String stationName = stationsNames.get(i).select("span.name").get(j).text();
                Station station = new Station(
                        stationName,
                        linesNumbers.get(i).attr("data-line")
                );
                    DataCollector.station2line.put(
                            stationName,
                            linesNames.get(i).text()
                    );
                    DataCollector.station2connection.put(
                            stationName,
                            !stationsNames.get(i).select("p").get(j).select("span.t-icon-metroln").isEmpty()
                    );
                DataCollector.stations.add(station);
            }
        }
    }
}
