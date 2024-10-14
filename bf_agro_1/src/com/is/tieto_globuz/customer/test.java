// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

public class test
{
    public static void main(final String[] args) {
        final String str = "aacdeaa";
        System.out.println("string = " + str);
        final CharSequence s1 = "cde";
        final CharSequence s2 = "ghi";
        final String replaceStr = str.replace(s1, s2);
        System.out.println("new string = " + str.replace(s1, s2));
    }
}
