package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class PhonebookOracleDAO  {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "1234";
    Connection conn;

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Oracle JDBC driver", e);
        }
    }

    private Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return conn;
    }

    public List<PhonebookVO> findAll() {
        List<PhonebookVO> list = new ArrayList<>();
        try {
            String sql = "select * from phonebook";
            Connection conn = getConnection(); // Get a valid connection first
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                list.add(new PhonebookVO(
                        rs.getInt("ph_num"),
                        rs.getString("ph_name"),
                        rs.getString("ph_hp"),
                        rs.getString("ph_memo")
                ));  
            }
            rs.close();
            pstat.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch all records", e);
        }
        return list;
    }
    

	 public int insert(PhonebookVO phonebook) {
	        String sql = "insert into phonebook VALUES(phone_idx_seq.nextval,?,?,?)";

	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	          
	            ps.setString(1, phonebook.getName());
	            ps.setString(2, phonebook.getHp());
	            ps.setString(3, phonebook.getMemo());
	            
	            System.err.println(ps.toString());
	            return ps.executeUpdate();
	            
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to insert record", e);
	        }
	    }

	    public int update(PhonebookVO phonebook) {
	        String sql = "update phonebook set ph_name = ?, ph_hp=?, ph_memo=? where ph_num = ?";

	        try (
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, phonebook.getName());
	            ps.setString(2, phonebook.getHp());
	            ps.setString(3, phonebook.getMemo());
	            ps.setInt(4, phonebook.getIdx());
	            
	            return ps.executeUpdate();
	            
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to update record", e);
	        }
	        
	    }

	    public int del(int idx) {
	        String sql = "delete from phonebook where ph_num=?";

	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, idx);
	            int rowsDeleted = ps.executeUpdate();

	            // Close the PreparedStatement here if it's no longer needed
	            ps.close();
	            
	            return rowsDeleted;
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to fetch page list", e);
	        }
	    }


	    public List<PhonebookVO> pageList(int currentPage, int countPerPage) {
	        int startRow = (currentPage - 1) * countPerPage + 1;
	        int endRow = currentPage * countPerPage;

	        String sql = "select * from phonebook " +
	                 "where ph_num >= ? and ph_num <= ?";

	        try (Connection conn = getConnection();
	             PreparedStatement pstat = conn.prepareStatement(sql)) {

	            pstat.setInt(1, startRow);
	            pstat.setInt(2, endRow);

	            try (ResultSet rs = pstat.executeQuery()) {
	                List<PhonebookVO> list = new ArrayList<>();
	                
	                while (rs.next()) {
	                	 System.out.println(rs.toString());
	                    list.add(new PhonebookVO(
	                            rs.getInt("ph_num"),
	                            rs.getString("ph_name"),
	                            rs.getString("ph_hp"),
	                            rs.getString("ph_memo")
	                    ));
	                }
	                pstat.close();
	                rs.close();
	                return list;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to fetch page list", e);
	        }
	    }

	    public int totalCount() {
	        String sql = "select count(*) from phonebook";

	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                return rs.getInt(1);
	            }

	            return 0;
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to count records", e);
	        }
	    }

	    public PhonebookVO findOne(int idx) {
	        String sql = "select * from phonebook where ph_num=?";

	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, idx);

	            ResultSet rs = ps.executeQuery();
	            	System.out.println(rs.toString());
	                if (rs.next()) {
	                	PhonebookVO vo = new PhonebookVO(
	                            rs.getInt("ph_num"),
	                            rs.getString("ph_name"),
	                            rs.getString("ph_hp"),
	                            rs.getString("ph_memo")
	                    );
	                	System.out.println(vo.toString());
	                	return vo;
	                	
	                   
	                }
	        }catch (Exception e) {
				// TODO: handle exception
			}
	                return null;
	            }
	        
	    
	    public List<PhonebookVO> search(String field, String query, int currentPage, int countPerPage) {
	        int startRow = (currentPage - 1) * countPerPage + 1;
	        int endRow = currentPage * countPerPage;

	        String sql = "select * from " +
	            "(select rownum as num, ph_name, ph_hp, ph_memo from phonebook where lower(" + field + ") like lower(?)) " +
	            "where num >= ? and num <= ?";

	        try (Connection conn = getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, "%" + query + "%");
	            ps.setInt(2, startRow);
	            ps.setInt(3, endRow);

	            try (ResultSet rs = ps.executeQuery()) {
	                List<PhonebookVO> list = new ArrayList<>();

	                while (rs.next()) {
	                    list.add(new PhonebookVO(
	                            rs.getInt("num"),
	                            rs.getString("ph_name"),
	                            rs.getString("ph_hp"),
	                            rs.getString("ph_memo")
	                    ));
	                }
	                ps.close();
	                
	                return list;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Failed to search records", e);
	        }
	    }

	}