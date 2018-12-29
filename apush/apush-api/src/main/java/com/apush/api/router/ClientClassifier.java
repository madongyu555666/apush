package com.apush.api.router;

import com.apush.api.spi.router.ClientClassifierFactory;

/**
 * 客户分类器
 * @author madongyu-ds
 *
 */
public interface ClientClassifier {

	 ClientClassifier I = ClientClassifierFactory.create();

	/**
	 * 通过名字获取client的类型 本地，远程
	 * @param osName
	 * @return
	 */
	int getClientType(String osName);
}
