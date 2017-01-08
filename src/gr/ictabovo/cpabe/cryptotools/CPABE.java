/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.cryptotools;

import src.main.java.cpabe.AbeSecretMasterKey;
import src.main.java.cpabe.Cpabe;


/**
 * Extends the Cpabe of the TU-Berlin JCPABE API and is responsible for the AbeSecretMasterKey object generation
 * @author Nick Athanasiou
 */
public class CPABE  extends Cpabe{
    
    public static AbeSecretMasterKey setup(String params) {
        return CpabePrime.setup(params);
    }
    
}
