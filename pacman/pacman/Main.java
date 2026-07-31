import javax.swing.*;

public class Main {

    // The entry point of the application
    public static void main(String[] args) {
        // Create a new JFrame with the title "Pacman"
        JFrame frame = new JFrame("Pacman");

        // Set the default close operation to exit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame to 800x600 pixels
        frame.setSize(800, 600);

        // Prevent the frame from being resized by the user
        frame.setResizable(false);

        // Create a new StateManager object and pass the frame to it
        StateManager stateManager = new StateManager(frame);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Ensure the frame is focusable and request focus for it
        frame.setFocusable(true);
        frame.requestFocus();

        // Make the frame visible
        frame.setVisible(true);
    }
}
