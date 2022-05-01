package co.tyba.tureserva.clientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.excepciones.ExcepcionTecnica;
import co.tyba.tureserva.modelo.ApiTomTomRespuesta;
import co.tyba.tureserva.modelo.ObtenerRestaurantesPeticion;
import co.tyba.tureserva.utilidades.CargadorPropiedad;

public class TomtomAPICliente {

	public ApiTomTomRespuesta obtenerLugaresCercanos(ObtenerRestaurantesPeticion peticion) throws ExcepcionTecnica {

		ApiTomTomRespuesta response = new ApiTomTomRespuesta();

		try {

			String urlFinal = obtenerDatosAPI(peticion);

			System.out.println(urlFinal);

			URL url = new URL(urlFinal);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);

				response = new ObjectMapper().readValue(output, ApiTomTomRespuesta.class);
				System.out.println("API Response " + response.toString());
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

		return response;

	}

	private String obtenerDatosAPI(ObtenerRestaurantesPeticion peticion) throws ExcepcionTecnica {

		String urlApi = "";
		String keyApi = "";

		try {
			urlApi = CargadorPropiedad.getInstance().getPropiedad(MsConstantes.URL_API_TOMTOM);
			keyApi = CargadorPropiedad.getInstance().getPropiedad(MsConstantes.KEY_API_TOMTOM);

		} catch (RuntimeException | ExcepcionTecnica e) {
			throw new ExcepcionTecnica("Ocurrio un error consultando los parametros del archivo de propiedades", e);
		}

		return String.format(urlApi, peticion.getLatitud(),
				peticion.getLongitud()) + MsConstantes.KEY_URL + keyApi;
	}

}
