import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class controllerMainFrame implements ActionListener,ChangeListener {
	//using this to swap out panels so we can see how they look, can be used later for the main/controller 
	//properties
	public JFrame theframe;
	C4WinnerLoserScreen gameplaypanel = new C4WinnerLoserScreen();
	C4HelpScreen helppanel = new C4HelpScreen();
	String strCurrentScreen = "gameplay";
	
	//methods
	public void actionPerformed(ActionEvent evt){
		
	}
	public void stateChanged(ChangeEvent evt){
		
	}
	
	
	//constructor
	public controllerMainFrame(){
		theframe = new JFrame("Connect 4");
		
		if(strCurrentScreen.equalsIgnoreCase("gameplay")){
			gameplaypanel.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(gameplaypanel);
			theframe.pack();
			
		}else if(strCurrentScreen.equalsIgnoreCase("help")){
			helppanel.setPreferredSize(new Dimension(1280, 720));
			theframe.setContentPane(helppanel);
			theframe.pack();
		}
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setResizable(false);

		
		
	}
	
	//main program
	public static void main(String[] args){
		new controllerMainFrame();
	}
}
