/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package register_and_login;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Message {
    private final String messageID;
    private final String recipient;
    private final String messageText;
    private final String messageHash;
    private static final List<String> messageList = new ArrayList<>();

    public Message(String messageID, String recipient, String messageText) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    Message() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private String createMessageHash() {
        return String.valueOf(new Random().nextInt(100000));
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10 && !messageID.trim().isEmpty();
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.length() <= 10 && recipient.startsWith("+");
    }

    public boolean checkMessageText() {
        return messageText != null && messageText.length() <= 250 && !messageText.trim().isEmpty();
    }

    public void sentMessage() {
        if (checkMessageID() && checkRecipientCell() && checkMessageText()) {
            String msgEntry = String.format("ID: %s, Hash: %s, Recipient: %s, Message: %s", 
                messageID, messageHash, recipient, messageText);
            messageList.add(msgEntry);
            
            JOptionPane.showMessageDialog(null, 
                "Message Sent:\nID: " + messageID +
                "\nHash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText);
        } else {
            JOptionPane.showMessageDialog(null, 
                "INVALID MESSAGE:\n" +
                (!checkMessageID() ? "• ID must be ≤10 chars\n" : "") +
                (!checkRecipientCell() ? "• Recipient must start with + and be ≤10 chars\n" : "") +
                (!checkMessageText() ? "• Message must be ≤250 chars" : ""));
        }
    }

    public static String printMessages() {
        return String.join("\n", messageList);
    }

    public static int returnTotalMessages() {
        return messageList.size();
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void pack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

class QuickChat {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        boolean running = true;

        while (running) {
            String menu = """
                    1. Send Message
                    2. Show recently sent messages
                    3. Quit""";
            String choice = JOptionPane.showInputDialog(menu);

            switch (choice) {
                case "1" -> sendMessages();
                case "2" -> showRecentMessages();
                case "3" -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    private static void sendMessages() {
        for (int i = 0; i < 2; i++) {
            String messageID = JOptionPane.showInputDialog("Enter Message ID (max 10 characters):");
            String recipient = JOptionPane.showInputDialog("Enter recipient cell number (max 10 characters, must start with +):");
            String messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            
            Message message = new Message(messageID, recipient, messageText);
            message.sentMessage();
        }
        
        // Access static methods directly through class
        JOptionPane.showMessageDialog(null, 
            "All messages Sent:\n" + Message.printMessages() +
            "\n\nTotal Messages Sent: " + Message.returnTotalMessages());
    }

    private static void showRecentMessages() {
        if (Message.returnTotalMessages() == 0) {
            JOptionPane.showMessageDialog(null, "No messages sent yet!");
        } else {
            JOptionPane.showMessageDialog(null, 
                "Recent Messages:\n" + Message.printMessages());
        }
    }
}