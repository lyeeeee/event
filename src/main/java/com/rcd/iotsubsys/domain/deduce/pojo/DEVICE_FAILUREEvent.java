package com.rcd.iotsubsys.domain.deduce.pojo;
import com.rcd.iotsubsys.domain.deduce.pojo.Event;
public class DEVICE_FAILUREEvent implements Event{
	public float failure_value ;
	public int device_no ;
	public int failure_type ;
	public float getFailure_value (){
	return failure_value ;
	}
	public int getDevice_no (){
	return device_no ;
	}
	public int getFailure_type (){
	return failure_type ;
	}
	public void setFailure_value (float failure_value ){
		this.failure_value  = failure_value ;
	}
	public void setDevice_no (int device_no ){
		this.device_no  = device_no ;
	}
	public void setFailure_type (int failure_type ){
		this.failure_type  = failure_type ;
	}
}