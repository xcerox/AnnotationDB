package com.help.db.annotacion;

public class AnnotacionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnnotacionException() {
		this("Error en la anotacion.");
	}

	public AnnotacionException(String message)
	{
		super(message);
	}
}
