#language: pt
@fullflow @login
Funcionalidade: Realizar Login no Demoblaze

  Cenario: Login bem-sucedido de um usuário existente
    Dado que estou na página inicial do Demoblaze
    Quando eu abro o formulário de login
    E preencho o formulário de login com nome de usuário "marco.rodrigues" e senha "123"
    E confirmo o login
    Entao devo ver meu nome de usuário como "Welcome marco.rodrigues"

