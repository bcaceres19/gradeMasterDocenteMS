package com.ms.grademaster.docente.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NotasGetDto {

    private String codigoNota;
    private String nombreColumna;
    private String nombreCampo;
    private BigDecimal porcentajeNota;
    private Short nrNota;
    private BigDecimal valorNota;
}
