package io.github.eternalpro.model;

import cn.dreampie.web.model.Model;
import com.jfinal.ext.plugin.tablebind.TableBind;

/**
 * Created by gefangshuai on 2015-05-18-0018.
 */
@TableBind(tableName = "sec_user", pkName = "id")
public class User extends Model<User> {
    public static User dao = new User();

    public User findByUsername(String loginName) {
        return dao.findFirst("select * from sec_user t where t.username = ?", loginName);
    }

    public User findByMobile(String username) {
        return null;
    }


}
