
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;


public class C4StartMenu extends JPanel {//implements KeyListener, ActionListener{
	//variables
		//JFrame theFrame;
		JPanel thePanel;
		SuperSocketMaster ssm;
		JLabel welcomeLabel;
		JTextField ipAddress;
		JLabel ipAddressLabel;
		JTextField userName;
		JLabel userNameLabel;
		JLabel statusLabel;;
		JButton serverButton;
		JButton clientButton;
		JButton playButton;
		JButton helpButton;
		JButton themeButton;
		JLabel textrecievedlabel;
		BufferedImage theBackGroundImg;
		//JFrame theframe1 = new JFrame("C4StartMenu ");
		int intnextpos1 = 0;
		int intnextpos2 = 0;
		Random random = new Random();
		
		public void paintComponent(Graphics g){
			
			g.drawImage(theBackGroundImg, 0, 0, null);
			
		}
		
		public void actionPerformed(ActionEvent evt){
			/*if(evt.getSource() == Username){
				if(ssm != null){
					thePanel.Player1 = Username.getText();
					Username.setEnabled(false); 
					ssm.sendText(Username.getText());  
					textrecieved.setEnabled(false);      
				}
			}else if(evt.getSource() == ssm){
				textrecieved.append(ssm.readText() + "\n");
				String What = ssm.readText();	 
				if (What.equals("Down")){
					intnextpos2 = thepanel.intY2 + 5;
					if (intnextpos2 < 505){
						thepanel.intY2Def = 5;
					}else if (intnextpos2 >= 505){
						thepanel.intY2Def = 0;
					}
				}else if(What.equals("Up")){
					intnextpos2 = thepanel.intY2 - 5;
					if (intnextpos2 > -5){
						thepanel.intY2Def = -5;
					}else if (intnextpos2 <= -5){
						thepanel.intY2Def = 0;
					}
				}else if(What.equals("Stop")){
					thepanel.intY2Def = 0;
				}else{
					thepanel.Player2 = What;
				}
			}*/
		}
		public void keyReleased(KeyEvent evt){
			/*if(evt.getKeyChar() == 'w'){
				thepanel.intY1Def = 0;
			}else if(evt.getKeyChar() == 's'){
				thepanel.intY1Def = 0;
			}*/		
		}
	//Moving paddles
		public void keyPressed(KeyEvent evt){
			/*if(evt.getKeyChar() == 'w'){
				intnextpos1 = thepanel.intY1 - 5;
				if (intnextpos1 > -5){
					thepanel.intY1Def = -5;
				}else if (intnextpos1 <= -5){
					thepanel.intY1Def = 0;
				}
			}else if(evt.getKeyChar() == 's'){
				intnextpos1 = thepanel.intY1 + 5;
				if (intnextpos1 < 505){
					thepanel.intY1Def = 5;
				}else if (intnextpos1 >= 505){
					thepanel.intY1Def = 0;
				}
			}else if(evt.getKeyCode() == 32){
				int posneg = random.nextInt(2);
				if (posneg == 0){
					thepanel.intBallXDef = 5;
				}else if (posneg == 1){
					thepanel.intBallXDef = -5;
				}
				thepanel.intBallYDef = random.nextInt(11)-5;
			}*/
		}
		public void keyTyped(KeyEvent evt){
		}
	//Constructor
		public C4StartMenu (){
			super();
			try{
				theBackGroundImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\Connect4BG.jpg"));
			}catch(IOException e){
				System.out.println("Error loading image");
			}
			this.setPreferredSize(new Dimension(1280,720));
			this.setLayout(null);
			
			
			welcomeLabel = new JLabel("Welcome to Connect 4!");
			welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
			welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			welcomeLabel.setSize(680, 70);
			welcomeLabel.setLocation(300, 50);
			welcomeLabel.setBackground(Color.WHITE);
			this.add(welcomeLabel);
			userNameLabel = new JLabel("Enter Your Username:");
			userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			userNameLabel.setSize(680, 35);
			userNameLabel.setLocation(300, 150);
			userNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			this.add(userNameLabel);
			userName = new JTextField();
			userName.setSize(680,35);
			userName.setLocation(300, 190);
			//userName.addActionListener(this);
			this.add(userName);
			serverButton = new JButton("Server");
			serverButton.setFont(new Font("Serif", Font.PLAIN, 20));
			serverButton.setHorizontalAlignment(SwingConstants.CENTER);
			serverButton.setSize(300, 40);
			serverButton.setLocation(300, 240);
			this.add(serverButton);
			clientButton = new JButton("Client");
			clientButton.setFont(new Font("Serif", Font.PLAIN, 20));
			clientButton.setHorizontalAlignment(SwingConstants.CENTER);
			clientButton.setSize(300, 40);
			clientButton.setLocation(680, 240);
			this.add(clientButton);
			ipAddressLabel = new JLabel("Enter IP Address to Play:");
			ipAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ipAddressLabel.setSize(680, 35);
			ipAddressLabel.setLocation(300, 300);
			ipAddressLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			this.add(ipAddressLabel);
			ipAddress = new JTextField("localhost");
			ipAddress = new JTextField("localhost");
			ipAddress.setSize(680,35);
			ipAddress.setLocation(300, 350);
			this.add(ipAddress);
			statusLabel = new JLabel();
			statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			statusLabel.setSize(680, 35);
			statusLabel.setLocation(300,385);
			statusLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			this.add(statusLabel);
			playButton = new JButton("Play");
			playButton.setFont(new Font("Serif", Font.PLAIN, 20));
			playButton.setHorizontalAlignment(SwingConstants.CENTER);
			playButton.setSize(300, 40);
			playButton.setLocation(300, 430);
			this.add(playButton);
			helpButton = new JButton("Help");
			helpButton.setFont(new Font("Serif", Font.PLAIN, 20));
			helpButton.setHorizontalAlignment(SwingConstants.CENTER);
			helpButton.setSize(300, 40);
			helpButton.setLocation(680, 430);
			this.add(helpButton);
			themeButton = new JButton("Themes Menu");
			themeButton.setHorizontalAlignment(SwingConstants.CENTER);
			themeButton.setSize(1280, 40);
			themeButton.setLocation(0, 680);
			themeButton.setFont(new Font("Serif", Font.PLAIN, 20));
			this.add(themeButton);
			/*textrecieved = new JTextArea();
			theScroll = new JScrollPane(textrecieved);
			theScroll.setSize(300,325);
			theScroll.setLocation(0, 125);
			textrecieved.setEnabled(false);
			thePanel.add(theScroll);  */
			//theFrame.setVisible(true);
		}
}
