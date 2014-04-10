package game;

import city.cs.engine.DebugViewer;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * This is the main class of the game.
 *
 * @author Giuseppe
 *
 *
 */
public class Game {

    private static final JFrame FRAME = new JFrame("Delio's adventures!");

    /**
     *
     * @param args main arguments.
     */
    public static void main(String[] args) {
        new Game();
    }

    private Level world;
    private final MyView view;
    private Player player;
    private KeyboardHandler listener;
    private Timer timer;
    private final Gui gui;
    private final ViewTracker viewTracker;

    /**
     * The constructor initializes all the elements of the game, including
     * World, Player, Listeners and the Window.
     */
    public Game() {
        world = new LevelOne(this);
        world.load();
        this.player = new Player(world);
        world.setPlayer(player);
        world.populate();
        player.putOn(world.getStartingPlatform());
        view = new MyView(world, 1000, 600);
        view.setZoom(25);
        viewTracker = new ViewTracker(view, player);
        world.start();
        timer = new Timer(world);
        world.addStepListener(timer);
        gui = new Gui(FRAME, this, timer);
        timer.addChangeListener(gui);
        listenersHandler();
        frameHandler();
        //JFrame debugView = new DebugViewer(world, 1024, 768);
        world.update();
    }

    /**
     *
     * @return the ViewTracker on the player
     */
    public ViewTracker getViewTracker() {
        return viewTracker;
    }

    /**
     *
     * @return the timer of the current world.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Handles the frame creation.
     */
    private final void frameHandler() {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setLocationByPlatform(true);
        FRAME.add(view);
        FRAME.setResizable(false);
        FRAME.pack();
        FRAME.setVisible(true);
        FRAME.add(gui, BorderLayout.NORTH);
        //view.setGridResolution(2);

    }

    /**
     * Handles the standard listeners creation.
     */
    private final void listenersHandler() {
        view.addMouseListener(new GiveFocus(view));
        listener = new KeyboardHandler(this, player, view);
        view.addKeyListener(listener);
        world.addStepListener(viewTracker);
    }

    /**
     * Method that starts a new World in the same Frame.
     *
     * @param w goes to the level w (usually the next level).
     */
    public void startLevel(Level w) {
        if (w != null) {
            if (!world.isPaused()) {
                world.setPaused(true);
            }
            this.player = null;
            world.setPlayer(null);
            world.removeStepListener(timer);
            world.removeStepListener(viewTracker);
            timer = null;
            view.setLevel(null);
            this.world = w;
            world.load();
            this.player = new Player(world);
            world.setPlayer(player);
            world.populate();
            player.putOn(world.getStartingPlatform());
            view.setWorld(world);
            viewTracker.setPlayer(player);
            listenersHandler();
            view.setZoom(25);
            world.start();
            timer = new Timer(world);
            world.addStepListener(timer);
            gui.resetUi(timer, this);
            timer.addChangeListener(gui);
            view.setLevel(world);
        } else {
            JDialog finish = new FinishGame(FRAME, true);
            finish.setAlwaysOnTop(true);
            finish.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            finish.setLocation(FRAME.getWidth() / 2, FRAME.getHeight() / 2);
            finish.setVisible(true);
        }
    }

    /**
     * Restarts a new instance of the same World in the same Frame.
     *
     */
    public void startLevel() {
        if (!world.isPaused()) {
            world.setPaused(true);
        }
        this.player = null;
        world.setPlayer(null);
        world.removeStepListener(timer);
        world.removeStepListener(viewTracker);
        timer = null;
        view.setLevel(null);
        this.world = this.world.restart();
        world.load();
        this.player = new Player(world);
        world.setPlayer(player);
        world.populate();
        player.putOn(world.getStartingPlatform());
        view.setWorld(world);
        viewTracker.setPlayer(player);
        listenersHandler();
        view.setZoom(25);
        world.start();
        timer = new Timer(world);
        world.addStepListener(timer);
        gui.resetUi(timer, this);
        timer.addChangeListener(gui);
        view.setLevel(world);
        JFrame debugView = new DebugViewer(world, 1024, 768);

    }

    /**
     * Starts the next level from the current world.
     */
    public void goNextLevel() {
        startLevel(world.nextLevel());
    }

    /**
     *
     * @return the player of the game.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return the current World.
     */
    public Level getLevel() {
        return world;
    }

}
