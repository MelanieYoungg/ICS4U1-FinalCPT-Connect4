import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class C4ThemeSelectionScreen extends JPanel{
	//PROPERTIES
	//File Names
	String strBackgroundFile = "Connect4BG.jpg";
	String strChristmasTheme = "christmasTheme.png";
	String strOriginalTheme = "originalTheme.png";
	String strEasterTheme = "easterTheme.png";
	
	//Background
	BufferedImage imgBackground;
	
	//UI Elements and Images
	JButton christmasButton = new JButton("Christmas");
	JButton originalButton = new JButton("Original");
	JButton easterButton = new JButton("Easter");
	
	BufferedImage imgChristmas;
	BufferedImage imgOriginal;
	BufferedImage imgEaster;
	
	JButton backToMenuButton = new JButton("Back to Menu");
	
	//METHODS
	public void paintComponent(Graphics g){
		g.drawImage(imgBackground, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){}

	//CONSTRUCTOR
	public C4ThemeSelectionScreen(){
		super();
		try{
			imgBackground = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strBackgroundFile));
			imgChristmas = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strChristmasTheme));
			imgOriginal = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strOriginalTheme));
			imgEaster = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strEasterTheme));
		}catch(IOException e){
			System.out.println("Error loading image");
		}
		
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		
	}
}
