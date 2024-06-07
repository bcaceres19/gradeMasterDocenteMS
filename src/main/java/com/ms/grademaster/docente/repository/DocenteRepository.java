package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, String> {

    List<DocenteEntity> findAllByEstadoEntityFk_Nombre(String nombre);

}
