package ek.kanji;

import java.awt.Font;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class Main 
{

	private static Font[] loadFont(String name) throws Exception
    {
        InputStream is = null;
        
        try
        {
            is = Main.class.getClassLoader().getResourceAsStream(name);
            if(is == null) throw new Exception("Could not open file " + name);
            
            Font[] fonts = Font.createFonts(is);
            if(fonts == null || fonts.length == 0) throw new Exception("Could not create font " + name);

            return fonts;
        }
        finally
        {
            if(is != null)
            {
                is.close();
            }
        }
    }

	
	public static void main(String[] args) throws Exception
	{
		UISettings cfg = new UISettings();
		
		Font[] fonts = loadFont("TakaoGothic.ttf");
        cfg.textFont = fonts[0].deriveFont(50.0f);
        cfg.helpFont = fonts[0].deriveFont(50.0f);
        cfg.labelFont = fonts[0].deriveFont(40.0f);

		UIManager.setLookAndFeel( new FlatDarkLaf());
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		SwingUtilities.invokeLater(() ->
        {
            MainWindow window = new MainWindow(cfg);
            window.setSize(1000, 800);
            // Center on screen
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });

	}

}
