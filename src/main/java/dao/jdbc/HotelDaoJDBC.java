package dao.jdbc;

import dao.implementation.HotelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class HotelDaoJDBC implements HotelDao {
    private static final Logger logger = LoggerFactory.getLogger(HotelDaoJDBC.class);
    private final DataSource dataSource;
    private static HotelDao instance;

    private HotelDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static HotelDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new HotelDaoJDBC(dataSource);
        }
        return instance;
    }

    public static HotelDao getInstance() {
        return instance;
    }

//    @Override
//    public void add(Hotel hotel) {
//        try (Connection conn = dataSource.getConnection()) {
//            String sql = "INSERT INTO ";
//
//        } catch (SQLException e) {
//            logger.error("Error while adding product: '{}'", product.toString());
//        }
//    }
