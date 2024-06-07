package com.ms.grademaster.docente.dto;

import com.ms.grademaster.comons.dto.MateriaDto;
import lombok.Data;

import java.util.List;

@Data
public class MateriasDocenteDto {

    private List<MateriaDto> materiasAsignadas;

    private List<MateriaDto> materiasNoAsignadas;

}
