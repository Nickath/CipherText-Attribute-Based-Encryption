package gr.ictabovo.cpabe.privateKeyAuthority;

import generated.javacc.cpabe.policyparser.ParseException;
import gr.ictabovo.cpabe.common.Attributes;
import gr.ictabovo.cpabe.common.PrivateKey;
import it.unisa.dia.gas.jpbc.Element;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import src.main.java.cpabe.AbeInputStream;
import src.main.java.cpabe.AbeOutputStream;
import src.main.java.cpabe.AbePrivateKey;
import src.main.java.cpabe.AbePublicKey;
import src.main.java.cpabe.AbeSecretMasterKey;
import src.main.java.cpabe.Cpabe;
import src.main.java.cpabe.bsw07.Bsw07PrivateKeyComponent;


/**
 *
 * @author nickubuntu
 */
public class PrivateKeyGenerator {

    private static byte[] masterKey;
    private static Attributes attributes = new Attributes();
    private static AbePrivateKey prvkey;
    

 

    public AbePrivateKey getPrvkey() {
        return prvkey;
    }

    public void setPrvkey(AbePrivateKey prvkey) {
        this.prvkey = prvkey;
    }
    
    public PrivateKeyGenerator() {

    }

    public PrivateKeyGenerator(byte[] masterkey) {
        this.masterKey = masterkey;
    }

    public PrivateKeyGenerator(Attributes attributes) {
        this.attributes = attributes;
    }

    public PrivateKeyGenerator(byte[] masterkey, Attributes attributes) {
        this.masterKey = masterkey;
        this.attributes = attributes;
       
    }

    public PrivateKey generatePrivateKey(byte[] masterkey, Attributes attributes) {
        this.masterKey = masterkey;
        this.attributes = attributes;
        try
        {
        return generatePrivateKey();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public PrivateKey generatePrivateKey() throws ParseException {
      AbeSecretMasterKey msk = null;
      try
      {
      msk = retrieveAbeSecretMasterKey(masterKey); //retrieving the AbeSecretMasterKey msk of the API from it's byte[] form
      }
      catch(Exception e) 
      {
          System.out.println(e);
      }
        String[] attributesArray = new String[attributes.getAttributes().size()];//getting the attributes to generate private key
        attributesArray = attributes.getAttributes().toArray(attributesArray);
        String policy = null;
        
        for(String s: attributesArray){
            System.out.println(s);
            policy += s +" ";
        }

       prvkey = Cpabe.keygen(msk,policy);
   //    prvkey = Bsw07.keygen(msk, attributesArray); //AbePrivateKey prvkey is generated from the Bsw07 function of the API 

        return new PrivateKey(exportPrivateKey(prvkey));//returning the prvkey in the form of our class PrivateKey which contains private
        //key in byte[] form
    }

    private byte[] exportPrivateKey(AbePrivateKey prvkey) {
        AbeOutputStream abeStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Element d = prvkey.getD();
            List<Bsw07PrivateKeyComponent> components = prvkey.getComponents();
            AbePublicKey pubKey = prvkey.getPublicKey();
            abeStream = new AbeOutputStream(baos, pubKey);
            pubKey.writeToStream(abeStream);
            abeStream.writeElement(d);
            int compsLength = components.size();
            abeStream.writeInt(compsLength);
            for (Bsw07PrivateKeyComponent component : components) {
                component.writeToStream(abeStream);
                
            }
            return baos.toByteArray();
        } catch (Exception ex) {

        }
        
        return baos.toByteArray();
    }

    //getters & setters for MasterKey , attributes & PrivateKey 
    public Attributes getAttribbutes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

   

    private AbeSecretMasterKey retrieveAbeSecretMasterKey(byte[] msk) throws IOException {
        try (ByteArrayInputStream stream = new ByteArrayInputStream(msk)) {
            return readFromStream(stream);
        }
    }
    
    
    private AbeSecretMasterKey readFromStream(InputStream stream) throws IOException {
     
        
        AbeInputStream abeStream = new AbeInputStream(stream);
        AbePublicKey pubKey = AbePublicKey.readFromStream(abeStream);

        abeStream.setPublicKey(pubKey);

        Element betaIn = (abeStream.readElement());
        Element g_alphaIn = (abeStream.readElement());
        return new AbeSecretMasterKey(pubKey, betaIn, g_alphaIn);
    }
    
    
    
  

}
