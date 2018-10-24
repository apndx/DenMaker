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
public class MazeBuilderTest {

    public Area testDungeonArea;
    public MazeBuilder testMazeBuilder;
    public RoomBuilder testRoomBuilder;
    public Room testRoomFull;
    public OwnArrayList<Room> testRooms;

    public Logic testLogic = new Logic();

    public MazeBuilderTest() {
        this.testDungeonArea = new Area();
        this.testMazeBuilder = new MazeBuilder(testDungeonArea);
        this.testRoomBuilder = new RoomBuilder(testDungeonArea);
        this.testRoomFull = new Room(5, 5, 1, 1); //height, width, starty, startx
        this.testRooms = new OwnArrayList<>();
        testRooms.add(testRoomFull);
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
    public void startingPointDetectedCorrectly() {

        testDungeonArea.tiles[2][2].setContent(1);
        assertEquals(1, testMazeBuilder.emptyFinder(0, 0).y);
        assertEquals(4, testMazeBuilder.emptyFinder(0, 0).x);
        assertEquals(2, testMazeBuilder.emptyFinder(2, 2).y);
        assertEquals(4, testMazeBuilder.emptyFinder(2, 2).x);

    }

    @Test
    public void buildMazeTest() {
        this.testDungeonArea = new Area(10, 10);
        this.testRoomBuilder.addRooms(testRooms);

        assertEquals(1, testMazeBuilder.build().tiles[1][8].content);
    }

    @Test
    public void deadEndTester() {

        this.testLogic.changeArea(10, 10);

        assertEquals(0, this.testLogic.denArea.tiles[1][6].content);
        assertEquals(0, this.testLogic.denArea.tiles[1][7].content);
        assertEquals(0, this.testLogic.denArea.tiles[1][8].content);
        assertEquals(0, this.testLogic.denArea.tiles[2][7].content);
        assertEquals(0, this.testLogic.denArea.tiles[3][7].content);
        assertEquals(0, this.testLogic.denArea.tiles[3][6].content);

        //room
        this.testLogic.denArea.tiles[1][4].setContent(1);
        this.testLogic.denArea.tiles[1][5].setContent(1);
        this.testLogic.denArea.tiles[2][4].setContent(1);
        this.testLogic.denArea.tiles[2][5].setContent(1);
        this.testLogic.denArea.tiles[3][4].setContent(1);
        this.testLogic.denArea.tiles[3][5].setContent(1);
        //corridor
        this.testLogic.denArea.tiles[1][6].setContent(1);
        this.testLogic.denArea.tiles[1][7].setContent(1);
        this.testLogic.denArea.tiles[1][8].setContent(1);
        this.testLogic.denArea.tiles[2][7].setContent(1);
        this.testLogic.denArea.tiles[3][7].setContent(1);
        this.testLogic.denArea.tiles[3][6].setContent(1);

        assertEquals(1, this.testLogic.denArea.tiles[1][6].content);
        assertEquals(1, this.testLogic.denArea.tiles[1][7].content);
        assertEquals(1, this.testLogic.denArea.tiles[1][8].content);
        assertEquals(1, this.testLogic.denArea.tiles[2][7].content);
        assertEquals(1, this.testLogic.denArea.tiles[3][7].content);
        assertEquals(1, this.testLogic.denArea.tiles[3][6].content);

        
        assertEquals(3, this.testLogic.mazeBuilder.deadEndHelper(this.testLogic.denArea.tiles[1][8]));
        assertEquals(1, this.testLogic.mazeBuilder.deadEndHelper(this.testLogic.denArea.tiles[1][7]));
        assertEquals(2, this.testLogic.mazeBuilder.deadEndHelper(this.testLogic.denArea.tiles[1][6]));

        testMazeBuilder.deadEndTrimmer();
        assertEquals(0, testDungeonArea.tiles[1][8].content);
    }

}
