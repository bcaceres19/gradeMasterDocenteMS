package com.ms.grademaster.docente.controller;

import com.ms.grademaster.comons.dto.DocenteDto;
import com.ms.grademaster.comons.dto.EstudianteDto;
import com.ms.grademaster.comons.dto.RespuestaGeneralDto;
import com.ms.grademaster.comons.service.IGenerarRecursos;
import com.ms.grademaster.comons.utils.constants.Constantes;
import com.ms.grademaster.comons.utils.enums.EstadoRespuestaEnum;
import com.ms.grademaster.comons.utils.enums.TipoUsuarioEnum;
import com.ms.grademaster.docente.dto.AsignarMateriasDto;
import com.ms.grademaster.docente.service.IConsultarDocenteService;
import com.ms.grademaster.docente.service.ICrearDocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class DocenteController {

    private final ICrearDocenteService crearDocenteService;

    private final IGenerarRecursos iGenerarRecursos;

    private final IConsultarDocenteService iConsultarDocenteService;

    @PostMapping("crear")
    public ResponseEntity<RespuestaGeneralDto> crearEstudiante(@RequestBody DocenteDto docenteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        crearDocenteService.crearDocente(docenteDto);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("codigo")
    public ResponseEntity<RespuestaGeneralDto> generarCodigo(@RequestBody DocenteDto docenteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iGenerarRecursos.generarCodigo(docenteDto, TipoUsuarioEnum.DOCENTE, Constantes.INICIALES_DOCENTE));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("correo")
    public ResponseEntity<RespuestaGeneralDto> generarCorreo(@RequestBody DocenteDto docenteDto){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iGenerarRecursos.generarCorreo(docenteDto, TipoUsuarioEnum.DOCENTE));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("allDocentesActivos")
    public ResponseEntity<RespuestaGeneralDto> getAllDocentesActivos(){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultarDocenteService.buscarDocentesHabilitados());
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("bucar-docente-nombre")
    public ResponseEntity<RespuestaGeneralDto> getBuscarDocenteNombre(@RequestParam("nombre") String nombre){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultarDocenteService.buscarDocentesNombre(nombre));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("allMateriasDocente")
    public ResponseEntity<RespuestaGeneralDto> getAllMateriasDocente(@RequestParam("codigoDocente") String codigoDocente){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultarDocenteService.buscarMateriasPorDocente(codigoDocente));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @PostMapping("asignarMaterias")
    public ResponseEntity<RespuestaGeneralDto> asignarMateriasDocente(@RequestBody AsignarMateriasDto asignarMaterias){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        crearDocenteService.asignarMateriasDocente(asignarMaterias);
        respuestaGeneral.setData(true);
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("allEstudiantesPorMateria")
    public ResponseEntity<RespuestaGeneralDto> getAllEstudiantesMateria(@RequestParam("codigoDocente") String codigoDocente,
                                                                        @RequestParam("codigoMateria") String codigoMateria){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultarDocenteService.estudiantesPorMateria(codigoDocente,codigoMateria));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

    @GetMapping("materiasAsignadasDocente")
    public ResponseEntity<RespuestaGeneralDto> getMateriasAsignadasDocente(@RequestParam("codigoDocente") String codigoDocente){
        RespuestaGeneralDto respuestaGeneral =  new RespuestaGeneralDto();
        respuestaGeneral.setData(iConsultarDocenteService.materiasAsiganadasDocente(codigoDocente));
        respuestaGeneral.setEstado(EstadoRespuestaEnum.OK);
        return ResponseEntity.ok(respuestaGeneral);
    }

}
