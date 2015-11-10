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
                    .getDeclaredMethod("getValidGYear" , String.class);
            method.setAccessible(true);
            
            //assertEquals("12004", method.invoke(mockObject, "1998"));
            assertEquals("0099" , method.invoke(mockObject , "1002001"));
            assertEquals("0100", method.invoke(mockObject , "10000000"));
            assertEquals("-0203", method.invoke(mockObject , "-203"));
   
    }
    
    
      
}
