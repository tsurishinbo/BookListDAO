package bean;

import dao.*;
import dto.*;
import java.io.Serializable;
import java.sql.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.*;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EditBean implements Serializable {
    
    private BookDTO book;
    private List<CategoryDTO> categoryList;

    @PostConstruct
    public void init() {
        Connection con = DBManager.getConnection();
        CategoryDAO catgoryDAO = new CategoryDAO(con);
        BookDAO bookDAO = new BookDAO(con);
        try {
            categoryList = catgoryDAO.findAll();
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            if (flash.size() > 0) {
                book = bookDAO.findById((Integer)flash.get("id"));
            }
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
            bookDAO.update(book);
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

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
    
    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }
}
