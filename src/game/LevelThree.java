package game;

import city.cs.engine.BoxShape;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 * Third level of the game.
 *
 * @author Giuseppe
 */
public class LevelThree extends Level {

    private static final String[] RESERVED_WORDS = {"start", "presentstep", "2floor", "lastpresentplatform", "winningplatform", "ballplatform"};

    /**
     *
     * @param game Current game in which the level will be created
     */
    public LevelThree(Game game) {
        super(game, CURRENT_LEVEL[2]);
        levelNumber = 3;
        balls = new ArrayList<>();
        elevators = new ArrayList<>();
        output = new ArrayList<>();
        input = new ArrayList<>();
        bridges = new ArrayList<>();
        levers = new ArrayList<>();
        gates = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");

    }

    /**
     *
     * @param game Current game in which the level will be created
     * @param d The data previously parsed from the .txt file.
     */
    public LevelThree(Game game, ArrayList<Data> d) {
        super(game, d);
        levelNumber = 3;
        balls = new ArrayList<>();
        elevators = new ArrayList<>();
        output = new ArrayList<>();
        input = new ArrayList<>();
        bridges = new ArrayList<>();
        levers = new ArrayList<>();
        gates = new ArrayList<>();
        backgroundMusic = new Music("data/music" + levelNumber + ".wav");
    }

    /**
     * Populate the world with static platforms and bodies.
     */
    @Override
    public void populate() {
        displayStaticPlatforms(RESERVED_WORDS);

        for (int i = 0; i < 3; i++) {
            presents.add(new Present(this, this.getPlayer()));
        }
        presents.get(0).putOn(search("presentstep"));
        presents.get(1).putOn(search("2floor"));
        presents.get(2).putOn(search("lastpresentplatform"));

        elevators.add(new AutomaticElevator(this, new Vec2(-31, 40.5f), 16));
        elevators.add(new ActivationElevator(this, new Vec2(72.5f, 24.5f), 13));
        elevators.add(new ActivationElevator(this, new Vec2(40.5f, 37.5f), 24));
        elevators.add(new AutomaticElevator(this, new Vec2(-31, 40.5f), 16));

        for (Elevator curr : elevators) {
            this.addStepListener(curr);
            curr.addCollisionListener(new ElevatorCollision(curr, this.getPlayer()));
        }

        new ElevatorSensor(this, elevators.get(1), new Vec2(116.5f, 27.25f), this.getPlayer());
        new ElevatorSensor(this, elevators.get(2), new Vec2(26, -11.25f), this.getPlayer());

        bridges.add(new Bridge(this, 5, 0.5f, new Vec2(91.5f, 24.5f)));
        levers.add(new Lever(this, bridges.get(0), this.getPlayer(), new Vec2(54.5f, 39.25f)));

        output.add(new TeleportOut(this, this.getPlayer(), new Vec2(81.5f, 26.25f)));
        output.add(new TeleportOut(this, this.getPlayer(), new Vec2(197, 51.75f)));

        input.add(new TeleportIn(this, output.get(0), this.getPlayer(), new Vec2(70, -12.25f)));
        input.add(new TeleportIn(this, output.get(1), this.getPlayer(), new Vec2(52, 51.25f)));
        input.add(new TeleportIn(this, output.get(1), this.getPlayer(), new Vec2(232, 26.75f)));

        Vec2 wPosition = search("winningplatform").getPosition();
        winning = new SwitchLevelSensor(this, new Vec2(wPosition.x, wPosition.y + 1.6f));

        balls.add(new Ball(this, this.getPlayer(), new Vec2(39, -9)));

        gates.add(new Gate(this, new Vec2(106.75f, 27.25f)));
        levers.add(new Lever(this, gates.get(0), this.getPlayer(), new Vec2(114.5f, 45.65f)));

        new Flames(this, new BoxShape(16.75f, 5), new Vec2(9.75f, 47));
        new Flames(this, new BoxShape(16.75f, 5), new Vec2(9.75f, 10));
    }

    /**
     *
     * @return a new instance of the same level
     */
    @Override
    public Level restart() {
        backgroundMusic.getMusic().stop();
        return new LevelThree(super.getGame(), getData());
    }

    /**
     *
     * @return a new instance of the next level
     */
    @Override
    public Level nextLevel() {
        backgroundMusic.getMusic().stop();
        writeToFile();
        return null;
    }

}
