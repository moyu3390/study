//package com.nijunyang.xa;
//
//import com.mysql.cj.jdbc.JdbcConnection;
//import com.mysql.cj.jdbc.MysqlXAConnection;
//import com.mysql.cj.jdbc.MysqlXid;
//
//import javax.sql.XAConnection;
//import javax.transaction.xa.XAException;
//import javax.transaction.xa.XAResource;
//import javax.transaction.xa.Xid;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
///**
// * Description:
// * Created by nijunyang on 2021/11/16 22:24
// */
//public class MysqlXA {
//
//    public static void main(String[] args) throws SQLException, XAException {
//        //true表示打印XA语句,，用于调试
//        boolean xaLog = true;
//        // 获得资源管理器操作接口实例 RM1
//        Connection conn1 = DriverManager.getConnection
//                ("jdbc:mysql://localhost:3306/njytest?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "root");
//        XAConnection xaConn1 = new MysqlXAConnection((JdbcConnection) conn1, xaLog);
//        XAResource rm1 = xaConn1.getXAResource();
//
//        // 获得资源管理器操作接口实例 RM2
//        Connection conn2 = DriverManager.getConnection
//                ("jdbc:mysql://localhost:3306/njytest1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "root");
//        XAConnection xaConn2 = new MysqlXAConnection((JdbcConnection) conn2, xaLog);
//        XAResource rm2 = xaConn2.getXAResource();
//
//        // AP请求TM执行一个分布式事务，TM生成全局事务id
//        byte[] globalTxId = "g001".getBytes();
//        int formatId = 1;
//        Xid xid1 = null;
//        Xid xid2 = null;
//        try {
//
//            // ==============分别执行RM1和RM2上的事务分支====================
//            // TM生成rm1上的事务分支id
//            byte[] branchTxId1 = "b1001".getBytes();
//            xid1 = new MysqlXid(globalTxId, branchTxId1, formatId);
//            // 执行rm1上的事务分支
//            rm1.start(xid1, XAResource.TMNOFLAGS);//One of TMNOFLAGS, TMJOIN, or TMRESUME.
//            PreparedStatement ps1 = conn1.prepareStatement(
//                    "INSERT into t_user(id, `name`) VALUES (1, '李四')");
//            ps1.execute();
//            rm1.end(xid1, XAResource.TMSUCCESS);
//
//            // TM生成rm2上的事务分支id
//            byte[] branchTxId2 = "b2001".getBytes();
//            xid2 = new MysqlXid(globalTxId, branchTxId2, formatId);
//            // 执行rm2上的事务分支
//            rm2.start(xid2, XAResource.TMNOFLAGS);
//            PreparedStatement ps2 = conn2.prepareStatement(
//                    "INSERT into account(user_id, money) VALUES (1,10000000)");
//            ps2.execute();
//            rm2.end(xid2, XAResource.TMSUCCESS);
//
//            // ===================两阶段提交================================
//            // phase1：询问所有的RM 准备提交事务分支
//            int rm1_prepare = rm1.prepare(xid1);
//            int rm2_prepare = rm2.prepare(xid2);
//            // phase2：提交所有事务分支
//            boolean onePhase = false;
//            //TM判断有2个事务分支，所以不能优化为一阶段提交
//            if (rm1_prepare == XAResource.XA_OK
//                    && rm2_prepare == XAResource.XA_OK) {
//                //所有事务分支都prepare成功，提交所有事务分支
//                rm1.commit(xid1, onePhase);
//                rm2.commit(xid2, onePhase);
//            } else {
//                //如果有事务分支没有成功，则回滚
//                rm1.rollback(xid1);
//                rm2.rollback(xid2);
//            }
//        } catch (XAException e) {
//            rm1.rollback(xid1);
//            rm2.rollback(xid2);
//        }
//    }
//}
