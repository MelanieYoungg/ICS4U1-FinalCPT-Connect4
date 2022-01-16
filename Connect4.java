
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Connect4 implements KeyListener, ActionListener{
	//variables
		JFrame theFrame;
		JPanel thePanel;
		APanel thePanel1;
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
		//JFrame theframe1 = new JFrame("Connect4");
		int intnextpos1 = 0;
		int intnextpos2 = 0;
		Random random = new Random();
		
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
		public Connect4(){
			ssm = new SuperSocketMaster(6112, this);
			ssm.connect();
			theFrame = new JFrame("Connect 4");
			theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			thePanel = new APanel();
			thePanel.setLayout(null);
			//thePanel.setPreferredSize(new Dimension(1280, 750));
			theFrame.setContentPane(thePanel);
			theFrame.setResizable(false);
			theFrame.pack();
			
			
			welcomeLabel = new JLabel("Welcome to Connect 4!");
			welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 30));
			welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			welcomeLabel.setSize(680, 70);
			welcomeLabel.setLocation(300, 50);
			welcomeLabel.setBackground(Color.WHITE);
			thePanel.add(welcomeLabel);
			userNameLabel = new JLabel("Enter Your Username:");
			userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			userNameLabel.setSize(680, 35);
			userNameLabel.setLocation(300, 150);
			userNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			thePanel.add(userNameLabel);
			userName = new JTextField();
			userName.setSize(680,35);
			userName.setLocation(300, 190);
			userName.addActionListener(this);
			thePanel.add(userName);
			serverButton = new JButton("Server");
			serverButton.setFont(new Font("Serif", Font.PLAIN, 20));
			serverButton.setHorizontalAlignment(SwingConstants.CENTER);
			serverButton.setSize(300, 40);
			serverButton.setLocation(300, 240);
			thePanel.add(serverButton);
			clientButton = new JButton("Client");
			clientButton.setFont(new Font("Serif", Font.PLAIN, 20));
			clientButton.setHorizontalAlignment(SwingConstants.CENTER);
			clientButton.setSize(300, 40);
			clientButton.setLocation(680, 240);
			thePanel.add(clientButton);
			ipAddressLabel = new JLabel("Enter IP Address to Play:");
			ipAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
			ipAddressLabel.setSize(680, 35);
			ipAddressLabel.setLocation(300, 300);
			ipAddressLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			thePanel.add(ipAddressLabel);
			ipAddress = new JTextField("localhost");
			ipAddress = new JTextField("localhost");
			ipAddress.setSize(680,35);
			ipAddress.setLocation(300, 350);
			thePanel.add(ipAddress);
			statusLabel = new JLabel();
			statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			statusLabel.setSize(680, 35);
			statusLabel.setLocation(300,385);
			ipAddressLabel.setFont(new Font("Serif", Font.PLAIN, 20));
			thePanel.add(statusLabel);
			playButton = new JButton("Play");
			playButton.setFont(new Font("Serif", Font.PLAIN, 20));
			playButton.setHorizontalAlignment(SwingConstants.CENTER);
			playButton.setSize(300, 40);
			playButton.setLocation(300, 430);
			thePanel.add(playButton);
			helpButton = new JButton("Help");
			helpButton.setFont(new Font("Serif", Font.PLAIN, 20));
			helpButton.setHorizontalAlignment(SwingConstants.CENTER);
			helpButton.setSize(300, 40);
			helpButton.setLocation(680, 430);
			thePanel.add(helpButton);
			themeButton = new JButton("Themes Menu");
			themeButton.setHorizontalAlignment(SwingConstants.CENTER);
			themeButton.setSize(1280, 40);
			themeButton.setLocation(0, 680);
			themeButton.setFont(new Font("Serif", Font.PLAIN, 20));
			thePanel.add(themeButton);
			/*textrecieved = new JTextArea();
			theScroll = new JScrollPane(textrecieved);
			theScroll.setSize(300,325);
			theScroll.setLocation(0, 125);
			textrecieved.setEnabled(false);
			thePanel.add(theScroll);  */
			theFrame.setVisible(true);
		}
	//main program
		public static void main(String[] args){
			Connect4 nt = new Connect4(); 
		}

}
