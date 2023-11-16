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
import java.sql.SQLOutput;
import java.util.Scanner;

//Revisar até absorver tudo! Depois passe para próxima parte
public class PrincipalComBusca {
    //thwrows - Não vamos fazer nada em relação ao erro e quem chamou que será delegada a tarefa.
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();

        //Concatenação para tornar a busca dinâmica
        //Replace trata se caso for um filme com espaçamento, ele apenas junta, pois na url não pode ter espaços
        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=c0e431f9";
        try {
        //Realiza a busca de acordo com o que foi digitado, criamos um client e realizamos uma requisição
        //Criando client para realizar a requisição
        HttpClient client = HttpClient.newHttpClient();
        //Construção da Requisição HTTP
        HttpRequest request = HttpRequest.newBuilder() //Inicia a construção de uma nova requisição HTTP | newBuilder indica que é um objeto complexo, para construir ele, vai ser usado metodos
                .uri(URI.create(endereco)) //Define a URI (Uniform Resource Identifier)
                .build(); //Constrói a requisição HTTP

        //Convertendo o corpo da resposta HTTP em uma string (ofString)
        //Quando colocamos o send, ele lança uma exceção e alguém tem que tratar, na linha 21 usamos o thwrows
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        System.out.println("#################");

        //Extraindo o corpo da resposta
        String json = response.body();
        System.out.println(json);

        //Configurando politica de nomenclatura (pois quando resgatamos o objeto Json, a chave vem em maiscula)
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        //Desserializa o JSON para um objeto Java da classe TituloOmdb
        TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class); //Criamos esse record, só para consumir a API
        System.out.println(meuTituloOmdb);
        //Ele não tem metodos do nosso Titulo, por isso criamos um construtor que recebe o Titulo Omdb para conseguir usarmos os metodos

        //Obs: É padrão criamos um 'objetinho' só para consumir a api, porque o 'objetão' que vc quer ele é mais complexo


            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Titulo convertido:");
            //Quando chamamos o println(x) ele automaticamente chama o toString do objeto x
            System.out.println(meuTitulo);
            //Se acontecer esse tipo de erro, faça isso'
        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na busca, verifique a URL");
        } finally {
            System.out.println("O programa finalizou corretamente!");
        }
    }
}
