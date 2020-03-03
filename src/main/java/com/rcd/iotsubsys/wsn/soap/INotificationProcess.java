package com.rcd.iotsubsys.wsn.soap;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * <b>function</b>: WSN调用处理通知消息服务的接口
 * @author 柴兆航
 * @version 1.0
 *
 */
@WebService(targetNamespace = "bupt.zht.pubsub.soap",name = "INotificationProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface INotificationProcess{
	public String notificationProcess(@WebParam(partName = "Wsn", name = "WsnProcess",
        targetNamespace = "http://edu.bupt.wangfu.module.wsnMgr.util.soap") String notification);
}
