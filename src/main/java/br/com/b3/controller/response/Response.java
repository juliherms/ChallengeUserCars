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
	private List<String> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		if(this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
