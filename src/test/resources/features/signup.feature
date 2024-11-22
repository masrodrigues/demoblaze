#language: pt
@fullflow @signup
Funcionalidade:  Cadastro no Demoblaze

  Cenario: Cadastro bem-sucedido
    Dado que estou na página inicial do Demoblaze
    Quando eu abro o formulário de cadastro
    E preencho o formulário de cadastro com dados dinâmicos
    E confirmo o cadastro
    Entao devo ver uma mensagem de sucesso confirmando o cadastro
