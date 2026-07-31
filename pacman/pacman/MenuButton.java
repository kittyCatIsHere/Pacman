import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {

    public MenuButton(String text) {
        super(text);

        setFont(new Font("Arial", Font.BOLD, 24));
        setPreferredSize(new Dimension(200, 50));
        setFocusPainted(false);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent evt) {
                setBackground(Color.BLACK);
                setForeground(Color.WHITE);
            }
        });
    }
}