package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import shape.Circle;
import shape.EquilTri;

public class MainAppFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8600503871502565231L;
	private JPanel contentPane;
	private final JPanel pnlControl = new JPanel();
	private final JLabel lblNewLabel = new JLabel("lblInfo");
	private final JButton btnNewButton = new JButton("New button");
	private final JButton triButton = new JButton("Triangle");
	private final JButton rectButton = new JButton("Rectangle");
	private final JButton cirButton = new JButton("Circle");
	private final JButton compButton = new JButton("Composite");
	private final JTextField txtSmart = new JTextField();
	private shape.AShape myshape;
	private final JPanel pnlCenter = new JPanel() {

		/**
		* Overridden paintComponent method to paint a shape in the panel.
		* @param g The Graphics object to paint on.
		**/
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);   // Do everything normally done first, e.g. clear the screen.
		    g.setColor(Color.RED);  // Set the color to use when drawing
		    
		    g.fillOval(75, 100, 20, 40);  // paint a filled 20x40 red ellipse whose upper left corner is at (75, 100)
		    if (myshape !=null)
		    	myshape.paint(50, 50, g);
		}
		
		private static final long serialVersionUID = 5880450911248120591L;
		
	};
	
	private final JPanel pnlSouth = new JPanel() ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppFrame frame = new MainAppFrame();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAppFrame() {
		txtSmart.setText("Type message here.");
		txtSmart.setColumns(10);
		initGUI();
	}
	public void start() {
		setVisible(true);
	}
	/**
	 * 
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlControl.setBackground(Color.ORANGE);
		
		contentPane.add(pnlControl, BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(txtSmart.getText());
				
			}
		});
		
		pnlControl.add(txtSmart);
		
		pnlControl.add(btnNewButton);
		pnlSouth.add(triButton);
		pnlSouth.add(compButton);
		pnlSouth.add(rectButton);

		pnlSouth.add(cirButton);
		triButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				myshape = new EquilTri(3, 30, 50);
				repaint();
			}
		});
		rectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myshape = new shape.Rect(50, 50);
				repaint();
				
			}
		});
		
		
		cirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				myshape = new Circle(50);
				repaint();
			}
		});
		compButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				myshape = new shape.Comp();
				repaint();
			}
		});
		

		pnlControl.add(lblNewLabel);
		pnlCenter.setBackground(Color.PINK);
		
		pnlSouth.setBackground(Color.BLACK);
		
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
	}

}
