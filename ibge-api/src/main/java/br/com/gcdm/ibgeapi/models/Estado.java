package br.com.gcdm.ibgeapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Estado {
	
	private int id;
	private String sigla;
	private String nome;
	
}
