package io.github.eternalpro.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import io.github.gefangshuai.wfinal.model.core.WModel;

import java.util.List;

/**
 * Created by gefangshuai on 2015-05-18-0018.
 */
@TableBind(tableName = "sec_user", pkName = "id")
public class User extends WModel<User> {
    public static final User dao = new User();

    public User findByUsername(String loginName) {
        return dao.findFirst("select * from sec_user t where t.username = ?", loginName);
    }

    public User findByMobile(String username) {
        return null;
    }

}
