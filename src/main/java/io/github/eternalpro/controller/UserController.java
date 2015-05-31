package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import io.github.eternalpro.model.User;

import java.util.List;

/**
 * Created by gefangshuai on 2015-05-18-0018.
 */
@ControllerBind(controllerKey = "/user", viewPath = "user")
public class UserController extends Controller {

    public void index(){
        List<User> users = User.dao.findAll();
        setAttr("users", users);
    }

}
