package br.com.b3.controller.response;

import java.io.Serializable;

/**
 * Metodo responsavel por representar uma mensagem de erro no sistema.
 * 
 * @author j.a.vasconcelos
 *
 */
public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Integer errorCode;

	public Error(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

}
