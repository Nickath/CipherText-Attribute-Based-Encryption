/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.encryptor;

import it.unisa.dia.gas.jpbc.Element;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import src.main.java.cpabe.AbeEncrypted;
import src.main.java.cpabe.AbeInputStream;
import src.main.java.cpabe.AbePublicKey;
import src.main.java.cpabe.Cpabe;



/**
 * Class responsible for the Encryption process
 * @author Nick Athanasiou
 */
public class Encryptor {
    
    private static AbeEncrypted abeencrypted;

    /**
     * Default constructor
     */
    public Encryptor(){
      
    }

    /**
     * reads the Public Key input Stream form and recreates from it the AbePublicKey object of JCPABE API
     * @param stream InputStream
     * @return AbePublicKey object
     */
    private static AbePublicKey readFromStream(InputStream stream) {
        AbePublicKey pubKey = null;
        AbeInputStream abeStream = new AbeInputStream(stream);
        try  
        {
        pubKey = AbePublicKey.readFromStream(abeStream);
        return pubKey;
        }
        catch(Exception e)
        {
            
        }
        return pubKey;
        
    }
    /**
     * Reads the pairing description which is a component of the AbePublicKey 
     * @param stream AbeInputStream
     * @return AbePublicKey object
     */
    private static AbePublicKey readPairingDescription(AbeInputStream stream)
    {
        AbePublicKey publicKey =null;
        try
        {
        String pairingDescription = stream.readString();
        publicKey = new AbePublicKey(pairingDescription);
        }
        catch(Exception e)
        {
        System.out.println(e);    
        }
        return publicKey;
    }
    
    private static Element readElementG(AbeInputStream stream,AbePublicKey publicKey) 
    {
         try
         {
         publicKey.g = stream.readElement();
         }
         catch(IOException e)
         {
             System.out.println(e);
         }
         return publicKey.g;
    }
    
     private static Element readElementH(AbeInputStream stream,AbePublicKey publicKey)
    {
         try
         {
         publicKey.h = stream.readElement();
         }
         catch(IOException e)
         {
             System.out.println(e);
         }
         return publicKey.h;
    }
     
      private static Element readElementF(AbeInputStream stream,AbePublicKey publicKey)
    {
        try
        {
         publicKey.f = stream.readElement();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
         return publicKey.f;
    }
      
       private static Element readElementE_g_g_hat_alpha(AbeInputStream stream,AbePublicKey publicKey)
    {
        try
        {
         publicKey.e_g_g_hat_alpha = stream.readElement();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
         return publicKey.e_g_g_hat_alpha;
    }
     
    

     private static AbePublicKey readFromStream(AbeInputStream stream)  {
         AbePublicKey publicKey =null;
         try
         {
         String pairingDescription = stream.readString();
        
         publicKey = new AbePublicKey(pairingDescription);
         stream.setPublicKey(publicKey);
         publicKey.g = stream.readElement();
         publicKey.h = stream.readElement();
         publicKey.f = stream.readElement();
         publicKey.e_g_g_hat_alpha = stream.readElement();
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
         return publicKey;
}

    /**
     * Function that encrypts the information. Takes as parameters the public key in byte[] form, the String object Policy with which we would like to encrypt the data and the data 
     * we want to encrypt into byte[] form too
     * @param publickeybytes byte[]
     * @param policy String
     * @param data byte[]
     * @return encrypted information (byte[])
     */
    public byte[] encrypt(byte[]publickeybytes , String policy , byte[] data) 
    {   
        //getting the masterkeybytes to derive the publickeyobject
        InputStream publickeyinput =new ByteArrayInputStream(publickeybytes);
        AbePublicKey publickeyobj = readFromStream(publickeyinput);
         ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] encrypteddata;
       //getting the abeencrypted object from the encryptor
       try       
       {
         abeencrypted = Cpabe.encrypt(publickeyobj,policy,data);
         abeencrypted.writeEncryptedData(out, publickeyobj);
       }
       catch(Exception e)
       {
           System.out.println("Something went wrong during the encrypt process"+e);
       }
         encrypteddata = out.toByteArray(); //getting the encrypted data to byte[] form

         return encrypteddata;
    }
      
    
}
