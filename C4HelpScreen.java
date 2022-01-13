import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class C4HelpScreen extends JPanel{
	//properties
	BufferedImage helpbackground = null;
	BufferedImage helpboard = null;
	BufferedImage helpgamepiece = null;
	
	JTextArea helptextarea;
	
	//methods
	public void paintComponent(Graphics g){
		g.drawImage(helpbackground, 0, 0, null);
		g.setColor(Color.WHITE);
		g.fillRect(750,50,480,600);
		g.drawImage(helpboard, 800, 50, null);
		g.drawImage(helpgamepiece, 1060, 300, null);

	}

	//constructor
	public C4HelpScreen(){
		super();

		try{
			helpbackground = ImageIO.read(new File("Connect4BG.jpg"));
			helpboard = ImageIO.read(new File("helpboard.png"));
			helpgamepiece = ImageIO.read(new File("P1original.png"));
			
		}catch(IOException e){
			System.out.println("Error loading image");
		}
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		
		helptextarea = new JTextArea();
		helptextarea.setText("\n  How to Play \n ");
		helptextarea.setEditable(false);
		helptextarea.setSize(620,620);
		helptextarea.setLocation(50,50);
		this.add(helptextarea);
		
	}

}
