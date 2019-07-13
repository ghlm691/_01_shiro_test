package com.qf.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * author: liu
 * date: 2019/7/11 22:37
 * info :
 */
public class Tutorial {

    @Test
    public void test1(){

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();

        currentUser.getSession();

        if (!currentUser.isAuthenticated()){

            UsernamePasswordToken token = new UsernamePasswordToken("root", "123");

            token.setRememberMe(true);

            try {

                currentUser.login(token);

                System.out.println("登录成功！！！");

            } catch (UnknownAccountException e){

                System.out.println("未知username");

            } catch (IncorrectCredentialsException e){

                System.out.println("未知密码");

            } catch (LockedAccountException e){

                System.out.println("账户被锁定");

            } catch (AuthenticationException e){

                System.out.println("错误！！");

            }

            if (currentUser.hasRole("admin")){

                System.out.println("你是管理员");

            }

            if (currentUser.isPermitted("can:add")){

                System.out.println("你可以增加");

            } else {

                System.out.println("你没有这个权限");
            }

            currentUser.logout();

        }


    }

}
