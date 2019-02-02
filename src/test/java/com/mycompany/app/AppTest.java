package com.mycompany.app;
import java.util.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
public void testFound() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

      int controlSum = 0;
      int expectedSum = 10;

      boolean controlBoolean;

      for(int i = 0; i<array.size(); i++){
       controlSum += array.get(i);
      }
      if(controlSum == expectedSum){
      controlBoolean = true;
      }else{
      controlBoolean = false;
      }

      assertTrue(controlBoolean);
    }

    public void testNotEqual() {
      ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
      
      int controlSum = 0;
      int expectedSum = 20;
      boolean controlBoolean;
      for(int i = 0; i<array.size(); i++){
       controlSum += array.get(i);
      }
      if(controlSum == expectedSum){
      controlBoolean = true;
      }else{
      controlBoolean = false;
      }
      assertFalse(controlBoolean);
    }

    public void testEmptyArray() {
      ArrayList<Integer> array = new ArrayList<>();
      assertFalse(new App().search(array, 1));
    }

    public void testNull() {
      assertFalse(new App().search(null, 1));
    }

}
