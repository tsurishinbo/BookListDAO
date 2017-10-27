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
public class IndexBean implements Serializable {
    
    private List<BookDTO> bookList;

    @PostConstruct
    public void init() {
        Connection con = DBManager.getConnection();
        BookDAO bookDao = new BookDAO(con);
        try {
            bookList = bookDao.findAll();
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

    public String add() {
        return "regist.xhtml?faces-redirect=true";
    }
    
    public String update(int id) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("id", id);
        return "edit.xhtml?faces-redirect=true";
    }
    
    public String getPointMark(int point) {
        String pointMark = null;
        switch (point) {
            case 1:
                pointMark = "☆";
                break;
            case 2:
                pointMark = "☆☆";
                break;
            case 3:
                pointMark = "☆☆☆";
                break;
            case 4:
                pointMark = "☆☆☆☆";
                break;
            case 5:
                pointMark = "☆☆☆☆☆";
                break;
        }
        return pointMark;
    }
    
    public List<BookDTO> getBookList() {
        return bookList;
    }
}
