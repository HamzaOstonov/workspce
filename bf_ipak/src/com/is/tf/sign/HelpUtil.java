package com.is.tf.sign;


import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;

//import org.jsoup.Jsoup;

public class HelpUtil {
	public static void main(String[] args) {
		try {
		String string = "&#x412;&#x430;&#x441;&#x44F;";
	    byte[] converttoBytes = string.getBytes("UTF-8");
	    //String parsed = Jsoup.parse(string).text();
	    //System.out.println(parsed);
	    System.out.println(convert(string));
	    System.out.println(string);
	    System.out.println(new String(string.getBytes("UTF-8")));
	    System.out.println(new String(string.getBytes("ISO-8859-1"), "UTF-8"));
	    System.out.println(new String( string.getBytes("ISO-8859-1"),"Cp1251"));
	    System.out.println(new String(CharacterCodeConverter.convertToUtf8(string),"utf-8"));
	    System.out.println(CharacterCodeConverter.convertToUnicode(string));
	    System.out.println(Arrays.toString(CharacterCodeConverter.convertToHEX(string)));
	    System.out.println(Arrays.toString(CharacterCodeConverter.convertToHexNCR(string)));
	    System.out.println(Arrays.toString(CharacterCodeConverter.convertToUTF8(string)));
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// your UTF-8 string here, taken from args, request params, etc.  
		String utf = "\u003c and \u003e &#x412;&#x430;&#x441;&#x44F;";  
		  
		// convert the input string to a character array  
		char[] chars = utf.toCharArray();  
		  
		StringBuilder sb = new StringBuilder();  
		for (int i = 0; i < chars.length; i++)  
		{  
		    int unipoint = Character.codePointAt(chars, i);  
		    if ((unipoint < 32) || (unipoint > 127))  
		    {  
		        StringBuilder hexString = new StringBuilder();  
		        for (int k = 0; k < 4; k++) // 4 times to build a 4-digit hex  
		        {  
		            hexString.insert(0, Integer.toHexString(unipoint % 16));  
		            unipoint = unipoint / 16;  
		        }  
		        sb.append("\\u"+hexString);  
		    }  
		    else  
		    {  
		        sb.append(chars[i]);  
		    }  
		}  
		  
		// display the ASCII encoded string  
		System.out.println ("String s = " + sb.toString());
	}  
	
	
	
	public static String convert(
		    String string)
		        throws IOException, UnsupportedEncodingException
		{
		String outfile = "";
	    
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		os.write( string.getBytes("Cp866") );
		os.write( string.getBytes("Cp1251") );
		os.write( string.getBytes("utf-8") );
		os.flush();
		os.close();
		return os.toString();
	}
	
	public static void convertFile(
		    String infile, //input file name, if null reads from console/stdin
		    String outfile, //output file name, if null writes to console/stdout
		    String from,   //encoding of input file (e.g. UTF-8/windows-1251, etc)
		    String to)     //encoding of output file (e.g. UTF-8/windows-1251, etc)
		        throws IOException, UnsupportedEncodingException
		{
		    // set up byte streams
		    InputStream in;
		    if(infile != null)
		        in=new FileInputStream(infile);
		    else
		        in=System.in;
		    OutputStream out;
		    if(outfile != null)
		        out=new FileOutputStream(outfile);
		    else
		        out=System.out;

		    // Use default encoding if no encoding is specified.
		    if(from == null) from=System.getProperty("file.encoding");
		    if(to == null) to=System.getProperty("file.encoding");

		    // Set up character stream
		    Reader r=new BufferedReader(new InputStreamReader(in, from));
		    Writer w=new BufferedWriter(new OutputStreamWriter(out, to));

		    // Copy characters from input to output.  The InputStreamReader
		    // converts from the input encoding to Unicode,, and the OutputStreamWriter
		    // converts from Unicode to the output encoding.  Characters that cannot be
		    // represented in the output encoding are output as '?'
		    char[] buffer=new char[4096];
		    int len;
		    while((len=r.read(buffer)) != -1)
		        w.write(buffer, 0, len);
		    r.close();
		    w.flush();
		    w.close();
		}
	
}
