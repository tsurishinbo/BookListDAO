package dao;

import dto.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class CategoryDAO {

    private Connection con;

    public CategoryDAO(Connection con) {
        this.con = con;
    }

    public List<CategoryDTO> findAll() throws SQLException {
        
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("id, ");
        sql.append("name ");
        sql.append("from m_category ");
        sql.append("order by id ");

        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        ArrayList list = new ArrayList();
        while (rs.next()) {
            CategoryDTO category = new CategoryDTO();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            list.add(category);
        }
        return list;
    }
}
