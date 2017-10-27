package dao;

import dto.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class BookDAO {

    private Connection con;
    
    public BookDAO(Connection con) {
        this.con = con;
    }
    
    public List<BookDTO> findAll() throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("b.id, ");
        sql.append("b.category_id, ");
        sql.append("c.name category_name, ");
        sql.append("b.title, ");
        sql.append("b.memo, ");
        sql.append("b.point ");
        sql.append("from t_book b ");
        sql.append("inner join m_category c ");
        sql.append("on c.id = b.category_id ");
        sql.append("order by b.id ");

        PreparedStatement stmt = con.prepareStatement(sql.toString());
        ResultSet rs = stmt.executeQuery();
        ArrayList list = new ArrayList();
        while (rs.next()) {
            BookDTO book = new BookDTO();
            book.setId(rs.getInt("id"));
            book.setCategoryId(rs.getInt("category_id"));
            book.setCategoryName(rs.getString("category_name"));
            book.setTitle(rs.getString("title"));
            book.setMemo(rs.getString("memo"));
            book.setPoint(rs.getInt("point"));
            list.add(book);
        }
        return list;
    }
    
    public BookDTO findById(int id) throws SQLException {
        
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("b.id, ");
        sql.append("b.category_id, ");
        sql.append("c.name category_name, ");
        sql.append("b.title, ");
        sql.append("b.memo, ");
        sql.append("b.point ");
        sql.append("from t_book b ");
        sql.append("inner join m_category c ");
        sql.append("on c.id = b.category_id ");
        sql.append("where b.id =  ? ");

        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        BookDTO book = new BookDTO();
        while (rs.next()) {
            book.setId(rs.getInt("id"));
            book.setCategoryId(rs.getInt("category_id"));
            book.setCategoryName(rs.getString("category_name"));
            book.setTitle(rs.getString("title"));
            book.setMemo(rs.getString("memo"));
            book.setPoint(rs.getInt("point"));
        }
        return book;
    }
    
    public void insert(BookDTO book) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_book ( ");
        sql.append("category_id, ");
        sql.append("title, ");
        sql.append("memo, ");
        sql.append("point ");
        sql.append(") ");
        sql.append("values ( ");
        sql.append("?, ");
        sql.append("?, ");
        sql.append("?, ");
        sql.append("? ");
        sql.append(") ");

        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setInt(1, book.getCategoryId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getMemo());
        stmt.setInt(4, book.getPoint());
        stmt.execute();
    }
    
    public void update(BookDTO book) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update t_book set ");
        sql.append("category_id = ?, ");
        sql.append("title = ?, ");
        sql.append("memo = ?, ");
        sql.append("point = ? ");
        sql.append("where id = ? ");

        PreparedStatement stmt = con.prepareStatement(sql.toString());
        stmt.setInt(1, book.getCategoryId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getMemo());
        stmt.setInt(4, book.getPoint());
        stmt.setInt(5, book.getId());
        stmt.execute();
    }
}
