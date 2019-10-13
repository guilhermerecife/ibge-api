package br.com.gcdm.ibgeapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Municipio {
	private int id;
	private String nome;
}
