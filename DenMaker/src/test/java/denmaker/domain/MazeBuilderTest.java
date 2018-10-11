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

    public MazeBuilderTest() {
        this.testDungeonArea = new Area();
        this.testMazeBuilder = new MazeBuilder(testDungeonArea);
        this.testRoomBuilder = new RoomBuilder(testDungeonArea);
        this.testRoomFull = new Room(5, 5, 1, 1);
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

        testDungeonArea.tiles[2][2].setContent(" ");
        assertEquals(4, testMazeBuilder.emptyFinder(0, 0).x);
        assertEquals(2, testMazeBuilder.emptyFinder(2, 2).y);
        assertEquals(4, testMazeBuilder.emptyFinder(2, 2).x);

    }

    @Test
    public void buildMazeTest() {
        testDungeonArea = new Area(10, 10);
        testRoomBuilder.addRooms(testRooms);

        assertEquals(" ", testMazeBuilder.build().tiles[1][8].content);

    }

}
