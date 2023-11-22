package src.br.com.alura.viacep.controll;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import src.br.com.alura.viacep.model.ClienteEnderecoViaCep;

import java.io.FileWriter;
import java.io.IOException;

public class GerarArquivos {
    public void salvaJson(ClienteEnderecoViaCep endereco) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        FileWriter escrita = new FileWriter(endereco.cep() + ".json");
        escrita.write(gson.toJson(endereco));
        escrita.close();
    }
}
