package dao;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int SQL = 2;
    public static final int ORACLE = 3;
    
    
    public static DAOFactory getDAOFactory(int qBD) {
        switch (qBD) {
        case MYSQL:
            return null;/*new MySQLDAOFactory();*/            
        default:
            return null;
        }
    }
    
}
