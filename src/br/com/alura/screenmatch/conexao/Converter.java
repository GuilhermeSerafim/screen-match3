package br.com.alura.screenmatch.conexao;

import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Converter {
    private Gson conversao = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting() //Facilitar leitura do JSON
            .create();
    private TituloOmdb tituloOmdb;

    public String converteJsonParaObjeto(BuscarTitulo titulo) throws IOException, InterruptedException {
        String buscaExtraida = titulo.buscar();
        tituloOmdb = conversao.fromJson(buscaExtraida, TituloOmdb.class);
        System.out.println("Teste: ");
        System.out.println(tituloOmdb);
        return buscaExtraida;
    }
}
