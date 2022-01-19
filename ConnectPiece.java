import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ConnectPiece{
	//properties
	int intX;
	int intY;
	int intXpos;
	int intYpos;
	int intDefY = 0;
	String strPlayer1File;
	String strPlayer2File;
	String strTurn = "player1";
	
	BufferedImage player1piece = null;
	BufferedImage player2piece = null;
	
	//method
	
	public void drawIt(Graphics g){
		/*/if(strTurn.equalsIgnoreCase("player1")){
			System.out.println("File: " + strPlayer1File);
			g.drawImage(player1piece, intX-50, intY-50, null);
		}else if (strTurn.equalsIgnoreCase("player2")){
			g.drawImage(player2piece, intX-50, intY-50, null);
		}/*/
		g.setColor(Color.BLACK);
		g.fillOval(intX-50, intY-50, 100,100);
		
	}
	
	//constructor
	public ConnectPiece(){
		intXpos = intX;
		intYpos = intY;
		strPlayer1File = strPlayer1File;
		strPlayer2File = strPlayer2File;
		try{
			player1piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strPlayer1File));
			System.out.println(strPlayer1File);
			player2piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strPlayer2File));
		}catch(IOException e){
			System.out.println("Error loading game piece");
		}
	}

}
