package com.vastag.msavaliadorcredito.application.exceptions;

import lombok.Getter;

public class ComunicaoAssincronaEntreServicosException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer status;

	public ComunicaoAssincronaEntreServicosException() {
		super("Falha na comunicação entre microserviços");
	}
	
	public ComunicaoAssincronaEntreServicosException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}
}
