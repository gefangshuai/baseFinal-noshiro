package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.EncryptionKit;
import io.github.eternalpro.constant.SessionConstant;
import io.github.eternalpro.core.FlashMessageUtils;
import io.github.eternalpro.model.User;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by fangshuai on 2015-04-14-0014.
 */
@ControllerBind(controllerKey = "/", viewPath = "")
public class IndexController extends Controller {

    /**
     * 站点首页
     */
    public void index() {
    }

    /**
     * 用户登录页
     */
    public void signin() {

    }

    /**
     * 用户注册页
     */
    public void signup() {

    }

    /**
     * 用户登录检查
     */
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
        } else {
            FlashMessageUtils.setSuccessMessage(this, "登录成功，欢迎！");
            getSession().setAttribute(SessionConstant.LOGIN_USER, dbUser);
            redirect("/");
        }

    }


    /**
     * 用户注册
     */
    public void register() {
        User user = getModel(User.class);
        String confirm_password = getPara("confirm_password");
        String username = user.getStr("username");
        String password = user.getStr("password");
        if (StringUtils.isBlank(username)) {
            FlashMessageUtils.setErrorMessage(this, "用户名不能为空！");
            redirect("/signup");
        } else if (User.dao.findByUsername(username) != null) {
            FlashMessageUtils.setErrorMessage(this, "用户名已被使用！");
            redirect("/signup");
        } else if (StringUtils.isBlank(password)) {
            FlashMessageUtils.setErrorMessage(this, "密码不能为空！");
            redirect("/signup");
        } else if (!password.equals(confirm_password)) {
            FlashMessageUtils.setErrorMessage(this, "两次密码输入不一致！");
            redirect("/signup");
        } else {
            user.set("password", EncryptionKit.md5Encrypt(password)).save();
            FlashMessageUtils.setSuccessMessage(this, "注册成功，请登录！");
            redirect("/signin");
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        getSession().removeAttribute(SessionConstant.LOGIN_USER);
        FlashMessageUtils.setSuccessMessage(this, "退出成功！");
        redirect("/signin");
    }
}
