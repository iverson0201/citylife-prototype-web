package com.citylife.trackup.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * 专用于Restful Service的异常.
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月15日 下午1:41:30
 */
@SuppressWarnings("serial")
public class RestException extends RuntimeException {

	public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	public RestException() {
	}

	public RestException(HttpStatus status) {
		this.status = status;
	}

	public RestException(String message) {
		super(message);
	}

	public RestException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
