package pt.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_PREPARACAO_LINHA;

public class PIN_MOV_PREPARACAO_LINHADao extends GenericDaoJpaImpl<PIN_MOV_PREPARACAO_LINHA, Integer>
		implements GenericDao<PIN_MOV_PREPARACAO_LINHA, Integer> {
	public PIN_MOV_PREPARACAO_LINHADao() {
		super(PIN_MOV_PREPARACAO_LINHA.class);
	}

	public List<PIN_MOV_PREPARACAO_LINHA> getbyidPREPARACAOcab(Integer id) {
		// -- (select distinct d.PREPARACAONAOPROGRAMADA from AB_DIC_BANHO_ADITIVO
		// d,PIN_MOV_PREPARACAO_CAB x where x.ID_PREPARACAO_CAB = a.ID_PREPARACAO_CAB
		// and x.ID_BANHO = d.ID_BANHO and a.ID_PRODUTO = d.ID_PRODUTO) as NAOP,
		Query query = entityManager.createQuery("Select a,b,"
				+ "(select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where a.ID_UNIDADE = c.ID_MEDIDA)  as Medida,  "
				+ " '' as NAOP,  "
				+ "(Select SUM(p.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ p where p.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN ) as total_etiquetas, "
				+ "(select COUNT(h.ID_MOV_PREP_ETIQUETA) from PIN_MOV_PREPARACAO_ETIQ h where h.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN and h.CONSUMIR = 0) as vazios, "
				+ "(select COUNT(h.ID_MOV_PREP_ETIQUETA) from PIN_MOV_PREPARACAO_ETIQ h where h.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN) as countt "
				+ "from PIN_MOV_PREPARACAO_LINHA a, PIN_DIC_PRODUTOS b where a.ID_PREPARACAO_CAB = :id and a.ID_PRODUTO = b.ID order by a.ID_PRODUTO");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_LINHA> getbyidPREPARACAOcabtotal2(Integer id) {
		Query query = entityManager.createNativeQuery("Select  b.cisterna,  "
				+ "(select COUNT(h.ID_MOV_PREP_ETIQUETA) from PIN_MOV_PREPARACAO_ETIQ h where h.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN) as countt, a.ID_PREPARACAO_LIN, "
				+ "(Select SUM(p.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ p where p.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN ) as total_etiquetas, "
				+ "a.VALOR "
				+ ",(SELECT count(*) FROM SILVER.dbo.stodet x ,PIN_MOV_PREPARACAO_ETIQ cb WHERE x.PROREF= cb.PROREF AND cb.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN AND x.LIECOD='DPCHI' AND x.empcod='ZONQUA' AND x.EMPCOD != cb.EMPCOD AND lotqte>0  ) as  TOTAL_ZONQUA "
				+ ",b.nome_COMPONENTE,b.COD_REF,b.NOME_REF "
				+ "from PIN_MOV_PREPARACAO_LINHA a, AB_DIC_COMPONENTE b where a.ID_PREPARACAO_CAB = :id and a.ID_PRODUTO = b.ID_COMPONENTE order by a.ID_PRODUTO");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_LINHA> getbyidPREPARACAOcabtotal(Integer id) {
		Query query = entityManager.createQuery("Select b, "
				+ "(select COUNT(h.ID_MOV_PREP_ETIQUETA) from PIN_MOV_PREPARACAO_ETIQ h where h.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN) as countt, a.ID_PREPARACAO_LIN, "
				+ "(Select SUM(p.CONSUMIR) from PIN_MOV_PREPARACAO_ETIQ p where p.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN ) as total_etiquetas, "
				+ "a.VALOR "
				+ ",(SELECT count(*) FROM SILVER.dbo.stodet x ,PIN_MOV_PREPARACAO_ETIQ cb WHERE x.PROREF= cb.PROREF AND cb.ID_PREPARACAO_LIN = a.ID_PREPARACAO_LIN AND x.LIECOD='DPCHI' AND x.empcod='ZONQUA' AND x.EMPCOD != cb.EMPCOD AND lotqte>0  ) as  TOTAL_ZONQUA "
				+ "from PIN_MOV_PREPARACAO_LINHA a, AB_DIC_COMPONENTE b where a.ID_PREPARACAO_CAB = :id and a.ID_PRODUTO = b.ID_COMPONENTE order by a.ID_PRODUTO");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_LINHA> getbyid(Integer id) {
		Query query = entityManager
				.createQuery("Select a from PIN_MOV_PREPARACAO_LINHA a where a.ID_PREPARACAO_LIN = :id ");
		query.setParameter("id", id);
		List<PIN_MOV_PREPARACAO_LINHA> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_PREPARACAO_LINHA> getbyid_manut_comp(Integer id, ArrayList<Integer> data2) {

		Query query = entityManager.createQuery("Select a "
				+ " from PIN_MOV_PREPARACAO_LINHA a where a.ID_PREPARACAO_CAB = :id and a.ID_PRODUTO in (:data2)");
		query.setParameter("id", id);
		query.setParameter("data2", data2);
		List<PIN_MOV_PREPARACAO_LINHA> data = query.getResultList();
		return data;

	}

	public int apagar_linhas(Integer id) {

		Query query = entityManager.createNativeQuery(
				"DELETE a FROM PIN_MOV_PREPARACAO_LINHA a inner join PIN_MOV_PREPARACAO_CAB b on a.ID_PREPARACAO_CAB = b.ID_PREPARACAO_CAB "
						+ "where (a.VALOR = '0' or a.VALOR = '' or a.VALOR is null ) and b.ID_PREPARACAO   = " + id
						+ " ");
		int utz = query.executeUpdate();
		return utz;

	}
}
