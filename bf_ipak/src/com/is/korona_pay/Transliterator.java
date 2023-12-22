package com.is.korona_pay;

import java.util.HashMap;
import java.util.Map;

public class Transliterator {

	private static final Map<Character, String> charMap = new HashMap<Character, String>();

    static {
        charMap.put('А', "A");
        charMap.put('Б', "B");
        charMap.put('В', "V");
        charMap.put('Г', "G");
        charMap.put('Д', "D");
        charMap.put('Е', "E");
        charMap.put('Ё', "E");
        charMap.put('Ж', "J");
        charMap.put('З', "Z");
        charMap.put('И', "I");
        /*charMap.put('Й', "I");*/
        charMap.put('Й', "Y");
        charMap.put('К', "K");
        charMap.put('Л', "L");
        charMap.put('М', "M");
        charMap.put('Н', "N");
        charMap.put('О', "O");
        charMap.put('П', "P");
        charMap.put('Р', "R");
        charMap.put('С', "S");
        charMap.put('Т', "T");
        charMap.put('У', "U");
        charMap.put('Ф', "F");
        charMap.put('Х', "X");
        charMap.put('Ч', "CH");
        charMap.put('Ц', "C");
        charMap.put('Ш', "SH");
        charMap.put('Щ', "SH");
        charMap.put('Ъ', "'");
        charMap.put('Ы', "I");
        charMap.put('Ь', "'");
        charMap.put('Э', "E");
        charMap.put('Ю', "YU");
        charMap.put('Я', "YA");
        charMap.put('а', "a");
        charMap.put('б', "b");
        charMap.put('в', "v");
        charMap.put('г', "g");
        charMap.put('д', "d");
        charMap.put('е', "e");
        charMap.put('ё', "e");
        charMap.put('ж', "j");
        charMap.put('з', "z");
        charMap.put('и', "i");
        /*charMap.put('й', "y");*/
        charMap.put('й', "y");
        charMap.put('к', "k");
        charMap.put('л', "l");
        charMap.put('м', "m");
        charMap.put('н', "n");
        charMap.put('о', "o");
        charMap.put('п', "p");
        charMap.put('р', "r");
        charMap.put('с', "s");
        charMap.put('т', "t");
        charMap.put('у', "u");
        charMap.put('ф', "f");
        charMap.put('х', "x");
        charMap.put('ц', "c");
        charMap.put('ч', "ch");
        charMap.put('ш', "sh");
        charMap.put('щ', "sh");
        charMap.put('ъ', "'");
        charMap.put('ы', "i");
        charMap.put('ь', "'");
        charMap.put('э', "e");
        charMap.put('ю', "yu");
        charMap.put('я', "ya");
        charMap.put('%', "KH");
        charMap.put('^', "ZH");

    }
    private static final Map<Character, String> charMapEn = new HashMap<Character, String>();

    static {
        charMapEn.put('A', "А");
        charMapEn.put('B', "Б");        
        charMapEn.put('C', "Ц");
        charMapEn.put('D', "Д");
        charMapEn.put('E', "Е");
        charMapEn.put('F', "Ф");
        charMapEn.put('G', "Г");
        charMapEn.put('H', "Х");
        charMapEn.put('I', "И");
        charMapEn.put('J', "Ж");
        charMapEn.put('K', "К");
        charMapEn.put('L', "Л");
        charMapEn.put('M', "М");
        charMapEn.put('N', "Н");
        charMapEn.put('O', "О");
        charMapEn.put('P', "П");
        charMapEn.put('Q', "К");
        charMapEn.put('R', "Р");
        charMapEn.put('S', "С");
        charMapEn.put('T', "Т");
        charMapEn.put('U', "У");
        charMapEn.put('V', "В");
        //charMapEn.put('W', "В");
        charMapEn.put('X', "Х");
        charMapEn.put('Y', "Й");
        charMapEn.put('Z', "З");
        charMapEn.put('a', "а");
        charMapEn.put('b', "б");
        charMapEn.put('c', "ц");
        charMapEn.put('d', "д");
        charMapEn.put('e', "е");
        charMapEn.put('f', "ф");
        charMapEn.put('g', "г");
        //charMapEn.put('h', "х");
        charMapEn.put('i', "и");
        charMapEn.put('j', "ж");
        charMapEn.put('k', "к");
        charMapEn.put('l', "л");
        charMapEn.put('m', "м");
        charMapEn.put('n', "н");
        charMapEn.put('o', "о");
        charMapEn.put('p', "п");
        charMapEn.put('q', "к");
        charMapEn.put('r', "р");
        charMapEn.put('s', "с");
        charMapEn.put('t', "т");
        charMapEn.put('u', "у");
        charMapEn.put('v', "в");
        //charMapEn.put('w', "в");
        charMapEn.put('x', "х");
        charMapEn.put('y', "й");
        charMapEn.put('z', "з");
        charMapEn.put('\'', "Ь");
        charMapEn.put('%',"Ш" );
        charMapEn.put('5', "ш");
        charMapEn.put('^', "Ч");
        charMapEn.put('6', "ч");
        charMapEn.put('?', "Щ");
        charMapEn.put('7', "щ");
        charMapEn.put('*', "Й");
    }
    
    
    private static final Map<Character,String> vowels = new HashMap<Character,String>();
    
    static{
    	vowels.put('A', "Я");
    	vowels.put('E', "Е");
    	vowels.put('U', "Ю");
    	vowels.put('O', "Е");
    	vowels.put('a', "я");
    	vowels.put('e', "е");
    	vowels.put('u', "ю");
    	vowels.put('o', "Е");
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
		for (int i = 0; i < s.length(); i++){   // Идем по строке слева направо. В принципе, подходит для обработки потока
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
				names[2] = lat2cyr(replaceComplex(names[0].toUpperCase())).replace("Е", "Э");
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
