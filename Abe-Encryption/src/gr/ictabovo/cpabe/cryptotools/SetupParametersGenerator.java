/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.cryptotools;

import gr.ictabovo.cpabe.common.SetupParameters;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class responsible for the Initialization of the Setup Parameters needed in CP-ABE to generate the Public and the Secret Master Keys
 * @author Nick Athanasiou
 */
public class SetupParametersGenerator {
    
    private SetupParameters setupParameters;
/**
 * Default Constructor
 * @return setupParameters
 */
    public SetupParameters getSetupParameters() {
        return setupParameters;
    }
/**
 * Gets setupParameters object given as input and assign it to the member variable
 * @param setupParameters 
 */
    public void setSetupParameters(SetupParameters setupParameters) {
        this.setupParameters = setupParameters;
    }
    /**
     * Responsible for the default SetupParameters generation in case the user hasn't give an input for them
     * @return SetupParameters object
     * @throws FileNotFoundException 
     */
    public SetupParameters generateParameters() throws FileNotFoundException
    {
        Properties setupParametersProperties = new Properties();
        
        SetupParameters  setupParameters = new SetupParameters(); 
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        
        
        try {
            InputStream input =  SetupParametersGenerator.class.getClassLoader().getResourceAsStream("gr/ictabovo/cpabe/cryptotools/defaultSetupParameters.properties");
            setupParametersProperties.load(input);
        }
        catch(Exception e){
            System.out.println("Properties file not found");
        }
        
        setupParameters.setType(setupParametersProperties.getProperty("type"));
        setupParameters.setQ(setupParametersProperties.getProperty("q"));
        setupParameters.setH(setupParametersProperties.getProperty("h"));
        setupParameters.setR(setupParametersProperties.getProperty("r"));
        setupParameters.setExp2(setupParametersProperties.getProperty("exp2"));
        setupParameters.setExp1(setupParametersProperties.getProperty("exp1"));
        setupParameters.setSign1(setupParametersProperties.getProperty("sign1"));
        setupParameters.setSign0(setupParametersProperties.getProperty("sign0"));
 
        return setupParameters;
    }
    
}
