# Sistema de Cadastro de Equipamentos

Uma API REST desenvolvida em Spring Boot para gerenciar o cadastro de equipamentos/periféricos, oferecendo funcionalidades completas de CRUD e geração de relatórios em Excel.

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
    - API: `http://localhost:8080`
    - Console H2: `http://localhost:8080/h2-console`
    - Documentação Swagger: `http://localhost:8080/swagger-ui.html`

### Configuração do Banco H2

- **URL**: `jdbc:h2:file:./data/equipamentosdb`
- **Usuário**: `sa`
- **Senha**: (vazio)

##  Endpoints da API

### Equipamentos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/equipamentos/` | Cadastrar novo equipamento |
| `GET` | `/api/equipamentos/` | Listar todos os equipamentos |
| `GET` | `/api/equipamentos/{id}` | Buscar equipamento por número de série |
| `PUT` | `/api/equipamentos/{id}` | Atualizar equipamento |
| `DELETE` | `/api/equipamentos/{id}` | Excluir equipamento |
| `GET` | `/api/equipamentos/excel` | Gerar relatório Excel |

### Exemplos de Uso

#### Cadastrar Equipamento
```bash
curl -X POST http://localhost:8080/api/equipamentos/ \
-H "Content-Type: application/json" \
-d '{
  "numeroDeSerie": "EQ001",
  "marca": "Dell",
  "modelo": "OptiPlex 7090",
  "dataDeEntrega": "2024-01-15"
}'
```

#### Listar Equipamentos
```bash
curl http://localhost:8080/api/equipamentos/
```

#### Buscar por Número de Série
```bash
curl http://localhost:8080/api/equipamentos/EQ001
```

#### Gerar Relatório Excel
```bash
curl http://localhost:8080/api/equipamentos/excel \
-H "Accept: application/octet-stream" \
--output relatorio-equipamentos.xlsx
```

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

##  Banco de Dados

O projeto utiliza H2 Database para desenvolvimento:

- **Persistência**: Arquivo local (`./data/equipamentosdb`)
- **Console Web**: Habilitado para consultas SQL
- **DDL**: Criação/atualização automática de tabelas

### Acessar Console H2

1. Vá para: `http://localhost:8080/h2-console`
2. Configure:
    - JDBC URL: `jdbc:h2:file:./data/equipamentosdb`
    - User Name: `sa`
    - Password: (deixe vazio)

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

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## Suporte

Para dúvidas ou problemas:
- Abra uma issue no GitHub
- Consulte a documentação do Swagger
- Verifique os logs da aplicação

---

**Desenvolvido com Spring Boot**
