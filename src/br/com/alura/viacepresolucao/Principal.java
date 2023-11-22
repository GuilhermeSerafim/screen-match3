package src.br.com.alura.viacepresolucao;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ConsultaCep consultaCep = new ConsultaCep();
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite o seu cep");
        var cep = leitura.nextLine();

        try {
            Endereco novoEndereco = consultaCep.buscaEndereco(cep);
            System.out.println(novoEndereco);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando aplicação");
        }
    }
}
