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
    private JTextArea url1, search;
    private int WIDTH = 800;
    private int HEIGHT = 700;


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
        mainFrame.setLayout(new GridLayout(8, 1)); //change to something other than GridLayout
//        mainFrame.setLayout(new FlowLayout());

        mb = new JMenuBar();

        url1 = new JTextArea();
        url1.setBounds(10, 10, 100, 10);

        search = new JTextArea();
        search.setBounds(10, 500, 100, 10);

        //headerLabel = new JLabel("url", JLabel.CENTER);

        urlLabel = new JLabel("url", JLabel.LEFT);
        urlLabel.setSize(350, 100);

        searchLabel = new JLabel("search term", JLabel.LEFT);
        searchLabel.setSize(350, 100);

        resultSection = new JLabel("results", JLabel.CENTER);
        resultSection.setSize(350, 100);

        mainFrame.add(mb);

        mainFrame.add(urlLabel);

        mainFrame.add(url1);

        mainFrame.add(searchLabel);

        mainFrame.add(search);

        mainFrame.setJMenuBar(mb);





        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
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
        mainFrame.add(statusLabel);


//        mainFrame.add(resultSection);

        mainFrame.setVisible(true);



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

//            URL url = new URL("https://www.milton.edu/");
            URL url = new URL("https://www." + url1.getText() + "/");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String line;

            while ( (line = reader.readLine()) != null ) {

                int start = line.indexOf("https");
                int end1 = line.indexOf("'", start);
                int end2 = line.indexOf("\"", start);

                if (line.contains(search.getText())) {
                    if (line.contains("https://")) {
                        if (start >= 0 && end1 >= 0) {
                            System.out.println(line.substring(start, end1));
                        }
                        if (start >= 0 && end2 >= 0) {
                            System.out.println(line.substring(start, end2));
                        }
                    }
                }
            }

            reader.close();

        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

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