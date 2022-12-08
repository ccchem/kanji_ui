package ek.kanji.rad.k2;

import ek.kanji.rad.SecondKeyMap;

public class Num_0_Map implements SecondKeyMap
{
    private static final String[] helpText = 
    {
        "呂 2  品 3  器 4  石 i  豆 m  舌 s"
    };
    
    @Override
    public char getKey1() 
    {
        return '0';
    }

    @Override
    public String[] getHelp()
    {
        return helpText;
    }
    
    @Override
    public String getKanji(char ch)
    {
        switch(ch)
        {
        case 'i': return("石");        
        case '2': return("呂");
        case '3': return("品");
        case '4': return("器");
        
        case 'm': return("豆");
        case 's': return("舌");

        }
        
        return null;
    }

}
