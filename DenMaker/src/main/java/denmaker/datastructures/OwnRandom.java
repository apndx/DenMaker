/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.datastructures;

/**
 *
 * @author apndx
 */
public class OwnRandom {

    public long mod;
    public double randomNow;
    public double randomLast;

    public OwnRandom() {
        this.mod = System.currentTimeMillis();
        this.randomLast = 0.9;
        this.randomNow = (this.randomLast * 252149039) % this.mod /10.0;
        
        
    }



}
