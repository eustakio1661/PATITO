package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ListadoEntreFechasDTO;
import interfaces.ReporteDAO;
import utils.MySQLConexion8;

public class MySQLReporteDAO implements ReporteDAO{

    @Override
    public ArrayList<ListadoEntreFechasDTO> reporte(String fecha1, String fecha2) {
        
        ArrayList<ListadoEntreFechasDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_LISTADOENTREFECHAS(?,?)}";
            pst = con.prepareStatement(sql);
            pst.setString(1, fecha1);
            pst.setString(2, fecha2);
            rs = pst.executeQuery();
            lista = new ArrayList<ListadoEntreFechasDTO>();
            while(rs.next()){
                ListadoEntreFechasDTO l = new ListadoEntreFechasDTO();
                l.setIdBoleta(rs.getString(1));
                l.setDescripcion(rs.getString(2));
                l.setFecha(rs.getString(3));
                l.setPrecio(rs.getDouble(4));
                l.setCantidad(rs.getInt(5));
                l.setDescuento(rs.getDouble(6));
                l.setImporteTotal(rs.getDouble(7));
                lista.add(l);
            }            
        }catch (Exception e) {
            System.out.println("Error en la lista entre fechas... : " + e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar : " + e.getMessage());
            }
        }
        return lista;
    }

    public ArrayList<ListadoEntreFechasDTO> lista() {
        ArrayList<ListadoEntreFechasDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{CALL USP_LISTADODEVENTAS()}";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<ListadoEntreFechasDTO>();
            while (rs.next()) {
                ListadoEntreFechasDTO l = new ListadoEntreFechasDTO();
                l.setIdBoleta(rs.getString(1));
                l.setDescripcion(rs.getString(2));
                l.setFecha(rs.getString(3));
                l.setPrecio(rs.getDouble(4));
                l.setCantidad(rs.getInt(5));
                l.setDescuento(rs.getDouble(6));
                l.setImporteTotal(rs.getDouble(7));                  
                lista.add(l);
            }
        } catch (Exception e) {
            System.out.println("Error en el listado de ventas...:" + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return lista;
    }

}
