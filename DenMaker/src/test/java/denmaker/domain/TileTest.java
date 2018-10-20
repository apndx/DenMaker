/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author apndx
 */
public class TileTest {

    public Tile block1;
    public Tile block2;
    public Tile empty;
    public Tile wall;

    public TileTest() {
        this.block1 = new Tile(0, 0, 0, null);
        this.block2 = new Tile(0, 0, 0, null);
        this.empty = new Tile(1, 1, 1, null);
        this.wall = new Tile(2, 2, 2, null);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hashTest() {

        assertEquals(block1.hashCode(), block2.hashCode());

    }

}
