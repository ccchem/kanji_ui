package ek.kanji;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;




public class MainWindow extends JFrame
{
	public MainWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.LINE_AXIS));
		
		String[] model1 = {"口", "日", "目", "皿", "田"};
		JList<String> list1 = new JList<>(model1);
		setAttributes(list1);
		listPanel.add(list1);
		
		RadicalListModel model2 = new RadicalListModel();
		JList<String> list2 = new JList<>(model2);
		setAttributes(list2);
		listPanel.add(list2);
		
		list1.addListSelectionListener((e) -> 
		{
			if(!e.getValueIsAdjusting())
			{
				String rad = list1.getSelectedValue();
				if(rad != null)
				{
					list2.clearSelection();
					model2.select(rad);					
				}
			}
		});

		list2.addListSelectionListener((e) -> 
		{
			if(!e.getValueIsAdjusting())
			{
				String rad = list2.getSelectedValue();
				//System.out.println(rad);
			}
		});
		
		
		getContentPane().add(listPanel, BorderLayout.CENTER);
	}


	private void setAttributes(JList<String> list)
	{
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setAlignmentY(TOP_ALIGNMENT);
		list.setFont(UIManager.getFont("h00.font"));		
		
		KeyAdapter ka = new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				char ch = e.getKeyChar();
				System.out.println("[" + ch + "]");
				if(ch == 'c' || ch == 'C')
				{
					String selection = list.getSelectedValue();
					if(selection != null)
					{
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(new StringSelection(selection), null);
					}
				}
			}
		};
		
		list.addKeyListener(ka);
	}
}
