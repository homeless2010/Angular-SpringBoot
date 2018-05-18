package com.piedpiper.platform.core.properties;
import java.util.HashMap;
import java.util.Map;

public class PlatformConstant {
	public static final String IS_CAS_LOGIN = "CasLogin";
	public static final String NOT_LANGUAGE_CODE_IDENTITY = "_c";
	public static final String COOKIE_CURRENT_LANGUAGE_CODE = "P_L_CODE";
	public static final String DEFAULT_RES_FALG = "avicit.security.defaultPageAuthIsGranted";
	public static final String CASS_FULL_AUTH = "avicit.security.permissCassFullAuth";
	public static final String MATCH_UPPER_LOGINAME = "avicit.security.matchUpperCase";
	public static final String CASS_FULL_FLAG = "?";
	public static final String DEFAULT_SLAT = "salt_password_flag";
	public static final String DEFAULT_PERMINSS = "prohibit_shiroPermiss_flag";
	public static final String D_LANGUAGE = "zh_CN";
	public static Map<String, String> opTypeMap = new HashMap() {
		private static final long serialVersionUID = 1L;
	};

	public static Map<String, String> opResultMap = new HashMap() {
		private static final long serialVersionUID = 9089728233340532696L;
	};

	public static Map<String, String> logTypeMap = new HashMap() {
		private static final long serialVersionUID = -1551575550530338892L;
	};
	public static final String USER_STATUS_INVALID = "3";
	public static final String USER_STATUS_NORMAL = "1";
	public static final String USER_LOCK_NORMAL = "0";
	public static final String USER_LOCK_LOCK_DEATH = "3";
	public static final String USER_LOCK_LOCK_LIVE = "2";
	public static final String USER_LOCK_LOCK_DEATH_OUT = "1";
	public static final String USER_LOCK_REGISTER = "9";
	public static final String USER_LOCK_REGISTER_FAIL = "8";
	public static final String PSD_TMPLET_KEY_HOWTIMELOCK = "howTimeLock";
	public static final String PSD_TMPLET_KEY_TIPBEFORETIME = "tipBeforeTime";
	public static final String PSD_TMPLET_KEY_HOWLONGLIMITTOLOCK = "howLongLimitToLock";
	public static final String PSD_TMPLET_KEY_HOWLONGMODIFY = "howLongModify";
	public static final String PSD_TMPLET_KEY_MODIFYDEFAULT = "modifyDefault";
	public static final String PSD_TMPLET_KEY_FIRSTLOGIN = "firstLogin";
	public static final String PSD_TMPLET_KEY_DISTINCTBEFORE = "distinctBefore";
	public static final String PSD_TMPLET_KEY_DIFFERENCE = "difference";
	public static final String PSD_TMPLET_KEY_MAXLENGTH = "maxlength";
	public static final String PSD_TMPLET_KEY_MINLENGTH = "minlength";
	public static final String PSD_TMPLET_KEY_INTENSITY = "intensity";
	public static final String MENU_DISPLAY_TAB_ICON = "PLATFORM_V6_DISPLAY_TAB_ICON";
	public static final String MENU_DISPLAY_ICON_TIPS = "PLATFORM_V6_DISPLAY_ICON_TIPS";
	public static final String MENU_DISPLAY_DIV_ICON = "PLATFORM_V6_DISPLAY_DIV_ICON";
	public static final String MENU_PMO_FIRST_TITLE_ICON = "PLATFORM_V6_PMO_FIRST_TITLE_ICON";
	public static final String MENU_PMO_SECONDE_TITLE_ICON = "PLATFORM_V6_PMO_SECONDE_TITLE_ICON";
	public static final String MENU_DEFAULT_ICON_IMAGE = "PLATFORM_V6_DEFAULT_ICON_IMAGE";
	public static final String MENU_DISPLAY_ICON_DESC = "PLATFORM_V6_DISPLAY_ICON_DESC";
	public static final String MENU_NORMAL = "1";
	public static final String MENU_PORTAL = "2";

	public static abstract enum ExtendPermissType {
		ex_report;

		public abstract String getAppid();
	}
	public static enum UseageModifier {
		publicLevel, privateLevel;
	}
	public static enum RecordType {
		system, application;
	}
	public static abstract enum FixedRole {
		general_user, platform_manager, system_manager, safety_manager, safety_auditor;

		protected final String code;
		private String[] codes = PlatformProperties.getProperty("platform.unableModify.system.sysRole").split(",");

		public abstract String getCode();
	}

	public static enum LogType {
		module_operate, system_manage, safety_manage, safety_audit;
	}

	public static enum OpResult {
		success, failure;
	}

	public static enum OpType {
		login, insert, delete, update, select, logout;
	}
}