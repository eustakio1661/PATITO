package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ProductoDTO;
import interfaces.ProductoDAO;
import utils.MySQLConexion8;

public class MySQLProductoDAO implements ProductoDAO {

    @Override
    public int registrar(ProductoDTO p) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_REGISTRARPRODUCTO()}";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getIdProducto());
            pst.setString(2, p.getDescripcion());
            pst.setDouble(3, p.getPrecio());
            pst.setInt(4, p.getCantidad());
            pst.setInt(5, p.getIdCategoria());
            pst.setString(6, p.getImagen());
            pst.setInt(7, p.getEstado());
            rs = pst.executeUpdate();
        } catch (Exception e2) {
            System.out.println("Error al registrar producto: " + e2.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
                if (pst != null)
                    pst.close();
            } catch (SQLException e3) {
                System.out.println("Error al cerrar     : " + e3.getMessage());
            }
        }
        return rs;
    }

    @Override
    public int eliminar(ProductoDTO p) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_ELIMINARPRODUCTO(?)}";
            pst = con.prepareStatement(sql);
            pst.setInt(1, p.getIdProducto());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error en eliminar producto... " + ex.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
                if (pst != null)
                    pst.close();
            } catch (SQLException e3) {
                System.out.println("Error al cerrar     : " + e3.getMessage());
            }
        }
        return rs;
    }

    @Override
    public int actualizar(ProductoDTO p) {
        int rs = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = MySQLConexion8.getConexion();
            String sql = "{CALL USP_ACTUALIZARPRODUCTO(?,?,?,?,?,?)}";
            pst = cn.prepareStatement(sql);
            pst.setString(1, p.getDescripcion());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getCantidad());
            pst.setInt(4, p.getIdCategoria());
            pst.setString(5, p.getImagen());
            pst.setInt(6, p.getEstado());
            pst.setInt(7, p.getIdProducto());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al actualizar productos... " + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }
        return rs;
    }

    @Override
    public ArrayList<ProductoDTO> listadoCategoria() {
        ArrayList<ProductoDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_LISTADOPRODUCTOS()}";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<ProductoDTO>();
            while (rs.next()) {
                ProductoDTO e = new ProductoDTO();
                e.setIdProducto(rs.getInt(1));
                e.setDescripcion(rs.getString(2));
                e.setPrecio(rs.getDouble(3));
                e.setCantidad(rs.getInt(4));
                e.setIdCategoria(rs.getInt(5));
                e.setImagen(rs.getString(6));
                e.setEstado(rs.getInt(7));
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos..." + e.getMessage());
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
