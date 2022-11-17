package br.com.five.desafio.threads.exception;

public class SemCombustivelException extends RuntimeException{

    private static final long serialVersionUID = 1L;    
    public SemCombustivelException() {
        
        super("Sem combustível");
    }

    
}
