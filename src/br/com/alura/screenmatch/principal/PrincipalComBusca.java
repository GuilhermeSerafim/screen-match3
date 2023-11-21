package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Essa classe foi aulas práticas, tem mais comentario, logo, facilita o entendimento

//Revisar até absorver tudo! Depois passe para próxima parte
public class PrincipalComBusca {
    //thwrows - Não vamos fazer nada em relação ao erro e quem chamou que será delegada a tarefa.
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();
        //Configurando política de nomenclatura (pois quando resgatamos o objeto Json, a chave vem em maiscula)
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() //Facilitar leitura do JSON
                .create();


        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para busca, ou sair para encerrar a busca: ");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")) {
                break;
            }

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

                //Desserializa o JSON para um objeto Java da classe TituloOmdb
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class); //Criamos esse record, só para consumir a API
                System.out.println(meuTituloOmdb);
                //Ele não tem metodos do nosso Titulo, por isso criamos um construtor que recebe o Titulo Omdb para conseguir usarmos os metodos

                //Obs: É padrão criamos um 'objetinho' só para consumir a api, porque o 'objetão' que vc quer ele é mais complexo


                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido:");
                //Quando chamamos o println(x) ele automaticamente chama o toString do objeto x
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);
//                //Gravação de dados em um arquivo
//                FileWriter escrita = new FileWriter("filmes.txt");
//                escrita.write(meuTitulo.toString()); //Usamos o toString para ele vir no formato que desejamos
//                escrita.close();

                //'Se acontecer esse tipo de erro, faça isso'
            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na busca, verifique a URL");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
                //O ideal aqui é tratar o erro, não exibir uma mensagem apenas
            }
        }
        System.out.println(titulos);
        FileWriter escrita = new FileWriter("filmes.json");
        //Queremos essa lista em JSON
        //Serializando titulos de string para json
        escrita.write(gson.toJson(titulos)); //Se eu colocar apenas titulos aqui dentro, ele vai chamar o toString dessa lista e vai mostrar exatamente o que vimos no println (não é o que queremos)
        escrita.close();
        System.out.println("O programa finalizou corretamente!");

        //Obs: Esse json gerado, pode ser o que o front vai consumir
    }
}
