# Plano — Correções e Melhorias (PDF 01-07-2026)

Fonte: `Correções e Melhorias - 01-07-2026.pdf` + anexos `Exemplo Análise.xlsx` (Bastidor) e `Análise Exemplo (média e desvio padrão).xlsx` (Salas de Mistura).

Projetos envolvidos:
| Área | Frontend | Backend |
|---|---|---|
| SGIID (Pintura) | `C:\work\Projects\GITLAB PROJECTS\sgiid` | `C:\work\Workspace\sgiid` (endpoints em `pt.example.rest.SIRB_3.java`, reports em `SIRB.java` `getFILEPOST` + `ReportGenerator`) |
| Manutenções | `C:\work\Projects\GITLAB PROJECTS\app-manu` | `C:\work\Workspace\app_manutencao` |

**Dependência transversal:** os exports/impressões das receitas são templates **JasperReports fora do repo** (servidor, `c:/{filepath}/relatorios/jasperfiles/`): `print_receitas.jrxml`, `export_receitas.jrxml`, `lista_resumo_receitas.jrxml`, `lista_dados_pecas_receitas.jrxml`, `lista_receitas.jrxml`. Itens que os alteram exigem acesso a esses ficheiros no servidor.

---

## Fase 1 — Correções rápidas (bugs) — ✅ implementada em 2026-07-02

Estado: 1.1 feito via SP (executar `MAN_GET_ALL_PIPE_2026-07-02.sql` na BD — na raiz do app-manu); 1.2 corrigido em programadas+não-programadas; 1.3 feito; 1.4 corrigido com fixes defensivos (seed UV + fallback `??` no gravar).

### 1.1 Manutenções: vistos das ações no popup "Iniciar Manutenção" (app-manu)
**Causa identificada:** o popup (em `pages/main-view` e `pages/painel-pendentes`) recebe as ações da SP `MAN_GET_ALL_PIPE` como string `nome||tempoEstimado||tempoReal` — o campo `MAN_MOV_MANUTENCAO_ACCOES.REALIZADA` não vem no payload. O ecrã de edição (`pages/manutencao`, checkbox `REALIZADA`) usa outro endpoint (`getMAN_MOV_MANUTENCAO_ACCOESbyid/{id}`) que já traz o campo.
**Solução (recomendada):** ao abrir o popup, carregar as ações via `MANMOVMANUTENCAOACCOESService.getbyid(idPedido)` (endpoint existente, `REALIZADA` no índice 3) e mostrar um visto (ícone check) atrás de cada ação realizada. Aplicar nos dois componentes (`main-view.component` e `painel-pendentes.component`).
**Alternativa:** acrescentar `REALIZADA` à string agregada na SP `MAN_GET_ALL_PIPE` (4.º token) — exige alteração de SP na BD.
**Esforço:** baixo.

### 1.2 Receitas: pote por defeito seleciona as válvulas todas
Sintoma: se o utilizador não mexe no pote pré-selecionado, a válvula fica com todas (ex. B4 → V4,V5,V6); só recalcula ao re-selecionar.
Local: `receitasForm.component.ts` — `atualizardadospotes()` (linha ~1598) só corre no evento `onChange` do `p-multiSelect`; o valor inicial/duplicado nunca é recalculado.
**Solução:** recalcular `valvula_pote`/`valvula_catalisador` a partir dos potes selecionados também no carregamento (`carregarLinhas`) e antes de gravar (`gravarlinhas`), em vez de confiar no texto persistido.
**Esforço:** baixo.

### 1.3 Receitas: diluente deixar de ser obrigatório
Local: `receitasForm.component.html` linhas ~915-917 — `<select class="obrigatorio_tabela" [required]="product.id_referencia_C == null">`.
**Solução:** remover o `required` (as validações dos diluentes 2-5 já estão comentadas). Confirmar que gravar/exportar toleram diluente null.
**Esforço:** muito baixo.

### 1.4 Receitas: perda de dados ao atualizar versão (ciclos, P.entrada/P.retorno/TºC, UV radiation)
**Causa real (BD, não frontend):** as novas versões também são geradas pelas SPs `PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO` (quando muda a % diluição de um produto) e `PIN_UPDATE_PIN_DIC_PRE_SET` (quando muda um pre-set). A SP da % diluição:
- não copiava `CICLO_PRIMARIO/CICLO_BASE/CICLO_VERNIZ` no INSERT do cabeçalho → ciclos desapareciam;
- não copiava a tabela `PIN_MOV_UV_RADIATION` → "Nenhum registo foi encontrado" e sem edição;
- faltava `a.VERSAO = c.VERSAO` no join do cursor;
- o bloco "ATUALIZA CONSUMOS" corria só para a última receita do cursor.
**Fix:** `PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO_2026-07-03.sql` (raiz sgiid) — executar na BD. Reparação de dados já perdidos: `PIN_RECEITAS_REPARACAO_DADOS_2026-07-03.sql` (diagnóstico + repair comentado). A SP do PRE_SET está correta. Nenhuma alteração de código frontend (revertidas as alterações defensivas de 2026-07-02).
⚠️ **Fases 2/3:** ao adicionar `HUMIDADE_MIN/MAX` (2.2) e `FILTRO_ROBOT`/`COR_IMPRESSAO`/pressões (3.x), acrescentar os novos campos aos INSERTs destas DUAS SPs, senão perdem-se ao versionar.

---

## Fase 2 — Campos novos + Análises (Salas de Mistura e Bastidor) — ✅ implementada em 2026-07-03

Estado: tudo implementado (BD: executar `PIN_MELHORIAS_FASE2_2026-07-03.sql`; backend: rebuild/deploy no Eclipse). Novos ecrãs em `registo_salas_mistura/analise` e `registo_lancamento_bastidor/analise` (botão gráfico no cabeçalho das listas). Cálculos: Média + Desvio Padrão (população) por Referência Cor; LIC/LSC = média ± 3σ (confirmado pelo excel exemplo); LI/LS do produto (viscosidade) e da receita (temp/humidade). Export client-side com `exceljs` (dependência nova): 1 folha por grupo com tabela + os 2 gráficos como imagem.

Modelo a seguir: "Quadro de análise" da cromagem — `paginas/home-modulo/gestao-banhos/gestao-banhos.component.*` (filtros + tabela + 2 `p-chart` line + export). Os cálculos Média/Desvio Padrão/LIC/LSC serão feitos no cliente (a cromagem usa limites fixos de BD; aqui os excel pedem estatística calculada).

### 2.1 Ficha do Produto: Limite Inferior/Superior de Viscosidade
Campos numéricos, 2 casas decimais.
- BD: `ALTER TABLE PIN_DIC_PRODUTOS ADD LIMITE_INF_VISCOSIDADE DECIMAL(10,2), LIMITE_SUP_VISCOSIDADE DECIMAL(10,2)`.
- Backend: `entity/PIN_DIC_PRODUTOS.java` — 2 campos `BigDecimal` + `@JsonProperty` + getters/setters (padrão `TAXA_MISTURA`). Endpoints sem alteração.
- Frontend: `entidades/PIN_DIC_PRODUTOOS.ts`→ `PIN_DIC_PRODUTOS.ts` + `produtosForm.component.ts/html` (ler em `inicia()` ~338, gravar nos 2 ramos ~419/458; inputs com `step 0.01`).

### 2.2 Receita: Limite Inferior/Superior de Humidade (%)
- BD: `ALTER TABLE PIN_MOV_RECEITAS ADD HUMIDADE_MIN DECIMAL(5,2), HUMIDADE_MAX DECIMAL(5,2)`.
- Backend: `entity/PIN_MOV_RECEITAS.java` (padrão `TEMPERATURA_MIN/MAX`, linhas 99-102/419-437).
- Frontend: `entidades/PIN_MOV_RECEITAS.ts` (junto de TEMPERATURA_MIN/MAX, linhas 43-44) + `receitasForm.component.html` (~404-413) + bindings no `.ts`. Garantir cópia em nova versão (ligação ao 1.4).

### 2.3 Análise — Registo Salas de Mistura
Nova rota/ecrã (ex. `registo_salas_mistura/analise`), botão "Análise" na lista + entrada de menu (node novo sob Engenharia Pintura).
- **Filtros:** Receita (dropdown `PIN_MOV_RECEITAS`), Cabine (`PIN_DIC_CABINES`), Data Início, Data Fim.
- **Dados:** novo endpoint `getPIN_DIC_REGISTO_SALAS_MISTURA_ANALISE/{idReceita}/{idCabine}/{ini}/{fim}` em `SIRB_3.java` + método no `PIN_DIC_REGISTO_SALAS_MISTURADao` (native query: registo + `NOME_PROJETO` + `NOME_CABINE` + `LIMITE_INF/SUP_VISCOSIDADE` do produto da referência cor).
- **Lista** (colunas do excel, agrupada por Referência Cor como as sheets): Projeto, Cabine, Data/Hora, Temperatura (ºC), Viscosidade, **Média**, **Desvio Padrão**, **LIC** (média−desvio), **LSC** (média+desvio), **LI**, **LS** (limites do produto), Referência Cor, Referência Diluente. Média/desvio calculados no cliente sobre o conjunto filtrado.
- **2 gráficos** (`p-chart` line, padrão gestao-banhos): Viscosidade ao longo do tempo com linhas horizontais Média/LIC/LSC/LI/LS; Temperatura ao longo do tempo.
- **Export Excel (2.5).**

### 2.4 Análise — Registo Lançamento Bastidor
Nova rota/ecrã (ex. `registo_lancamento_bastidor/analise`).
- **Filtros:** Referência/produto (= Receita, dropdown), Data Início, Data Fim.
- **Dados:** novo endpoint `getPIN_DIC_REGISTO_BASTIDOR_ANALISE/{idReceita}/{ini}/{fim}` + método no `PIN_DIC_REGISTO_BASTIDORDao` (registos das 3 cabines "des-pivotados" ou 3 blocos, + `TEMPERATURA_MIN/MAX` e `HUMIDADE_MIN/MAX` da receita).
- **Lista** (excel: 1 secção por cabine — Primário/Base/Verniz): Projeto, Data, Hora, Cabine, Temperatura (ºC), LI T, LS T (receita), Humidade (%), LI H, LS H (novos campos 2.2).
- **2 gráficos:** Temperatura com LI T/LS T; Humidade com LI H/LS H.
- **Export Excel (2.5).**

### 2.5 Export Excel "conforme apresentado no ecrã" (lista + gráficos)
O pedido exige incluir os gráficos tal como aparecem — o caminho Jasper (usado na cromagem) não replica os gráficos do ecrã. **Proposta:** export client-side:
- Lista via `xlsx-with-styles` (já usado, ex. `cores_acabamentos.component.ts exportExcel()`);
- Gráficos: `canvas.toDataURL('image/png')` do Chart.js. Como `xlsx` não embebe imagens, adicionar **`exceljs`** (suporta `addImage`) só para estes 2 ecrãs — 1 sheet com tabela + imagens dos gráficos por baixo.
- Alternativa sem dependência nova: exportar lista em xlsx + gráficos em PNG separado (a validar com o cliente).

---

## Fase 3 — Melhorias Receitas (frontend + BD) — ✅ implementada em 2026-07-03

Estado: 3.1 potes automáticos ao escolher cor/acab (query `getbytipoAll2` passou a devolver IDS_POTE por tipo de cabine); 3.2 coluna Filtro Robot (+ SPs); 3.3 catalisador movido para "Sistema de Preparação" (tabela + preview); 3.4 popup Pressões por Programa (tabela `PIN_MOV_RECEITAS_PRESSOES` com 24 campos, CRUD completo, grava com a receita, copiada nas 2 SPs de versões; export do ficheiro "ID371.00" fica na Fase 4); 3.5 color picker "Cor Impressão" no cabeçalho da receita (campo `COR_IMPRESSAO` — usar nos exports na Fase 4); 3.6 coluna Código 100→160px. Build dev OK. SQL consolidado em `PIN_MELHORIAS_FASE2_2026-07-03.sql`.

### 3.1 Potes automáticos a partir da Cor/Acab.
Hoje o multiSelect de potes mostra todos os potes ativos e obriga a escolher, mesmo com `PIN_DIC_CORES_ACABAMENTOS.ID_POTE` (CSV) já definido para a cor.
**Solução:** em `receitasForm.component.ts`, ao escolher a cor/acab. da linha (primário/base/verniz) no modo novo, pré-preencher `product.id_pote` com o CSV de `PIN_DIC_CORES_ACABAMENTOS.ID_POTE` e correr `atualizardadospotes()` para as válvulas. Manter possibilidade de alterar manualmente.
**Esforço:** baixo/médio.

### 3.2 Nova coluna "Filtro Robot" nas linhas da receita
A seguir a "Válvula Produto Nº", secção "Sistema de Preparação/Transferência de Produto".
- BD: `ALTER TABLE PIN_MOV_RECEITAS_LINHAS ADD FILTRO_ROBOT VARCHAR(...)`.
- Backend: `entity/PIN_MOV_RECEITAS_LINHAS.java` + entidade TS.
- Frontend: coluna na tabela de linhas do `receitasForm.component.html` + carregar/gravar no `.ts` (`carregarLinhas`/`gravarlinhas`).
- Incluir nos exports/impressão (Fase 4).

### 3.3 Mover "Catalisador Pote Nº" e "Válvula Catalisador Nº" para "Sistema de Preparação/Transferência de Produto"
Só reordenação de colunas no `receitasForm.component.html` (headers das secções amarelas + ordem das `<td>`). Verificar impressão (Fase 4 — print_receitas).
**Esforço:** baixo.

### 3.4 Popup "Pressões mín/máx por programa"
Novo botão na ficha da receita que abre `p-dialog` com grelha multi-linha:
- Colunas por linha: **Programa** + para cada cabine (Primário, Base, Verniz): P Tinta Entrada min/max, P Tinta Saída min/max, P Catalisador Entrada min/max, P Catalisador Saída min/max (modelo Excel "Pressões (modelo)").
- BD: nova tabela `PIN_MOV_RECEITAS_PRESSOES` (ID, ID_RECEITA, VERSAO, PROGRAMA, 24 colunas de valores, auditoria) — duplicar em nova versão (ligação 1.4).
- Backend: entidade + DAO + CRUD em `SIRB_3.java` (padrão `PIN_MOV_RECEITAS_REFERENCIAS`).
- Frontend: entidade TS + serviço + dialog no `receitasForm`.
- Export tipo "ID371.00 - Pressões por programa e pre-set": export xlsx client-side ou Jasper novo (decidir na Fase 4).
**Esforço:** médio/alto.

### 3.5 Cor de impressão por receita
Modelo: "Acabamento Pormenorizado" (`parametros/tipos-acabamento` — `p-colorPicker`, campo `COR`).
- BD: `ALTER TABLE PIN_MOV_RECEITAS ADD COR_IMPRESSAO VARCHAR(9)`.
- Backend/frontend: campo na entidade + `p-colorPicker` na ficha da receita.
- Usar a cor nas linhas dos exports "receita de preparação de tinta" (Fase 4) — fundo da linha da receita.

### 3.6 Cores/Acab.: aumentar espaço da coluna Código
`tabelas/cores_acabamentos/cores_acabamentos.component.html` — ajustar largura da coluna/filtro. **Esforço:** muito baixo.

---

## Fase 4 — Relatórios/Exports (JasperReports + 2 exports novos) — ✅ implementada em 2026-07-03

Templates em `C:\sgiid\relatorios\jasperfiles` — o backend recompila o `.jrxml` a cada execução. Backups `.bak_2026-07-03` de todos os alterados.
Estado:
- 4.1 `lista_resumo_receitas.jrxml` REGENERADO: secções INFORMAÇÃO RECEITA / SISTEMA DE PINTURA / INFORMAÇÃO DO PRODUTO / RELAÇÃO QTD-MISTURA / SALA DE MISTURAS; removidas colunas a vermelho (fornecedor, cor, master, acabamentos); taxas de mistura com sufixo " %"; coluna nova UNIDADE DE PREPARAÇÃO (header amarelo); fundo das linhas = `COR_IMPRESSAO` da receita; corrigido bug antigo (coluna Ref Diluente 5 mostrava DILUENTE5_C em vez de REFERENCIA5_C).
- 4.2 `lista_dados_pecas_receitas.jrxml`: TEMPO_CICLO (soma) → 3 colunas CICLO PRIMÁRIO/BASE/VERNIZ.
- 4.3 `print_receitas.jrxml`: Hum. Min/Max (Fase 2) + Filtro Robot (linha nova na secção Sistema) + Catalisador Pote/Válvula movidos para a secção Sistema (referências sobem na coluna Informação). Bloco Consumos/Consumo Carga já existia (commit de jun/2026) — validar no teste.
- 4.4 NOVO `consumo_job_cabine.jrxml`: soma consumos rack/carga (COR+CATALISADOR) por JOB × cabine, receitas filtradas na lista.
- 4.5 NOVO `pressoes_programa.jrxml` (ID371.00): pressões por receita/programa com cabeçalho 4 níveis (cabines × P Tinta/P Catalisador × Entrada/Saída × min/max).
- Menu Exportar da lista de receitas: +2 opções (Consumo por JOB por cabine, Pressões por programa).
⚠️ Todos dependem do SQL da Fase 2/3 executado (HUMIDADE, FILTRO_ROBOT, COR_IMPRESSAO, PIN_MOV_RECEITAS_PRESSOES) — impressão/exports dão erro até lá.

### 4.1 `lista_resumo_receitas.jrxml` ("Receita de preparação de tinta")
- Eliminar as colunas marcadas a vermelho no PDF (fornecedor, cor, etc. — confirmar lista exata com o cliente sobre o screenshot).
- Colunas a amarelo passarem a mostrar valores com `%`.
- Acrescentar coluna "Unidade de Preparação" (campo `ID_UNIDADE_PREPARACAO`/tempo agitação das linhas).
- Aplicar `COR_IMPRESSAO` (3.5) como cor de fundo por receita.

### 4.2 `lista_dados_pecas_receitas.jrxml` ("Exportar dados de pintura por peça/referência")
Tempos de ciclo deixam de ser somados — sair **por programa e por cabine** (3 colunas: Ciclo Primário, Ciclo Base, Ciclo Verniz, conforme campos assinalados no PDF). Alterar a query do template para expor `CICLO_PRIMARIO/CICLO_BASE/CICLO_VERNIZ` separados.

### 4.3 `print_receitas.jrxml` (IMPRIMIR da ficha)
- Acrescentar a informação em falta no fim (bloco Consumos Rack/Carga + Quantidades Necessárias — última secção da ficha não sai).
- Refletir 3.2 (Filtro Robot) e 3.3 (reposicionamento catalisador).

### 4.4 Export novo: "Consumo por JOB por cabine"
Na lista de receitas (`receitas.component.ts`, menu `items` + `exportar()`), nova opção que abrange **todas as receitas filtradas** (mesmo padrão `IDS`):
- Compilar por JOB: consumo por cabine (dados de `PIN_MOV_RECEITAS_LINHAS.CONSUMO_*` agregados por `JOB` + cabine de aplicação).
- Implementar como novo template Jasper `consumo_job_cabine.jrxml` (padrão dos restantes) ou client-side; recomendado Jasper para consistência com os outros exports da lista.

---

## Ordem sugerida e estimativa

| Fase | Conteúdo | Esforço |
|---|---|---|
| 1 | Bugs (manutenção popup, válvulas, diluente, versões) | 3-5 dias |
| 2 | Limites + 2 ecrãs de análise + export c/ gráficos | 8-12 dias |
| 3 | Melhorias receitas (potes, filtro robot, pressões, cor, layout) | 6-9 dias |
| 4 | Jasper reports + export consumo JOB | 4-6 dias (dependente do acesso aos .jrxml) |

Notas:
- Scripts SQL de alteração de BD a agrupar num ficheiro (ex. `PIN_MELHORIAS_2026-07.sql`), como feito em `QUA_MC_MELHORIAS_2026.sql`.
- 1.4 (versões) deve ficar resolvido antes de 3.4/2.2 para que os novos dados (pressões, humidade) não se percam ao versionar.
- Confirmar com o cliente: lista exata de colunas a remover no 4.1 e formato do export com gráficos (2.5 — exceljs vs. PNG separado).
