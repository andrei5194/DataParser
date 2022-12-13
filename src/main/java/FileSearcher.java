import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher extends SimpleFileVisitor<Path> {
    private String fileFormat;
    private List<Path> pathsJSON;
    private List<Path> pathsCSV;
    private Path startFrom;

    public FileSearcher(String path){
        pathsJSON = new ArrayList<>();
        pathsCSV = new ArrayList<>();
        this.startFrom = Paths.get(path);
    }

    public void searchFile(String fileFormat, FileSearcher object){
        this.fileFormat = fileFormat;
        try
        {
            Files.walkFileTree(startFrom, object);
        }
        catch (IOException ex)
        {
            throw new RuntimeException();
        }
    }

    public List<Path> getPathsJSON(){
        return new ArrayList<>(pathsJSON);
    }

    public List<Path> getPathsCSV(){
        return new ArrayList<>(pathsCSV);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if(file.getFileName().toString().contains(fileFormat)){
            if(fileFormat.contains(".json")) {
                pathsJSON.add(file);
            } else if(fileFormat.contains(".csv")){
                pathsCSV.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
