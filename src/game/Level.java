package game;

import city.cs.engine.World;
import java.util.ArrayList;
import java.io.IOException;
import org.jbox2d.common.Vec2;

/**
 * This is the Level class, extension of World. It provides the default methods
 * and variables for its subclasses. It reads the static platform position and
 * shape from a *.txt file and initialize the score.
 *
 * @author Giuseppe
 *
 *
 */
public abstract class Level extends World {

    protected static final String[] CURRENT_LEVEL = {"data/level0.txt", "data/level1.txt", "data/level2.txt", "data/level3.txt"};

    protected ArrayList<Platform> platforms;
    protected ArrayList<Present> presents;
    protected ArrayList<Platform> staticPlatforms;
    protected ArrayList<Gate> gates;
    protected ArrayList<Lever> levers;
    protected SwitchLevelSensor winning;
    protected ArrayList<Elevator> elevators;
    protected ArrayList<Ball> balls;
    protected ArrayList<Bridge> bridges;
    protected ArrayList<TeleportOut> output;
    protected ArrayList<TeleportIn> input;
    protected Music backgroundMusic;

    private ArrayList<Data> data;
    private ArrayList<Data> visualizing;

    private String filePath;
    private Score score;
    private Game g;
    private Player p;
    private long startingTime;
    protected int levelNumber;

    /**
     *
     * @param g Current game in which the level will be created
     */
    public Level(Game g) {
        super(45);
        this.g = g;
        this.filePath = null;
        platforms = new ArrayList<>();
        score = new Score();
        presents = new ArrayList<>();
        staticPlatforms = new ArrayList<>();
        data = new ArrayList<>();
        visualizing = new ArrayList<>();
        setGravity(60);
    }

    /**
     *
     * @param g Current game in which the level will be created
     * @param filePath The file path to scan for the .txt file that generates
     * the level.
     */
    public Level(Game g, String filePath) {
        this(g);
        this.filePath = filePath;
    }

    /**
     *
     * @param g Current game in which the level will be created
     * @param d The data previously parsed from the .txt file.
     */
    public Level(Game g, ArrayList<Data> d) {
        this(g);
        for (Data current : d) {
            data.add(current);
        }
    }

    /**
     *
     * @return the ArrayList of Data.
     */
    public ArrayList<Data> getData() {
        return data;
    }

    /**
     * @return the gates
     */
    public ArrayList<Gate> getGates() {
        return gates;
    }

    /**
     * @return the levers
     */
    public ArrayList<Lever> getLevers() {
        return levers;
    }

    /**
     *
     * @return the level number.
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     *
     * @param p set the player to the World.
     */
    public void setPlayer(Player p) {
        this.p = p;
    }

    /**
     *
     * @return the Player in the world.
     */
    public Player getPlayer() {
        return p;
    }

    /**
     *
     * @return the game where the World exists.
     */
    public Game getGame() {
        return g;
    }

    /**
     *
     * @return current score.
     */
    public Score getScore() {
        return score;
    }

    /**
     * @return the winning sensor.
     */
    public SwitchLevelSensor getWinning() {
        return winning;
    }

    /**
     *
     * @return boolean value that is true if the level is completed.
     */
    public boolean isCompleted() {
        return score.getScore() > 0;
    }

    /**
     *
     * @return the starting time.
     */
    public long getStartingTime() {
        return startingTime;
    }

    /**
     *
     * @param s String to be found.
     * @return The platform with the name given as a parameter.
     */
    protected Platform search(String s) {
        Platform found = null;
        for (Platform current : staticPlatforms) {
            if (current.getName().equalsIgnoreCase(s)) {
                found = current;
            }
        }
        if (found == null) {
            for (Platform current : platforms) {
                if (current.getName().equalsIgnoreCase(s)) {
                    found = current;
                }
            }
        }
        return found;
    }

    /**
     *
     * @param s the string to be found.
     * @return the data with the name given as a parameter.
     */
    protected Data searchData(String s) {
        Data found = null;
        for (Data current : data) {
            if (current.getName().equalsIgnoreCase(s)) {
                found = current;
            }
        }
        return found;

    }

    /**
     *
     * @return the starting Platform.
     */
    public Platform getStartingPlatform() {
        Platform returnValue = null;
        for (Platform current : staticPlatforms) {
            if (current.getName().equalsIgnoreCase("start")) {
                returnValue = current;
            }
        }
        return returnValue;
    }

    /**
     * Overrides the World start method, starting the timer as well.
     */
    @Override
    public void start() {
        super.start();
        backgroundMusic.getMusic().loop();
        startingTime = System.currentTimeMillis();
    }

    @Override
    public void setPaused(boolean pause) {
        super.setPaused(pause);
        backgroundMusic.getMusic().stop();
    }

    /**
     * Loads the platforms information in an ArrayList&lt;Data&gt;
     */
    protected final void load() {
        if (data.isEmpty()) {
            JavaFileScanner jfs = new JavaFileScanner();
            data = jfs.openFile(filePath);
        }
    }

    /**
     * Populate the world
     */
    public abstract void populate();

    /**
     *
     * @return a new Instance of the same Level.
     */
    public abstract Level restart();

    /**
     *
     * @return a new Instance of the next level.
     */
    public abstract Level nextLevel();

    /**
     * Writes the highscore for the current level.
     */
    protected final void writeToFile() {
        try {
            RecordWriter.write(this, this.getGame().getTimer(), this.getScore());
        } catch (IOException ex) {
            System.out.println("Impossible to write to a file.");
        }
    }

    /**
     * Updates the ArrayList containing the platforms to be visualized according
     * to the player position.
     */
    public void update() {
        ArrayList<Data> toVisualize = new ArrayList<>();
        for (Data current : data) {
            Vec2 position = current.getVec().sub(p.getPosition());
            if ((position.x <= 55 && position.y <= 55) && (position.x >= -50 && position.y >= -50)) {
                toVisualize.add(current);
                // System.out.println(current.getName() + " " + position.x + " " + position.y);

            }
        }
        if (!toVisualize.equals(visualizing)) {
            visualizing.clear();
            visualizing.addAll(toVisualize);
        }
    }

    /**
     * Display the platforms on the screen.
     */
    public void displayPlatforms() {
        if (!platforms.isEmpty()) {
            for (Platform c : platforms) {
                c.destroy();
            }
            platforms.clear();
        }
        for (Data current : visualizing) {
            platforms.add(new Platform(this, current.getShape(), current.getVec(), current.getName()));
        }

    }

    /**
     * Display the platforms that need to be in the world from the beginning to
     * the end, also called static platforms.
     *
     * @param sp String of the values to be written in the staticPlatforms
     * array.
     */
    public void displayStaticPlatforms(String[] sp) {
        for (String s : sp) {
            Data value = searchData(s);
            if (value != null) {
                staticPlatforms.add(new Platform(this, value.getShape(), value.getVec(), value.getName()));
            }
        }
    }
}
