# Sistema de Cadastro de Equipamentos

Uma API REST desenvolvida em Spring Boot para gerenciar o cadastro de equipamentos no setor de TI , oferecendo funcionalidades completas de CRUD e geração de relatórios em Excel que é utlizado para avaliar tempo de vida util do equipamento, visando a reduçaão de custos futuras comprando equipamentos com maior tempo de durabilidade.

##  Funcionalidades

- **Cadastro de Equipamentos**: Registre novos equipamentos com informações detalhadas
- **Listagem Completa**: Visualize todos os equipamentos cadastrados
- **Busca por ID**: Encontre equipamentos específicos pelo número de série
- **Atualização**: Modifique informações de equipamentos existentes
- **Exclusão**: Remove equipamentos do sistema
- **Relatório Excel**: Gere relatórios com cálculo automático de tempo de uso
- **Documentação Swagger**: Interface interativa para testar a API

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Apache POI** - Geração de arquivos Excel
- **Swagger/OpenAPI 3** - Documentação da API
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

##  Estrutura do Projeto

```
src/main/java/com/cadastro/equipamentos/
├── controller/
│   └── EquipamentoController.java    # Endpoints REST
├── entities/
│   └── Equipamento.java              # Entidade JPA
├── repository/
│   └── EquipamentoRepository.java    # Interface de acesso aos dados
├── service/
│   └── EquipamentoService.java       # Lógica de negócio
└── CadastroEquipamentoApplication.java # Classe principal
```

##  Modelo de Dados

A entidade `Equipamento` possui os seguintes campos:

- **numeroDeSerie** (String) - Chave primária, identificador único
- **marca** (String) - Marca do equipamento
- **modelo** (String) - Modelo do equipamento
- **dataDeEntrega** (LocalDate) - Data de entrega/instalação

##  Configuração e Execução

### Pré-requisitos

- Java 21+
- Maven 3.6+

### Como executar

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd cadastro-equipamentos
   ```

2. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação**
    - API: `http://localhost:8080/equipamentos`
    - Console H2: `http://localhost:8080/h2-console`
    - Documentação Swagger: `http://localhost:8080/swagger-ui.html`

### Configuração do Banco H2

- **URL**: `jdbc:h2:file:./data/equipamentosdb`
- **Usuário**: `sa`
- **Senha**: (vazio)

##  Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/equipamentos` | Cadastrar novo equipamento |
| `GET` | `/equipamentos` | Listar todos os equipamentos |
| `GET` | `/equipamentos/{id}` | Buscar equipamento por número de série |
| `PUT` | `/equipamentos/{id}` | Atualizar equipamento |
| `DELETE` | `/equipamentos/{id}` | Excluir equipamento |
| `GET` | `/equipamentos/excel` | Gerar relatório Excel |



##  Relatório Excel

O sistema gera automaticamente um relatório Excel contendo:

- Número de Série
- Marca
- Modelo
- Data de Entrega
- **Dias em Uso** (calculado automaticamente)

O cálculo de dias em uso é feito em tempo real, considerando a diferença entre a data de entrega e a data atual.

##  Documentação da API

Acesse a documentação interativa via Swagger em:
`http://localhost:8080/swagger-ui.html`

A documentação permite:
- Visualizar todos os endpoints
- Testar requests diretamente na interface
- Verificar modelos de dados
- Consultar códigos de resposta



## Configurações

### Profiles de Ambiente

Edite `application.properties` para configurar:

- Configurações do banco de dados
- Níveis de log
- Porta da aplicação
- Configurações do H2 Console

### Logs

O sistema possui logs configurados para:
- Consultas SQL (`show-sql=true`)
- Debug do Spring Web
- Trace do Spring HTTP


## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.


---

**Desenvolvido por Filipe Cândido Rodrigues**
