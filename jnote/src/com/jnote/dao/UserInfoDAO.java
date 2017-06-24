package com.jnote.dao;
// default package

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jnote.vo.UserInfo;

/**
 	* A data access object (DAO) providing persistence and search support for UserInfo entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .UserInfo
  * @author MyEclipse Persistence Tools 
 */

public class UserInfoDAO extends HibernateDaoSupport  {
		 private static final Log log = LogFactory.getLog(UserInfoDAO.class);
		//property constants
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	public static final String HEADURL = "headurl";
	public static final String USERID = "userid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(UserInfo transientInstance) {
        log.debug("saving UserInfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public int updateUserInfo(UserInfo ui){
    	try{
    		String hql = "update UserInfo as userInfo set userInfo.tel =:tel,userInfo.email=:em,userInfo.headurl=:hu where userInfo.userid=:uid";
        	Query query = this.getSession().createQuery(hql);
        	query.setString("tel", ui.getTel());
        	query.setString("em", ui.getEmail());
        	query.setString("hu", ui.getHeadurl());
        	query.setInteger("uid", ui.getUserid());
        	int resultLine = query.executeUpdate();
        	return resultLine;
    	}catch(RuntimeException re){
    		throw re;
    	}
    }
    
	public void delete(UserInfo persistentInstance) {
        log.debug("deleting UserInfo instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public UserInfo findById( java.lang.Integer id) {
        log.debug("getting UserInfo instance with id: " + id);
        try {
            UserInfo instance = (UserInfo) getHibernateTemplate()
                    .get("UserInfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(UserInfo instance) {
        log.debug("finding UserInfo instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding UserInfo instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from UserInfo as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
    
    public UserInfo findByUserid(Object userid){
    	List list = findByProperty(USERID, userid);
    	if(list.size() == 0){
    		return null;
    	}else {
    		return (UserInfo) list.get(0);
    	}
    }

	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List findByTel(Object tel
	) {
		return findByProperty(TEL, tel
		);
	}
	
	public List findByHeadurl(Object headurl
	) {
		return findByProperty(HEADURL, headurl
		);
	}
	

	public List findAll() {
		log.debug("finding all UserInfo instances");
		try {
			String queryString = "from UserInfo";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public UserInfo merge(UserInfo detachedInstance) {
        log.debug("merging UserInfo instance");
        try {
            UserInfo result = (UserInfo) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(UserInfo instance) {
        log.debug("attaching dirty UserInfo instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(UserInfo instance) {
        log.debug("attaching clean UserInfo instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static UserInfoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (UserInfoDAO) ctx.getBean("UserInfoDAO");
	}
}