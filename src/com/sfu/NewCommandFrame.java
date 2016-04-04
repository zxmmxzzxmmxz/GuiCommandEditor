package com.sfu;
import javax.swing.*;

import com.sfu.mainFrame.COMMAND_PART;
import com.sfu.mainFrame.COMMAND_TYPE;

import java.awt.event.*;
import java.util.Vector;

public class NewCommandFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected mainFrame mFrame;
	Vector<COMMAND_PART> procedure;
	String newCommand;
	COMMAND_TYPE cmdType;
	NewCommandFrame(mainFrame m,Vector<COMMAND_PART> p,COMMAND_TYPE commandType){
		this.cmdType = commandType;
		switch (commandType){
		case NEWLETTER:{
			this.newCommand="LETTER ";
			break;
		}
		case NEWPACKAGE:{
			this.newCommand = "PACKAGE ";
			break;
		}
		case PICKUP:{
			this.newCommand = "PICKUP ";
			break;
		}
		case BUILD:{
			this.newCommand = "BUILD ";
			break;
		}
		case NSADELAY:{
			this.newCommand = "NSADELAY ";
			break;
		}
		case SCIENCE:{
			this.newCommand = "SCIENCE ";
		}
		}
		this.mFrame = m;
		this.procedure = p;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init_start();
	}
	
	private int panelIndex = 0;
	private JPanel currentPanel;
	private void init_start(){
		panelIndex++;
		if(procedure.size()>0 && procedure.get(0) == COMMAND_PART.INIT_OFFICE){
			currentPanel = new OfficeSelectPanel("Please Select Initial Office",this);
		}
		else if(procedure.size()>0 && procedure.get(0) == COMMAND_PART.BUILD_OFFICE){
			currentPanel = new TextEnteringPanel("Please Enter the Office Name to Build",this, true);
		}
		else if(procedure.size()>0 && cmdType == COMMAND_TYPE.NSADELAY){
			this.currentPanel = new TextEnteringPanel("Please Enter the Receiver's Name to Delay",this,true);
		}
		else if(procedure.size()>0 && cmdType == COMMAND_TYPE.SCIENCE){
			this.currentPanel = new TextEnteringPanel("Please Enter the Days to do Science",this,false);
		}
		this.add(this.currentPanel);
		this.pack();
		this.setVisible(true);
	}
	
	public void next(){
		this.remove(this.currentPanel);
		this.currentPanel = null;
		if(panelIndex >= procedure.size()){
			this.mFrame.addNewCommand(this.newCommand);
			this.dispose();
		}
		else{
			COMMAND_PART cmd = procedure.get(this.panelIndex);
			if(cmd == COMMAND_PART.RECEIVER){
				this.currentPanel = new TextEnteringPanel("Please Enter Receiver's Name",this,true);
			}
			else if(cmd == COMMAND_PART.SENDER){
				this.currentPanel = new TextEnteringPanel("Please Enter Sender's Name",this,true);
			}
			else if(cmd == COMMAND_PART.DEST_OFFICE){
				this.currentPanel = new OfficeSelectPanel("Please Select Destination Office",this);
			}
			else if(cmd == COMMAND_PART.POSTAGE){
				this.currentPanel = new TextEnteringPanel("Please Enter the Postage",this,false);
			}
			else if(cmd == COMMAND_PART.LENGTH){
				this.currentPanel = new TextEnteringPanel("Please Enter the Length",this,false);
			}
			else if(cmd == COMMAND_PART.CAPACITY){
				this.currentPanel = new TextEnteringPanel("Please Enter the Capacity",this,false);
			}
			else if(cmd == COMMAND_PART.PERSUATION){
				this.currentPanel = new TextEnteringPanel("Please Enter the Persuation Amount",this,false);
			}
			else if(cmd == COMMAND_PART.TRANSIT_TIME){
				this.currentPanel = new TextEnteringPanel("Please Enter the Transit Time",this,false);
			}
			else if(cmd == COMMAND_PART.DELAY_AMOUNT){
				this.currentPanel = new TextEnteringPanel("Please Enter the Delay Amount",this,false);
			}
			if(this.currentPanel != null){
				this.add(this.currentPanel);
				this.pack();
				this.panelIndex++;
			}
			else{
				this.mFrame.addNewCommand(this.newCommand);
				this.dispose();
			}
		}
	}

	public void addPartToCommand(String part){
		this.newCommand += part;
		this.newCommand += " ";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	
}
