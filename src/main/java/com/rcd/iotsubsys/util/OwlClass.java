package com.rcd.iotsubsys.util;

import java.util.HashMap;
import java.util.Map;


public class OwlClass implements java.io.Serializable{
    public String uri;
    public String id; //名字
    //public String label;
    //public String example;
    //public String definition;
    //public String isDefinedBy;
    //public String comment;
    //public Set<String> equivalentClass;
    public OwlClass parentClass;
    public Map<String,equivalent_class> equivalentClass;
    public Map<String,String> annotations;

    public OwlClass() {
        this.uri=null;
        this.id=null;
        this.parentClass=null;
        this.equivalentClass=new HashMap<>();
        this.annotations=new HashMap<>();
    }

    @Override
    public String toString() {
        return this.id;
    }
}
