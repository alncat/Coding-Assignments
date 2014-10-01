package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// import javax.swing.BoxLayout;

// import java.awt.FlowLayout;

// import javax.swing.GroupLayout;
// import javax.swing.GroupLayout.Alignment;


// import model.IViewControlAdapter;
// import model.IViewUpdateAdapter;
import net.miginfocom.swing.MigLayout;

// import javax.swing.JScrollPane;
// import javax.swing.JList;

// import strategies.IUpdateStrategy;

/**
 * View Class for the BallWorld GUI
 * 
 * @author Jayson Carter, Xilin Liu
 */
public class BallView<TDropListItem> extends JFrame {
	/** Generated SerialVersion UID for the body panel */
	private static final long serialVersionUID = -7063488266654092746L;
	/**
	 * Buttons and DropList
	 */
	private final JButton btnCombineStrategy = new JButton("Combine!");
	private final JButton btnMakeBall = new JButton("Make Selected Ball");
	private final JComboBox<TDropListItem> lstTopStrategy = new JComboBox<TDropListItem>();
	private final JComboBox<TDropListItem> lstBottomStrategy = new JComboBox<TDropListItem>();
	private final JButton btnMakeSwitcher = new JButton("Make Switcher");
	private final JButton btnSwitchStrategy = new JButton("Switch!");
	private final JButton btnAddPaintStrategy = new JButton("Add");
	private final JTextField txtPaintStrategy = new JTextField();
	private final JComboBox<TDropListItem> lstPaintStrategy = new JComboBox<TDropListItem>();

	private IModelControlAdapter<TDropListItem> _modelControlAdapter;
	@SuppressWarnings("rawtypes")
	private IModelUpdateAdapter _modelUpdateAdapter = IModelUpdateAdapter.NULL_OBJECT;

	/** Content Panel that holds all panels. */
	private JPanel contentPane;

	/** Header panel that holds the options for the user. */
	private final JPanel hdrPanel = new JPanel();

	/** Text Field to type the name of the class into. */
	private final JTextField txtAddStrategy = new JTextField();

	/**
	 * Button to add a ball of the name specified by txtBallClassToAdd to bodyPanel upon click.
	 */
	private final JButton btnAddStrategy = new JButton("Add Strategy");

	/** Button to clear all balls upon click. */
	private final JButton btnClearBalls = new JButton("Clear Balls");

	/** Panel in which the Balls will be displayed */
	private final JPanel bodyPanel = new JPanel() {
		/** Generated SerialVersion UID for the body panel */
		private static final long serialVersionUID = -6952656931251224807L;

		/**
		 * Repains the panel. Clears all items on the panel and repains as specified by the
		 * adapter.paint function.
		 * 
		 * @param g The graphic to paint onto
		 */
		public void paintComponent(Graphics g) {
			/* Clears the screen */
			super.paintComponent(g);
			/* Call model to paint all required items */
			_modelUpdateAdapter.update(g);

		}
	};


	/**
	 * Constructor is supplied with an instance of the model adapters.
	 * 
	 * @param _inModelControlAdapter - Control Adapter from model to view
	 * @param _inModelUpdateAdapter - Update Adapter from model to view
	 */
	@SuppressWarnings("unchecked")
	public BallView(@SuppressWarnings("rawtypes") IModelControlAdapter _inModelControlAdapter,
			@SuppressWarnings("rawtypes") IModelUpdateAdapter _inModelUpdateAdapter) {
		txtPaintStrategy.setText("BirdSheepImage");
		txtPaintStrategy.setToolTipText("Add Update Strategies Here!");
		txtPaintStrategy.setColumns(10);
		this._modelControlAdapter = _inModelControlAdapter;
		this._modelUpdateAdapter = _inModelUpdateAdapter;
		initGUI();
	}


	/**
	 * Starts up the GUI
	 */
	public void start() {
		/* Makes the GUI visible */
		setVisible(true);
	}

	/**
	 * Initializes the GUI
	 */
	private void initGUI() {

		/* Set up frame and panels */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/* Sets up the header panel, including tooltips for all items */
		hdrPanel.setBackground(new Color(153, 204, 255));
		contentPane.add(hdrPanel, BorderLayout.NORTH);
		hdrPanel.setLayout(new MigLayout("", "[134px,grow][95px,grow][][134px,grow][grow]",
				"[grow][][grow][]"));
		btnMakeBall.setToolTipText("Make a ball with the strategy selected in the top DropList");
		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Make ball with selected strategy
				TDropListItem item = lstTopStrategy.getItemAt(lstTopStrategy.getSelectedIndex());
				TDropListItem paint =
						lstPaintStrategy.getItemAt(lstPaintStrategy.getSelectedIndex());
				if (item == null || paint == null)
					return;
				_modelControlAdapter.makeBall(item, paint);
			}
		});

		hdrPanel.add(btnMakeBall, "cell 1 0,alignx center");

		hdrPanel.add(txtPaintStrategy, "cell 4 0,growx");
		txtAddStrategy.setText("Straight");
		txtAddStrategy.setToolTipText("Type name of strategy here.");
		txtAddStrategy.setColumns(10);
		hdrPanel.add(txtAddStrategy, "cell 0 1,growx,aligny top");
		lstTopStrategy.setToolTipText("Add Paint Strategies Here!");

		hdrPanel.add(lstTopStrategy, "cell 1 1,grow");
		btnMakeSwitcher.setToolTipText("Create a ball that can switch strategies.");
		btnMakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Make switcherBall with straight strategy
				TDropListItem switcher =
						lstPaintStrategy.getItemAt(lstPaintStrategy.getSelectedIndex());
				_modelControlAdapter.makeSwitcherBall(switcher);
			}
		});

		hdrPanel.add(btnMakeSwitcher, "cell 2 1,alignx center");
		btnAddPaintStrategy.setToolTipText("Update strategy for ball");
		btnAddPaintStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Add a paint strategy to the list
				String sName = txtPaintStrategy.getText();
				for(int i = 0; i < lstPaintStrategy.getItemCount(); i++){
					if(lstPaintStrategy.getItemAt(i).toString().contains(sName)){
						return;
					}
				}
				if (sName == null)
					return;
				TDropListItem uStr = _modelControlAdapter.addStrategy("paint", sName);
				if (uStr == null)
					return;

				lstPaintStrategy.insertItemAt(uStr, 0);
				//lstPaintStrategy.setAction((Action) uStr);
				//lstPaintStrategy.setName(sName);
				lstPaintStrategy.setSelectedItem(uStr);
			}
		});

		hdrPanel.add(btnAddPaintStrategy, "cell 4 1,growx,aligny center");
		btnAddStrategy.setToolTipText("Add strategy to both dropdown lists. The rest of the classname is already added.");
		hdrPanel.add(btnAddStrategy, "cell 0 2,alignx center,aligny top");
		btnAddStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add a strategy to the list
				String sName = txtAddStrategy.getText();
				for(int i = 0; i < lstTopStrategy.getItemCount(); i++){
				if(lstTopStrategy.getItemAt(i).toString().contains(sName)){
					return;
				}
				}
				if (sName == null)
					return;
				TDropListItem uStr = _modelControlAdapter.addStrategy("update", sName);
				if (uStr == null)
					return;
				
				lstTopStrategy.insertItemAt(uStr, 0);
				lstBottomStrategy.insertItemAt(uStr, 0);
				lstTopStrategy.setSelectedItem(uStr);
				lstBottomStrategy.setSelectedItem(uStr);

			}

		});
		btnCombineStrategy.setToolTipText("Combine the selected strategies from both dropdown lists into a single strategy that is added to both dropdown lists.");
		btnCombineStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Combine two strategies and add the combined-strategy to the strategy list
				TDropListItem item1 = lstTopStrategy.getItemAt(lstTopStrategy.getSelectedIndex());
				TDropListItem item2 =
						lstBottomStrategy.getItemAt(lstBottomStrategy.getSelectedIndex());

				if (item1 == null || item2 == null)
					return;

				TDropListItem itemCom = _modelControlAdapter.combineStrategies(item1, item2);
				lstTopStrategy.insertItemAt(itemCom, 0);
				lstBottomStrategy.insertItemAt(itemCom, 0);
			}
		});

		hdrPanel.add(lstBottomStrategy, "cell 1 2,grow");
		btnSwitchStrategy.setToolTipText("Switch the strategy on all switcher balls to the currently selected strategy in the upper dropdown list.");
		btnSwitchStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Apply switch strategy to all SwitcherBalls
				TDropListItem item1 = lstTopStrategy.getItemAt(lstTopStrategy.getSelectedIndex());
				if (item1 == null)
					return;
				_modelControlAdapter.switchStrategy(item1);

			}
		});

		hdrPanel.add(btnSwitchStrategy, "cell 2 2,alignx center");
		btnClearBalls.setToolTipText("Click to remove all balls from the display area below");
		hdrPanel.add(btnClearBalls, "cell 3 2,alignx center,aligny top");
		btnClearBalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear all balls on canvas
				_modelControlAdapter.clearBalls();
			}
		});
		lstPaintStrategy.setToolTipText("Select a strategy.");

		hdrPanel.add(lstPaintStrategy, "cell 4 2,growx");

		hdrPanel.add(btnCombineStrategy, "cell 1 3,alignx center");
		contentPane.add(bodyPanel, BorderLayout.CENTER);
	}

	/**
	 * Repaints the view.
	 */
	public void update() {
		bodyPanel.repaint();
	}

	/**
	 * Gets the bodyPanel as a Component
	 * 
	 * @return the bodyPanel
	 */
	public Component getCanvas() {
		return bodyPanel;
	}
}
