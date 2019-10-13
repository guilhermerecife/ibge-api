package br.com.gcdm.ibgeapi;

import java.util.List;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.gcdm.ibgeapi.models.Estado;
import br.com.gcdm.ibgeapi.models.Municipio;

@Service
public class IbgeApi {
	
	private static RestTemplate restTemplate;
	
	private static final String URL_MUNICIPIOS_DO_ESTADO = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%d/municipios";
	private static final String URL_MUNICIPIO = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/%d";
	
	private static RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
		            HttpClientBuilder.create().build());
			restTemplate = new RestTemplate(clientHttpRequestFactory);
		}
		return restTemplate;
	}
	
	/**
	 * Busca município.
	 * @param idMunicipio
	 * @return
	 */
	public Municipio buscaMunicipio(int idMunicipio) {
		return getRestTemplate().getForObject(String.format(URL_MUNICIPIO, idMunicipio), Municipio.class);
	}
	
	/**
	 * Lista municípios do estado.
	 * @param estado
	 * @return
	 */
	public List<Municipio> listaMunicipios(Estado estado) {
		return listaMunicipios(estado.getId());
	}
	
	/**
	 * Lista municípios do estado.
	 * @return
	 */
	public List<Municipio> listaMunicipios(int estado) {
		String url = String.format(URL_MUNICIPIOS_DO_ESTADO, estado);
		ResponseEntity<List<Municipio>> s = getRestTemplate().exchange(url
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<List<Municipio>>() {
				});
		return s.getBody();
	}
	
}
