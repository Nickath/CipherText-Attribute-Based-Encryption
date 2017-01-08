/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.common;



/**
 * Class related to te Public Key
 * @author Nick Athanasiou
 */
public class PublicKey {
    
    
      
    private byte[] pairingDesc;
    private byte[] g;
    private byte[] h;
    private byte[] f;
    private byte[] e_g_g_hat_alpha;
    private byte[] publicKey;
    
     public byte[] getPublicKeyGetter() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

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
  
    
    
    public PublicKey()
    {
        
    }
    
    public PublicKey(byte[] pairingDesc,byte[] g, byte[] h , byte[] f, byte[] e_g_g_hat_alpha)
    {
        this.pairingDesc = pairingDesc;
        this.g = g;
        this.h = h;
        this.f = f;
        this.e_g_g_hat_alpha = e_g_g_hat_alpha;
    }
    
    public PublicKey(byte[] publicKey)
    {
        this.publicKey = publicKey;
    }
    
    //getter gia na paroume olo to kleidi se byte array morfi
    
    public byte[] getPublicKeyComponents()
    {
        byte[] a =new byte[this.pairingDesc.length + this.g.length];
        System.arraycopy(this.pairingDesc, 0, a, 0, this.pairingDesc.length);
        System.arraycopy(this.g, 0, a, this.pairingDesc.length, this.g.length);
        
         byte[] b =new byte[a.length + this.h.length];
        System.arraycopy(a, 0, b, 0, a.length);
        System.arraycopy(this.h, 0, b, a.length, this.h.length);
        
        byte[] c =new byte[b.length + this.f.length];
        System.arraycopy(b, 0, c, 0, b.length);
        System.arraycopy(this.h, 0, c, b.length, this.f.length);
        
        byte[] d =new byte[c.length + this.e_g_g_hat_alpha.length];
        System.arraycopy(c, 0, d, 0, c.length);
        System.arraycopy(this.e_g_g_hat_alpha, 0, d, c.length, this.e_g_g_hat_alpha.length);
        
        return d;
    }
     /**
      * Get the public Key in byte array form 
      * @return public key (bye[] )
      */
    public byte[] getPublicKey()
    {
        return publicKey;
    }
 
}
