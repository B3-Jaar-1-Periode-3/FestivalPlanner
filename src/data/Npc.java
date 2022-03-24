package data;

import org.jfree.fx.FXGraphics2D;
import tiled.pathfinding.Target;

import java.awt.geom.Point2D;

public abstract class Npc {

    private boolean spawned;
    private String name;
    protected Point2D position;
    protected Target target;

    public abstract void draw(FXGraphics2D graphics);
    public abstract void update(double deltaTime);

    public Npc(String name, Target target) {
        this.spawned = false;
        this.name = name;
        this.target = target;
    }

    public void spawn(Point2D spawn) {
        if (!spawned) {
            spawned = true;
            position = spawn;
        }
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Target getTarget() {
        return target;
    }
}
