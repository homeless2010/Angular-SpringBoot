package com.piedpiper.platform.api.commonpopup;

import com.piedpiper.platform.api.commonpopup.dto.Node;
import java.util.List;
import java.util.Map;

public abstract interface CommonSelectionAPI {
	public static final String COMMON_SELECTOR_CONST_ROOT = "root";
	public static final String COMMON_SELECTOR_CONST_ORG = "org";
	public static final String COMMON_SELECTOR_CONST_DEPT = "dept";
	public static final String COMMON_SELECTOR_CONST_COMDEPT = "dept_Comprehensive";
	public static final String COMMON_SELECTOR_CONST_SUBDEPT = "subDept";
	public static final String COMMON_SELECTOR_CONST_USER = "user";
	public static final String COMMON_SELECTOR_CONST_COMUSER = "user_Comprehensive";
	public static final String COMMON_SELECTOR_CONST_ROLE = "role";
	public static final String COMMON_SELECTOR_CONST_GROUP = "group";
	public static final String COMMON_SELECTOR_CONST_POSITION = "position";
	public static final String TREE_NODE_STATE_CLOSED = "closed";
	public static final String TREE_NODE_STATE_OPEN = "open";
	public static final String TREE_NODE_ICON_USER = "icon-user";
	public static final String TREE_NODE_ICON_DEPT = "icon-dept";
	public static final String TREE_NODE_ICON_ROOT = "icon-home";
	public static final String TREE_NODE_ICON_ORG = "icon-org";

	public abstract Node getOrgNodeBySysOrgId(String paramString1, String paramString2, String paramString3,
			Map<String, Object> paramMap);

	public abstract Node getOrgNode(String paramString1, String paramString2, Map<String, Object> paramMap);

	public abstract List<Node> getSysDeptNodesByOrgId(String paramString1, String paramString2, String paramString3,
			Map<String, Object> paramMap);
}
