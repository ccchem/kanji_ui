package ek.kanji;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;

import ek.kanji.rad.SecondKeyMap;
import ek.kanji.rad.TextListener;
import ek.kanji.rad.TwoKeyMap;


@SuppressWarnings("serial")
public class RadicalPanel extends JPanel implements KeyListener
{
    private JTextField txtInput;
    private JLabel lblHelp1;
    private JLabel lblHelp2;
    
    private char key1 = 0;
    
    //private SingleKeyMap sKeyMap = new SingleKeyMap();
    private TwoKeyMap twoKeyMap = new TwoKeyMap();
    //private AltKeyMap altKeyMap = new AltKeyMap();            
    
    private TextListener textListener;
    
    private UndoManager undoMgr;
    
    
    public RadicalPanel(UISettings cfg)
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(cfg.labelBG);

        undoMgr = new UndoManager();
        undoMgr.setLimit(20);
        
        txtInput = new JTextField();
        //txtInput.setCaretColor(Color.GRAY);
        txtInput.addKeyListener(this);
        //txtInput.getActionMap().put(DefaultEditorKit.deletePrevCharAction, new CustomTextActions.DeletePrevCharAction());
        txtInput.setFont(cfg.textFont);
//        txtInput.setBackground(cfg.textBG);
//        txtInput.setForeground(cfg.textFG);
        txtInput.setBorder(new EmptyBorder(10, 5, 10, 5));
        txtInput.setAlignmentX(LEFT_ALIGNMENT);
        
        lblHelp1 = new JLabel();
        lblHelp1.setFont(cfg.helpFont);
//        lblHelp1.setForeground(cfg.labelFG);
        lblHelp1.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblHelp1.setAlignmentX(LEFT_ALIGNMENT);

        lblHelp2 = new JLabel();
        lblHelp2.setFont(cfg.helpFont);
//        lblHelp2.setForeground(cfg.labelFG);
        lblHelp2.setBorder(new EmptyBorder(0, 5, 5, 5));
        lblHelp2.setAlignmentX(LEFT_ALIGNMENT);

        add(txtInput);
        add(lblHelp1);
        add(lblHelp2);
        
        txtInput.getDocument().addUndoableEditListener((e) -> 
        {
            undoMgr.addEdit(e.getEdit()); 
        });
    }

    
    public void setTextListener(TextListener listener)
    {
        this.textListener = listener;
    }
    
    
    public void setHelp1(String txt)
    {
        lblHelp1.setText(txt);
    }

    public void setHelp2(String txt)
    {
        lblHelp2.setText(txt);
    }

    
    @Override
    public void keyTyped(KeyEvent e)
    {
        boolean isCtrl = e.isControlDown();
        boolean isAlt = e.isAltDown();
        
        char ch = e.getKeyChar();
        e.consume();

        if(key1 == 0)
        {
            if(isCtrl)
            {
                processCtrl(ch);
            }
            else if(isAlt)
            {
                //processAlt(ch);
            }
            else
            {
                processFirstKey(ch);
            }
        }
        else
        {
            processSecondKey(ch);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(key1 != 0) return;
        
        /*
        switch(e.getKeyCode())
        {
        case KeyEvent.VK_F2:
            showYazawaRef(getSelectedChar());
            break;
        case KeyEvent.VK_F3:
            showReading(getSelectedChar());
            break;
        }
		*/  
    }

 
    /*
    private void processAlt(char ch)
    {
        char kanji = altKeyMap.get(ch);
        if(kanji != 0)
        {
            insertAtCursor("" + kanji);
        }
    }
    */
    
    private void processCtrl(char ch)
    {
        // Ctrl-Z
        if(ch == 26)
        {
            if(undoMgr.canUndo())
            {
                undoMgr.undo();
            }
        }
    }
    
    
    private char getSelectedChar()
    {
        String txt = txtInput.getSelectedText();
        if(txt == null || txt.isEmpty())
        {
            txt = txtInput.getText();
        }
        
        if(txt == null || txt.isEmpty()) return 0;
        
        if(txt.length() == 1)
        {
            return txt.charAt(0);
        }
        else
        {
            return txt.charAt(txt.length()-1);
        }
    }
    
    
    private void processFirstKey(char ch)
    {
        if(ch == '\n')
        {
            handleEnter();
        }
        // Esc
        else if(ch == 27)
        {
            clearHelp();
        }
        else
        {
            SecondKeyMap kp = twoKeyMap.get(ch);
            if(kp != null)
            {
                key1 = ch;
                String[] help = kp.getHelp();
                if(help.length >= 1) lblHelp1.setText(help[0]);
                if(help.length >= 2) lblHelp2.setText(kp.getHelp()[1]);
            }
            else
            {
                char kanji = 0; //sKeyMap.get(ch);
                if(kanji != 0)
                {
                    insertAtCursor("" + kanji);
                }
            }
        }
    }

    
    public void clearHelp()
    {
        lblHelp1.setText("");
        lblHelp2.setText("");
    }
    
    private void processSecondKey(char ch)
    {
        // Esc
        if(ch == 27)
        {
            clearHelp();
            key1 = 0;
        }
        else
        {
            String kanji = null;
            SecondKeyMap kp = twoKeyMap.get(key1);
            if(kp != null)
            {
                kanji = kp.getKanji(ch);
            }
            
            if(kanji != null)
            {
                insertAtCursor(kanji);
                key1 = 0;
                clearHelp();
            }
        }
    }
    
    
    private void handleEnter()
    {
        String txt = txtInput.getText();
        if(!txt.isEmpty())
        {
        	/*
            String kanji = KanjiService.getInstance().findKanjiByParts(txt);

            if(kanji != null)
            {
                txtInput.setText("");
                if(textListener != null) 
                {
                    textListener.onText(kanji);
                }
            }
            */
        }
    }
    
    private void insertAtCursor(String str)
    {
        txtInput.replaceSelection("");
        try
        {
            txtInput.getDocument().insertString(txtInput.getCaretPosition(), str, null);
        }
        catch(Exception ex)
        {
        }
    }
    
}
