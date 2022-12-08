package ek.kanji.rad.k2;

import ek.kanji.rad.SecondKeyMap;

public class Num_1_Map implements SecondKeyMap
{
    private static final String[] helpText = 
    {
        "亻 1 彳 2 厂 3 广 4 疒 5"
    };

    @Override
    public char getKey1() 
    {
        return '1';
    }
    
    public String[] getHelp() 
    { 
        return helpText; 
    }
    
    @Override
    public String getKanji(char ch)
    {
        switch(ch)
        {
        case '1': return("亻");
        case '2': return("彳");
        case '3': return("厂");
        case '4': return("广");
        case '5': return("疒");

        }
        
        return null;
    }
}
