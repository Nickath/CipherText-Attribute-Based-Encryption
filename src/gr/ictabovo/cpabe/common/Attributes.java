/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.ictabovo.cpabe.common;

import java.util.ArrayList;


/**
 *
 * @author Nick Athanasiou
 */
public class Attributes {
    
    private static ArrayList<String> attributes = new ArrayList<String>();
    private String attributesstring;
    /**
     * Getter for the Attributes
     * @return ArrayList attributes
     */
    public ArrayList<String> getAttributes() {
        return attributes;
    }

    /**
     * Setter for the Attributes, sets value to the Attributes ArrayList
     * @param attributes (ArrayList())
     */
    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
     //   System.out.println(attributes);
    }
    
    //TO DO : toString() method, addAttribute() method , remove() method , empty() method
    
    /**
     * Converts the ArrayList attributes object to String object
     * @param attributes
     * @return String attributes
     */
    public String toString(ArrayList<String> attributes)
    {
        for(String s:attributes)
        {
            attributesstring += s+" ";
        }
        return attributesstring;
    }
    /**
     * Add attribute as input 
     * @param attr (String)
     */
    public void addAttribute(String attr)
    {
        this.attributes.add(attr);
    }
    
    /**
     * Removes attribute 
     * @param index (int)
     */
    public void removeAttribute(int index)
    {
        this.attributes.remove(index);
    }
    
    /**
     * Empties the ArrayList of attributes of a user
     */
    public void empty()
    {
        this.attributes.clear();
    }
}
