package br.com.alura.screenmatch.conexao;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscarTitulo {
    private String buscaNaRequisicao;
    private Scanner leitura = new Scanner(System.in);
    private String endereco;
    private List<Titulo> titulos = new ArrayList<>();

    public BuscarTitulo() {
    }

    //Quem vai tratar as exceções geradas pelos send (enviar requisição) vai ser o metodo
    //Mas não definimos como tratar, apenas delegamos
    public String buscar() throws IOException, InterruptedException {
            buscaNaRequisicao = leitura.nextLine();

            //Concatenação para tornar a busca dinâmica
            //Tratando titulos com espaço
            endereco = "https://www.omdbapi.com/?t="
                       + buscaNaRequisicao.replace(" ", "+")
                       + "&apikey=c0e431f9";
            try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder() //Inicia a construção de uma nova requisição HTTP | newBuilder indica que é um objeto complexo, para construir ele, vai ser usado metodos
                    .uri(URI.create(endereco)) //Define a URI (Uniform Resource Identifier)
                    .build(); //Constrói a requisição HTTP
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); //ofString - converte em String

            String respostaEmString = response.body();
            return respostaEmString;
            //Tratamento de algumas exceptions
            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na busca, verifique a URL");
                return null;
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
                return null;
            }
    }
}
