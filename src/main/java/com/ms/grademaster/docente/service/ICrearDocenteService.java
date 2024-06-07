package com.ms.grademaster.docente.service;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.docente.dto.AsignarMateriasDto;

import java.util.List;

public interface ICrearDocenteService {

    void crearDocente(DocenteDto docenteDto);

    void asignarMateriasDocente(AsignarMateriasDto asignarMateriasDto);

}
