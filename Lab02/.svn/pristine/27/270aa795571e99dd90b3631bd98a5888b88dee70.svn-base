package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private int _timeSlice = 50;  // update every 50 milliseconds

	private java.awt.Color color = java.awt.Color.BLUE;   // The color of the ball
	private java.awt.Point location = new java.awt.Point(23, 84);  // the location of the center of the ball
	private int radius = 10;   // the radius of the ball
	private Timer _timer = new Timer(_timeSlice, new ActionListener() {

	    /**
	     * The timer "ticks" by calling this method every _timeslice milliseconds
	     */
	    public void actionPerformed (ActionEvent e) {
	    	centerPanel.repaint();
	    }
	});
	private final JPanel centerPanel = new JPanel(){
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);   // Do everything normally done first, e.g. clear the screen.
		    g.setColor(color);  // Set the color to use when drawing
		    
		    g.fillOval(location.x -radius, location.y - radius, 2*radius, 2*radius);  // paint a filled 20x40 red ellipse whose upper left corner is at (75, 100)
		    location.x += 3;
		    location.y += 1;
		}
	};



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		initGUI();
		_timer.start();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(centerPanel, BorderLayout.CENTER);
	}

}
