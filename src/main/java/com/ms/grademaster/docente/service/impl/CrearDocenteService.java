package com.ms.grademaster.docente.service.impl;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.MateriaDto;
import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.mapper.*;
import com.ms.grademaster.docente.dto.AsignarMateriasDto;
import com.ms.grademaster.docente.repository.DocenteMateriaRepository;
import com.ms.grademaster.docente.repository.DocenteRepository;
import com.ms.grademaster.docente.repository.EstadoRepository;
import com.ms.grademaster.docente.service.ICrearDocenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrearDocenteService implements ICrearDocenteService {

    private final DocenteRepository docenteRepository;

    private final DocenteMapper docenteMapper;

    private final DocenteMateriaRepository docenteMateriaRepository;

    private final EstadoRepository estadoRepository;

    private final DocenteMateriaMapper docenteMateriaMapper;

    private final MateriaMapper materiaMapper;

    private final EstadoMapper estadoMapper;

    private final SemestreMapper semestreMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void crearDocente(DocenteDto docenteDto) {
        docenteRepository.save(docenteMapper.dtoToEntity(docenteDto));
    }

    @Override
    @Transactional
    public void asignarMateriasDocente(AsignarMateriasDto asignarMateriasDto) {
        if(!asignarMateriasDto.getMateriasNoAsignadas().isEmpty()){
            asignarMateriasDto.getMateriasNoAsignadas().forEach(materia -> {
                if(materia.getCodigo() != null){
                    docenteMateriaRepository.deleteByCodigoMateriaEntityFk_Codigo(materia.getCodigo());
                }
            });
        }
        docenteMateriaRepository.saveAll(docenteMateriaMapper.objectsToEntity(
                materiaMapper.listDtoToListEntity(asignarMateriasDto.getMateriasAsignar()),
                docenteMapper.dtoToEntity(asignarMateriasDto.getDocenteDto()),
                semestreMapper.dtoToEntity(asignarMateriasDto.getSemestreDto()),
                estadoRepository.findByNombre("ACTIVO")
        ));
    }
}
