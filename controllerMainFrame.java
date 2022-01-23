import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;


public class controllerMainFrame implements ActionListener,ChangeListener {
	//PROPERTIES
	JFrame theframe = new JFrame("Connect 4");
	
	//Screens
	C4GameplayScreen gameplaypanel = new C4GameplayScreen();
	C4HelpScreen helppanel = new C4HelpScreen();
	C4StartMenu startMenu = new C4StartMenu();
	C4WinnerLoserScreen winnerLoserScreen = new C4WinnerLoserScreen();
	C4ThemeSelectionScreen themeSelectionScreen = new C4ThemeSelectionScreen();

	//Networking Properties
	//boolean isServer;
	//SuperSocketMaster ssm;
	//temperary hardcode, may in configuration file.
	int port = 6112;
	
	//METHODS
	public void changeTheme(String strTheme){
		try{
			PrintWriter txtTheme = new PrintWriter(new FileWriter("themes.txt", false));

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
		if(evt.getSource() == startMenu.serverButton){
			startMenu.serverButton.setEnabled(false); 
			startMenu.clientButton.setVisible(false);
			gameplaypanel.isServer = true;
			startMenu.ipAddress.setEnabled(false);
			startMenu.userName.setEnabled(false);
			//startMenu.statusLabel.setText("Server connected");
			gameplaypanel.ssm = new SuperSocketMaster(port, this);
			gameplaypanel.prefix = "Server";
			gameplaypanel.intTurn = 1;
		    boolean gotConnect = gameplaypanel.ssm.connect();
		    if(gotConnect){
		    	System.out.println("got connection!");
		    	startMenu.statusLabel.setText("Server Start");
				
		     }else{
		    	startMenu.statusLabel.setText("Server Start failed");
		    }
		      
		 }else if(evt.getSource() == startMenu.clientButton) {
			 startMenu.clientButton.setEnabled(false); 
			 startMenu.serverButton.setVisible(false);
			 gameplaypanel.isServer = false;
			 startMenu.ipAddress.setEnabled(false);
			 startMenu.userName.setEnabled(false);
			 gameplaypanel.ssm = new SuperSocketMaster(startMenu.ipAddress.getText(), port, this);
			 gameplaypanel.prefix = "Client";
			 gameplaypanel.intTurn = 2;
			 boolean gotConnect = gameplaypanel.ssm.connect();
			 if(gotConnect){
			     startMenu.statusLabel.setText("Server connected.");
			 }else {
				 startMenu.statusLabel.setText("Server is unavilable.");
				 startMenu.clientButton.setEnabled(true);
				 startMenu.ipAddress.setEnabled(true);
			 }
			    
		 }else if(evt.getSource() == startMenu.playButton) {
			 if(startMenu.ipAddress.getText() != null && startMenu.userName.getText() != null 
					 && !startMenu.ipAddress.getText().isEmpty() && !startMenu.userName.getText().isEmpty() 
					 && (!startMenu.serverButton.isVisible() || !startMenu.clientButton.isVisible())) {
				 gameplaypanel.setPreferredSize(new Dimension(1280, 720));
				 theframe.setContentPane(gameplaypanel);
				 theframe.pack();
			 }
			    
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

		}//C4HelpScreen.java
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
		}else if(evt.getSource() == gameplaypanel.ssm) {
			
			String strMessage = "";
	    	String ssmText = gameplaypanel.ssm.readText();
	    	String[] textArray = ssmText.split(",");
	    	if(textArray.length>0) {
	    		System.out.println("ssm event from "+textArray[0]);
	    		if(textArray[0].equals("Server")) {
	    			System.out.println("!!! event from server");
	    			if(!gameplaypanel.isServer) {
	    				gameplaypanel.intTurn = 2;
	    			
	    				gameplaypanel.blnDroppedPiece = true;
	    				gameplaypanel.intColumnDropped = Integer.parseInt(textArray[1]);
	    			}
	    			//gameplaypanel.intTurn = 2;
	    			System.out.println("!!! event from server, intTurn is "+gameplaypanel.intTurn);
	    		}else if(textArray[0].equals("Client")) {
	    			
	    			if(gameplaypanel.isServer) {
	    				gameplaypanel.intTurn = 1;
	    				gameplaypanel.blnDroppedPiece = true;
	    				gameplaypanel.intColumnDropped = Integer.parseInt(textArray[1]);
	    			}
	    			//gameplaypanel.intTurn = 1;
	    		}else if (textArray[0].equals("win")){
						gameplaypanel.blnHasWon = true;
						System.out.println("Player "+textArray[1]+" has won!");
					
				}else if (textArray[0].equalsIgnoreCase("chat")){
					//puts username and chat message into the chat box
					gameplaypanel.chatarea.append(textArray[1]+" says: " + textArray[2]+"\n");
					gameplaypanel.chatarea.setCaretPosition(gameplaypanel.chatarea.getDocument().getLength());
				
				}
	    			//ConnectPiece newgamepiece = new ConnectPiece();
	    			gameplaypanel.newgamepiece.intColumn = gameplaypanel.intColumnDropped;
	    			//if(blnDroppedPiece) {
					gameplaypanel.arrayboard.addPosition(gameplaypanel.intColumnDropped);
					gameplaypanel.newgamepiece.intRow = gameplaypanel.arrayboard.intCurrentRow;
					for(int intRows = 5; intRows >= 0; intRows--){
						System.out.println("!!!! intRows are "+intRows);
						if(gameplaypanel.arrayboard.intBoard[intRows][gameplaypanel.intColumnDropped] != 0){
							gameplaypanel.newgamepiece.intRow = intRows;
						}
					}
				//}
	    	}	
		}
		if(gameplaypanel.blnHasWon == true){
			winnerLoserScreen.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(winnerLoserScreen);
			theframe.pack();
		}
	}
	public void stateChanged(ChangeEvent evt){}
	
	//constructor
	public controllerMainFrame(){
		theframe = new JFrame("Connect 4");
		startMenu.setPreferredSize(new Dimension(1280, 720));
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
		theframe.setContentPane(startMenu);
		theframe.pack();
	}
	
	//MAIN PROGRAM
	public static void main(String[] args){
		new controllerMainFrame();
	}
}
