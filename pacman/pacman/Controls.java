import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls {
    public Controls(JFrame frame, GameState state) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    state.setDirection("up");
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    state.setDirection("down");
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    state.setDirection("left");
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    state.setDirection("right");
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    state.endGame = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    state.up = false;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    state.down = false;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    state.left = false;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    state.right = false;
                }
            }
        });
    }
}
