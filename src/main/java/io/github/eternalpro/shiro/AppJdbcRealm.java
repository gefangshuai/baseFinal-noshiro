package io.github.eternalpro.shiro;

import cn.dreampie.shiro.core.SubjectKit;
import io.github.eternalpro.model.Permission;
import io.github.eternalpro.model.Role;
import io.github.eternalpro.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AppJdbcRealm extends AuthorizingRealm {

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws org.apache.shiro.authc.AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        User user = User.dao.findByUsername(username);
        if (user != null) {
            ShiroUser shiroUser = new ShiroUser();
            shiroUser.setUsername(user.getStr("username"));
            shiroUser.setRealname(user.getStr("realname"));
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, user.getStr("password"), getName());
            return info;
        }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     *
     * @param principals 用户信息
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = ((ShiroUser)principals.asList().get(0));
        String loginName = shiroUser.getUsername();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new LinkedHashSet<>(); // 角色集合
        Set<String> permissionSet = new LinkedHashSet<>();  // 权限集合
        List<Role> roles = null;
        User user = User.dao.findByUsername(loginName);
        if (user != null) {
            // 遍历角色
            roles = Role.dao.findByUser(user.getLong("id"));
            shiroUser.setUrl(roles.get(0).getStr("url"));
        } else {
            SubjectKit.getSubject().logout();
        }
        loadRole(roleSet, permissionSet, roles);
        info.setRoles(roleSet); // 设置角色
        info.setStringPermissions(permissionSet); // 设置权限
        return info;
    }

    /**
     * @param roleSet
     * @param permissionSet
     * @param roles
     */
    private void loadRole(Set<String> roleSet, Set<String> permissionSet, List<Role> roles) {
        List<Permission> permissions;
        for (Role role : roles) {
            //角色可用
            if (role.getDate("deleted_at") == null) {
                roleSet.add(role.getStr("value"));
                permissions = Permission.dao.findByRole(role.getLong("id"));
                loadAuth(permissionSet, permissions);
            }
        }
    }

    /**
     * @param permissionSet
     * @param permissions
     */
    private void loadAuth(Set<String> permissionSet, List<Permission> permissions) {
        //遍历权限
        for (Permission permission : permissions) {
            //权限可用
            if (permission.getDate("deleted_at") == null) {
                permissionSet.add(permission.getStr("value"));
            }
        }
    }

    /**
     * 更新用户授权信息缓存.
     */

    public void clearCachedAuthorizationInfo(Object principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}