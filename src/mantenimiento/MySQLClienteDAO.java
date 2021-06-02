package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import beans.ClienteDTO;
import beans.ProductoDTO;
import interfaces.ClienteDAO;
import utils.MySQLConexion8;

public class MySQLClienteDAO implements ClienteDAO {

	public ArrayList<ClienteDTO> listarCliente() {

		ArrayList<ClienteDTO> listac = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion8.getConexion();
			String sql = "select  * from CLIENTE";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			listac = new ArrayList<ClienteDTO>();
			while (rs.next()) {
				ClienteDTO c = new ClienteDTO();
				c.setCodigo(rs.getInt(1));
				c.setCodigoDistrito(rs.getInt(2));
				c.setDni(rs.getString(3));
				c.setNombre(rs.getString(4));
				c.setApellido(rs.getString(5));
				c.setDireccion(rs.getString(6));
				c.setTelefono(rs.getString(7));
				c.setEstado(rs.getInt(8));
				listac.add(c);
			}
		} catch (Exception e) {
			System.out.println("Error en listado: " + e.getMessage());
		} finally {
			MySQLConexion8.closeConexion(con);
		}

		return listac;

	}

	public static String msjBD;

	public int registrarCliente(ClienteDTO cli) {

		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLConexion8.getConexion();
			String sql = "{USP_REGISTRARCLIENTE(?,?,?,?,?,?)}";
			pst = con.prepareStatement(sql);
			pst.setInt(1, cli.getCodigoDistrito());
			pst.setString(2, cli.getDni());
			pst.setString(3, cli.getNombre());
			pst.setString(4, cli.getApellido());
			pst.setString(5, cli.getDireccion());
			pst.setString(6, cli.getTelefono());
			
			rs = pst.executeUpdate();
		} catch (Exception e2) {
			System.out.println("Error en registrar: " + e2.getMessage());
			msjBD = e2.getMessage();
		} finally {
			MySQLConexion8.closeConexion(con);
		}
		return rs;
	}

	 public int actualizarCliente(ClienteDTO cli) {
	        int rs = 0;
	        Connection cn = null;
	        PreparedStatement pst = null;
	        try {
	            cn = MySQLConexion8.getConexion();
	            String sql = "{CALL USP_ACTUALIZARCLIENTE(?,?,?,?,?,?,?)}";
	            pst = cn.prepareStatement(sql);
	            pst.setInt(1, cli.getCodigo());
	            pst.setString(2, cli.getNombre());
	            pst.setString(3, cli.getApellido());
	            pst.setString(4, cli.getTelefono());
	            pst.setString(5, cli.getDni());
	            pst.setInt(6, cli.getCodigoDistrito());
	            pst.setString(7, cli.getDireccion());
	            
	            rs = pst.executeUpdate();

	        } catch (Exception ex) {
	            System.out.println("Error al actualizar clientes:" + ex.getMessage());
	        } finally {
	            MySQLConexion8.closeConexion(cn);
	        }
	        return rs;
	    }
	    

	
	
	public int eliminarCliente(ClienteDTO c) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_ELIMINARCLIENTE(?)}";
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getCodigo());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error en eliminar cliente: " + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return rs;
    }

	public ClienteDTO buscarCliente(int cod) {

		ClienteDTO c = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConexion8.getConexion();
			String sql = "select * from CLIENTE where ID_CLI=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				c = new ClienteDTO();
				c.setCodigo(rs.getInt(1));
				c.setCodigoDistrito(rs.getInt(2));
				c.setDni(rs.getString(3));
				c.setNombre(rs.getString(4));
				c.setApellido(rs.getString(5));
				c.setDireccion(rs.getString(6));
				c.setTelefono(rs.getString(7));
				c.setEstado(rs.getInt(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return c;
	}

	@Override
	public ArrayList<ClienteDTO> listarClientexDistrito() {
		ArrayList<ClienteDTO> listacd = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion8.getConexion();
			String sql = "{USP_ClientexDistrito()}";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			listacd = new ArrayList<ClienteDTO>();
			while (rs.next()) {
				ClienteDTO cd = new ClienteDTO();
				cd.setCodigo(rs.getInt(1));
				cd.setDni(rs.getString(2));
				cd.setNombre(rs.getString(3));
				cd.setApellido(rs.getString(4));
				cd.setDireccion(rs.getString(5));
				cd.setTelefono(rs.getString(6));
				cd.setCodigoDistrito(rs.getInt(7));
				cd.setNombre(rs.getString(8));
				listacd.add(cd);
			}
		} catch (Exception e) {
			System.out.println("Error en listado: " + e.getMessage());
		} finally {
			MySQLConexion8.closeConexion(con);
		}

		return listacd;
	}

}
