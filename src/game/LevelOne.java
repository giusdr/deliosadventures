package game;

import java.util.ArrayList;
import org.jbox2d.common.*;

/**
 * First level of the game.
 *
 * @author Giuseppe
 */
public class LevelOne extends Level {

    private final static String[] RESERVED_WORDS = {"start", "steptoball2", "steptochest3", "winningplatform", "cagefloor"};

    /**
     *
     * @param game Current game in which the level will be created
     */
    public LevelOne(Game game) {
        super(game, CURRENT_LEVEL[0]);
        levelNumber = 1;
        gates = new ArrayList<>();
        levers = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");

    }

    /**
     *
     * @param game Current game in which the level will be created
     * @param d The data previously parsed from the .txt file.
     */
    public LevelOne(Game game, ArrayList<Data> d) {
        super(game, d);
        levelNumber = 1;
        gates = new ArrayList<>();
        levers = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");

    }

    /**
     * Populate the world with static platforms and bodies.
     */
    @Override
    public void populate() {
        displayStaticPlatforms(RESERVED_WORDS);

        gates.add(new Gate(this, new Vec2(155.25f, -2.25f)));
        gates.add(new Gate(this, new Vec2(155.25f, -7.75f)));
        gates.add(new Gate(this, new Vec2(172.75f, -2.25f)));

        levers.add(new Lever(this, gates.get(2), this.getPlayer(), new Vec2(210, -9.75f)));
        levers.add(new Lever(this, gates.get(0), this.getPlayer(), new Vec2(164, -3.25f)));
        levers.add(new Lever(this, gates.get(1), this.getPlayer(), new Vec2(160, -3.25f)));

        presents.add(new Present(this, this.getPlayer(), new Vec2(105, 7)));
        presents.add(new Present(this, this.getPlayer(), new Vec2(162, -9)));
        presents.add(new Present(this, this.getPlayer()));
        presents.get(2).putOn(search("steptoball2"));

        Vec2 wPosition = search("winningplatform").getPosition();
        winning = new SwitchLevelSensor(this, new Vec2(wPosition.x, wPosition.y + 1.5f));

    }

    /**
     *
     * @return a new instance of the current level
     */
    @Override
    public Level restart() {
        backgroundMusic.getMusic().stop();
        return new LevelOne(super.getGame(), getData());
    }

    /**
     *
     * @return a new instance of the next level
     */
    @Override
    public Level nextLevel() {
        writeToFile();
        backgroundMusic.getMusic().stop();
        return new LevelTwo(this.getGame());
    }
}
