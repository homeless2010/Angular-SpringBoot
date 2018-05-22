package com.piedpiper.platform.api.bpmdesigner;

import com.piedpiper.platform.api.bpmdesigner.dto.Group;
import com.piedpiper.platform.api.bpmdesigner.dto.Position;
import com.piedpiper.platform.api.bpmdesigner.dto.Role;
import java.util.List;

public abstract interface BpmOrgAPI {
	public abstract List<Role> getRoleListByAppId(String paramString) throws Exception;

	public abstract List<Role> getAllRoleList() throws Exception;

	public abstract List<Position> getPositionListByOrgId(String paramString) throws Exception;

	public abstract List<Position> getAllPositionList() throws Exception;

	public abstract List<Group> getSysTemGroupListByOrgId(String paramString) throws Exception;

	public abstract List<Group> getAllSysTemGroupList() throws Exception;
}
