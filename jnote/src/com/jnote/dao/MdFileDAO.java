package com.jnote.dao;
// default package

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jnote.vo.MdFile;

/**
 	* A data access object (DAO) providing persistence and search support for MdFile entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .MdFile
  * @author MyEclipse Persistence Tools 
 */

public class MdFileDAO extends HibernateDaoSupport  {
		 private static final Log log = LogFactory.getLog(MdFileDAO.class);
		//property constants
	public static final String FILENAME = "filename";
	public static final String CONTENT = "content";
	public static final String USERID = "userid";
	public static final String FOLDERID = "folderid";
	public static final String SHAREURL = "shareurl";
	public static final String MDFILEID = "mdfileid";
	public static final String MDHTML = "mdhtml";



	protected void initDao() {
		//do nothing
	}
    
    public void save(MdFile transientInstance) {
        log.debug("saving MdFile instance");
        try {
//        	System.out.println("comein mdfileDao");
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void updateFile(MdFile mf){
//    	System.out.println("updata dao in ");
    	try{
    		String hql = "update MdFile mdfile set mdfile.filename=:fn, mdfile.content.content=:c, mdfile.modifytime=:mt, mdfile.mdhtml=:mh where mdfile.mdfileid=:mfid";
    		Query query = this.getSession().createQuery(hql);
    		query.setString("fn", mf.getFilename());
    		query.setString("c", mf.getContent());
    		query.setTimestamp("mt", mf.getModifytime());
    		query.setInteger("mfid", mf.getMdfileid());
    		query.setString("mh", mf.getMdhtml());
    		query.executeUpdate();
    	}catch(RuntimeException re){
    		throw re;
    	}
    }
    
    public int updataFileUrl(Integer mdfileid, String url){
    	try{
    		String hql = "update MdFile mdfile set mdfile.shareurl=:su where mdfile.mdfileid=:id";
    		Query query = this.getSession().createQuery(hql);
    		query.setString("su", url);
    		query.setInteger("id", mdfileid);
    		int resultLine = query.executeUpdate();
    		return resultLine;
    	}catch(RuntimeException re){
    		throw re;
    	}
    }
    
	public void delete(MdFile persistentInstance) {
        log.debug("deleting MdFile instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	/**
	 * deleteById-mdfileid
	 * @param fid
	 * @return
	 */
	public int deleteById(Integer fid){
		try{
//			System.out.println("folder-dao-in-deletebyid");
			String hql = "delete from MdFile where mdfileid=:id";
			Query query = this.getSession().createQuery(hql);
			query.setParameter("id", fid);
			int resultNum = query.executeUpdate();
//			System.out.println("folder-dao-in-deletebyid"+resultNum);
			return resultNum;
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public List getChildMdFile(java.lang.Integer userid,java.lang.Integer folderid){
    	try{
    		List lists = null;
    		String hql ="from MdFile where userid=:uid and folderid=:fid";
    		Query query = this.getSession().createQuery(hql);
    		query.setParameter("uid", userid);
    		query.setParameter("fid", folderid);
    		lists = query.list();
    		return lists;
    	}catch(RuntimeException re){
    		throw re;
    	}
//    	System.out.println(userid+"-"+folderid);
//    	System.out.println("comein DAO :"+lists.size());
    	
	}
    
    public MdFile findById( java.lang.Integer id) {
        log.debug("getting MdFile instance with id: " + id);
        try {
            MdFile instance = (MdFile) getHibernateTemplate().get("MdFile", id);
//            System.out.println(instance);//这个方法始终不可用
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public MdFile findFileById(java.lang.Integer id){
    	try{
    		List list = findByProperty(MDFILEID, id);
    		if(list.size() == 0){
    			return null;
    		}else{
    			return (MdFile) list.get(0);
    		}
    	}catch(RuntimeException re){
    		throw re;
    	}
    }
    
    
    public List findByExample(MdFile instance) {
        log.debug("finding MdFile instance by example");
        try {
//        	System.out.println(instance.getMdfileid());
            List results = getHibernateTemplate().findByExample(instance);
//          System.out.println(results.size());
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding MdFile instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MdFile as model where model." + propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByFilename(Object filename) {
		try{
			return findByProperty(FILENAME, filename);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List findByUserid(Object userid
	) {
		return findByProperty(USERID, userid
		);
	}
	
	public List findByFolderid(Object folderid
	) {
		return findByProperty(FOLDERID, folderid
		);
	}
	
	public List findByShareurl(Object shareurl
	) {
		return findByProperty(SHAREURL, shareurl
		);
	}
	

	public List findAll() {
		log.debug("finding all MdFile instances");
		try {
			String queryString = "from MdFile";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MdFile merge(MdFile detachedInstance) {
        log.debug("merging MdFile instance");
        try {
            MdFile result = (MdFile) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MdFile instance) {
        log.debug("attaching dirty MdFile instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MdFile instance) {
        log.debug("attaching clean MdFile instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MdFileDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MdFileDAO) ctx.getBean("MdFileDAO");
	}
}