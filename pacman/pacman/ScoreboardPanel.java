import javax.swing.*;
import java.awt.*;
import java.lang.Integer;
import java.util.List;

public class ScoreboardPanel extends JPanel {

    public MenuButton actionButton;
    private JList<String> scoreList;
    private JPanel mainPanel;

    public ScoreboardPanel(List<String> names, List<Integer> scores) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < names.size(); i++) {
            listModel.addElement(String.format("%2d. %-15s %5d", i + 1, names.get(i), scores.get(i)));
        }

        scoreList = new JList<>(listModel);
        scoreList.setFont(new Font("monospaced", Font.PLAIN, 18)); // Scale up the font size
        scoreList.setFixedCellHeight(30); // Scale up the cell height for better readability

        JScrollPane scrollPane = new JScrollPane(scoreList);
        scrollPane.setPreferredSize(new Dimension(700, 400)); // Scale up the scroll pane size

        actionButton = new MenuButton("back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(actionButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createVerticalGlue());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
}