package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_FORMACAO;

public class RH_FORMACAODao extends GenericDaoJpaImpl<RH_FORMACAO, Integer>
		implements GenericDao<RH_FORMACAO, Integer> {
	public RH_FORMACAODao() {
		super(RH_FORMACAO.class);
	}

	public List<RH_FORMACAO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_FORMACAO> data = query.getResultList();
		return data;

	}

	public List<RH_FORMACAO> getall() {

		Query query = entityManager.createQuery("Select a from RH_FORMACAO a ");
		List<RH_FORMACAO> data = query.getResultList();
		return data;

	}

	public List<RH_FORMACAO> getall2(String id) {

		Query query = entityManager.createNativeQuery("declare @id varchar(25) =  " + id
				+ " Select a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and ((@id is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @id))))");
		List<RH_FORMACAO> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_FORMACOES_TOTAIS(String ano, String utilizador, String DATA_INICIO, String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " " + " declare @ano varchar(25) =  " + ano
				+ " Select sum(case when CAST(GETDATE() as date) > DATA_FIM THEN 1 else 0 end) total_Terminadas, "
				+ "sum(case when CAST(GETDATE() as date) >= DATA_INICIO and  CAST(GETDATE() as date) <= DATA_FIM THEN 1 else 0 end) total_Em_Progresso, "
				+ "sum(case when DATA_INICIO > CAST(GETDATE() as date) THEN 1 else 0 end) total_Por_Iniciar, "
				
				+ "sum(case when CAST(GETDATE() as date) > DATA_FIM THEN DURACAO else 0 end * ISNULL(b.total,0)) total_Horas_Terminadas, "
				+ "sum(case when CAST(GETDATE() as date) >= DATA_INICIO and  CAST(GETDATE() as date) <= DATA_FIM THEN DURACAO else 0 end * ISNULL(b.total,0)) total_Horas_Em_Progresso, "
				+ "sum(case when DATA_INICIO > CAST(GETDATE() as date) THEN DURACAO else 0 end * ISNULL(b.total,0)) total_Horas_Por_Iniciar "
				+ " from RH_FORMACAO a "
				+ " left join (select count(f.ID_FUNCIONARIO) total,f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where ((@utilizador is null ) or (f.ID_FUNCIONARIO =  @utilizador)) group by ID_FORMACAO ) b on b.ID_FORMACAO = a.ID "
				+ " where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) ");
		List<Object[]> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_FORMACOES_TERMINADAS(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " " + " declare @ano varchar(25) =  " + ano
				+ " Select top 5 a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) "
				+ "and CAST(GETDATE() as date) > DATA_FIM order by DATA_FIM desc");
		List<Object[]> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_FORMACOES_EM_PROGRESSO(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " " + " declare @ano varchar(25) =  " + ano
				+ " Select top 5 a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) "
				+ "and  CAST(GETDATE() as date) >= DATA_INICIO and  CAST(GETDATE() as date) <= DATA_FIM order by DATA_INICIO");
		List<Object[]> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_FORMACOES_POR_INICIAR(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " " + " declare @ano varchar(25) =  " + ano
				+ " Select top 5 a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) "
				+ "and DATA_INICIO > CAST(GETDATE() as date) order by DATA_INICIO");
		List<Object[]> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_PROXIMAS_FORMACOES(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " declare @ano varchar(25) =  " + ano
				+ " Select a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano and DATA_INICIO > CAST(GETDATE() as date) order by DATA_INICIO");
		List<Object[]> data = query.getResultList();
		return data;

	}

	public List<Object[]> RH_GET_FORMACOES_EXPIRADAS(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " " + " declare @ano varchar(25) =  " + ano
				+ " Select a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) "
				+ "and CAST(GETDATE() as date) > VALIDADE order by DATA_INICIO");
		List<Object[]> data = query.getResultList();
		return data;

	}
	
	
	public List<Object[]> RH_GET_AVALIACAO_EFICACIA(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " declare @ano varchar(25) =  " + ano
				+ " Select a.ID, a.NOME, a.OBJETIVOS, a.ID_AREA_FORMACAO, a.MODALIDADE, a.ID_CRITERIOS_AVALIACAO, a.INTERMO_EXTERNO, a.ID_ENTIDADE_FORMADORA, a.FORMADOR, a.DATA_INICIO, a.DATA_FIM, a.DURACAO, a.HORARIO, a.VALIDADE, a.OBSERVACOES, a.AVALIACAO_EFICACIA, a.AVALIACAO_TEXTO, a.DATA_CRIA,a.ATIVO "
				+ ",b.DESCRICAO AREA_FORMACAO , C.DESCRICAO CRITERIOS_AVALIACAO,d.DESCRICAO ENTIDADE_FORMADORA "
				+ ",(select count(*) from RH_FORMACAO_PARTICIPANTES e where e.ID_FORMACAO = a.ID) TOTAL "
				+ "from RH_FORMACAO a " + "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador)))) "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO)) "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO)) "
				+ "and ISNULL(a.AVALIACAO_EFICACIA,0) = 0 and DATEDIFF(day,DATA_FIM,GETDATE()) > 90 order by DATA_INICIO");
		List<Object[]> data = query.getResultList();
		return data;

	}
	
	public List<Object[]> RH_GET_MAIS_35_HORAS(String ano, String utilizador, String DATA_INICIO,
			String DATA_FIM) {

		Query query = entityManager.createNativeQuery("declare @DATA_INICIO varchar(25) =  " + DATA_INICIO
				+ " declare @DATA_FIM varchar(25) =  " + DATA_FIM + " declare @utilizador varchar(25) =  " + utilizador
				+ " declare @ano varchar(25) =  " + ano
				+ " Select pa.NOME_FUNCIONARIO,sum(ISNULL(a.DURACAO,0)) TOTAL,pa.ID_FUNCIONARIO "
				+ "from RH_FORMACAO_PARTICIPANTES pa "
				+ "left join RH_FORMACAO a  on a.ID = pa.ID_FORMACAO "
				+ "left join RH_DIC_AREA_FORMACAO b on a.ID_AREA_FORMACAO = b.ID  "
				+ "left join RH_DIC_CRITERIOS_AVALIACAO c on a.ID_CRITERIOS_AVALIACAO = c.ID  "
				+ "left join RH_DIC_ENTIDADE_FORMADORA d on a.ID_ENTIDADE_FORMADORA = d.ID  "
				+ "where a.ATIVO = 1 and YEAR(DATA_INICIO) = @ano  "
				+ "AND ((@utilizador is null) or (a.ID in ((select f.ID_FORMACAO from RH_FORMACAO_PARTICIPANTES f where f.ID_FUNCIONARIO = @utilizador))))  "
				+ "AND ((@DATA_INICIO is null ) or ( @DATA_INICIO <= DATA_INICIO))  "
				+ "AND ((@DATA_FIM is null ) or ( @DATA_FIM >= DATA_INICIO))  "
				+ "group by pa.NOME_FUNCIONARIO,pa.ID_FUNCIONARIO "
				+ "having sum(ISNULL(a.DURACAO,0)) >= 35 "
				+ "order by TOTAL desc "
				+ "");
		List<Object[]> data = query.getResultList();
		return data;

	}
}
