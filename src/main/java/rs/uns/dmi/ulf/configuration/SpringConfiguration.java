package rs.uns.dmi.ulf.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Nikola on 5.4.2016.
 */
@Configuration
@ComponentScan("rs.uns.dmi.ulf")
@EnableJpaRepositories("rs.uns.dmi.ulf.data.repositories")
public class SpringConfiguration {

    @Bean(name="transactionManager")
    public PlatformTransactionManager getPlatformTransactionManager(){
        JpaTransactionManager transactionManager=new JpaTransactionManager();
        return transactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalEntitiyManagerFactoryBean(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("rs.uns.dmi.ulf.data.entities");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        jpaProperties.put("hibernate.show_sql",false);
        jpaProperties.put("hibernate.hbm2ddl.auto","update");
        jpaProperties.setProperty("packagesToScan", "rs.uns.dmi.ulf.data.entities");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/ulfDataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
