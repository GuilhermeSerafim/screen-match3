package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.conexao.ListaDeTitulos;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class PrincipalRefatorada {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileWriter escrita = new FileWriter("titulos.json");
        Gson conversao = new Gson();
        ListaDeTitulos listaDeTitulos = new ListaDeTitulos();
        escrita.write(conversao.toJson(listaDeTitulos.listarTitulos())); //Aqui poderia ser consumida pelo front
        escrita.close();
        System.out.println("O programa finalizou corretamente!");
    }
}
