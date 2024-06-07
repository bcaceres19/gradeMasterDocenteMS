package com.ms.grademaster.docente.service;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.docente.dto.MateriasDocenteDto;

import java.util.List;

public interface IConsultarDocenteService {

    List<DocenteDto> buscarDocentesHabilitados();

    MateriasDocenteDto buscarMateriasPorDocente(String codigoDocente);

    List<EstudianteDto> estudiantesPorMateria(String codigoDocente, String codigoMateria);

    List<MateriaDto> materiasAsiganadasDocente(String codigoDocente);

}
