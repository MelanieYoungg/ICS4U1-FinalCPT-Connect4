
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class APanel extends JPanel implements ActionListener{

    //Properties
    Timer thetimer = new Timer(1000/60, this);
    int intY1 = 250;
    int intY1Def = 0;
    int intY2 = 250;
    int intY2Def = 0;
    int intBallX = 395;
    int intBallY = 295;
    int intBallXDef = 0;
    int intBallYDef = 0;
    int intP1 = 0;
    int intP2 = 0;
    boolean start = false;
    String playerOne;
    String playerTwo;
    
    BufferedImage theBackGroundImg;
    
    Color colorBackground = Color.BLACK;
    
    //Methods
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == thetimer){
            this.repaint();
        }    
    }
    
    //Overide paintComponent
    public void paintComponent(Graphics g){
        
        g.setColor(colorBackground);
        g.fillRect(0,0,1280,720);
        
        
        g.drawImage(theBackGroundImg,0, 0,null);
        
        
        /*if (intBallX <= 0){
            intBallX = 395;
            intBallY = 295;
            intBallXDef = 0;
            intBallYDef = 0;
            intP2 = intP2 + 1;
            start=false;
        }else if (intBallX >= 795){
            intBallX = 395;
            intBallY = 295;
            intBallXDef = 0;
            intBallYDef = 0;
            intP1 = intP1 + 1;
            start=false;
        }else if (intBallY <= 0){
            intBallYDef = intBallYDef * -1;
        }else if (intBallY >= 590){
            intBallYDef = intBallYDef * -1;
        }else if (intBallX <= 10 && intBallY <= intY1 + 100 && intBallY >= intY1){
            intBallXDef = intBallXDef * -1;
        }else if (intBallX >= 780 && intBallY <= intY2 + 100 && intBallY >= intY2){
            intBallXDef = intBallXDef * -1;
        }
        
        if (intP2 >= 4 || intP1 >= 4){
            colorBackground = new Color(0, 0, 0);
        }else if (intP2 == 3 || intP1 == 3){
            colorBackground = new Color(50, 100, 0);
        }else if (intP2 == 2 || intP1 == 2){
            colorBackground = new Color(0, 100, 50);
        }else if (intP2 == 1 || intP1 == 1){
            colorBackground = new Color(100, 50, 100);
        }else{
            colorBackground = new Color(0, 0, 0);
        }
        /*if(intP1 == 5 && intP2 == 0 || intP1 == 0 && intP2 == 5){
            g.drawImage(GameEnd2, 0, 0, null);
        }else if(intP1 == 5 || intP2 == 5){
            g.drawImage(GameEnd1, 0, 0, null);
        }
        if(intP1 ==5 && intP2 <5) {// || intP1 > 0 && intP2 == 0){
            g.drawImage(GameEnd1, 0, 0, null);
            
        }else if(intP2 ==5 && intP1 <5){
            g.drawImage(GameEnd2, 0, 0, null);
            
        }*/
    }
    
    //Constructors
    
    public APanel(){
        super();
        this.setPreferredSize(new Dimension(1280,720));
        this.setLayout(null);
        thetimer.start();
        try{
        	theBackGroundImg = ImageIO.read(new File("Connect4BG.jpg"));
        }catch(IOException e){
            System.out.println("ERROR");
        }
        
        
    }
    
}
