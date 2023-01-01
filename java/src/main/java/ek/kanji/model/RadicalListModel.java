package ek.kanji.model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class RadicalListModel extends AbstractListModel<String>
{
	protected Map<String, String[]> map = new TreeMap<>();
	protected String[] data;
	protected boolean includeKey = true;
	
	public RadicalListModel()
	{
	}
	
	protected void addMapEntry(String str)
	{
		String[] rads = str.split(" ");
		String key = rads[0];
		
		if(!includeKey)
		{
			rads = Arrays.copyOfRange(rads, 1, rads.length);	
		}
		
		map.put(key, rads);
	}
	
	public void select(String rad)
	{
		if(rad == null) 
		{
			data = null;
		}
		else
		{
			data = map.get(rad);
		}
		
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
