import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private final JButton rockButton, paperButton, scissorsButton, quitButton;
    private final JTextField playerWinsField, computerWinsField, tiesField;
    private final JTextArea resultTextArea;
    private final Random random = new Random();
    private int playerWins = 0, computerWins = 0, ties = 0;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Button panel
        JPanel buttonPanel = new JPanel();
        rockButton = new JButton(loadImage("src/rock.png", 128, 128));
        paperButton = new JButton(loadImage("src/paper.png", 128, 128));
        scissorsButton = new JButton(loadImage("src/scissors.png", 128, 128));
        quitButton = new JButton("Quit");
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Option"));

        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 6));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 5);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 5);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 5);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        // Result display area
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Add action listeners
        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        pack();
        setVisible(true);
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[random.nextInt(choices.length)];
        String result;

        if (playerChoice.equals(computerChoice)) {
            result = "It's a tie!";
            ties++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = playerChoice + " beats " + computerChoice + " (Player wins)";
            playerWins++;
        } else {
            result = computerChoice + " beats " + playerChoice + " (Computer wins)";
            computerWins++;
        }

        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
        resultTextArea.append(result + "\n");
    }

    private ImageIcon loadImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }


    public static void main(String[] args) {
        new RockPaperScissorsFrame();
    }
}
