package com.apush.api.spi.router;

import com.apush.api.router.ClientClassifier;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
 * 实例化spi的实现类，并调用了1.8的新特性，Supplier 的get方法，没次创建一个新的对象，及时加载（客户分类器工厂）
 * @author madongyu-ds
 *
 */
public interface ClientClassifierFactory extends Factory<ClientClassifier>{

	/**
	 * 由工厂生产 client的产生器
	 * @return
	 */
	static ClientClassifier create() {
        return SpiLoader.load(ClientClassifierFactory.class).get();
    }
	
}
