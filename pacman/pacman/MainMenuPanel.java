import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {

    public MenuButton newGameButton;
    public MenuButton scoreboardButton;

    public MainMenuPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        this.newGameButton = new MenuButton("New Game");

        JButton exitButton = new MenuButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.scoreboardButton = new MenuButton("High Scores");

        add(this.newGameButton, gbc);
        add(this.scoreboardButton, gbc);
        add(exitButton, gbc);
    }
}