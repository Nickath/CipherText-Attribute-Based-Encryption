/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.cryptotools;

import it.unisa.dia.gas.jpbc.Element;
import src.main.java.cpabe.AbePublicKey;
import src.main.java.cpabe.AbeSecretMasterKey;
import src.main.java.cpabe.bsw07.Bsw07;

/**
 * Extends the Bsw07 of the JCPABElib and is responsible for the AbeSecretMasterKey object generation
 * @author Nick Athanasiou
 */
public class CpabePrime extends Bsw07 {
    
    public static AbeSecretMasterKey setup(String curveParams) {
        AbePublicKey pub = new AbePublicKey(curveParams);
        Element g = pub.getPairing().getG1().newRandomElement();
        Element alpha = pub.getPairing().getZr().newRandomElement();
        Element beta = pub.getPairing().getZr().newRandomElement();
        Element beta_inv = beta.duplicate().invert();

        Element h = g.duplicate().powZn(beta);
        Element f = g.duplicate().powZn(beta_inv);
        Element g_hat_alpha = g.duplicate().powZn(alpha);
        Element e_g_g_hat_alpha = pub.getPairing().pairing(g, g_hat_alpha);
        pub.setElements(g, h, f, e_g_g_hat_alpha);
        return new AbeSecretMasterKey(pub, beta, g_hat_alpha);
    }
    
}
