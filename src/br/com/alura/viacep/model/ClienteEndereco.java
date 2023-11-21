package src.br.com.alura.viacep.model;

import java.util.Scanner;

public class ClienteEndereco {
    private String cep;
    private String requisicaoViaCep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private int id;

    public ClienteEndereco() {
    }

    public void requisicaoApiViaCep() {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite seu cep: ");
        cep = in.nextLine();
        requisicaoViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
        System.out.println("Teste" + requisicaoViaCep);
    }
}
