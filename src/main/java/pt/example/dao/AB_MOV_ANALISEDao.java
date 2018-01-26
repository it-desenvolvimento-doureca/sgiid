package pt.example.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_ANALISE;

public class AB_MOV_ANALISEDao extends GenericDaoJpaImpl<AB_MOV_ANALISE, Integer>
		implements GenericDao<AB_MOV_ANALISE, Integer> {
	public AB_MOV_ANALISEDao() {
		super(AB_MOV_ANALISE.class);
	}

	public List<AB_MOV_ANALISE> getbyid(Integer id, Integer linha) {

		Query query = entityManager
				.createQuery("Select a,b,c, " + "(select e.COD_TINA from AB_DIC_TINA e where c.ID_TINA = e.ID_TINA), "
						+ "(select f.NOME_UTILIZADOR from GER_UTILIZADORES f where f.ID_UTILIZADOR = a.UTZ_CRIA) "
						+ "from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c where  a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and a.ID_ANALISE = :id "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) ");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getall(Integer linha) {
		Query query = entityManager
				.createQuery("Select a,b,c,d " + "from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c,AB_DIC_TINA d "
						+ "where  a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and c.ID_TINA = d.ID_TINA and a.INATIVO not in (1) "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha)) order by a.ID_ANALISE desc");
		query.setParameter("linha", linha);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getallmanu(Integer linha, Integer idbanho, Date datas) {
		Query query = entityManager
				.createQuery("Select a,b,c,d from AB_MOV_ANALISE a,AB_DIC_LINHA b,AB_DIC_BANHO c,AB_DIC_TINA d where  "
						// + "a.ID_BANHO = :idbanho and a.ID_ANALISE not
						// in(select e.ID_ANALISE from AB_MOV_MANUTENCAO_CAB e
						// where e.ID_ANALISE is not null) and a.ESTADO in
						// ('C','V') and "
						+ "a.ID_BANHO = :idbanho and a.ESTADO in ('C','V') and  "
						+ "a.ID_LINHA = b.ID_LINHA and a.ID_BANHO = c.ID_BANHO and c.ID_TINA = d.ID_TINA and a.INATIVO not in (1) "
						+ "and ((not :linha != 0) or (a.ID_LINHA = :linha))  and a.DATA_ANALISE > :datas  order by a.ID_ANALISE desc");
		query.setParameter("linha", linha);
		query.setParameter("idbanho", idbanho);
		query.setParameter("datas", datas);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getallbyidbanho(Integer idbanho, Integer inicio, Integer fim, Integer id_analise) {
		Query query = entityManager.createNativeQuery(
				"SELECT ID_ANALISE,DATA_ANALISE,HORA_ANALISE, ( SELECT COUNT(*) AS totalPayments FROM AB_MOV_ANALISE where ID_BANHO = :idbanho and ID_ANALISE not in (:id_analise) ) FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY DATA_ANALISE desc) as row FROM AB_MOV_ANALISE where ID_BANHO = :idbanho and ID_ANALISE not in (:id_analise) ) a WHERE row > :inicio and row <= :fim");
		query.setParameter("idbanho", idbanho);
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);
		query.setParameter("id_analise", id_analise);
		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

	public List<AB_MOV_ANALISE> getallbyidbanho_manut(Integer idbanho, Integer inicio, Integer fim,List<HashMap<String, String>> dados) throws ParseException {
		HashMap<String, String> firstMap = dados.get(0);
		String date;
		String date2;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		date = (firstMap.get("date1") == null ? null : firstMap.get("date1").toString());


		date2 = (firstMap.get("date2") == null ? null : firstMap.get("date2").toString());
		
		Query query = entityManager.createNativeQuery(
				"select  a.ID_ANALISE,b.ID_MANUTENCAO_CAB,a.DATA_ANALISE,a.HORA_ANALISE,b.DATA_EXECUCAO,ROW_NUMBER() OVER (ORDER BY b.DATA_EXECUCAO desc) as row, "
						+ "( SELECT COUNT(*) AS totalPayments  " + "FROM  "
						+ "(select * from AB_MOV_ANALISE where ((not'"+date+"' != 'null') or (DATA_ANALISE <='"+date+"')) and ((not '"+date2+"' != 'null') or (DATA_ANALISE >= '"+date2+"'))) a " + "FULL join  "
						+ "(select f.* from AB_MOV_MANUTENCAO_CAB f,AB_MOV_MANUTENCAO g where f.id_banho= :idbanho and f.ID_MANUTENCAO = g.ID_MANUTENCAO and g.CLASSIF='M' and ((not '"+date+"' != 'null') or (f.DATA_EXECUCAO <='"+date+"')) and ((not '"+date2+"' != 'null') or (f.DATA_EXECUCAO >= '"+date2+"')) ) b on a.ID_ANALISE = b.ID_ANALISE),b.ID_MANUTENCAO "
						+ "FROM  " + "(select * from AB_MOV_ANALISE where ((not '"+date+"' != 'null') or (DATA_ANALISE <= '"+date+"')) and ((not '"+date2+"' != 'null') or (DATA_ANALISE >= '"+date2+"'))) a "
						+ "FULL join  "
						+ "(select f.* from AB_MOV_MANUTENCAO_CAB f,AB_MOV_MANUTENCAO g where f.ID_BANHO= :idbanho and f.ID_MANUTENCAO = g.ID_MANUTENCAO and g.CLASSIF='M' and ((not '"+date+"' != 'null') or (f.DATA_EXECUCAO <= '"+date+"')) and ((not '"+date2+"' != 'null') or (f.DATA_EXECUCAO >= '"+date2+"')) ) b on a.ID_ANALISE = b.ID_ANALISE "
						+ "order by b.DATA_EXECUCAO desc, a.DATA_ANALISE desc " + "OFFSET :inicio ROWS " + "FETCH NEXT :fim ROWS ONLY ");
		query.setParameter("idbanho", idbanho);
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);


		List<AB_MOV_ANALISE> data = query.getResultList();
		return data;

	}

}
