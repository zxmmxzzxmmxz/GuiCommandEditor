package com.sfu;
import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

public class OfficeSelectPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected NewCommandFrame frame;
	protected JLabel messageLabel;
	protected JComboBox<String> box;
	
	OfficeSelectPanel(String message,NewCommandFrame f){
		super();
		frame = f;
		this.messageLabel = new JLabel(message);
		this.box = new JComboBox<String>();
		Set<Office> offices = RunCommand.get_offices();
		for(Office o:offices){
			this.box.addItem(o.getName());
		}
		box.addActionListener(this);
		add(messageLabel);
		add(box);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.addPartToCommand((String)box.getSelectedItem());
		frame.next();
	}
	
}