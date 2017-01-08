/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.common;

/**
 * Class related to the Private Key 
 * @author Nick Athanasiou
 */
public class PrivateKey {

    
    private byte[] privatekey;
    private byte[] components;
    private byte[] publickey;
    private byte[] d;
    
    
    
    public byte[] getD() {
        return d;
    }

    public void setD(byte[] d) {
        this.d = d;
    }
    
    
    public byte[] getComponents() {
        return components;
    }

    public void setComponents(byte[] components) {
        this.components = components;
    }

    public byte[] getPublickey() {
        return publickey;
    }

    public void setPublickey(byte[] publickey) {
        this.publickey = publickey;
    }
    
    
    public PrivateKey(byte[] privatekey)
    {
        this.privatekey = privatekey;
    }

    
    public PrivateKey() 
    {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public byte[] getPrivatekey()
    {
        return privatekey;
    }

    public void setPrivatekey(byte[] privatekey) 
    {
        this.privatekey = privatekey;
    }
    
    
    
    
    public PrivateKey(byte[] d , byte[] components, byte[] publickey)
    {
        this.d = d;
        this.components = components;
        this.publickey = publickey;
    }
    
    
    
    /**
     * Get the Private Key in byte array format
     * @return privatekey (byte[])
     */
    public byte[] getPrivateKey()
    {
        byte[] a =new byte[this.d.length + this.components.length];
        System.arraycopy(this.d, 0, a, 0, this.d.length);
        System.arraycopy(this.components, 0, a, this.d.length, this.components.length);
        
         byte[] privatekey =new byte[a.length + this.publickey.length];
        System.arraycopy(a, 0, privatekey, 0, a.length);
        System.arraycopy(this.publickey, 0, privatekey, a.length, this.publickey.length);
        
        return privatekey;
    }
  
}
