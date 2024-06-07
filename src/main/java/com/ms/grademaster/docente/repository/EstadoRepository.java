package com.ms.grademaster.docente.repository;

import com.ms.grademaster.comons.entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer> {

    EstadoEntity findByNombre(String nombre);

}
