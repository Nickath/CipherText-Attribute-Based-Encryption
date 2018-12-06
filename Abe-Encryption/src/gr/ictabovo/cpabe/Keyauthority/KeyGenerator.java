/*
 * 
 *
 * 
 * 
 * 
 * 
 */
package gr.ictabovo.cpabe.Keyauthority;

import gr.ictabovo.cpabe.cryptotools.SetupParametersGenerator;
import gr.ictabovo.cpabe.common.PublicKey;
import gr.ictabovo.cpabe.common.SecretMasterKey;
import gr.ictabovo.cpabe.common.SetupParameters;
import gr.ictabovo.cpabe.cryptotools.CPABE;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import src.main.java.cpabe.AbeOutputStream;
import src.main.java.cpabe.AbePublicKey;
import src.main.java.cpabe.AbeSecretMasterKey;

/**
 * This Class is responsible for the Key Generation of the Public Master Key and the Secret Master Key
 * Public Key is used for the encryption process and Master Key is used for the Private Key Generation 
 * of the users.
 *
 * @author Nick Athanasiou
 */
public class KeyGenerator {

    private static AbeSecretMasterKey msk;
    private static AbePublicKey pubkey;
    InputStream inputStream;

    private SetupParameters setupParameters = null;

   
/** Default Constructor  */
    public KeyGenerator() {
        this.setupParameters = null;

    }
/**  Constructor with SetupParameters object as variable
     * @param setupParameters */
    public KeyGenerator(SetupParameters setupParameters) {
        this.setupParameters = setupParameters;
    }

    /**
     * Generates a SecretMasterKey object using the SetupParameters object as parameter, first checks if the setupParameters object is null, if it is,
     * it chooses the default ones.
     * @return MasterKey object
     * @throws IOException */
     public SecretMasterKey generateMasterKey() throws IOException
    {
        if (this.setupParameters == null) {
            SetupParametersGenerator setupParametersGenerator = new SetupParametersGenerator();
            this.setupParameters = setupParametersGenerator.generateParameters();
        }

        return generateMasterKey(setupParameters);
    }
    
    /**
     * Generates and returns the SecretMasterKey object 
     * @param params SetupParameters
     * @return Secret Master Key object
     * @throws IOException
     */
    public SecretMasterKey generateMasterKey(SetupParameters params) throws IOException {
        //take the SetuParameters object and convert it to String format
        String flatParameters = params.toString();

        CPABE cpabestp = new CPABE();//CPABE class extends the Cpabe class of JCPABElib
        AbeSecretMasterKey masterKey = cpabestp.setup(flatParameters);//object masterkey of Class AbeSecretMasterKey is calculated from the Bsw07.setup()
        msk = masterKey; //store Secret Master Key in static variable of Class AbeSecretMasterKey msk
        pubkey = msk.getPublicKey();
        SecretMasterKey secretmasterkey = new SecretMasterKey(getAsByteArrayMasterKey());
        return secretmasterkey;
        
    }

    /**
     * Generates and returns the PublicKey object 
     * @return PublicKey Object
     * @throws IOException
     */
    public PublicKey generatePublicKey() throws IOException
    {
        if (this.setupParameters == null) {
            SetupParametersGenerator setupParametersGenerator = new SetupParametersGenerator();
            this.setupParameters = setupParametersGenerator.generateParameters();
        }

        return generatePublicKey(setupParameters);
    }
    
    /**
     * Generates the PublicKey object using the SetupParameters object . To generate the Public Key you should have created the Secret Master Key before 
     * @param params Object
     * @return
     * @throws IOException
     */
    public PublicKey generatePublicKey(SetupParameters params) throws IOException
    {
       
        pubkey = msk.getPublicKey();//store it into pubkey variable
      //  PublicKey publickey = new PublicKey(getAsByteArrayPairingDesc(),getAsByteArrayG(),getAsByteArrayH(),getAsByteArrayF(),getAsByteArrayE_g_g_hat_alpha());
          PublicKey publickey = new PublicKey(getAsByteArrayPublicKey());
        return publickey;
    }

    //Stream functions to derive the byte array form of AbeSecretMaster Key and Public Key objects
    
    
    //for the master key
   
    //for g_alpha
      private void writeToStreamG_alpha(AbeOutputStream stream) throws IOException {
        stream.writeElement(msk.g_alpha);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayG_alpha() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamG_alpha(baos);
        return baos.toByteArray();
    }

    private void writeToStreamG_alpha(OutputStream stream) throws IOException {
        writeToStreamG_alpha(new AbeOutputStream(stream, pubkey));
    }
    
    //for beta
    
      private void writeToStreamBeta(AbeOutputStream stream) throws IOException {
        stream.writeElement(msk.beta);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayBeta() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamBeta(baos);
        return baos.toByteArray();
    }

    private void writeToStreamBeta(OutputStream stream) throws IOException {
        writeToStreamBeta(new AbeOutputStream(stream, pubkey));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //for the public key
  
    //for g Element
    
      private void writeToStreamG(AbeOutputStream stream) throws IOException {
        stream.writeElement(pubkey.g);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayG() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamG(baos);
        return baos.toByteArray();
    }

    private void writeToStreamG(OutputStream stream) throws IOException {
        writeToStreamG(new AbeOutputStream(stream, pubkey));
    }
    
   //for h Element
    
    
    
      private void writeToStreamH(AbeOutputStream stream) throws IOException {
        stream.writeElement(pubkey.h);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayH() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamH(baos);
        return baos.toByteArray();
    }

    private void writeToStreamH(OutputStream stream) throws IOException {
        writeToStreamG(new AbeOutputStream(stream, pubkey));
    }
    
    
    //for f Element
    
    
      private void writeToStreamF(AbeOutputStream stream) throws IOException {
        stream.writeElement(pubkey.f);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayF() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamG(baos);
        return baos.toByteArray();
    }

    private void writeToStreamF(OutputStream stream) throws IOException {
        writeToStreamF(new AbeOutputStream(stream, pubkey));
    }
    
    
    //for e_g_g_hat_alpha Element
    
    
      private void writeToStreamE_g_g_hat_alpha(AbeOutputStream stream) throws IOException {
        stream.writeElement(pubkey.e_g_g_hat_alpha);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayE_g_g_hat_alpha() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamG(baos);
        return baos.toByteArray();
    }

    private void writeToStreamE_g_g_hat_alpha(OutputStream stream) throws IOException {
        writeToStreamE_g_g_hat_alpha(new AbeOutputStream(stream, pubkey));
    }
    
    //for pairingDesc
    
      
      private void writeToStreamPairingDesc(AbeOutputStream stream) throws IOException {
        stream.writeString(pubkey.getPairingDescription());//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
    }

    private byte[] getAsByteArrayPairingDesc() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamPairingDesc(baos);
        return baos.toByteArray();
    }

    private void writeToStreamPairingDesc(OutputStream stream) throws IOException {
        writeToStreamPairingDesc(new AbeOutputStream(stream, pubkey));
    }
    
    
  private void writeToStreamPublicKey(OutputStream stream) throws IOException {
        writeToStreamPublicKey(new AbeOutputStream(stream, pubkey));
    }

    private void writeToStreamPublicKey(AbeOutputStream stream) throws IOException {
        pubkey.writeToStream(stream);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
     
    }

    private byte[] getAsByteArrayPublicKey() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamPublicKey(baos);
        return baos.toByteArray();
    }
    //getters for the Object Keys
    private AbeSecretMasterKey getMSKobj() {
        return msk;
    }

    private AbePublicKey getPubKey() {
        return pubkey;
    }
    
    
    private void writeToStreamMasterKey(OutputStream stream) throws IOException {
        writeToStreamMasterKey(new AbeOutputStream(stream,pubkey));
    }

    private void writeToStreamMasterKey(AbeOutputStream stream) throws IOException {
        pubkey.writeToStream(stream);
        stream.writeElement(msk.beta);
        stream.writeElement(msk.g_alpha);//paei stin AbePublicKey kai kalountai oi sunartiseis pou grafoun sto stream tis AbeOutputStream
     
    }

    private byte[] getAsByteArrayMasterKey() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.writeToStreamMasterKey(baos);
        return baos.toByteArray();
    }
     
  
}   
