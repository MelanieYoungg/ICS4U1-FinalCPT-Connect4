import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;


public class controllerMainFrame implements ActionListener,ChangeListener {
	//PROPERTIES
	JFrame theframe = new JFrame("Connect 4");
	
	//Screens
	//using this to swap out panels so we can see how they look, can be used later for the main/controller 
	C4GameplayScreen gameplaypanel = new C4GameplayScreen();
	C4HelpScreen helppanel = new C4HelpScreen();
	C4StartMenu startMenu = new C4StartMenu();
	C4WinnerLoserScreen winnerLoserScreen = new C4WinnerLoserScreen();
	C4ThemeSelectionScreen themeSelectionScreen = new C4ThemeSelectionScreen();

	//Networking Properties
	boolean isServer;
	SuperSocketMaster ssm;
	
	//METHODS
	public void changeTheme(String strTheme){
		try{
			PrintWriter txtTheme = new PrintWriter(new FileWriter("theme.txt", true));

			String strFileData = "Original,OriginalBG.jpg,OriginalBoard.png,P1original.png,P2original.png\nChristmas,ChristmasBG.jpg,ChristmasBoard.png,P1christmas.png,P2christmas.png\nEaster,EasterBG.jpg,EasterBoard.png,P1easter.png,P2easter.png\n";
			if(strTheme.equals("Christmas")){
				txtTheme.println(strFileData + "Current,christmas");
			}else if(strTheme.equals("Original")){
				txtTheme.println(strFileData + "Current,original");
			}else if(strTheme.equals("Easter")){
				txtTheme.println(strFileData + "Current,easter");
			}
			txtTheme.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	public void actionPerformed(ActionEvent evt){
		//C4StartMenu.java
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

		//Menu Items
		else if(evt.getSource() == startMenu.menuItemChristmas){


		}else if(evt.getSource() == startMenu.menuItemOriginal){

		}else if(evt.getSource() == startMenu.menuItemEaster){

		}else if(evt.getSource() == startMenu.menuItemTheme){

		}

		//C4HelpScreen.java
		else if(evt.getSource() == helppanel.backbutton) {
			startMenu.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(startMenu);
			theframe.pack();
		 }
		 
		//C4WinnerLoserScreen.java
		else if(evt.getSource() == winnerLoserScreen.playAgainButton){
			gameplaypanel.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(gameplaypanel);
			theframe.pack();
		}else if(evt.getSource() == winnerLoserScreen.disconnectButton){
			//ssm.disconnect();
			startMenu.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(startMenu);
			theframe.pack();
		}

		//C4ThemeSelectionScreen.java
		else if(evt.getSource() == themeSelectionScreen.christmasButton){
			changeTheme("Christmas");
		}else if(evt.getSource() == themeSelectionScreen.originalButton){
			changeTheme("Original");
		}else if(evt.getSource() == themeSelectionScreen.easterButton){
			changeTheme("Easter");
		}else if(evt.getSource() == themeSelectionScreen.backButton){
			startMenu.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(startMenu);
			theframe.pack();
		}
	}
	public void stateChanged(ChangeEvent evt){}
	
	//CONSTRUCTOR
	public controllerMainFrame(){
		startMenu.setPreferredSize(new Dimension(1280, 720));
		
		//Action Listener for Start Menu
		startMenu.userName.addActionListener(this);
		startMenu.serverButton.addActionListener(this);
		startMenu.clientButton.addActionListener(this);
		startMenu.playButton.addActionListener(this);
		startMenu.helpButton.addActionListener(this);
		
		//Action Listener for Winner Loser Screen
		winnerLoserScreen.playAgainButton.addActionListener(this);
		winnerLoserScreen.disconnectButton.addActionListener(this);
		
		//Action Listener for Theme Selection
		themeSelectionScreen.christmasButton.addActionListener(this);
		themeSelectionScreen.originalButton.addActionListener(this);
		themeSelectionScreen.easterButton.addActionListener(this);		
		
		//Boiler Plate Code
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setResizable(false);
		
		//Used for Swapping Panels
		theframe.setContentPane(themeSelectionScreen);
		theframe.pack();
	}
	
	//MAIN PROGRAM
	public static void main(String[] args){
		new controllerMainFrame();
	}
}
