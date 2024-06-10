package com.ms.grademaster.docente.dto;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.comons.dto.SemestreDto;
import lombok.Data;

import java.util.List;

@Data
public class AsignarMateriasDto {

    private SemestreDto semestreDto;

    private DocenteDto docenteDto;

    private List<MateriaDto> materiasAsignar;

    private List<MateriaDto> materiasNoAsignadas;

}
