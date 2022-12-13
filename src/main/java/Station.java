import java.util.ArrayList;
import java.util.List;

public class Station {
    private String line;
    private String name;

    public Station(String name, String line)
    {
        this.name = name;
        this.line = line;
    }

    public String getName()
    {
        return name;
    }

    public String getLine()
    {
        return line;
    }


}
