package com.sfu;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.List;

public class mainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel newCommandButtonsPanel,commandEditButtonsPanel;
	JButton NLButton,NPButton,PUButton,DButton,BButton,SciButton,SneakButton,GButton,NSADButton,InfButton,DefButton;
	JButton saveButton,deleteButton,moveUpButton,moveDownButton;
	JScrollPane commandsDisplayPanel;
	Vector<String> commands;
	JList<String> JCommandsList;
	public static enum COMMAND_PART{
		RECEIVER,SENDER,INIT_OFFICE,DEST_OFFICE,LENGTH,POSTAGE,BUILD_OFFICE,TRANSIT_TIME,CAPACITY,PERSUATION,DELAY_AMOUNT,ScienceDay
	}
	
	public static enum COMMAND_TYPE{
		NEWLETTER,NEWPACKAGE,PICKUP,BUILD,NSADELAY,SCIENCE
	}
	
	mainFrame(){
		//0.init Frame
		this.setTitle("Command Create/Edit    --By Ximinz");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		//1.init buttons Panels
		/* Decrepted because new method new_add_button is introduced
		this.NLButton = new JButton("New Letter");
		this.NPButton = new JButton("New Package");
		this.PUButton = new JButton("Pick Up");
		this.DButton = new JButton("Day");
		this.saveButton = new JButton("Save");
		this.deleteButton = new JButton("Delete");
		this.moveUpButton = new JButton("Move Up");
		this.moveDownButton = new JButton("Move Down");
		*/
		this.newCommandButtonsPanel = new JPanel();
		this.commandEditButtonsPanel = new JPanel();
		
		this.newCommandButtonsPanel.add(new JLabel("New Command Panel"));
		boolean is_new_command = true;
		NLButton = new_add_button("New Letter",is_new_command);
		NPButton= new_add_button("New Package",is_new_command);
		PUButton = new_add_button("Pick Up",is_new_command);
		BButton = new_add_button("Build",is_new_command);
		SciButton = new_add_button("Science",is_new_command);
		NSADButton = new_add_button("NSADelay",is_new_command);
		DButton = new_add_button("Day",is_new_command);
		GButton = new_add_button("Good",is_new_command);
		SneakButton = new_add_button("Sneak",is_new_command);
		InfButton = new_add_button("Inflation",is_new_command);
		DefButton = new_add_button("Deflation",is_new_command);
		this.commandEditButtonsPanel.add(new JLabel("Edit Command Panel"));
		moveUpButton = new_add_button("Move Up",!is_new_command);
		moveDownButton = new_add_button("Move Down",!is_new_command);
		deleteButton = new_add_button("Delete",!is_new_command);
		saveButton = new_add_button("Save",!is_new_command);
		
		commands = new Vector<String>();
		List<String> temp = RunCommand.get_commands();
		if(temp != null)
			for(String s:temp){
				commands.add(s);
			}
		this.JCommandsList = new JList<String>(commands);
		this.commandsDisplayPanel = new JScrollPane(this.JCommandsList);
		//2.add listeners
		/* Decrepted because new method new_add_button is introduced
		this.NLButton.addActionListener(this);
		this.NPButton.addActionListener(this);
		this.PUButton.addActionListener(this);
		this.DButton.addActionListener(this);
		this.saveButton.addActionListener(this);
		this.deleteButton.addActionListener(this);
		this.moveDownButton.addActionListener(this);
		this.moveUpButton.addActionListener(this);
		*/
		//3.add buttons
		/* Decrepted because new method new_add_button is introduced
		buttonsPanel.add(this.NLButton);
		buttonsPanel.add(this.NPButton);
		buttonsPanel.add(this.PUButton);
		buttonsPanel.add(this.DButton);
		buttonsPanel.add(this.saveButton);
		buttonsPanel.add(this.deleteButton);
		buttonsPanel.add(this.moveUpButton);
		buttonsPanel.add(this.moveDownButton);
		*/
		this.newCommandButtonsPanel.setLayout(new FlowLayout());
		this.newCommandButtonsPanel.setPreferredSize(new Dimension(180,200));
		this.commandEditButtonsPanel.setLayout(new FlowLayout());
		this.commandEditButtonsPanel.setPreferredSize(new Dimension(180,200));
		this.setContentPane(new TranslucentPane());
		this.getContentPane().add(this.newCommandButtonsPanel);
		this.getContentPane().add(this.commandEditButtonsPanel);
		this.getContentPane().add(this.commandsDisplayPanel);
		this.pack();
		this.setVisible(true);
	}
	
	  public class TranslucentPane extends JPanel {

	        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			public TranslucentPane() {
	            setOpaque(false);
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g); 

	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setComposite(AlphaComposite.SrcOver.derive(0.85f));
	            g2d.setColor(getBackground());
	            g2d.fillRect(0, 0, getWidth(), getHeight());

	        }

	    }

	public static void main(String[] args) throws Exception {
		//setup for the BeautyEye LNF
    	try{
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e){}
    	//disable settings button
    	UIManager.put("RootPane.setupButtonVisible", false);
    	//setup offices
    	try{
    		new RunCommand();
    	}
    	catch(Exception e){
    		System.out.println("File Not Found!");
    	}
    	//start the frame
    	new mainFrame();
	}

	public void addNewCommand(String command){
		if(command != null){
			commands.add(command);
		}
		this.JCommandsList.updateUI();
		this.add(this.commandsDisplayPanel);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("clicked");
		Vector<COMMAND_PART> procedure = new Vector<COMMAND_PART>();
		if(e.getSource().equals(NLButton)){
			procedure.add(COMMAND_PART.INIT_OFFICE);
			procedure.add(COMMAND_PART.RECEIVER);
			procedure.add(COMMAND_PART.DEST_OFFICE);
			procedure.add(COMMAND_PART.SENDER);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.NEWLETTER);
		}
		else if(e.getSource() == this.NPButton){
			procedure.add(COMMAND_PART.INIT_OFFICE);
			procedure.add(COMMAND_PART.RECEIVER);
			procedure.add(COMMAND_PART.DEST_OFFICE);
			procedure.add(COMMAND_PART.POSTAGE);
			procedure.add(COMMAND_PART.LENGTH);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.NEWPACKAGE);
		}
		else if(e.getSource() == this.PUButton){
			procedure.add(COMMAND_PART.INIT_OFFICE);
			procedure.add(COMMAND_PART.RECEIVER);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.PICKUP);
		}
		else if(e.getSource() == this.SciButton){
			procedure.add(COMMAND_PART.ScienceDay);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.SCIENCE);
		}
		else if(e.getSource() == this.DButton){
			addNewCommand("DAY");
		}
		else if(e.getSource() == this.GButton){
			addNewCommand("GOOD");
		}
		else if(e.getSource() == this.SneakButton){
			addNewCommand("SNEAK");
		}
		else if(e.getSource() == this.InfButton){
			addNewCommand("INFLATION");
		}
		else if(e.getSource() == this.DefButton){
			addNewCommand("DEFLATION");
		}
		else if(e.getSource() == this.NSADButton){
			procedure.add(COMMAND_PART.RECEIVER);
			procedure.add(COMMAND_PART.DELAY_AMOUNT);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.NSADELAY);
		}
		else if(e.getSource() == this.BButton){
			procedure.add(COMMAND_PART.BUILD_OFFICE);
			procedure.add(COMMAND_PART.TRANSIT_TIME);
			procedure.add(COMMAND_PART.POSTAGE);
			procedure.add(COMMAND_PART.CAPACITY);
			procedure.add(COMMAND_PART.PERSUATION);
			procedure.add(COMMAND_PART.LENGTH);
			new NewCommandFrame(this,procedure,COMMAND_TYPE.BUILD);
		}
		else if(e.getSource() == this.saveButton){
			try {
				FileSaving.saveCommands(this.commands);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == this.deleteButton){
			try{
				if(null != this.commands.get(this.JCommandsList.getSelectedIndex())){
					this.commands.remove(this.JCommandsList.getSelectedIndex());
					this.JCommandsList.updateUI();
					this.pack();
				}
				else{
					throw new Exception("haha");
				}
			}
			catch(Exception e0){
				JOptionPane.showMessageDialog(this,"Select A Command to Delete First!");
			}
		}
		else if(e.getSource() == this.moveDownButton){
			int index = this.JCommandsList.getSelectedIndex();
			if(index<commands.size()-1){
				try{
					String cmd = this.commands.remove(index);
					this.commands.add(index+1, cmd);
					this.JCommandsList.setSelectedIndex(index+1);
					this.JCommandsList.updateUI();
					this.pack();
				}
				catch(Exception gg){
					System.out.println(gg.toString());
				}
			}
			else if(index==commands.size()-1){}
			else{
				JOptionPane.showMessageDialog(this,"Select A Command to Move Down First!");
			}
		}
		else if(e.getSource() == this.moveUpButton){
			int index = this.JCommandsList.getSelectedIndex();
			if(index>0){
				try{
					String cmd = this.commands.remove(index);
					this.commands.add(index-1, cmd);
					this.JCommandsList.updateUI();;
					this.JCommandsList.setSelectedIndex(index-1);
				}
				catch(Exception gg){}
			}
			else if(index ==0){
			}
			else{
				JOptionPane.showMessageDialog(this,"Select A Command to Move Up First!");
			}
		}
	}
	
	private JButton new_add_button(String message,boolean new_command){
		JButton button = new JButton(message);
		button.addActionListener(this);
		if(new_command)
			this.newCommandButtonsPanel.add(button);
		else
			this.commandEditButtonsPanel.add(button);
		return button;
	}

}
