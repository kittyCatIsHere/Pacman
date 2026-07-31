import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SavePanel extends JPanel {

    public MenuButton exitButton;
    public MenuButton saveButton;
    public String name = "";

    public SavePanel(int final_score) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        exitButton = new MenuButton("Exit");
        saveButton = new MenuButton("Save");

        Font font = new Font("Arial", Font.BOLD, 24);
        JLabel text1 = new JLabel(String.format("Your score is %d", final_score));
        JLabel text2 = new JLabel("Enter your name:");
        JTextField textField = new JTextField(20);

        text1.setFont(font);
        text2.setFont(font);
        textField.setFont(font);
        textField.setHorizontalAlignment(JTextField.CENTER);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                name = textField.getText();
            }
        });

        add(text1, gbc);
        add(text2, gbc);
        add(textField, gbc);
        add(saveButton, gbc);
        add(exitButton, gbc);
    }
}