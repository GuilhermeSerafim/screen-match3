package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    //thwrows - Não vamos fazer nada e quem chamou que será delegada a tarefa.
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();

        String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=c0e431f9";

        //Criando client para realizar a requisição
        HttpClient client = HttpClient.newHttpClient();
        //Requisição do cliente
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        //Resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        System.out.println("#################");

        //Atribuindo em uma variavel o response body
        String json = response.body();
        System.out.println(json);


        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(meuTituloOmdb);

        Titulo meuTitulo = new Titulo(meuTituloOmdb);
        System.out.println("Titulo convertido:");
        System.out.println(meuTitulo);

    }
}
