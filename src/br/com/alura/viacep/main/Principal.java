package src.br.com.alura.viacep.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import src.br.com.alura.viacep.connection.ConsultaCep;
import src.br.com.alura.viacep.controll.GerarArquivos;
import src.br.com.alura.viacep.model.ClienteEnderecoViaCep;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        ConsultaCep consulta = new ConsultaCep();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                        .create();
        System.out.println("Digite seu CEP");
        var cep = in.nextLine();

        try {
            ClienteEnderecoViaCep novoEndereco = consulta.buscaEndereco(cep);
            System.out.println("Endereço cadastrado!");
            System.out.println(novoEndereco);
            GerarArquivos gerador = new GerarArquivos();
            gerador.salvaJson(novoEndereco);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando aplicação...");
        }

    }
}
