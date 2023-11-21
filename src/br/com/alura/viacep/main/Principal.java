package src.br.com.alura.viacep.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import src.br.com.alura.viacep.model.ClienteEndereco;
import src.br.com.alura.viacep.model.ClienteEnderecoViaCep;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        ClienteEndereco cliente = new ClienteEndereco();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().create();
        FileWriter escrita = new FileWriter("enderecos.json");
        List<ClienteEndereco> clientes = new ArrayList<>();
        String sair = "";

        while (!sair.equalsIgnoreCase("sair")) {
            System.out.println("Aperte enter, ou digite 'sair' para encerrrar");
            sair = in.nextLine();
            if (sair.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Digite seu cep");
            String cep = in.nextLine();

            //Realizando requisição e puxando corpo da requisição
            String extraindoCorpoDaRequisicao = cliente.requisicaoApiViaCep(cep);

            //Convertendo para objeto
            ClienteEnderecoViaCep viaCep = gson.fromJson(extraindoCorpoDaRequisicao, ClienteEnderecoViaCep.class);
            ClienteEndereco clienteEndereco = new ClienteEndereco(viaCep);
            clientes.add(clienteEndereco);
        }

        //Convertendo para json
        escrita.write(gson.toJson(clientes));
        escrita.close();
    }
}
