package edu.stanford.smi.protegex.widget.identifier;

import java.util.Collection;

import edu.stanford.smi.protege.model.*;
import edu.stanford.smi.protege.util.CollectionUtilities;
import edu.stanford.smi.protege.util.SystemUtilities;
import edu.stanford.smi.protege.widget.TextFieldWidget;
import java.util.Date;
import java.util.UUID;

/**
 * Copies the value of the system property "user.name" into a string slot.
 *
 * @author Phillip Cheng
 * @version 1.1
 */

public class UBBIdentifierWidget extends TextFieldWidget {
        
	public static boolean isSuitable(Cls cls, Slot slot, Facet facet) {
    	boolean isSuitable;
    	if ((cls == null) || (slot == null)) {
      		isSuitable = false;
      	} else {
      		//boolean isAnyType = cls.getTemplateSlotValueType(slot) == ValueType.ANY;
      		boolean isMultiple = cls.getTemplateSlotAllowsMultipleValues(slot);
      		isSuitable = !isMultiple;
    	}
		return isSuitable;
	}
        
        @Override
	public void setValues(final Collection values) {
          String uniqueId = UUID.randomUUID().toString();
       if(this.getText().isEmpty())
          this.setText(uniqueId);
  	}

       /** @Override
	public void setValues(final Collection values) {
		String userName = (String) CollectionUtilities.getFirstItem(values);
        if (userName == null) {
            userName = SystemUtilities.getUserName();
      		setText(userName);
      		setInstanceValues();
        } else {
            super.setValues(values);
    	}
  	}**/
}
