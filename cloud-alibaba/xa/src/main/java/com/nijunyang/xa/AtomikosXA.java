package com.nijunyang.xa;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Description:
 * Created by nijunyang on 2021/11/16 23:08
 */
public class AtomikosXA {


    public static void main(String[] args) {
        AtomikosDataSourceBean ds1 = createDataSourceBean("njytest");
        AtomikosDataSourceBean ds2 = createDataSourceBean("njytest1");
        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        UserTransaction userTransaction = new UserTransactionImp();
        try {
            // 开启事务
            userTransaction.begin();

            // 执行db1上的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("INSERT into t_user(id, `name`) VALUES (3, '张三')");
            ps1.execute();
            // 执行db2上的sql
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("INSERT into account(user_id, money) VALUES (3,10000000)");
            ps2.execute();
//            // 模拟异常 ，直接进入catch代码块，2个都不会提交
//            int i=1/0;
            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn1.close();
                conn2.close();
                ds1.close();
                ds2.close();
            } catch (Exception ignore) {
            }
        }
    }

    private static AtomikosDataSourceBean createDataSourceBean(String dbName) {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", String.format("jdbc:mysql://localhost:3306/%s??useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", dbName));
        p.setProperty("user", "root");
        p.setProperty("password", "root");
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setUniqueResourceName(dbName);
        dataSourceBean.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        dataSourceBean.setXaProperties(p);
        return dataSourceBean;
    }


}
