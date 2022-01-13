import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class C4GameplayScreen extends JPanel{
	//properties
	String strLine = "";
	String strGetTheme[];
	String strThemeElements[];
	String strThemeImages[];
	String strTheme;
	String strBackgroundFile = "";
	String strBoardFile = "";
	String strP1File= "";
	String strP2File= "";
	BufferedImage thebackground = null;
	BufferedImage theboard = null;
	BufferedImage player1piece = null;
	BufferedImage player2piece = null;
	
	JTextArea chatarea;
	JScrollPane chatscroll;
	JTextField typechat = new JTextField ("Type in chat...");
	
	//methods
	public void paintComponent(Graphics g){
		
		g.drawImage(thebackground, 0, 0, null);
		g.drawImage(theboard, 50,50, null);
		g.drawImage(player1piece, 920,480, null);
		g.drawImage(player2piece, 1050,480, null);
	}
	
	public String LoadTheme(){
		strLine = "";
		try{
			BufferedReader themefile = new BufferedReader(new FileReader("themes.txt"));
			while(strLine != null){
				strLine = themefile.readLine();
				if(strLine != null){
					strGetTheme = strLine.split(",");
					if(strGetTheme[0].equalsIgnoreCase("Current")){
						strTheme = strGetTheme[1];
					}
				}
			}
			themefile.close();
		}catch(IOException e){
			System.out.println("Error loading theme");
		}
		return strTheme;
	}
	
	public String[] LoadBG(){
		strLine = "";
		try{
			BufferedReader themefile = new BufferedReader(new FileReader("themes.txt"));
			while(strLine != null){
				strLine = themefile.readLine();
				if(strLine != null){
					strThemeElements = strLine.split(",");
					if(strThemeElements[0].equalsIgnoreCase(strTheme)){
						strThemeImages = strThemeElements;
					}
				}
			}
			themefile.close();
		}catch(IOException e){
			System.out.println("Error loading theme images");
		}
		return strThemeImages;
	}

	//constructor
	public C4GameplayScreen(){
		super();

		try{
			strTheme = this.LoadTheme();
			strThemeElements = this.LoadBG();
			strBackgroundFile = strThemeElements[1];
			strBoardFile = strThemeElements[2];
			strP1File = strThemeElements[3];
			strP2File = strThemeElements[4];
			thebackground = ImageIO.read(new File(strBackgroundFile));
			theboard = ImageIO.read(new File(strBoardFile));
			player1piece = ImageIO.read(new File(strP1File));
			player2piece = ImageIO.read(new File(strP2File));
			
		}catch(IOException e){
			System.out.println("Error loading image");
		}
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		
		chatarea = new JTextArea();
		chatarea.setEditable(false);
		chatscroll = new JScrollPane(chatarea);
		chatscroll.setSize(380,350);
		chatscroll.setLocation(850,50);
		chatscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(chatscroll);
		
		typechat.setSize(new Dimension(380,50));
		typechat.setLocation(850,400);
		this.add(typechat);
	}

}
