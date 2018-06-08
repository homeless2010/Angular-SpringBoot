package com.piedpiper.platform.core.dao.datasource;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.StringUtils;

public class HibernateSessionFactoryBean extends LocalSessionFactoryBean implements ApplicationContextAware {
	private String dataSourceName;
	private ApplicationContext applicationContext;
	private boolean isDefault = false;

	public void afterPropertiesSet() throws IOException {
		DataSourceFactory dataSourceFactory = (DataSourceFactory) this.applicationContext
				.getBean(DataSourceFactory.class);
		if (StringUtils.hasText(this.dataSourceName)) {
			Map<String, DataSource> dsMap = dataSourceFactory.getDataSources();
			for (String name : dsMap.keySet()) {
				if (name.equals(this.dataSourceName)) {
					setDataSource((DataSource) dsMap.get(name));
//					this.logger.info("当前HibernateSessionFactoryBean的数据源设置为：" + this.dataSourceName);
					break;
				}
			}
			if (this.dataSourceName.equals(DataSourceFactory.getDefaultDataSourceName())) {
				setDefault(true);
			}
		}
		super.afterPropertiesSet();
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public String getDataSourceName() {
		return this.dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public boolean isDefault() {
		return this.isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
