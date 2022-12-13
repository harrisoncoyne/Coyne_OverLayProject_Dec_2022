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
    private JLabel resultSection;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextField url1, search;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public JTextArea results;
    public JScrollPane scroll;
    public URL url2;


    public Code() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Code layout = new Code();
        //HtmlRead html = new HtmlRead();
        layout.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(6, 1)); //change to something other than GridLayout
//        mainFrame.setLayout(new FlowLayout());

        mb = new JMenuBar();

        url1 = new JTextField("https://www.");
//        url1.setBounds(10, 10, 100, 10);

        search = new JTextField();
//        search.setBounds(10, 500, 100, 10);

        //headerLabel = new JLabel("url", JLabel.CENTER);

//        urlLabel = new JLabel("url (don't type: 'https://www.')", JLabel.CENTER);
        urlLabel = new JLabel("url", JLabel.CENTER);
//        urlLabel.setSize(350, 100);

        searchLabel = new JLabel("search term", JLabel.CENTER);
//        searchLabel.setSize(350, 100);

//        resultSection = new JLabel("results", JLabel.CENTER);
//        resultSection.setSize(350, 100);

//        mainFrame.add(mb);

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

//        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
//        mainFrame.add(statusLabel);

        JPanel panel = resultsPanel();
        mainFrame.add(BorderLayout.CENTER, new JScrollPane(panel));


//        mainFrame.add(resultSection);

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
            URL url2;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

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

//                                url2 = new URL(line.substring(start, end1) + "/");
//                                HtmlRead1();
//                                HtmlRead();
                            }
                            if (start >= 0 && end2 >= 0) {
                                System.out.println(line.substring(start, end2));
                                results.setText(results.getText() + "\n" + line.substring(start, end2) + "\n");

//                                url2 = new URL(line.substring(start, end2) + "/");
//                                HtmlRead1();
//                                HtmlRead();
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

//    public void HtmlRead1(){
//        try {
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(url2.openStream())
//            );
//
//            String line;
//
//            while ( (line = reader.readLine()) != null ) {
//
//                int start = line.indexOf("https");
//                int end1 = line.indexOf("' ", start);
//                int end2 = line.indexOf("\" ", start);
//
////                if (line.contains(search.getText())) {
//                    if (line.contains("https://")) {
//                        if (!line.contains("font")) {
//                            if (start >= 0 && end1 >= 0) {
//                                System.out.println(line.substring(start, end1));
//                                results.setText(results.getText() + "\n" + line.substring(start, end1));
//
//                                url2 = new URL(line.substring(start, end1) + "/");
//                                HtmlRead1();
//                            }
//                            if (start >= 0 && end2 >= 0) {
//                                System.out.println(line.substring(start, end2));
//                                results.setText(results.getText() + "\n" + line.substring(start, end2));
//
//                                url2 = new URL(line.substring(start, end2) + "/");
//                                HtmlRead1();
//                            }
//                        }
//                    }
//                }
////            }
//
//            reader.close();
//
//        }
//        catch(Exception ex) {
//            System.out.println(ex);
//        }
//
//    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                HtmlRead();
//                statusLabel.setText("typed: " + url.getText() + " : " + search.getText());
                statusLabel.setText(url1.getText() + " : " + search.getText());
//                statusLabel.setText("search");
            }
        }
    }
}