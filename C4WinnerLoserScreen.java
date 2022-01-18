import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class C4WinnerLoserScreen extends JPanel implements ActionListener{
	//PROPERTIES
	String strBackgroundFile = "Connect4BG.jpg";
	BufferedImage thebackground;
	
	JTextArea winnerTextArea = new JTextArea("\n             Client Wins!");
	JButton playAgainButton = new JButton("Play Again!");
	JButton disconnectButton = new JButton("Disconnect");
	
	//METHODS
	public void paintComponent(Graphics g){
		g.drawImage(thebackground, 0, 0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == playAgainButton){
			
		}else if(evt.getSource() == disconnectButton){
			
		}
	}

	//CONSTRUCTOR
	public C4WinnerLoserScreen(){
		super();
		try{
			thebackground = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strBackgroundFile));
		}catch(IOException e){
			System.out.println("Error loading image");
		}
		
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		
		winnerTextArea.setFont(new Font("Serif", Font.PLAIN, 60));
		winnerTextArea.setSize(680, 210);
		winnerTextArea.setLocation(300, 100);
		winnerTextArea.setEnabled(false);
		this.add(winnerTextArea);
		
		playAgainButton.setFont(new Font("Serif", Font.PLAIN, 30));
		playAgainButton.setSize(680, 70);
		playAgainButton.setLocation(300, 350);
		this.add(playAgainButton);
		
		disconnectButton.setFont(new Font("Serif", Font.PLAIN, 30));
		disconnectButton.setSize(680, 70);
		disconnectButton.setLocation(300, 450);
		this.add(disconnectButton);
	}
}
