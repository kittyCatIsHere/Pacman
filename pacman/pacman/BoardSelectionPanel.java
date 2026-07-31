import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;

public class BoardSelectionPanel extends JPanel {

    public MenuButton[] boardButton = new MenuButton[5];

    public BoardSelectionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        for (int i = 0; i < 5; i++) {
            boardButton[i] = new MenuButton("Board " + (i + 1));
            add(boardButton[i], gbc);
        }
    }
}