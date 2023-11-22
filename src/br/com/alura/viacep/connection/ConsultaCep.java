package src.br.com.alura.viacep.connection;

import com.google.gson.Gson;
import src.br.com.alura.viacep.model.ClienteEnderecoViaCep;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep {
    public ClienteEnderecoViaCep buscaEndereco(String cep) {
        URI enderco = URI.create("https://viacep.com.br/ws/" + cep + "/json");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(enderco)
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ClienteEnderecoViaCep.class);
        } catch (Exception e) { //Aqui engloba o ioexception e o interruptedexception
            throw new RuntimeException("Não consegui obter o endereço, a partir desse cep");
        }
    }
}
