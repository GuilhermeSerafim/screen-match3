package br.com.alura.screenmatch.conexao;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class ConverterJson {
    private Gson conversao = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting() //Facilitar leitura do JSON
            .create();
    private TituloOmdb tituloAPIOmdb;
    private Titulo tituloConvertidoEmObjeto;

    public Titulo converteJsonParaObjeto() throws IOException, InterruptedException {
        BuscarTitulo tituloEmStringNoFormatoJson = new BuscarTitulo();
        //Acionando metodo de buscar titulo e extraindo
        String buscaExtraida = tituloEmStringNoFormatoJson.buscar();
        tituloAPIOmdb = conversao.fromJson(buscaExtraida, TituloOmdb.class);

        tituloConvertidoEmObjeto = new Titulo(tituloAPIOmdb);

        System.out.println(tituloConvertidoEmObjeto);
        return tituloConvertidoEmObjeto;
    }
}
