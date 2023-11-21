package src.br.com.alura.viacep.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteEndereco {
    private String cep;
    private String requisicaoViaCep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public ClienteEndereco() {
    }

    public ClienteEndereco(ClienteEnderecoViaCep viaCep) {
        this.cep = viaCep.cep();
        this.logradouro = viaCep.logradouro();
        this.bairro = viaCep.bairro();
        this.localidade = viaCep.localidade();
        this.uf = viaCep.uf();
    }

    public String requisicaoApiViaCep(String cep) throws IOException, InterruptedException {
        requisicaoViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
        System.out.println("Teste" + requisicaoViaCep);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requisicaoViaCep))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            String extraindoResposta = response.body();
            return extraindoResposta;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na busca, verifique a url");
        }
        return "Erro ao buscar o cep";
    }

    @Override
    public String toString() {
        return "Cep: " + cep + "\n" +
               "Logradouro: " + logradouro  + "\n" +
               "Bairro: " + bairro  + "\n" +
               "Cidade: " + localidade  + "\n" +
               "UF: " + uf  + "\n";

    }
}
