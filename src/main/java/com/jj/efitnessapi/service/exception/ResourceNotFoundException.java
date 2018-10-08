package com.jj.efitnessapi.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7932824642135781094L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
