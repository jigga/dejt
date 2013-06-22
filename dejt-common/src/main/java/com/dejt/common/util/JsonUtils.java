/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.util;

import com.dejt.common.spi.orange.LocationOutput;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

public class JsonUtils {

    /**
     * Sets a JSON field, but only if the value is not {@literal null}.
     */
    public static void setJsonField(Map<Object, Object> json, String field,
	    Object value) {
	if (value != null) {
	    json.put(field, value);
	}
    }
    
    /**
     * returns LocationOutput on the basis of Jnode object returned by getLocation
     */

    public static LocationOutput printAll(JsonNode node,LocationOutput location) {
	
	Iterator<String> fieldNames = node.getFieldNames();
	while (fieldNames.hasNext()) {
	    String fieldName = fieldNames.next();
	    JsonNode fieldValue = node.get(fieldName);
	    if (fieldValue.isObject()) {
		// System.out.println("object field " + fieldName + " :");
		printAll(fieldValue,location);
	    } else {
		String value = fieldValue.asText();
		LocationOutput.FieldsNames[] objectValues = LocationOutput.FieldsNames
			.values();
		for (int i = 0; i < objectValues.length; i++) {
		    if (fieldName.equals(objectValues[i].toString())) {
			location.setField(value, objectValues[i]);
		    }
		}
		// System.out.println("field " + fieldName + " : " + value);
	    }
	}
	return location;
    }

}
