import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Code implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JLabel urlLabel;
    private JLabel searchLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JTextField url1, search;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public JTextArea results;


    public Code() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Code layout = new Code();
        layout.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(6, 1));

        mb = new JMenuBar();

        url1 = new JTextField("https://www.");

        search = new JTextField();

        urlLabel = new JLabel("url", JLabel.CENTER);

        searchLabel = new JLabel("search term", JLabel.CENTER);

        mainFrame.add(urlLabel);

        mainFrame.add(url1);

        mainFrame.add(searchLabel);

        mainFrame.add(search);

        mainFrame.setJMenuBar(mb);

        headerLabel = new JLabel("1", JLabel.CENTER);
        statusLabel = new JLabel("2", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);

        JPanel panel = resultsPanel();
        mainFrame.add(BorderLayout.CENTER, new JScrollPane(panel));

        mainFrame.setVisible(true);



    }

    public JPanel resultsPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel label = new JLabel("RESULTS:");

        results = new JTextArea();

        panel.add(label);
        panel.add(results);

        return panel;
    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("OK");

        okButton.setActionCommand("OK");

        okButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void HtmlRead(){
        try {
            URL url = new URL( url1.getText() + "/");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            while ( (line = reader.readLine()) != null ) {

                int start = line.indexOf("https");
                int end1 = line.indexOf("' ", start);
                int end2 = line.indexOf("\" ", start);

                if (line.contains(search.getText())) {
                    if (line.contains("https://")) {
                        if (!line.contains("font")) {
                            if (start >= 0 && end1 >= 0) {
                                System.out.println(line.substring(start, end1));
                                results.setText(results.getText() + "\n" + line.substring(start, end1) + "\n");
                            }
                            if (start >= 0 && end2 >= 0) {
                                System.out.println(line.substring(start, end2));
                                results.setText(results.getText() + "\n" + line.substring(start, end2) + "\n");
                            }
                        }
                    }
                }
            }

            reader.close();

        }
        catch(Exception ex) {
            System.out.println(ex);
        }

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                HtmlRead();
                statusLabel.setText(url1.getText() + " : " + search.getText());
            }
        }
    }
}