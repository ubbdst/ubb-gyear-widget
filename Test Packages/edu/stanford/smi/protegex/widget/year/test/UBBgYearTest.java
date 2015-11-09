package edu.stanford.smi.protegex.widget.year.test;


/**
 * @author Hemed
 */

import edu.stanford.smi.protegex.widget.year.UBBgYearWidget;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class UBBgYearTest  extends TestCase {
    
    @Test
    public void testGYear() throws ClassNotFoundException, InstantiationException, 
            IllegalAccessException, NoSuchMethodException,
            IllegalArgumentException, InvocationTargetException{
        
            UBBgYearWidget mockObject = mock(UBBgYearWidget.class);
            Object testObj = Class.forName(UBBgYearWidget.class.getName())
                    .newInstance();
 
            Method method = testObj.getClass()
                    .getDeclaredMethod("isValidGYear" , String.class);
            method.setAccessible(true);
            
            assertTrue((Boolean)method.invoke(mockObject, "12004"));
            assertEquals(false, method.invoke(mockObject , "2000-01"));
            assertEquals(false, method.invoke(mockObject , "hemed"));
            assertEquals(true, method.invoke(mockObject , "2023"));
   
    }
    
    
      
}
