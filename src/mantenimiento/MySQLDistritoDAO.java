package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import beans.DistritoDTO;
import interfaces.DistritoDAO;
import utils.MySQLConexion8;

public class MySQLDistritoDAO implements DistritoDAO {

	@Override
	public ArrayList<DistritoDTO> listarDistrito() {
		ArrayList<DistritoDTO> distrito = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion8.getConexion();
			String sql = "SELECT * FROM DISTRITO";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			distrito = new ArrayList<DistritoDTO>();
			while (rs.next()) {
				DistritoDTO d = new DistritoDTO();
				d.setId(rs.getInt(1));
				d.setDescripcion(rs.getString(2));

				distrito.add(d);
			}
		} catch (Exception e) {
			System.out.println("Error al listar distritos:" + e.getMessage());
		} finally {
			MySQLConexion8.closeConexion(con);
		}
		return distrito;
	}
}
