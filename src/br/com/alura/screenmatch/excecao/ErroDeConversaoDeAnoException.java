package br.com.alura.screenmatch.excecao;

//RuntimeException é uma exceção unchecked exception (exceção não verificada)
public class ErroDeConversaoDeAnoException extends RuntimeException {
    private String mensagem;
    public ErroDeConversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
