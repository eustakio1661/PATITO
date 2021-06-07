package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CategoriaDTO;
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
            String sql = "{CALL USP_REGISTRARPRODUCTO(?,?,?,?,?)}";
            pst = con.prepareStatement(sql);
            pst.setString(1, p.getDescripcion());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getCantidad());
            pst.setInt(4, p.getIdCategoria());
            pst.setString(5, p.getImagen());
            rs = pst.executeUpdate();
        } catch (Exception e2) {
            System.out.println("Error al registrar producto: " + e2.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
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
            System.out.println("Error en eliminar producto: " + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
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
            pst.setInt(1, p.getIdProducto());
            pst.setString(2, p.getDescripcion());
            pst.setDouble(3, p.getPrecio());
            pst.setInt(4, p.getCantidad());
            pst.setInt(5, p.getIdCategoria());
            pst.setString(6, p.getImagen());
            rs = pst.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error al actualizar productos:" + ex.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }
        return rs;
    }

    @Override
    public ArrayList<ProductoDTO> listado() {
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
                ProductoDTO p = new ProductoDTO();
                p.setIdProducto(rs.getInt(1));
                p.setDescripcion(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCantidad(rs.getInt(4));
                p.setIdCategoria(rs.getInt(5));
                p.setDescCategoria(rs.getString(6));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

    @Override
    public ArrayList<CategoriaDTO> listadoCategoria() {
        ArrayList<CategoriaDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "SELECT * FROM CATEGORIA";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<CategoriaDTO>();
            while (rs.next()) {
                CategoriaDTO c = new CategoriaDTO();
                c.setIdcategoria(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar categoría:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

    @Override
    public ProductoDTO buscar(int codigo) {
        ProductoDTO p = null;

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = MySQLConexion8.getConexion();

            String sql = "select * from PRODUCTO where ID_PRO = ?";

            pst = cn.prepareStatement(sql);

            pst.setInt(1, codigo);

            rs = pst.executeQuery();

            if (rs.next()) {
                p = new ProductoDTO();
                p.setIdProducto(rs.getInt(1));
                p.setDescripcion(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCantidad(rs.getInt(4));
                p.setIdCategoria(rs.getInt(5));
                p.setImagen(rs.getString(6));
                p.setEstado(rs.getInt(7));

            }

        } catch (Exception e) {
            System.out.println("Error al buscar producto " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(cn);
        }

        return p;
    }

}
