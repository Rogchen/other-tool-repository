package com.ylzinfo.ms.common.cache.localImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ylzinfo.ms.common.cache.Cache;
import com.ylzinfo.ms.common.cache.CacheException;
/**
 * 
* @author xuyajie 
* @date 2017年8月17日 下午5:20:18 
* 本地缓存的实现
 */
public class LocalCache<K extends Serializable, V extends Serializable> implements Cache<K, V>{

	private ConcurrentMap<K,ValueWrapper<K,V>> cache = new ConcurrentHashMap<K, ValueWrapper<K,V>>();
	
	private int expireCheckIntervalSeconds = 5;//过期值检查间隔
	
	//定期检查过期的key
	public LocalCache(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Set<K> keySet = cache.keySet();
				for(K key : keySet){
					ValueWrapper<K,V> wrapper = cache.get(key);
					if(wrapper.isExpired()){
						cache.remove(key);
					}
				}
			}
		}, 0, expireCheckIntervalSeconds * 1000);
	}
	
	@Override
	public V get(K key) throws CacheException {
		ValueWrapper<K,V> valueWrapper = cache.get(key);
		if(valueWrapper ==null){
			return null;
		}
		if(valueWrapper.isExpired()){
			//如果该值设置了过期时间并已过期，则从缓存中移除
			long currentTime = System.currentTimeMillis();
			if(currentTime > valueWrapper.getExpiredTime()){
				remove(key);
				return null;
			}
		}
		return valueWrapper.getValue();
	}

	@Override
	public V put(K key, V value) throws CacheException {
		ValueWrapper<K,V> valueWrapper = new ValueWrapper<K, V>(key, value);
		cache.put(key, valueWrapper);
		return value;
	}

	@Override
	public V put(K key, V value, long expire) throws CacheException {
		long expiredTime = System.currentTimeMillis() + expire * 1000;//计算过期时间
		ValueWrapper<K,V> valueWrapper = new ValueWrapper<K, V>(key, value, expiredTime);
		cache.put(key, valueWrapper);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		ValueWrapper<K,V> valueWrapper = cache.remove(key);
		if(valueWrapper == null){
			return null;
		}
		return valueWrapper.getValue();
	}

	@Override
	public void clear() throws CacheException {
		cache.clear();
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public Collection<V> values() {
		Collection<ValueWrapper<K,V>> wrappers = cache.values();
		Collection<V> values = new ArrayList<V>();
		for(ValueWrapper<K,V> wrapper : wrappers){
			values.add(wrapper.getValue());
		}
		return values;
	}

	@Override
	public void flushDB() {
		clear();
	}

	public int getExpireCheckIntervalSeconds() {
		return expireCheckIntervalSeconds;
	}

	public void setExpireCheckIntervalSeconds(int expireCheckIntervalSeconds) {
		this.expireCheckIntervalSeconds = expireCheckIntervalSeconds;
	}

	
}