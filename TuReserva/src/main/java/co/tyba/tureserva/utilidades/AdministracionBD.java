package co.tyba.tureserva.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.excepciones.ExcepcionGenerica;
import co.tyba.tureserva.excepciones.ExcepcionTecnica;
import co.tyba.tureserva.modelo.ConexionBDDTO;
import co.tyba.tureserva.modelo.LoginDTO;
import co.tyba.tureserva.modelo.RegistroUsuarioPeticionDTO;

public class AdministracionBD {

	/** Instancia del log4j del contexto */
	private static final Logger loggerService = LogManager.getLogger();

	private Connection crearConexionBD() throws ExcepcionTecnica {

		ConexionBDDTO datosConexion = getDatosConexion();

		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(datosConexion.getUrl(), datosConexion.getUsuario(),
					datosConexion.getContrasena());

			System.out.println("Opened database successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return connection;

	}

	private ConexionBDDTO getDatosConexion() throws ExcepcionTecnica {

		String urlBD;
		String usuario;
		String contrasena;

		try {
			urlBD = CargadorPropiedad.getInstance().getPropiedad(MsConstantes.URL_BD);
			usuario = CargadorPropiedad.getInstance().getPropiedad(MsConstantes.USUARIO_BD);
			contrasena = CargadorPropiedad.getInstance().getPropiedad(MsConstantes.CONTRASENA_BD);

		} catch (RuntimeException | ExcepcionTecnica e) {
			throw new ExcepcionTecnica("Ocurrio un error consultando los parametros del archivo de propiedades", e);
		}

		return ConexionBDDTO.builder().url(urlBD).usuario(usuario).contrasena(contrasena).build();
	}

	public void insertarUsuario(String rutaPropiedades, RegistroUsuarioPeticionDTO peticion)
			throws ExcepcionTecnica, ExcepcionGenerica {

		PreparedStatement stmt = null;
		Connection connection = null;

		String errCod = "";
		String errMsg = "";

		try {
			connection = crearConexionBD();
			stmt = connection
					.prepareStatement(CargadorPropiedad.getInstance().getPropiedad(MsConstantes.FUNCION_INSERT_USER));

			stmt.setString(1, peticion.getNombreUsuario());
			stmt.setString(2, peticion.getClave());
			stmt.setString(3, peticion.getRol());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				errCod = rs.getString(MsConstantes.RESPONSE_CODE_BD);
				errMsg = rs.getString(MsConstantes.RESPONSE_MESSAGE_BD);
			}

			if (errCod.equals(MsConstantes.RESPONSE_CODE_OK)) {
				System.out.println("Finalizo correctamente el insert del usuario");
			} else {
				System.out.println("Finalizo la ejecucion del paquete de forma incorrecta, con errores:  " + errMsg);

				throw new ExcepcionGenerica("0020", "Error: se presento un error al insertar el usuario "
						+ " retorno los siguientes valores errCod:" + errCod + " errMsg: " + errMsg);

			}

		} catch (SQLException e) {
			throw new ExcepcionGenerica(e.getMessage() + MsConstantes.FUNCION_INSERT_USER, e);
		}

		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Se presento un error liberando la conexion a la base de datos");
			}
			cerrarConexion(connection);
		}

	}

	public void login(String rutaPropiedades, LoginDTO login) throws ExcepcionTecnica, ExcepcionGenerica {

		PreparedStatement stmt = null;
		Connection connection = null;

		String errCod = "";
		String errMsg = "";

		try {
			connection = crearConexionBD();
			stmt = connection
					.prepareStatement(CargadorPropiedad.getInstance().getPropiedad(MsConstantes.FUNCION_LOGIN));

			stmt.setString(1, login.getNombreUsuario());
			stmt.setString(2, login.getClave());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				errCod = rs.getString(MsConstantes.RESPONSE_CODE_BD);
				errMsg = rs.getString(MsConstantes.RESPONSE_MESSAGE_BD);
			}

			if (errCod.equals(MsConstantes.RESPONSE_CODE_OK)) {
				loggerService.debug("Finalizo correctamente la autenticacion del usuario:" + login.getNombreUsuario());
			} else {
				loggerService.error("Finalizo la ejecucion del paquete de forma incorrecta, con errores:  " + errMsg);

				throw new ExcepcionGenerica("0021",
						"Error: se presento un error al autenticar al usuario:" + login.getNombreUsuario()
								+ " retorno los siguientes valores errCod:" + errCod + " errMsg: " + errMsg);

			}

		} catch (SQLException e) {
			throw new ExcepcionGenerica(e.getMessage() + MsConstantes.FUNCION_LOGIN, e);
		}

		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				loggerService.error("Se presento un error liberando la conexion a la base de datos");
			}
			cerrarConexion(connection);
		}

	}

	private void cerrarConexion(Connection conn) {

		if (conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {

			}
	}
}
