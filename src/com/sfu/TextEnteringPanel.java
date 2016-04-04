package com.sfu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextEnteringPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected NewCommandFrame frame;
	protected JLabel messageLabel;
	protected JTextField textField;
	protected JButton okButton;
	protected boolean should_letter;
	
	TextEnteringPanel(String message,NewCommandFrame f,boolean is_letter){
		super();
		this.should_letter = is_letter;
		this.frame = f;
		this.messageLabel = new JLabel(message);
		this.textField = new JTextField("");
		Dimension size = new Dimension(300,25);
		this.textField.setPreferredSize(size);
		this.textField.setMaximumSize(size);
		this.textField.setMinimumSize(size);
		this.okButton = new JButton("OK");
		okButton.addActionListener(this);
		add(messageLabel);
		add(textField);
		add(okButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((should_letter && !this.textField.getText().equals("") &&!this.textField.getText().matches(".*\\d.*")) || (!this.should_letter && !this.textField.getText().equals("") &&this.textField.getText().matches("\\d*"))){
			frame.addPartToCommand(this.textField.getText());
			frame.next();
		}
		else{
			JOptionPane.showMessageDialog(frame,"Please Enter Valid Parameter!");
		}
	}
	
}
