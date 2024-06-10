package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.DocenteEntity;
import com.ms.grademaster.comons.entity.DocenteMateriaEntity;
import com.ms.grademaster.comons.entity.DocenteMateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteMateriaRepository extends JpaRepository<DocenteMateriaEntity, DocenteMateriaId> {

    @Query(value = """
    select m.codigo, m.nombre, m.ncreditos, mh.hora_inicio, mh.hora_final  from dbo.docente_materia dm
    inner join dbo.Estado e on dm.id_estado_fk = e.id_estado inner join dbo.Materia m on dm.codigo_materia_fk = m.codigo
    inner join dbo.materia_horario mh on m.codigo = mh.codigo_materia_fk
    where dm.codigo_docente_fk = :codigoDocente and e.nombre = :codigoEstado
    """, nativeQuery = true)
    List<Object[]> buscarMateriasPorDocente(@Param("codigoDocente") String codigoDocente, @Param("codigoEstado") String codigoEstado);

    @Query(value = """
    select e.codigo_estudiante, e.nombres, e.apellidos from dbo.docente_materia dm
        inner join dbo.estudiante_materia em on dm.codigo_materia_fk = em.codigo_materia_fk
    inner join dbo.Estudiante e on e.codigo_estudiante = em.codigo_estudiante_fk
    where dm.codigo_docente_fk = :codigoDocente and dm.codigo_materia_fk = :codigoMateria
    """, nativeQuery = true)
    List<Object[]> buscarEstudiantesDocente(@Param("codigoDocente") String codigoDocente, @Param("codigoMateria") String codigoMateria);

    void deleteByCodigoMateriaEntityFk_Codigo(String codigoMateria);

}
