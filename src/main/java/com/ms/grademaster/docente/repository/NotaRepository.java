package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.dto.NotaDto;
import com.ms.grademaster.comons.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, String> {

    Boolean existsByMateriaEntityFk_CodigoAndDocenteEntityFk_CodigoDocente(String codigoMateria,String codigoDocente);

    @Query(value = """
    SELECT new NotaEntity(n.codigoNota, n.porcentajeNota, n.nrNota) FROM NotaEntity n where n.materiaEntityFk.codigo = :codigoMateria and n.docenteEntityFk.codigoDocente = :codigoDocente
    """)
    List<NotaEntity> allNotasByMateriaAndDocente(@Param("codigoMateria") String codigoMateria, @Param("codigoDocente") String codigoDocente);

}
