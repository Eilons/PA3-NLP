package com.Technion.ie.exception;

import java.io.Serializable;

public class KeyNotFound extends Exception {
	
	private static final long serialVersionUID = -6742782840457184597L;
	public KeyNotFound (String message)
	{
		super (message);
	}

}
