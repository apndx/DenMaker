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
public class RoomBuilderTest {

    public Area testDungeonArea;
    public RoomBuilder testRoomBuilder;
    public Room testRoom; //random testroom

    public RoomBuilderTest() {

        this.testDungeonArea = new Area();
        this.testRoomBuilder = new RoomBuilder(testDungeonArea);
        this.testRoom = new Room(testDungeonArea.areaHeight, testDungeonArea.areaWidth);
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
    public void isCollisionDetectedCorrectly() {
        // test cases are borders 
        
        Room testRoomCornerDown = testRoom;
        testRoom.setHeight(5);
        testRoom.setWidth(5);
        testRoom.setStarty(0);
        testRoom.setStartx(0);        
        assertEquals(true, testRoomBuilder.collisionCheck(testRoomCornerDown));
        
        Room testRoomCornerUp = testRoom;
        testRoom.setHeight(5);
        testRoom.setWidth(5);
        testRoom.setStarty(testDungeonArea.areaHeight-5);
        testRoom.setStartx(0);  
        assertEquals(true, testRoomBuilder.collisionCheck(testRoomCornerUp));
        
        Room testRoomMiddle = testRoom;
        testRoom.setHeight(5);
        testRoom.setWidth(5);
        testRoom.setStarty(testDungeonArea.areaHeight-15);
        testRoom.setStartx(15);  
        assertEquals(false, testRoomBuilder.collisionCheck(testRoomMiddle));
        
    }
}
