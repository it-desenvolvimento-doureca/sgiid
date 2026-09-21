package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.example.entity.MoldeSilver;

/**
 * Os moldes, LIDOS DO SILVER. Nao ha tabela de moldes do nosso lado.
 *
 * Uma copia desactualiza-se: um molde novo, uma descricao corrigida ou um molde
 * abatido so chegariam ca quando alguem se lembrasse de importar. E o mesmo
 * raciocinio dos defeitos (SPAQUA) e das OFs.
 *
 * A LEITURA E POR OPENQUERY, nao por uma tabela do SILVER_BI: o SDTGOO nao
 * existe la. O servidor ligado "silver" vai ao Progress, onde a tabela vive.
 *
 * O TEXTO DA PESQUISA NAO ENTRA NO PROGRESS. A consulta do OPENQUERY e uma
 * string fixa e o filtro e aplicado deste lado, com parametro. Um texto
 * concatenado dentro do OPENQUERY passava por tres niveis de aspas e era o
 * sitio obvio para uma injeccao.
 *
 * Custo: traz as ~2800 linhas de cada vez, medido em ~730 ms. E um ecra de
 * configuracao, nao um tablet a espera.
 */
public class MoldeSilverDao {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	private static final String CONSULTA =
			"SELECT PROREF, PRODES1, PRODES2 "
			+ "FROM OPENQUERY(silver, "
			+ "'SELECT DISTINCT g.PROREF AS PROREF, pr.PRODES1 AS PRODES1, "
			+ "pr.PRODES2 AS PRODES2 FROM PUB.SDTGOO g, PUB.SDTPRA pr "
			+ "WHERE g.PROREF = pr.PROREF') "
			+ "WHERE ?1 = '' OR PROREF LIKE '%' + ?1 + '%' OR PRODES1 LIKE '%' + ?1 + '%' "
			+ "ORDER BY PROREF";

	/**
	 * Os moldes que ja tem configuracao, com a descricao do Silver.
	 *
	 * E por aqui que o ecra comeca. A lista de quais sao e nossa; a descricao
	 * nao — por isso o EXISTS contra a nossa tabela e feito do lado do SQL
	 * Server, sobre o que o OPENQUERY trouxe.
	 */
	private static final String CONFIGURADOS =
			"SELECT s.PROREF, s.PRODES1, s.PRODES2 "
			+ "FROM OPENQUERY(silver, "
			+ "'SELECT DISTINCT g.PROREF AS PROREF, pr.PRODES1 AS PRODES1, "
			+ "pr.PRODES2 AS PRODES2 FROM PUB.SDTGOO g, PUB.SDTPRA pr "
			+ "WHERE g.PROREF = pr.PROREF') s "
			+ "WHERE EXISTS (SELECT 1 FROM INJ_DIC_MOLDE_REF r WHERE r.REF_MOLDE = s.PROREF) "
			+ "ORDER BY s.PROREF";

	public List<MoldeSilver> procurar(String texto) {
		Query query = entityManager.createNativeQuery(CONSULTA);
		query.setParameter(1, texto == null ? "" : texto.trim());
		return converter(query);
	}

	public List<MoldeSilver> configurados() {
		return converter(entityManager.createNativeQuery(CONFIGURADOS));
	}

	private List<MoldeSilver> converter(Query query) {
		@SuppressWarnings("unchecked")
		List<Object[]> linhas = query.getResultList();

		List<MoldeSilver> moldes = new ArrayList<MoldeSilver>();
		for (Object[] l : linhas) {
			MoldeSilver m = new MoldeSilver();
			m.setREF_MOLDE(texto(l[0]));
			// As duas linhas de descricao do Silver mostram-se como uma so.
			String d1 = texto(l[1]);
			String d2 = texto(l[2]);
			m.setDESCRICAO((d1 + " " + d2).trim());
			moldes.add(m);
		}
		return moldes;
	}

	private String texto(Object o) {
		return o == null ? "" : o.toString().trim();
	}
}
