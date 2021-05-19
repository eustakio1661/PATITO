package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String sql = "insert into empleado values(null,?,?,?,?,?,?,?,?,?,default)";
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getDni());
            pst.setString(2, e.getNombre());
            pst.setString(3, e.getApellido());
            pst.setString(4, e.getTelefono());
            pst.setString(5, e.getDireccion());
            pst.setString(6, e.getCorreo());
            pst.setString(7, e.getUsuario());
            pst.setString(8, e.getClave());
            pst.setInt(9, e.getIdTipo());
            pst.setInt(10, e.getEstado());
            pst.setString(11, e.getImagen());
            rs = pst.executeUpdate();
        } catch (Exception e2) {
            System.out.println("Error al registrar empleado: " + e2.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
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
                EmpleadoDTO e = new EmpleadoDTO();
                e.setId(rs.getInt(1));
                e.setDni(rs.getString(2));
                e.setNombre(rs.getString(3));
                e.setApellido(rs.getString(4));
                e.setTelefono(rs.getString(5));
                e.setDireccion(rs.getString(6));
                e.setUsuario(rs.getString(7));
                e.setClave(rs.getString(8));
                e.setIdTipo(rs.getInt(9));
                e.setEstado(rs.getInt(10));
                e.setImagen(rs.getString(11));
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

    @Override
    public int actualizar(EmpleadoDTO e) {
        int rs = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MySQLConexion8.getConexion();
            String sql = "{call usp_actualizarEmpleado(?,?,?,?,?,?,?,?,?,?,?)}";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, e.getId());
            pst.setString(2, e.getDni());
            pst.setString(3, e.getNombre());
            pst.setString(4, e.getApellido());
            pst.setString(5, e.getTelefono());
            pst.setString(6, e.getDireccion());
            pst.setString(7, e.getCorreo());
            pst.setString(8, e.getUsuario());
            pst.setString(9, e.getClave());
            pst.setInt(10, e.getIdTipo());
            pst.setInt(11, e.getEstado());
            pst.setString(12, e.getImagen());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al actualizar empleado: " + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }
        return rs;
    }

    @Override
    public int eliminar(EmpleadoDTO e) {
        int rs = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MySQLConexion8.getConexion();
            String sql = "{call usp_eliminarEmpleado(?)}";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, e.getId());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error en eliminar empleado: " + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }
        return rs;
    }

    @Override
    public EmpleadoDTO validarAcceso(String correo_usuario, String clave) {
        EmpleadoDTO em = null;

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = MySQLConexion8.getConexion();
            String sql = "{call usp_validarAcceso(?,?)}";
            pst = cn.prepareStatement(sql);
            pst.setString(1, correo_usuario);
            pst.setString(2, clave);

            rs = pst.executeQuery();

            if (rs.next()) {
                em = new EmpleadoDTO();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNombre(rs.getString(3));
                em.setApellido(rs.getString(4));
                em.setTelefono(rs.getString(5));
                em.setDireccion(rs.getString(6));
                em.setCorreo(rs.getString(7));
                em.setUsuario(rs.getString(8));
                em.setClave(rs.getString(9));
                em.setIdTipo(rs.getInt(10));
                em.setEstado(rs.getInt(11));
                em.setImagen(rs.getString(12));
            }

        } catch (Exception e) {
            System.out.println("Error validar acceso " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }

        return em;
    }

}
