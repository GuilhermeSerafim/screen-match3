package src.br.com.alura.viacep.model;

//Para consumo da api
public record ClienteEnderecoViaCep(String cep, String logradouro, String bairro, String localidade, String uf) {
}
