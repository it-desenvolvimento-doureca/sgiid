package pt.example.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO_COMPONENTE;

public class AB_DIC_BANHO_COMPONENTEDao extends GenericDaoJpaImpl<AB_DIC_BANHO_COMPONENTE,Integer> implements GenericDao<AB_DIC_BANHO_COMPONENTE,Integer> {
	public AB_DIC_BANHO_COMPONENTEDao() {
		super(AB_DIC_BANHO_COMPONENTE.class);
	}
	
	public List<AB_DIC_BANHO_COMPONENTE> getbyid_banho(Integer id,String datas) {
		
		String date2 = datas.replace("\"", "").substring(0, 10);
		
		
		Query query = entityManager.createQuery("Select a,b,"
				+ "CASE WHEN a.ID_FORNECEDOR IS NULL THEN '' ELSE (select d.NOME_FORNECEDOR from GER_FORNECEDOR d where d.ID_FORNECEDOR = a.ID_FORNECEDOR)END as nome_forne,"
				+ "CASE WHEN a.ID_UNIDADE_COMPONENTE IS NULL THEN '' ELSE (select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where c.ID_MEDIDA = a.ID_UNIDADE_COMPONENTE)END as medida"
				+ " from AB_DIC_BANHO_COMPONENTE a,AB_DIC_COMPONENTE b "
				+ "where  a.ID_COMPONENTE = b.ID_COMPONENTE  and a.ID_BANHO = :id and a.INATIVO != 1 and (a.DATA_FIM >= '"+date2+"' or a.DATA_FIM is null)  and a.DATA_INICIO <= '"+date2+"' "
				+ "order by b.DATA_CRIA,b.ID_COMPONENTE");
		query.setParameter("id", id);
		List<AB_DIC_BANHO_COMPONENTE> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO_COMPONENTE> getbyid_banhoall(Integer id) {

		Query query = entityManager.createQuery("Select a,b,"
				+ "CASE WHEN a.ID_FORNECEDOR IS NULL THEN '' ELSE (select d.NOME_FORNECEDOR from GER_FORNECEDOR d where d.ID_FORNECEDOR = a.ID_FORNECEDOR)END as nome_forne,"
				+ "CASE WHEN a.ID_UNIDADE_COMPONENTE IS NULL THEN '' ELSE (select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where c.ID_MEDIDA = a.ID_UNIDADE_COMPONENTE)END as medida"
				+ " from AB_DIC_BANHO_COMPONENTE a,AB_DIC_COMPONENTE b "
				+ "where  a.ID_COMPONENTE = b.ID_COMPONENTE  and a.ID_BANHO = :id and a.INATIVO != 1 "
				+ "order by b.DATA_CRIA,b.ID_COMPONENTE");
		query.setParameter("id", id);
		List<AB_DIC_BANHO_COMPONENTE> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO_COMPONENTE> getbyid_banho_comp(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_BANHO_COMPONENTE a  where  a.ID_BANHO_COMP = :id ");
		query.setParameter("id", id);
		List<AB_DIC_BANHO_COMPONENTE> data = query.getResultList();
		return data;

	}
	


}
