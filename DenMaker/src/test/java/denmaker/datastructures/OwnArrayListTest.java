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
    public Tile testTile2;

    public OwnArrayListTest() {
        this.testList = new OwnArrayList<>();
        this.testTile = new Tile(0, 0, 0, null);
        this.testTile2 = new Tile(0, 0, 0, testTile);

        for (int i = 0; i < 1000; i++) {
            testList.add(testTile);
        }

        for (int i = 0; i < 910; i++) {
            testList.remove(testTile);
        }

        assertEquals(90, testList.size());
        assertEquals(910, testList.removeCount());
        assertEquals(910, testList.valuesLenght());
        assertTrue(testList.includes(testTile2));
        assertEquals(testTile2, testList.get(testList.valueIndex(testTile2)));

        testList.remove(testTile2);
        assertFalse(testList.includes(testTile2));

        if (!testList.includes(testTile2)) {
            testList.add(testTile2);
        }

    }
}
