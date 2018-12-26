import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateImage {

    public static BufferedImage Rotate(Image src, int angel) {
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        Rectangle rectangle = CalcRotatedSize(new Rectangle(new Dimension(
                width, height)), angel);

        BufferedImage bufferedImage;
        bufferedImage = new BufferedImage(rectangle.width, rectangle.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.translate((rectangle.width - width) / 2, (rectangle.height - height) / 2);
        graphics2D.rotate(Math.toRadians(angel), width / 2, height / 2);

        graphics2D.drawImage(src, null, null);
        return bufferedImage;
    }

    static String getImage(String inputName,String outputFileName,int agl) throws IOException {
        BufferedImage src = ImageIO.read(new File("G:\\ProgrammingLabAutumn2018\\"+inputName));
        BufferedImage des = RotateImage.Rotate(src, agl);
        String outputName=outputFileName+"/"+inputName.replace("images/","");
        ImageIO.write(des, "png", new File("G:\\ProgrammingLabAutumn2018\\"+outputName));

        return outputName;
    }

    public static Rectangle CalcRotatedSize(Rectangle rectangle, int angel) {
        // if angel is greater than 90 degree, we need to do some conversion
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = rectangle.height;
                rectangle.height = rectangle.width;
                rectangle.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(rectangle.height * rectangle.height + rectangle.width * rectangle.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) rectangle.height / rectangle.width);
        double angel_dalta_height = Math.atan((double) rectangle.width / rectangle.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = rectangle.width + len_dalta_width * 2;
        int des_height = rectangle.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
}
