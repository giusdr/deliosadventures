package game;

import city.cs.engine.BoxShape;
import org.jbox2d.common.Vec2;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class scans a .txt file, and parse it into an ArrayList. The ArrayList
 * is going to contain Data objects.
 *
 * @author Giuseppe*
 *
 */
public class JavaFileScanner {

    /**
     *
     * @param path Location of the file to open
     * @return ArrayList&lt;Data&gt; containing all the values to create new
     * platforms
     */
    public ArrayList<Data> openFile(String path) {
        ArrayList<Data> data = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(path));
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                //parse line to get Data object
                data.add(parseLine(scanner.next()));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage() + " FILE NOT FOUND");
            System.err.println("The program will close now.");
            System.exit(0);
        } finally {
            if (scanner != null) {
                scanner.close();
                //System.out.println("Scanner is closed now.");
            }
        }
        return data;
    }

    /**
     * This method is parsing the string and creates a new instance of Data
     *
     * @param line line of text to be parsed
     * @return Data file to be added to the ArrayList
     */
    private Data parseLine(String line) {
        float width, height, vx, vy;
        String name;
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",");
            name = scanner.next();
            width = Float.parseFloat(scanner.next());
            height = Float.parseFloat(scanner.next());
            vx = Float.parseFloat(scanner.next());
            vy = Float.parseFloat(scanner.next());
            return new Data(new BoxShape(width, height), new Vec2(vx, vy), name);
        } finally {
            //System.err.println("ParseLine Scanner is now closed.");

        }
    }

}
