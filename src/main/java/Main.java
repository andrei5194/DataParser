import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        HtmlParser htmlParser = new HtmlParser("https://skillbox-java.github.io/");
        htmlParser.parseFile();
        htmlParser.parseLines();
        htmlParser.parseStations();

        FileSearcher fileSearcher = new FileSearcher("data");
        fileSearcher.searchFile(".json", fileSearcher);
        fileSearcher.searchFile(".csv", fileSearcher);
//        System.out.println(fileSearcher.getPathsJSON());
//        System.out.println(fileSearcher.getPathsCSV());
        fileSearcher.getPathsJSON().forEach(path -> {
                ParseJSON jsonParser = new ParseJSON(path);
                jsonParser.parseJsonFile();
        });
        fileSearcher.getPathsCSV().forEach(path -> {
            ParseCSV csvParser = new ParseCSV(path);
            csvParser.parseCsvFile();
        });

//        DataCollector dataCollector = new DataCollector();
//        dataCollector.getLines();
//        dataCollector.getStations();
//        dataCollector.printStationLine();
//        dataCollector.printStationConnection();
//        dataCollector.printStationDate();
//        dataCollector.printStationDepth();

        DataWriter dataWriter = new DataWriter();
        dataWriter.writeMapJSON();
        dataWriter.writeStationsJSON();
    }

}
