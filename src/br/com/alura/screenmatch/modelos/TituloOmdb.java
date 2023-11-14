package br.com.alura.screenmatch.modelos;

//Códigos boilerplates gerados
public record TituloOmdb(String title, String year, String runtime) {
}
//Em resumo, a introdução do Record TituloOmdb resolve o problema de forma eficiente,
// fornecendo uma representação simples e direta dos dados provenientes da API OMDb.
//Esse Record é projetado especificamente para a desserialização dos dados JSON,
// eliminando a necessidade de usar anotações como @SerializedName diretamente na classe Titulo.