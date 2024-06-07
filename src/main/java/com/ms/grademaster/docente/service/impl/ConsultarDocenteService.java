package com.ms.grademaster.docente.service.impl;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.comons.mapper.DocenteMapper;
import com.ms.grademaster.comons.mapper.EstudianteMapper;
import com.ms.grademaster.comons.mapper.MateriaMapper;
import com.ms.grademaster.docente.dto.MateriasDocenteDto;
import com.ms.grademaster.docente.repository.DocenteMateriaRepository;
import com.ms.grademaster.docente.repository.DocenteRepository;
import com.ms.grademaster.docente.repository.MateriaRepository;
import com.ms.grademaster.docente.service.IConsultarDocenteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultarDocenteService implements IConsultarDocenteService {

    private final DocenteRepository docenteRepository;

    private final DocenteMapper docenteMapper;

    private final DocenteMateriaRepository docenteMateriaRepository;

    private final MateriaMapper materiaMapper;

    private final MateriaRepository materiaRepository;

    private final EstudianteMapper estudianteMapper;

    @Override
    public List<DocenteDto> buscarDocentesHabilitados() {
        try {
            return docenteMapper.listEntityToListDto( docenteRepository.findAllByEstadoEntityFk_Nombre("HABILITADO"));
        }catch (Exception e){
            log.error(e);
        }
        return null;
    }

    @Override
    public MateriasDocenteDto buscarMateriasPorDocente(String codigoDocente) {
        MateriasDocenteDto materiaDocente = new MateriasDocenteDto();
        log.error(materiaRepository.findAllHabilitados("HABILITADO"));
        List<MateriaDto> materias = materiaMapper.listEntityToListDto(materiaRepository.findAllHabilitados("HABILITADO"));
        List<MateriaDto> materiasAsignadas = materiaMapper.lisObjectToListMateriaDocente(docenteMateriaRepository.buscarMateriasPorDocente(codigoDocente, "HABILITADO"));
        materiaDocente.setMateriasAsignadas(materiasAsignadas);
        materiaDocente.setMateriasNoAsignadas(materias.stream().filter(materia -> materiasAsignadas.stream().noneMatch(materiaAsig -> materiaAsig.getCodigo().equals(materia.getCodigo()))).collect(Collectors.toList()));
        return materiaDocente;
    }

    @Override
    public List<EstudianteDto> estudiantesPorMateria(String codigoDocente, String codigoMateria) {
        return estudianteMapper.listObjectsToListDto(docenteMateriaRepository.buscarEstudiantesDocente(codigoDocente, codigoMateria));
    }

    @Override
    public List<MateriaDto> materiasAsiganadasDocente(String codigoDocente) {
        return  materiaMapper.lisObjectToListMateriaDocente(docenteMateriaRepository.buscarMateriasPorDocente(codigoDocente, "HABILITADO"));
    }
}
