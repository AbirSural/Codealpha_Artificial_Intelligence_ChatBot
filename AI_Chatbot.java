import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AIChatbot extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    Map<String, String> faqResponses;

    public AIChatbot() {
        setTitle("Java AI Chatbot");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField(30);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Initialize expanded responses
        faqResponses = new HashMap<>();
        // Greetings
        faqResponses.put("hi", "Hello! How can I assist you today?");
        faqResponses.put("hello", "Hi there! What would you like to talk about?");
        faqResponses.put("good morning", "Good morning! Hope you have a wonderful day ahead.");
        faqResponses.put("good night", "Good night! Sleep well.");

        // Personal questions
        faqResponses.put("how are you", "I'm an AI bot, so I don't have feelings, but I'm here to help!");
        faqResponses.put("who are you", "I'm a Java-based AI chatbot created to assist you.");

        // Weather
        faqResponses.put("weather", "I'm not connected to live weather data, but it's always a good day to learn something new!");

        // Technology
        faqResponses.put("java", "Java is a powerful, platform-independent programming language. Do you want to know more?");
        faqResponses.put("python", "Python is popular for data science, automation, and web development.");
        faqResponses.put("ai", "AI stands for Artificial Intelligence — making machines mimic human intelligence!");
        faqResponses.put("machine learning", "Machine learning enables computers to learn from data without explicit programming.");

        // Motivational
        faqResponses.put("motivate me", "Believe in yourself! Every step you take brings you closer to your goals.");
        faqResponses.put("inspire me", "Challenges are what make life interesting. Overcoming them is what makes life meaningful.");

        // Hobbies
        faqResponses.put("sports", "Sports are great for both physical and mental health. What's your favorite sport?");
        faqResponses.put("music", "Music can uplift your mood and inspire creativity. Which genre do you like?");
        faqResponses.put("movies", "Movies can take us on amazing journeys. Do you have a favorite one?");

        // Goodbye
        faqResponses.put("bye", "Goodbye! It was nice talking to you. Have a great day!");
        faqResponses.put("see you", "See you later! Feel free to chat anytime.");

        // General
        faqResponses.put("thank you", "You're welcome! Happy to help.");
        faqResponses.put("help", "Sure! Ask me anything about technology, motivation, hobbies, or general topics.");

        setVisible(true);
    }

    // Basic NLP pre-processing (lowercase and remove punctuation)
    private String preprocess(String text) {
        return text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").trim();
    }

    // Rule-based response logic
    private String generateResponse(String userInput) {
        String processedInput = preprocess(userInput);

        for (String key : faqResponses.keySet()) {
            if (processedInput.contains(key)) {
                return faqResponses.get(key);
            }
        }
        return "Hmm... I'm not sure how to respond to that. Can you ask something else?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = inputField.getText();
        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n");
        String botResponse = generateResponse(userInput);
        chatArea.append("Bot: " + botResponse + "\n");
        inputField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AIChatbot());
    }
}
