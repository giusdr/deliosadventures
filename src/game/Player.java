/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;
import static game.moveState.*;

/**
 * The player of the world. It can be controlled by keyboard. He can walk, jump,
 * use interactions and eventually die.
 *
 * @author Giuseppe
 */
public class Player extends DynamicBody {

    private static final float HEIGHT = 3.5f;
    private static final float HEAD_WIDTH = 0.3f;
    private static final float HEAD_HEIGHT = 0.30f;
    private static final float BODY_WIDTH = 0.34f;
    private static final float BODY_HEIGHT = 0.68f;
    private static final float FEET_WIDTH = 0.35f;
    private static final float FEET_HEIGHT = 0.30f;
    private static final float JUMPING_SPEED = 25;
    private static final float WALKING_SPEED = 20;
    private final Walker walkLeft;
    private final Walker walkRight;
    private Interaction current;
    private Walker currentWalker;
    private final Level w;
    private static final Music JUMP = new Music("data/jump.wav");
    private static final Music DIE = new Music("data/die.wav");

    private static final BodyImage PLAYER_RIGHT = new BodyImage("data/playerRight.png", HEIGHT);
    private static final BodyImage PLAYER_LEFT = new BodyImage("data/playerLeft.png", HEIGHT);
    private static final BodyImage PLAYER_JUMP = new BodyImage("data/playerJump.png", HEIGHT);

    /**
     *
     * @param w the world in which the player will exist
     *
     */
    public Player(Level w) {
        super(w);
        DIE.getMusic().stop();
        this.w = w;
        createFixtures();
        this.setFixedRotation(true);
        this.walkLeft = new Walker(this, -WALKING_SPEED);
        this.walkRight = new Walker(this, WALKING_SPEED);
        this.currentWalker = null;
        this.setImage(PLAYER_RIGHT);
    }

    /**
     * Creates the 3 fixtures of the player.
     */
    private final void createFixtures() {
        Shape headShape = new BoxShape(HEAD_WIDTH * HEIGHT / 2, HEAD_HEIGHT * HEIGHT / 2, new Vec2(0, BODY_HEIGHT * HEIGHT / 2));
        Shape bodyShape = new BoxShape(BODY_WIDTH * HEIGHT / 2, BODY_HEIGHT * HEIGHT / 2, new Vec2(-0.08f, 0));
        Shape feetShape = new BoxShape(FEET_WIDTH * HEIGHT / 2, FEET_HEIGHT * HEIGHT / 2, new Vec2(0, -BODY_HEIGHT * HEIGHT / 2));
        new SolidFixture(this, headShape);
        new SolidFixture(this, bodyShape);
        SolidFixture feet = new SolidFixture(this, feetShape);
        feet.setDensity(2);
        feet.setRestitution(0.1f);
    }

    /**
     *
     * @return the walkingSpeed
     */
    public float getWalkingSpeed() {
        return WALKING_SPEED;
    }

    /**
     * Move the player according to the moveState
     *
     * @param m moveState informs the method the direction of the player
     */
    public void move(moveState m) {
        switch (m) {
            case MS_LEFT:
                this.setImage(PLAYER_LEFT);
                clearWalker();
                setWalker(walkLeft);
                break;
            case MS_RIGHT:
                clearWalker();
                setWalker(walkRight);
                this.setImage(PLAYER_RIGHT);
                break;
            case MS_JUMP:
                JUMP.getMusic().play();
                this.setImage(PLAYER_JUMP);
                Vec2 v = this.getLinearVelocity();
                if (Math.abs(v.y) < 0.001f) {
                    this.setLinearVelocity(new Vec2(v.x, JUMPING_SPEED));
                }
                break;
            default:
                clearWalker();
                break;

        }

    }

    /**
     * Use the interactions that the player is overlapping.
     *
     * NullPointerException is caught if there is no overlap with an interaction
     */
    public void use() {
        try {
            current.changeState();
        } catch (NullPointerException e) {
            System.err.println("Nothing to use.");
        }

    }

    /**
     * Set the walker, unless already walking
     *
     * @param w set the walker to this one.
     */
    public void setWalker(Walker w) {
        if (currentWalker == null) {
            currentWalker = w;
            this.getWorld().addStepListener(currentWalker);
        }
    }

    /**
     * Clear the walker, if this is the one in effect.
     */
    public void clearWalker() {
        this.getWorld().removeStepListener(currentWalker);
        currentWalker = null;

    }

    /**
     *
     * @return the interaction that the player is overlapping.
     */
    public Interaction isOn() {
        return current;
    }

    /**
     * Set the interaction that the player is overlapping.
     *
     * @param current a new interaction
     */
    public void isOn(Interaction current) {
        this.current = current;
    }

    /**
     * The method calls a new GameOver, and stops the world.
     */
    public void die() {
        this.destroy();
        DIE.getMusic().play();
        new GameOver(w.getGame(), new StaticBody(w), new BoxShape(3, 3));
        this.getWorld().setPaused(true);
    }

    /**
     *
     * @return the current level.
     */
    public Level getLevel() {
        return w;
    }

}
