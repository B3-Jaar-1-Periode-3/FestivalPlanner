package data;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;

public abstract class Npc {
    private boolean spawned;
    private String name;
    private Point2D position;

    public abstract void draw(FXGraphics2D graphics);
    public abstract void update(double deltaTime);

    public Npc(String name) {
        this.spawned = false;
        this.name = name;
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

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
