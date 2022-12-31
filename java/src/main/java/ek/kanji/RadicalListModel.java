package ek.kanji;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.AbstractListModel;

public class RadicalListModel extends AbstractListModel<String>
{
	private Map<String, String[]> map = new TreeMap<>();
	private String[] data;
	
	public RadicalListModel()
	{
		addMapEntry("口 兄 言 石");
		addMapEntry("日 月 門");
		addMapEntry("目 貝 見 頁 耳");
		addMapEntry("皿 冊 而 亜");
		addMapEntry("田 車 里 重");
	}
	
	private void addMapEntry(String str)
	{
		String[] rads = str.split(" ");
		map.put(rads[0], rads);
	}
	
	public void select(String rad)
	{
		data = map.get(rad);
		fireContentsChanged(this, 0, getSize());
	}
	
	@Override
	public int getSize() 
	{
		return data == null ? 0 : data.length;
	}

	@Override
	public String getElementAt(int index) 
	{
		return data[index];
	}

}
