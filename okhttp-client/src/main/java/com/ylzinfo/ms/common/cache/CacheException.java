package com.ylzinfo.ms.common.cache;

/**
 * 
 * @description: 缓存异常，定义缓存操作时抛出的异常
 * @date: 2015年12月23日 下午6:39:29
 * @author: Xu Yajie
 */
@SuppressWarnings("serial")
public class CacheException extends RuntimeException {

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 */
	public CacheException() {
		super();
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param message
	 *            抛出异常的原因.
	 */
	public CacheException(String message) {
		super(message);
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param cause
	 *            抛出异常的底层原因.
	 */
	public CacheException(Throwable cause) {
		super(cause);
	}

	/**
	 * 创建一个新的 <code>CacheException</code>.
	 *
	 * @param message
	 *            抛出异常的原因.
	 * @param cause
	 *            抛出异常的底层原因.
	 */
	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}
}
