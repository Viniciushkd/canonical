package br.com.project.dto;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data;
	
	public T getData() {
		return data;
	}

	public ResponseDto(final T data) {
		this.data = data;
	}
	
}
