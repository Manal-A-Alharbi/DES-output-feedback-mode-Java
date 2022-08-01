/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des.ofb;


import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class DESOFB {

    public static byte[] xor_With_Ciphertext (byte[] output , byte[] ciphertext ){
            byte[]  plaintext = new byte[8];
            for (int j = 0; j < ciphertext.length; j++){  
                if (ciphertext[j] != 0){ 
                    plaintext[j] = (byte)  (output[j] ^ ciphertext[j]); 
                }
            }
            System.out.print("your plaintext in byte  :\t ");
            System.out.println(Arrays.toString(plaintext));   
            String stringDec = new String(plaintext);
            System.out.print("your plaintext in String : \t");
            System.out.println(stringDec);  
            
           return plaintext;
     }
     
    public static byte[] xor_With_Plaintext (byte[] output , byte[] subString_byte){
            byte[]  ciphertext = new byte[8];
            for (int j = 0; j < subString_byte.length ; j++){ 
            ciphertext[j] = (byte)  (output[j] ^ subString_byte[j]); 
           }
            System.out.print("your ciphertext in byte  :\t ");
            System.out.println(Arrays.toString(ciphertext));   
            String stringEnc = new String(ciphertext);
            System.out.print("your ciphertext in String : \t");
            System.out.println(stringEnc);   
            
           return ciphertext;
     }
     public static byte[] substring (String text, int index){
        byte[] subString_byte = new byte[8];
        double devidedByEight = text.length()/8.0;
        int numOfRounds =(int) Math.floor(devidedByEight);
        int beginSubstring = index*8;
        
            if (  numOfRounds < index+1  ){
                double numOfChar =  (devidedByEight - numOfRounds)*8; 
                if(text.length()> beginSubstring){ 
                    String subString = text.substring(beginSubstring ,(int)numOfChar + beginSubstring);  
                    System.out.println("\t ___________  After substring  ___________");
                    System.out.println(" in string : "+subString);  
                    subString_byte = subString.getBytes(); 
                    System.out.print(" in byte : "+Arrays.toString(subString_byte)); 
               }
            }else{ 
                    if(text.length()> beginSubstring){  
                        String subString = text.substring(beginSubstring , beginSubstring+8); 
                        System.out.println("\t ___________  After substring  ___________");
                        System.out.println(" in string : "+subString);    
                        subString_byte = subString.getBytes();
                        System.out.print(" in byte : "+Arrays.toString(subString_byte)); 
                    }
                }
            System.out.println("");
        return subString_byte;  
    }
    
    
    public static void main(String[] args) throws Exception {
        
        
            Scanner sc = new Scanner(System.in);  
            System.out.print("Enter your plaintext : "); 
            String text=sc.nextLine();
            byte[] plaintextByte = text.getBytes(); 
            System.out.print("your plaintext in byte : ");
            System.out.println(Arrays.toString(plaintextByte));  
            System.out.println("******************************************************************");
            
            
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            Key key = keyGen.generateKey();
            
            
            SecureRandom random = new SecureRandom();
            byte IV[] = new byte[8];
            // new SecureRandom().nextBytes(IV);
            random.nextBytes(IV);  
             
            Cipher E = Cipher.getInstance("DES/ECB/NoPadding");     
            double devidedByEight = text.length()/8.0; 
            int numOfRounds =(int) Math.floor(devidedByEight); 
            byte[] output = new byte[8];
            
            //          encryption
            for (int i = 0; i <= numOfRounds; i++){  
                System.out.println("");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Round "+(i+1)+" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
                System.out.println("");
                E.init (Cipher.ENCRYPT_MODE, key, random); 
                output = E.doFinal(IV); 
                byte[] subString_byte = substring(text,i);  
                System.out.println("\t ___________ encryption  ___________");
                byte[] ciphertext = xor_With_Plaintext(output,subString_byte); 
                System.out.println("\t ___________  decryption  ___________");
                byte[] plaintext;
                plaintext = xor_With_Ciphertext(output,ciphertext);   
            
           }
        
    }
    
}
