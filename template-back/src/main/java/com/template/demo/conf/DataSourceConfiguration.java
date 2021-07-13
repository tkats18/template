package com.template.demo.conf;
//
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.template.demo.storage.repository" ,
        entityManagerFactoryRef="templateEntityManagerFactory",
        transactionManagerRef = "templateTransactionManager"
)
public class DataSourceConfiguration {

//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Bean(name = "templateDataSource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().driverClassName(Driver.class.getName())
//                .username("tato")
//                .password("Qwerty123$")
//                .driverClassName(driverClassName)
//                .url(url)
//                .build();
//    }
//
    @Bean(name = "templateEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean filterEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        Map<String, Object> prop = new HashMap<>();
        prop.put("hikari.maximum-pool-size", 2);
        prop.put("hikari.minimum", 1);
//        prop.put("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQL12Dialect");
//        prop.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL12Dialect");
//        prop.put("spring.datasource.platform", "postgres");
        prop.put("hibernate.hbm2ddl.auto", "create-drop");
//        prop.put("hibernate.id.new_generator_mappings",true);
        prop.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
//        prop.put("spring.jpa.hibernate.naming.implicit-strategy","org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        prop.put("hikari.idle-timeout", 10000);
        prop.put("hikari.max-lifetime", 30000);

        return builder
                .dataSource(dataSource)
                .packages("com.template.demo.storage")
                .persistenceUnit("template")
                .properties(prop)
                .build();
    }
    @Bean(name = "templateTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("templateEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
