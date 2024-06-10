package com.ms.grademaster.docente.service.impl;

import com.ms.grademaster.comons.dto.NotaDto;
import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.comons.mapper.NotaMapper;
import com.ms.grademaster.comons.mapper.NotaMateriaMapper;
import com.ms.grademaster.docente.dto.GenerarNuevoCorteDto;
import com.ms.grademaster.docente.dto.NotasGetDto;
import com.ms.grademaster.docente.repository.NotaMateriaRepository;
import com.ms.grademaster.docente.repository.NotaRepository;
import com.ms.grademaster.docente.service.INotasDocenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasDocenteService implements INotasDocenteService {

    private final NotaRepository notaRepository;

    private final NotaMapper notaMapper;

    private final NotaMateriaMapper notaMateriaMapper;

    private final NotaMateriaRepository notaMateriaRepository;

    @Override
    public Boolean existePreNotas(String codigoDocente, String codigoMateria) {
        return notaRepository.existsByMateriaEntityFk_CodigoAndDocenteEntityFk_CodigoDocente(codigoMateria, codigoDocente);
    }

    @Override
    @Transactional
    public void crearNotas(List<NotaDto> notas) {
        this.notaRepository.saveAll(notaMapper.listDtoToListEntity(notas));
    }

    @Override
    @Transactional
    public void crearNotasEstudiante(List<NotaMateriaDto> notaMateriaDto) {
        this.notaMateriaRepository.saveAll(notaMateriaMapper.listDtoToListEntityTru(notaMateriaDto));
    }

    @Override
    public List<NotasGetDto> getAllNotasMateriaDocente(String codigoEstudiante,
                                                       String codigoDocente,
                                                       String codigoMateria,
                                                       Long numeroCorte) {
        List<NotaDto> notasDocente= notaMapper.listEntityToListDto(notaRepository.allNotasByMateriaAndDocente(codigoMateria, codigoDocente));
        List<NotasGetDto> notasGetDto = new ArrayList<>();
        for (NotaDto notaDto : notasDocente) {
            NotasGetDto notasGetDto1 = new NotasGetDto();
            notasGetDto1.setCodigoNota(notaDto.getCodigoNota());
            StringBuilder nombreCol = new StringBuilder();
            nombreCol.append(notaDto.getNrNota()).append(")").append(notaDto.getPorcentajeNota().toString().replace(".", ",")).append("%");
            notasGetDto1.setNombreColumna(nombreCol.toString());
            notasGetDto1.setNombreCampo(nombreCol.toString());
            notasGetDto1.setPorcentajeNota(notaDto.getPorcentajeNota());
            notasGetDto1.setNrNota(notaDto.getNrNota());
            NotaMateriaDto notaMateriaDto = getAllNotasMateriaEstudiante(codigoEstudiante, codigoMateria, notaDto.getCodigoNota(),numeroCorte);
            if(notaMateriaDto != null && notaMateriaDto.getValorNota() != null){
                notasGetDto1.setValorNota(notaMateriaDto.getValorNota());
            }else{
                notasGetDto1.setValorNota(BigDecimal.valueOf(0.0));
            }
            notasGetDto.add(notasGetDto1);
        }
        return notasGetDto;
    }

    @Override
    public NotaMateriaDto getAllNotasMateriaEstudiante(String codigoEstudiante, String codigoMateria, String codigoNota, Long numeroCorte) {
        return notaMateriaMapper.entityToDto(notaMateriaRepository.findByNotasEstudiante(codigoEstudiante, codigoMateria,codigoNota, numeroCorte));
    }

    @Override
    public String ultimoCorteMateria(String codigoMateria) {
        return notaMateriaRepository.ultimoCorteMateria(codigoMateria);
    }

    @Override
    @Transactional
    public void crearNuevoCorte(GenerarNuevoCorteDto generarNuevoCorte) {
        List<NotaMateriaDto> listaNotaMateriaDto = notaMateriaMapper.listObjectsToListDto(notaMateriaRepository.buscarMateriasUltimoCorte(generarNuevoCorte.getCodigoMateria()));
        for (NotaMateriaDto notaMateriaDto : listaNotaMateriaDto) {
            notaMateriaDto.setNumeroCorte(Long.valueOf(generarNuevoCorte.getNumeroCorteNuevo()));
            notaMateriaRepository.crearNotasNuevas(notaMateriaDto);
        }
    }
}
