package com.rcd.iotsubsys.domain.event;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-05-01 11:41
 */
public class Valid {
    /**
     * 事件或目标限制的id
     * */
    private long id;

    /**
     *  原子事件id
     * */
    private long metaEventId;

    /**
     *  属性名
     * */
    private String attrName;

    private String comparator;

    private double value;

    private double anotherValue;

    public Valid(long id, String comparator, double value, long metaEventId, String attrName) {
        this.id = id;
        this.comparator = comparator;
        this.value = value;
        this.metaEventId = metaEventId;
        this.attrName = attrName;
    }

    public Valid(long id, String comparator, double value, double anotherValue, long metaEventId, String attrName) {
        this.id = id;
        this.comparator = comparator;
        this.value = value;
        this.anotherValue = anotherValue;
        this.metaEventId = metaEventId;
        this.attrName = attrName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getAnotherValue() {
        return anotherValue;
    }

    public void setAnotherValue(double anotherValue) {
        this.anotherValue = anotherValue;
    }

    public long getMetaEventId() {
        return metaEventId;
    }

    public void setMetaEventId(long metaEventId) {
        this.metaEventId = metaEventId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public boolean valid(double value) {
        if (comparator.equals("0")) {
            return value < this.value;
        } else if (comparator.equals("1")) {
            return value <= this.value;
        } else if (comparator.equals("2")) {
            return value == this.value;
        } else if (comparator.equals("3")) {
            return value >= this.value;
        } else if (comparator.equals("4")) {
            return value > this.value;
        } else if (comparator.equals("5")) {
            return value > this.value && value < this.anotherValue;
        } else if (comparator.equals("6")) {
            return value != this.value;
        } else if (comparator.equals("7")) {
            return value >= this.value && value <= this.anotherValue;
        }
        return false;
    }
}
