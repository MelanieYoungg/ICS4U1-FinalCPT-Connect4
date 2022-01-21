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
	boolean blnDroppedPiece = false;
	boolean blnPlayedPiece = false;
	boolean blnInRange = false;
	ConnectPiece newgamepiece = new ConnectPiece();
	moduleBackendBoard arrayboard = new moduleBackendBoard();
	int intColumnDropped;
	int intRowDropped = 5;
	int intPlayedColumnCoords;
	int intPlayedRowCoords;
	int intMouseX;
	int intMouseY;
	int intTurn = 1;
	
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
		//drawing themed images
		g.drawImage(thebackground, 0, 0, null);
		g.drawImage(theboard, 50,50, null);
		g.drawImage(player1piece, 920,480, null);
		g.drawImage(player2piece, 1050,480, null);
		
		//drawing the game piece when grabbed
		if(blnHoldingPiece == true){
			newgamepiece.drawIt(g);
		}
		//drawing the animation
		if(blnDroppedPiece == true){
			System.out.println("!!!!!!!!!!!!! blnDroppedPiece is true");
			newgamepiece.intColumn = intColumnDropped;
			System.out.println("!!!!!!!!!!!!! intRowCoords is "+newgamepiece.intRowCoords);
			System.out.println("!!!!!!!!!!!!! intY is "+newgamepiece.intY);
			if(newgamepiece.intY < newgamepiece.intRowCoords){
				newgamepiece.dropAnimation(g);
			}else{
				blnDroppedPiece = false;
				newgamepiece.blnStay = true;
				blnInRange = false;
				blnPlayedPiece = true;
			}
			
		}
		//drawing the game piece in the board
		for(int intRow = 0; intRow < 6; intRow++){
				for(int intCol = 0; intCol < 7; intCol++){
					if(arrayboard.intBoard[intRow][intCol] == 1){
						g.drawImage(player1piece, intCol*100+50,intRow*100+50, null);
					}else if(arrayboard.intBoard[intRow][intCol] == 2){
						g.drawImage(player2piece, intCol*100+50,intRow*100+50, null);
					}
				}
			
		}
		
		
		/*/drawing the game piece in the board
		if(newgamepiece.blnStay == true && blnDroppedPiece == false && blnHoldingPiece == false){
			if(strTurn.equalsIgnoreCase("player1")){
				//ConnectPiece playedpiece = new ConnectPiece();
				//newgamepiece.intB = 100;
				newgamepiece.intX = intColumnDropped*100+50;
				newgamepiece.intY = intRowDropped*100+50;
				newgamepiece.drawOnBoard(g);
				//strTurn = "player2";
				//System.out.println(strTurn);
			}else if (strTurn.equalsIgnoreCase("player2")){
				//ConnectPiece playedpiece = new ConnectPiece();
				//newgamepiece.intB = 20;
				newgamepiece.intX = intColumnDropped*100+50;
				newgamepiece.intY = intRowDropped*100+50;
				newgamepiece.drawOnBoard(g);
				//strTurn = "player1";
				//System.out.println(strTurn);
			}
			//need to get the drawn piece to stay in the right spot. As of right now the previous game pieces also move to the current column drop.
			//it cant be in an IF STATEMENT because it will just get overridden
			//do i need to make all of the bln variables declared in the Connect piece object? (except blnPlayedPiece bc that determines the turn system) 
		}/*/
		
		//turn system
		if (blnPlayedPiece == true){
			if(intTurn == 1){
				newgamepiece.intB = 100;
				intTurn = 2;
				System.out.println(intTurn);
				blnPlayedPiece = false;
			}else if (intTurn == 2){
				newgamepiece.intB = 20;
				intTurn = 1;
				System.out.println(intTurn);
				blnPlayedPiece = false;
			}
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
		System.out.println("!!!! mouse dragged start");
		//dragging the gamepiece to the board
		if(blnHoldingPiece == true && blnDroppedPiece == false){
			intMouseX = evt.getX();
			intMouseY = evt.getY();
			//gamepiece will follow the mouse
			newgamepiece.intX = intMouseX;
			newgamepiece.intY = intMouseY;
		}
		
	}
	public void mouseExited(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseReleased(MouseEvent evt){
		System.out.println("!!!!! mouse released");
		if(blnHoldingPiece) {
		blnHoldingPiece = false;
		intMouseX = evt.getX();
		intMouseY = evt.getY();
		//if(blnInRange) {
		//checking which column the piece is dropped in
		if(intMouseX >= 50 && intMouseX < 150){
			intColumnDropped = 0;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 150 && intMouseX < 250){
			intColumnDropped = 1;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 250 && intMouseX < 350){
			intColumnDropped = 2;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 350 && intMouseX < 450){
			intColumnDropped = 3;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 450 && intMouseX < 550){
			intColumnDropped = 4;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 550 && intMouseX < 650){
			intColumnDropped = 5;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
			
		}else if(intMouseX >= 650 && intMouseX <= 750){
			intColumnDropped = 6;
			ConnectPiece newgamepiece = new ConnectPiece();
			newgamepiece.intColumn = intColumnDropped;
			blnDroppedPiece = true;
			System.out.println("Column dropped: "+intColumnDropped);
		}
		//adding to the board array
		if(blnDroppedPiece) {
			arrayboard.addPosition(intColumnDropped);
			newgamepiece.intRow = arrayboard.intCurrentRow;
			for(int intRows = 6; intRows >= 0; intRows--){
				System.out.println("!!!! intRows are "+intRows);
					if(arrayboard.intBoard[intColumnDropped][intRows] != 0){
						newgamepiece.intRow = intRows;
				}
			}
		}
		}
		System.out.println("!!!! mouse released method end");
	}
	public void mousePressed(MouseEvent evt){
		System.out.println("!!!! mouse pressed start");
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
		System.out.println("!!!! mouse pressed end");	
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
