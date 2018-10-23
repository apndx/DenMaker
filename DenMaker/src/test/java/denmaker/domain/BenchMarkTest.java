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
public class BenchMarkTest {

    public Logic testLogic;

    public BenchMarkTest() {
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
        this.testLogic = new Logic();
        this.testLogic.benchmark = new Benchmark(this.testLogic);
    }

    @Test
    public void doHelperMethodsWorkCorrectly() {
        this.testLogic.benchmark = new Benchmark(this.testLogic);
        this.testLogic.benchmark.testLooper(2, 2, 100, 100); // roomattempts, testruns, height, width
        this.testLogic.benchmark.averageCounter(2);          // testruns
        assertEquals(2, testLogic.benchmark.testResults.size());    
        assertEquals(0.0, (testLogic.benchmark.averagePerformance.get(0)/2), 2); 
    }

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
