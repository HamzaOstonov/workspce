package com.is.tf.sign;

import org.w3c.dom.Attr;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Node;

import com.is.ISLogger;
import com.is.utils.CheckNull;

import javax.xml.soap.SOAPElement;

import java.nio.charset.Charset;
import java.util.Iterator;

public class Canonicalizer {
    public static String toText(SOAPElement element) {

        StringBuilder buffer = new StringBuilder();
        appendElement(buffer, element.getChildElements());
        //System.out.println("!!!"+element.toString());
        //System.out.println("!!!"+buffer.toString());
        return buffer.toString();
    }

    private static void appendElement(StringBuilder buffer, Iterator i) {
        while (i.hasNext()) {
        	Node node = (Node) i.next();
        	//SOAPElement element = (SOAPElement) node;
        	node.normalize();
        	buffer.append(node.toString());
        	/*
        	Node node = (Node) i.next();
            if (node.getNodeType() == Node.TEXT_NODE)
                buffer.append(((CharacterData) node).getData());

            else if (node instanceof SOAPElement) {
                SOAPElement element = (SOAPElement) node;
                String tag = element.getTagName();
                buffer.append("<").append(tag);
                Iterator i1 = element.getNamespacePrefixes();
                while (i1.hasNext()) {
                    String prefix = (String) i1.next();
                    buffer.append(prefix);
                    ISLogger.getLogger().warn(prefix);
                }
                
                buffer.append(">");
                appendElement(buffer, element.getChildElements());
                buffer.append("</").append(tag).append(">");
            }
            */
        }
    }
}
