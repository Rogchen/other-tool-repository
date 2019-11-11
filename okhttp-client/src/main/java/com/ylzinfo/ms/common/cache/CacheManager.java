package com.ylzinfo.ms.common.cache;

import java.io.Serializable;

/**
 * 
 * @description: 缓存管理接口，获取指定名称下的缓存Cache
 * @date: 2015年12月23日 下午6:39:13
 * @author: Xu Yajie
 */
public interface CacheManager {

	/**
	 * 获取指定名称的缓存，当这个缓存不存在时，将会创建一个缓存并返回。
	 *
	 * @param name
	 *            需要获取缓存的名称
	 * @return 指定名称的缓存
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public <K extends Serializable, V extends Serializable> Cache<K, V> getCache(String name) throws CacheException;
}
