package com.piedpiper.platform.core.hibernate;

import com.piedpiper.platform.commons.utils.ComUtil;
import com.piedpiper.platform.core.dao.hibernate.CommonHibernateDao2;
import com.piedpiper.platform.core.domain.DbOperationDesc;
import com.piedpiper.platform.core.spring.SpringFactory;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ShardRecorder {
	public static ShardRecorder recorder = null;

	public static ShardRecorder getInstance() {
		if (recorder == null) {
			recorder = new ShardRecorder();
		}
		return recorder;
	}

	protected CommonHibernateDao2 hibernateDao = (CommonHibernateDao2) SpringFactory.getBean("commonHibernateDao2");

	private DbOperationDesc desc = null;

	public void setLocalVariable(String keyId, String operationName, String name) {
		if (null == keyId)
			return;
		ActionMarker am = new ActionMarker();
		am.setActionName(operationName);
		am.setKeyId(keyId);
		if (ShardInterceptor.IN_BPM_ACTION.get() == null) {
			ShardInterceptor.IN_BPM_ACTION.set(Integer.valueOf(1));
		} else {
			int num = ((Integer) ShardInterceptor.IN_BPM_ACTION.get()).intValue();
			num++;
			ShardInterceptor.IN_BPM_ACTION.set(Integer.valueOf(num));
		}
		ShardInterceptor.LOCAL_MARKER.set(am);
	}

	public void setLocalVariable1(String keyId, String operationName) {
		if (null == keyId)
			return;
		ActionMarker am = new ActionMarker();
		am.setActionName(operationName);
		am.setKeyId(keyId);
		if (ShardInterceptor.IN_BPM_ACTION.get() == null) {
			ShardInterceptor.IN_BPM_ACTION.set(Integer.valueOf(1));
		} else {
			int num = ((Integer) ShardInterceptor.IN_BPM_ACTION.get()).intValue();
			num++;
			ShardInterceptor.IN_BPM_ACTION.set(Integer.valueOf(num));
		}
		ShardInterceptor.LOCAL_MARKER.set(am);
	}

	public void removeLocalVariable(String name) {
		if (null == ShardInterceptor.IN_BPM_ACTION.get()) {
			return;
		}
		int num = ((Integer) ShardInterceptor.IN_BPM_ACTION.get()).intValue();
		num--;
		if (num <= 0) {
			ShardInterceptor.IN_BPM_ACTION.remove();
			ShardInterceptor.LOCAL_MARKER.remove();
		} else {
			ShardInterceptor.IN_BPM_ACTION.set(Integer.valueOf(num));
		}
	}

	public void startRecord(String keyId, String operationName, String name) {
		setLocalVariable1(keyId, operationName);
		DbOperationDesc desc = new DbOperationDesc();
		String id = ComUtil.getId();
		desc.setDbid(id);
		desc.setKeyid(keyId);
		desc.setOperateDesc(operationName);
		this.hibernateDao.save(desc);
	}

	public void startRecordForDelete(String keyId, String operationName, String name) {
		DbOperationDesc desc = new DbOperationDesc();
		String id = ComUtil.getId();
		desc.setDbid(id);
		desc.setKeyid(keyId);
		desc.setOperateDesc(operationName);
		this.hibernateDao.save(desc);
	}

	public void endRecordForDelete(String name, String Id) {
		DetachedCriteria dc = DetachedCriteria.forClass(DbOperationDesc.class);
		dc.add(Restrictions.eq("keyid", Id));
		List<DbOperationDesc> list = this.hibernateDao.findByCriteria(dc);
		if ((null != list) && (list.size() > 0)) {
			for (DbOperationDesc desc : list) {
				this.hibernateDao.delete(desc);
			}
		}
	}

	public void endRecord(String name, String Id) {
		DetachedCriteria dc = DetachedCriteria.forClass(DbOperationDesc.class);
		dc.add(Restrictions.eq("keyid", Id));
		List<DbOperationDesc> list = this.hibernateDao.findByCriteria(dc);
		if ((null != list) && (list.size() > 0)) {
			for (DbOperationDesc desc : list) {
				this.hibernateDao.delete(desc);
			}
		}
	}
}
