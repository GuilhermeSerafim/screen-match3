package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.conexao.BuscarTitulo;
import br.com.alura.screenmatch.conexao.Converter;

import java.io.IOException;

public class PrincipalRefatorada {
    public static void main(String[] args) throws IOException, InterruptedException {
        BuscarTitulo busca = new BuscarTitulo();
        Converter converter = new Converter();

        String resposta = converter.converteJsonParaObjeto(busca);
    }
}
