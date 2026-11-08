![thumbnail-Formação Java (4)](https://user-images.githubusercontent.com/66698429/226751112-f79aaa28-16c9-4561-8a15-0ac62ec9cb44.png)


# Java: consumindo uma API, gravando arquivos e lidando com erros

Projeto desenvolvido no quarto curso da formação Java da Alura


## 🔨 Objetivos do projeto

- Aprender a consumir uma API HTTP em Java;
- Entender como receber e converter dados no formato JSON; 
- Utilizar a biblioteca externa GSON para fazer serialização e desserialização de dados;
- Lidar com erros e tratar exceções;
- Conhecer o pacote java.io para manipulação de arquivos.

## Configuração da consulta à OMDb

A entrada `PrincipalComBusca` lê a chave da OMDb pela variável de ambiente `OMDB_API_KEY`. Configure-a no terminal antes de executar o programa com o ambiente Java/Gson do curso. Os valores abaixo são placeholders; substitua-os pela sua própria configuração e não versione chaves.

PowerShell:

```powershell
$env:OMDB_API_KEY = "<SUA_CHAVE_OMDB>"
```

Bash:

```bash
export OMDB_API_KEY="<SUA_CHAVE_OMDB>"
```

Se a variável estiver ausente, vazia ou contiver somente espaços, o programa encerra com uma mensagem indicando `OMDB_API_KEY`, antes de pedir o título ou criar/enviar a requisição HTTP. A busca e a gravação em `filmes.json` mantêm o fluxo didático existente.
