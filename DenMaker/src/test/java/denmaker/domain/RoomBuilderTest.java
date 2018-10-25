/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.domain;

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
public class RoomBuilderTest {

    public Area testDungeonArea;
    public RoomBuilder testRoomBuilder;
    public Room randomTestRoom; //random testroom
    public Room testRoomCornerDown;
    public Room testRoomCornerDownOther;
    public Room testRoomCornerUp;
    public Room testRoomMiddle1;
    public Room testRoomMiddle2;
    public OwnArrayList<Room> rooms;

    public RoomBuilderTest() {

        this.testDungeonArea = new Area();
        this.testRoomBuilder = new RoomBuilder(testDungeonArea);
        this.randomTestRoom = new Room(testDungeonArea.areaHeight, testDungeonArea.areaWidth);
        this.testRoomCornerDown = new Room(5, 5, 0, 0);
        this.testRoomCornerDownOther = new Room(5, 5, testDungeonArea.areaHeight, testDungeonArea.areaWidth);
        this.testRoomCornerUp = new Room(5, 5, testDungeonArea.areaHeight - 5, 0);
        this.testRoomMiddle1 = new Room(5, 5, testDungeonArea.areaHeight - 15, 15);
        this.testRoomMiddle2 = new Room(5, 5, testDungeonArea.areaHeight - 15, 15);
        this.rooms = new OwnArrayList<>();
        rooms.add(testRoomCornerDown);
        rooms.add(testRoomCornerUp);
        rooms.add(testRoomMiddle1);
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
    //
    /**
     * Tests if the room collision is detected
     */
    @Test
    public void isCollisionDetectedCorrectly() {

        assertEquals(true, testRoomBuilder.collisionCheck(testRoomCornerDown));
        assertEquals(true, testRoomBuilder.collisionCheck(testRoomCornerDownOther));
        assertEquals(true, testRoomBuilder.collisionCheck(testRoomCornerUp));
        assertEquals(false, testRoomBuilder.collisionCheck(testRoomMiddle1));

    }

    @Test
    public void isRoomAddedCorrectly() {

        testRoomBuilder.addRooms(rooms);
        
        assertEquals(true, testRoomBuilder.collisionCheck(testRoomMiddle1));
        assertEquals(0, testDungeonArea.tiles[0][0].content);
        assertEquals(0, testDungeonArea.tiles[testDungeonArea.areaHeight - 5][0].content); 
        assertEquals(1, testDungeonArea.tiles[testDungeonArea.areaHeight - 15][15].content);
        assertEquals(2, testDungeonArea.tiles[testDungeonArea.areaHeight - 16][15].content); 
        assertEquals(0, testDungeonArea.tiles[testDungeonArea.areaHeight - 17][15].content); 

    }
    
    @Test
    public void hashTest() {
        assertEquals(testRoomMiddle1.hashCode(), testRoomMiddle2.hashCode());
    }
    
    
}
