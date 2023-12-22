package com.is.tf.sign;

public class CharacterCodeConverter  {  
    
  /** 
  *This method is employed to convert the input string into a String of Decimal Code. 
  * @param str: Input String which is to be converted into a String of Decimal Code. 
  * @return: Decimal Code String. 
  */  
  public static String toDecimalCode(String str)  
   {  
    String outputString = "";  
    int haut = 0;  
    for (int i = 0; i < str.length(); i++)  
    {  
     char b = str.charAt(i);    
     if (b < 0 || b > 0xFFFF)  
     {  
      outputString += "!error " + dec2hexCode(b) + "!";  
     }  
     if (haut != 0)  
     {  
      if (0xDC00 <= b && b <= 0xDFFF)  
      {  
       outputString += dec2hexCode(0x10000 + ((haut - 0xD800) << 10) + (b - 0xDC00)) + "";  
       haut = 0;  
       continue;  
      }  
      else  
      {  
       outputString += "!error " + dec2hexCode(haut) + "!";  
       haut = 0;  
      }  
     }  
     if (0xD800 <= b && b <= 0xDBFF)  
     {  
      haut = b;  
     }  
     else  
     {  
      outputString += dec2hexCode(b) + "";  
     }  
    }  
    return outputString;  
   }  
   private static String dec2hexCode (int textString)  
   {  
    int t = textString + 0;  
    String temp  = Integer.toString(t).toUpperCase()+" ";  
    return temp;  
   }  
    
    
  /** 
  *This method is employed to convert the input string into a String of Unicode. 
  * @param str: Input String which is to be converted into a String of Unicode. 
  * @return: Unicod String. 
  */  
    
     public static String convertToUnicode(String inputString)  
   {  
    String finalString = "";  
    String hex="";  
    try  
    {    
     char[] charArray = inputString.toCharArray();  
     for (int i=0; i<charArray.length; i++)  
     {  
      hex = new String((Integer.toHexString(charArray[i])).getBytes("Cp1252"), "Cp1256");  
      switch (hex.length())  
      {  
       case 1: finalString = finalString+"\\u000"; break;  
       case 2: finalString = finalString+"\\u00"; break;  
       case 3: finalString = finalString+"\\u0"; break;  
       case 4: finalString = finalString+"\\u"; break;  
       default: System.out.println( hex+" is too long to be a Character");  
      }  
      finalString = finalString+hex;  
     }   
    }  
    catch (Exception e)  
    {  
     e.printStackTrace();  
    }  
    return finalString;  
   }  
    
    
  /** 
  *This method is employed to convert the input string into a Bytes Array of UTF-8. 
  * @param str: Input String which is to be converted into a Byte Array of UTF-8. 
  * @return: Byte Array of UTF-8. 
  */  
   public static byte[] convertToUtf8(String string)  
   {  
    byte[] utf8Bytes = null;  
    try {  
        //String string = "abcu5639u563b";  
        utf8Bytes = string.getBytes("UTF-8");  
          
          
      }catch (Exception e) {  
        e.printStackTrace();  
      }  
      return utf8Bytes;  
   }  
    
    
  /** 
  *This method is employed to convert the input string into a String of Decimal NCR. 
  * @param str: Input String which is to be converted into a String of Decimal NCR. 
  * @return: Decimal NCR  String. 
  */  
  public static String toDecimalNCR(String str)  
   {  
    String outputString = "";  
    int haut = 0;  
    for (int i = 0; i < str.length(); i++)  
    {  
     char b = str.charAt(i);   
     if (b < 0 || b > 0xFFFF)  
     {  
      outputString += "!error " + dec2hex(b) + "!";  
     }  
     if (haut != 0)  
     {  
      if (0xDC00 <= b && b <= 0xDFFF)  
      {  
       outputString += dec2hex(0x10000 + ((haut - 0xD800) << 10) + (b - 0xDC00)) + "";  
       haut = 0;  
       continue;  
      }  
      else  
      {  
       outputString += "!error " + dec2hex(haut) + "!";  
       haut = 0;  
      }  
     }  
     if (0xD800 <= b && b <= 0xDBFF)  
     {  
      haut = b;  
     }  
     else  
     {  
      outputString += dec2hex(b) + "";  
     }  
    }  
    return outputString;  
   }  
   private static String dec2hex (int textString)  
   {  
    int t = textString + 0;  
    String temp  = "&#"+Integer.toString(t).toUpperCase()+";";  
    return temp.trim();  
   }  
    
    
  /** 
  *This method is employed to convert the input string into a String Array of UTF-8. 
  * @param str: Input String which is to be converted into a String Array of UTF-8. 
  * @return: String Array of UTF-8. 
  */  
   public static String[] convertToUTF8(String string)  
   {  
    String[] sUTF8=null;//new sHexBytes[100];  
    byte[] bUTF8Bytes = null;  
    try {  
    
        //String string = "abcu5639u563b";  
        bUTF8Bytes = string.getBytes("UTF-8");  
        sUTF8=printUTF8(bUTF8Bytes);  
          
          
      }catch (Exception e) {  
        e.printStackTrace();  
      }  
      return sUTF8;  
   }  
    
    
   private static String[] printUTF8(byte[] array)  
   {  
    int l=array.length;  
     String[] temp = new String[l];  
        for (int k = 0; k < array.length; k++) {  
      temp[k]= new String();  
      temp[k]  =byteToHex(array[k]);  
      temp[k].trim();  
        }  
     return temp;  
    }  
    
    
  /** 
  *This method is employed to convert the input string into a String Array of Hexadecimal NCR Code. 
  * @param string: Input String which is to be converted into a String Array of Hexadecimal NCR Code. 
  * @return: String Array of Hexadecimal  NCR Code. 
  */  
   public static String[] convertToHexNCR(String string)  
   {  
    String[] sHexBytes=null;  
    byte[] hexBytes = null;  
    try {  
    
          
        hexBytes = string.getBytes("UTF-8");  
        sHexBytes=printHexNCR(hexBytes);  
          
          
      }catch (Exception e) {  
        e.printStackTrace();  
      }  
      return sHexBytes;  
   }  
    
    
   private static String[] printHexNCR(byte[] array)  
   {  
    int l=array.length;  
     String[] temp = new String[l];  
        for (int k = 0; k < array.length; k++) {  
      temp[k]= new String();  
      temp[k]  ="&#x" + byteToHex(array[k])+";";  
      temp[k].trim();  
        }  
     return temp;  
    }  
    
    
    
    
    
    
  /** 
  *This method is employed to convert the input string into a String Array of Hexadecimal Code. 
  * @param string: Input String which is to be converted into a String Array of Hexadecimal  Code. 
  * @return: String Array of Hexadecimal Code. 
  */  
    
   public static String[] convertToHEX(String string)  
   {  
    String[] sHexBytes=null;  
    byte[] hexBytes = null;  
    try {  
    
          
        hexBytes = string.getBytes("UTF-8");  
        sHexBytes=printHex(hexBytes);  
          
          
      }catch (Exception e) {  
        e.printStackTrace();  
      }  
      return sHexBytes;  
   }  
    
    
   private static String[] printHex(byte[] array)  
   {  
    int l=array.length;  
     String[] temp = new String[l];  
        for (int k = 0; k < array.length; k++) {  
      temp[k]= new String();  
      temp[k]  ="0x" + byteToHex(array[k]);  
      temp[k].trim();  
        }  
     return temp;  
    }  
  /////////////////////////////////////  
    
   private static String byteToHex(byte b) {  
        // Returns hex String representation of byte b  
        char hexDigit[] = {  
           '0', '1', '2', '3', '4', '5', '6', '7',  
           '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'  
        };  
        char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };  
        return new String(array);  
     }  
    
   private static String charToHex(char c) {  
        // Returns hex String representation of char c  
        byte hi = (byte) (c >>> 8);  
        byte lo = (byte) (c & 0xff);  
        return byteToHex(hi) + byteToHex(lo);  
     }  
    
    
  }//class  
