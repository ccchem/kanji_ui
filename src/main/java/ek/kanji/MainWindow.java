package ek.kanji;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainWindow extends JFrame 
{
	
	public MainWindow(UISettings cfg)
	{
		super("Kanji Lookup");
	
		getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(40,40,40));
	    //getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//RadicalPanel radP = new RadicalPanel(cfg);
		RadPanel radP = new RadPanel(cfg);
		
		getContentPane().add(radP, BorderLayout.NORTH);
		
	}	
}
