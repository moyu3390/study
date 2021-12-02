package com.nijunyang.tx.xa.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.nijunyang.tx.xa.mapper1.OrderMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Description:
 * Created by nijunyang on 2021/12/3 0:11
 */
@Configuration
/**
 * 制定此mapper使用哪个sqlSessionTemplate
 */
@MapperScan(basePackages = "com.nijunyang.tx.xa.mapper1", sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class MyBatisConfig1 {

    //配置XA数据源
    @Primary
    @Bean(name = "orderDataSource")
    public DataSource orderDataSource(DBConfig1 dbConfig1) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfig1.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfig1.getPassword());
        mysqlXaDataSource.setUser(dbConfig1.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("orderDataSource");

        xaDataSource.setMinPoolSize(dbConfig1.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfig1.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfig1.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfig1.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dbConfig1.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfig1.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfig1.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfig1.getTestQuery());
        return xaDataSource;
    }

    @Primary
    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(
            @Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
