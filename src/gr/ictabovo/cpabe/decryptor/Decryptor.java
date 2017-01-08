/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.decryptor;
 

import it.unisa.dia.gas.jpbc.Element;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import src.main.java.cpabe.AbeEncrypted;
import src.main.java.cpabe.AbeInputStream;
import src.main.java.cpabe.AbePrivateKey;
import src.main.java.cpabe.AbePublicKey;
import src.main.java.cpabe.bsw07.Bsw07PrivateKeyComponent;

/**
 * Class responsible for the Decryption process
 * @author Nick Athanasiou
 */
public class Decryptor {
    
    
  
    /**
     * Decrypt function used for decryption . Takes as parameters the private key of the user who wants to decrypt the information to byte[] form
     * and the encrypted data which the user would like to decrypt into byte[] form too
     *
     * @param privatekeybytes byte[]
     * @param encrypteddata byte[]
     * @return decrypted data (byte[]) or Error Message 
     */
    public byte[] decrypt(byte[] privatekeybytes,byte[] encrypteddata)
    {
        //getting the private key object from the byte[] form
        
        try 
        {
        ByteArrayInputStream byteinput = new ByteArrayInputStream(privatekeybytes);
        AbePrivateKey privateKey = readFromStream(byteinput);

        //getting the public key from the private key via its getter
          AbePublicKey publicKey = privateKey.getPublicKey();

        //getting the AbeEncrypted object from the byte[] form
 
         ByteArrayInputStream bytearrayinput = new ByteArrayInputStream(encrypteddata);
         AbeEncrypted abeencrypted = AbeEncrypted.readFromStream(publicKey,bytearrayinput);
         return decrypt(privateKey,abeencrypted);
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong during the decryption process "+e);
            return encrypteddata;
        }
    }
            
            
           private static byte[] decrypt(AbePrivateKey privateKey, AbeEncrypted encryptedData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
        encryptedData.writeDecryptedData(privateKey, out);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return out.toByteArray();
}
    
    
    
     private static AbePrivateKey readFromStream(InputStream stream) {
        AbeInputStream abeStream = new AbeInputStream(stream);
        try
        {
        AbePublicKey pubKey = AbePublicKey.readFromStream(abeStream);
        abeStream.setPublicKey(pubKey);
        Element d = abeStream.readElement();
        int compsLength = abeStream.readInt();
        ArrayList<Bsw07PrivateKeyComponent> components = new ArrayList<Bsw07PrivateKeyComponent>(compsLength);

        for (int i = 0; i < compsLength; i++) {
            components.add(Bsw07PrivateKeyComponent.readFromStream(abeStream));
        }
        return new AbePrivateKey(d, components, pubKey);
        }
        catch(Exception e)
        {
        return null;
        }
    }
    
     
  
     
}
