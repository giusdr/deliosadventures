package game;

import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * This class represents the superclass of all the GhostlyFixtures that can kill
 * the player.
 *
 * @author Giuseppe
 */
public abstract class DieZone extends GhostlyFixture {

    private final Sensor sensor;

    /**
     *
     * @param w the world where the fixture will exist
     * @param s the shape for this fixture
     * @param p the position of the fixture in the world
     */
    public DieZone(Level w, Shape s, Vec2 p) {
        super(new StaticBody(w), s);
        this.getBody().setPosition(p);
        this.sensor = new Sensor(this.getBody(), s);
        this.sensor.addSensorListener(new DamageZoneContact(w.getPlayer()));
    }

}
