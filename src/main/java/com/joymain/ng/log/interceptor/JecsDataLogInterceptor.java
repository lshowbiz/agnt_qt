package com.joymain.ng.log.interceptor;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.joymain.ng.log.util.LogConstants;
import com.joymain.ng.log.util.LogUtil;
import com.joymain.ng.model.BaseObject;
import com.joymain.ng.util.ContextUtil;








public class JecsDataLogInterceptor extends EmptyInterceptor {
	private static Log log = LogFactory.getLog(JecsDataLogInterceptor.class);
	private static final String UPDATE = "update";
	private static final String INSERT = "insert";
	private static final String DELETE = "delete";
	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		log.info("onDelete:>>>"+entity.getClass());
		// TODO Auto-generated method stub
		if (entity instanceof BaseObject) {
			if (LogConstants.black_list.contains(entity.getClass())) {
				return;
			}
			try {
				String simpleClassName=entity.getClass().getSimpleName();

				LogUtil.saveLog(entity, null, null, id.toString(), DELETE,simpleClassName);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}

	@Override
	public boolean onFlushDirty(Object obj, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		log.info("onFlushDirty:>>>"+obj.getClass());
		// TODO Auto-generated method stub
		if (obj instanceof BaseObject) {
			if (LogConstants.black_list.contains(obj.getClass())) {
				return false;
			}
			if (ContextUtil.getContext() == null) {
				return false;
			}
//			DataLog log = (DataLog) ContextUtil.getContext().getBean("dataLog");

			Class objectClass = obj.getClass();
			String className = objectClass.getSimpleName();
/*
			String[] tokens = className.split("\\.");
			int lastToken = tokens.length - 1;
			className = tokens[lastToken];

*/			try {
				// Use the id and class to get the pre-update state from the
				// database
				Serializable persistedObjectId = LogUtil.getObjectId(obj);
				Object preUpdateState = LogUtil.getPreviousObject (objectClass, persistedObjectId);

				LogUtil.saveLog(obj, preUpdateState, null, persistedObjectId.toString(), UPDATE, className);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean onSave(Object obj, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		log.info("onsaveLog:>>>"+obj.getClass());

		if (obj instanceof BaseObject) {
			if (LogConstants.black_list.contains(obj.getClass())) {
				return false;
			}
			if (ContextUtil.getContext() == null) {
				return false;
			}
			Class objectClass = obj.getClass();
			String className = objectClass.getSimpleName();
/*
			String[] tokens = className.split("\\.");
			int lastToken = tokens.length - 1;
			className = tokens[lastToken];

*/			try {
				// Use the id and class to get the pre-update state from the
				Serializable persistedObjectId = LogUtil.getObjectId(obj);

				LogUtil.saveLog(obj, null, null, persistedObjectId.toString(), INSERT, className);

			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
			} catch (IllegalAccessException e) {
//				e.printStackTrace();
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		return false;
	}

}
