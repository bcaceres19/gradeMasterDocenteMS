package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.comons.entity.NotaMateriaEntity;
import com.ms.grademaster.comons.entity.NotaMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    n.codigoNota = :codigoNota AND nm.numeroCorte = :numeroCorte
    """)
    NotaMateriaEntity findByNotasEstudiante(@Param("codigoEstudiante") String codigoEstudiante,
                                            @Param("codigoMateria")String codigoMateria,
                                            @Param("codigoNota") String codigoNota,
                                            @Param("numeroCorte") Long numeroCorte
                                            );


    @Query(value = """
    select nm.numero_corte  from dbo.nota_materia nm where nm.codigo_materia_fk = :codigoMateria order by nm.numero_corte desc limit 1
    """, nativeQuery = true)
    String ultimoCorteMateria(@Param("codigoMateria") String codigoMateria);

    @Query(value = """
            select nm.codigo_semestre_fk,nm.codigo_materia_fk, codigo_estudiante_fk,codigo_nota_fk
                  from dbo.nota_materia nm where nm.codigo_materia_fk = :codigoMateria
    """, nativeQuery = true)
    List<Object[]> buscarMateriasUltimoCorte(@Param("codigoMateria") String codigoMateria);

    @Query(value = """
    insert into dbo.nota_materia(codigo_semestre_fk,
                                 codigo_materia_fk, 
                                 codigo_estudiante_fk,
                                 codigo_nota_fk,
                                 valor_nota,
                                 numero_corte)
    values(:#{#notaMateria.codigoSemestreEntityFk.codigoSemestre},
           :#{#notaMateria.codigoMateriaEntityFk.codigo},
           :#{#notaMateria.codigoEstudianteEntityFk.codigoEstudiante},
           :#{#notaMateria.codigoNotaEntityFk.codigoNota},
           0.0,
           :#{#notaMateria.numeroCorte}          
           )
    """, nativeQuery = true)
    @Modifying
    void crearNotasNuevas(@Param("notaMateria") NotaMateriaDto notaMateriaDto);

}
