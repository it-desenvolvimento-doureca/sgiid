package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_TIPOS_GRATIFICACOES_LINHA;

public class RH_DIC_TIPOS_GRATIFICACOES_LINHADao extends GenericDaoJpaImpl<RH_DIC_TIPOS_GRATIFICACOES_LINHA, Integer>
		implements GenericDao<RH_DIC_TIPOS_GRATIFICACOES_LINHA, Integer> {

	public RH_DIC_TIPOS_GRATIFICACOES_LINHADao() {
		super(RH_DIC_TIPOS_GRATIFICACOES_LINHA.class);
	}

	 
	public List<RH_DIC_TIPOS_GRATIFICACOES_LINHA> getAll() {
		Query query = entityManager.createQuery("SELECT l FROM RH_DIC_TIPOS_GRATIFICACOES_LINHA l");
		return query.getResultList();
	}
	
	 /* WHERE TP_CLASSIF_ADF = ''''F''''*/
	public List<Object[]> getGRATIFICACAOsAll() {
	    Query query = entityManager.createNativeQuery(
	        "EXEC ( "
	        + "'SELECT A.ID_ABONO_DESC AS COD_GRATIFICACAO, "
	        + "        A.DES_ABONO_DESC AS DESC_GRATIFICACAO "
	        + " FROM OPENQUERY([ESIGEMP], "
	        + "      ''SELECT ID_ABONO_DESC, DES_ABONO_DESC "
	        + "        FROM ERPMASTER.RHS_ABON_DESC_FALT "
	        + "       '' "
	        + " ) AS A "
	        + " WHERE NOT EXISTS ( "
	        + "       SELECT 1 "
	        + "       FROM RH_DIC_TIPOS_GRATIFICACOES_LINHA B "
	        + "       WHERE B.COD_GRATIFICACAO = A.ID_ABONO_DESC "
	        + " )'); " 
	    );
	    return query.getResultList();
	}

	 
	
}