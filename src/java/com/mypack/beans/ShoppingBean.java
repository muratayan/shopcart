/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mypack.beans;

import java.util.*;

/**
 *
 * @author murti_000
 */
public class ShoppingBean {
    
    private Collection cart;
    
    /** Creates a new instance of ShoppingBean */

    public ShoppingBean() {
        cart = new ArrayList();
    }
    
    public Collection getCart(){
      return cart;
    }
    
    public void addPizza(PizzaBean bb, int quantity) {
        
        Object newItem[] = null;
        PizzaBean tmpBean = null;

	// if the cart is empty just add the pizza

        if (cart.isEmpty()){
            newItem = new Object[2];
            newItem[0]=bb;
            newItem[1]=new Integer(quantity);    
            cart.add(newItem);
        }

	// otherwise we need to check if this pizza already
	// is in the cart

        else{
	    Iterator iter = cart.iterator();  // get an iterator
	    Object tmpArr[];
	    boolean found = false;
	    while(iter.hasNext()){
		tmpArr=(Object[])iter.next();

		// check if we found the pizza

		if(((PizzaBean)tmpArr[0]).getId()==bb.getId()){ 

		    // yes, increase the quantity

		    Integer tmpAntal = (Integer)tmpArr[1];
		    tmpArr[1]=new Integer(tmpAntal.intValue()+quantity); 
		    found= true;
		}
		
	    }
	    
	    // if we didn't find it, add it
	    
	    if(!found){
		newItem = new Object[2];
		newItem[0]=bb;
		newItem[1]=new Integer(quantity);    
		cart.add(newItem);
		System.out.println("addProduct: cart.size():" + cart.size());
	    }
	    
        }          
    }    
    
    public void removePizza(int id, int quantity) {

	// if must not be empty

        if(!cart.isEmpty()){
            Iterator iter = cart.iterator();
            Object tmpArr[];

	    // search for the book

            while(iter.hasNext()){
                tmpArr=(Object[])iter.next();
                if(((PizzaBean)tmpArr[0]).getId()==id){

		    // found

                    Integer tmpAntal = (Integer)tmpArr[1];

		    // if all copies removed, remove the book
		    // from the cart

                    if(tmpAntal.intValue()<=quantity){
                        iter.remove();
                    }
                    else{

			// else reduce quantity

                        tmpArr[1]=new Integer(tmpAntal.intValue()-quantity);
                    }
                }
            }
        }
    }     
    
    // clear the cart

    public void clear() {
        cart.clear();
    }
    
    
}
