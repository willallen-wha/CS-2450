package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import Common.NuggetMath;

/**
 * Class to represent the Calculator's GUI in the main GUI class.
 * Contains all necessary components and pieces, and does all necessary math based on items given.
 */
public class CalcGUI extends JPanel{
    
	// Establish all components outside of the constructor's scope to use them later
	private JPanel inputs, percentInputs, weightInputs, weightText, weightButtons, outputs;
	private JLabel percentLabel, percentNumLabel, weightLabel, modeLabel, poundLabel, numPounds, nugLabel, numNuggets;
	private JComboBox<String> percentMode;
	private JSlider percentSlider;
	private JTextField percentExact, weightInput;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClr, btn0, btnPer;
	private JRadioButton exact, rounded;
	private ButtonGroup outputMode;

	// Some constant error codes
	private static final String ERR_BADPERCENT = "Bad percent";
	private static final String ERR_PERCENT100 = "Percentage of 100";
	private static final String ERR_NONLOGICAL = "Non-logical argument";
	private static final String ERR_BADWEIGHT = "Bad weight";

	// Hashmap of valid button ActionEvents
	private static final String[] inputStrings = {"1","2","3"
												, "4","5","6"
												, "7","8","9"
											    , "C","0","."};
	private static final HashSet<String> keyInput = new HashSet<>(Arrays.asList(inputStrings));

    /**
     * Default constructor: Should be the only constructor used.
     * @param None
     * @return A new instance of a JPanel object to be used in the calculator.
     */
    public CalcGUI() {
    	
    	// Establish the gridbag objects for later use
    	GridBagLayout l;
    	GridBagConstraints c;
    	
    	// Set the overarching layout for Calc GUI
    	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    	
    	// --------------------------------INPUTS--------------------------------
    	// Manager Panel
    	inputs = new JPanel();
    	inputs.setLayout(new BorderLayout(0, 0));
    	
    	// Panel which contains percent selector
    	percentInputs = new JPanel();
    	l = new GridBagLayout();
    	l.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    	percentInputs.setLayout(l);
    	
        // Label
        percentLabel = new JLabel("Desired Percentage:");
        percentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        c = new GridBagConstraints();
        c.insets = new Insets(20, 10, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.5; c.gridx = 0; c.gridy = 0; c.gridwidth = 3;
        percentInputs.add(percentLabel, c);
        
        // Options box
    	String[] options = {"Slider", "Exact"};
        percentMode = new JComboBox<>(options);
        percentMode.setSelectedIndex(0);
        percentMode.addActionListener(customListener);
        c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 0.5; c.gridx = 3; c.gridy = 0; c.gridwidth = 3;
        percentInputs.add(percentMode, c);

        // The slider or the input box, but only the slider is added in.
        percentSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 1);
        percentSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                update();
            }
        });
        percentSlider.setMajorTickSpacing(10); percentSlider.setMinorTickSpacing(1);
        percentSlider.setPaintTicks(true); percentSlider.setPaintLabels(true);
        c = new GridBagConstraints();
        c.gridx = 0; c.weightx = 1; c.gridy = 1; c.gridwidth = 5;
        percentInputs.add(percentSlider, c);
        
        percentNumLabel = new JLabel("1%");
        percentNumLabel.setAlignmentX(CENTER_ALIGNMENT); percentNumLabel.setAlignmentY(CENTER_ALIGNMENT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL; c.insets = new Insets(0, 10, 0, 0); 
        c.gridx = 5;c.gridy = 1; c.gridwidth = 1;
        percentInputs.add(percentNumLabel, c);

    	percentExact = new JTextField();
    	percentExact.setFont(new Font("Tahoma", Font.PLAIN, 18));
		percentExact.addActionListener(customListener);
    	percentExact.setColumns(10);
		percentExact.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {update();}
			public void keyPressed(KeyEvent e) {update();}
			public void keyReleased(KeyEvent e) {update();}});
    	c = new GridBagConstraints(); c.insets = new Insets(9, 10, 8, 10);
    	c.gridwidth = 6; c.fill = GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 1;
		percentExact.setVisible(false);
    	percentInputs.add(percentExact, c);
    	
    	// Panel which contains weight input
    	weightInputs = new JPanel();
    	weightInputs.setLayout(new BoxLayout(weightInputs, BoxLayout.Y_AXIS));
    	
    	weightText = new JPanel();
    	weightInputs.add(weightText);
    	
    	weightLabel = new JLabel("Current Weight:   ");
    	weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
    	weightText.add(weightLabel);
    	weightLabel.setLabelFor(weightInput);
    	
    	weightInput = new JTextField();
		weightInput.setText("99");
    	weightInput.setColumns(10);
		weightInput.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {update();}
			public void keyPressed(KeyEvent e) {update();}
			public void keyReleased(KeyEvent e) {update();}});
    	weightText.add(weightInput);
    	
    	weightButtons = new JPanel();
    	weightInputs.add(weightButtons);
    	l = new GridBagLayout();
    	l.columnWidths = new int[]{0, 0, 0, 0}; l.rowHeights = new int[]{30, 30, 30, 30, 30};
    	l.columnWeights = new double[]{0.5, 0.5, 0.5, Double.MIN_VALUE}; l.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    	weightButtons.setLayout(l);
    	
		int centerOff = 75, topOff = 15;

    	btn1 = new JButton("1");
		btn1.setActionCommand("1"); btn1.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(topOff, centerOff, 5, 5); c.gridx = 0; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn1, c);
    	
    	btn2 = new JButton("2");
		btn2.setActionCommand("2"); btn2.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(topOff, 5, 5, 5); c.gridx = 1; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn2, c);
    	
    	btn3 = new JButton("3");
		btn3.setActionCommand("3"); btn3.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(topOff, 5, 5, centerOff); c.gridx = 2; c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn3, c);

    	btn4 = new JButton("4");
		btn4.setActionCommand("4"); btn4.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, centerOff, 5, 5); c.gridx = 0; c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn4, c);
    	
    	btn5 = new JButton("5");
		btn5.setActionCommand("5"); btn5.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, 5); c.gridx = 1; c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn5, c);
    	
    	btn6 = new JButton("6");
		btn6.setActionCommand("6"); btn6.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, centerOff); c.gridx = 2; c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn6, c);

    	btn7 = new JButton("7");
		btn7.setActionCommand("7"); btn7.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, centerOff, 5, 5); c.gridx = 0; c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn7, c);
    	
    	btn8 = new JButton("8");
		btn8.setActionCommand("8"); btn8.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, 5); c.gridx = 1; c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn8, c);
    	
    	btn9 = new JButton("9");
		btn9.setActionCommand("9"); btn9.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, centerOff); c.gridx = 2; c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn9, c);

    	btnClr = new JButton("C");
		btnClr.setActionCommand("C"); btnClr.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, centerOff, 5, 5); c.gridx = 0; c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btnClr, c);
    	
    	btn0 = new JButton("0");
		btn0.setActionCommand("0"); btn0.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, 5); c.gridx = 1; c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btn0, c);
    	
    	btnPer = new JButton(".");
		btnPer.setActionCommand("."); btnPer.addActionListener(customListener);
    	c = new GridBagConstraints(); c.insets = new Insets(5, 5, 5, centerOff); c.gridx = 2; c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
    	weightButtons.add(btnPer, c);

    	// Add everything in
    	inputs.add(percentInputs, BorderLayout.NORTH);
    	inputs.add(weightInputs, BorderLayout.CENTER);
    	
    	// --------------------------------OUTPUTS--------------------------------
    	// The right panel, which contains all the outputs
    	outputs = new JPanel();
    	l = new GridBagLayout();
    	l.columnWidths = new int[]{71, 71, 0};
    	l.rowHeights = new int[]{29, 21, 21, 0, 0};
    	l.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    	l.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
    	outputs.setLayout(l);
    	
    	// Output mode selector
    	modeLabel = new JLabel("Output Mode");
    	modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	modeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	modeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	c = new GridBagConstraints();
    	c.insets = new Insets(5, 0, 5, 0); c.anchor = GridBagConstraints.NORTH;
    	c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
    	outputs.add(modeLabel, c);
    	
    	exact = new JRadioButton("Exact");
    	exact.setSelected(true);
        exact.addActionListener(customListener);
    	c = new GridBagConstraints();
    	c.insets = new Insets(0, 0, 5, 0); c.gridx = 0; c.gridy = 1;
    	outputs.add(exact, c);
    	
    	rounded = new JRadioButton("Rounded");
        rounded.addActionListener(customListener);
    	c = new GridBagConstraints();
    	c.insets = new Insets(0, 0, 5, 0); c.gridx = 1; c.gridy = 1;
    	outputs.add(rounded, c);
    	
    	outputMode = new ButtonGroup();
    	outputMode.add(exact); outputMode.add(rounded);
    	
    	// Output for the number of pounds needed
        poundLabel = new JLabel("Pounds Needed");
    	poundLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        poundLabel.setHorizontalAlignment(JLabel.CENTER);
    	c = new GridBagConstraints(); c.insets = new Insets(20, 0, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        outputs.add(poundLabel, c);
        
        numPounds = new JLabel("12.03");
        numPounds.setVerticalAlignment(JLabel.CENTER);
        numPounds.setHorizontalAlignment(JLabel.CENTER);
        numPounds.setFont(new Font("Monospaced", Font.BOLD, 25));
        numPounds.setBorder(BorderFactory.createLoweredBevelBorder());
    	c = new GridBagConstraints(); c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        outputs.add(numPounds, c);
        
        // Output for the number of nuggets needed
        nugLabel = new JLabel("Nuggets Needed");
    	nugLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nugLabel.setHorizontalAlignment(JLabel.CENTER);
    	c = new GridBagConstraints(); c.insets = new Insets(20, 0, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL; c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        outputs.add(nugLabel, c);
        
        numNuggets = new JLabel("102.5");
        numNuggets.setVerticalAlignment(JLabel.CENTER);
        numNuggets.setHorizontalAlignment(JLabel.CENTER);
        numNuggets.setFont(new Font("Monospaced", Font.BOLD, 25));
        numNuggets.setBorder(BorderFactory.createLoweredBevelBorder());
    	c = new GridBagConstraints(); c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        outputs.add(numNuggets, c);
        
		// Logout button
		JButton logoutButton = new JButton("Sign Out");
        logoutButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		logoutButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {requestExit();}});
    	c = new GridBagConstraints(); c.insets = new Insets(30, 0, 20, 0);
        c.fill = GridBagConstraints.NONE; c.gridx = 0; c.gridy = 6; c.gridwidth = 2;
		outputs.add(logoutButton, c);

    	// Add everything in
    	this.add(inputs);
    	this.add(outputs);

		// Validate and update
		this.validate();
		this.update();

    }

    /**
     * Custom action listener which is called any time something is changed on the form.
     * The listener simply calls the update() function, which updates all components of the 
     * panel.
     * @see update
     */
    private ActionListener customListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
			// If a button called this update, make the requested change
			if(e.getActionCommand() != null) {
				updateWeightField(e.getActionCommand());
			}
            update();
        }
    };

    /**
     * Function which updates all of the components in this frame. Uses aspects of the individual pieces of the frame
     * to inform what should be output. Should only be called internally.
     */
    private void update() {
		// Begin gathering inputs
		double desiredPercent = getPercent();
		if(desiredPercent == -1) {
			error(ERR_BADPERCENT);
			return;
		}
		double currentWeight = getWeight();
		if(currentWeight == -1) {
			error(ERR_BADWEIGHT);
			return;
		}

		// Calculate values
		BigDecimal pounds, nuggets;
		try {
			pounds = NuggetMath.amountForPercent(currentWeight, desiredPercent);
			nuggets = NuggetMath.numberNuggets(currentWeight, desiredPercent);
		} catch(IllegalArgumentException ill) {
			error(ERR_NONLOGICAL);
			return;
		} catch(ArithmeticException e) {
			e.printStackTrace();
			error(ERR_PERCENT100);
			return;
		}

		// Begin output
		if(exact.isSelected()) {
			numPounds.setText(pounds.toString());
			// If no nuggets are needed, then the output looks goofy so check for that
			if(nuggets.toString().equals("0E+61")) numNuggets.setText(0 + "");
			else numNuggets.setText(nuggets.toString());
		}
		else {
			numPounds.setText(NuggetMath.roundUp(pounds) + "");
			numNuggets.setText(NuggetMath.roundUp(nuggets) + "");
		}

        System.out.println("Something happened");

		// Validate
		this.validate();
    }

	/**
	 * Function to update the weight field in the UI. Should only called before the update() function by the custom
	 * listener.
	 * @param update String which holds the requested update
	 * @return none
	 * @see update() and customerListener
	 */
	private void updateWeightField(String update) {
		// Input validation
		if(!keyInput.contains(update)) return;
		// If clear is requested, clear the text box
		if(update.equals("C")) weightInput.setText("");
		// Otherwise, just append that string
		else weightInput.setText(weightInput.getText() + update);
	}

	private double getPercent() {
		// If the input is on the slider, make the slider visible and update the slider display
		if(percentMode.getSelectedItem().equals("Slider")) {
			percentExact.setVisible(false);
			percentNumLabel.setVisible(true);
			percentSlider.setVisible(true);
			percentNumLabel.setText(percentSlider.getValue() + "%");
			return Double.valueOf(percentSlider.getValue());
		}
		// Otherwise, remove the slider and make the text field visible
		else {
			percentExact.setVisible(true);
			percentNumLabel.setVisible(false);
			percentSlider.setVisible(false);
			// Validate the input from the text input
			return validateDouble(percentExact.getText());
		}
	}

	private double getWeight() {
		return validateDouble(weightInput.getText());
	}

	/**
	 * Method to take a string as input, and then validate it. Returns the string validated into a doulbe value.
	 * @param s String to be validated.
	 * @return The string interperated as a double value.
	 */
	private double validateDouble(String s) {
		// Do a little cleaning
		s = s.trim();
		// Remove any percent sign if present
		s = s.replaceAll("%", "");
		// If the text is empty, assume 0.
		if(s.equals("")) {
			return 0;
		}
		// Otherwise, attempt parsing
		else {
			try {
				return Double.valueOf(s);
			} catch(Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	/**
	 * Method for requesting closure of self. Used for signing out, and calls the static closure method
	 * in MainGUI.java. Results in destruction of this GUI element in memory.
	 * 
	 * @param none
	 * @return none
	 * @see MainGUI.exit
	 */
	private void requestExit() {
		MainGUI.exit(this);
	}

	private void error(String error) {
		//Output that there has been an error
		numPounds.setText("ERR");
		numNuggets.setText("ERR");
	}
}
