package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import io.github.eternalpro.model.Book;
import io.github.gefangshuai.plugin.menumapper.core.Menu;
import io.github.gefangshuai.plugin.model.core.Direction;
import io.github.gefangshuai.plugin.model.core.Sort;
import io.github.gefangshuai.wfinal.flash.core.FlashMessage;
import io.github.gefangshuai.wfinal.flash.core.FlashMessageUtils;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/7.
 */
@Menu(mapper = "book")
@ControllerBind(controllerKey = "/book", viewPath = "book")
public class BookController extends Controller{
    public void index(){
        List<Book> books = Book.dao.findAll(new Sort(Book.dao.getPkName(), Direction.DESC));
        setAttr("books", books);
    }

    public void edit(){
        Integer id = getParaToInt();
        Book book;
        if (id == null|| id == 0) {
            book = new Book();
        }else {
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

    public void delete(){
        Integer id = getParaToInt();
        Book book = Book.dao.findById(id);
        book.delete();
        FlashMessageUtils.setSuccessMessage(this, "删除成功！");
        redirect("/book");
    }
}
