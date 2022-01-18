import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class controllerMainFrame implements ActionListener,ChangeListener {
	//using this to swap out panels so we can see how they look, can be used later for the main/controller 
	//properties
	public JFrame theframe;
	C4GameplayScreen gameplaypanel = new C4GameplayScreen();
	C4HelpScreen helppanel = new C4HelpScreen();
	C4StartMenu startMenu = new C4StartMenu();
	String strCurrentScreen = "";
	boolean isServer = false;
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == startMenu.serverButton){
			startMenu.serverButton.setEnabled(false); 
			startMenu.clientButton.setVisible(false);
			isServer = true;
			startMenu.ipAddress.setEnabled(false);
			startMenu.userName.setEnabled(false);
		    //ssm = new SuperSocketMaster(ipAddress.getText(), 1234, this);
		    /*boolean gotConnect = ssm.connect();
		    if(gotConnect){
		        //discBut.setEnabled(true);
		        //texttosend.append("Server Address: "+ssm.getMyAddress()+"\n");
		        //texttosend.append("Server Hostname: "+ssm.getMyHostname()+"\n");
		    }else{
		       // serverBut.setEnabled(true); 
		        //clientBut.setEnabled(true); 
		        //ipaddress.setEnabled(true);
		        //port.setEnabled(true);
		    }*/
		      
		 }else if(evt.getSource() == startMenu.clientButton) {
			startMenu.clientButton.setEnabled(false); 
			startMenu.serverButton.setVisible(false);
			isServer = false;
			startMenu.ipAddress.setEnabled(false);
			startMenu.userName.setEnabled(false);
			    
		 }else if(evt.getSource() == startMenu.playButton) {
			gameplaypanel.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(gameplaypanel);
			theframe.pack();
			    
		 }else if(evt.getSource() == startMenu.helpButton) {
			helppanel.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(helppanel);
			theframe.pack();
			    
		 }
		
	}
	public void stateChanged(ChangeEvent evt){
		
	}
	
	
	//constructor
	public controllerMainFrame(){
		theframe = new JFrame("Connect 4");
		startMenu.setPreferredSize(new Dimension(1280, 720));
		startMenu.userName.addActionListener(this);
		startMenu.serverButton.addActionListener(this);
		startMenu.clientButton.addActionListener(this);
		startMenu.playButton.addActionListener(this);
		startMenu.helpButton.addActionListener(this);
		theframe.setContentPane(startMenu);
		theframe.pack();
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setResizable(false);

		
		
	}
	
	//main program
	public static void main(String[] args){
		new controllerMainFrame();
	}
}
