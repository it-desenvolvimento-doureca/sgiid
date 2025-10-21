package pt.example.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Query;

import pt.example.entity.RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA;

public class RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHADao
		extends GenericDaoJpaImpl<RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA, Integer>
		implements GenericDao<RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA, Integer> {

	public RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHADao() {
		super(RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA.class);
	}

	public List<RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA> getByCategoriaId(Integer grupoId) {
		Query query = entityManager.createQuery(
				"SELECT l FROM RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA l WHERE l.grupoColaboradores.ID = :grupoId");
		query.setParameter("grupoId", grupoId);
		return query.getResultList();
	}

	public List<RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA> getAll() {
		Query query = entityManager.createQuery("SELECT l FROM RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA l");
		return query.getResultList();
	}

	public List<Object[]> getCategoriasAll(Integer idCategoriaProfissional) {
		Query query = entityManager
				.createNativeQuery("EXEC RH_GET_DADOS_ESIGEMP 'RHD_CAT_PROFISSIONAIS_PROFISSAO', ''");
		List<Object[]> all = query.getResultList();

		Query associados = entityManager.createNativeQuery(
				"SELECT ID_CATEGORIA FROM RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA WHERE ID_CATEGORIA_PROFISSIONAL = "
						+ idCategoriaProfissional + "");
		List<String> ids = associados.getResultList();

		return all.stream().filter(row -> {
			String idCat = String.valueOf(row[0]); // ID_CATEGORIA na posição 0
			return !ids.contains(idCat);
		}).collect(Collectors.toList());
	}

	public List<Object[]> getCategoriasGrupos(Integer idCategoriaProfissional) {
	    Query query = entityManager
	            .createNativeQuery("EXEC RH_GET_DADOS_ESIGEMP 'RHD_CAT_PROFISSIONAIS_PROFISSAO', ''");
	    List<Object[]> all = query.getResultList();

	    Query associados = entityManager.createNativeQuery(
	            "SELECT ID_CATEGORIA, ID FROM RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA WHERE ID_CATEGORIA_PROFISSIONAL = "
	                    + idCategoriaProfissional);
	    List<Object[]> associadosList = associados.getResultList();

	    // Mapeia os IDs de categoria para seus respectivos IDs associados
	    Map<String, String> categoriaToIdMap = associadosList.stream()
	            .collect(Collectors.toMap(
	                    row -> String.valueOf(row[0]), // ID_CATEGORIA
	                    row -> String.valueOf(row[1])  // ID
	            ));

	    return all.stream()
	            .filter(row -> categoriaToIdMap.containsKey(String.valueOf(row[0]))) // ID_CATEGORIA na posição 0
	            .map(row -> {
	                Object[] newRow = Arrays.copyOf(row, row.length + 1);
	                newRow[row.length] = categoriaToIdMap.get(String.valueOf(row[0])); // adiciona o ID associado
	                return newRow;
	            })
	            .collect(Collectors.toList());
	}


}