package bean;

import dao.*;
import dto.*;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RegistBean implements Serializable {
 
    private Integer categoryId;
    private String title;
    private String memo;
    private Integer point;
    private List<CategoryDTO> categoryList;

    @PostConstruct
    public void init() {
        Connection con = DBManager.getConnection();
        CategoryDAO catgoryDAO = new CategoryDAO(con);
        try {
            categoryList = catgoryDAO.findAll();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        }
    }
    
    public String regist() {
        Connection con = DBManager.getConnection();
        BookDAO bookDAO = new BookDAO(con);
        try {
            BookDTO book = new BookDTO();
            book.setCategoryId(categoryId);
            book.setTitle(title);
            book.setMemo(memo);
            book.setPoint(point);
            bookDAO.insert(book);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new SQLRuntimeException(e);
            }
        }
        return "index.xhtml?faces-redirect=true";
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }
}
