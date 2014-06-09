/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mypack.beans;

/**
 *
 * @author murti_000
 */
public class PizzaBean {
    
    private String name;
    private String desc;
    private int id;
    private int ing1;
    private int ing2;
    private int ing3;
    private int ing4;

    public PizzaBean() {
        ing1 = 0;
        ing2 = 0;
        ing3 = 0;
        ing4 = 0;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the ing1
     */
    public int getIng1() {
        return ing1;
    }

    /**
     * @param ing1 the ing1 to set
     */
    public void setIng1(int ing1) {
        this.ing1 = ing1;
    }

    /**
     * @return the ing2
     */
    public int getIng2() {
        return ing2;
    }

    /**
     * @param ing2 the ing2 to set
     */
    public void setIng2(int ing2) {
        this.ing2 = ing2;
    }

    /**
     * @return the ing3
     */
    public int getIng3() {
        return ing3;
    }

    /**
     * @param ing3 the ing3 to set
     */
    public void setIng3(int ing3) {
        this.ing3 = ing3;
    }

    /**
     * @return the ing4
     */
    public int getIng4() {
        return ing4;
    }

    /**
     * @param ing4 the ing4 to set
     */
    public void setIng4(int ing4) {
        this.ing4 = ing4;
    }
    
}
