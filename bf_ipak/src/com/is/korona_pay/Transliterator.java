package com.is.korona_pay;

import java.util.HashMap;
import java.util.Map;

public class Transliterator {

	private static final Map<Character, String> charMap = new HashMap<Character, String>();

    static {
        charMap.put('�', "A");
        charMap.put('�', "B");
        charMap.put('�', "V");
        charMap.put('�', "G");
        charMap.put('�', "D");
        charMap.put('�', "E");
        charMap.put('�', "E");
        charMap.put('�', "J");
        charMap.put('�', "Z");
        charMap.put('�', "I");
        /*charMap.put('�', "I");*/
        charMap.put('�', "Y");
        charMap.put('�', "K");
        charMap.put('�', "L");
        charMap.put('�', "M");
        charMap.put('�', "N");
        charMap.put('�', "O");
        charMap.put('�', "P");
        charMap.put('�', "R");
        charMap.put('�', "S");
        charMap.put('�', "T");
        charMap.put('�', "U");
        charMap.put('�', "F");
        charMap.put('�', "X");
        charMap.put('�', "CH");
        charMap.put('�', "C");
        charMap.put('�', "SH");
        charMap.put('�', "SH");
        charMap.put('�', "'");
        charMap.put('�', "I");
        charMap.put('�', "'");
        charMap.put('�', "E");
        charMap.put('�', "YU");
        charMap.put('�', "YA");
        charMap.put('�', "a");
        charMap.put('�', "b");
        charMap.put('�', "v");
        charMap.put('�', "g");
        charMap.put('�', "d");
        charMap.put('�', "e");
        charMap.put('�', "e");
        charMap.put('�', "j");
        charMap.put('�', "z");
        charMap.put('�', "i");
        /*charMap.put('�', "y");*/
        charMap.put('�', "y");
        charMap.put('�', "k");
        charMap.put('�', "l");
        charMap.put('�', "m");
        charMap.put('�', "n");
        charMap.put('�', "o");
        charMap.put('�', "p");
        charMap.put('�', "r");
        charMap.put('�', "s");
        charMap.put('�', "t");
        charMap.put('�', "u");
        charMap.put('�', "f");
        charMap.put('�', "x");
        charMap.put('�', "c");
        charMap.put('�', "ch");
        charMap.put('�', "sh");
        charMap.put('�', "sh");
        charMap.put('�', "'");
        charMap.put('�', "i");
        charMap.put('�', "'");
        charMap.put('�', "e");
        charMap.put('�', "yu");
        charMap.put('�', "ya");
        charMap.put('%', "KH");
        charMap.put('^', "ZH");

    }
    private static final Map<Character, String> charMapEn = new HashMap<Character, String>();

    static {
        charMapEn.put('A', "�");
        charMapEn.put('B', "�");        
        charMapEn.put('C', "�");
        charMapEn.put('D', "�");
        charMapEn.put('E', "�");
        charMapEn.put('F', "�");
        charMapEn.put('G', "�");
        charMapEn.put('H', "�");
        charMapEn.put('I', "�");
        charMapEn.put('J', "�");
        charMapEn.put('K', "�");
        charMapEn.put('L', "�");
        charMapEn.put('M', "�");
        charMapEn.put('N', "�");
        charMapEn.put('O', "�");
        charMapEn.put('P', "�");
        charMapEn.put('Q', "�");
        charMapEn.put('R', "�");
        charMapEn.put('S', "�");
        charMapEn.put('T', "�");
        charMapEn.put('U', "�");
        charMapEn.put('V', "�");
        //charMapEn.put('W', "�");
        charMapEn.put('X', "�");
        charMapEn.put('Y', "�");
        charMapEn.put('Z', "�");
        charMapEn.put('a', "�");
        charMapEn.put('b', "�");
        charMapEn.put('c', "�");
        charMapEn.put('d', "�");
        charMapEn.put('e', "�");
        charMapEn.put('f', "�");
        charMapEn.put('g', "�");
        //charMapEn.put('h', "�");
        charMapEn.put('i', "�");
        charMapEn.put('j', "�");
        charMapEn.put('k', "�");
        charMapEn.put('l', "�");
        charMapEn.put('m', "�");
        charMapEn.put('n', "�");
        charMapEn.put('o', "�");
        charMapEn.put('p', "�");
        charMapEn.put('q', "�");
        charMapEn.put('r', "�");
        charMapEn.put('s', "�");
        charMapEn.put('t', "�");
        charMapEn.put('u', "�");
        charMapEn.put('v', "�");
        //charMapEn.put('w', "�");
        charMapEn.put('x', "�");
        charMapEn.put('y', "�");
        charMapEn.put('z', "�");
        charMapEn.put('\'', "�");
        charMapEn.put('%',"�" );
        charMapEn.put('5', "�");
        charMapEn.put('^', "�");
        charMapEn.put('6', "�");
        charMapEn.put('?', "�");
        charMapEn.put('7', "�");
        charMapEn.put('*', "�");
    }
    
    
    private static final Map<Character,String> vowels = new HashMap<Character,String>();
    
    static{
    	vowels.put('A', "�");
    	vowels.put('E', "�");
    	vowels.put('U', "�");
    	vowels.put('O', "�");
    	vowels.put('a', "�");
    	vowels.put('e', "�");
    	vowels.put('u', "�");
    	vowels.put('o', "�");
    }
    
    private static String cyr2lat(String string) {
        StringBuilder transliteratedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            String charFromMap = charMap.get(ch);
            if (charFromMap == null) {
                transliteratedString.append(ch);
            } else {
                transliteratedString.append(charFromMap);
            }
        }
        return transliteratedString.toString();
    }
    

	private static Boolean isCyrillic(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.UnicodeBlock.of(text.charAt(i)).equals(
					Character.UnicodeBlock.CYRILLIC)) {
				// contains Cyrillic
				return true;
			}
		}
		return false;
	}
	
	static boolean flag = false;
	public static String lat2cyr(String s){
		s = s.replace("null", "").trim();
		s = replaceComplex(s);
		StringBuilder sb = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); i++){   // ���� �� ������ ����� �������. � ��������, �������� ��� ��������� ������
			char ch = s.charAt(i);
			String charFromMap = charMapEn.get(ch);
			if (charFromMap != null){
				sb.append(charFromMap);
				if (flag){
					if (vowels.get(ch) != null){
						if (sb.length()!=0)
							sb.deleteCharAt(sb.length()-1);
						if (sb.length()!=0)
							sb.deleteCharAt(sb.length()-1);
						sb.append(vowels.get(ch));
						flag = false;
					}
					else
						flag = false;
				}
				if (ch == 'Y' || ch == 'y')
					flag = true;
			}
			else{
				flag = false;
				sb.append(ch);
			}
			     
		}
		return sb.toString();
	}
	
	public static String[] getTransliteratedNames(String name){
		
		String[] names = new String[7];
		if (name!=null && !name.isEmpty()){
			names[0] = name.replace("null", "").toUpperCase();
			if (isCyrillic(name)){
				names[1] = cyr2lat(names[0]).toUpperCase();
				names[2] = cyr2lat(names[0]).replace("X", "KH").replace("J", "ZH");
				names[3] = cyr2lat(names[0]).replace("X", "H");
			}
			else{
				names[1] = lat2cyr(replaceComplex(names[0].toUpperCase()));
				names[2] = lat2cyr(replaceComplex(names[0].toUpperCase())).replace("�", "�");
				names[3] = "";
			}
			}
		return names;
	}
	private static String replaceComplex(String src){
		src = src.replace("SH", "%");
		src = src.replace("SH'", "?");
		src = src.replace("CH", "^");
		src = src.replace("KH", "X");
		src = src.replace("ZH", "J");
		src = src.replace("Y'", "*");
		return src;
	}

}
