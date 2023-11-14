
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

class Tlo extends JPanel {
    private Image tlo;

    public Tlo() {
        try {
            // Wczytaj obraz z folderu resources wewnątrz projektu
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/mietek.jpg");
            assert inputStream != null;
            tlo = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tlo != null) {
            g.drawImage(tlo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Osiedlowa Afera");

        // Utwórz panel z tłem PNG
        Tlo panel = new Tlo();
        frame.add(panel);

        // Dodaj tytul "Osiedlowa Afera" do displaya
        JLabel napisLabel = new JLabel("Osiedlowa Afera");
        napisLabel.setForeground(Color.WHITE);
        napisLabel.setFont(new Font("Tytulowa", Font.PLAIN, 40));
        napisLabel.setBounds(550, 100, 300, 30);

        panel.setLayout(null);
        panel.add(napisLabel);

        // Dodaj przycisk "Graj"
        JButton grajButton = new JButton("Graj");
        grajButton.setBounds(650, 200, 100, 30);
        grajButton.addActionListener(e -> {
            // Zaktualizuj zawartość bieżącej ramki
            panel.removeAll();

            // Utwórz nowy panel z tłem niebieskim
            JPanel nowyPanel = new JPanel();
            nowyPanel.setBackground(Color.BLUE);

            // Dodaj napis "Witaj w Grze" do nowego panelu
            JLabel nowyNapisLabel = new JLabel("Witaj w Grze");
            nowyNapisLabel.setForeground(Color.WHITE);
            nowyNapisLabel.setFont(new Font("Tytulowa", Font.PLAIN, 40));
            nowyPanel.add(nowyNapisLabel);

            // Ustaw nowy panel jako zawartość bieżącej ramki
            frame.setContentPane(nowyPanel);

            // Odśwież ramkę
            frame.revalidate();
            frame.repaint();
        });
        panel.add(grajButton);

        // Konfiguracja ramki
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

