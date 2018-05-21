package com.piedpiper.platform.core.hibernate;

import com.piedpiper.platform.commons.utils.ComUtil;
import com.piedpiper.platform.core.mybatis.shard.ShardException;
import com.piedpiper.platform.core.mybatis.shard.converter.SqlConverterFactory;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShardInterceptor extends EmptyInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ShardInterceptor.class);
	private static final long serialVersionUID = 1L;
	public static final int tableCounts = 8;
	private String instanceId = null;
	public static final ThreadLocal<ActionMarker> LOCAL_MARKER = new ThreadLocal();
	public static final ThreadLocal<Integer> IN_BPM_ACTION = new ThreadLocal();

	public String getIntanceId() {
		return this.instanceId;
	}

	public void setIntanceId(String intanceId) {
		this.instanceId = intanceId;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		return false;
	}

	public String onPrepareStatement(String sql) {
		String regEx = "BPM_|bpm_";
		boolean result = Pattern.compile(regEx).matcher(sql).find();
		if (!result)
			return sql;
		SqlConverterFactory cf = SqlConverterFactory.getInstance();
		try {
			if (sql.contains("delete from BPM_OPERATE_DESC")) {
				if (IN_BPM_ACTION.get() == null) {
					logger.debug("=============shardInterceptor======null");
				} else if ((IN_BPM_ACTION.get() != null) && (((Integer) IN_BPM_ACTION.get()).intValue() == 1)) {
					LOCAL_MARKER.remove();
					IN_BPM_ACTION.remove();
					logger.debug("=============shardInterceptor======111");
				} else {
					int num = ((Integer) IN_BPM_ACTION.get()).intValue();
					num--;
					IN_BPM_ACTION.set(Integer.valueOf(num));
					logger.debug("=============shardInterceptor======***");
				}
				return sql;
			}
			if ((IN_BPM_ACTION.get() != null) && (((Integer) IN_BPM_ACTION.get()).intValue() > 0)) {
				String keyId = ((ActionMarker) LOCAL_MARKER.get()).getKeyId();
				sql = cf.convert(sql, Integer.valueOf(ComUtil.getHashInt(keyId, 8)), null);
			}
		} catch (ShardException e) {
			e.printStackTrace();
		}
		return sql;
	}
}
