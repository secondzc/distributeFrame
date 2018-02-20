package com.tongyuan.testmp1.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan("com.tongyuan.testmp1.dao")
public class MyBatisConfig {

	@Bean("mybatisSqlSession")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration) throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setTypeAliasesPackage("com.tongyuan.testmp1.dao");
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		sqlSessionFactory.setConfiguration(configuration);
//		sqlSessionFactory.setPlugins(new Interceptor[]{
//				new PaginationInterceptor(),
//				new PerformanceInterceptor(),
//				new OptimisticLockerInterceptor()
//		});
		sqlSessionFactory.setGlobalConfig(globalConfiguration);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public GlobalConfiguration globalConfiguration() {
		GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
		conf.setLogicDeleteValue("-1");
		conf.setLogicNotDeleteValue("1");
		conf.setIdType(0);
//		conf.setMetaObjectHandler(new H2MetaObjectHandler());
		return conf;
	}
}