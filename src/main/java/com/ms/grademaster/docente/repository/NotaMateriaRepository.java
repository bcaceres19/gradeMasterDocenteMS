package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.NotaMateriaEntity;
import com.ms.grademaster.comons.entity.NotaMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaMateriaRepository extends JpaRepository<NotaMateriaEntity, NotaMateriaId> {

    @Query(value = """
    SELECT new NotaMateriaEntity(nm.valorNota) FROM NotaMateriaEntity nm 
    inner join EstudianteEntity e on nm.notaMateriaId.codigoEstudianteEntityFk = e.codigoEstudiante
    inner join MateriaEntity m on nm.notaMateriaId.codigoMateriaEntityFk = m.codigo
    inner join NotaEntity n on nm.notaMateriaId.codigoNotaEntityFk = n.codigoNota
    WHERE e.codigoEstudiante = :codigoEstudiante
    AND m.codigo = :codigoMateria AND
    n.codigoNota = :codigoNota
    """)
    NotaMateriaEntity findByNotasEstudiante(@Param("codigoEstudiante") String codigoEstudiante, @Param("codigoMateria")String codigoMateria, @Param("codigoNota") String codigoNota);

}
