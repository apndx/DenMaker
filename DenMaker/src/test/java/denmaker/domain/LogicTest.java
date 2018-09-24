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
public class LogicTest {

    public Logic testLogic;

    public LogicTest() {

        this.testLogic = new Logic();

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void doesAreaChangeCorrectly() {

        testLogic.changeArea(20, 30);

        assertEquals(20, testLogic.dungeonArea.areaHeight);
        assertEquals(30, testLogic.dungeonArea.areaWidth);

    }

    @Test
    public void doesRoomBuildingWorkCorrectly() {

        testLogic.buildRooms(10);
        Tile tileToTest = testLogic.dungeonArea.roomList.get(0).roomWalls.get(0);
        assertEquals("+", tileToTest.content);

    }

    @Test
    public void doesBuildMazeWorkCorrectly() {

        //logic.buildMaze();
    }

}
