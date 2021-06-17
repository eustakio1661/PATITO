package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import beans.BoletaDTO;
import beans.DetallePedidoDTO;
import beans.PedidoDTO;
import interfaces.VentaDAO;
import utils.MySQLConexion8;

public class MySQLVentaDAO implements VentaDAO {

    @Override
    public int generarNumPedido() {
        int num = 1;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = MySQLConexion8.getConexion();
            String sql = "SELECT count(ID_PE) + 1 AS CODIGO FROM PEDIDO";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en numero Pedido: " + e.getMessage());
        } finally {
            try {
                MySQLConexion8.closeConexion(con);
            } catch (Exception e2) {
                System.out.println("Error al cerrar : " + e2.getMessage());
            }
        }

        return num;
    }
    @Override
    public String generaNumBoleta() {
        String codigo = "B001-000001";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "select substring(ID_BOL,6)" + "from BOLETA order by ID_BOL desc limit 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                codigo = "B001-" + new DecimalFormat("000000").format(Integer.parseInt(rs.getString(1)) + 1);
            }
        } catch (Exception e) {
            System.out.println("Error en generar número de boleta : " + e.getMessage());
        } finally {
            MySQLConexion8.closeConexion(con);
        }
        return codigo;
    }

    @Override
    public int realizarVenta(PedidoDTO p, ArrayList<DetallePedidoDTO> det, BoletaDTO b) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        PreparedStatement pst4 = null;
        try {
            con = MySQLConexion8.getConexion();
            con.setAutoCommit(false);

            String sql1 = "{call USP_REGISTRARPEDIDO(?,?,?,?)}";
            pst1 = con.prepareStatement(sql1);
            pst1.setInt(1, p.getId_pe());
            pst1.setInt(2, p.getId_em());
            pst1.setInt(3, p.getId_cli());
            pst1.setInt(4, p.getCantidadTotal());
            rs = pst1.executeUpdate();

            String sql2 = "{call USP_REGISTRARDETALLEPEDIDO(?,?,?,?,?)}";
            String sql3 = "{call USP_RESTARPRODUCTO(?,?)}";
            for (DetallePedidoDTO d : det) {
                pst2 = con.prepareStatement(sql2);
                pst2.setInt(1, p.getId_pe());
                pst2.setInt(2, d.getId_pro());
                pst2.setDouble(3, d.getPrecio());
                pst2.setInt(4, d.getCantidad());
                pst2.setDouble(5, d.getImporte());
                rs += pst2.executeUpdate();
                
                pst3 = con.prepareStatement(sql3);          
                pst3.setInt(1, d.getCantidad());
                pst3.setInt(2, d.getId_pro());
                rs += pst3.executeUpdate();
            }
            String sql4 = "{call USP_REGISTRARBOLETA(?,?,?,?)}";
            pst4 = con.prepareStatement(sql4);
            pst4.setInt(1, p.getId_pe());
            pst4.setString(2, b.getId_bol());
            pst4.setDouble(3, b.getPrecioTotal());
            pst4.setDouble(4, b.getDescuento());
            rs = pst4.executeUpdate();

            con.commit();

        } catch (Exception e) {
            System.out.println("Error en Realizar Venta: " + e.getMessage());
            rs = 0;
            try {

            } catch (Exception e2) {
                System.out.println("Error al restaurar: " + e.getMessage());
            }
        } finally {

        }
        return rs;
    }

   

}
