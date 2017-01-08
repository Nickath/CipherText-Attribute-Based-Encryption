/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Class related to the Parameters initialization
 * @author Nick Athanasiou
 */
public class SetupParameters {
    
      private final static  DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      private final static  String STRINGS_LOCALE = "US-ASCII";
      private final static  String ELEMENT_HASHING_ALGORITHM = "SHA-1";

      private String curveParams;
      private String type;
      private String q ;
      private String h;
      private String r;
      private String exp2;
      private String exp1;
      private String sign1;
      private String sign0;

    /**
     * Default Constructor
     */
    public SetupParameters()
    {
        
    }
    
    /**
     * Constructor for input parameters initialization
     * @param type String
     * @param q String
     * @param h String
     * @param r String
     * @param exp2 String
     * @param exp1 String
     * @param sign1 String
     * @param sign0 String
     */
    public SetupParameters(String type, String q, String h, String r, String exp2, String exp1, String sign1 , String sign0)
    {
        setType(type);
        setQ(q);
        setH(h);
        setR(r);
        setExp2(exp2);
        setExp1(exp1);
        setSign1(sign1);
        setSign0(sign0);
    }
  
/**
 * getter for curve parameters
 * @return  curveParams String
 */
    public String getCurveParams() {
        return curveParams;
    }
/**
 * Setter for curve parameters
 * @param curveParams  String
 */
    public void setCurveParams(String curveParams) {
        this.curveParams = curveParams;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getExp2() {
        return exp2;
    }

    public void setExp2(String exp2) {
        this.exp2 = exp2;
    }

    public String getExp1() {
        return exp1;
    }

    public void setExp1(String exp1) {
        this.exp1 = exp1;
    }

    public String getSign1() {
        return sign1;
    }

    public void setSign1(String sign1) {
        this.sign1 = sign1;
    }

    public String getSign0() {
        return sign0;
    }

    public void setSign0(String sign0) {
        this.sign0 = sign0;
    }
      /**
       * Creates String format of curve parameters
       * @return String curve parameters
       */
    @Override
    public String toString() {
        
       return "type "+type.trim()+"\nq " +q.trim() +"\nh "+h.trim()+"\nr "+r.trim()+"\nexp2 "+exp2.trim()+"\nexp1 "+exp1.trim()+"\nsign1 "+sign1.trim()+"\nsign0 "+sign0.trim()+"\n";
    }
    
    
    
}

   