package edu.stanford.smi.protegex.widget.year;

import java.util.Collection;

import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.util.CollectionUtilities;
import edu.stanford.smi.protege.widget.NumberFieldWidget;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author  Hemed Ali (hemed.ruwehy@uib.no)
 * @version  1.0
 * @since   04-11-2015
 * <br />
 * A Slot Widget for validating gYear.
 */

public class UBBgYearWidget extends NumberFieldWidget{
    
  static final Logger logger = Logger.getLogger(UBBgYearWidget.class.getName());
  
   //Initialize the widget
    @Override
        public void initialize() {
        super.initialize();
        getTextField().setHorizontalAlignment(SwingConstants.RIGHT);
        setPreferredColumns(1);
        setPreferredRows(1);
    }
        
	public static boolean isSuitable(Cls cls, Slot slot, Facet facet) {
            
            //check if a slot accept values of type String, 
           // if it does then show this widget in the dropdown list as one of the it's options.
           boolean isInt = cls.getTemplateSlotValueType(slot) == ValueType.INTEGER;
           
           return true;
	}
        
       /*
         I haven't found an easy way to simply validate a gYear, instead I am trying
         to create a fake date based on the input year, then parse it to 
         GregorianCalendar. If the process fail, then that means the year was not valid.
        */
       private boolean isValidGYear(String yearString){
          boolean isValid = true;
            try {
                  int year = Integer.parseInt(yearString);
                  XMLGregorianCalendar gCalendar = DatatypeFactory
                          .newInstance()
                          .newXMLGregorianCalendarDate(year , 1, 1, 0);
                  
                  //String[] xmlGYear = gCalendar.toXMLFormat().trim().split("-");
                  //System.out.println("gYear: " + xmlGYear[0];  
                  //+ "\ngYear full format: " + gCalendar.toXMLFormat());

                } 
            catch (DatatypeConfigurationException ex) {
                  logger.log(Level.SEVERE, ex.getLocalizedMessage());
                  isValid = false;
            }
            catch(NumberFormatException nfe){
                 logger.log(Level.SEVERE, "gYear must be a number: {0}", nfe.getLocalizedMessage());
                 isValid = false;
         }
          return isValid;
       }

   /*
    This method is called on the value change. 
    The idea is to validate the input whenever there is a change in value.
    The method returns the current value displayed by the widget.
   */ 
      @Override
      public Collection getValues() {
         //Get the current value displayed in the widget
         String currentSlotValue = getText();
         
             if(currentSlotValue != null && !isValidGYear(currentSlotValue)){  
                 
                 getTextField().setForeground(Color.RED);
                 //Display error message
                 JOptionPane.showMessageDialog(
                          null, 
                         "Invalid value for gYear: "  + currentSlotValue,
                         "Invalid Input",
                         JOptionPane.ERROR_MESSAGE);
                 
                 logger.log(Level.SEVERE, "Invalid input for gYear: {0}", currentSlotValue);
                 currentSlotValue = null;
             }
             
          return CollectionUtilities.createCollection(currentSlotValue);
      }
      
      //A Protege main methord to allow easy debuging
      public static void main(String[] args) {
        edu.stanford.smi.protege.Application.main(args);
      }
   
  
}
               


