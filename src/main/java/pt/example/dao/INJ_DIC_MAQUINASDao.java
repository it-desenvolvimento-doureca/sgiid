package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.INJ_DIC_MAQUINAS;

/**
 * Maquinas de injecao.
 *
 * A PASSWORD NAO SAI DAQUI. Estas credenciais dao acesso as maquinas de
 * injecao, e um ecra de configuracao nao precisa de as mostrar para as deixar
 * mudar: quem abre o ecra ve o campo vazio, escreve uma nova se quiser trocar,
 * e deixa em branco para manter a que la esta.
 *
 * O PR_DIC_MAQUINAS_MATRIX devolve a password ao browser. Nao e para copiar:
 * basta abrir as ferramentas de programador para a ler, e quem tiver acesso ao
 * ecra passa a ter acesso as maquinas todas.
 */
public class INJ_DIC_MAQUINASDao extends GenericDaoJpaImpl<INJ_DIC_MAQUINAS, Integer>
		implements GenericDao<INJ_DIC_MAQUINAS, Integer> {

	/** Marca que o ecra devolve quando a password nao foi alterada. */
	private static final String PASSWORD_INALTERADA = "";

	public INJ_DIC_MAQUINASDao() {
		super(INJ_DIC_MAQUINAS.class);
	}

	public List<INJ_DIC_MAQUINAS> getall() {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MAQUINAS a order by a.NOME_MAQUINA ");
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MAQUINAS> data = query.getResultList();
		return semPassword(data);
	}

	public List<INJ_DIC_MAQUINAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from INJ_DIC_MAQUINAS a where a.ID = :id ");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<INJ_DIC_MAQUINAS> data = query.getResultList();
		return semPassword(data);
	}

	/**
	 * Grava, mantendo a password que la esta quando o ecra nao manda uma nova.
	 *
	 * Sem isto, gravar uma linha depois de a ler apagava a password: o ecra
	 * recebe-a vazia (ver semPassword) e devolvia-a vazia, e a maquina deixava
	 * de responder sem ninguem perceber porque.
	 */
	public INJ_DIC_MAQUINAS guardar(INJ_DIC_MAQUINAS dados) {
		String nova = dados.getPASSWORD();

		if (nova == null || PASSWORD_INALTERADA.equals(nova.trim())) {
			INJ_DIC_MAQUINAS atual = entityManager.find(INJ_DIC_MAQUINAS.class, dados.getID());
			dados.setPASSWORD(atual != null ? atual.getPASSWORD() : null);
		}

		INJ_DIC_MAQUINAS gravada = update(dados);
		return semPassword(gravada);
	}

	/**
	 * Apaga a password da copia que vai para o browser.
	 *
	 * As entidades vindas de uma query estao GERIDAS pelo JPA: mudar-lhes um
	 * campo aqui sem as separar gravava o campo vazio na base de dados no fim
	 * da transacao. Daí o detach antes de tocar em cada uma.
	 */
	private List<INJ_DIC_MAQUINAS> semPassword(List<INJ_DIC_MAQUINAS> data) {
		for (INJ_DIC_MAQUINAS m : data) {
			semPassword(m);
		}
		return data;
	}

	private INJ_DIC_MAQUINAS semPassword(INJ_DIC_MAQUINAS m) {
		if (m == null) {
			return null;
		}
		entityManager.detach(m);
		m.setPASSWORD(null);
		return m;
	}
}
