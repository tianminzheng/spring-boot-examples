package com.springboot.async.proxy.api;

import java.util.concurrent.Future;

public interface AsyncResult<T> extends Future<T> {
	 
    Object getResult();
 
}