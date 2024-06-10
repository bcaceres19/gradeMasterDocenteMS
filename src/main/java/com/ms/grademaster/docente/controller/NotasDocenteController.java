package com.ms.grademaster.docente.controller;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.NotaDto;
import com.ms.grademaster.comons.dto.NotaMateriaDto;
import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.service.IGenerarRecursos;
import com.ms.grademaster.comons.utils.constants.Constantes;
import com.ms.grademaster.comons.utils.enums.EstadoRespuestaEnum;
import com.ms.grademaster.comons.utils.enums.TipoUsuarioEnum;
import com.ms.grademaster.docente.dto.GenerarNuevoCorteDto;
import com.ms.grademaster.docente.service.INotasDocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notasDocente")
@RequiredArgsConstructor
public class NotasDocenteController {

    private final INotasDocenteService notasDocenteService;

    private final IGenerarRecursos iGenerarRecursos;

    @GetMapping("allNotasMateriaDocente")
    public ResponseEntity<RespuestaGeneralDto> getAllNotasMateriaDocente(
            @RequestParam("codigoEstudiante") String codigoEstudiante,
            @RequestParam("codigoDocente") String codigoDocente,
            @RequestParam("codigoMateria") String codigoMateria,
            @RequestParam("numeroCorte") Long numeroCorte){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(notasDocenteService.getAllNotasMateriaDocente(codigoEstudiante,codigoDocente, codigoMateria,numeroCorte));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);

    }

    @GetMapping("existenciaNotasDocente")
    public ResponseEntity<RespuestaGeneralDto> verificarExistenciaNotas(
            @RequestParam("codigoDocente") String codigoDocente,
            @RequestParam("codigoMateria") String codigoMateria){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(notasDocenteService.existePreNotas(codigoDocente, codigoMateria));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("ultimo-corte-materia")
    public ResponseEntity<RespuestaGeneralDto> ultimoCorteMateria(
            @RequestParam("codigoMateria") String codigoMateria){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(notasDocenteService.ultimoCorteMateria(codigoMateria));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("generar-nuevo-corte")
    public ResponseEntity<RespuestaGeneralDto> generarNuevoCorte(
            @RequestBody GenerarNuevoCorteDto generarNuevoCorteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        notasDocenteService.crearNuevoCorte(generarNuevoCorteDto);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("generarCodigo")
    public ResponseEntity<RespuestaGeneralDto> generarCodigo(){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iGenerarRecursos.generarCodigo(null, TipoUsuarioEnum.NOTA, Constantes.INICIALES_NOTA));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("crearNotas")
    public ResponseEntity<RespuestaGeneralDto> crearNotas(
            @RequestBody List<NotaDto> notaDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        notasDocenteService.crearNotas(notaDto);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("crearNotasEstudiante")
    public ResponseEntity<RespuestaGeneralDto> crearNotasEstudiante(
            @RequestBody List<NotaMateriaDto> notaMateriaDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        notasDocenteService.crearNotasEstudiante(notaMateriaDto);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

}
