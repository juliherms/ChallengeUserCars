package br.com.b3.controller.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por representar um response do resource de objetos
 * Objetivo desta classe é padronizar o retorno dos erros.
 * @author j.a.vasconcelos
 *
 * @param <T>
 */
public class Response<T> {

	private T data;
	private List<Error> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Error> getErrors() {
		if(this.errors == null) {
			this.errors = new ArrayList<Error>();
		}
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
}
