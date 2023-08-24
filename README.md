# Bolsa de valores

Este é um projeto pessoal feito para aplicar na prática meus conhecimentos adquiridos quanto ao Java com Spring Boot. 

# Funcionamento do projeto:

A ideia do projeto é simular o funcionamento de uma pequena bolsa de valores onde se tem ações e investidores que executam operações de compra e venda das ações. 
Toda compra e venda traz alguma alteração no valor atual da ação. 

O projeto está utilizando o H2 Database, portanto, ao iniciar a aplicação, o banco de dados sobe junto em memória. 

Ao iniciar, serão adicionados os seguintes dados:

  *AÇÕES
    - Itaú Unibanco, Banco Bradesco, Vivo (Telefônica Brasil);

  *Investidores
    - James Gosling, Guido Van Rossum, Ada Lovelace e Gustavo Guanabara;

  *Admin
    - Alessandro Lima;

    Basta acessar o banco e verificar os dados de cada unidade para interagir com o software.

    Ao iniciar a aplicação, todos os investidores começam com R$: 5.000,00 de saldo na conta. 

# Funcionalidades e Permissões:
  *ADMIN
    - Inserção, deleção e consulta de ações.
    - Inserção e consulta de investidores.
    
  *INVESTIDOR
    - Consulta, compra e venda de ações.


# Lógica de preço das ações:

  As ações variação em seu preço, por meio de compra/venda das mesmas, e pela lógica estabelecida que simula a variação do papel a cada 5 segunda para mais ou para menos. 
  Há uma pequena tendência coloca para movimentos de alta. 
  O algoritmo que simula essa mudança em tempo real fica rodando em uma Thread Java que é iniciada juntamento da aplicação.

Em todo o código, as ações são sempre referidas como "Papeis".

Para detalhes sobre as rotas e quais são os parâmetros a serem enviado, foi implementado também a documentação utilizando o SWAGGER. Só acessar a rota da documentação ('/swagger-ui.html').

# Agradeço pelo interesse em meu código.
