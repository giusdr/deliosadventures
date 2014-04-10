/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * The sensor that will be displayed in the world that will enable the player to
 * go to the next level.
 *
 * @author Giuseppe
 */
public class SwitchLevelSensor extends GhostlyFixture {

    private Sensor sensor;
    private static final BodyImage IMAGE = new BodyImage("data/winningportal.png", 2.5f);

    /**
     *
     * @param world the world in which this sensor exist
     */
    public SwitchLevelSensor(Level world) {
        super(new StaticBody(world), new BoxShape(1, 2));
        this.sensor = new Sensor(this.getBody(), new BoxShape(1, 2));
        sensor.addSensorListener(new SwitchLevelListener(world.getGame(), world));
        this.getBody().setImage(IMAGE);
    }

    /**
     *
     * @param world the world in which this sensor exist
     * @param p the position of this sensor.
     */
    public SwitchLevelSensor(Level world, Vec2 p) {
        this(world);
        this.setPosition(p);
    }

    /**
     * Set a new position
     *
     * @param p new position to the sensor.
     */
    public final void setPosition(Vec2 p) {
        this.getBody().setPosition(p);
    }

    /**
     *
     * @return the current position of the sensor.
     */
    public Vec2 getPosition() {
        return this.getBody().getPosition();
    }

}
