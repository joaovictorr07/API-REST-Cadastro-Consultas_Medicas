package com.teste.demo.responses;

import java.util.ArrayList;
import java.util.List;

public class Responsivo<T> {

	private T data;
	private List<String> errors;

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
		return this.errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
