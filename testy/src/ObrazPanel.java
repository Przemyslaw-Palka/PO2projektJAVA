// ObrazPanel.java
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

public class ObrazPanel {
    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int frameWidth;
    private int frameHeight;
    private List<ObrazPanel> obrazy;
    private GraSzachowa graSzachownica; // Dodano pole dla GraSzachownica

    // Added fields for initial position
    private int initialX;
    private int initialY;

    public ObrazPanel(String imagePath, int x, int y, int width, int height, int frameWidth, int frameHeight, List<ObrazPanel> obrazy, GraSzachowa graSzachowa) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            this.image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.obrazy = obrazy;
        this.graSzachownica = graSzachowa; // Przekazanie referencji do GraSzachownica

        // Set initial position
        setInitialPosition();
    }

    public void setInitialPosition() {
        initialX = x;
        initialY = y;
    }

    public boolean hasMoved() {
        return initialX != x || initialY != y;
    }

    public void rysuj(Graphics g, Component component) {
        g.drawImage(image, x, y, width, height, component);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void przesunWGore() {
        if (y - 100 >= 30 && !kolizjaZInnymiObrazami(x, y - 100, width, height)) {
            y -= 100;
            graSzachownica.resetujRuch(); // Resetuj flagę ruchu po przesunięciu
        }
    }

    public void przesunWDol() {
        if (y + 100 + height <= frameHeight - 30 && !kolizjaZInnymiObrazami(x, y + 100, width, height)) {
            y += 100;
            graSzachownica.resetujRuch(); // Resetuj flagę ruchu po przesunięciu
        }
    }

    public void przesunWLewo() {
        if (x - 100 >= 200 && !kolizjaZInnymiObrazami(x - 100, y, width, height)) {
            x -= 100;
            graSzachownica.resetujRuch(); // Resetuj flagę ruchu po przesunięciu
        }
    }

    public void przesunWPrawo() {
        if (x + 100 + width <= frameWidth - 200 && !kolizjaZInnymiObrazami(x + 100, y, width, height)) {
            x += 100;
            graSzachownica.resetujRuch(); // Resetuj flagę ruchu po przesunięciu
        }
    }

    private boolean kolizjaZInnymiObrazami(int newX, int newY, int newWidth, int newHeight) {
        for (ObrazPanel obraz : obrazy) {
            if (obraz != this) {
                if (newX < obraz.getX() + obraz.width &&
                        newX + newWidth > obraz.getX() &&
                        newY < obraz.getY() + obraz.height &&
                        newY + newHeight > obraz.getY()) {
                    return true;
                }
            }
        }
        return false;
    }
}
