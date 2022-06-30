package com.vastag.msavaliadorcredito.application.exceptions;

public class ErroSolicitacaoEmissaoCartaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErroSolicitacaoEmissaoCartaoException(String msg) {
		super(msg);
	}
}
