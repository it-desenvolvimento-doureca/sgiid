package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.MAN_MOV_MANUTENCAO_CAB;
import pt.example.entity.MAN_MOV_MANUTENCAO_CAB; 

public class MAN_MOV_MANUTENCAO_CABDao extends GenericDaoJpaImpl<MAN_MOV_MANUTENCAO_CAB, Integer> implements GenericDao<MAN_MOV_MANUTENCAO_CAB, Integer> {
	public MAN_MOV_MANUTENCAO_CABDao() {
		super(MAN_MOV_MANUTENCAO_CAB.class);
	}

	public List<MAN_MOV_MANUTENCAO_CAB> getall() {

		Query query = entityManager.createQuery("Select a from MAN_MOV_MANUTENCAO_CAB a ");

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> getHistorico(Integer id,String tipo,String datainicio,String datafim) {

		String query_filter = "";
		if(datainicio != null && !datainicio.equals("null")) query_filter = " and CAST(a.DATA_CRIA as DATE) >= '"+datainicio+"' ";
		if(datafim != null && !datafim.equals("null")) query_filter = query_filter+ " and CAST(a.DATA_CRIA as DATE) >= '"+datainicio+"' ";
		if(tipo != null && !tipo.equals("null")) query_filter = query_filter+ " and a.TIPO_MANUTENCAO = '"+tipo+"' ";
		
		Query query = entityManager.createNativeQuery("select a.TIPO_MANUTENCAO,a.ESTADO,a.DATA_INICIO,a.DATA_FIM,a.DATA_HORA_PEDIDO,a.DATA_CRIA,a.ID_MANUTENCAO_CAB "
				+ ",c.DESCRICAO_PT,b.TEMPO,b.TEMPO_ESTIMADO,b.REALIZADA , CASE WHEN b.TIPO_REPETICAO= 2 THEN 'W'  WHEN b.TIPO_REPETICAO= 3 THEN CASE WHEN b.REPETIR = 1 THEN 'M' WHEN b.REPETIR = 2 THEN 'B' WHEN b.REPETIR = 3 THEN 'T' WHEN b.REPETIR = 4 THEN 'Q' WHEN b.REPETIR = 6 THEN 'S' ELSE "
				+ "	'M' END  WHEN b.TIPO_REPETICAO = 4 THEN 'A' WHEN b.TIPO_REPETICAO= 1 THEN 'D' ELSE '' END   periodicidade "
				+ "from MAN_MOV_MANUTENCAO_CAB a "
				+ "left join MAN_MOV_MANUTENCAO_ACCOES b on a.ID_MANUTENCAO_CAB = b.ID_MANUTENCAO_CAB "
				+ "left join GT_DIC_TAREFAS c on b.ID_ACAO = c.ID "
				+ "where EQUIPAMENTO = "+id+" and ESTADO in ('P','C') and ESTADO not in ('A') " 
				+ query_filter
				+ "order by ISNULL(DATA_HORA_PEDIDO,a.DATA_CRIA) desc");

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> getall2(String classificacao,String user) {

		Query query = entityManager.createNativeQuery("EXEC MAN_GET_PEDIDOS_LISTA '" + classificacao +"',"+user);

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> MAN_GET_MANUTENCOES_MELHORIA(String id) {

		Query query = entityManager.createNativeQuery("EXEC MAN_GET_MANUTENCOES_MELHORIA "+id);

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> getbyid_MANUTENCAO(Integer id) {
		Query query = entityManager.createNativeQuery(
				"select a.ID_MANUTENCAO_CAB,a.DATA_INICIO,a.ESTADO from MAN_MOV_MANUTENCAO_CAB a "
				+ "WHERE a.ID_ORIGEM = :id AND a.ESTADO in ('E','P','S') ");
		query.setParameter("id", id);

		List<MAN_MOV_MANUTENCAO_CAB> data = query.getResultList();
		return data;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> getall3(String tipo,String id) {

		Query query = entityManager.createNativeQuery("EXEC MAN_GET_MANUTENCOES_PENDENTES'" + tipo +"','"+id+"'");

		List<MAN_MOV_MANUTENCAO_CAB> utz = query.getResultList();
		return utz;

	}
	
	public List<MAN_MOV_MANUTENCAO_CAB> getbyId(Integer id) {
		Query query = entityManager.createQuery(
				"select a from MAN_MOV_MANUTENCAO_CAB a where a.ID_MANUTENCAO_CAB = :id");
		query.setParameter("id", id);

		List<MAN_MOV_MANUTENCAO_CAB> data = query.getResultList();
		return data;

	}
}
