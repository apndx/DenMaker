/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

/**
 *
 * @author apndx
 */
public class Tile {
    
    public String content; // # wall, " " empty, @ door
    public int weight; // 0-100 random weight
  
    
    public void Tile(String content, int weight) {
        this.content = content;
        this.weight = weight;
    }
    
    
    
    
}
