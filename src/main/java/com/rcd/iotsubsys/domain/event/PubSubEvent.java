package com.rcd.iotsubsys.domain.event;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-05-01 17:32
 */
public class PubSubEvent {

    private String deviceName;

    private String dataName;

    private String deviceNameAndDataName;

    private String location;

    private long time;

    private double value;


    public PubSubEvent(String deviceName, String dataName, String location, long time, double value) {
        this.deviceName = deviceName;
        this.dataName = dataName;
        this.location = location;
        this.time = time;
        this.value = value;
        this.deviceNameAndDataName = this.deviceName + this.dataName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDeviceNameAndDataName() {
        return deviceNameAndDataName;
    }

    public void setDeviceNameAndDataName(String deviceNameAndDataName) {
        this.deviceNameAndDataName = deviceNameAndDataName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
