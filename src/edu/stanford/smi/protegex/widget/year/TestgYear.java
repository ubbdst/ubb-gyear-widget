
package edu.stanford.smi.protegex.widget.year;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Hemed
 */
public class TestgYear {
    
    
    public static void main(String[] args) {
        
        System.out.println(isValidGYear(10));
    }
            
         /*
         I haven't found an easy way to simply validate a gYear, instead I am trying
         to create a fake date based on the input year, then parse it to 
         GregorianCalendar. If the process fail, then that means the year was not valid.
        */
       private static boolean isValidGYear(int year){
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          String fakeDateString = year + "-01-01";
          boolean isValid = true;
           
              try {
                  Date fd = formatter.parse(fakeDateString);
                  GregorianCalendar gCalender = new GregorianCalendar();
                  gCalender.setTime(fd);
                  XMLGregorianCalendar gd = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalender);

              } catch (ParseException ex) {
                  Logger.getLogger(UBBgYearWidget.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                  isValid = false;
              } catch (DatatypeConfigurationException ex) {
                  Logger.getLogger(UBBgYearWidget.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
                  isValid = false;
              }
          return isValid;
       }
    
}
