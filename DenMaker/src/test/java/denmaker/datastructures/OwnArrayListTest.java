/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.datastructures;

import denmaker.domain.Tile;
import static org.junit.Assert.*;

/**
 *
 * @author apndx
 */
public class OwnArrayListTest {

    public OwnArrayList<Tile> testList;
    public Tile testTile;

    public OwnArrayListTest() {
        this.testList = new OwnArrayList<>();
        Tile testTile = new Tile(0, 0, 0, null);

        for (int i = 0; i < 1000; i++) {
            testList.add(testTile);
        }
        
        for (int i =0; i< 910; i++) {
            testList.remove(testTile);           
        }    
        
        assertEquals(90, testList.size());
        assertEquals(910, testList.removeCount());
        assertEquals(910, testList.valuesLenght());

    }
}
