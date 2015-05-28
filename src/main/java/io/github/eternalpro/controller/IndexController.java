package io.github.eternalpro.controller;

import cn.dreampie.web.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.EncryptionKit;
import io.github.eternalpro.constant.SessionConstant;
import io.github.eternalpro.core.FlashMessageUtils;
import io.github.eternalpro.model.User;


/**
 * Created by fangshuai on 2015-04-14-0014.
 */
@ControllerBind(controllerKey = "/", viewPath = "")
public class IndexController extends Controller {

    public void index() {
    }

    public void signin() {

    }

    public void checkLogin() {
        String username = getPara("username");
        String password = getPara("password");
        User dbUser = User.dao.findByUsername(username);
        if (dbUser == null) {
            FlashMessageUtils.setErrorMessage(this, "用户不存在！");
            redirect("/signin");
        } else if (!dbUser.getStr("password").equals(EncryptionKit.md5Encrypt(password))) {
            FlashMessageUtils.setErrorMessage(this, "密码错误！");
            redirect("/signin");
        }else{
            FlashMessageUtils.setSuccessMessage(this, "登录成功，欢迎！");
            getSession().setAttribute(SessionConstant.LOGIN_USER, dbUser);
            redirect("/");
        }

    }

    public void logout() {
        getSession().removeAttribute(SessionConstant.LOGIN_USER);
        FlashMessageUtils.setSuccessMessage(this, "退出成功！");
        redirect("/signin");
    }
}
