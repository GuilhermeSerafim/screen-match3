package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.conexao.ListaDeTitulos;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class PrincipalRefatorada {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileWriter escrita = new FileWriter("titulos.json");
        Gson gson = new Gson();
        ListaDeTitulos listaDeTitulos = new ListaDeTitulos();
        escrita.write(gson.toJson(listaDeTitulos.listarTitulos()));
        escrita.close();
        System.out.println("O programa finalizou corretamente!");
    }
}
