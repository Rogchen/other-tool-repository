package com.ylzinfo.ms.common.cache.localImpl;

import java.io.Serializable;

/**
 * 
* @author xuyajie 
* @date 2017年8月17日 下午4:42:54 
* 缓存中的值包装类
 */
public class ValueWrapper<K extends Serializable, V extends Serializable>{
	
	private long expiredTime = -1;//过期时间（以服务器时间为准）
	
	private K key;//缓存的key
	
	private V value;//缓存的值
	
	public ValueWrapper(){
		super();
	}
	
	public ValueWrapper(K key, V value){
		super();
		this.key = key;
		this.value = value;
	}
	public ValueWrapper(K key, V value, long expiredTime){
		super();
		this.key = key;
		this.value = value;
		this.expiredTime = expiredTime;
	}
	/**
	 * 
	* @date 2017年8月17日 下午4:55:54 
	* @author xuyajie 
* 该值是否有过期时间
	 */
	public boolean isExpired(){
		return expiredTime > 0;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	
}