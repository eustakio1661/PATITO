package interfaces;

import java.util.ArrayList;

import beans.CategoriaDTO;
import beans.ProductoDTO;

public interface ProductoDAO {

    public int registrar(ProductoDTO p);

    public int eliminar(ProductoDTO p);

    public int actualizar(ProductoDTO p);

    public ArrayList<ProductoDTO> listado();

    public ArrayList<CategoriaDTO> listadoCategoria();

    public ArrayList<ProductoDTO> listadoProductoEstado(int estado);
    
    public ProductoDTO buscar(int codigo);
    
    public int actualizarEstado(ProductoDTO p);
}
