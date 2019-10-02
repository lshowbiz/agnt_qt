package com.joymain.ng.dao.jpa;

import com.joymain.ng.dao.UserDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Query;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.util.List;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with 
 *   the new BaseDaoHibernate implementation that uses generics.
*/
@Repository("userDao")
public class UserDaoJpa extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {
    @Autowired
    private DataSource dataSource;

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoJpa() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query q = getSession().createQuery("select u from User u order by upper(u.username)");
        return q.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query q = getSession().createQuery("select u from User u where username=?");
        q.setParameter(1, username);
        List<User> users = q.list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return users.get(0);
        }
    }

    /**
     * Save user and flush entityManager
     * @param user the user to save
     * @return the updated user
     */
    public User saveUser(User user) {
        User u = super.save(user);
        getSession().flush();
        return u;
    }

    /**
     * {@inheritDoc}
     */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
    }
}
