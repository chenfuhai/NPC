package com.liufeng.npc.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;

/*
可以自己改下表名什么的
protected static final String DEFAULT_AUTHENTICATION_QUERY = "select password from users where username = ?";
protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select password, password_salt from users where username = ?";
protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";
protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from roles_permissions where role_name = ?";
 */
public class MyJdbcRealm extends JdbcRealm {
}
