import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class C4GameplayScreen extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
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
	Timer thetimer = new Timer(1000/60, this);
	boolean blnHoldingPiece = false;
	ConnectPiece newgamepiece = new ConnectPiece();
	int intColumnDropped;
	int intMouseX;
	int intMouseY;
	
	JTextArea chatarea;
	JScrollPane chatscroll;
	JTextField typechat = new JTextField ("Type in chat...");
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
		
	public void paintComponent(Graphics g){
		
		g.drawImage(thebackground, 0, 0, null);
		g.drawImage(theboard, 50,50, null);
		g.drawImage(player1piece, 920,480, null);
		g.drawImage(player2piece, 1050,480, null);
		
		//drawing the game piece when grabbed
		if(blnHoldingPiece == true){
			newgamepiece.drawIt(g);
		}
	}
	//loading theme
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
	//loading files into array based on theme
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
	
	public void mouseMoved(MouseEvent evt){
	}
	public void mouseDragged(MouseEvent evt){
		intMouseX = evt.getX();
		intMouseY = evt.getY();
		newgamepiece.intX = intMouseX;
		newgamepiece.intY = intMouseY;
		
	}
	public void mouseExited(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseReleased(MouseEvent evt){
		blnHoldingPiece = false;
		if(intMouseX >= 50 && intMouseX <= 150){
			intColumnDropped = 0;
			System.out.println("Column dropped: "+intColumnDropped);
		}
	}
	public void mousePressed(MouseEvent evt){
		//getting mouse position
		int intMouseX = evt.getX();
		int intMouseY = evt.getY();
		//checking if player grabs the pieces
		if(SwingUtilities.isLeftMouseButton(evt)&& intMouseX>=920 && intMouseX<=1150 && intMouseY >= 480 && intMouseY <= 580){
			//gamepiece will follow the mouse
			newgamepiece.intX = intMouseX;
			newgamepiece.intY = intMouseY;
			//generate new game piece to put down
			ConnectPiece newgamepiece = new ConnectPiece();
			this.blnHoldingPiece = true;
			System.out.println("pressed within range of pieces");
		}
			
	}
	public void mouseClicked(MouseEvent evt){
	}

	//constructor
	public C4GameplayScreen(){
		super();
		thetimer.start();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//loading pictures	
		try{
			strTheme = this.LoadTheme();
			strThemeElements = this.LoadBG();
			strBackgroundFile = strThemeElements[1];
			strBoardFile = strThemeElements[2];
			strP1File = strThemeElements[3];
			strP2File = strThemeElements[4];
			newgamepiece.strPlayer1File = strP1File;
			newgamepiece.strPlayer2File = strP2File;
			
			thebackground = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strBackgroundFile));
			theboard = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strBoardFile));
			player1piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strP1File));
			player2piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strP2File));
			
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
