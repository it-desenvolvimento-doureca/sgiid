# SGIID - Sistema de Gestao Integrado Industrial Doureca (Backend)

Backend REST API do sistema SGIID, uma aplicacao empresarial Java EE para gestao industrial integrada. Abrange producao, manutencao, recursos humanos, qualidade, financas, comercial, pintura, banhos e outras areas operacionais.

## Stack Tecnologica

| Tecnologia | Versao | Descricao |
|---|---|---|
| **Java EE** | 7.0 | Framework empresarial (JAX-RS, JPA, CDI, EJB) |
| **SQL Server** | - | Base de dados relacional (JDBC) |
| **JPA** | 2.1 | Persistencia de dados com EntityManager |
| **JAX-RS** | 2.0 | API REST com anotacoes (`@Path`, `@GET`, `@POST`, etc.) |
| **CDI** | 1.1 | Injecao de dependencias (`@Inject`) |
| **EJB** | 3.2 | Componentes de negocio (`@Stateless`) |
| **Maven** | - | Gestao de dependencias e build (`war` packaging) |
| **JasperReports** | - | Geracao de relatorios PDF/XLSX/DOCX |
| **Apache FOP** | 0.95 | Formatacao de documentos XML/XSL-FO |
| **Barcode4J** | 2.1 | Geracao de codigos de barras |
| **OpenCMIS** | - | Integracao com Alfresco (gestao documental) |
| **PDFBox** | - | Impressao de PDFs em impressoras fisicas |

## Arquitetura

```
src/main/java/pt/example/
├── bootstrap/          # Configuracao e utilitarios (13 ficheiros)
│   ├── JaxRsActivator  # Ativa API REST em /rest
│   ├── CORSFilter      # Filtro CORS para todas as respostas
│   ├── ConnectionSQL    # Conexao JDBC ao SQL Server
│   ├── ReportGenerator  # Geracao de relatorios JasperReports
│   ├── SendEmail        # Envio de emails SMTP com anexos
│   ├── Printer          # Impressao fisica (PDF e texto)
│   ├── AlfrescoApi      # Integracao documental Alfresco/CMIS
│   └── SomeQuarterlyJob # Importacao de ficheiros de dados
├── entity/             # Entidades JPA (320 classes)
├── dao/                # Data Access Objects (317 classes)
│   ├── GenericDao       # Interface CRUD generica
│   └── GenericDaoJpaImpl# Implementacao JPA base
└── rest/               # Endpoints REST (5 ficheiros)
    ├── SIRB             # Controlador REST principal
    ├── SIRB_2           # Controlador REST estendido
    ├── SIRB_3           # Controlador REST adicional
    └── REST_POWERBI     # Integracao Power BI (OAuth2)
```

### Padrao DAO

Todas as classes DAO estendem `GenericDaoJpaImpl` e utilizam um unico `persistenceUnit`:

```java
@Stateless
public class ExemploDao extends GenericDaoJpaImpl<ENTIDADE, Integer> {
    public List<Object[]> getall() {
        Query q = em.createNativeQuery("SELECT ... FROM TABELA WHERE INATIVO = 0");
        return q.getResultList();
    }
}
```

### Padrao Entity

Todas as entidades mapeiam tabelas SQL Server com:
- `@Id` + `@GeneratedValue(IDENTITY)` para chaves primarias auto-incremento
- Campos de auditoria: `DATA_CRIA`, `UTZ_CRIA`, `DATA_ULT_MODIF`, `UTZ_ULT_MODIF`, `DATA_ANULACAO`, `UTZ_ANULACAO`
- Soft delete via flag `INATIVO`
- Sem relacionamentos JPA (`@ManyToOne`, `@OneToMany`) - joins feitos manualmente nos DAOs

### API REST

Todos os endpoints estao disponibilizados sob o path `/rest/sirb/` e seguem o padrao:

| Metodo | Path | Operacao |
|---|---|---|
| `POST` | `/rest/sirb/createEntidade` | Criar registo |
| `GET` | `/rest/sirb/getEntidade` | Listar registos |
| `GET` | `/rest/sirb/getEntidadeByID/{id}` | Obter por ID |
| `PUT` | `/rest/sirb/updateEntidade` | Atualizar registo |
| `DELETE` | `/rest/sirb/deleteEntidade/{id}` | Eliminar registo |

## Modulos Funcionais

### Producao (PR_) - 46 entidades
Gestao de producao industrial: planeamento, ordens de fabrico, registos de producao, capacidade de linhas, defeitos, paragens, e analise de eficiencia (OEE).

Tabelas principais:
- `PR_PLANEAMENTO_PRODUCAO_CAB/LINHAS` - Planeamento de producao por semana
- `PR_DIC_PRODUCAO_SEMANA` - Dias de producao por semana/linha
- `PR_DIC_CAPACIDADE_RACKS` - Capacidade de racks e voltas por dia
- `PR_MOV_ORDEM_FABRICO` - Ordens de fabrico
- `PR_MOV_REGISTOS_PRODUCAO` - Registos diarios de producao

### Recursos Humanos (RH_) - 45 entidades
Gestao de colaboradores, contratos, formacao, ferias, faltas, avaliacoes de desempenho, medicina no trabalho e equipamentos de protecao individual.

### Geral (GER_) - 41 entidades
Modulo transversal: utilizadores, perfis de acesso, modulos, fornecedores, armazens, postos de trabalho, parametros do sistema, logs de eventos e configuracoes.

### Manutencao (MAN_) - 29 entidades
Gestao de manutencao industrial: equipamentos, ordens de trabalho, manutencao preventiva e corretiva, pecas de substituicao, pedidos de intervencao e historico.

### Pintura (PIN_) - 28 entidades
Gestao do processo de pintura: receitas (primario, base, verniz), cores e acabamentos, ciclos de pintura, planos de producao de pintura e controlo de qualidade.

Tabelas principais:
- `PIN_MOV_RECEITAS` - Receitas de pintura com versoes
- `PIN_MOV_RECEITAS_REFERENCIAS` - Associacao receita-referencia
- `PIN_DIC_CORES_ACABAMENTOS` - Dicionario de cores e acabamentos
- `PIN_PLANOS_PRODUCAO` - Planos de producao de pintura

### Banhos/Revestimento (AB_) - 26 entidades
Gestao de linhas de banhos galvanicos: tinas, zonas, analises quimicas, aditivos, componentes, turnos, manutencao de banhos e doseamento.

### Reclamacoes (RC_) - 22 entidades
Gestao de reclamacoes de clientes e fornecedores, nao conformidades, acoes corretivas/preventivas, custos associados e seguimento.

### Comercial (COM_) - 19 entidades
Gestao comercial: clientes, encomendas, artigos, tabelas de precos, fichas tecnicas e expedicao.

### Financeira (FIN_) - 9 entidades
Gestao financeira: centros de custo, orcamentos, faturas e controlo de despesas.

### Qualidade (QUA_) - 7 entidades
Gestao da qualidade: auditorias, indicadores, documentos do sistema de gestao e calibracao de equipamentos.

### Documentacao (DOC_) - 7 entidades
Gestao documental integrada com Alfresco: categorias, tipos de documentos, versoes e controlo de acessos.

### Reunioes (REU_) - 6 entidades
Gestao de reunioes: agendamento, atas, participantes, acoes e seguimento de decisoes.

### Acidentes de Trabalho (AT_) - 6 entidades
Gestao de seguranca: registo de ocorrencias, investigacao de acidentes, acoes corretivas e estatisticas.

### Gestao de Tarefas (GT_) - 5 entidades
Gestao de tarefas e atividades: atribuicao, estados, prioridades e acompanhamento.

### Planeamento (PA_) - 4 entidades
Planeamento geral e agendamento de atividades.

### Logistica (LG_) - 2 entidades
Gestao logistica: movimentos de armazem e expedicao.

## Integracoes Externas

| Sistema | Tecnologia | Funcionalidade |
|---|---|---|
| **Alfresco** | CMIS (OpenCMIS) | Gestao documental - upload, versionamento e consulta de documentos |
| **Power BI** | OAuth2 REST API | Dashboards e relatorios embebidos com tokens de acesso |
| **Email SMTP** | JavaMail | Notificacoes com HTML, anexos e imagens base64 |
| **Impressoras** | Java Print Service + PDFBox | Impressao direta de PDFs e etiquetas |
| **JasperReports** | JasperReports API | Geracao de relatorios PDF, XLSX e DOCX a partir de templates JRXML |

## Configuracao

### Ficheiros de Configuracao Externos

| Ficheiro | Descricao |
|---|---|
| `c:\sgiid\conf_email.ini` | Configuracao SMTP (host, porta, credenciais) |
| `c:\cartelas\conf.ini` | Conexao SQL Server para sistema legado |
| `c:/relatorios/jasperfiles/` | Templates JasperReports (.jrxml) |

### Persistencia (JPA)

O persistence unit `persistenceUnit` e configurado no application server (WildFly/JBoss) com datasource apontando para SQL Server.

## Build e Deploy

```bash
# Compilar e gerar WAR
mvn clean package

# O ficheiro WAR e gerado em:
# target/app-0.0.1-SNAPSHOT.war

# Deploy no WildFly/JBoss:
# Copiar o WAR para a pasta de deployments do servidor
```

## Convencoes de Nomenclatura

### Prefixos de Modulo
| Prefixo | Modulo |
|---|---|
| `AB_` | Banhos (Baths/Coating) |
| `AT_` | Acidentes de Trabalho (Safety) |
| `COM_` | Comercial (Commercial) |
| `DOC_` | Documentacao (Documentation) |
| `FIN_` | Financeira (Finance) |
| `GER_` | Geral (Generic) |
| `GT_` | Gestao de Tarefas (Task Management) |
| `LG_` | Logistica (Logistics) |
| `MAN_` | Manutencao (Maintenance) |
| `PA_` | Planeamento (Planning) |
| `PIN_` | Pintura (Painting) |
| `PR_` | Producao (Production) |
| `QUA_` | Qualidade (Quality) |
| `RC_` | Reclamacoes (Claims) |
| `REU_` | Reunioes (Meetings) |
| `RH_` | Recursos Humanos (HR) |

### Sufixos de Tabela
| Sufixo | Tipo |
|---|---|
| `_DIC_` | Dicionario / Dados mestre |
| `_MOV_` | Movimentos / Dados transacionais |
| `_CAB` | Cabecalho (Header) |
| `_LIN` / `_LINHAS` | Linhas (Detail lines) |
