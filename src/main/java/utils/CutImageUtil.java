package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CutImageUtil {
    public static void cutImage(File source) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(source);
        int width = bufferedImage.getWidth() / 2;
        int height = bufferedImage.getHeight() / 2;
        BufferedImage resImage = new BufferedImage(width, height, bufferedImage.getType());

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                resImage.setRGB(i, j, bufferedImage.getRGB(width + i, j));
            }
        }

        ImageIO.write(resImage, "png", source);
    }
}
