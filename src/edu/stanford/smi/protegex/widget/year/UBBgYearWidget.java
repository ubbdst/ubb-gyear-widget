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
import javax.xml.datatype.DatatypeConstants;
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
       private String getValidGYear(String yearString){
          String xmlGYear;
          
            try {
                  int inputYear = Integer.parseInt(yearString);
                  XMLGregorianCalendar gCalendar = DatatypeFactory
                          .newInstance()
                          .newXMLGregorianCalendarDate(
                                  inputYear,
                                  DatatypeConstants.FIELD_UNDEFINED, 
                                  DatatypeConstants.FIELD_UNDEFINED, 
                                  DatatypeConstants.FIELD_UNDEFINED
                          );
                  
                  
                  String xmlDate = gCalendar.toXMLFormat();
                  
                  //xmlDate will be in the form of 2020-01-01Z or -0160-01-01Z
                  String [] dateToken = xmlDate.split("-");
                  
                  xmlGYear = dateToken[0];
                  
                  /**
                   * This deals with a situation when a date is preceded by "-" e.g -0160-01-01Z
                   * Note that -0160 is a valid gYear. (160 BC)
                  **/
                  if(dateToken[0].isEmpty()){
                      
                        xmlGYear = "-" + dateToken[1];
                  }
                  
                  //validGYear = Integer.parseInt(xmlGYear);
                  System.out.println("gYear: " + gCalendar.getYear() + "\ngYear full format: " + gCalendar.toXMLFormat());

                } 
            catch (DatatypeConfigurationException ex) {
                  logger.log(Level.SEVERE, ex.getLocalizedMessage());
                  xmlGYear = ""; 
            }
            catch(NumberFormatException nfe){
                 logger.log(Level.SEVERE, "gYear must be a number: {0}", nfe.getLocalizedMessage());
                 xmlGYear = "";
         }
          return xmlGYear;
       }

   /*
    This method is called on the value change. 
    The idea is to validate the input whenever there is a change in value.
    The method returns the current value displayed by the widget.
   */ 
      @Override
      public Collection getValues() {
         //Get the current input value
         String currentSlotValue = getText();
         String gYear = getValidGYear(currentSlotValue);
         
             if(gYear.isEmpty() && currentSlotValue != null){ 
                 
                 getTextField().setForeground(Color.RED);
                 //Display error message
                 JOptionPane.showMessageDialog(
                          null, 
                         "Invalid value for gYear: "  + currentSlotValue,
                         "Invalid Input",
                         JOptionPane.ERROR_MESSAGE);
                 
                 logger.log(Level.SEVERE, "Invalid input for gYear: {0}", currentSlotValue);
                 gYear = null;
             }
             
          return CollectionUtilities.createCollection(gYear);
      }
      
      //A Protege main methord to allow easy debuging
      public static void main(String[] args) {
        edu.stanford.smi.protege.Application.main(args);
      }
   
  
}
               


