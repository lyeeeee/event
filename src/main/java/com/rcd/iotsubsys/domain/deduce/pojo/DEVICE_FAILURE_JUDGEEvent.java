package com.rcd.iotsubsys.domain.deduce.pojo;
import com.rcd.iotsubsys.domain.deduce.pojo.Event;
public class DEVICE_FAILURE_JUDGEEvent implements Event{
	public String name ;
	public String getName (){
	return name ;
	}
	public void setName (String name ){
		this.name  = name ;
	}
}