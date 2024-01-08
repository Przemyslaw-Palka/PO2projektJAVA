// MyKeyListener.java
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    private Szachownica szachownica;

    public MyKeyListener(Szachownica szachownica) {
        this.szachownica = szachownica;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                szachownica.obrazy.get(szachownica.aktywnyObrazIndex).przesunWGore();
                break;
            case KeyEvent.VK_DOWN:
                szachownica.obrazy.get(szachownica.aktywnyObrazIndex).przesunWDol();
                break;
            case KeyEvent.VK_LEFT:
                szachownica.obrazy.get(szachownica.aktywnyObrazIndex).przesunWLewo();
                break;
            case KeyEvent.VK_RIGHT:
                szachownica.obrazy.get(szachownica.aktywnyObrazIndex).przesunWPrawo();
                break;
            case KeyEvent.VK_1:
                szachownica.aktywnyObrazIndex = 0;
                break;
            case KeyEvent.VK_2:
                szachownica.aktywnyObrazIndex = 1;
                break;
            case KeyEvent.VK_3:
                szachownica.aktywnyObrazIndex = 2;
                break;
            case KeyEvent.VK_4:
                szachownica.aktywnyObrazIndex = 3;
                break;
        }
        szachownica.panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
