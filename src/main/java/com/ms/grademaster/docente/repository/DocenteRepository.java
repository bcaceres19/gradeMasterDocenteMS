package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, String> {

    List<DocenteEntity> findAllByEstadoEntityFk_Nombre(String nombre);

    @Query(value = """
        SELECT d FROM DocenteEntity d WHERE d.nombres LIKE %:nombre%
    """)
    List<DocenteEntity> buscarDocentesNombre(@Param("nombre") String nombre);

}
