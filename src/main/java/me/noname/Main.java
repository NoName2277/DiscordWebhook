package me.noname;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame{

    private JPanel mainPanel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JButton clickButton;
    private JTextField messageField;
    private JTextField webhookTextField1;
    private JRadioButton embedRadioButton;
    private JRadioButton ttsRadioButton;


    Main(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));

        pack();
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiscordWebhook webhook = new DiscordWebhook(webhookTextField1.getText());
                if (embedRadioButton.isSelected()) {
                    webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(messageField.getText()));
                } else {
                    webhook.setContent(messageField.getText());
                }

                if (ttsRadioButton.isSelected()) {
                    webhook.setTts(true);
                }

                try {
                    webhook.execute();
                } catch (IOException x) {
                    JOptionPane.showMessageDialog(mainPanel, "Webhook jest niepoprawny", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new Main("Test");
        frame.setVisible(true);

    }

}
