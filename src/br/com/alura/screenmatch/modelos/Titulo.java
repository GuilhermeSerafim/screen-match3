package br.com.alura.screenmatch.modelos;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
    //Serializando dados
    // Com a anotação @SerializedName avisamos ao GSON qual o nome deve procurar no formato serializado.
//    //Ou seja toda vez que eu me referir ao nome, estou me referindo ao Title do objeto JSON que pegamos da API
//    @SerializedName("Title") //Futuramente pode ser que esse 'Title' que colocamos estaticamente, seja 'MovieName'
//    private String nome;    //Ou seja, não é uma boa prática. Para isso criamos o nosso Record, para não digitarmos manualmente todos os SerializedName em cada variavel
//    @SerializedName("Year") //Pois futuramente, possa ser que essa classe consuma 2 API com nomenclaturas diferentes...
//    private int anoDeLancamento; //Logo o Record foi criado com o nome da mesma classe, e o nome da API. Criamos um construtor que recebe a API como parametro, e manipulamos esses dados
    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    //Construtor que aceita um objeto TituloOmdb
    public Titulo(TituloOmdb meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();

        //Criando nossa exceção
        if(meuTituloOmdb.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano, pois tem mais de 4 caracteres");

        }
        //Conversão de uma String para um Objeto do tipo Integer (inteiro)
        this.anoDeLancamento = Integer.valueOf(meuTituloOmdb.year());
        this.duracaoEmMinutos = Integer.valueOf((meuTituloOmdb.runtime().substring(0, 2)));
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota){
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia(){
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    //Para tiramos do formato padrão do toString (br.com.alura.screenmatch.modelos.Titulo@4387b79e)
    @Override
    public String toString() {
        return "Titulo: " + nome + " (" + anoDeLancamento + ") " + "| Duração: " + duracaoEmMinutos + " min";
    }
}
