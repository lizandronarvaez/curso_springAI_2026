package com.lizandronarvaez.spring_ai.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Clase: GetCapitalResponse.java
 * Autor: narvaez
 * Fecha: 9/1/26
 */
public record GetCapitalResponse(
        @JsonPropertyDescription("Nombre de la capital") String ciudad,
        @JsonPropertyDescription("El nombre de la moneda oficial y su símbolo") String moneda
        ) {
}
