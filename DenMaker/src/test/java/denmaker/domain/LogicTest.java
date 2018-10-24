/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

//import java.util.ArrayList;
import denmaker.datastructures.OwnArrayList;
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

        assertEquals(20, testLogic.denArea.areaHeight);
        assertEquals(30, testLogic.denArea.areaWidth);

    }

    @Test
    public void doesRoomBuildingWorkCorrectly() {

        testLogic.buildRooms(10);
        Tile tileToTest = testLogic.denArea.roomList.get(0).roomWalls.get(0);
        assertEquals(2, tileToTest.content);

    }

    @Test
    public void doesEntranceOpenCorrectly() {

        testLogic.changeArea(10, 10);

        Room testRoomFull = new Room(5, 5, 1, 1);
        OwnArrayList<Room> testRooms = new OwnArrayList<>();
        testRooms.add(testRoomFull);

        testLogic.buildRooms(testRooms);
        testLogic.buildMaze();
        testLogic.getOutOfTheBox();
        testLogic.drawArea();

        assertEquals(20, testLogic.denArea.roomList.get(0).roomWalls.size());

        //tests that getOutOfTheBox changes one of the roomwalls to an entrance
        int count = 0;
        for (int i = 0; i < testLogic.denArea.roomList.get(0).roomWalls.size(); i++) {

            if (testLogic.denArea.roomList.get(0).roomWalls.get(i).content==1) {
                count++;
            }            
        }
       assertEquals(1, count);
        
    }

    @Test
    public void isStringConversionWorking() {

        char uniChar = '\u2588';
        String block = String.valueOf(uniChar); // ascii block

        testLogic.denArea = new Area(10, 10);

        testLogic.denArea.tiles[1][1].content = 1;
        testLogic.denArea.tiles[8][8].content = 1;
        testLogic.denArea.tiles[3][3].content = 2;
        testLogic.denArea.tiles[4][4].content = 2;

        assertEquals(0, testLogic.denArea.tiles[0][0].content);
        assertEquals(0, testLogic.denArea.tiles[1][7].content);
        assertEquals(0, testLogic.denArea.tiles[9][9].content);
        assertEquals(1, testLogic.denArea.tiles[1][1].content);
        assertEquals(1, testLogic.denArea.tiles[8][8].content);
        assertEquals(2, testLogic.denArea.tiles[3][3].content);
        assertEquals(2, testLogic.denArea.tiles[4][4].content);
        
        String[][] testStringDen = testLogic.contentToString();

        assertEquals(block, testStringDen[0][0]);
        assertEquals(block, testStringDen[1][7]);
        assertEquals(block, testStringDen[9][9]);
        assertEquals(" ", testStringDen[1][1]);
        assertEquals(" ", testStringDen[8][8]);
        assertEquals("+", testStringDen[3][3]);
        assertEquals("+", testStringDen[4][4]);

    }

}
