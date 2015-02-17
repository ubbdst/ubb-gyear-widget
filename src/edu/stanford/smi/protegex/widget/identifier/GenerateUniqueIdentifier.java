/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.smi.protegex.widget.identifier;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author hru066
 */
public class GenerateUniqueIdentifier {
    
    Date currentDate = new Date(System.currentTimeMillis());
    double randomNumber = Math.random();
    
    /**public static void main(String [] args)
    {
        Date currentDate = new Date(System.currentTimeMillis());
        System.out.println(currentDate.toString());
        double randomNumber = Math.random();
        
        String uniqueString =  randomNumber + currentDate.toString();
        //System.out.println(generateHashString(uniqueString, "SHA1")); 
        System.out.println(UUID.randomUUID());
    }**/
    
     public static String generateHashString(String txt, String hashType) {
        try {     
                    
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
                    byte[] array = md.digest(txt.getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                 }
                    return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
    }

  }
    

