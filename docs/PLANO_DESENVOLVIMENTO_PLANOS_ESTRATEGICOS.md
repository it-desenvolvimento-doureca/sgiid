# Plano de Desenvolvimento — Planos Ações / Estratégicos

**Data:** 2026-03-27
**Projeto:** SGIID — Sistema de Gestão Integrado Industrial Doureca
**Módulo:** Planos Estratégicos / Planos de Ação
**Área:** `src/app/paginas/modulo-planos-estrategicos/`

---

## Índice

1. [Requisitos](#1-requisitos)
2. [Estrutura de Dados Atual](#2-estrutura-de-dados-atual)
3. [Alterações de Base de Dados](#3-alterações-de-base-de-dados)
4. [Backend — Java / JAX-RS](#4-backend--java--jax-rs)
5. [Frontend — Angular](#5-frontend--angular)
6. [Validação de Stored Procedures](#6-validação-de-stored-procedures)
7. [Ordem de Implementação](#7-ordem-de-implementação)

---

## 1. Requisitos

| # | Requisito | Prioridade |
|---|---|---|
| R1 | Ao alterar a data de objetivo do plano (estado=Planeado) pedir justificação | Alta |
| R2 | Guardar histórico de alterações de estado e data/justificação | Alta |
| R3 | Ordenação das listas pela data de criação mais recente | Baixa |
| R4 | Ao adicionar linhas no estado Planeado, gravar no histórico com o estado do plano no momento | Média |
| R5 | Anular linhas (soft delete — não remover fisicamente) | Alta |
| R6 | Rever campos de datas no dialog do plano de ação | Média |
| R7 | Ícone de histórico nas páginas de planos de ação | Média |
| R8 | Ícone com cor diferente quando há alteração de data objetivo ou anulação de linha | Média |
| R9 | `PE_PLANOS_ASSOCIADOS`: manter registo ao remover — marcar como anulado, não editar | Alta |

---

## 2. Estrutura de Dados Atual

### Tabelas Frontend (Entidades Angular)

| Tabela | Ficheiro | Campos Relevantes |
|---|---|---|
| `PE_MOV_CAB` | `PE_MOV_CAB.ts` | ID, ESTADO, DEPARTAMENTO, RESPONSAVEL, ANO_PLANO, TIPO, DATA_FECHA, ATIVO |
| `PE_MOV_CAB_HISTORICO` | `PE_MOV_CAB_HISTORICO.ts` | ID, ID_PLANO_ESTRATEGICO, DESCRICAO, OBSERVACOES, DATA_CRIA, UTZ_CRIA |
| `PE_MOV_FICHEIROS` | `PE_MOV_FICHEIROS.ts` | id, id_PLANO_ESTRATEGICO, nome, caminho, tipo, tamanho |
| `PA_MOV_CAB` | `PA_MOV_CAB.ts` | id_PLANO_CAB, estado, data_OBJETIVO, data_ORIGEM, ambito, origem, id_PLANO_ESTRATEGICO, ativo, data_ANULA, utz_ANULA |
| `PA_MOV_LINHA` | `PA_MOV_LINHA.ts` | id_PLANO_LINHA, id_PLANO_CAB, responsavel, data_ACCAO, estado, data_CANCELADO, utz_CANCELADO, data_APROVADO, utz_APROVADO |
| `PA_MOV_FICHEIROS` | `PA_MOV_FICHEIROS.ts` | id, id_PLANO_CAB, nome, caminho |
| `PA_DIC_AMBITOS` | `PA_DIC_AMBITOS.ts` | id_AMBITO, descricao |

### Tabela de Junção (sem entidade Angular)

| Tabela | Campos Atuais | Gestão |
|---|---|---|
| `PE_PLANOS_ASSOCIADOS` | ID, ID_PLANO_CAB, ID_PLANO_ESTRATEGICO | Native SQL via `PA_MOV_CABDao.java` |

### Relações Chave

```
PE_MOV_CAB (1) ──── (N) PE_PLANOS_ASSOCIADOS (N) ──── (1) PA_MOV_CAB
PA_MOV_CAB  (1) ──── (N) PA_MOV_LINHA
PA_MOV_LINHA (1) ──── (0..1) GT_MOV_TAREFAS  [ID_MODULO=13, SUB_MODULO='PA']
```

### Serviços Backend (SIRB.java / SIRB_2.java)

| Endpoint | Método HTTP | Ficheiro | SP / Query |
|---|---|---|---|
| `getPA_MOV_CABbyidPlanoEstrategico/{tipo}/{id}/{user}` | GET | SIRB.java | `PA_MOV_CABDao.getbyidPlanoEstrategico()` |
| `getPA_MOV_CABAssociarPlanoEstrategico/{id_plano}/{id}` | GET | SIRB.java | Native SQL — INSERT em `PE_PLANOS_ASSOCIADOS` |
| `getPA_MOV_CABRemoverPlanoEstrategico/{id_plano}/{id}` | GET | SIRB.java | Native SQL — DELETE em `PE_PLANOS_ASSOCIADOS` |
| `getPA_MOV_CABbyTIPOASSOCIAR/{tipo}` | POST | SIRB.java | `PA_MOV_CABDao.getPA_MOV_CABbyTIPOASSOCIAR()` |
| `PE_GET_ACOES_EM_ATRASO` | POST | SIRB_2.java | `EXEC PE_GET_ACOES_EM_ATRASO @ANO, @PAGINA` |
| `PE_GET_ULTIMAS_ACOES_CONCLUIDAS` | POST | SIRB_2.java | `EXEC PE_GET_ULTIMAS_ACOES_CONCLUIDAS @ANO, @PAGINA` |
| `PE_GET_ANALISE_CONTADORES` | POST | SIRB_2.java | `EXEC PE_GET_ANALISE_CONTADORES @ANO` |
| `PE_GET_ANALISE_GRAFICO` | POST | SIRB_2.java | `EXEC PE_GET_ANALISE_GRAFICO @ANO` |
| `getPE_MOV_CABbyTIPO/{tipo}` | POST | SIRB.java | `PE_MOV_CABDao.getallbyTIPO()` → `EXEC PE_GET_PLANOS_ESTRATEGICOS` |
| `getPE_MOV_CABbyDEPT` | POST | SIRB.java | `PE_MOV_CABDao.getallbyDEPT()` → `EXEC PE_GET_PLANOS_ESTRATEGICOS` |

---

## 3. Alterações de Base de Dados

### 3.1 — Nova tabela `PA_MOV_CAB_HISTORICO`

Regista alterações ao cabeçalho do Plano de Ação (data objetivo, estado).

```sql
CREATE TABLE PA_MOV_CAB_HISTORICO (
    ID              INT           IDENTITY(1,1) PRIMARY KEY,
    ID_PLANO_CAB    INT           NOT NULL,  -- FK → PA_MOV_CAB
    TIPO_ALTERACAO  VARCHAR(50)   NOT NULL,  -- 'DATA_OBJETIVO', 'ESTADO', 'ADICAO_LINHA'
    VALOR_ANTERIOR  VARCHAR(200)  NULL,
    VALOR_NOVO      VARCHAR(200)  NULL,
    JUSTIFICACAO    VARCHAR(500)  NULL,
    ESTADO_PE       VARCHAR(5)    NULL,      -- estado do PE_MOV_CAB no momento
    DATA_CRIA       DATETIME      DEFAULT GETDATE(),
    UTZ_CRIA        INT           NOT NULL
);
```

### 3.2 — Nova tabela `PA_MOV_LINHA_HISTORICO`

Regista adições, anulações e alterações nas linhas do Plano de Ação.

```sql
CREATE TABLE PA_MOV_LINHA_HISTORICO (
    ID                   INT          IDENTITY(1,1) PRIMARY KEY,
    ID_PLANO_CAB         INT          NOT NULL,  -- FK → PA_MOV_CAB
    ID_PLANO_LINHA       INT          NULL,       -- FK → PA_MOV_LINHA (null para adições antes de gravar)
    TIPO_ALTERACAO       VARCHAR(50)  NOT NULL,  -- 'ADICAO', 'ANULACAO', 'ALTERACAO_DATA'
    DESCRICAO            VARCHAR(500) NULL,
    ESTADO_PLANO_MOMENTO VARCHAR(5)   NULL,      -- estado do PE_MOV_CAB no momento
    DATA_CRIA            DATETIME     DEFAULT GETDATE(),
    UTZ_CRIA             INT          NOT NULL
);
```

### 3.3 — Novos campos em `PA_MOV_LINHA`

Suporte a soft delete (anulação sem remoção física).

```sql
ALTER TABLE PA_MOV_LINHA ADD DATA_ANULA DATETIME NULL;
ALTER TABLE PA_MOV_LINHA ADD UTZ_ANULA  INT       NULL;
ALTER TABLE PA_MOV_LINHA ADD INATIVO    BIT       DEFAULT 0 NOT NULL;
```

> **Nota:** `PA_MOV_LINHA` já tem relação com `GT_MOV_TAREFAS` (ID_MODULO=13, SUB_MODULO='PA').
> Ao anular uma linha, a tarefa associada deve ser atualizada para estado='A'.

### 3.4 — Novos campos em `PE_PLANOS_ASSOCIADOS`

Suporte a soft delete da associação PE ↔ PA.

```sql
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD INATIVO    BIT      DEFAULT 0 NOT NULL;
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD DATA_ANULA DATETIME NULL;
ALTER TABLE PE_PLANOS_ASSOCIADOS ADD UTZ_ANULA  INT      NULL;
```

---

## 4. Backend — Java / JAX-RS

**Ficheiros a alterar:**
- `C:\work\Workspace\sgiid\src\main\java\pt\example\rest\SIRB.java`
- `C:\work\Workspace\sgiid\src\main\java\pt\example\dao\PA_MOV_CABDao.java`

**Ficheiros a criar (opcional — pode ficar em SIRB.java):**
- Endpoints para `PA_MOV_CAB_HISTORICO` e `PA_MOV_LINHA_HISTORICO`

---

### 4.1 — Novo endpoint: `getPA_MOV_CABAnularAssociacao` (SIRB.java)

Substitui o DELETE por UPDATE em `PE_PLANOS_ASSOCIADOS`.

```java
// Adicionar após getPA_MOV_CABRemoverPlanoEstrategico (~linha 4815)
@GET
@Path("/getPA_MOV_CABAnularAssociacao/{id_plano}/{id}/{utz}")
@Produces("application/json")
public int getPA_MOV_CABAnularAssociacao(
        @PathParam("id") String id,
        @PathParam("id_plano") String id_plano,
        @PathParam("utz") String utz) {
    return entityManager.createNativeQuery(
        "UPDATE PE_PLANOS_ASSOCIADOS SET INATIVO = 1, DATA_ANULA = GETDATE(), UTZ_ANULA = " + utz
        + " WHERE ID_PLANO_CAB = " + id + " AND ID_PLANO_ESTRATEGICO = " + id_plano)
        .executeUpdate();
}
```

---

### 4.2 — Atualizar `getPA_MOV_CABAssociarPlanoEstrategico` (SIRB.java ~linha 4796)

Reativar associação anulada se já existir, em vez de criar duplicado.

```java
@GET
@Path("/getPA_MOV_CABAssociarPlanoEstrategico/{id_plano}/{id}")
@Produces("application/json")
public int getPA_MOV_CABAssociarPlanoEstrategico(
        @PathParam("id") String id,
        @PathParam("id_plano") String id_plano) {
    return entityManager.createNativeQuery(
        "IF EXISTS ( SELECT * FROM PE_PLANOS_ASSOCIADOS WHERE ID_PLANO_CAB = " + id
        + " AND ID_PLANO_ESTRATEGICO = " + id_plano + " AND INATIVO = 1 )"
        + " BEGIN"
        + "   UPDATE PE_PLANOS_ASSOCIADOS SET INATIVO = 0, DATA_ANULA = NULL, UTZ_ANULA = NULL"
        + "   WHERE ID_PLANO_CAB = " + id + " AND ID_PLANO_ESTRATEGICO = " + id_plano
        + " END"
        + " ELSE IF NOT EXISTS ( SELECT * FROM PE_PLANOS_ASSOCIADOS WHERE ID_PLANO_CAB = " + id
        + " AND ID_PLANO_ESTRATEGICO = " + id_plano + " )"
        + " BEGIN"
        + "   INSERT INTO PE_PLANOS_ASSOCIADOS (ID_PLANO_CAB, ID_PLANO_ESTRATEGICO)"
        + "   VALUES (" + id + ", " + id_plano + ")"
        + " END")
        .executeUpdate();
}
```

---

### 4.3 — Novos endpoints: Histórico PA_MOV_CAB e PA_MOV_LINHA (SIRB.java)

```java
// Criar histórico de alteração do plano de ação
@POST
@Path("/createPA_MOV_CAB_HISTORICO")
@Consumes("application/json")
@Produces("application/json")
public Response createPA_MOV_CAB_HISTORICO(final List<HashMap<String, String>> dados) {
    HashMap<String, String> d = dados.get(0);
    entityManager.createNativeQuery(
        "INSERT INTO PA_MOV_CAB_HISTORICO (ID_PLANO_CAB, TIPO_ALTERACAO, VALOR_ANTERIOR, VALOR_NOVO, JUSTIFICACAO, ESTADO_PE, DATA_CRIA, UTZ_CRIA)"
        + " VALUES (" + d.get("ID_PLANO_CAB") + ", '" + d.get("TIPO_ALTERACAO") + "', '"
        + d.get("VALOR_ANTERIOR") + "', '" + d.get("VALOR_NOVO") + "', '"
        + d.get("JUSTIFICACAO") + "', '" + d.get("ESTADO_PE") + "', GETDATE(), " + d.get("UTZ_CRIA") + ")")
        .executeUpdate();
    return Response.ok().build();
}

// Obter histórico do plano de ação
@GET
@Path("/getPA_MOV_CAB_HISTORICObyPlano/{id}")
@Produces("application/json")
public List<Object[]> getPA_MOV_CAB_HISTORICObyPlano(@PathParam("id") Integer id) {
    return entityManager.createNativeQuery(
        "SELECT h.ID, h.TIPO_ALTERACAO, h.VALOR_ANTERIOR, h.VALOR_NOVO, h.JUSTIFICACAO,"
        + " h.ESTADO_PE, h.DATA_CRIA, (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES WHERE ID_UTILIZADOR = h.UTZ_CRIA)"
        + " FROM PA_MOV_CAB_HISTORICO h WHERE h.ID_PLANO_CAB = " + id
        + " ORDER BY h.DATA_CRIA DESC")
        .getResultList();
}

// Criar histórico de linha
@POST
@Path("/createPA_MOV_LINHA_HISTORICO")
@Consumes("application/json")
@Produces("application/json")
public Response createPA_MOV_LINHA_HISTORICO(final List<HashMap<String, String>> dados) {
    HashMap<String, String> d = dados.get(0);
    entityManager.createNativeQuery(
        "INSERT INTO PA_MOV_LINHA_HISTORICO (ID_PLANO_CAB, ID_PLANO_LINHA, TIPO_ALTERACAO, DESCRICAO, ESTADO_PLANO_MOMENTO, DATA_CRIA, UTZ_CRIA)"
        + " VALUES (" + d.get("ID_PLANO_CAB") + ", " + d.get("ID_PLANO_LINHA") + ", '"
        + d.get("TIPO_ALTERACAO") + "', '" + d.get("DESCRICAO") + "', '"
        + d.get("ESTADO_PLANO_MOMENTO") + "', GETDATE(), " + d.get("UTZ_CRIA") + ")")
        .executeUpdate();
    return Response.ok().build();
}

// Obter histórico das linhas por plano de ação
@GET
@Path("/getPA_MOV_LINHA_HISTORICObyPlano/{id}")
@Produces("application/json")
public List<Object[]> getPA_MOV_LINHA_HISTORICObyPlano(@PathParam("id") Integer id) {
    return entityManager.createNativeQuery(
        "SELECT h.ID, h.ID_PLANO_LINHA, h.TIPO_ALTERACAO, h.DESCRICAO, h.ESTADO_PLANO_MOMENTO,"
        + " h.DATA_CRIA, (SELECT NOME_UTILIZADOR FROM GER_UTILIZADORES WHERE ID_UTILIZADOR = h.UTZ_CRIA)"
        + " FROM PA_MOV_LINHA_HISTORICO h WHERE h.ID_PLANO_CAB = " + id
        + " ORDER BY h.DATA_CRIA DESC")
        .getResultList();
}
```

---

### 4.4 — Atualizar `PA_MOV_CABDao.getbyidPlanoEstrategico()` (PA_MOV_CABDao.java ~linha 37)

Adicionar campo `ASSOC_INATIVO` no final do SELECT (índice 33) sem alterar os índices existentes.

```java
// Adicionar no final do SELECT, após "a.DATA_ORIGEM":
+ " ,(SELECT ISNULL(xa.INATIVO,0) FROM PE_PLANOS_ASSOCIADOS xa"
+ "   WHERE xa.ID_PLANO_CAB = a.ID_PLANO_CAB AND xa.ID_PLANO_ESTRATEGICO = " + id + ") as ASSOC_INATIVO "
```

> Os planos com associação INATIVO=1 já aparecem no resultado porque a associação ainda existe na tabela.
> O campo ASSOC_INATIVO permite ao frontend saber que está anulada e aplicar o estilo correto.

---

### 4.5 — Atualizar `PA_MOV_CABDao.getPA_MOV_CABbyTIPOASSOCIAR()` (PA_MOV_CABDao.java ~linha 154)

Três alterações para excluir associações anuladas das restrições de visibilidade.

```java
// Linha 190 — adicionar filtro INATIVO na subquery da condição de estado 'P':
// ANTES:
"AND ( (a.ESTADO = 'P' AND a.ID_PLANO_CAB in (select pa.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS pa inner join PE_MOV_CAB pb on pa.ID_PLANO_ESTRATEGICO = pb.ID  WHERE pb.ANO_PLANO !=  @ano AND pa.ID_PLANO_ESTRATEGICO != @id_plano))"
// DEPOIS:
"AND ( (a.ESTADO = 'P' AND a.ID_PLANO_CAB in (select pa.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS pa inner join PE_MOV_CAB pb on pa.ID_PLANO_ESTRATEGICO = pb.ID  WHERE pb.ANO_PLANO !=  @ano AND pa.ID_PLANO_ESTRATEGICO != @id_plano AND ISNULL(pa.INATIVO,0) = 0))"

// Linha 191 — contar apenas associações ativas:
// ANTES:
" or ((select count(xa.ID_PLANO_CAB) from PE_PLANOS_ASSOCIADOS xa where xa.ID_PLANO_CAB = a.ID_PLANO_CAB) = 0 ) and a.ESTADO in ('P','EX') )"
// DEPOIS:
" or ((select count(xa.ID_PLANO_CAB) from PE_PLANOS_ASSOCIADOS xa where xa.ID_PLANO_CAB = a.ID_PLANO_CAB AND ISNULL(xa.INATIVO,0) = 0) = 0 ) and a.ESTADO in ('P','EX') )"

// Linha 192 — excluir apenas associações ativas do NOT IN (permitir re-associar anulados):
// ANTES:
" and a.ID_PLANO_CAB not in (select xa.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS xa where xa.ID_PLANO_ESTRATEGICO = @id_plano) and a.ESTADO not in ('A','E') "
// DEPOIS:
" and a.ID_PLANO_CAB not in (select xa.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS xa where xa.ID_PLANO_ESTRATEGICO = @id_plano AND ISNULL(xa.INATIVO,0) = 0) and a.ESTADO not in ('A','E') "
```

---

## 5. Frontend — Angular

### 5.1 — Novos ficheiros a criar

#### `src/app/entidades/PA_MOV_CAB_HISTORICO.ts`
```typescript
export class PA_MOV_CAB_HISTORICO {
    ID: number;
    ID_PLANO_CAB: number;
    TIPO_ALTERACAO: string;   // 'DATA_OBJETIVO' | 'ESTADO' | 'ADICAO_LINHA'
    VALOR_ANTERIOR: string;
    VALOR_NOVO: string;
    JUSTIFICACAO: string;
    ESTADO_PE: string;
    DATA_CRIA: Date;
    UTZ_CRIA: number;
}
```

#### `src/app/entidades/PA_MOV_LINHA_HISTORICO.ts`
```typescript
export class PA_MOV_LINHA_HISTORICO {
    ID: number;
    ID_PLANO_CAB: number;
    ID_PLANO_LINHA: number;
    TIPO_ALTERACAO: string;   // 'ADICAO' | 'ANULACAO' | 'ALTERACAO_DATA'
    DESCRICAO: string;
    ESTADO_PLANO_MOMENTO: string;
    DATA_CRIA: Date;
    UTZ_CRIA: number;
}
```

#### `src/app/servicos/pa-mov-cab-historico.service.ts`
```typescript
@Injectable()
export class PAMOVCABHISTORICOService {
    constructor(private http: HttpClient) { }

    create(data: any) {
        return this.http.post(webUrl.host + '/rest/sirb/createPA_MOV_CAB_HISTORICO',
            JSON.stringify([data]), { headers });
    }

    getByPlano(id: number): Observable<any[]> {
        return this.http.get<any[]>(webUrl.host + '/rest/sirb/getPA_MOV_CAB_HISTORICObyPlano/' + id);
    }
}
```

#### `src/app/servicos/pa-mov-linha-historico.service.ts`
```typescript
@Injectable()
export class PAMOVLINHAHISTORICOService {
    constructor(private http: HttpClient) { }

    create(data: any) {
        return this.http.post(webUrl.host + '/rest/sirb/createPA_MOV_LINHA_HISTORICO',
            JSON.stringify([data]), { headers });
    }

    getByPlano(id: number): Observable<any[]> {
        return this.http.get<any[]>(webUrl.host + '/rest/sirb/getPA_MOV_LINHA_HISTORICObyPlano/' + id);
    }
}
```

---

### 5.2 — Ficheiros a atualizar (entidades)

#### `src/app/entidades/PA_MOV_LINHA.ts`
Adicionar campos:
```typescript
data_ANULA: Date;
utz_ANULA: number;
inativo: boolean;
```

---

### 5.3 — Atualizar `src/app/servicos/pa-mov-cab.service.ts`

Adicionar método (após `getPA_MOV_CABRemoverPlanoEstrategico`):
```typescript
getPA_MOV_CABAnularAssociacao(idplano: any, id: any, utz: any): Observable<any> {
    const url = webUrl.host + '/rest/sirb/getPA_MOV_CABAnularAssociacao/' + idplano + '/' + id + '/' + utz;
    return this.http.get<any>(url);
}
```

---

### 5.4 — `form-planos-estrategicos.component.ts`

#### R1 + R2 — Justificação alteração de `data_OBJETIVO` do plano (estado=P)

Adicionar propriedades:
```typescript
displayJustificacaoDataObjetivoPlano: boolean;
justificacaoDataObjetivoPlano: string;
dataObjetivoPlanoAnterior: any;
```

Adicionar método interceptor (chamar no `(ngModelChange)` do campo data_OBJETIVO do plano de ação):
```typescript
onDataObjetivoPlanoChange(novaData: any) {
    // Só pede justificação se o PE estiver em estado Planeado e o plano já existir
    if (this.estado == 'P' && this.plano != null) {
        this.dataObjetivoPlanoAnterior = this.data_OBJETIVO;
        this.data_OBJETIVO = novaData;
        this.justificacaoDataObjetivoPlano = null;
        this.displayJustificacaoDataObjetivoPlano = true;
    }
}

gravarJustificacaoDataObjetivoPlano() {
    var hist = {
        ID_PLANO_CAB: this.plano.id_PLANO_CAB,
        TIPO_ALTERACAO: 'DATA_OBJETIVO',
        VALOR_ANTERIOR: this.dataObjetivoPlanoAnterior
            ? this.formatDate(this.dataObjetivoPlanoAnterior) : '',
        VALOR_NOVO: this.data_OBJETIVO
            ? this.formatDate(this.data_OBJETIVO) : '',
        JUSTIFICACAO: this.justificacaoDataObjetivoPlano,
        ESTADO_PE: this.estado,
        UTZ_CRIA: this.user
    };
    this.PAMOVCABHISTORICOService.create(hist).subscribe(
        () => { this.displayJustificacaoDataObjetivoPlano = false; },
        error => { console.log(error); });
}

cancelarJustificacaoDataObjetivoPlano() {
    // Reverter a data para o valor anterior
    this.data_OBJETIVO = this.dataObjetivoPlanoAnterior;
    this.displayJustificacaoDataObjetivoPlano = false;
}
```

#### R2 — Histórico de mudanças de estado do PE

Nos pontos de transição (lançar plano, validar, fechar, cancelar), chamar o novo serviço **adicionalmente** ao `gravartabela_historico()` existente para registar em `PA_MOV_CAB_HISTORICO` o estado anterior → novo, com o ID_PLANO_CAB do plano de ação afetado.

#### R4 — Registo histórico ao adicionar linha (estado PE=Planeado)

Em `gravalinhasPLANOACOES()`, após gravar cada linha nova com sucesso, se `this.estado == 'P'`:
```typescript
// Após criar a linha com sucesso (obter o ID da nova linha):
if (this.estado == 'P') {
    var hist = {
        ID_PLANO_CAB: id_plano_cab,
        ID_PLANO_LINHA: novaLinha.id_PLANO_LINHA,
        TIPO_ALTERACAO: 'ADICAO',
        DESCRICAO: 'Adicionada nova linha: ' + accoes.descricao,
        ESTADO_PLANO_MOMENTO: this.estado,
        UTZ_CRIA: this.user
    };
    this.PAMOVLINHAHISTORICOService.create(hist).subscribe();
}
```

#### R5 — Anular linha (soft delete) em vez de apagar

Substituir `apagar_linha()`:
```typescript
anular_linha(index: any) {
    var tab = this.tabelaaccoes[index];
    if (tab.id_PLANO_LINHA == null) {
        // Linha não gravada: remove do array
        this.tabelaaccoes = this.tabelaaccoes.slice(0, index)
            .concat(this.tabelaaccoes.slice(index + 1));
        this.tabelaaccoes = this.tabelaaccoes.slice();
        return;
    }
    // Linha gravada: soft delete
    var linha: any = new PA_MOV_LINHA;
    linha.id_PLANO_LINHA = tab.id_PLANO_LINHA;
    linha.id_PLANO_CAB = tab.id_PLANO_CAB || this.plano?.id_PLANO_CAB;
    linha.inativo = true;
    linha.data_ANULA = new Date();
    linha.utz_ANULA = this.user;
    linha.estado = 'A';

    this.PAMOVLINHAService.update(linha).then(
        () => {
            // Atualizar tarefa associada para Anulada
            this.atualizaestadoTarefa(tab.id_PLANO_LINHA, 'A');
            // Gravar no histórico das linhas
            var hist = {
                ID_PLANO_CAB: linha.id_PLANO_CAB,
                ID_PLANO_LINHA: tab.id_PLANO_LINHA,
                TIPO_ALTERACAO: 'ANULACAO',
                DESCRICAO: 'Linha anulada: ' + tab.descricao,
                ESTADO_PLANO_MOMENTO: this.estado,
                UTZ_CRIA: this.user
            };
            this.PAMOVLINHAHISTORICOService.create(hist).subscribe();
            // Marcar visualmente (não remove do array)
            tab.inativo = true;
            tab.estado = this.getestado_accao('A');
            this.tabelaaccoes = this.tabelaaccoes.slice();
        },
        error => {
            console.log(error);
            this.messageService.add({
                severity: 'error', summary: 'Erro', detail: 'Não foi possível anular a linha!'
            });
        });
}
```

> **Nota:** Verificar se `PAMOVLINHAService` tem método `update()`. Se não existir, criar endpoint `updatePA_MOV_LINHA` no backend.

#### R9 — `PE_PLANOS_ASSOCIADOS` soft delete

**Alterar `removeplano()`** (~linha 2739):
```typescript
removeplano(index: any, id: any) {
    var tab = this.tabelaplanos[index];
    if (tab.id_PLANO_ESTRATEGICO == null) {
        // Não gravado: remove do array
        this.tabelaplanos = this.tabelaplanos.slice(0, index)
            .concat(this.tabelaplanos.slice(index + 1));
        this.atualizanumeracao();
        this.calcucarpercentagem_conclusao();
    } else {
        // Gravado: anular associação, manter visível
        this.PAMOVCABService.getPA_MOV_CABAnularAssociacao(this.id_PLANO, id, this.user).subscribe(
            () => {
                tab.inativo = true;
                tab.cod_estado = 'A';
                tab.estado = this.getestado('A');
                this.tabelaplanos = this.tabelaplanos.slice();
                this.atualizanumeracao();
                this.calcucarpercentagem_conclusao();
            },
            error => {
                console.log(error);
                this.messageService.add({
                    severity: 'error', summary: 'Erro', detail: 'Não foi possível anular!'
                });
            });
    }
}
```

**Alterar `removerassociacao()`** (~linha 1353):
```typescript
removerassociacao() {
    this.anular();
    for (var x in this.tabelaplanos) {
        this.PAMOVCABService.getPA_MOV_CABAnularAssociacao(
            this.id_PLANO, this.tabelaplanos[x].id, this.user
        ).subscribe(res => { }, error => { console.log(error); });
    }
}
```

**Atualizar `atualizanumeracao()`**:
```typescript
atualizanumeracao() {
    this.numero = 1.1;
    for (var x in this.tabelaplanos) {
        if (this.tabelaplanos[x].inativo) continue; // ignorar anulados
        // ... lógica existente
    }
}
```

**Atualizar `calcucarpercentagem_conclusao()`**:
```typescript
// Filtrar anulados antes de calcular
var planosAtivos = this.tabelaplanos.filter(p => !p.inativo);
// Usar planosAtivos no cálculo em vez de this.tabelaplanos
```

**Mapear campo `ASSOC_INATIVO` (índice 33)** na função que carrega `tabelaplanos`:
```typescript
inativo: response[x][33] == 1,
```

---

### 5.5 — Template HTML (`form-planos-estrategicos.component.html`)

#### Dialog justificação data objetivo do plano (R1)
```html
<p-dialog header="Justificação Alteração Data Objetivo"
    [(visible)]="displayJustificacaoDataObjetivoPlano"
    [closable]="false" [modal]="true" [style]="{width: '500px'}">
    <form (ngSubmit)="gravarJustificacaoDataObjetivoPlano()">
        <div class="form-group">
            <label>Data Anterior: <strong>{{ dataObjetivoPlanoAnterior | date:'dd/MM/yyyy' }}</strong></label>
        </div>
        <div class="form-group">
            <label>Nova Data: <strong>{{ data_OBJETIVO | date:'dd/MM/yyyy' }}</strong></label>
        </div>
        <div class="form-group">
            <label>Justificação *</label>
            <textarea class="form-control" [(ngModel)]="justificacaoDataObjetivoPlano"
                name="justificacaoDataObjetivoPlano" rows="4" required></textarea>
        </div>
        <p-footer>
            <button type="submit" class="btn btn-primary"
                [disabled]="!justificacaoDataObjetivoPlano">Confirmar</button>
            <button type="button" class="btn btn-default"
                (click)="cancelarJustificacaoDataObjetivoPlano()">Cancelar</button>
        </p-footer>
    </form>
</p-dialog>
```

#### Linhas anuladas na tabela de ações (R5)
```html
<!-- Estilo na linha da tabela -->
<tr [ngStyle]="{
    'background-color': linha.inativo ? '#f5f5f5' : '',
    'color': linha.inativo ? '#aaa' : '',
    'text-decoration': linha.inativo ? 'line-through' : ''
}">

<!-- Badge de anulado -->
<td>
    {{ linha.estado }}
    <span *ngIf="linha.inativo" class="label label-default"
        style="text-decoration:none; margin-left:4px; vertical-align:middle;">Anulada</span>
</td>

<!-- Botão Anular (substituir botão Apagar) -->
<button type="button" class="btn btn-warning btn-xs"
    [disabled]="!modoedicao_plano || linha.inativo
        || ['C','I','V','R','A'].includes(linha?.estado_cod)"
    title="Anular linha"
    (click)="anular_linha(index)">
    <i class="pi pi-ban"></i>
</button>
```

#### Planos associados anulados na tabela de planos (R9)
```html
<!-- Estilo da linha do plano associado -->
<tr [ngStyle]="{
    'background-color': plano.inativo ? '#f5f5f5' : '',
    'color': plano.inativo ? '#aaa' : '',
    'text-decoration': plano.inativo ? 'line-through' : ''
}">
    <td>
        {{ plano.estado }}
        <span *ngIf="plano.inativo" class="label label-default"
            style="text-decoration:none; margin-left:4px;">Anulado</span>
    </td>
    <!-- Botão Anular associação -->
    <td>
        <button type="button" class="btn btn-warning btn-xs"
            [disabled]="!modoedicao || plano.inativo"
            title="Anular associação"
            (click)="removerplano(i, plano.id)">
            <i class="pi pi-ban"></i>
        </button>
    </td>
</tr>
```

---

### 5.6 — `lista-planos-estrategicos.component` (R3, R7, R8)

#### R3 — Ordenação por data de criação mais recente
No template, na `<p-table>` da lista principal:
```html
<p-table ... [defaultSortField]="'data_registo'" [defaultSortOrder]="-1" ...>
```

O SP `PE_GET_PLANOS_ESTRATEGICOS` já ordena por `DATA_CRIA DESC` — confirmar após validação do SP.

#### R7 + R8 — Ícone de histórico com cor dinâmica

A query `PE_GET_PLANOS_ESTRATEGICOS` deve retornar dois campos adicionais (ver secção 6):
- `TEM_ALTERACAO_DATA` — existe registo em `PA_MOV_CAB_HISTORICO` com `TIPO_ALTERACAO='DATA_OBJETIVO'`
- `TEM_LINHA_ANULADA` — existe linha com `INATIVO=1` no plano

No componente:
```typescript
getHistoricoIconCor(plano: any): string {
    if (plano.tem_linha_anulada) return '#e53935';   // vermelho
    if (plano.tem_alteracao_data) return '#ff6f00';  // laranja
    return '#9e9e9e';                                  // cinza
}

abrirHistorico(plano: any) {
    this.historicoPlanoDados = [];
    this.historicoLinhasDados = [];
    this.PAMOVCABHISTORICOService.getByPlano(plano.id).subscribe(
        (res: any) => {
            this.historicoPlanoDados = res.map((r: any) => ({
                tipo: r[1], anterior: r[2], novo: r[3],
                justificacao: r[4], estado_pe: r[5],
                data: r[6], utilizador: r[7]
            }));
        });
    this.PAMOVLINHAHISTORICOService.getByPlano(plano.id).subscribe(
        (res: any) => {
            this.historicoLinhasDados = res.map((r: any) => ({
                id_linha: r[1], tipo: r[2], descricao: r[3],
                estado_momento: r[4], data: r[5], utilizador: r[6]
            }));
        });
    this.displayHistorico = true;
}
```

No template, coluna com ícone de histórico nos planos de ação (filhos):
```html
<td style="width:40px; text-align:center;">
    <i class="pi pi-history"
        [style.color]="getHistoricoIconCor(plano)"
        style="cursor:pointer; font-size:1.3rem;"
        title="Ver Histórico"
        (click)="abrirHistorico(plano, $event)">
    </i>
</td>
```

Dialog de histórico:
```html
<p-dialog header="Histórico do Plano de Ação" [(visible)]="displayHistorico"
    [style]="{width:'850px'}" [modal]="true">
    <p-tabView>
        <p-tabPanel header="Histórico do Plano">
            <p-table [value]="historicoPlanoDados" [responsive]="true">
                <ng-template pTemplate="header">
                    <tr>
                        <th>Data</th>
                        <th>Utilizador</th>
                        <th>Tipo Alteração</th>
                        <th>Anterior</th>
                        <th>Novo</th>
                        <th>Justificação</th>
                    </tr>
                </ng-template>
                <ng-template pTemplate="body" let-h>
                    <tr>
                        <td>{{ h.data | date:'dd/MM/yyyy HH:mm' }}</td>
                        <td>{{ h.utilizador }}</td>
                        <td>{{ h.tipo }}</td>
                        <td>{{ h.anterior }}</td>
                        <td>{{ h.novo }}</td>
                        <td>{{ h.justificacao }}</td>
                    </tr>
                </ng-template>
            </p-table>
        </p-tabPanel>
        <p-tabPanel header="Histórico das Linhas">
            <p-table [value]="historicoLinhasDados" [responsive]="true">
                <ng-template pTemplate="header">
                    <tr>
                        <th>Data</th>
                        <th>Utilizador</th>
                        <th>Tipo</th>
                        <th>Descrição</th>
                        <th>Estado Plano no Momento</th>
                    </tr>
                </ng-template>
                <ng-template pTemplate="body" let-h>
                    <tr>
                        <td>{{ h.data | date:'dd/MM/yyyy HH:mm' }}</td>
                        <td>{{ h.utilizador }}</td>
                        <td>{{ h.tipo }}</td>
                        <td>{{ h.descricao }}</td>
                        <td>{{ h.estado_momento }}</td>
                    </tr>
                </ng-template>
            </p-table>
        </p-tabPanel>
    </p-tabView>
</p-dialog>
```

---

## 6. Validação de Stored Procedures

Após todas as alterações de base de dados e lógica, validar os seguintes SPs para garantir que reflectem corretamente os novos campos e regras.

### `PE_GET_PLANOS_ESTRATEGICOS`
**Chamado por:** `PE_MOV_CABDao.getallbyTIPO()` e `getallbyDEPT()`
**Parâmetros:** `@TIPO`, `@EM_ATRASO`, `@USER`, `@DEPARTAMENTO` (opcional), `@ANO` (opcional)

**Pontos a validar:**
- [ ] A query que conta planos de ação associados (`PE_PLANOS_ASSOCIADOS`) deve excluir registos com `INATIVO=1`
- [ ] A percentagem de conclusão calculada deve excluir planos de ação anulados e linhas com `INATIVO=1`
- [ ] Ordenação por `DATA_CRIA DESC` no resultado final (R3)
- [ ] Adicionar ao resultado final dois campos calculados para os ícones (R8):
  ```sql
  ,CASE WHEN EXISTS (
      SELECT 1 FROM PA_MOV_CAB_HISTORICO hc
      INNER JOIN PE_PLANOS_ASSOCIADOS pa ON hc.ID_PLANO_CAB = pa.ID_PLANO_CAB
      WHERE pa.ID_PLANO_ESTRATEGICO = pe.ID AND hc.TIPO_ALTERACAO = 'DATA_OBJETIVO'
      AND ISNULL(pa.INATIVO,0) = 0
  ) THEN 1 ELSE 0 END as TEM_ALTERACAO_DATA
  ,CASE WHEN EXISTS (
      SELECT 1 FROM PA_MOV_LINHA pl
      INNER JOIN PE_PLANOS_ASSOCIADOS pa ON pl.ID_PLANO_CAB = pa.ID_PLANO_CAB
      WHERE pa.ID_PLANO_ESTRATEGICO = pe.ID AND pl.INATIVO = 1
      AND ISNULL(pa.INATIVO,0) = 0
  ) THEN 1 ELSE 0 END as TEM_LINHA_ANULADA
  ```

---

### `PE_GET_ANALISE_CONTADORES`
**Chamado por:** `SIRB_2.java → PE_GET_ANALISE_CONTADORES`
**Parâmetros:** `@ANO`

**Pontos a validar:**
- [ ] Contadores de ações planeadas: excluir `PA_MOV_LINHA` com `INATIVO=1`
- [ ] Contadores de planos em execução: excluir `PE_PLANOS_ASSOCIADOS` com `INATIVO=1`
- [ ] Contador de ações em atraso: excluir linhas anuladas

---

### `PE_GET_ANALISE_GRAFICO`
**Chamado por:** `SIRB_2.java → PE_GET_ANALISE_GRAFICO`
**Parâmetros:** `@ANO`

**Pontos a validar:**
- [ ] Dados de Planeadas/Concluídas/Em Atraso por departamento: excluir `PA_MOV_LINHA` com `INATIVO=1`
- [ ] Associações de planos: excluir `PE_PLANOS_ASSOCIADOS` com `INATIVO=1`

---

### `PE_GET_ACOES_EM_ATRASO`
**Chamado por:** `SIRB_2.java → PE_GET_ACOES_EM_ATRASO`
**Parâmetros:** `@ANO`, `@PAGINA`

**Pontos a validar:**
- [ ] Excluir `PA_MOV_LINHA` com `INATIVO=1` da listagem de ações em atraso
- [ ] Excluir `PE_PLANOS_ASSOCIADOS` com `INATIVO=1`

---

### `PE_GET_ULTIMAS_ACOES_CONCLUIDAS`
**Chamado por:** `SIRB_2.java → PE_GET_ULTIMAS_ACOES_CONCLUIDAS`
**Parâmetros:** `@ANO`, `@PAGINA`

**Pontos a validar:**
- [ ] Excluir `PA_MOV_LINHA` com `INATIVO=1` da listagem de ações concluídas
- [ ] Verificar que ações anuladas (estado='A') não aparecem como concluídas

---

### `PE_GRAVAR_SNAPSHOT_SEMANAL`
**Chamado por:** Job agendado (não referenciado no código Java — provavelmente SQL Agent Job)
**Parâmetros:** a confirmar

**Pontos a validar:**
- [ ] O snapshot semanal deve capturar o estado correto, excluindo `PE_PLANOS_ASSOCIADOS` com `INATIVO=1`
- [ ] As métricas de progresso devem excluir `PA_MOV_LINHA` com `INATIVO=1`
- [ ] Confirmar se o SP captura as novas tabelas `PA_MOV_CAB_HISTORICO` / `PA_MOV_LINHA_HISTORICO`
- [ ] Confirmar agendamento do SQL Agent Job (não encontrado referência no backend Java)

---

## 7. Ordem de Implementação

### Fase 1 — Base de Dados
1. [ ] Adicionar campos a `PE_PLANOS_ASSOCIADOS` (INATIVO, DATA_ANULA, UTZ_ANULA)
2. [ ] Adicionar campos a `PA_MOV_LINHA` (INATIVO, DATA_ANULA, UTZ_ANULA)
3. [ ] Criar tabela `PA_MOV_CAB_HISTORICO`
4. [ ] Criar tabela `PA_MOV_LINHA_HISTORICO`

### Fase 2 — Backend
5. [ ] Novo endpoint `getPA_MOV_CABAnularAssociacao` (SIRB.java)
6. [ ] Atualizar `getPA_MOV_CABAssociarPlanoEstrategico` — reativar anulados (SIRB.java)
7. [ ] Novos endpoints histórico PA_MOV_CAB e PA_MOV_LINHA (SIRB.java)
8. [ ] Atualizar `getbyidPlanoEstrategico` — adicionar `ASSOC_INATIVO` índice 33 (PA_MOV_CABDao.java)
9. [ ] Atualizar `getPA_MOV_CABbyTIPOASSOCIAR` — filtrar `INATIVO=0` (PA_MOV_CABDao.java)

### Fase 3 — Frontend (Entidades e Serviços)
10. [ ] Criar `PA_MOV_CAB_HISTORICO.ts`
11. [ ] Criar `PA_MOV_LINHA_HISTORICO.ts`
12. [ ] Atualizar `PA_MOV_LINHA.ts` — adicionar campos inativo/data_ANULA/utz_ANULA
13. [ ] Criar `pa-mov-cab-historico.service.ts`
14. [ ] Criar `pa-mov-linha-historico.service.ts`
15. [ ] Atualizar `pa-mov-cab.service.ts` — adicionar `getPA_MOV_CABAnularAssociacao`
16. [ ] Registar novos serviços em `app.module.ts`

### Fase 4 — Frontend (Componente Form)
17. [ ] R9 — Alterar `removeplano()` — soft delete associação
18. [ ] R9 — Alterar `removerassociacao()` — usar anular
19. [ ] R9 — Atualizar `atualizanumeracao()` e `calcucarpercentagem_conclusao()` — ignorar anulados
20. [ ] R9 — Mapear `ASSOC_INATIVO` índice 33 no carregamento de `tabelaplanos`
21. [ ] R5 — Criar `anular_linha()` em substituição de `apagar_linha()`
22. [ ] R1+R2 — Adicionar dialog justificação data objetivo plano + método `onDataObjetivoPlanoChange()`
23. [ ] R4 — Registar histórico ao adicionar linha (estado=Planeado)
24. [ ] R6 — Rever e corrigir campos de datas no dialog do plano de ação

### Fase 5 — Frontend (Template HTML + Lista)
25. [ ] R9 — Estilo visual planos anulados + badge + botão anular na tabela de planos
26. [ ] R5 — Estilo visual linhas anuladas + badge + botão anular na tabela de ações
27. [ ] R1 — Dialog justificação data objetivo no HTML
28. [ ] R3 — Ordenação padrão por data_registo DESC na p-table
29. [ ] R7+R8 — Coluna ícone histórico com cor dinâmica nas listas
30. [ ] R7 — Dialog de histórico (tabs: Plano / Linhas)

### Fase 6 — Validação Stored Procedures
31. [ ] Validar e corrigir `PE_GET_PLANOS_ESTRATEGICOS`
32. [ ] Validar e corrigir `PE_GET_ANALISE_CONTADORES`
33. [ ] Validar e corrigir `PE_GET_ANALISE_GRAFICO`
34. [ ] Validar e corrigir `PE_GET_ACOES_EM_ATRASO`
35. [ ] Validar e corrigir `PE_GET_ULTIMAS_ACOES_CONCLUIDAS`
36. [ ] Validar e corrigir `PE_GRAVAR_SNAPSHOT_SEMANAL`
