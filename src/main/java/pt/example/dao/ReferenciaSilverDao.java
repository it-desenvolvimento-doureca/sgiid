package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.example.entity.ReferenciaSilver;

/**
 * Pesquisa de referencias de peca no Silver (PUB.SDTPRA).
 *
 * PORQUE PESQUISAR EM VEZ DE ESCREVER: a referencia configurada tem de bater
 * exactamente com a que vem na OF. Uma referencia mal escrita nao da erro
 * nenhum — so faz a contagem automatica nunca encontrar a configuracao, e isso
 * so se descobre quando a producao aparece a zero.
 *
 * O TEXTO NAO ENTRA NO PROGRESS. A consulta do OPENQUERY e fixa e o filtro e
 * aplicado deste lado, com parametro. Sao ~28800 referencias e demora pouco
 * mais de um segundo — e uma pesquisa de ecra de configuracao, nao um ciclo.
 *
 * TOP 50: uma lista maior do que isso nao se le. Quem nao encontra, escreve
 * mais.
 */
public class ReferenciaSilverDao {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	private static final String CONSULTA =
			"SELECT TOP 50 PROREF, PRODES1 "
			+ "FROM OPENQUERY(silver, "
			+ "'SELECT PROREF AS PROREF, PRODES1 AS PRODES1 FROM PUB.SDTPRA') "
			+ "WHERE PROREF LIKE '%' + ?1 + '%' OR PRODES1 LIKE '%' + ?1 + '%' "
			+ "ORDER BY PROREF";

	public List<ReferenciaSilver> procurar(String texto) {
		List<ReferenciaSilver> refs = new ArrayList<ReferenciaSilver>();

		// Sem texto nao se vai buscar nada: seriam 28800 linhas sem serventia.
		if (texto == null || texto.trim().isEmpty()) {
			return refs;
		}

		Query query = entityManager.createNativeQuery(CONSULTA);
		query.setParameter(1, texto.trim());

		@SuppressWarnings("unchecked")
		List<Object[]> linhas = query.getResultList();

		for (Object[] l : linhas) {
			ReferenciaSilver r = new ReferenciaSilver();
			r.setREF_NUM(l[0] == null ? "" : l[0].toString().trim());
			r.setREF_DES(l[1] == null ? "" : l[1].toString().trim());
			refs.add(r);
		}
		return refs;
	}
}
