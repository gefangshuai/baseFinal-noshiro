package io.github.eternalpro.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.HashKit;
import io.github.eternalpro.constant.SessionConstant;
import io.github.eternalpro.model.User;
import io.github.gefangshuai.wfinal.flash.core.FlashMessageUtils;
import io.github.gefangshuai.wfinal.security.core.SecurityKit;
import io.github.gefangshuai.wfinal.security.core.Subject;
import io.github.gefangshuai.wfinal.security.proxy.LoginValidateProxy;
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
        final String username = getPara("username");
        final String password = getPara("password");
        final User dbUser = User.dao.findByUsername(username);

        boolean isLogin = SecurityKit.login(new LoginValidateProxy() {
            @Override
            public Subject loginCheck() {
                if (HashKit.md5(password).equals(dbUser.getStr("password"))) {
                    return new Subject(dbUser, true);
                }
                return null;
            }
        }, getSession());

        if (isLogin) {
            FlashMessageUtils.redirectSuccessMessage(this, "/", "登录成功，欢迎访问！");
        }else{
            FlashMessageUtils.redirectErrorMessage(this, "/signin", "登录失败，请重新登录！");
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
            user.set("password", HashKit.md5(password)).save();
            FlashMessageUtils.setSuccessMessage(this, "注册成功，请登录！");
            redirect("/signin");
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        SecurityKit.logout(getSession());
        FlashMessageUtils.setSuccessMessage(this, "退出成功！");
        redirect("/signin");
    }
}
