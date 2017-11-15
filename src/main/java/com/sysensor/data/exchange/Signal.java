package com.sysensor.data.exchange;

public class Signal {
    public String TYPE;
    public boolean STATUS;
    public String LOCATION;

    @Override
    public String toString() {
        return "{" + TYPE + "," + STATUS + "," + LOCATION + "}";
    }
}
