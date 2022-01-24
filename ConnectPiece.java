import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ConnectPiece{
	//properties
	int intX;
	int intY = 0;
	int intDefY = 20;
	String strPlayer1File;
	String strPlayer2File;
	int intTurn;
	int intColumn;
	int intRow = 5; 
	int intColumnCoords = intColumn*100+50;
	int intRowCoords = intRow*100+50;
	int intB;
	boolean blnStay = false;

	BufferedImage player1piece = null;
	BufferedImage player2piece = null;
	
	//method
	public void nextLoc(){
		System.out.println("!!!! intDefY ="+intDefY);
		intRowCoords = intRow*100+50;
		if(intRowCoords >= intY){
			intY = intY+intDefY;
		}else{
			intDefY = 0;
		}
		
	}
	public void drawOnBoard(Graphics g){
		/*/if(strTurn.equalsIgnoreCase("player1")){
			System.out.println("File: " + strPlayer1File);
			g.drawImage(player1piece, intX-50, intY-50, null);
		}else if (strTurn.equalsIgnoreCase("player2")){
			g.drawImage(player2piece, intX-50, intY-50, null);
		}/*/
		System.out.println(intColumn);
		blnStay = true;
		g.setColor((new Color(40, 70, intB)));
		g.fillOval(intX, intY, 100,100);
		
	}
	public void drawIt(Graphics g){
		/*/if(intTurn == 1){
			System.out.println("OOOOO File: " + strPlayer1File);
			g.drawImage(player1piece, intX-50, intY-50, null);
		}else if (intTurn == 2){
			g.drawImage(player2piece, intX-50, intY-50, null);
		}/*/
		g.setColor(Color.BLACK);
		g.fillOval(intX-50, intY-50, 100,100);
		
	}
	public void dropAnimation(Graphics g){
		/*/if(strTurn.equalsIgnoreCase("player1")){
			System.out.println("File: " + strPlayer1File);
			g.drawImage(player1piece, intX-50, intY-50, null);
		}else if (strTurn.equalsIgnoreCase("player2")){
			g.drawImage(player2piece, intX-50, intY-50, null);
		}/*/
		
		System.out.println(intColumn);
		System.out.println("Row: " + intRow);
		if(intRow>0) {
			intColumnCoords = intColumn*100+50;
			g.setColor((new Color(40, 70, intB)));
			System.out.println("!!!!!!!! intY is "+intY);
			g.fillOval(intColumnCoords, intY, 100,100);
			this.nextLoc();
		}else {
			intDefY = 20;
		}
	}
	public void dropAnimationHelp(Graphics g){
		System.out.println(intColumn);
		System.out.println("Row: " + intRow);
		intColumnCoords = (intColumn*100)+800;
		g.setColor(Color.RED);
		g.fillOval(intColumnCoords, intY, 100,100);
		this.nextLoc();
	}
	public void resetAnimation(){
		intX = 0;
		intY = 0;
		intDefY = 5;
		intColumn = 0;
		intColumnCoords = intColumn+50;
	
	}
	
	//constructor
	public ConnectPiece(){
		try{
			player1piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strPlayer1File));
			System.out.println(strPlayer1File);
			player2piece = ImageIO.read(new File(System.getProperty("user.dir") + "\\Pictures\\" + strPlayer2File));
		}catch(IOException e){
			System.out.println("Error loading game piece");
		}
	}

}
