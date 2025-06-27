package pt.example.dao;

import java.util.List;

import javax.persistence.Query;


import pt.example.entity.MAN_MOV_MANUTENCAO_COMPONENTES;
import pt.example.entity.MAN_MOV_MANUTENCAO_EQUIPAMENTOS;

public class MAN_MOV_MANUTENCAO_COMPONENTESDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_COMPONENTES, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_COMPONENTES, Integer> {
	public MAN_MOV_MANUTENCAO_COMPONENTESDao() {
		super(MAN_MOV_MANUTENCAO_COMPONENTES.class);
	}

	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_COMPONENTES a ");

		List<MAN_MOV_MANUTENCAO_COMPONENTES> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a from MAN_MOV_MANUTENCAO_COMPONENTES a "
				+ "where  a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_COMPONENTES> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_COMPONENTES> getbyid2(Integer id) {

		String sql = 
			    "IF OBJECT_ID('tempdb..#StockTotal') IS NOT NULL DROP TABLE #StockTotal; " +
			    "SELECT PROREF, SUM(lotqte) AS STOCK_TOTAL " +
			    "INTO #StockTotal " +
			    "FROM SILVER.dbo.stodet " +
			    "WHERE proref IN (SELECT REFERENCIA FROM MAN_MOV_MANUTENCAO_COMPONENTES WHERE ID_MANUTENCAO = :id) " +
			    "GROUP BY PROREF; " +

			    "WITH Localizacao AS ( " +
			    "    SELECT CONCAT('E', ID) AS ID, DESCRICAO FROM MAN_DIC_EDIFICIOS " +
			    "    UNION ALL " +
			    "    SELECT CONCAT('P', a.ID) AS ID, b.DESCRICAO + '/' + a.DESCRICAO " +
			    "    FROM MAN_DIC_PISOS a " +
			    "    INNER JOIN MAN_DIC_EDIFICIOS b ON a.ID_EDIFICIO = b.ID " +
			    "    UNION ALL " +
			    "    SELECT CONCAT('D', a.ID) AS ID, c.DESCRICAO + '/' + b.DESCRICAO + '/' + a.DESCRICAO " +
			    "    FROM MAN_DIC_DIVISOES a " +
			    "    INNER JOIN MAN_DIC_PISOS b ON a.ID_PISO = b.ID " +
			    "    INNER JOIN MAN_DIC_EDIFICIOS c ON b.ID_EDIFICIO = c.ID " +
			    ") " +

			    "SELECT a.ID,ID_MANUTENCAO,a.REFERENCIA,a.DESC_REFERENCIA,a.QUANTIDADE,a.ANEXO,b.STOCK_TOTAL,e.DESCRICAO,a.TIPO_LOCALIZACAO,a.LOCALIZACAO,a.UTZ_CRIA,a.DATA_CRIA,a.ESTADO,a.UTZ_VALIDA,a.DATA_VALIDADO, c.Localizacao as localizacao_silver " +
			    "FROM MAN_MOV_MANUTENCAO_COMPONENTES a " +
			    "LEFT JOIN #StockTotal b ON b.PROREF = a.REFERENCIA " +
			    "LEFT JOIN Localizacao e ON e.ID = CONCAT(ISNULL(a.TIPO_LOCALIZACAO, 'E'), a.LOCALIZACAO) " +
			    "LEFT JOIN ( " +
			    "    SELECT Referencia, " +
			    "           ISNULL(STRING_AGG(NULLIF(CONCAT(Armazem, '-', Localizacao), ''), ';') " +
			    "           WITHIN GROUP (ORDER BY Localizacao), 's/ localização') AS Localizacao " +
			    "    FROM ( " +
			    "        SELECT DISTINCT proref AS Referencia, liecod AS Armazem, empcod AS Localizacao " +
			    "        FROM SILVER.dbo.stodet WHERE lotqte <> 0 " +
			    "    ) a " +
			    "    GROUP BY Referencia " +
			    ") c ON c.Referencia = a.REFERENCIA " +
			    "WHERE a.ID_MANUTENCAO = :id " +
			    "ORDER BY a.ID; " +

			    "DROP TABLE #StockTotal;";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", id);
		List<MAN_MOV_MANUTENCAO_COMPONENTES> data = query.getResultList();
		return data;

	}
}
