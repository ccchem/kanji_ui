package ek.kanji;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RadPanel extends JPanel
{
	private UISettings cfg;

	private static final String[] K_KUCHI = { "口", "言", "石" };	
	private static final String[] K_HI = { "日", "月", "門" };
	private static final String[] K_ME = { "目", "貝", "頁", "耳" };
	private static final String[] K_SARA = { "皿", "亜" };
	private static final String[] K_TA = { "田", "車", "里" };
	private static final String[] K_BOX = { "冂", "囗"};

	
	private static final String[] K_KI = { "木", "禾", "米" };
	private static final String[] K_OOKI = { "大", "矢" };
	
	private static final String[] K_LEGS = { "儿", "ハ", "川", "山" };
	

	private static final String[] K_LEFT_1 = { "亻", "彳", "礻", "忄" };
	private static final String[] K_LEFT_2 = { "厂", "广", "疒", "尸" };
	
	private static final String[] K_TOP = { "一", "亠", "冖", "宀", "⺌" };
	
	private static final String[] K_PLUS = { "土", "十", };
	
	
	private static final String[] K_OU = { "工", "王", "生" };
	private static final String[] K_MIZU = { "氵", "水", "雨" };
	private static final String[] K_4 = { "糸", "幺", "厶", "雨" };
	
	
	private static class ComboListener implements ActionListener
	{
		private JTextField fld;
		
		public ComboListener(JTextField fld)
		{
			this.fld = fld;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JComboBox cb = (JComboBox)e.getSource();
			String rad = (String)cb.getSelectedItem();
			cb.setSelectedIndex(0);
			
			String txt = fld.getText();
			if(txt == null || txt.contains(rad)) return;

			fld.setText(txt + rad);
		}
	}
	
	private ComboListener comboLIstener;
	private JTextField txtRad;
	
	public RadPanel(UISettings cfg)
	{
		super();
		this.cfg = cfg;

		txtRad = new JTextField();
		txtRad.setFont(cfg.labelFont);
		
		comboLIstener = new ComboListener(txtRad);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(txtRad);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 6, 10, 10));
		p1.add(createComboBox(K_KUCHI));
		p1.add(createComboBox(K_HI));
		p1.add(createComboBox(K_ME));
		p1.add(createComboBox(K_SARA));
		p1.add(createComboBox(K_TA));
		p1.add(createComboBox(K_BOX));
		
		p1.add(createComboBox(K_KI));
		p1.add(createComboBox(K_OOKI));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0, 6, 10, 10));
		p2.add(createComboBox(K_LEFT_1));
		p2.add(createComboBox(K_LEFT_2));
		p2.add(createComboBox(K_TOP));
		
		add(p1);
		add(p2);
	}
	
	private JComboBox<String> createComboBox(String... items)
	{
		JComboBox<String> cb = new JComboBox<>(items);
		cb.setFont(cfg.labelFont);
		cb.addActionListener(comboLIstener);
		return cb;
	}
}
