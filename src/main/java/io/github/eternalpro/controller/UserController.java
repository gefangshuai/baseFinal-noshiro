package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import io.github.eternalpro.model.User;
import io.github.gefangshuai.wfinal.menumapper.annotation.Menu;
import io.github.gefangshuai.wfinal.model.search.Direction;
import io.github.gefangshuai.wfinal.model.search.Sort;
import io.github.gefangshuai.wfinal.security.annotation.AccessClear;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;
import io.github.gefangshuai.wfinal.security.core.SecurityKit;

import java.util.List;

/**
 * Created by gefangshuai on 2015-05-18-0018.
 */
@Menu(mapper = "user")
@ControllerBind(controllerKey = "/user", viewPath = "user")
public class UserController extends Controller {

    @AccessClear
    public void index() {

        List<User> users = User.dao.findAll(new Sort("id", Direction.ASC));
        setAttr("users", users);
    }

    @LoginRequired
    public void view(){
        Integer id = getParaToInt();
        User user = User.dao.findById(id);
        setAttr("user", user);
    }

}
