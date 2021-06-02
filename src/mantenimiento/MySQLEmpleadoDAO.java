package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.EmpleadoDTO;
import beans.TipoEmpleadoDTO;
import interfaces.EmpleadoDAO;
import utils.MySQLConexion8;

public class MySQLEmpleadoDAO implements EmpleadoDAO {

    public int registrar(EmpleadoDTO e) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL REGISTRAR_EMPLEADO(?,?,?,?,?,?)}";
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getDni());
            pst.setString(2, e.getNombre());
            pst.setString(3, e.getApellido());
            pst.setString(4, e.getTelefono());
            pst.setString(5, e.getDireccion());
            pst.setInt(6, e.getIdTipo());
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
                e.setCorreo(rs.getString(6));
                e.setDescripcionTipoEmpleado(rs.getString(7));
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error en el listado de empleados...:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

    @Override
    public int actualizarEmpleado(EmpleadoDTO e) {
        int rs = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MySQLConexion8.getConexion();
            String sql = "{CALL USP_ACTUALIZAREMPLEADO(?,?,?,?,?,?,?,?)}";
            pst = cn.prepareStatement(sql);
            pst.setString(1, e.getNombre());
            pst.setString(2, e.getApellido());
            pst.setString(3, e.getTelefono());
            pst.setString(4, e.getDireccion());
            pst.setString(5, e.getCorreo());
            pst.setString(6, e.getClave());
            pst.setString(7, e.getImagen());
            pst.setInt(8, e.getId());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al actualizar empleado...: " + ex.getMessage());
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
            String sql = "{call usp_eliminarEmpleado(?,?,?,?,?,?,?,?,?,?,?)}";
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
                em.setDescripcionTipoEmpleado(rs.getString(13));
            }

        } catch (Exception e) {
            System.out.println("Error validar acceso " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }

        return em;
    }

    @Override
    public ArrayList<TipoEmpleadoDTO> listadoTipoEmpleado() {
        ArrayList<TipoEmpleadoDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "SELECT * FROM TIPOEMPLEADO";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<TipoEmpleadoDTO>();
            while (rs.next()) {
                TipoEmpleadoDTO t = new TipoEmpleadoDTO();
                t.setId(rs.getInt(1));
                t.setDescripcion(rs.getString(2));
                lista.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tipo empleado:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

}
