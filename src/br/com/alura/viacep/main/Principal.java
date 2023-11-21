package src.br.com.alura.viacep.main;

import src.br.com.alura.viacep.model.ClienteEndereco;

public class Principal {
    public static void main(String[] args) {
        ClienteEndereco x = new ClienteEndereco();
        x.requisicaoApiViaCep();
    }
}
