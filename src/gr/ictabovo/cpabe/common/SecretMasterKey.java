/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.common;

/**
 * Class related to the Secret Master Key
 * @author Nick Athanasiou
 */
public class SecretMasterKey {
    
    private byte[] pubKey;
    private byte[] g_alpha;
    private byte[] beta;
    private byte[] pairingDesc;
    private byte[] g;
    private byte[] h;
    private byte[] f;
    private byte[] e_g_g_hat_alpha;
    private byte[] masterKey;
    
  

    public byte[] getPairingDesc() {
        return pairingDesc;
    }

    public void setPairingDesc(byte[] pairingDesc) {
        this.pairingDesc = pairingDesc;
    }

    public byte[] getG() {
        return g;
    }

    public void setG(byte[] g) {
        this.g = g;
    }

    public byte[] getH() {
        return h;
    }

    public void setH(byte[] h) {
        this.h = h;
    }

    public byte[] getF() {
        return f;
    }

    public void setF(byte[] f) {
        this.f = f;
    }

    public byte[] getE_g_g_hat_alpha() {
        return e_g_g_hat_alpha;
    }

    public void setE_g_g_hat_alpha(byte[] e_g_g_hat_alpha) {
        this.e_g_g_hat_alpha = e_g_g_hat_alpha;
    }

    public byte[] getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(byte[] masterKey) {
        this.masterKey = masterKey;
    }
    

    

    public byte[] getPubKey() {
        return pubKey;
    }

    public void setPubKey(byte[] pubKey) {
        this.pubKey = pubKey;
    }

    public byte[] getG_alpha() {
        return g_alpha;
    }

    public void setG_alpha(byte[] g_alpha) {
        this.g_alpha = g_alpha;
    }

    public byte[] getBeta() {
        return beta;
    }

    public void setBeta(byte[] beta) {
        this.beta = beta;
    }
    
    //constructor
    
    public SecretMasterKey(){
        
    }
    
    public SecretMasterKey(byte[] masterKey)
    {
        this.masterKey = masterKey;
    }
    
    public SecretMasterKey(byte[] pairingDesc,byte[] g, byte[] h, byte[] f, byte[] e_g_g_hat_alpha ,byte[] g_alpha, byte[] beta){

        this.pairingDesc = pairingDesc;
        this.g = g;
        this.h = h;
        this.f =f;
        this.e_g_g_hat_alpha = e_g_g_hat_alpha;
        this.g_alpha = g_alpha;
        this.beta = beta;
    }
    
     public SecretMasterKey(byte[] publickey,byte[] beta, byte[] g_alpha){
        this.pubKey = publickey;
        this.beta = beta;
        this.g_alpha = g_alpha;
     
    }
    
    
    public byte[] getSecretMasterKeyComponents()
    {
        byte[] da =new byte[this.pairingDesc.length + this.g.length];
        System.arraycopy(this.pairingDesc, 0, da, 0, this.pairingDesc.length);
        System.arraycopy(this.g, 0, da, this.pairingDesc.length, this.g.length);
        
         byte[] b =new byte[da.length + this.h.length];
        System.arraycopy(da, 0, b, 0, da.length);
        System.arraycopy(this.h, 0, b, da.length, this.h.length);
        
        byte[] c =new byte[b.length + this.f.length];
        System.arraycopy(b, 0, c, 0, b.length);
        System.arraycopy(this.h, 0, c, b.length, this.f.length);
        
        byte[] d =new byte[c.length + this.e_g_g_hat_alpha.length];
        System.arraycopy(c, 0, d, 0, c.length);
        System.arraycopy(this.e_g_g_hat_alpha, 0, d, c.length, this.e_g_g_hat_alpha.length);
        
        
          byte[] a =new byte[d.length + this.beta.length];
        System.arraycopy(d, 0, a, 0, d.length);
        System.arraycopy(this.beta, 0, a, d.length, this.beta.length);
        
       byte[]   masterkey =new byte[a.length  + this.g_alpha.length];
        System.arraycopy(a, 0, masterkey, 0, a.length);
        System.arraycopy(this.g_alpha, 0, masterkey, a.length, this.g_alpha.length);
        return masterkey;
    }
    
    


    public byte[] getSecretMasterKeyWitPublicKeyPart()
    {
        
        byte[] da =new byte[this.pubKey.length + this.beta.length];
        System.arraycopy(this.pubKey, 0, da, 0, this.pubKey.length);
        System.arraycopy(this.g_alpha, 0, da, this.pubKey.length, this.beta.length);
        
        
        
        byte[]   masterkey =new byte[da.length  + this.g_alpha.length];
        System.arraycopy(da, 0, masterkey, 0, da.length);
        System.arraycopy(this.g_alpha, 0, masterkey, da.length, this.g_alpha.length);
        return masterkey;
    }
    /**
     * Get Secret Master Key in byte array format
     * @return masterKey (byte[])
     */
    public byte[] getSecretMasterKey()
    {
        return masterKey;
    }
}
