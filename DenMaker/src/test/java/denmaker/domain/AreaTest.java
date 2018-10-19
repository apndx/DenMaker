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

/**
 *
 * @author apndx
 */
public class AreaTest {

    public Area testDenArea;

    public AreaTest() {

        this.testDenArea = new Area();

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
    public void entranceOpenerCornerCases() {
        // add room
        this.testDenArea.tiles[1][1].content = 1;
        this.testDenArea.tiles[1][2].content = 1;
        this.testDenArea.tiles[2][1].content = 1;
        this.testDenArea.tiles[2][2].content = 1;
        Room testRoom = new Room(2, 2, 1, 1);
        this.testDenArea.roomList.add(testRoom);
        // add "corridor"
        this.testDenArea.tiles[3][3].content = 1;

        this.testDenArea.outOfTheBox();

        assertTrue(this.testDenArea.tiles[3][2].content == 1 || this.testDenArea.tiles[2][3].content == 1);

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
