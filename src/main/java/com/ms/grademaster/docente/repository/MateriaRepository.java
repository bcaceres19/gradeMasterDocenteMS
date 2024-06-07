package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, String> {

    @Query(value = "SELECT new MateriaEntity(m.codigo, m.nombre) FROM MateriaEntity m where m.estadoEntityFk.nombre = :nombreEstado")
    List<MateriaEntity> findAllHabilitados(@Param("nombreEstado") String nombreEstado);

}
