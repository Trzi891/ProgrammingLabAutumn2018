
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateImageTest {

    @Test
    public void testRotate() throws IOException {
        ImageIcon a = new ImageIcon();
        BufferedImage src = ImageIO.read(new File("G:\\ProgrammingLabAutumn2018\\images\\1tiao.png"));
        BufferedImage des = RotateImage.Rotate(src, 90);
        Assert.assertNotNull(des);
        Assert.assertTrue(ImageIO.write(des, "png", new File("G:\\ProgrammingLabAutumn2018\\90left/1tiao.png")));

        des = RotateImage.Rotate(src, 180);
        Assert.assertNotNull(des);
        Assert.assertTrue(ImageIO.write(des, "png", new File("G:\\ProgrammingLabAutumn2018\\180up\\dog3.jpg")));

        des = RotateImage.Rotate(src, 270);
        Assert.assertNotNull(des);
        Assert.assertTrue(ImageIO.write(des, "png", new File("G:\\ProgrammingLabAutumn2018\\270right\\dog4.jpg")));

    }

    @Test
    public void getImageTest() throws IOException {
        String string="images/2wan.png";
        Assert.assertEquals("90left/2wan.png", RotateImage.getImage(string, "90left", 90));
    }

}