package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.EmpleadoDTO;
import interfaces.EmpleadoDAO;
import utils.MySQLConexion8;

public class MySQLEmpleadoDAO implements EmpleadoDAO {


    public int registrar(EmpleadoDTO e) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "insert into empleado values(null,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getDni());
            pst.setString(2, e.getNombre());
            pst.setString(3, e.getApellido());
            pst.setString(4, e.getTelefono());
            pst.setString(5, e.getUsuario());
            pst.setString(6, e.getClave());
            pst.setInt(7, e.getIdTipo());
            pst.setInt(8, e.getEstado());
            pst.setString(9, e.getImagen());
            rs = pst.executeUpdate();
        } catch (Exception e2) {
            System.out.println("Error al registrar empleado: " + e2.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
                if (pst != null)
                    pst.close();
            } catch (SQLException e3) {
                System.out.println("Error al cerrar : " + e3.getMessage());
            }
        }
        return rs;
    }


    public ArrayList<EmpleadoDTO> listado() {
        ArrayList<EmpleadoDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{call usp_listadoEmpleado()}";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<EmpleadoDTO>();
            while (rs.next()) {
                EmpleadoDTO p = new EmpleadoDTO();
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setApellido(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setUsuario(rs.getString(6));
                p.setClave(rs.getString(7));
                p.setIdTipo(rs.getInt(8));
                p.setEstado(rs.getInt(9));
                p.setImagen(rs.getString(10));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados..." + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
                if (pst != null)
                    pst.close();
            } catch (SQLException e2) {
                System.out.println("Error al cerrar : " + e2.getMessage());
            }
        }
        return lista;
    }

}
