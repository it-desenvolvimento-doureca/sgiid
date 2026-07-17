# Plano de Testes — Módulo Meios de Controlo

**Data:** 2026-07-10
**Âmbito:** Alterações pedidas no PDF da cliente (Equipamentos.pdf) — frontend sgiid, backend sgiid, etiquetas Jasper, alertas EVENTOS_DOURECA.

## Pré-requisitos de deploy (fazer antes de testar)

| # | Ação | Onde |
|---|------|------|
| P1 | Executar `sql/QUA_MC_MELHORIAS_2026.sql` (se ainda não correu — cria `QUA_MC_GABARITOS_FICHEIROS`) | BD SGIID |
| P2 | Executar `sql/QUA_MC_VERIF_GABARITO_FICHEIROS_2026-07-10.sql` (tabela nova de anexos de verificação) | BD SGIID |
| P3 | Executar `QUA_MC_ALERTAS_CALIBRACAO_2026-07-09.sql` **depois de ajustar EMAIL_PARA** | BD SGIID |
| P4 | Build + deploy do WAR backend (Eclipse) | Servidor JBoss |
| P5 | Build + deploy do frontend Angular | Servidor |
| P6 | Copiar `mc_etiqueta_calib_equipamento.jrxml`, `mc_etiqueta_verif_gabarito.jrxml`, `mc_plano_anual_equipamentos.jrxml` (e restantes `mc_*.jrxml` atualizados) para `<servidor>\relatorios\jasperfiles\` e **apagar os `.jasper` correspondentes** (recompilam no 1.º uso) | Servidor de relatórios |
| P7 | Recompilar EVENTOS_DOURECA (Visual Studio) e substituir o executável agendado no Task Scheduler | Servidor de eventos |
| P8 | Limpar o estado guardado das tabelas no browser (localStorage `mc_equipamentos`/`mc_gabaritos`) se a ordenação aparecer estranha | Browser de teste |

## 1. Listagem de Equipamentos

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 1.1 | Abrir Qualidade → Meios de Controlo → Equipamentos | Colunas: Designação, Cód. Interno, Secção, Estado Metrológico, MSA, **Próxima Calibração**, Em Utilização, Obsoleto |
| 1.2 | Equipamento com última calibração + intervalo definido | Próxima Calibração = última data + menor intervalo aplicável (meses); semáforo **verde** se > 30 dias, **amarelo** se ≤ 30 dias, **vermelho** se no passado |
| 1.3 | Equipamento sem calibrações ou com "Calibração: Não" | Sem data e sem semáforo (ou estado manual do dicionário, se existir) |
| 1.4 | Coluna MSA | Círculo verde/amarelo/vermelho conforme dicionário; **sem registo → círculo vazio com contorno** (tooltip "Sem estudo") |
| 1.5 | Em Utilização / Obsoleto | ✓ verde quando em utilização; ✗ vermelho quando obsoleto; sem "P"/"F" (testar no browser da cliente — era a fonte Material Icons) |
| 1.6 | Tooltip dos círculos | Mostra o texto do estado ("Calibração válida", "Calibração expira brevemente", "Calibração expirada") |
| 1.7 | Checkbox "Só obsoletos" | Lista passa a mostrar apenas obsoletos; desmarcar repõe tudo |
| 1.8 | Botão "Plano Anual" → escolher ano → Gerar PDF | PDF `mc_plano_anual_equipamentos` com equipamentos ativos/em utilização, agrupados por secção, marcas **I** (interna) e **E** (externa) nos meses previstos do ano escolhido |
| 1.9 | Ordenar/filtrar pelas colunas | Ordenação por Próxima Calibração funciona; filtros de texto mantêm-se |

## 2. Ficha de Equipamento — Dados Gerais

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 2.1 | Abrir ficha | Label **"Requer MSA:"** (era "Estudos R&R") |
| 2.2 | Marcar/desmarcar "Requer MSA" em modo edição | Campo **"Freq. Estudo MSA (meses)"** só aparece quando marcado |
| 2.3 | Separadores | Segundo separador chama-se **"Controlo Metrológico"** |
| 2.4 | Nota | Ao desmarcar "Requer MSA", o valor da frequência mantém-se na BD (só fica oculto) — confirmar que é o comportamento pretendido |

## 3. Ficha de Equipamento — Controlo Metrológico

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 3.1 | Grelha de calibrações | 2 colunas novas: **Freq. (semanas)** (= Período Intercalib. Anterior) e **Próx. Calibração** |
| 3.2 | Próx. Calibração por linha | Data da linha + intervalo do tipo (externa→Int. Ext., interna→Int. Int., estudo R&R→Freq. Estudo); tipos não reconhecidos usam o menor intervalo |
| 3.3 | Gráfico "Evolução do Erro Máximo" | Aparece abaixo da grelha quando há linhas com Erro Máximo preenchido; pontos ordenados por data; sem dados → não aparece |
| 3.4 | Modal Registo de Calibração — labels | **N.º de Certificado**, **Validação**, **N.º de Validação**, **Ficheiro do Certificado**, **Ficheiro da Validação** |
| 3.5 | Clicar no nome de um anexo PDF | Abre **preview em iframe sem descarregar**; imagem abre inline; outros tipos descarregam |
| 3.6 | Ícone de download do anexo | Descarrega o ficheiro |
| 3.7 | Upload de anexos (novo registo e edição) | Continua a funcionar (regressão) e o anexo fica associado à linha certa |

## 4. Gabaritos

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 4.1 | Listagem | Igual a 1.1–1.7 mas com **Próxima Verificação** (última verificação + Freq. Verificação meses) |
| 4.2 | Ficha — Dados Gerais | Label **"Projeto:"** (era "Cód. Desenho Técnico"); separador **"Estado Metrológico"** (era "Verificações") |
| 4.3 | Upload de documentos (Dados Gerais, em modo edição) | Funciona após P1/P4 — era o bug reportado; validar com PDF > 1 MB |
| 4.4 | Grelha de verificações | Coluna nova **Próx. Verificação** |
| 4.5 | Modal Registo de Verificação | Campo novo **"Ficheiro do Relatório"** com upload, preview, download e remoção (requer P2/P4) |
| 4.6 | Gravar verificação nova com anexo | Anexo fica associado; reabrir a verificação mostra o anexo |
| 4.7 | Plano Anual de gabaritos | Continua a funcionar (regressão) |

## 5. Dashboard (novo)

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 5.1 | Navegar para `/mc_dashboard` | Cartões de totais (Total, Em Utilização, Válida, Expira Brevemente, Expirada, Obsoletos) para equipamentos e gabaritos + 2 gráficos doughnut |
| 5.2 | Tabela "Expiradas ou a Expirar" | Junta equipamentos e gabaritos ordenados pela próxima data; duplo-clique/lupa abre a ficha respetiva |
| 5.3 | Coerência | Os números batem certo com as listagens (mesma regra de 30 dias); obsoletos não contam para expirados |
| 5.4 | Menu e permissões | Entrada "Dashboard" no menu Meios de Controlo (`node5638`); só fica clicável depois de atribuída a permissão ao perfil em Configurações → Permissões |

## 6. Etiquetas (Jasper — após P6)

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 6.1 | Ficha equipamento → Identificação | Etiqueta DOCX com **logotipo**; labels em branco sobre azul (nada a vermelho) |
| 6.2 | Ficha equipamento → Calibração | CALIB. EXT./CALIB. INT. com **data da próxima calibração do tipo respetivo** quando existe, senão "Sim"/"Não aplicável"; ESTUDO R_R com data do próximo estudo quando existe |
| 6.3 | Equipamento só com calibração externa | CALIB. INT. = "Não aplicável" |
| 6.4 | Ficha gabarito → Verificação | **N.º Verificação com a data** ("6457 de dd/mm/aaaa"); sem Tipo Verif./Tipo Aceit./%R&R; linha nova **PRÓX. VERIF.** com a data; etiqueta mais curta |
| 6.5 | Uma etiqueta apenas | A etiqueta reflete a calibração/verificação **mais recente** (TOP 1), não uma página por registo |

## 7. Alertas por email (após P3/P7)

| # | Teste | Resultado esperado |
|---|-------|--------------------|
| 7.1 | Preparar um equipamento com próxima calibração < hoje+30 e correr o EVENTOS_DOURECA manualmente | Email "SGIID - N equipamento(s)..." com tabela (código, designação, secção, últimas/próximas datas, estado colorido) para os destinatários do `GER_EVENTOS_CONF` |
| 7.2 | Idem para gabaritos | Email equivalente de verificações |
| 7.3 | Sem itens a expirar | Nenhum email é enviado |
| 7.4 | Equipamento obsoleto ou "Calibração: Não" com data antiga | **Não** aparece no email |
| 7.5 | `RelatorioErros.txt` na pasta do executável | Sem erros novos das funções `getAlertasCalibracaoEquipamentos`/`getAlertasVerificacaoGabaritos` |

## 8. Regressão rápida

| # | Teste |
|---|-------|
| 8.1 | Criar/editar/apagar equipamento e gabarito |
| 8.2 | Criar/editar/apagar registo de calibração e de verificação |
| 8.3 | Upload/preview/download/apagar documentos nos Dados Gerais de equipamentos |
| 8.4 | Impressão da ficha de equipamento e de gabarito (PDF) |
| 8.5 | Outras páginas do módulo (Salas, Máquinas, Entidades de Calibração, Parametrização) abrem sem erros de consola |

## Decisões ainda em aberto com a cliente

1. Limiar "expira brevemente" = **30 dias** (hardcoded em: `equipamentos.component.ts`, `gabaritos.component.ts`, `dashboard-mc.component.ts`, queries do `EVENTOS.cs`) — confirmar valor.
2. Próxima calibração quando há interna **e** externa = **menor** dos dois intervalos — confirmar regra.
3. Destinatários e frequência dos alertas (o email repete-se em cada execução do scheduler enquanto o item não for calibrado).
4. Ao desmarcar "Requer MSA", limpar ou manter a frequência gravada (hoje mantém).
