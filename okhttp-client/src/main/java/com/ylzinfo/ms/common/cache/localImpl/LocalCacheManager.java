package com.ylzinfo.ms.common.cache.localImpl;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ylzinfo.ms.common.cache.Cache;
import com.ylzinfo.ms.common.cache.CacheException;
import com.ylzinfo.ms.common.cache.CacheManager;

/**
 * 
* @author xuyajie 
* @date 2017年8月17日 下午5:20:51 
* 本地缓存管理接口，获取指定名称下的缓存Cache
 */
public class LocalCacheManager implements CacheManager{
	
	private  ConcurrentMap<String,Cache<Serializable,Serializable>> caches = new ConcurrentHashMap<String,Cache<Serializable,Serializable>>();

	/**
	 * 获取指定名称的缓存，当这个缓存不存在时，将会创建一个缓存并返回。
	 *
	 * @param name
	 *            需要获取缓存的名称
	 * @return 指定名称的缓存
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	@SuppressWarnings("unchecked")
	public  Cache<?, ?> getCache(String name) throws CacheException{
		return caches.putIfAbsent(name, new LocalCache<Serializable, Serializable>());
	}
}
