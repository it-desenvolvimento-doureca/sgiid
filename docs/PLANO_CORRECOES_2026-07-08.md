# Plano — Revisão "Correções e Melhorias - 08-07-2026"

Fonte: `Correções e Melhorias - 08-07-2026 1.pdf` (revisão do PDF de 01-07) + anexo `ID371.00 - Pressões por programa e pre-set 07-07-2026.xlsx`.
Legenda do PDF: **verde = validado pelo cliente** (sem ação); **amarelo = em falta ou pedido novo** (este plano).

Plano anterior: `PLANO_MELHORIAS_2026-07-01.md` (Fases 1-4 implementadas; SQLs ainda pendentes de execução na BD).

## Estado (2026-07-14) — ✅ implementado
- **A** — filtros trocados nos 2 ecrãs (frontend + DAOs/endpoints backend). ⚠️ rebuild/deploy do backend no Eclipse.
- **B** — popup Pressões: Programa = Job (readonly), 1 linha fixa, sem Adicionar Linha/remover.
- **C.1** — `pressoes_programa.jrxml`: 12 variáveis min/max + banda summary com as 3 linhas de cálculo (Mín/Máx, ±0,3, LIMITES) + legenda TENDÊNCIA/LIMITES. Backup `.bak_2026-07-14`.
- **C.2** — `print_receitas.jrxml`: `DATA_REGISTO` no cabeçalho (x=430 y=33, por baixo do Master) + tabela UV Radiation em banda summary (4 linhas via ROW_NUMBER na query — sem subreport novo). Backup `.bak_2026-07-14`.
- **C.3** — `lista_resumo_receitas.jrxml`: texto da coluna Unidade de Preparação passou de vermelho a preto (header + células). Backup `.bak_2026-07-14`.
- **C.4** — label do export → "Tempos de Ciclo" (`receitas.component.ts`).
- **C.5** — NOVO `lista_jobs.jrxml` (Job, Nome da Receita, Estado; todas as receitas ativas+inativas, ignora filtro da lista) + opção "Lista de Jobs" no menu Exportar.
- **D** — frontend e SPs pendentes de 07-03 já cobrem os 3 campos; NOVO script `PIN_RECEITAS_REPARACAO_PRESSOES_LINHAS_2026-07-14.sql` (verificação das SPs em produção + diagnóstico + reparação de versões danificadas) — **executar na BD**.
- **E** — duplicar Preparações Programadas: deixou de anular `ID_RECEITA/VERSAO` na cópia; dropdowns Receita/Versão editáveis enquanto a linha não está preparada/executada (antes bloqueavam com `item.id != null`).
- **F** — corrigido pelo Tiago (fora deste trabalho).

## Validado (verde — sem ação)
Limites viscosidade/humidade; ecrãs de análise + export; vistos das ações nas manutenções; potes automáticos; Filtro Robot; catalisador movido; popup pressões (base); cor de impressão; colunas vermelho/% no resumo; coluna Código; 3 colunas de ciclo; consumos no print; export consumo por JOB; bugs válvulas/diluente/UV-ciclos nas versões.

---

## A — Análises: trocar filtros (frontend + backend)

Os filtros pedidos ficaram trocados entre os dois ecrãs. Estado atual confirmado no código:

### A.1 Salas de Mistura — filtro passa a **Referência** (remove Receita e Cabine)
- Atual: Receita + Cabine + datas (`analise_salas_mistura.component.ts` — `preenche_receitas`/`preenche_cabines`, `getAnalise(id_receita, id_cabine, ini, fim)`).
- Novo: dropdown única **Referência** (referência cor — `PIN_DIC_PRODUTOS`, padrão do `preenche_produtos()` do analise_bastidor) + Data Início/Fim.
- Backend: `getPIN_DIC_REGISTO_SALAS_MISTURA_ANALISE` passa a `/{idReferencia}/{ini}/{fim}`; query do `PIN_DIC_REGISTO_SALAS_MISTURADao.getanalise` filtra pela referência cor em vez de receita/cabine.

### A.2 Bastidor — filtro passa a **Receita + Cabine** (remove Referência/produto)
- Atual: Referência/produto + datas (`analise_bastidor.component.ts` — `preenche_produtos`, `getAnalise(id_produto, ini, fim)`).
- Novo: dropdowns **Receita** + **Cabine** (padrão `preenche_receitas`/`preenche_cabines` do analise_salas_mistura) + datas.
- Backend: endpoint `getPIN_DIC_REGISTO_BASTIDOR_ANALISE` passa a `/{idReceita}/{idCabine}/{ini}/{fim}`; no DAO (UNION ALL das 3 cabines) filtrar por receita e, com cabine selecionada, restringir ao ramo da cabine.
- Nota: o agrupamento/gráficos por cabine mantém-se; com filtro de cabine ativa sai só 1 grupo.

## B — Receitas: popup "Pressões Mín/Máx por Programa"

### B.1 Programa = Job automático, linha única, sem "Adicionar Linha"
`receitasForm.component.ts/html` (dialog das pressões):
- Pré-preencher `PROGRAMA` com o **Job** da receita (readonly); se o Job mudar na ficha, refletir no popup.
- Grelha fixa de 1 linha: remover botão "Adicionar Linha" e o botão apagar linha.
- Migração leve: se existirem registos antigos com várias linhas, mostrar a primeira (ou a do Job) — validar com Tiago se é preciso limpeza de dados.

## C — Exports / Impressão (Jasper em `C:\sgiid\relatorios\jasperfiles`)

### C.1 Export "Pressões por programa" — resumo final com cálculos (ID371.00 novo)
O excel exemplo 07-07-2026 tem **1 folha por cabine** (Primário/Base/Verniz) e, no fim de cada folha, 3 linhas de resumo por bloco de colunas (P Tinta Entrada/Saída, P Catalisador Entrada/Saída):
1. `Mín (Entrada)/Máx (saída)`: MIN das colunas de entrada; MAX das colunas de saída (sobre todos os programas preenchidos);
2. `Mín - 0,3/Máx + 0,3`: entrada −0,3; saída +0,3;
3. `LIMITES`: repete a linha 2 (valores finais).
Mais legenda: "TENDÊNCIA: QUANTO MAIOR O CAUDAL MAIOR PRESSÃO SAIDA!" e "LIMITES: ENTRADA = valor mínimo / SAIDA = valor máximo".
- Aplicar em `pressoes_programa.jrxml`: manter a tabela larga (3 cabines lado a lado) e acrescentar as 3 linhas de resumo por coluna min/max + legenda no summary band (variáveis Jasper MIN/MAX por coluna), **ou** migrar para export client-side (exceljs, já no projeto) com 3 folhas iguais ao exemplo. Decidir com Tiago — recomendado exceljs para replicar 1:1 o ficheiro do cliente.

### C.2 `print_receitas.jrxml` — Data da Versão/Registo + UV Radiation
- Cabeçalho: adicionar **Data da Versão/Registo** (`DATA_REGISTO` da versão impressa).
- A tabela **UV Radiation** não sai na impressão: adicionar subreport/bloco com `PIN_MOV_UV_RADIATION` (A/B/C/V + recommended dose). Atenção às coordenadas por banda (cabeçalho 477-1069).

### C.3 `lista_resumo_receitas.jrxml` — texto preto na coluna amarela
Coluna "UNIDADE DE PREPARAÇÃO" (header/fundo amarelo): forecolor a preto como as restantes colunas.

### C.4 Renomear opção de export
`receitas.component.ts:27`: label "Exportar dados de pintura por peça/referência" → **"Tempos de Ciclo"** (mantém o template `lista_dados_pecas_receitas`).

### C.5 Novo export "Lista de Jobs"
Nova opção no menu Exportar da lista de receitas: colunas **Job, Nome da Receita, Estado (Ativo/Inativo)**; abrange **todas** as receitas (ativas e inativas), ignorando o filtro Inativo da lista (confirmar se respeita os outros filtros).
- Implementação: novo `lista_jobs.jrxml` (padrão dos existentes) ou xlsx client-side simples; recomendado Jasper para consistência do menu.

## D — Bug: P. Entrada, P. Retorno e TºC perdem-se ao atualizar versão

Campos das **linhas** da receita (`PIN_MOV_RECEITAS_LINHAS`: P_ENTRADA/P_RETORNO/TC ou equivalentes). O fix de 07-03 cobriu o cabeçalho (ciclos, UV) na SP `PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO`; verificar se:
1. o INSERT das linhas dessa SP copia estes 3 campos;
2. a SP `PIN_UPDATE_PIN_DIC_PRE_SET` também os copia (foi dada como correta para o caso anterior, mas pode falhar nestes campos);
3. a criação de nova versão pelo frontend (`receitasForm` gravar/duplicar versão) os transporta.
Corrigir onde faltar + script de reparação de dados se houver versões já afetadas (padrão `PIN_RECEITAS_REPARACAO_DADOS_2026-07-03.sql`).
⚠️ Regra existente: qualquer campo novo em RECEITAS/LINHAS tem de entrar nos INSERTs das DUAS SPs de versão.

## E — Preparações Programadas: duplicar não funciona

"Quando queremos duplicar, não conseguimos após duplicação editar a receita e não duplicou (devia)".
- Local: `programadas.component.ts` / `programadasform.component.ts` (fluxo DUPLICAR).
- Sintomas: a receita não é copiada para a nova preparação e o dropdown da receita fica bloqueado ("Selecione um item na lista" no screenshot).
- Investigar: o duplicar copia o cabeçalho mas não as linhas/receita? O estado "Em Planeamento" da cópia deixa o campo editável? Corrigir para duplicar completo e permitir edição.

## F — Manutenções (app-manu/app_manutencao): periodicidade gera datas erradas

"Todas as tarefas voltaram a aparecer como dia 2026-07-07" (exceto INDICAÇÕES + TAREFAS + CHECK-LIST DIÁRIOS, semanal ter/qua/qui, que está correta).
- Screenshot "Periodicidade → SIMULAR PRÓXIMAS 10 DATAS" mostra as 10 ocorrências todas iguais a 2026-06-23 → o cálculo da próxima data não avança (nem no simulador nem na geração real).
- Investigar no backend `C:\work\Workspace\app_manutencao` (job/SP de geração de pedidos preventivos) e no frontend do plano de manutenção (planos anuais PREV/PRED do equipamento TAREFAS): padrão de repetição semanal com múltiplos dias funciona; os restantes padrões (repetição simples) repetem a data de início.
- Corrigir cálculo da próxima ocorrência + limpar/regenerar pedidos gerados com data errada.

---

## Ordem sugerida e estimativa

| Bloco | Conteúdo | Esforço | Dependências |
|---|---|---|---|
| D | Bug versões P.Entrada/Retorno/TºC (SPs) | 0,5-1 dia | Acesso BD |
| A | Troca de filtros nas 2 análises | 1-2 dias | SQL Fase 2 executado |
| B | Popup pressões: Job automático, 1 linha | 0,5 dia | — |
| C | Exports (resumo ID371.00, print, cores, rename, Lista de Jobs) | 2-3 dias | Acesso jasperfiles; SQL Fases 2/3 executado |
| E | Duplicar preparações programadas | 0,5-1 dia | — |
| F | Periodicidade manutenções (app-manu) | 1-2 dias | Repo/BD app_manutencao |

Notas:
- Os SQLs das fases anteriores (`PIN_MELHORIAS_FASE2_2026-07-03.sql`, `PIN_UPDATE_PIN_DIC_PRODUTOS_PERC_DILUICAO_2026-07-03.sql`, `MAN_GET_ALL_PIPE_2026-07-02.sql`) continuam **pendentes de execução** — os exports/análises dão erro até lá.
- Decidir com o cliente: C.1 Jasper vs exceljs (3 folhas como o exemplo); C.5 Jasper vs xlsx.
