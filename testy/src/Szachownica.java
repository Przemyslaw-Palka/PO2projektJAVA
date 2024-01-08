// Szachownica.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Szachownica extends JFrame {

    public List<ObrazPanel> obrazy = new ArrayList<>();
    public int aktywnyObrazIndex = 0;
    public JPanel panel;
    private GraSzachowa graSzachowa;

    public Szachownica(GraSzachowa graSzachowa) {
        super("Szachownica");
        this.graSzachowa = graSzachowa;
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        obrazy.add(new ObrazPanel("/jony.png", 210, 40, 80, 80, getWidth(), getHeight(), obrazy, graSzachowa));
        obrazy.add(new ObrazPanel("/janusz.png", 210, 140, 80, 80, getWidth(), getHeight(), obrazy, graSzachowa));
        obrazy.add(new ObrazPanel("/shizzzka.jpg", 710, 540, 80, 80, getWidth(), getHeight(), obrazy, graSzachowa));
        obrazy.add(new ObrazPanel("/jojo.png", 710, 440, 80, 80, getWidth(), getHeight(), obrazy, graSzachowa));

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int size = 100;
                int rows = 6;
                int cols = 6;

                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        int x = 200 + col * size;
                        int y = 30 + row * size;

                        if ((row + col) % 2 == 0) {
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.BLACK);
                        }

                        g.fillRect(x, y, size, size);
                    }
                }

                for (ObrazPanel obraz : obrazy) {
                    obraz.rysuj(g, this);
                }
            }
        };

        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.addKeyListener(new MyKeyListener(this));

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraSzachowa graSzachownica = new GraSzachowa();
            Szachownica szachownica = new Szachownica(graSzachownica);

            szachownica.setVisible(true);

            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if any image has moved before incrementing the turn counter
                    boolean moveMade = false;
                    for (ObrazPanel obraz : szachownica.obrazy) {
                        if (obraz.hasMoved()) {
                            moveMade = true;
                            break;
                        }
                    }

                    if (moveMade) {
                        graSzachownica.wykonajTure();
                        szachownica.panel.repaint();
                    }
                }
            });

            timer.start();
        });
    }
}
