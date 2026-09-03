# PLANO DE MELHORIAS — Segurança no Trabalho / Acidentes e Incidentes

**Data:** 2026-08-10
**Origem:** anotações manuscritas do cliente em `C:\Users\it2\Downloads\doureca\acidentes\`
**Módulo:** SGIID → Segurança no Trabalho → Relatórios de Ocorrências (`AT_*`)

---

## 0. Documentos de origem

| Ficheiro | O que é | Papel no plano |
|---|---|---|
| `Ficha Registo Acidentes - Alterações 1.pdf` | Screenshots do ecrã atual do SGIID (6 separadores), anotados | Alterações ao formulário existente |
| `Report Acidentes - Alterações.pdf` | Relatório PDF impresso (3 págs), anotado | Alterações ao relatório/impressão |
| `Ficha Registo Incidentes - Novo.pdf` | Formulário em papel `ID288.03` "Relatório de Investigação de Incidentes", anotado | **Novo** formulário a informatizar |
| `Diagrama_Ishikawa.pdf` | Diagrama de causa-efeito com 6 espinhas e opções pré-definidas | Novo componente que substitui a checklist de causas |

Anotação-chave na ficha de incidentes: *"Novo form no SGIID"*.

---

## 1. Resumo das alterações

Cinco frentes de trabalho:

1. **Terminologia** — eliminar o conceito de "Emergência" e introduzir "Quase Acidente"
2. **Campos novos** no formulário de acidentes (aptidão, 1ºs socorros, afastamento, anexos)
3. **Nova escala de gravidade** e granularidade das partes do corpo
4. **Diagrama de Ishikawa** a substituir a checklist de causas (acidentes *e* incidentes)
5. **Novo formulário** de Investigação de Incidentes Industriais/Tecnológicos

---

## 2. FRENTE 1 — Terminologia e tipo de ocorrência

### 2.1 Tipos de ocorrência
O tipo "Emergência / 1ºs socorros" está **riscado** e é substituído por "Quase Acidente".

| Antes | Depois |
|---|---|
| Acidente de Trabalho, Se (Sim): Mortal / Não-Mortal | *(mantém)* |
| ~~Emergência / 1ºs socorros~~ | **Quase Acidente (só danos materiais)** |

### 2.2 Renomeações a aplicar em todo o módulo (ecrã + relatório)

| Antes | Depois |
|---|---|
| "Informação sobre o Acidente ou Emergência" | "Informação sobre o Quase Acidente ou Acidente" |
| "Análise das Causas do Acidente ou Emergência" | "Análise das Causas do Quase Acidente ou Acidente" |
| "Relatório de Investigação de Acidentes de Trabalho/~~Emergências~~" | "Relatório de Investigação de Acidentes de Trabalho / Quase Acidentes" |
| "Dados relativos ao trabalhador ~~acidentado~~" | "Dados relativos ao trabalhador **envolvido / sinistrado** " |
| "Assinatura do trabalhador ~~acidentado~~" | "Assinatura do trabalhador **envolvido**" |
| "Hora do ~~acidente ou emergência~~" | "Hora do Quase Acidente ou Acidente" |
| "Local do ~~acidente ou emergência~~" | "Local do Quase Acidente ou Acidente" |
| "Descrição breve do ~~acidente ou da emergência~~" | "Descrição breve do Quase Acidente ou Acidente" |
| "Assinatura do responsável da investigação do acidente de trabalho ~~ou emergência~~" | "... do Quase Acidente ou Acidente de trabalho" |

> **Nota:** o campo "Vínculo Temporário: Sim/Não" está circundado com a indicação **"MANTER"** — não remover.

### 2.3 Relatório impresso
- **Novo logo Doureca** no cabeçalho (anotado "NOVO LOGO" com seta para o logo atual).

---

## 3. FRENTE 2 — Campos novos no formulário de acidentes

Separador **Informação sobre o Quase Acidente ou Acidente**:

| Campo | Tipo | Notas |
|---|---|---|
| **Ficha de Aptidão** | Radio Sim/Não + Data | Anotado "F.Aptidão: Sim ☐ Não ☐ DATA:" |
| **Relatório Assinado** | Upload de ficheiro | Anotado "Relatório Assinado — Anexar" |
| **Hora de início do afastamento** | Hora | Anotado "Hora de Início do Afastamento: [ : ]" |
| **Primeiros Socorros** | Radio Sim/Não | Anotado "Primeiros Socorros: ☐Sim ☐Não" |
| **Desempenho / Avaliação da equipa de 1ºs socorros** | Texto livre | Campo de texto largo |
| **Danos Materiais** | Texto livre | Já existe no ecrã mas está sublinhado/marcado — validar se fica só visível quando tipo = Quase Acidente |
| **Nº de dias (IT)** | Numérico | Anotado "Nº Dias [ ]" junto a Incapacidade Temporária |
| **Duração (dias)** | Numérico | No relatório, "~~Dias perdidos~~" → "**Duração (dias)**" |

### 3.1 Separador Linha de Investigação
- **Remover** "1. Recolha de evidências: Fotos / Vídeo / Esquema do acidente / Outro(s)" — riscado por completo.
- **Remover** "Se (Sim), quantas testemunhas:" — riscado (a contagem passa a ser automática pela tabela de testemunhas).
- **Manter** "2. Testemunha(s): Sim / Não".
- **Adicionar** campo de **Assinatura** nas entrevistas (secções 3 e 4) — anotado "ASSINATURA" com caixa em ambas.

### 3.2 Assinaturas (última página do relatório)
Nova secção a acrescentar:

> **ASSINATURA DOS TRABALHADORES EM POSTOS / TAREFAS DE TRABALHO SIMILARES**
> Tomei Conhecimento: ______________  Data: __/__/____
> *(múltiplas linhas — tabela dinâmica)*

A pergunta "Existem outros trabalhadores em postos/tarefas de trabalho similares?" tem a anotação **"(PASSAR P/ FIM)"** → mover para o final do formulário, junto a esta nova secção de assinaturas.

---

## 4. FRENTE 3 — Escala de gravidade e partes do corpo

### 4.1 Grau da lesão — nova escala

| Antes | Depois |
|---|---|
| Leve / ~~Muito Grave~~ / Grave / Mortal | **Leve / Moderado / Grave / Mortal** |

Critérios anotados (a mostrar como legenda/tooltip no ecrã):

| Grau | Critério |
|---|---|
| Leve | ≤ 1 dia |
| Moderado | 1 a 3 dias |
| Grave | 4 a 30 dias |
| Mortal | — |

> "Muito Grave" é eliminado; "Moderado" é introduzido.

### 4.2 Partes do corpo atingidas — dividir opções

| Opção atual | Ação | Resultado |
|---|---|---|
| Ombro/braço/cotovelo | **DIVIDIR** | Ombro · Braço · Cotovelo |
| Joelho/perna/tornozelo | **DIVIDIR** | Joelho · Perna · Tornozelo |
| Antebraço/pulso | **DIVIDIR** | Antebraço · Pulso |
| Cabeça (~~exceto olhos~~) | Remover qualificador | Cabeça |
| Pé (~~exceto dedos~~) | Remover qualificador | Pé |
| Mão (~~excluindo dedos~~) | Remover qualificador | Mão |
| — | **ADICIONAR** | **Dedos da mão** |

*(Mantêm-se: Olhos, Pescoço, Costas/coluna, Tórax, Abdómen, Dedos do pé, Localizações múltiplas, Anca/coxa/rótula, Outro)*

---

## 5. FRENTE 4 — Diagrama de Ishikawa (substitui checklist de causas)

No separador **Análise das Causas**, toda a checklist atual está **riscada** com a anotação **"DIAGRAMA ISHIKAWA"**. Idem no relatório impresso ("✓ DIAGRAMA").

**Opções a eliminar:** Ato Inseguro, Desorganização do posto de trabalho, Inexistência de equipamento adequado, Utilização inadequada de equipamentos, Ausência de utilização de EPI, Distração/Descuido do sinistrado, Operação/método perigoso, Acondicionamento inseguro de materiais, Defeito/avaria ou ligação incorreta do equipamento, Incumprimento de procedimento/instrução, Responsabilidade de terceiros.

**Substituir por** componente Ishikawa com 6 categorias (cada uma com opção "Outro" + texto livre):

### PESSOAS
Falta de Formação · Utilização Inadequada de EPI · Postura Incorreta · Comportamento de Risco · Falta de Experiência · Falta de atenção · Utilização de Vestuário/Acessórios · Outro

### MÉTODOS
Procedimentos Desatualizados · Método de Trabalho · Falta de Planeamento · Instruções Pouco Claras · Etapas trocadas · Armazenamento Inseguro · Outro

### ORGANIZAÇÃO
Falta de Supervisão · Comunicação Ineficaz · Falta de Liderança · Inexistência de procedimentos sobre a segurança · Formação insuficiente · Prioridade à produção · Outro

### MÁQUINAS
Falta de manutenção · Equipamento com defeito · Máquina sem proteção · Comandos não identificados · Arranque Inesperado · Cabos Expostos · Outro

### AMBIENTE
Iluminação inadequada · Temperatura extrema · Ruído elevado · Presença de substâncias perigosas · Limpeza Inadequada · Pavimento danificado · Outro

### MATERIAIS
Ferramentas Inadequadas · Derrames · Carga Excessiva · Transporte inseguro · Falta de Identificação · Ausência de sinalização · Outro

**Mantém-se** o campo "Descrição pormenorizada das causas".

> **Aplica-se também ao formulário de incidentes** — a metodologia dos "5 Porquês" está riscada e substituída por "ANÁLISE DAS CAUSAS" → Ishikawa. Decisão a confirmar: manter os 5 Porquês *em complemento* ao Ishikawa ou eliminá-los.

---

## 6. FRENTE 5 — NOVO formulário: Investigação de Incidentes Industriais/Tecnológicos

Formulário em papel `ID288.03` a informatizar de novo no SGIID (anotação: *"Novo form no SGIID"*).

**Título:** "Relatório de Investigação de Incidentes **Industriais Tecnológicos**"
**Numeração:** `Ficha n.º [AUTO] / ano` — sequencial automático por ano

### 6.1 Identificação e enquadramento
- Local / Zona afetada
- Início (data/hora) · Fim (data/hora)
- **Tipo de ~~Acidente~~ → INCIDENTE:** Derrame · Incêndio · Explosão · Fuga · Outro
- ❌ **Remover** checkboxes "Acidente" / "Quase Acidente" (riscados)
- ❌ **Remover** bloco "Circunstâncias / Período de Laboração" (Avaria, Paragem, Arranque, Manutenção) — riscado por completo

### 6.2 Pessoas presentes
Tabela dinâmica: Nome · Setor · Cargo/Função

### 6.3 Descrição do acontecimento
Texto livre (área grande)

### 6.4 Substâncias perigosas
- Envolvidas: Sim / Não
- **Se Sim, qual(ais):** ______ *(campo novo)*
- **Quantidade estimada:** ______ *(passa a estar condicionado ao "Sim")*

### 6.5 Análise das Causas
- ❌ Remover "Causa/Origem (metodologia dos 5 porquês)" com 5 linhas "Porquê" — riscado
- ✅ Substituir por **Análise das Causas → Diagrama de Ishikawa** (secção 5)

### 6.6 Contexto da ocorrência
- Existiam trabalhos de reparação/manutenção junto à zona? Sim / Não
  - **Se Sim:** Quais? ______ *(o "Quando?" está riscado)*
- Meio | Sistema Utilizado: Extintores · Mantas ignífugas · Carretéis · Hidrantes · Outro (Qual?)
- Tinham sido tomadas todas as providências? Sim / Não + Quais?
- Consequências (texto livre)

### 6.7 Feridos
- Feridos: Sim / Não
- ❌ Remover "Quantidade" e "Tipo e gravidade de ferimentos" (riscados)
- ✅ **Se Sim** → ligação ao **"Relatório de Investigação de Quase Acidentes / Acidentes de Trabalho n.º ____"**
  → *isto cria uma relação entre o novo módulo de Incidentes e o módulo de Acidentes existente*

### 6.8 Tempos
- Entre o início da ocorrência e a deteção
- Entre a deteção e a 1ª intervenção
- Entre a deteção e a chegada dos meios ao local

### 6.9 Fecho
- Lições aprendidas
- Requer atualização de algum documento (procedimento, instrução, outro)? Sim / Não + Qual(ais)
- Ações Corretivas
- Medidas Preventivas
- Equipa de Investigação
- Data
- Difusão do Relatório

---

## 7. Impacto técnico estimado

### 7.1 Base de dados (backend `C:\work\Workspace\sgiid`)
| Alteração | Detalhe |
|---|---|
| Tipo de ocorrência | Novo valor "Quase Acidente"; retirar "Emergência" do domínio |
| Campos novos na tabela de acidentes | `FICHA_APTIDAO`, `DATA_FICHA_APTIDAO`, `HORA_INICIO_AFASTAMENTO`, `PRIMEIROS_SOCORROS`, `AVALIACAO_EQUIPA_SOCORROS`, `NR_DIAS_IT`, `RELATORIO_ASSINADO` (anexo) |
| Grau da lesão | Migrar "Muito Grave" → "Grave" ou "Moderado" (**decisão do cliente**) |
| Partes do corpo | Novas linhas no dicionário; migrar registos das opções agregadas |
| Ishikawa | Nova tabela de dicionário (categoria + causa) + tabela de ligação N:N à ocorrência |
| Assinaturas de similares | Nova tabela filha (nome, data, tomei conhecimento) |
| **Novo módulo Incidentes** | Tabela cabeçalho + filhas (pessoas presentes, causas Ishikawa, ações, medidas) + FK opcional para o relatório de acidente |

### 7.2 Frontend (`sgiid` Angular)
- Alterar componente/entidade/serviço existentes de Relatórios de Ocorrências
- **Novo componente Ishikawa reutilizável** (usado em Acidentes e em Incidentes)
- Novo conjunto de páginas para Incidentes (lista / novo / editar / ver) + rota + entrada de menu + permissões
- Upload de anexo (relatório assinado)

### 7.3 Relatórios (Jasper, fora do repo — `C:/SGIID/relatorios/jasperfiles/`)
- Novo logo Doureca
- Renomeações de títulos
- Nova secção de assinaturas de trabalhadores similares
- Reposicionar "Existem outros trabalhadores em postos similares?" para o fim
- Substituir checklist de causas pelo Ishikawa
- **Novo** relatório para Investigação de Incidentes

---

## 8. Faseamento proposto

| Fase | Âmbito | Depende de |
|---|---|---|
| **F1** | Terminologia + tipo "Quase Acidente" + renomeações (ecrã e relatório) | — |
| **F2** | Campos novos no formulário de acidentes (aptidão, 1ºs socorros, afastamento, anexo, Nº dias IT) | F1 |
| **F3** | Nova escala de gravidade + divisão das partes do corpo (inclui script de migração) | F1 |
| **F4** | Componente Ishikawa + substituição da checklist de causas | F1 |
| **F5** | Assinaturas de trabalhadores similares + reposicionamento no relatório | F1 |
| **F6** | **Novo módulo de Investigação de Incidentes** (reutiliza o Ishikawa da F4) | F4 |

---

## 9. Pontos a confirmar com o cliente

1. **Migração de "Muito Grave"** — os registos existentes passam a "Grave" ou a "Moderado"?
2. **Critério do grau "Moderado"** — a anotação diz "1 a 3 dias" mas "Leve ≤ 1 dia"; sobrepõem-se no dia 1. Confirmar limites exatos.
3. **5 Porquês nos incidentes** — eliminar de vez, ou manter em complemento ao Ishikawa?
4. **"Danos Materiais"** — é obrigatório quando o tipo é "Quase Acidente (só danos materiais)"? Fica escondido nos acidentes?
5. **Registos antigos com tipo "Emergência"** — o que lhes acontece? Passam a "Quase Acidente"?
6. **Ficha de Aptidão** — é só Sim/Não + data, ou também precisa de anexo?
7. **Novo logo** — obter o ficheiro do logo atualizado da Doureca.
8. **Numeração automática dos incidentes** — sequência independente da dos acidentes? Reinicia a cada ano?
9. **Ishikawa** — o cliente quer visualização gráfica em espinha de peixe, ou basta a seleção agrupada por categoria (mais simples e imprime melhor)?

---

**Preparado por:** Tiago Pereira
**Próximo passo:** validar a secção 9 com o cliente antes de iniciar a F1.
