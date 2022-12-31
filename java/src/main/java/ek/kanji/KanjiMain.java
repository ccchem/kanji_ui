package ek.kanji;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;


public class KanjiMain 
{

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(new FlatDarkLaf());
		if(SystemInfo.isLinux)
		{
			JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		}
		
		SwingUtilities.invokeLater(() ->
        {
            MainWindow window = new MainWindow();
            window.setSize(1000, 800);
            // Center on screen
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });

	}

}
