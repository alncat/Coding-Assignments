package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class View extends JFrame {

    private JPanel contentPane;
    private final JPanel panel = new JPanel();
    private final JSplitPane splitPane = new JSplitPane();
    private final JTextField textFieldFile = new JTextField();
    private final JLabel lblFile = new JLabel("File:");
    private final JButton btnLoad = new JButton("Load");
    private final JButton btnParse = new JButton("Parse");
    private final JButton btnPlay = new JButton("Play");
    private final JScrollPane scrollPaneFileContents = new JScrollPane();
    private final JScrollPane scrollPaneParsed = new JScrollPane();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public View() {
        textFieldFile.setToolTipText("The file to load. Assumed to be in the \"songs\" package with \".abc\" extension.");
        textFieldFile.setColumns(10);
        initGUI();
    }
    private void initGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        contentPane.add(panel, BorderLayout.NORTH);
        
        panel.add(lblFile);
        
        panel.add(textFieldFile);
        btnLoad.setToolTipText("Load the file and create the ABCParser.");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        panel.add(btnLoad);
        btnParse.setToolTipText("Parse the file and create the IPhrase structure.");
        
        panel.add(btnParse);
        btnPlay.setToolTipText("Play the parsed IPhrase data structure.");
        
        panel.add(btnPlay);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        contentPane.add(splitPane, BorderLayout.CENTER);
        scrollPaneFileContents.setToolTipText("The text contents of the file.");
        scrollPaneFileContents.setViewportBorder(new TitledBorder(null, "File Contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        splitPane.setLeftComponent(scrollPaneFileContents);
        scrollPaneParsed.setToolTipText("The String representation of the IPhrase data structure parsed from the input ABC file.");
        scrollPaneParsed.setViewportBorder(new TitledBorder(null, "Parsed IPhrase Structure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        splitPane.setRightComponent(scrollPaneParsed);
    }

}
