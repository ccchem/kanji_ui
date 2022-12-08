package ek.kanji.rad;

import ek.kanji.rad.k2.Num_0_Map;
import ek.kanji.rad.k2.Num_1_Map;

public class TwoKeyMap
{
    private SecondKeyMap[] keyMap = new SecondKeyMap[128];

    public TwoKeyMap()
    {
        initKeyMap();
    }
    
    public SecondKeyMap get(char ch)
    {
        return keyMap[ch];
    }
    
    
    private void initKeyMap()
    {
        // Numbers
        addKeyMap(new Num_0_Map());
        addKeyMap(new Num_1_Map());
        
    }
    
    
    private void addKeyMap(SecondKeyMap kp)
    {
        keyMap[kp.getKey1()] = kp;
    }

}
