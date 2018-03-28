package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // o Spring ativa o gerenciamento de transações - habilitado
public class JPAConfiguration {// estamos fornecendo para o Spring qual é o banco, o usuário ou a senha do
								// banco de dados.

	// criaremos o método que será gerenciado pelo Spring e criará o EntityManager
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		factoryBean.setJpaVendorAdapter(vendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource(); // contém as configurações de banco de
																			// dados.
		dataSource.setUsername("root");
		dataSource.setPassword("pc1000");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_spring");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");

		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models"); // aonde o EM encontrara as entidades

		return factoryBean;
	}

	@Bean
	// nossa operação com o banco de dados deve ser gerenciada com uma transação.
	// precisaremos de um TransactionManager que conheça o EntityManager para
	// gerenciar as transações das entidades
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
