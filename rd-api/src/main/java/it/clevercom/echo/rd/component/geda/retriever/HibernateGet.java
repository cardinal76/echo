package it.clevercom.echo.rd.component.geda.retriever;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import com.inspiresoftware.lib.dto.geda.adapter.EntityRetriever;

public class HibernateGet implements EntityRetriever {
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean rdEntityManagerFactory;
	
	private Session session;

	public HibernateGet() {
		this.session = rdEntityManagerFactory.getObject().unwrap(Session.class);
	}

    public Object retrieveByPrimaryKey(final Class entityInterface, final Class entityClass, final Object primaryKey) {
     	return session.get(entityClass, (Serializable)primaryKey);
    }
}