package data;

import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor extends Npc{

    private ArrayList<BufferedImage> femaleSprites;
    private ArrayList<BufferedImage> maleSprites;

    public Visitor(String name) {
        super(name);
        femaleSprites = new ArrayList<>();
        maleSprites = new ArrayList<>();

        try {
            System.out.println("Creating new visitor");
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("betty.png"));
            int spriteWidth = image.getWidth() / 4;
            int spriteHeight = image.getHeight() / 4;
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    femaleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("george.png"));
            int spriteWidth = image.getWidth() / 4;
            int spriteHeight = image.getHeight() / 4;
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    maleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        if (isSpawned()) {
            AffineTransform transform = graphics.getTransform();
            transform.translate(getPosition().getX(), getPosition().getY());
            graphics.drawImage(maleSprites.get(0), transform, null);
        }
    }

    @Override
    public void update(double deltaTime) {
        if (isSpawned()) {
            setPosition(new Point2D.Double(getPosition().getX() - 32 * deltaTime, getPosition().getY()));
        }
    }

    public ArrayList<BufferedImage> getFemaleSprites() {
        return femaleSprites;
    }

    public ArrayList<BufferedImage> getMaleSprites() {
        return maleSprites;
    }
}
