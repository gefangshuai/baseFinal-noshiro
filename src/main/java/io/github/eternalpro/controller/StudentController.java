package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import io.github.eternalpro.model.Student;
import io.github.gefangshuai.wfinal.menumapper.annotation.Menu;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/7/30.
 */
@Menu(mapper = "student")
@ControllerBind(controllerKey = "/student", viewPath = "student")
public class StudentController extends Controller{
    public void index(){
        renderJsp("");
    }
    public void edit(){

    }
    public void save() {
        Student student = getModel(Student.class, "student");
        renderJson(student);
    }
}
