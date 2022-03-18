import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Visitor {

    private ArrayList<BufferedImage> femaleSprites;
    private ArrayList<BufferedImage> maleSprites;

    public Visitor() {

        femaleSprites = new ArrayList<>();
        maleSprites = new ArrayList<>();

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("betty.png"));
            int spriteWidth = image.getWidth()/4;
            int spriteHeight = image.getHeight() / 4;
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    femaleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("george.png"));
            int spriteWidth = image.getWidth() / 4;
            int spriteHeight = image.getHeight() / 4;
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    maleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BufferedImage> getFemaleSprites() {
        return femaleSprites;
    }

    public ArrayList<BufferedImage> getMaleSprites() {
        return maleSprites;
    }
}
