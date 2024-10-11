package com.is.SwiftBuffer.parser;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import net.sourceforge.wife.swift.parser.SwiftParser;
import net.sourceforge.wife.swift.model.SwiftBlock;
import net.sourceforge.wife.swift.model.SwiftBlock1;
import net.sourceforge.wife.swift.model.SwiftBlock2;
import net.sourceforge.wife.swift.model.SwiftBlock3;
import net.sourceforge.wife.swift.model.SwiftBlock4;
import net.sourceforge.wife.swift.model.SwiftBlock5;
import net.sourceforge.wife.swift.model.SwiftMessage;

public class SwiftMessageHelper 
{
	
	public static String ParseFromFile(String fileName)
	{
			try 
			{
				File f = new File(fileName);
				
				if (f!=null) 
				{
				System.out.println(f);
				
				FileReader r = new FileReader(f);
				SwiftParser p = new SwiftParser(r);
				SwiftMessage m = p.message();
                return Parse(m);
				}
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  return null;
	}
	
	 public static ArrayList<SwiftMessageWrapper> GetMsgFromFile(String fileName)
	  {
	   BufferedReader reader = null;
	   ArrayList<String> candidates = new ArrayList<String>();
	   ArrayList<SwiftMessageWrapper> messages = new ArrayList<SwiftMessageWrapper>();
	   System.out.println("read file");
	   try
	   {
	     reader = new BufferedReader(new FileReader(fileName));
	   }
	   catch (Exception ex)
	   {
	     ex.printStackTrace();
	   }
	   
     try
     {
       int currentStep = 0;
       int i;
       CharBuffer charBuffer = CharBuffer.allocate(1024);
       StringBuilder strBuffer = new StringBuilder(1024);
       StringBuilder stepBuffer = new StringBuilder(1024);
       String tail = null;
       boolean isStarted = false;
       
       do
       {
         ++currentStep;
         charBuffer.clear();
         i = reader.read(charBuffer);       
         
         if (i == -1)
         {
           if (tail != null)
           {
             int lastPos = tail.lastIndexOf("}");
             if ((lastPos != -1) && (lastPos != (tail.length() -1 )))
             {
               candidates.add(new StringBuilder("{1:").append(tail.substring(0, lastPos + 1)).toString());
             }
             else
             {
                 candidates.add(new StringBuilder("{1:").append(tail).toString());
             }
           }
           
           break;
         }
         
         strBuffer.delete(0, strBuffer.length());

         if (!isStarted)
         {
               strBuffer.append(stepBuffer.toString());
         }
         else
         {
           if (tail != null)
           {
             strBuffer.append(tail);
             tail = null;
           }
         }
         
         stepBuffer.delete(0, stepBuffer.length());

         stepBuffer.append(charBuffer.array(), 0, charBuffer.position());
         strBuffer.append(stepBuffer.toString());
         
         String[] strings = strBuffer.toString().split("\\{1:");
         
         int startIdx = isStarted ? 0 : 1;
         
         if (strings.length > 1)
         {
           isStarted = true;
         }
         
         if (strings.length > startIdx)
         {
           for (int k = startIdx; k < strings.length - 1; k++)
           {
             int lastPos = strings[k].lastIndexOf("}");
             if ((lastPos != -1) && (lastPos != (strings[k].length() -1 )))
             {
               candidates.add(new StringBuilder("{1:").append(strings[k].substring(0, lastPos + 1)).toString());
             }
             else
             {
                 candidates.add(new StringBuilder("{1:").append(strings[k]).toString());
             }
           }
           
           tail = strings[strings.length - 1];
         }   
         
       }
       while (i != -1);
     }
     catch (Exception ex)
     {
       return null;
     }
     finally
     {
       if (reader != null) 
       {      
        try
        {
          reader.close();
        } 
        catch (IOException e)
        {
         // TODO Auto-generated catch block
         e.printStackTrace();
        }
       }
     }
     
     for (int c = 0; c < candidates.size(); c++)
     {
       try
       {
         SwiftParser p = new SwiftParser(candidates.get(c));
         SwiftMessage msg = p.message();
         if (msg != null)
         {
           messages.add(new SwiftMessageWrapper(msg, candidates.get(c)));
         }
       }
       catch (Exception ex)
       {
         ex.printStackTrace();
         return null;
       }
     }

	    return messages;
	  }
	 
	 public static String ProcessDir(String pathName, String filter)
	 {
     StringBuilder result = new StringBuilder();
     result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
     result.append("<messages>\n");
            
	    File folder = new File(pathName);
	    File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) 
	    {
	      if (!listOfFiles[i].isFile()) 
	      {
	        continue;
	      } 
	      
	      result.append(ProcessSwiftMessages(listOfFiles[i].getAbsolutePath(), filter, false));
	    }
	             
      result.append("</messages>\n");

	    return result.toString();
	 }
	 
   public static String ProcessDirWithExt(String pathName, String nameFilter, String msgFilter)
   {
     StringBuilder result = new StringBuilder();
     result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
     result.append("<messages>\n");
            
      File folder = null;
      File[] listOfFiles = null;
      
      try
      {
        folder = new File(pathName);
        listOfFiles = folder.listFiles();  
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }

      for (int i = 0; i < listOfFiles.length; i++) 
      {
        if (!listOfFiles[i].isFile()) 
        {
          continue;
        } 
        
        if (!listOfFiles[i].getName().matches(nameFilter))
        {
          continue;
        }
        
        result.append(ProcessSwiftMessages(listOfFiles[i].getAbsolutePath(), msgFilter, false));
      }
               
      result.append("</messages>\n");

      return result.toString();
   }
	
	 public static String ProcessSwiftMessages(String fileName, String filter, boolean withHeader)
	 {
	   boolean needsFiltering = false;
	   Set<String> filters = new HashSet<String>();
	   
	   if (filter != null)
	   {
	     Collections.addAll(filters, filter.split(","));
	     if (filters.size() > 0)
	     {
	       needsFiltering = true;
	     }
	   }
	   
	   ArrayList<SwiftMessageWrapper> messages = GetMsgFromFile(fileName);
	   if (messages == null)
	   {
	     return null;
	   }
	   
	   StringBuilder result = new StringBuilder();
	   if (withHeader)
	   {
	     result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	     result.append("<messages>\n");
	   }
	   
	   for (int i = 0; i < messages.size(); i++)
	   {
	     SwiftMessageWrapper msg = messages.get(i);
	     if (msg == null)
	     {
	       continue;
	     }
	     
	     if (needsFiltering)
	     {
	       if (!filters.contains(msg.getMessageType()))
	       {
	         continue;
	       }
	     }
	     
	     result.append(msg.getXmlText(false));
	   }
	   
     if (withHeader)
     {	   
	     result.append("</messages>\n");
     }
	   
	   return result.toString();
	 }
	
	private static String Parse(SwiftMessage msg)
	{
		if (msg == null)
		{
			return null;
		}
		
		try 
		{
		  SwiftBlock1 block1 = msg.getBlock1();
		  if (block1 != null)
		  {
		    
		  }
		  
      SwiftBlock2 block2 = msg.getBlock2();
      if (block2 != null)
      {
      
      }
      
      SwiftBlock3 block3 = msg.getBlock3();
      if (block3 != null)
      {
      
      }
      
      SwiftBlock4 block4 = msg.getBlock4();
      if (block4 != null)
      {
      
      }
      
      SwiftBlock5 block5 = msg.getBlock5();
      if (block5 != null)
      {
      
      }
			
    	  StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	  
    	  result.append("<SwiftMessage>");
    	  
		  result.append("<Type>");
		  result.append(msg.getType());
		  System.out.println("Message type: "+msg.getType());
		  result.append("</Type>");
		  
		  result.append("<Blocks>");
		  
			for (int i=1;i<=5;i++) 
			{
				SwiftBlock b = msg.getBlock(i);

				if (b!=null) 
				{
					System.out.println("Block: "+b.getId());
					
					result.append("<Block");
					result.append(i);
					result.append(">");
					
					result.append("<Tags>");
			/*		
					for (Iterator it = b. .tagIterator() ; it.hasNext() ; ) 
					{
						Tag t = (Tag) it.next();
						result.append("<Name>");
						result.append(t.getName());
						result.append("</Name>");
						result.append("<Value>");
						result.append(t.getValue());
						result.append("</Value>");						
						System.out.println("Tag: "+t);
					}
					*/
					result.append("</Tags>");
					
					result.append("</Block");
					result.append(i);
					result.append(">");
				}
			}
			
			result.append("</Blocks>");	
		  
		  result.append("<Body>");
		  result.append(msg.toString());		  
	 	  result.append("</Body>");
	 	  
	 	 result.append("</SwiftMessage>");
	 	  
	 	 return result.toString();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	  return null;
	}

}

