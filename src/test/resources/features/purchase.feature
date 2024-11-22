#language: pt
@fullflow @purchase
Funcionalidade: Realizar compra de monitor no Demoblaze

  Cenario: Compra bem-sucedida de um monitor
    Dado que estou na página inicial do Demoblaze
    Quando eu abro o formulário de login
    E preencho o formulário de login com nome de usuário "marco.rodrigues" e senha "123"
    E confirmo o login
    E navego para a categoria "Monitors"
    E adiciono o primeiro monitor ao carrinho
    E vou para o carrinho
    E concluo a compra com detalhes válidos
    Então devo ver uma mensagem de confirmação de compra
