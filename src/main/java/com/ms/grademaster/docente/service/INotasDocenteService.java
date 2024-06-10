package com.ms.grademaster.docente.service;

import com.ms.grademaster.comons.dto.NotaDto;
import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.docente.dto.GenerarNuevoCorteDto;
import com.ms.grademaster.docente.dto.NotasGetDto;

import java.util.List;

public interface INotasDocenteService {

    Boolean existePreNotas(String codigoDocente, String codigoMateria);

    void crearNotas(List<NotaDto> notas);

    void crearNotasEstudiante(List<NotaMateriaDto> notaMateriaDto);

    List<NotasGetDto> getAllNotasMateriaDocente(String codigoEstudiante,
                                                String codigoDocente,
                                                String codigoMateria,
                                                Long numeroCorte);

    NotaMateriaDto getAllNotasMateriaEstudiante(String codigoEstudiante, String codigoMateria, String codigoNota, Long numeroCorte);

    String ultimoCorteMateria(String codigoMateria);

    void crearNuevoCorte(GenerarNuevoCorteDto generarNuevoCorte);

}
