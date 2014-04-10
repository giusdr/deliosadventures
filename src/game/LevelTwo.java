package game;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 * Second level of the game.
 *
 * @author Giuseppe
 */
public class LevelTwo extends Level {

    private static final String[] RESERVED_WORDS = {"start", "bottomblock", "elevatorplatform", "platformunderpillar", "winningplatform", "ballplatform"};

    /**
     *
     * @param game Current game in which the level will be created
     */
    public LevelTwo(Game game) {
        super(game, CURRENT_LEVEL[1]);
        levelNumber = 2;
        gates = new ArrayList<>();
        levers = new ArrayList<>();
        elevators = new ArrayList<>();
        balls = new ArrayList<>();
        bridges = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");

    }

    /**
     *
     * @param game Current game in which the level will be created
     * @param d The data previously parsed from the .txt file.
     */
    public LevelTwo(Game game, ArrayList<Data> d) {
        super(game, d);
        levelNumber = 2;
        gates = new ArrayList<>();
        levers = new ArrayList<>();
        elevators = new ArrayList<>();
        balls = new ArrayList<>();
        bridges = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");

    }

    /**
     * Populate the world with static platforms and bodies.
     */
    @Override
    public void populate() {
        displayStaticPlatforms(RESERVED_WORDS);

        elevators.add(new AutomaticElevator(this, new Vec2(18, -10), 8));
        elevators.add(new AutomaticElevator(this, new Vec2(105, -8), 7));
        elevators.add(new AutomaticElevator(this, new Vec2(195, 14), 14));
        elevators.add(new OneTimeElevator(this, new Vec2(140, 0), 28));

        for (Elevator curr : elevators) {
            this.addStepListener(curr);
            curr.addCollisionListener(new ElevatorCollision(curr, this.getPlayer()));
        }

        new ElevatorSensor(this, elevators.get(3), new Vec2(144, 2.5f), this.getPlayer());

        gates.add(new Gate(this, new Vec2(57.75f, -11.25f)));
        gates.add(new Gate(this, new Vec2(228.25f, 11.25f)));

        bridges.add(new Bridge(this, 0.5f, 12, new Vec2(76.5f, 40.5f)));

        levers.add(new Lever(this, gates.get(0), this.getPlayer(), new Vec2(54, -12.25f)));
        levers.add(new Lever(this, gates.get(1), this.getPlayer(), new Vec2(240.5f, 0.75f)));
        levers.add(new Lever(this, bridges.get(0), this.getPlayer(), new Vec2(240.5f, 10.25f)));

        for (int i = 0; i < 3; i++) {
            presents.add(new Present(this, this.getPlayer()));
        }
        presents.get(0).putOn(search("bottomblock"));
        presents.get(1).putOn(search("elevatorplatform"));
        presents.get(2).putOn(search("platformunderpillar"));

        for (int i = 0; i < 2; i++) {
            balls.add(new Ball(this, this.getPlayer(), new Vec2(102 + (i * 6), 40.5f)));
        }

        new TipsBoard(this, new Vec2(70, 8));

        Vec2 wPosition = search("winningplatform").getPosition();
        winning = new SwitchLevelSensor(this, new Vec2(wPosition.x, wPosition.y + 1.5f));

    }

    /**
     *
     * @return a new instance of the same level
     */
    @Override
    public Level restart() {
        backgroundMusic.getMusic().stop();
        return new LevelTwo(super.getGame(), getData());
    }

    /**
     *
     * @return a new instance of the next level
     */
    @Override
    public Level nextLevel() {
        backgroundMusic.getMusic().stop();

        writeToFile();
        return new LevelThree(super.getGame());
    }

}
