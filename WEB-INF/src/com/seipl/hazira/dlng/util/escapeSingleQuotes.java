package com.seipl.hazira.dlng.util;
public class escapeSingleQuotes{

 public String replaceSingleQuotes(String sInput){
       StringBuffer sbOutput = new StringBuffer();
       for(int i = 0; sInput!= null && i < sInput.length(); i++)
       {
           sbOutput.append(sInput.charAt(i));
           // If the character is a single quote
           // escape it.
           if(sInput.charAt(i) == '\''){
               sbOutput.append('\'');
           }
           
       }
       return sbOutput.toString();
}

}//end of class
