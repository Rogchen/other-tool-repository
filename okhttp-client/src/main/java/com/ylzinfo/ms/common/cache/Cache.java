package com.ylzinfo.ms.common.cache;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @description: 缓存有效地存储临时对象，以提高一个应用的性能 本系统定义一个缓存的接口，用来扩展现有的缓存框架，比如 JCache, Ehcache, JCS,
 * OSCache, JBossCache, TerraCotta, Coherence, GigaSpaces等。
 * @date: 2015年12月23日 下午6:40:16
 * @author: Xu Yajie
 */
public interface Cache<K extends Serializable, V extends Serializable> {

	/**
	 * 获取缓存中指定key下的值，当不存在时，返回为空
	 *
	 * @param 在设置缓存时指定的key
	 * @return 缓存的对象或者为空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public V get(K key) throws CacheException;

	/**
	 * 添加一个缓存实体
	 *
	 * @param key
	 *            用来标识放入缓存的实体
	 * @param value
	 *            存放缓存中实体的值
	 * @return 返回这个key下，之前存放的实体，当不存在时，返回空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public V put(K key, V value) throws CacheException;

	/**
	 * 添加一个缓存实体
	 *
	 * @param key
	 *            用来标识放入缓存的实体
	 * @param value
	 *            存放缓存中实体的值
	 * @param expire
	 *            过期时间（秒）
	 * @return 返回这个key下，之前存放的实体，当不存在时，返回空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public V put(K key, V value, long expire) throws CacheException;

	/**
	 * 从缓存中，移除指定key的实体
	 *
	 * @param 在添加缓存时设置的key
	 * @return 返回这个key下，之前存放的实体，当不存在时，返回空
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public V remove(K key) throws CacheException;

	/**
	 * 清空缓存
	 *
	 * @throws 当访问出现问题时，抛出CacheException异常
	 */
	public void clear() throws CacheException;

	/**
	 * 返回在缓存中，存放实体的个数
	 *
	 * @return 在缓存中，存放实体的个数
	 */
	public int size();

	/**
	 * 返回在缓存中，存放的所有实体
	 *
	 * @return 在缓存中，存放的所有实体
	 */
	public Collection<V> values();

	/**
	 * 刷新缓存系统
	 */
	public void flushDB();
}
