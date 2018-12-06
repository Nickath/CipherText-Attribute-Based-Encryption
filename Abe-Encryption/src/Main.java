import gr.ictabovo.cpabe.Keyauthority.KeyGenerator;
import gr.ictabovo.cpabe.common.*;
import gr.ictabovo.cpabe.cryptotools.SetupParametersGenerator;
import gr.ictabovo.cpabe.decryptor.Decryptor;
import gr.ictabovo.cpabe.encryptor.Encryptor;
import gr.ictabovo.cpabe.privateKeyAuthority.PrivateKeyGenerator;
import src.main.java.cpabe.AbeInputStream;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private String attributes = "A B";
    private static String policy = "(  (A AND (B OR C )  ) )";
    private static InputStream input;

    public static void main(String[] args) {


        try{
            input = new AbeInputStream(new FileInputStream("/home/nickubuntu/IdeaProjects/Abe-Encryption/files/simiosis asfalia.pdf"));
            OutputStream output = new FileOutputStream("/home/nickubuntu/IdeaProjects/Abe-Encryption/files/simiosis asfalia.pdf.enc");

            //setting default parameters
            SetupParameters params = new SetupParameters();
            SetupParametersGenerator setupParametersGenerator = new SetupParametersGenerator();
            SetupParameters setupParameters = setupParametersGenerator.generateParameters();

            System.out.println(setupParameters.toString());

            KeyGenerator keyGenerator = new KeyGenerator();

            SecretMasterKey masterKey = keyGenerator.generateMasterKey(setupParameters);

            PublicKey publicKey = keyGenerator.generatePublicKey();

            //byte[] masterkeybytes = masterKey.getSecretMasterKeyWitPublicKeyPart();
            byte[] masterkeybytes = masterKey.getSecretMasterKey();
            byte[] publickeybytes = publicKey.getPublicKey();


            System.out.println(masterkeybytes.length);
            System.out.println(publickeybytes.length);

            //generate the private key
            Attributes attributes = new Attributes();
            String[] attributesString = {"A,B"};
            attributes.setAttributes(new ArrayList<String>(Arrays.asList(attributesString)));

            PrivateKeyGenerator privateKeyGenerator = new PrivateKeyGenerator();

            PrivateKey privateKey = privateKeyGenerator.generatePrivateKey(masterkeybytes, attributes);
            byte[] privatekeybytes = privateKey.getPrivatekey();

            System.out.println(privatekeybytes.length);



            Encryptor encryptor = new Encryptor();
            File file = new File("/home/nickubuntu/IdeaProjects/Abe-Encryption/files/simiosis asfalia.pdf");
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            //encrypt the data

            byte[] encryptedData = encryptor.encrypt(publickeybytes,policy,fileBytes);

            System.out.println("Encrypted data length is " + encryptedData.length);


            //decrypt
            Decryptor decryptor = new Decryptor();

            byte[] decryptedData = decryptor.decrypt(privatekeybytes,encryptedData);

            System.out.println("Decrypted data lenght is " + decryptedData.length);


            //write the data
            try (FileOutputStream fos = new FileOutputStream("/home/nickubuntu/IdeaProjects/Abe-Encryption/files/simiosis asfalia.pdf.pdf")) {
                fos.write(decryptedData);
                //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
            }


        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
