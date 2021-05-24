package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import beans.ClienteDTO;
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
            String sql = "{call usp_listarclientes()}";
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
                
                listac.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error en listado: " + e.getMessage());
        }finally {
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
	        String sql = "insert into cliente values(?,?,?,?,?,?,?)";
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, cli.getCodigo());
	        pst.setInt(2, cli.getCodigoDistrito());
	        pst.setString(3, cli.getDni());
	        pst.setString(4, cli.getNombre());
	        pst.setString(5, cli.getApellido());
	        pst.setString(6, cli.getDireccion());
	        pst.setString(7, cli.getTelefono());
	        
	        rs = pst.executeUpdate();
	    } catch (Exception e2) {
	        System.out.println("Error en registrar: " + e2.getMessage());
	        msjBD = e2.getMessage();
	    }finally {
	        MySQLConexion8.closeConexion(con);
	    }
	        return rs;
	}

	
	public int actualizarCliente(ClienteDTO cli) {
		
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConexion8.getConexion();
			String sql = "update cliente set ID_DIST=?, DNI_CLI=?, NOM_CLI=?, APE_CLI=?, DIR_CLI=?, TELEF_CLI=? where ID_CLI=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cli.getCodigo());
			pstm.setInt(2, cli.getCodigoDistrito());
			pstm.setString(3, cli.getDni());
			pstm.setString(4, cli.getNombre());
			pstm.setString(5, cli.getApellido());
			pstm.setString(6, cli.getDireccion());
			pstm.setString(7, cli.getTelefono());
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
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
	

}
