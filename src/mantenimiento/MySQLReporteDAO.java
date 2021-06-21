package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.ListadoEntreFechasDTO;
import beans.PedidoDTO;
import beans.ReporteClienteDTO;
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

    @Override
    public ArrayList<ReporteClienteDTO> reporteCliente() {
        ArrayList<ReporteClienteDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{call USP_CATEGORIZACIONCLIENTES()}";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<ReporteClienteDTO>();
            while (rs.next()) {
                ReporteClienteDTO r = new ReporteClienteDTO();
                r.setId_cli(rs.getInt(1));
                r.setNombreCompleto(rs.getString(2));
                r.setCantidadCompras(rs.getInt(3));
                r.setSegmentacion(rs.getString(4));
                lista.add(r);
            }
        } catch (Exception e) {
            System.out.println("Error en listar segmentacion de Cliente: " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }

        return lista;
    }

    @Override
    public ArrayList<PedidoDTO> listarPedidosPendientes() {
        ArrayList<PedidoDTO> lista = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "{call USP_listarPedidosPendientes()}";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            lista = new ArrayList<PedidoDTO>();
            while (rs.next()) {
                PedidoDTO p = new PedidoDTO();
                p.setId_pe(rs.getInt(1));
                p.setNombreEmpleado(rs.getString(2));
                p.setNombreCliente(rs.getString(3));
                p.setFechaPedido(rs.getString(4));
                p.setCantidadTotal(rs.getInt(5));
                p.setEstado(rs.getInt(6));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error en listar pedidos pendientes: " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }

        return lista;
    }

}
