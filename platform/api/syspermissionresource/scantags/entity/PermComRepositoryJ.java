package com.piedpiper.platform.api.syspermissionresource.scantags.entity;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface.PermComRepositoryI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.collections.ListUtils;

public class PermComRepositoryJ extends HashMap<String, Object> implements PermComRepositoryI {
	private static final long serialVersionUID = -7976562221911951131L;

	public List<SysPermissionResource> getPermResourceWithDataGrid() {
		List<SysPermissionResource> result = new ArrayList();
		for (Map.Entry<String, Object> entry : entrySet()) {
			if ((entry.getValue() instanceof DataGridDefinitions)) {
				DataGridDefinitions definitions = (DataGridDefinitions) entry.getValue();
				SysPermissionResource res = new SysPermissionResource();
				res.setDatagrid(definitions.getDataGridId());
				res.setDataset(definitions.getUrl());
				res.setMetadata(definitions.getDataPermission());
				res.setDatatype("");
				res.setStatus("0");

				String secretField = "0";
				for (DataGridDefinitions.DataGridField dataGridFiled : definitions.getFieldList()) {
					String fieldName = dataGridFiled.getFiledName();
					if ((fieldName.equalsIgnoreCase("SECRET_LEVEL")) || (fieldName.equalsIgnoreCase("secretLevel"))) {
						secretField = "1";
						break;
					}
				}

				res.setHaveSecret(secretField);
				res.setIsSecret("0");
				result.add(res);
			}
		}
		return ListUtils.unmodifiableList(result);
	}
}
