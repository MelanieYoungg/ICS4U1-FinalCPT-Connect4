import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class C4MainFrame implements ActionListener,ChangeListener {
	//using this to swap out panels so we can see how they look, can be used later for the main/controller 
	//properties
	public JFrame theframe;
	C4GameplayScreen thepanel = new C4GameplayScreen();
	
	//methods
	public void actionPerformed(ActionEvent evt){
		
	}
	public void stateChanged(ChangeEvent evt){
		
	}
	
	
	//constructor
	public C4MainFrame(){
		theframe = new JFrame("Connect 4");
		
		thepanel.setPreferredSize(new Dimension(1280, 720));
		
		theframe.setContentPane(thepanel);
		theframe.pack();
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		theframe.setResizable(false);

		
		
	}
	
	//main program
	public static void main(String[] args){
		new C4MainFrame();
	}
}
