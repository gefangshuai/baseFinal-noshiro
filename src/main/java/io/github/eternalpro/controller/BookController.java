package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Page;
import io.github.eternalpro.model.Book;
import io.github.gefangshuai.wfinal.flash.core.FlashMessageUtils;
import io.github.gefangshuai.wfinal.menumapper.annotation.Menu;
import io.github.gefangshuai.wfinal.model.search.*;
import io.github.gefangshuai.wfinal.model.utils.QueryUtils;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/7.
 */
@Menu(mapper = "book")
@LoginRequired
@ControllerBind(controllerKey = "/book", viewPath = "book")
public class BookController extends Controller {
    public void index() {
//        List<Book> books = Book.dao.findAll(new Sort(Book.dao.getPkName(), Direction.DESC));
        String key = getPara("key", "");
        QueryParam queryParam = new QueryParam("name", Operator.LK, QueryUtils.getLikeValue(key));
        QueryMap queryMap = new QueryMap(queryParam).or("author", Operator.EQ, key);
//        List<Book> books = Book.dao.findAll(queryMap);
        List<Book> books = Book.dao.findAll(queryMap, new Sort(Book.dao.getPkNames()[0], Direction.DESC));
        setAttr("books", books);
    }

    /**
     * 测试分页
     */
    @Menu(mapper = "bookpage")
    public void page() {
        String key = getPara("key", "");
        QueryParam queryParam = new QueryParam("name", Operator.LK, QueryUtils.getLikeValue(key));
        QueryMap queryMap = new QueryMap(queryParam).or("author", Operator.LK, QueryUtils.getLikeValue(key));

        PageRequest pageRequest = new PageRequest(getParaToInt("page", 1), 5);
        Page<Book> bookPage = Book.dao.pageRecord(pageRequest, queryMap, new Sort("name", Direction.ASC));

        setAttr("books", bookPage.getList());
        setAttr("bookPage", bookPage);
    }

    public void edit() {
        Integer id = getParaToInt();
        Book book;
        if (id == null || id == 0) {
            book = new Book();
        } else {
            book = Book.dao.findById(id);
        }
        setAttr("book", book);
    }

    public void save() {
        Book book = getModel(Book.class);
        book.saveOrUpdate();
        FlashMessageUtils.setMessage(this, "msg", "哈哈哈");
        redirect("/book");
    }

    public void delete() {
        Integer id = getParaToInt();
        Book book = Book.dao.findById(id);
        book.delete();
        FlashMessageUtils.setSuccessMessage(this, "删除成功！");
        redirect("/book");
    }
}
