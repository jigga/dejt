/*
 * Copyright 2011 Telekomunikacja Polska S.A. All rights reserved.
 * Telekomunikacja Polska S.A. PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */
package com.dejt.common.spi.orange;

public class LocationOutput {

    
@Override
    public String toString() {
	return "LocationOutput [latitude=" + latitude + ", longitude="
		+ longitude + ", timestamp=" + timestamp + "]";
    }

    //    Enum required in parsing Json output message returned from getLocation service
    public enum FieldsNames {

	LATITUDE("latitude"), LONGITUDE("longitude"), TIMESTAMP("timestamp");

	String suffix;

	FieldsNames(String suffix) {
	    this.suffix = suffix;
	}

	@Override
	public String toString() {
	    return suffix;
	}
    }

    private float latitude;

    private float longitude;

    private String timestamp;

    public float getLatitude() {
	return latitude;
    }

    public void setLatitude(float latitude) {
	this.latitude = latitude;
    }

    public void setField(String value, FieldsNames name) {
	if (name == FieldsNames.LATITUDE || name == FieldsNames.LONGITUDE) {
	    float val = Float.valueOf(value);
	    if (name == FieldsNames.LATITUDE) {
		setLatitude(val);
	    }
	    if (name == FieldsNames.LONGITUDE) {
		setLongitude(val);
	    }
	}
	setTimestamp(value);
    }

    public float getLongitude() {
	return longitude;
    }

    public void setLongitude(float longitude) {
	this.longitude = longitude;
    }

    public String getTimestamp() {
	return timestamp;
    }

    public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
    }

}
