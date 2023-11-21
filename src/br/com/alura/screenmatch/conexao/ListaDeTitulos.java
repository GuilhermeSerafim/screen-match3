package br.com.alura.screenmatch.conexao;

import br.com.alura.screenmatch.modelos.Titulo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaDeTitulos {
    private List<Titulo> titulos = new ArrayList<>();
    private String sair = "";
    private Scanner in = new Scanner(System.in);
    private ConverterJson buscandoEConvertendo = new ConverterJson();
    public List<Titulo> listarTitulos() throws IOException, InterruptedException {
        while(true) {
            System.out.println("Aperte enter, ou digite sair para encerrar a busca: ");
            sair = in.nextLine();
            if(sair.equalsIgnoreCase("sair")) {
                break;
            }
            System.out.println("Digite seu titulo: ");
            titulos.add(buscandoEConvertendo.converteJsonParaObjeto());
        }
        System.out.println(titulos);
        return titulos;
}

}
