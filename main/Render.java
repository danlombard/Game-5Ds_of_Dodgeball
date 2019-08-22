package game.tutorial.main;

import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Render {
    public float x;
    public float y;
    public Image image;
    public AffineTransform transform;

    public Render() {
    }

    public Render(float x, float y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);
    }
}
