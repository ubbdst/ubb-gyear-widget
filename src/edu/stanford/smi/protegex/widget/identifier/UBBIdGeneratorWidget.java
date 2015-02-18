package edu.stanford.smi.protegex.widget.identifier;

import java.util.Collection;

import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.util.CollectionUtilities;
import edu.stanford.smi.protege.widget.TextFieldWidget;
import java.util.UUID;

/**
 * @author Hemed Ali (hemed.ruwehy@ub.uib.no)
 * @version 1.0
 * @date 18-02-2015
 * 
 * This class generates Universal Unique Identifier (UUID version 4) of the instance (in the protege slot) if it does not have one from before.
 */

public class UBBIdGeneratorWidget extends TextFieldWidget {
        
	public static boolean isSuitable(Cls cls, Slot slot, Facet facet) {
          
            //check if a slot accept values of type String, 
           // if it does then show this widget in the dropdown list as one of the it's options.
           boolean isString = cls.getTemplateSlotValueType(slot) == ValueType.STRING;

           return isString;
	}
        
       /**
        * Set value of the slot if it does not have one.
        * @param values
        **/
      @Override
        public void setValues(final Collection values) {
              String uniqueId = (String)CollectionUtilities.getFirstItem(values);
              
              if (uniqueId == null){
                  
                //generate unique Id
                uniqueId = UUID.randomUUID().toString();
                setText(uniqueId);
                //getTextField().setEnabled(false);
                setInstanceValues();
              }
           else 
             super.setValues(values);
        }
        
}
