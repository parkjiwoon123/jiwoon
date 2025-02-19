package moa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class gogoDAO {
    Connection conn;

    public gogoDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public int insertProductAndPriceHistory(gogoVO vo, int userId, double price) {
        int result = 0;
        int productId = 0;

        // 시퀀스로 product_id 값을 가져옵니다.
        String sqlSeq = "SELECT products_seq.NEXTVAL FROM DUAL";
        try (PreparedStatement psSeq = conn.prepareStatement(sqlSeq);
             ResultSet rs = psSeq.executeQuery()) {
            if (rs.next()) {
                productId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (productId == 0) {
            return result;
        }

        // 상품 정보를 products 테이블에 저장합니다.
        String sqlProduct = "INSERT INTO products (product_id, user_id, link, site, product_name) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement psProduct = conn.prepareStatement(sqlProduct)) {
            psProduct.setInt(1, productId);
            psProduct.setInt(2, userId);
            psProduct.setString(3, vo.getUrl());
            psProduct.setString(4, vo.getSite());
            psProduct.setString(5, vo.getName());
            psProduct.executeUpdate();

            // 가격 정보를 price_history 테이블에 저장합니다.
            String sqlPriceHistory = "INSERT INTO price_history (product_id, price, recorded_at) VALUES (?, ?, ?)";
            try (PreparedStatement psPriceHistory = conn.prepareStatement(sqlPriceHistory)) {
                psPriceHistory.setInt(1, productId);
                psPriceHistory.setDouble(2, price);
                psPriceHistory.setTimestamp(3, new Timestamp(new Date().getTime()));
                result = psPriceHistory.executeUpdate();

                // Logging to check results and values
                System.out.println("Insert Result: " + result);
                System.out.println("Price: " + price);
                System.out.println("Product ID: " + productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    public int updateProduct(gogoVO vo, int productId) {
        try {
            String sql = "UPDATE products SET link=?, site=?, product_name=? WHERE product_id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, vo.getUrl());
                ps.setString(2, vo.getSite());
                ps.setString(3, vo.getName());
                ps.setInt(4, productId);

                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteProduct(int productId) {
        try {
            String sql = "DELETE FROM products WHERE product_id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, productId);

                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int registerUser(UserVO user) {
        try {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword()); // Ensure to apply hashing to the password

                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean loginCheck(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password); // Ensure to compare with hashed password

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getUserIdByUsername(String username) {
        try {
            String sql = "SELECT user_id FROM users WHERE username=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return rs.getInt("user_id");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;  // Return 0 or throw an exception if user not found
    }

    public List<Product> getProductsByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        SomeDatabaseService da = new SomeDatabaseService();
        System.out.println(userId);
        try {
            String sql = "SELECT * FROM products WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            	System.out.println("key:"+userId);
            	
                ps.setInt(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setProductId(rs.getInt("product_id"));
                        product.setUserId(rs.getInt("user_id"));
                        product.setLink(rs.getString("link"));
                        product.setSite(rs.getString("site"));
                        product.setProductName(rs.getString("product_name"));
                        products.add(product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public PriceData getPriceDataForItem(int itemId) {
        PriceData priceData = new PriceData();
        List<String> dates = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        String sql = "SELECT price, TO_CHAR(recorded_at, 'YYYY-MM-DD') AS recorded_date FROM price_history WHERE product_id = ? ORDER BY recorded_at";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prices.add(rs.getDouble("price"));
                    dates.add(rs.getString("recorded_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        priceData.setDates(dates);
        priceData.setPrices(prices);
        return priceData;
    }

}


