/*
SQLyog Ultimate v9.10 
MySQL - 5.1.62-community : Database - piedpiper
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `bpm_access` */

CREATE TABLE `bpm_access` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `TARGETTYPE_` varchar(50) DEFAULT NULL COMMENT '设置权限设置的目标，可能的类型有：部门(DEPT)、角色(ROLE)、群组(GROUP)、岗位(POS)、用户(USER)\r\nTARGET_TYPE字段与TARGET_ID字段联合使用，当TARGET_TYPE存储不同的值时，TARGET_ID存储响应的类型的ID值。\r\n类型优先级如下：用户(USER) > 角色(ROLE) > 部门(DEPT) > 岗位(POS) > 群组(GROUP) > ',
  `TARGETID_` varchar(255) DEFAULT NULL COMMENT '根据TARGET_TYPE的不同的值存储不同的内容，具体内容如下：\r\nTARGET_TYPE的值， TARGET_ID存储的值\r\nROLE              ROLE_ID\r\nDEPT             DEPT_ID\r\n等等',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT '流程部署ID',
  `ACCESS_` varchar(50) DEFAULT NULL COMMENT '是否可以启动流程',
  `TYPE_` varchar(50) DEFAULT NULL COMMENT '权限类型，备用',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程启动权限表';

/*Table structure for table `bpm_button` */

CREATE TABLE `bpm_button` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键id',
  `BUTTON_CODE_` varchar(100) DEFAULT NULL COMMENT '按钮代码',
  `BUTTON_DEFAULT_NAME_` varchar(100) DEFAULT NULL COMMENT '按钮默认名称',
  `BUTTON_NAME_` varchar(100) DEFAULT NULL COMMENT '按钮名称',
  `BUTTON_SPECIAL_STEP_NAME_` text COMMENT '按钮在特定节点中的名称',
  `BUTTON_DESCRIPTION_` text COMMENT '按钮描述',
  `BUTTON_ORDER_` varchar(50) DEFAULT NULL COMMENT '按钮顺序',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程按钮名称定义表';

/*Table structure for table `bpm_catalog` */

CREATE TABLE `bpm_catalog` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `PARENT_ID_` varchar(50) NOT NULL COMMENT '业务目录父目录',
  `NAME_` text NOT NULL COMMENT '业务目录名称',
  `CODE_` varchar(100) DEFAULT NULL COMMENT '业务目录代码',
  `REMARK_` text COMMENT '备注',
  `ORDER_BY_` decimal(16,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程业务目录表';

/*Table structure for table `bpm_catalog_process_relation` */

CREATE TABLE `bpm_catalog_process_relation` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `BUSSINESS_CATALOG_DBID_` varchar(50) NOT NULL COMMENT '业务目录表DBID_',
  `BPM_PROCESS_KEY_` varchar(50) NOT NULL COMMENT '流程文件KEY',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程业务目录与流程关系表';

/*Table structure for table `bpm_class` */

CREATE TABLE `bpm_class` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `NAME_` varchar(100) NOT NULL COMMENT '类名',
  `PATH_` text NOT NULL COMMENT '类路径',
  `TYPE_` varchar(100) DEFAULT NULL COMMENT '类型',
  `REMARK_` text COMMENT '备注',
  `ORDER_BY_` decimal(16,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Java类表';

/*Table structure for table `bpm_class_method` */

CREATE TABLE `bpm_class_method` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `BPM_CLASS_DBID_` varchar(50) NOT NULL COMMENT 'BPM_CLASS表主键',
  `METHOD_` text NOT NULL COMMENT '方法名',
  `RETURN_` varchar(100) NOT NULL COMMENT '返回值',
  `REMARK_` text COMMENT '备注',
  `ORDER_BY_` decimal(16,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Java类中含有的方法表';

/*Table structure for table `bpm_class_properties` */

CREATE TABLE `bpm_class_properties` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `THIRD_PARTY_DBID_` varchar(50) NOT NULL COMMENT 'BPM_CLASS或BPM_CLASS_METHOD表主键',
  `SORT_` varchar(100) NOT NULL COMMENT '属性种类(field或arg)',
  `NAME_` varchar(100) NOT NULL COMMENT '属性名',
  `TYPE_` varchar(100) NOT NULL COMMENT '属性类型',
  `INIT_EXPR_` text COMMENT '属性默认值',
  `REMARK_` text COMMENT '备注',
  `ORDER_BY_` decimal(16,0) NOT NULL COMMENT '排序',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Java类中含有的属性或方法中传递的参数表';

/*Table structure for table `bpm_configuration` */

CREATE TABLE `bpm_configuration` (
  `DBID_` varchar(32) NOT NULL COMMENT '主键',
  `NAME_` varchar(200) DEFAULT NULL COMMENT 'CLASS类名',
  `CLASS_` text COMMENT 'CLASS包路径',
  `TYPE_` varchar(10) DEFAULT NULL COMMENT '1-处理类型、2-路由条件、3-自定义选人',
  `REMARK_` text COMMENT '描述',
  `ORDER_BY_` decimal(16,0) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程预、后处理函数等的配置';

/*Table structure for table `bpm_cross_net_instance` */

CREATE TABLE `bpm_cross_net_instance` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `STATUS_` varchar(30) DEFAULT NULL COMMENT '实例导出状态',
  `ID_` varchar(50) DEFAULT NULL COMMENT '流程实例ID',
  `ATTRIBUTE_00` varchar(50) DEFAULT NULL COMMENT '保留属性00',
  `ATTRIBUTE_01` varchar(50) DEFAULT NULL COMMENT '保留属性01',
  `ISENDED_` varchar(30) DEFAULT NULL COMMENT '标记当前实例是否已经结束',
  `HAS_EXPORTED_` varchar(30) DEFAULT NULL COMMENT '对于已结束的流程实例仅导出一次',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_deployment` */

CREATE TABLE `bpm_deployment` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `NAME_` longtext COMMENT '流程名称，暂时不启用',
  `TIMESTAMP_` decimal(19,0) DEFAULT NULL COMMENT '部署时间',
  `STATE_` varchar(255) DEFAULT NULL COMMENT '状态；active激活、suspended暂停',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '流程类型，regular常规流程、temporary临时流程',
  `DESIGNER_` varchar(255) DEFAULT NULL COMMENT '流程设计人',
  `DEPLOYER_` varchar(255) DEFAULT NULL COMMENT '流程发布人',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程部署表';

/*Table structure for table `bpm_deployprop` */

CREATE TABLE `bpm_deployprop` (
  `DBID_` varchar(50) NOT NULL,
  `DEPLOYMENT_` varchar(50) DEFAULT NULL,
  `OBJNAME_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `STRINGVAL_` varchar(255) DEFAULT NULL,
  `LONGVAL_` decimal(19,0) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_exception` */

CREATE TABLE `bpm_exception` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `INSTANCEID_` varchar(50) DEFAULT NULL COMMENT '流程实例ID',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '流程执行ID',
  `ACTIVITYID_` varchar(255) DEFAULT NULL COMMENT '当前活动节点ID',
  `ACTIVITYNAME_` varchar(255) DEFAULT NULL COMMENT '当前活动节点名称',
  `PDID_` varchar(255) DEFAULT NULL COMMENT '流程定义ID',
  `PDNAME_` varchar(255) DEFAULT NULL COMMENT '流程定义名称',
  `EXCEPTION_` longtext COMMENT '异常信息',
  `USERID_` varchar(50) DEFAULT NULL COMMENT '操作人ID',
  `USERNAME_` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `DEPTID_` varchar(50) DEFAULT NULL COMMENT '操作人部门ID',
  `DEPTNAME_` varchar(255) DEFAULT NULL COMMENT '操作人部门名称',
  `STATE_` varchar(50) DEFAULT NULL COMMENT '状态（备用是否标记）',
  `TIME_` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程异常记录表';

/*Table structure for table `bpm_execution` */

CREATE TABLE `bpm_execution` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据version',
  `ACTIVITYNAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PROCDEFID_` varchar(255) DEFAULT NULL COMMENT '流程部署的pdid',
  `HASVARS_` decimal(1,0) DEFAULT NULL COMMENT '是否使用流程变量；否=0、是=1',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '目前只在foreach和fork中用到，记录只要的name',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '启动流程时候手工指定的，暂时不启用',
  `ID_` varchar(255) DEFAULT NULL COMMENT 'bpm_deployprop 表中key 是 dbid的值',
  `STATE_` varchar(255) DEFAULT NULL COMMENT '状态',
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL COMMENT '暂停的状态；suspended',
  `PRIORITY_` decimal(10,0) DEFAULT NULL COMMENT '优先级，暂时没有用到',
  `HISACTINST_` varchar(50) DEFAULT NULL COMMENT 'bpm_hist_actinst表dbid_',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_execution表dbid_记录父关系',
  `INSTANCE_` varchar(50) DEFAULT NULL COMMENT 'bpm_hist_procinst表dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `SUBPROCINST_` varchar(50) DEFAULT NULL COMMENT '子流程dbid_',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '子execution的索引',
  `ACTIVITYALIAS_` varchar(255) DEFAULT NULL COMMENT '节点中文名称',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_ext_form_security` */

CREATE TABLE `bpm_ext_form_security` (
  `ID` varchar(50) NOT NULL COMMENT '系统编号 : 系统编号 : 系统编号 : 系统编号',
  `PD_ID` varchar(100) DEFAULT NULL COMMENT '流程模版ID : 流程模版ID : 流程模版ID : 流程模版ID',
  `PD_ACTIVITY_NAME` varchar(60) DEFAULT NULL COMMENT '流程节点名称 : 流程节点名称 : 流程节点名称 : 流程节点名称',
  `TAG` varchar(200) DEFAULT NULL COMMENT '控件的Tag属性的值 : 控件的SecurityTag属性的值',
  `TAG_NAME` varchar(200) DEFAULT NULL COMMENT '控件的名称',
  `ACCESSIBILITY` decimal(38,0) DEFAULT NULL COMMENT '可访问性，用来表示一个资源是否可以访问的属性，\r\n对于菜单来说，代表该菜单是否可以访问\r\n\r\n对于URL来说，代表是否可以访问该URL\r\n\r\n对于按钮来说，代表是否显隐\r\n对于表单列和表格列来说，代表是否显隐\r\n\r\n1-允许访问\r\n0-禁止访问',
  `OPERABILITY` decimal(38,0) DEFAULT NULL COMMENT '可操作性，用来表示一个资源是否可以操作的属性，\r\n对于菜单和URL来说，没有可操作性的属性\r\n对于按钮来说，可操作性是指按钮是否可以点击\r\n对于表单列和表格列来说，可操作性是指是否可以更改其中的数据\r\n\r\n1-允许操作\r\n0-禁止操作',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `FORM_CODE` varchar(50) DEFAULT NULL COMMENT '所属全局表单CODE',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程表单权限表 ';

/*Table structure for table `bpm_ext_next_activity` */

CREATE TABLE `bpm_ext_next_activity` (
  `ID` varchar(50) NOT NULL COMMENT '系统编号 : 系统编号 : 系统编号 : 系统编号',
  `ATTRIBUTE_ID` varchar(50) DEFAULT NULL COMMENT '任务属性ID',
  `ACTIVITY_NAME` varchar(50) DEFAULT NULL COMMENT '节点名称 : 节点名称',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='跳转节点定义';

/*Table structure for table `bpm_ext_process_attribute` */

CREATE TABLE `bpm_ext_process_attribute` (
  `ID` varchar(50) NOT NULL COMMENT '系统编号 : 系统编号 : 系统编号 : 系统编号',
  `PD_ID` varchar(100) DEFAULT NULL COMMENT '流程模版ID : 流程模版ID : 流程模版ID : 流程模版ID',
  `PROXY_PAGE` varchar(2) DEFAULT NULL COMMENT '是否由系统通用审批页面托管审批功能(Y:支持,N:不支持):如果允许，则设置的页面将被嵌入到系统通用审批页面中',
  `REMARK` text COMMENT '描述 ',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='BPM_EXT_PROCESS_ATTRIBUTE : 流程属性表 : 用来为存储流程的扩展信息';

/*Table structure for table `bpm_ext_task_attribute` */

CREATE TABLE `bpm_ext_task_attribute` (
  `ID` varchar(50) NOT NULL COMMENT '系统编号 : 系统编号 : 系统编号 : 系统编号',
  `PD_ID` varchar(100) DEFAULT NULL COMMENT '流程模版ID : 流程模版ID : 流程模版ID : 流程模版ID',
  `PD_ACTIVITY_NAME` varchar(60) DEFAULT NULL COMMENT '流程节点名称 : 流程节点名称 : 流程节点名称 : 流程节点名称',
  `URL` varchar(200) DEFAULT NULL COMMENT '绑定的任务表单所在页面的URL : 绑定的任务表单所在页面的URL : 绑定的任务表单所在页面的URL : 绑定的任务表单所在页面的URL',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '是全局表单还是节点表单，global，activity',
  `REMARK` text COMMENT 'URL参数',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `PROXY_PAGE` varchar(2) DEFAULT NULL COMMENT '是否由系统通用审批页面托管审批功能(Y:支持,N:不支持):如果允许，则设置的页面将被嵌入到系统通用审批页面中',
  `FORM_ID` varchar(50) DEFAULT NULL COMMENT 'bpm_form表的ID',
  `FORM_CODE` varchar(50) DEFAULT NULL COMMENT '所属全局表单CODE',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务属性表 : 用来为任务节点的任务的处理方式及节点对应表单等属性信息';

/*Table structure for table `bpm_focused_task` */

CREATE TABLE `bpm_focused_task` (
  `TASK_DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `OUTCOME_` varchar(255) DEFAULT NULL COMMENT 'Transition的name属性值',
  `ASSIGNEE_` varchar(255) DEFAULT NULL COMMENT '分派的处理人',
  `PRIORITY_` decimal(10,0) DEFAULT NULL COMMENT '优先级',
  `STATE_` varchar(255) DEFAULT NULL COMMENT '状态',
  `CREATE_` datetime DEFAULT NULL COMMENT '创建时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `SUPERTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `TASK_ORDER_` varchar(255) DEFAULT NULL COMMENT '多人顺序处理 记录的顺序',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `TAKE_STATE_` varchar(255) DEFAULT NULL COMMENT 'Task的状态',
  `PROCINST_` varchar(50) DEFAULT NULL COMMENT 'bpm_hist_procinst表id _',
  `DESCR_` longtext COMMENT 'Description属性值',
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL COMMENT '流程暂停状态',
  `TASK_TYPE_` varchar(255) DEFAULT NULL COMMENT '待办=0;待阅=1',
  `TASK_B_ID_` varchar(255) DEFAULT NULL COMMENT '业务数据id',
  `TASK_TITLE_` varchar(255) DEFAULT NULL COMMENT '待办标题',
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL COMMENT '待办发送人',
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL COMMENT '待办发送部门',
  `FORM_` varchar(255) DEFAULT NULL COMMENT '待办url',
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义name',
  `TASK_FINISHED_` varchar(255) DEFAULT NULL COMMENT '标志完成 0 未办 1 已办',
  `TASK_STATE_` varchar(255) DEFAULT NULL COMMENT '待办状态 0无效 1 有效',
  `TASK_NAME_` varchar(255) DEFAULT NULL COMMENT '节点唯一标识名称',
  `HISACTINST_` varchar(50) DEFAULT NULL COMMENT 'bpm_hist_actinst表dbid_',
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL COMMENT '工作移交类型',
  `WORKHAND_USER_` varchar(255) DEFAULT NULL COMMENT '工作移交参与人',
  `APP_ID_` varchar(255) DEFAULT NULL COMMENT '应用id',
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL COMMENT '分派人部门',
  PRIMARY KEY (`TASK_DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_forms` */

CREATE TABLE `bpm_forms` (
  `ID` varchar(50) NOT NULL COMMENT '表ID',
  `FORM_CODE` varchar(50) NOT NULL COMMENT '表单代码',
  `FORM_NAME` varchar(200) NOT NULL COMMENT '表单名称',
  `FORM_URL` text NOT NULL COMMENT '表单URL地址，流程引擎根据这个地址打开相应的表单',
  `FORM_TYPE` varchar(10) NOT NULL COMMENT '表单类型，表单类型有：\r\nD7—— 表示基于Dorado 7开发模块\r\nD5—— 表示基于Dorado 5开发的模块\r\nURI——表示该表单是一个Web 的URI（比如JSP、Servlet地址、Spring MVC地址、Action地址等）\r\n\r\n不同的表单类型，流程引擎根据表单类型传递给不同的流程引擎参数',
  `APP_ID` varchar(50) NOT NULL COMMENT '应用ID，表示该表单属于哪个应用',
  `VALID_FLAG` varchar(2) NOT NULL COMMENT '有效标示。1-有效，0和空表示无效。默认1',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单定义表，保存在流程中运行的表单信息。';

/*Table structure for table `bpm_froms_relation` */

CREATE TABLE `bpm_froms_relation` (
  `ID` varchar(50) NOT NULL COMMENT '表ID',
  `DEPLOYMENT_ID` varchar(50) NOT NULL COMMENT '流程发布表的ID',
  `FORM_ID` varchar(50) NOT NULL COMMENT '表单表的ID',
  `VALID_FLAG` varchar(2) NOT NULL COMMENT '有效标示。1-有效，0和空表示无效。默认1',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程与表单的关系表，只有发布后的流程才能与表单挂接';

/*Table structure for table `bpm_hist_actinst` */

CREATE TABLE `bpm_hist_actinst` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext1` */

CREATE TABLE `bpm_hist_actinst_ext1` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext2` */

CREATE TABLE `bpm_hist_actinst_ext2` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext3` */

CREATE TABLE `bpm_hist_actinst_ext3` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext4` */

CREATE TABLE `bpm_hist_actinst_ext4` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext5` */

CREATE TABLE `bpm_hist_actinst_ext5` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext6` */

CREATE TABLE `bpm_hist_actinst_ext6` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext7` */

CREATE TABLE `bpm_hist_actinst_ext7` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_ext8` */

CREATE TABLE `bpm_hist_actinst_ext8` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id；bpm_hist_procinst表dbid_',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '节点类型',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表activity_name_',
  `START_` datetime DEFAULT NULL COMMENT '开始时间',
  `END_` datetime DEFAULT NULL COMMENT '结束时间',
  `DURATION_` decimal(19,0) DEFAULT NULL COMMENT '持续时间',
  `TRANSITION_` varchar(255) DEFAULT NULL COMMENT '转移(Transition)的name值',
  `NEXTIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `DEAL_TYPE_` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `ALIAS_` varchar(255) DEFAULT NULL COMMENT '节点别名',
  `HIST_ACTI_PRE_` varchar(50) DEFAULT NULL COMMENT '上一节点dbid_',
  `SUPEREXEC_` varchar(50) DEFAULT NULL COMMENT '主流程dbid_',
  `ISINGROUP_` varchar(10) DEFAULT NULL COMMENT '该节点是否在group中',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev` */

CREATE TABLE `bpm_hist_actinst_prev` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext1` */

CREATE TABLE `bpm_hist_actinst_prev_ext1` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext2` */

CREATE TABLE `bpm_hist_actinst_prev_ext2` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext3` */

CREATE TABLE `bpm_hist_actinst_prev_ext3` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext4` */

CREATE TABLE `bpm_hist_actinst_prev_ext4` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext5` */

CREATE TABLE `bpm_hist_actinst_prev_ext5` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext6` */

CREATE TABLE `bpm_hist_actinst_prev_ext6` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext7` */

CREATE TABLE `bpm_hist_actinst_prev_ext7` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_actinst_prev_ext8` */

CREATE TABLE `bpm_hist_actinst_prev_ext8` (
  `DBID_` varchar(50) NOT NULL,
  `PREVIOUSID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_detail` */

CREATE TABLE `bpm_hist_detail` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `USERID_` varchar(255) DEFAULT NULL,
  `TIME_` datetime DEFAULT NULL,
  `HPROCI_` varchar(50) DEFAULT NULL,
  `HPROCIIDX_` decimal(10,0) DEFAULT NULL,
  `HACTI_` varchar(50) DEFAULT NULL,
  `HACTIIDX_` decimal(10,0) DEFAULT NULL,
  `HTASK_` varchar(50) DEFAULT NULL,
  `HTASKIDX_` decimal(10,0) DEFAULT NULL,
  `HVAR_` varchar(50) DEFAULT NULL,
  `HVARIDX_` decimal(10,0) DEFAULT NULL,
  `MESSAGE_` longtext,
  `OLD_STR_` varchar(255) DEFAULT NULL,
  `NEW_STR_` varchar(255) DEFAULT NULL,
  `OLD_INT_` decimal(10,0) DEFAULT NULL,
  `NEW_INT_` decimal(10,0) DEFAULT NULL,
  `OLD_TIME_` datetime DEFAULT NULL,
  `NEW_TIME_` datetime DEFAULT NULL,
  `PARENT_` varchar(50) DEFAULT NULL,
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_procinst` */

CREATE TABLE `bpm_hist_procinst` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `PROCDEFID_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `ENDACTIVITY_` varchar(255) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `FORMID_` varchar(50) DEFAULT NULL,
  `TITLE_` varchar(255) DEFAULT NULL,
  `USERID_` varchar(255) DEFAULT NULL,
  `DEPTID_` varchar(255) DEFAULT NULL,
  `BUSINESSSTATE_` varchar(255) DEFAULT NULL,
  `FORM_CODE_` varchar(50) DEFAULT NULL COMMENT '所属全局表单CODE',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task` */

CREATE TABLE `bpm_hist_task` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext1` */

CREATE TABLE `bpm_hist_task_ext1` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext2` */

CREATE TABLE `bpm_hist_task_ext2` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext3` */

CREATE TABLE `bpm_hist_task_ext3` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext4` */

CREATE TABLE `bpm_hist_task_ext4` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext5` */

CREATE TABLE `bpm_hist_task_ext5` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext6` */

CREATE TABLE `bpm_hist_task_ext6` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext7` */

CREATE TABLE `bpm_hist_task_ext7` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_task_ext8` */

CREATE TABLE `bpm_hist_task_ext8` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `NEXTIDX_` decimal(10,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `DESCR_` longtext,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `IS_READTODO_` varchar(2) DEFAULT NULL COMMENT '标识业务传阅节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var` */

CREATE TABLE `bpm_hist_var` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext1` */

CREATE TABLE `bpm_hist_var_ext1` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext2` */

CREATE TABLE `bpm_hist_var_ext2` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext3` */

CREATE TABLE `bpm_hist_var_ext3` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext4` */

CREATE TABLE `bpm_hist_var_ext4` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext5` */

CREATE TABLE `bpm_hist_var_ext5` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext6` */

CREATE TABLE `bpm_hist_var_ext6` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext7` */

CREATE TABLE `bpm_hist_var_ext7` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_hist_var_ext8` */

CREATE TABLE `bpm_hist_var_ext8` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `PROCINSTID_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `EXECUTIONID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `VARNAME_` varchar(255) DEFAULT NULL COMMENT 'bpm_variable表key_',
  `VALUE_` text COMMENT 'bpm_variable表value_',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT 'bpm_task表dbid_',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_idea_style` */

CREATE TABLE `bpm_idea_style` (
  `ID` varchar(50) NOT NULL COMMENT '表ID',
  `TITLE` varchar(50) NOT NULL COMMENT '标题',
  `CODE` varchar(50) NOT NULL COMMENT '代码',
  `ORDERBY` varchar(50) NOT NULL COMMENT '顺序',
  `TYPE` varchar(10) NOT NULL COMMENT '类型',
  `POSITION` text COMMENT '职位',
  `PROCESSDEFINITIONID` varchar(50) NOT NULL COMMENT '流程ID',
  `ACTIVITYALIAS` text NOT NULL COMMENT '显示的节点别名',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ACTIVITYNAME` text COMMENT '显示的节点名称',
  `STYLENAME` varchar(255) DEFAULT NULL COMMENT '样式名称',
  `QUOTEID` text COMMENT '引用ID',
  `SHOWSIGN` varchar(10) DEFAULT NULL COMMENT '是否显示电子签名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_job` */

CREATE TABLE `bpm_job` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `DUEDATE_` datetime DEFAULT NULL COMMENT 'Timer节点的duedate的属性值',
  `STATE_` varchar(255) DEFAULT NULL COMMENT '状态；Waiting、acquired、 error 、suspended',
  `ISEXCLUSIVE_` decimal(1,0) DEFAULT NULL COMMENT '是否高级功能 如：异步调用是高级功能、timer不是高级功能',
  `LOCKOWNER_` varchar(255) DEFAULT NULL COMMENT '执行者',
  `LOCKEXPTIME_` datetime DEFAULT NULL COMMENT '执行时间',
  `EXCEPTION_` longtext COMMENT '异常',
  `RETRIES_` decimal(10,0) DEFAULT NULL COMMENT '重复次数',
  `PROCESSINSTANCE_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT 'bpm_execution表id_',
  `CFG_` varchar(50) DEFAULT NULL COMMENT 'bpm_lob表dbid_',
  `SIGNAL_` varchar(255) DEFAULT NULL COMMENT 'timer中的signal属性值',
  `EVENT_` varchar(255) DEFAULT NULL COMMENT 'timer中的event属性值',
  `REPEAT_` varchar(255) DEFAULT NULL COMMENT 'timer中的repeat属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob` */

CREATE TABLE `bpm_lob` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext1` */

CREATE TABLE `bpm_lob_ext1` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext2` */

CREATE TABLE `bpm_lob_ext2` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext3` */

CREATE TABLE `bpm_lob_ext3` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext4` */

CREATE TABLE `bpm_lob_ext4` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext5` */

CREATE TABLE `bpm_lob_ext5` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext6` */

CREATE TABLE `bpm_lob_ext6` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext7` */

CREATE TABLE `bpm_lob_ext7` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_lob_ext8` */

CREATE TABLE `bpm_lob_ext8` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `BLOB_VALUE_` longblob COMMENT '发布的流程定义文件',
  `DEPLOYMENT_` varchar(50) DEFAULT NULL COMMENT 'bpm_deployment表dbid_',
  `FIGURE_VALUE_` longblob COMMENT '发布的流程图文件',
  `NAME_` varchar(255) DEFAULT NULL COMMENT '流程定义文件中的key属性值',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_operate_desc` */

CREATE TABLE `bpm_operate_desc` (
  `DBID_` varchar(50) NOT NULL,
  `KEYID_` varchar(50) DEFAULT NULL,
  `OPE_DESC` varchar(150) DEFAULT NULL,
  `ATTRIBUTE01` varchar(50) DEFAULT NULL,
  `ATTRIBUTE02` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_participation` */

CREATE TABLE `bpm_participation` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `GROUPID_` varchar(255) DEFAULT NULL COMMENT '暂时不启用',
  `USERID_` varchar(255) DEFAULT NULL COMMENT '处理人',
  `TYPE_` varchar(255) DEFAULT NULL COMMENT '处理人类型',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '任务dbid_',
  `SWIMLANE_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_process_def` */

CREATE TABLE `bpm_process_def` (
  `DBID_` varchar(32) NOT NULL COMMENT '模型主键',
  `KEY_` varchar(100) NOT NULL COMMENT '模型唯一标识',
  `NAME_` varchar(100) NOT NULL COMMENT '模型名称',
  `XML_` longblob NOT NULL COMMENT '模型文件',
  `PNG_` longblob NOT NULL COMMENT '模型图片',
  `DESC_` text COMMENT '模型描述',
  `STATE_` varchar(10) NOT NULL COMMENT '模型状态',
  `APP_` varchar(100) NOT NULL COMMENT '模型所属应用',
  `DEPLOYMENT_` varchar(100) DEFAULT NULL COMMENT '模型发布后的流程定义ID',
  `DESIGNER_` varchar(100) DEFAULT NULL COMMENT '模型设计人',
  `DEPLOYER_` varchar(100) DEFAULT NULL COMMENT '模型发布人',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程模型定义表，用于保存未发布前的流程定义模型';

/*Table structure for table `bpm_processdef_rel` */

CREATE TABLE `bpm_processdef_rel` (
  `DBID_` varchar(32) NOT NULL COMMENT '关系主键',
  `PROCESSDEF_KEY_` varchar(32) NOT NULL COMMENT '模型key',
  `SUBPROCESSDEF_KEY_` varchar(32) NOT NULL COMMENT '子模型key',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程模型定义关系表，用于保存流程定义模型主子关系';

/*Table structure for table `bpm_property` */

CREATE TABLE `bpm_property` (
  `KEY_` varchar(255) NOT NULL COMMENT 'next.dbid',
  `VERSION_` decimal(10,0) NOT NULL COMMENT '版本',
  `VALUE_` varchar(255) DEFAULT NULL COMMENT '产生的dbid_',
  PRIMARY KEY (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_select_person` */

CREATE TABLE `bpm_select_person` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `ENTRY_ID` varchar(32) NOT NULL COMMENT '流程实例ID',
  `STEP_ID` varchar(32) NOT NULL COMMENT '流程步骤ID',
  `OPERATION_USER_ID` varchar(32) NOT NULL COMMENT '流程选人修改人',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '最近修改的IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `SELECT_USER_ID` longtext COMMENT '步骤操作人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义选人配置表';

/*Table structure for table `bpm_swimlane` */

CREATE TABLE `bpm_swimlane` (
  `DBID_` varchar(50) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task` */

CREATE TABLE `bpm_task` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext1` */

CREATE TABLE `bpm_task_ext1` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext2` */

CREATE TABLE `bpm_task_ext2` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext3` */

CREATE TABLE `bpm_task_ext3` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext4` */

CREATE TABLE `bpm_task_ext4` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext5` */

CREATE TABLE `bpm_task_ext5` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext6` */

CREATE TABLE `bpm_task_ext6` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext7` */

CREATE TABLE `bpm_task_ext7` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_task_ext8` */

CREATE TABLE `bpm_task_ext8` (
  `DBID_` varchar(50) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` decimal(10,0) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` decimal(10,0) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` decimal(10,0) DEFAULT NULL,
  `SIGNALLING_` decimal(1,0) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` decimal(1,0) DEFAULT NULL,
  `SUPERTASK_` varchar(50) DEFAULT NULL,
  `EXECUTION_` varchar(50) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `SWIMLANE_` varchar(50) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track` */

CREATE TABLE `bpm_track` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext1` */

CREATE TABLE `bpm_track_ext1` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext2` */

CREATE TABLE `bpm_track_ext2` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext3` */

CREATE TABLE `bpm_track_ext3` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext4` */

CREATE TABLE `bpm_track_ext4` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext5` */

CREATE TABLE `bpm_track_ext5` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext6` */

CREATE TABLE `bpm_track_ext6` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext7` */

CREATE TABLE `bpm_track_ext7` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_track_ext8` */

CREATE TABLE `bpm_track_ext8` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点dbid_',
  `HTASK_` varchar(50) DEFAULT NULL COMMENT '流程任务dbid_',
  `HTASKIDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `MESSAGE_` longtext COMMENT '意见',
  `PARENT_` varchar(50) DEFAULT NULL COMMENT '暂时不启用',
  `PARENT_IDX_` decimal(10,0) DEFAULT NULL COMMENT '暂时不启用',
  `CURRENT_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '当前节点别名',
  `CURRENT_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '当前节点id',
  `PRE_ACTI_LABEL_` varchar(255) DEFAULT NULL COMMENT '前一节点别名',
  `PRE_ACTI_NAME_` varchar(255) DEFAULT NULL COMMENT '前一节点id',
  `ASSIGNEE_ID_` varchar(255) DEFAULT NULL COMMENT '处理人id',
  `ASSIGNEE_NAME_` varchar(255) DEFAULT NULL COMMENT '处理人name',
  `ASSIGNEE_DID_` varchar(255) DEFAULT NULL COMMENT '处理人部门id',
  `ASSIGNEE_DNAME_` varchar(255) DEFAULT NULL COMMENT '处理人部门name',
  `INTO_` datetime DEFAULT NULL COMMENT '进入时间',
  `OPEN_` datetime DEFAULT NULL COMMENT '打开时间',
  `END_` datetime DEFAULT NULL COMMENT '完成时间',
  `IDEA_TYPE_` varchar(255) DEFAULT NULL COMMENT '意见类型',
  `COMPEL_MANNER_` varchar(255) DEFAULT NULL COMMENT '强制表态',
  `DISPLAY_STYLE_` varchar(255) DEFAULT NULL COMMENT '意见显示方式',
  `SEND_TYPE_` varchar(255) DEFAULT NULL COMMENT '发送类型',
  `SEND_UID_` varchar(255) DEFAULT NULL COMMENT '发送人id',
  `SEND_UNAME_` varchar(255) DEFAULT NULL COMMENT '发送人name',
  `SEND_DID_` varchar(255) DEFAULT NULL COMMENT '发送人部门id',
  `SEND_DNAME_` varchar(255) DEFAULT NULL COMMENT '发送人部门name',
  `OP_TYPE_` varchar(255) DEFAULT NULL COMMENT '办理类型',
  `OP_UID_` varchar(255) DEFAULT NULL COMMENT '办理人id',
  `OP_UNAME_` varchar(255) DEFAULT NULL COMMENT '办理人name',
  `OP_DID_` varchar(255) DEFAULT NULL COMMENT '办理人部门id',
  `OP_DNAME_` varchar(255) DEFAULT NULL COMMENT '办理人部门name',
  `COMMENT_` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable` */

CREATE TABLE `bpm_variable` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext1` */

CREATE TABLE `bpm_variable_ext1` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext2` */

CREATE TABLE `bpm_variable_ext2` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext3` */

CREATE TABLE `bpm_variable_ext3` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext4` */

CREATE TABLE `bpm_variable_ext4` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext5` */

CREATE TABLE `bpm_variable_ext5` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext6` */

CREATE TABLE `bpm_variable_ext6` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext7` */

CREATE TABLE `bpm_variable_ext7` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_variable_ext8` */

CREATE TABLE `bpm_variable_ext8` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `CLASS_` varchar(255) NOT NULL COMMENT '数据记录类型',
  `DBVERSION_` decimal(10,0) NOT NULL COMMENT '数据记录版本',
  `KEY_` varchar(255) DEFAULT NULL COMMENT '流程变量的key',
  `CONVERTER_` varchar(255) DEFAULT NULL COMMENT '标识lob类型',
  `HIST_` decimal(1,0) DEFAULT NULL COMMENT '是否保存的历史变量',
  `EXECUTION_` varchar(50) DEFAULT NULL COMMENT '流程实例',
  `TASK_` varchar(50) DEFAULT NULL COMMENT '流程任务',
  `LOB_` varchar(50) DEFAULT NULL COMMENT 'LOG表关联',
  `DATE_VALUE_` datetime DEFAULT NULL COMMENT '时间类型对应的数值',
  `DOUBLE_VALUE_` double DEFAULT NULL COMMENT 'double类型对应的数值',
  `CLASSNAME_` varchar(255) DEFAULT NULL COMMENT 'class类型对应的数值',
  `LONG_VALUE_` decimal(19,0) DEFAULT NULL COMMENT 'long类型对应的数值',
  `STRING_VALUE_` text COMMENT 'string类型对应的数值',
  `TEXT_VALUE_` longtext COMMENT 'text类型对应的数值',
  `EXESYS_` varchar(50) DEFAULT NULL COMMENT '系统变量',
  `HACTI_` varchar(50) DEFAULT NULL COMMENT '流程历史节点',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_waring` */

CREATE TABLE `bpm_waring` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键ID',
  `WARING_TYPE_` varchar(100) NOT NULL COMMENT '预警类型',
  `VALUE_` varchar(100) NOT NULL COMMENT '预警阀值',
  `SEND_TYPE01_` varchar(50) DEFAULT NULL COMMENT '是否发送给自己',
  `SEND_TYPE02_` varchar(50) DEFAULT NULL COMMENT '是否发送给他人',
  `OTHER_MAILS_` text COMMENT '其他邮件地址',
  `JOB_RULE_` varchar(100) DEFAULT NULL COMMENT '定时任务规则',
  `JOB_CLASS_` text COMMENT '定时任务处理类',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程预警';

/*Table structure for table `bpm_work_hand_task` */

CREATE TABLE `bpm_work_hand_task` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `HPROCI_` varchar(50) DEFAULT NULL COMMENT '流程实例id',
  `EXECUTION_` varchar(255) DEFAULT NULL COMMENT 'bpm_execution表id',
  `HIST_TASK_DBID_` varchar(50) DEFAULT NULL COMMENT '历史任务dbid',
  `TRACK_DBID_` varchar(50) DEFAULT NULL COMMENT '流程跟踪dbid',
  `WORK_OWNER_ID_` varchar(50) DEFAULT NULL COMMENT '工作移交人',
  `RECEPT_USER_ID_` varchar(50) DEFAULT NULL COMMENT '工作移交接收人',
  `HAND_TASK_DATE_` datetime DEFAULT NULL COMMENT '移交时间',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_work_hand_task_pass` */

CREATE TABLE `bpm_work_hand_task_pass` (
  `DBID_` varchar(50) NOT NULL COMMENT '主键',
  `WORK_HAND_TASK_` varchar(50) DEFAULT NULL COMMENT '移交主表dbid',
  `HIST_TASK_DBID_` varchar(50) DEFAULT NULL COMMENT '历史任务dbid',
  `WORK_OWNER_ID_` varchar(50) DEFAULT NULL COMMENT '工作移交人',
  `RECEPT_USER_ID_` varchar(50) DEFAULT NULL COMMENT '工作接收人',
  `ORDER_BY_` decimal(19,0) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eform_custom_button` */

CREATE TABLE `eform_custom_button` (
  `ID` varchar(50) NOT NULL,
  `BUTTON_NAME` varchar(50) DEFAULT NULL,
  `BUTTON_URL` text,
  `TABLE_ID` varchar(50) DEFAULT NULL,
  `IS_PAGE` varchar(50) DEFAULT NULL,
  `BUTTON_ICON` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `VERSION` decimal(16,0) DEFAULT NULL,
  `BUTTON_ORDER` decimal(16,0) DEFAULT NULL,
  `PRE_JS` text,
  `EVENT_CLASS` varchar(200) DEFAULT NULL,
  `POST_JS` text,
  `IS_DEFAULT` varchar(10) DEFAULT NULL,
  `BUTTON_CODE` varchar(50) DEFAULT NULL,
  `REMARK` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eform_tab_col_code` */

CREATE TABLE `eform_tab_col_code` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID',
  `CODE_ORDER` decimal(65,30) NOT NULL COMMENT '顺序',
  `LOOKUP_NAME` varchar(128) NOT NULL COMMENT '显示值',
  `LOOKUP_CODE` varchar(128) NOT NULL COMMENT '真实值；手动输入，默认与显示值一样',
  `CODE_IS_VALID` varchar(2) NOT NULL COMMENT '是否有效；Y-有效，N-无效',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码维护';

/*Table structure for table `eform_tab_col_config_group` */

CREATE TABLE `eform_tab_col_config_group` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SOURCE_COLUMN_ID` varchar(50) NOT NULL COMMENT '源字段ID',
  `TARGET_COLUMN_ID` varchar(50) NOT NULL COMMENT '组合字段ID',
  `TARGET_INPUT` text COMMENT '组合输入值',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  `GROUP_ORDER` varchar(2) NOT NULL COMMENT '组合字段顺序',
  `TARGET_TABLE_ID` varchar(50) DEFAULT NULL COMMENT '目标表ID',
  `SOURCE_TABLE_ID` varchar(50) DEFAULT NULL COMMENT '源字段ID',
  `TARGET_COLUMN_OPERATION` varchar(2) DEFAULT NULL COMMENT '运算方式\r\n1：第一个；2：最后一个；3：最大的；4：最小的；5：求和',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组合项配置信息';

/*Table structure for table `eform_tab_col_index` */

CREATE TABLE `eform_tab_col_index` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID',
  `COLUMN_ID` text NOT NULL COMMENT '列ID',
  `INDEX_NAME` varchar(128) NOT NULL COMMENT '名称',
  `INDEX_TYPE` varchar(2) DEFAULT NULL COMMENT '索引类型（1 普通索引 2 唯一索引）',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='唯一索引';

/*Table structure for table `eform_tab_col_search` */

CREATE TABLE `eform_tab_col_search` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID',
  `SEARCH_ORDER` decimal(65,30) NOT NULL COMMENT '顺序',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类检索';

/*Table structure for table `eform_tab_col_sort` */

CREATE TABLE `eform_tab_col_sort` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID',
  `SORT_ORDER` decimal(65,30) NOT NULL COMMENT '顺序',
  `SORT_CODE` varchar(2) NOT NULL COMMENT '排序标识；A-升序，D-降序；默认为升序A',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排序设置';

/*Table structure for table `eform_tab_col_sys_code` */

CREATE TABLE `eform_tab_col_sys_code` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID',
  `SYS_LOOKUP_ID` varchar(50) NOT NULL COMMENT '平台通用代码ID',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码维护-平台通用';

/*Table structure for table `eform_tab_columns` */

CREATE TABLE `eform_tab_columns` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) DEFAULT NULL COMMENT '表ID',
  `COL_NAME` varchar(32) DEFAULT NULL COMMENT '字段英文名',
  `COL_LABEL` varchar(128) DEFAULT NULL COMMENT '字段中文名',
  `COL_TYPE` varchar(50) DEFAULT NULL COMMENT '字段类型；下拉选择：字符串，整型，日期时间型',
  `COL_LENGTH` decimal(5,0) DEFAULT NULL COMMENT '字段长度；默认字符串长度为20，数值为3',
  `COL_DECIMAL` decimal(2,0) DEFAULT NULL COMMENT '小数位数',
  `COL_IS_SYS` varchar(2) DEFAULT NULL COMMENT '是否系统字段；Y-是，N-否；默认否',
  `COL_ORDER` decimal(65,30) DEFAULT NULL COMMENT '显示顺序；自动按序生成',
  `COL_IS_MUST` varchar(2) DEFAULT NULL COMMENT '是否必著；Y-是，N-否；默认否',
  `COL_IS_VISIBLE` varchar(2) DEFAULT NULL COMMENT '是否显示；Y-是，N-否；默认是',
  `COL_IS_SEARCH` varchar(2) DEFAULT NULL COMMENT '是否查询字段；Y-是，N-否；默认否',
  `COL_IS_EDIT` varchar(2) DEFAULT NULL COMMENT '编辑标识；Y-是，N-否；默认是',
  `COL_IS_TAB_VISIBLE` varchar(2) DEFAULT NULL COMMENT '是否列表显示；Y-是，N-否；默认是',
  `COL_IS_DETAIL` varchar(2) DEFAULT NULL COMMENT '是否详细显示；Y-是，N-否；默认是',
  `COL_DROPDOWN_TYPE` varchar(2) DEFAULT NULL COMMENT '下拉类型；0-无，1-参选，2-只选，3-选择(选人，选部门，自定义弹出页)\r\n',
  `COL_GENE_METHOD` varchar(2) DEFAULT NULL COMMENT '生成方式；下拉选择：0-空，1-默认值，2-序列，3-顺带，4-组合项，5-统计生成，6-选择',
  `COL_RULE_NAME` text COMMENT '生成规则英文',
  `COL_RULE_TITLE` varchar(128) DEFAULT NULL COMMENT '生成规则中文',
  `CUSTOM_PATH` text COMMENT '路径',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  `COL_SHOW_FORMAT` varchar(2) DEFAULT NULL COMMENT '显示格式 0 无 1 日期 2 金额',
  `COL_GENE_METHOD_RULE` text COMMENT '生成方式规则',
  `COL_IS_DISPLAY` varchar(2) DEFAULT NULL COMMENT '是否可见；Y是，N否',
  `ELEMENT_TYPE` varchar(50) DEFAULT NULL,
  `COL_VALIDATE_TYPE` varchar(50) DEFAULT NULL,
  `COL_IS_UNIQUE` varchar(50) DEFAULT NULL,
  `COL_AUTOCODE` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='列信息';

/*Table structure for table `eform_tab_form` */

CREATE TABLE `eform_tab_form` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `FORM_NAME` varchar(128) NOT NULL COMMENT '表单名称',
  `FORM_IS_DEFALT` varchar(2) NOT NULL COMMENT '是否默认表单；Y-是，N-否',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  `FORM_CONTENT` longblob,
  `FORM_LAYOUT` longblob,
  `TABLE_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单基本信息';

/*Table structure for table `eform_tab_form_config` */

CREATE TABLE `eform_tab_form_config` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `FORM_ID` varchar(50) NOT NULL COMMENT '表单ID',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID',
  `FORM_VERTICAL` decimal(65,30) NOT NULL COMMENT '纵向栏数',
  `FORM_HORIZONTAL` decimal(65,30) NOT NULL COMMENT '横向栏数',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单配置信息';

/*Table structure for table `eform_tab_form_define` */

CREATE TABLE `eform_tab_form_define` (
  `ID` varchar(50) NOT NULL,
  `TABLE_ID` varchar(50) DEFAULT NULL,
  `COLUMN_NAME` varchar(50) DEFAULT NULL,
  `TR_ORDER` decimal(65,30) DEFAULT NULL,
  `TD_ORDER` decimal(65,30) DEFAULT NULL,
  `TD_ROWSPAN` decimal(65,30) DEFAULT NULL,
  `TD_COLSPAN` decimal(65,30) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `VERSION` decimal(65,30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eform_tab_form_node` */

CREATE TABLE `eform_tab_form_node` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TAB_NODE_ID` varchar(50) NOT NULL COMMENT '表与节点关系ID（外键）',
  `FORM_ID` varchar(50) NOT NULL COMMENT '表单ID（外键）',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单与节点关联表';

/*Table structure for table `eform_tab_form_select` */

CREATE TABLE `eform_tab_form_select` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SEL_NAME` varchar(128) NOT NULL COMMENT '名称',
  `SEL_CODE` varchar(32) DEFAULT NULL COMMENT '代码',
  `SEL_URL` text COMMENT '路径',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成方式选择表';

/*Table structure for table `eform_tab_node` */

CREATE TABLE `eform_tab_node` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表ID（外键）',
  `NODE_ID` varchar(50) NOT NULL COMMENT '节点ID（外键）',
  `TAB_ORDER` decimal(65,30) NOT NULL COMMENT '显示顺序；自动按序生成',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 表与节点关联表';

/*Table structure for table `eform_tab_node_record` */

CREATE TABLE `eform_tab_node_record` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TAB_NODE_ID` varchar(50) NOT NULL COMMENT '表与节点关系ID（外键）',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '列ID（外键）',
  `COL_IS_VISIBLE` varchar(2) NOT NULL COMMENT '是否显示；Y-是，N-否；默认是',
  `COL_IS_TAB_VISIBLE` varchar(2) NOT NULL COMMENT '是否列表显示；Y-是，N-否；默认是',
  `COL_IS_DETAIL` varchar(2) NOT NULL COMMENT '是否详细显示；Y-是，N-否；默认是',
  `COL_ORDER` decimal(65,30) NOT NULL COMMENT '显示顺序；自动按序生成',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点著录定义表';

/*Table structure for table `eform_tab_table_templet` */

CREATE TABLE `eform_tab_table_templet` (
  `ID` varchar(50) NOT NULL,
  `TABLE_ID` varchar(50) DEFAULT NULL,
  `TEMPLET_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `VERSION` decimal(16,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eform_tab_version` */

CREATE TABLE `eform_tab_version` (
  `ID` varchar(50) DEFAULT NULL,
  `TABLE_ID` varchar(50) DEFAULT NULL,
  `VERSION_KEY` decimal(65,30) DEFAULT NULL,
  `VERSION_VALUE` varchar(50) DEFAULT NULL,
  `VERSION_DESC` longtext,
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `VERSION` decimal(65,30) DEFAULT NULL,
  `COLUMN_ID` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eform_tables` */

CREATE TABLE `eform_tables` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_NAME` varchar(32) DEFAULT NULL COMMENT '表英文名',
  `TABLE_TITLE` varchar(32) DEFAULT NULL COMMENT '表中文名',
  `IS_VISIBLE` varchar(2) DEFAULT NULL COMMENT '是否可见；Y-是，N-否；默认是',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  `TABLE_IS_TRUE_TABLE` varchar(2) DEFAULT NULL COMMENT '虚拟表标示；Y真实表，N虚拟表',
  `SUB_TABLE_NAME` varchar(50) DEFAULT NULL,
  `SUB_COLUMN_NAME` varchar(50) DEFAULT NULL,
  `TABLE_IS_CREATED` varchar(2) DEFAULT NULL,
  `TABLE_IS_UPLOAD` varchar(2) DEFAULT NULL,
  `TABLE_IS_BPM` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表信息';

/*Table structure for table `eform_templet_code` */

CREATE TABLE `eform_templet_code` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TEMPLET_ID` varchar(50) NOT NULL COMMENT '模板ID',
  `FIELD_ID` varchar(50) NOT NULL COMMENT '字段ID',
  `CODE_ORDER` decimal(65,30) NOT NULL COMMENT '顺序',
  `LOOKUP_NAME` varchar(128) NOT NULL COMMENT '显示值',
  `LOOKUP_CODE` varchar(128) NOT NULL COMMENT '真实值；手动输入，默认与显示值一样',
  `CODE_IS_VALID` varchar(2) NOT NULL COMMENT '是否有效；Y-有效，N-无效',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参考模板代码维护';

/*Table structure for table `eform_templet_field` */

CREATE TABLE `eform_templet_field` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TEMPLET_ID` varchar(50) NOT NULL COMMENT '模板ID',
  `COL_NAME` varchar(32) NOT NULL COMMENT '字段英文名',
  `COL_LABEL` varchar(128) NOT NULL COMMENT '字段中文名',
  `COL_TYPE` varchar(50) NOT NULL COMMENT '字段类型；下拉选择：字符串，数值，日期时间',
  `COL_LENGTH` decimal(5,0) DEFAULT NULL COMMENT '字段长度；默认字符串长度为20，数值为3',
  `COL_DECIMAL` decimal(2,0) DEFAULT NULL COMMENT '小数位数',
  `COL_IS_SYS` varchar(2) DEFAULT NULL COMMENT '是否系统字段；Y-是，N-否；默认否',
  `COL_ORDER` decimal(65,30) NOT NULL COMMENT '显示顺序；自动按序生成',
  `COL_IS_MUST` varchar(2) NOT NULL COMMENT '是否必著；Y-是，N-否；默认否',
  `COL_IS_VISIBLE` varchar(2) NOT NULL COMMENT '是否显示；Y-是，N-否；默认是',
  `COL_IS_SEARCH` varchar(2) NOT NULL COMMENT '是否查询字段；Y-是，N-否；默认否',
  `COL_IS_EDIT` varchar(2) NOT NULL COMMENT '编辑标识；Y-是，N-否；默认是',
  `COL_IS_TAB_VISIBLE` varchar(2) NOT NULL COMMENT '是否列表显示；Y-是，N-否；默认是',
  `COL_IS_DETAIL` varchar(2) NOT NULL COMMENT '是否详细显示；Y-是，N-否；默认是',
  `COL_DROPDOWN_TYPE` varchar(2) NOT NULL COMMENT '下拉类型；0-无，1-只选，2-参选',
  `COL_GENE_METHOD` varchar(2) NOT NULL COMMENT '生成方式；下拉选择：0-空，1-默认值，2-序列，3-顺带，4-组合项，5-统计生成，6-选择',
  `COL_RULE_NAME` text COMMENT '生成规则英文',
  `COL_RULE_TITLE` varchar(128) DEFAULT NULL COMMENT '生成规则中文',
  `CUSTOM_PATH` text COMMENT '路径',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  `COL_IS_DISPLAY` varchar(2) DEFAULT NULL COMMENT '是否可见；Y是，N否',
  `COL_GENE_METHOD_RULE` text COMMENT '生成方式规则值',
  `COL_SHOW_FORMAT` varchar(2) DEFAULT NULL COMMENT '显示格式 0无，1日期，2金额',
  `ELEMENT_TYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参考模板字段信息';

/*Table structure for table `eform_templet_info` */

CREATE TABLE `eform_templet_info` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TEMP_CODE` varchar(32) DEFAULT NULL COMMENT '编号',
  `TEMP_NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `PARENT_ID` varchar(50) DEFAULT NULL COMMENT '父ID',
  `TEMP_TYPE` varchar(2) DEFAULT NULL COMMENT '类型；R-根节点；F-模板夹，C-模板，O-组织标识，S-系统标识',
  `IS_SYS_INIT` varchar(2) DEFAULT NULL COMMENT '是否系统初始；Y-是，N-否',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) DEFAULT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参考模板基本信息';

/*Table structure for table `eform_templet_sys_code` */

CREATE TABLE `eform_templet_sys_code` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TEMPLET_ID` varchar(50) NOT NULL COMMENT '模板ID',
  `FIELD_ID` varchar(50) NOT NULL COMMENT '字段ID',
  `SYS_LOOKUP_ID` varchar(50) NOT NULL COMMENT '平台通用代码ID',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '说明',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `SYS_ID` varchar(50) DEFAULT NULL COMMENT '系统标识ID',
  `SECRET_LEVEL` varchar(50) DEFAULT NULL COMMENT '密级',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '多应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段6',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段7',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段8',
  `ATTRIBUTE_09` datetime DEFAULT NULL COMMENT '预留字段9',
  `ATTRIBUTE_10` datetime DEFAULT NULL COMMENT '预留字段10',
  `ATTRIBUTE_11` decimal(65,30) DEFAULT NULL COMMENT '预留字段11',
  `ATTRIBUTE_12` decimal(65,30) DEFAULT NULL COMMENT '预留字段12',
  `ATTRIBUTE_13` decimal(65,30) DEFAULT NULL COMMENT '预留字段13',
  `ATTRIBUTE_14` decimal(65,30) DEFAULT NULL COMMENT '预留字段14',
  `ATTRIBUTE_15` decimal(65,30) DEFAULT NULL COMMENT '预留字段15',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参考模板代码维护-平台通用';

/*Table structure for table `files_fastdfs` */

CREATE TABLE `files_fastdfs` (
  `ID` varchar(50) NOT NULL,
  `TEMP_FAST_ID` varchar(50) NOT NULL,
  `FASTDFS_LOCATION` text,
  `ORDER_BY` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `files_temp` */

CREATE TABLE `files_temp` (
  `ID` varchar(50) NOT NULL,
  `FILE_NAME` varchar(100) DEFAULT NULL,
  `SCHUNK` decimal(10,0) DEFAULT NULL,
  `SCHUNKS` decimal(10,0) DEFAULT NULL,
  `FASTDFS_LOCATION` text,
  `PARENT_TABLE_ID` varchar(50) DEFAULT NULL,
  `PARENT_REGISTER_ID` varchar(50) DEFAULT NULL,
  `ATTACH_CATEGORY` varchar(32) DEFAULT NULL,
  `SECRET_LEVEL` varchar(32) DEFAULT NULL,
  `LASTDATE` varchar(100) DEFAULT NULL,
  `FILE_SIZE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `model_business_flow` */

CREATE TABLE `model_business_flow` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '模型类型',
  `NAME` varchar(100) DEFAULT NULL COMMENT '模型名称',
  `XML` longblob COMMENT '模型实体',
  `ATTRIBUTE` longblob COMMENT '模型属性',
  `REMARKS` text COMMENT '模型描述',
  `FLOW_ID` varchar(100) DEFAULT NULL COMMENT '模型ID',
  `VERSION` decimal(16,1) DEFAULT NULL COMMENT '模型版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务流程设计器模型表';

/*Table structure for table `model_function_module` */

CREATE TABLE `model_function_module` (
  `MODEL_ID` varchar(35) NOT NULL COMMENT '模型ID',
  `MODEL_NAME` varchar(255) DEFAULT NULL COMMENT '功能结构模型名称',
  `MODEL_DESC` varchar(255) DEFAULT NULL COMMENT '功能结构模型描述信息',
  `TYPE` varchar(25) DEFAULT NULL COMMENT '模型类型（功能结构图）',
  `MODEL_DEF_XML` longblob COMMENT '模型定义的XML内容',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '模型版本信息',
  `MODEL_ATTRIBUTE01` varchar(255) DEFAULT NULL COMMENT '保留属性字段',
  `MODEL_ATTRIBUTE00` varchar(255) DEFAULT NULL COMMENT '保留属性字段',
  `MODEL_ALL_CONTENT` longblob COMMENT '利用XMLStream保存当前模型对象的所有信息',
  `DB_ID` varchar(38) NOT NULL COMMENT '主键ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `model_function_module_tree` */

CREATE TABLE `model_function_module_tree` (
  `DBID` varchar(50) NOT NULL COMMENT '主键ID',
  `NODE_NAME` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `CODE` varchar(100) DEFAULT NULL COMMENT '可能用到的代码',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '类型描述',
  `PARENTID` varchar(100) DEFAULT NULL COMMENT '所属父元素ID',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '其他',
  `MODELID` varchar(50) DEFAULT NULL COMMENT '模型ID',
  `ORDER_BY` varchar(10) DEFAULT NULL COMMENT '排序列'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `model_organiz` */

CREATE TABLE `model_organiz` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '模型类型',
  `NAME` varchar(100) DEFAULT NULL COMMENT '模型名称',
  `XML` longblob COMMENT '模型实体',
  `ATTRIBUTE` longblob COMMENT '模型属性',
  `REMARK` text COMMENT '模型描述',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '模型版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务流程设计器模型表';

/*Table structure for table `model_user_case` */

CREATE TABLE `model_user_case` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '模型类型',
  `NAME` varchar(100) DEFAULT NULL COMMENT '模型名称',
  `XML` longblob COMMENT '模型实体',
  `ATTRIBUTE` longblob COMMENT '模型属性',
  `REMARK` text COMMENT '模型描述',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '模型版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务流程设计器模型表';

/*Table structure for table `qrtz_blob_triggers` */

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_calendars` */

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` longblob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_cron_triggers` */

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_fired_triggers` */

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` decimal(13,0) NOT NULL,
  `PRIORITY` decimal(13,0) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_job_details` */

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_locks` */

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_paused_trigger_grps` */

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_scheduler_state` */

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` decimal(13,0) NOT NULL,
  `CHECKIN_INTERVAL` decimal(13,0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_simple_triggers` */

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` decimal(7,0) NOT NULL,
  `REPEAT_INTERVAL` decimal(12,0) NOT NULL,
  `TIMES_TRIGGERED` decimal(10,0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_simprop_triggers` */

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` text,
  `STR_PROP_2` text,
  `STR_PROP_3` text,
  `INT_PROP_1` decimal(10,0) DEFAULT NULL,
  `INT_PROP_2` decimal(10,0) DEFAULT NULL,
  `LONG_PROP_1` decimal(13,0) DEFAULT NULL,
  `LONG_PROP_2` decimal(13,0) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `qrtz_triggers` */

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PREV_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PRIORITY` decimal(13,0) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` decimal(13,0) NOT NULL,
  `END_TIME` decimal(13,0) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` decimal(2,0) DEFAULT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `resteasy_authorization` */

CREATE TABLE `resteasy_authorization` (
  `ID` varchar(50) NOT NULL COMMENT '主键唯一',
  `RESOURCES_ID` varchar(50) DEFAULT NULL COMMENT '资源表主键',
  `SYSTEM_ID` varchar(50) DEFAULT NULL COMMENT '系统表主键',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `resteasy_org` */

CREATE TABLE `resteasy_org` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ORG_NAME` varchar(100) DEFAULT NULL COMMENT '组织机构名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `resteasy_resources` */

CREATE TABLE `resteasy_resources` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `REST_URL` varchar(200) DEFAULT NULL COMMENT '服务URL访问地址',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '服务状态  1可用 0不可用',
  `URL_DESC` text COMMENT '服务描述',
  `SYSTEM_ID` varchar(50) DEFAULT NULL COMMENT '服务所属系统',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `resteasy_system` */

CREATE TABLE `resteasy_system` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '所属组织机构',
  `SYSTEM_NAME` varchar(100) DEFAULT NULL COMMENT '系统名称',
  `SYSTEM_DESC` text COMMENT '系统描述',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '状态  1可用 0不可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `resteasy_user` */

CREATE TABLE `resteasy_user` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `USER_PASSWORD` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `BASE_KEY` varchar(100) DEFAULT NULL COMMENT '用户秘钥   由名称和密码MD5计算',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '状态  1可用  0不可用',
  `SYSTEM_ID` varchar(50) DEFAULT NULL COMMENT '所属系统',
  `TYPE` varchar(2) DEFAULT NULL COMMENT '用户类型   1系统间调用   0平台自己调用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `search_connection` */

CREATE TABLE `search_connection` (
  `ID` varchar(50) NOT NULL,
  `CONNECTION_URL` varchar(100) DEFAULT NULL,
  `CONNECTION_USERNAME` varchar(100) DEFAULT NULL,
  `CONNECTION_PASSWORD` varchar(100) DEFAULT NULL,
  `CONNECTION_DRIVER` varchar(100) DEFAULT NULL,
  `CONNECTION_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `search_datasources` */

CREATE TABLE `search_datasources` (
  `ID` varchar(50) NOT NULL,
  `DATASOURCE_NAME` varchar(50) DEFAULT NULL,
  `DISPLAY_NAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DATASOURCE_DESC` text,
  `DISPLAY_URL` text,
  `SQL_STATEMENT` text,
  `DOC_PATH` text,
  `TYPE` varchar(2) DEFAULT NULL,
  `CONNECTION_FK` varchar(50) DEFAULT NULL,
  `SYSROLE_FK` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `search_details` */

CREATE TABLE `search_details` (
  `ID` varchar(50) NOT NULL,
  `MAINDATA_ID` varchar(50) DEFAULT NULL,
  `COLUMN_NAME` varchar(50) DEFAULT NULL,
  `STORED` varchar(2) DEFAULT NULL,
  `INDEXED` varchar(2) DEFAULT NULL,
  `BELONG` varchar(2) DEFAULT NULL,
  `SECRETED` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_accesscontrol` */

CREATE TABLE `sys_accesscontrol` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TARGET_TYPE` varchar(50) NOT NULL COMMENT '设置权限设置的目标，可能的类型有：部门(DEPT)、角色(ROLE)、群组(GROUP)、岗位(POS)、用户(USER)\r\nTARGET_TYPE字段与TARGET_ID字段联合使用，当TARGET_TYPE存储不同的值时，TARGET_ID存储响应的类型的ID值。\r\n类型优先级如下：用户(USER) > 角色(ROLE) > 部门(DEPT) > 岗位(POS) > 群组(GROUP) > ',
  `TARGET_ID` varchar(100) NOT NULL COMMENT '根据TARGET_TYPE的不同的值存储不同的内容，具体内容如下：\r\nTARGET_TYPE的值， TARGET_ID存储的值\r\nROLE              ROLE_ID\r\nDEPT             DEPT_ID\r\n等等',
  `RESOURE_ID` text NOT NULL COMMENT '资源ID,  引用资源表的ID',
  `ACCESSIBILITY` decimal(1,0) NOT NULL COMMENT '可访问性，用来表示一个资源是否可以访问的属性，\r\n对于菜单来说，代表该菜单是否可以访问\r\n\r\n对于URL来说，代表是否可以访问该URL\r\n\r\n对于按钮来说，代表是否显隐\r\n对于表单列和表格列来说，代表是否显隐\r\n\r\n1-允许访问\r\n0-禁止访问',
  `OPERABILITY` decimal(1,0) NOT NULL COMMENT '可操作性，用来表示一个资源是否可以操作的属性，\r\n对于菜单和URL来说，没有可操作性的属性\r\n对于按钮来说，可操作性是指按钮是否可以点击\r\n对于表单列和表格列来说，可操作性是指是否可以更改其中的数据\r\n\r\n1-允许操作\r\n0-禁止操作',
  `SYS_APPLICATION_ID` varchar(50) NOT NULL COMMENT '应用ID，适用于多应用情况',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源权限控制表，存储权限设置\r\n';

/*Table structure for table `sys_app_role` */

CREATE TABLE `sys_app_role` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYSAPP_ID` varchar(50) NOT NULL COMMENT '应用id',
  `SYSROLE_ID` varchar(50) NOT NULL COMMENT '角色id',
  `SYSAPP_NAME` varchar(250) DEFAULT NULL COMMENT '应用名称',
  `SYSROLE_NAME` varchar(250) DEFAULT NULL COMMENT '角色名称',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用角色关系表';

/*Table structure for table `sys_application` */

CREATE TABLE `sys_application` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `APPLICATION_NAME` varchar(100) DEFAULT NULL COMMENT '应用名称',
  `APPLICATION_CODE` varchar(50) DEFAULT NULL COMMENT '应用代码',
  `BASEPATH` varchar(100) DEFAULT NULL COMMENT '应用调用路径',
  `RUN_STATE` varchar(1) DEFAULT NULL COMMENT '运行状态\r\n1：run ;0:stop；default:0',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '应用描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多应用配置表';

/*Table structure for table `sys_attachment` */

CREATE TABLE `sys_attachment` (
  `ID` varchar(50) NOT NULL,
  `ATTACH_NAME` varchar(200) NOT NULL,
  `PARENT_TABLE_ID` varchar(50) DEFAULT NULL,
  `PARENT_REGISTER_ID` varchar(50) NOT NULL,
  `ATTACH_BLOB` longblob,
  `ATTACH_TYPE` varchar(10) DEFAULT NULL,
  `ATTACH_SIZE` decimal(20,0) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(32) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(20) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `VERSION` decimal(16,0) DEFAULT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT 'groupName',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT 'remoteFlieName',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  `UPLOAD_PATH` text,
  `PARENT_TABLE_FIELD` varchar(100) DEFAULT NULL COMMENT '附件所关联字段名称',
  `ATTACH_CATEGORY` varchar(32) DEFAULT NULL,
  `SECRET_LEVEL` varchar(32) DEFAULT NULL,
  `FASTDFS_LOCATION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_bpm_hist_procinst` */

CREATE TABLE `sys_bpm_hist_procinst` (
  `DBID_` varchar(50) NOT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `PROCDEFID_` varchar(255) DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `FORMID_` varchar(50) DEFAULT NULL,
  `TITLE_` varchar(255) DEFAULT NULL,
  `USERID_` varchar(255) DEFAULT NULL,
  `DEPTID_` varchar(255) DEFAULT NULL,
  `BUSINESSSTATE_` varchar(255) DEFAULT NULL,
  `ACTIVITYALIAS_` text,
  `LAST_UPDATE_DATE_` datetime DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_bpm_hist_task` */

CREATE TABLE `sys_bpm_hist_task` (
  `DBID_` varchar(50) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `TASK_ORDER_` varchar(255) DEFAULT NULL,
  `OPEN_` datetime DEFAULT NULL,
  `TAKE_STATE_` varchar(255) DEFAULT NULL,
  `PROCINST_` varchar(50) DEFAULT NULL,
  `TASK_TYPE_` varchar(255) DEFAULT NULL,
  `TASK_B_ID_` varchar(255) DEFAULT NULL,
  `TASK_TITLE_` varchar(255) DEFAULT NULL,
  `TASK_SENDUSER_` varchar(255) DEFAULT NULL,
  `TASK_SENDDEPT_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PROCESS_DEF_NAME_` varchar(255) DEFAULT NULL,
  `TASK_FINISHED_` varchar(255) DEFAULT NULL,
  `TASK_STATE_` varchar(255) DEFAULT NULL,
  `TASK_NAME_` varchar(255) DEFAULT NULL,
  `HISACTINST_` varchar(50) DEFAULT NULL,
  `WORKHAND_TYPE_` varchar(255) DEFAULT NULL,
  `WORKHAND_USER_` varchar(255) DEFAULT NULL,
  `APP_ID_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_DEPT_` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE_` datetime DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_coding_com_segment` */

CREATE TABLE `sys_coding_com_segment` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `SYS_APPLICATION_ID` varchar(50) NOT NULL COMMENT '应用ID',
  `SEGMENT_NAME` varchar(200) NOT NULL COMMENT '码段名称',
  `SEGMENT_LENGTH` decimal(2,0) DEFAULT NULL COMMENT '码段长度',
  `SEGMENT_REMARK` text COMMENT '码段说明',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-通用码段表';

/*Table structure for table `sys_coding_com_segment_values` */

CREATE TABLE `sys_coding_com_segment_values` (
  `ID` varchar(32) NOT NULL,
  `SEGMENT_ID` varchar(32) NOT NULL COMMENT '通用码段ID',
  `SEGMENT_NAME` varchar(200) NOT NULL COMMENT '码名称',
  `SEGMENT_VALUE` varchar(200) NOT NULL COMMENT '码值',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-通用码段码值表';

/*Table structure for table `sys_coding_rule_base` */

CREATE TABLE `sys_coding_rule_base` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `SYS_APPLICATION_ID` varchar(50) NOT NULL COMMENT '应用ID',
  `CODING_NAME` varchar(200) NOT NULL COMMENT '规则名称',
  `SEGMENT_NUMBER` decimal(2,0) NOT NULL COMMENT '码段个数',
  `STATUS` varchar(10) NOT NULL COMMENT '状态(通用代码：MDM_CODING_SEGMENT_STATUS)',
  `RULE_REMARK` text COMMENT '说明',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  `CODING_CODE` varchar(50) NOT NULL COMMENT '规则编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-基本设置表';

/*Table structure for table `sys_coding_rule_segment` */

CREATE TABLE `sys_coding_rule_segment` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BASE_ID` varchar(32) NOT NULL COMMENT '基本设置ID',
  `SEGMENT_INDEX` decimal(2,0) NOT NULL COMMENT '码段号',
  `SEGMENT_NAME` varchar(200) NOT NULL COMMENT '码段名称',
  `SEGMENT_TYPE` varchar(10) NOT NULL COMMENT '码段类型',
  `SEGMENT_LENGTH` decimal(2,0) DEFAULT NULL COMMENT '码段长度',
  `SEGMENT_PREFIXION` varchar(200) DEFAULT NULL COMMENT '前缀',
  `SEGMENT_RELATION` text COMMENT '分类码值关联',
  `SERIAL_NUMBER_START` decimal(16,0) DEFAULT NULL COMMENT '流水号开始',
  `SERIAL_NUMBER_END` decimal(16,0) DEFAULT NULL COMMENT '流水号结束',
  `SERIAL_STEP` decimal(16,0) DEFAULT NULL COMMENT '流水步长',
  `FORMAT` varchar(200) DEFAULT NULL COMMENT '格式',
  `COM_SEGMENT_ID` varchar(32) DEFAULT NULL COMMENT '通用码段ID',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  `SEGMENT_SUFFIX` varchar(200) DEFAULT NULL COMMENT '后缀',
  `IS_CURRENT_TIME` varchar(1) DEFAULT NULL COMMENT '是否默认当前时间,Y表示是，N表示否',
  `IS_INPUT_SERIAL` varchar(1) DEFAULT NULL COMMENT '是否可以直接输入流水号,Y表示是，N表示否',
  `FUNC` varchar(200) DEFAULT NULL COMMENT '函数全路径',
  `SQL_EXPRESSION` text COMMENT 'SQL语句',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-码段属性表';

/*Table structure for table `sys_coding_rule_segment_values` */

CREATE TABLE `sys_coding_rule_segment_values` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BASE_ID` varchar(32) NOT NULL COMMENT '基本设置ID',
  `SEGMENT_INDEX` decimal(2,0) NOT NULL COMMENT '码段号',
  `SEGMENT_NAME` varchar(200) DEFAULT NULL COMMENT '码名称',
  `SEGMENT_VALUE` varchar(200) NOT NULL COMMENT '码值',
  `DEPEND_VALUES` text COMMENT '依赖值(null表示没有依赖关系)',
  `COM_SEGMENT_VALUE_ID` varchar(32) DEFAULT NULL COMMENT '通用码值ID',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-码值表';

/*Table structure for table `sys_coding_rule_serial` */

CREATE TABLE `sys_coding_rule_serial` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `BASE_ID` varchar(32) NOT NULL COMMENT '基本设置ID',
  `SEGMENT_INDEX` decimal(2,0) NOT NULL COMMENT '码段号',
  `DEPEND_VALUES` text COMMENT '依赖值',
  `MAX_SERIAL` decimal(16,0) NOT NULL COMMENT '最大流水号',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自编代码规则-流水号表';

/*Table structure for table `sys_coding_used_info` */

CREATE TABLE `sys_coding_used_info` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `CODING_ID` varchar(32) NOT NULL COMMENT '对应Table MDM_CODING_RULE_BASE表的ID',
  `CODING_VALUE` text NOT NULL COMMENT '编码值',
  `MODULE_NAME` text NOT NULL COMMENT '模块名称',
  `CREATED_BY` varchar(32) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(32) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_IP` varchar(20) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL,
  `FORM_ID` varchar(50) DEFAULT NULL COMMENT '业务数据ID',
  `USED_SERIAL` decimal(16,0) DEFAULT NULL COMMENT '使用的流水号',
  `USED_SERIAL_DEPEND` text COMMENT '使用的流水号的依赖值',
  `IS_DELETED` varchar(1) DEFAULT NULL COMMENT '是否已删除，Y表示已删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='编码使用情况表';

/*Table structure for table `sys_customed` */

CREATE TABLE `sys_customed` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '组织ID',
  `USER_ID` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `KEY` varchar(50) DEFAULT NULL COMMENT '惯用设置选项代码',
  `VALUE` text COMMENT '惯用设置选项值',
  `IS_MULTI` varchar(1) DEFAULT NULL COMMENT '是否是多值  1 代表多值  0代表单值',
  `IS_DEFAULT` varchar(1) DEFAULT NULL COMMENT '是否是默认  1 默认     ',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `SYS_APP_ID` varchar(50) NOT NULL COMMENT '所属应用id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='惯用设置表';

/*Table structure for table `sys_dept` */

CREATE TABLE `sys_dept` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `DEPT_CODE` varchar(100) NOT NULL COMMENT '部门代码，在同一组织下的部门代码不能重复',
  `PARENT_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '父部门ID',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `ORDER_BY` decimal(16,0) NOT NULL COMMENT '排序',
  `POST` varchar(50) DEFAULT NULL COMMENT '邮编',
  `TEL` varchar(50) DEFAULT NULL COMMENT '电话',
  `FAX` varchar(50) DEFAULT NULL COMMENT '传真',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮件',
  `DEPT_ALIAS` varchar(50) DEFAULT NULL COMMENT '部门别称',
  `DEPT_TYPE` varchar(1) DEFAULT NULL COMMENT '部门类型',
  `DEPT_INDEX_TREE_NO` text COMMENT '树形索引',
  `WORK_CALENDAR_ID` varchar(50) DEFAULT NULL COMMENT '工作日历ID',
  `VALID_FLAG` varchar(1) NOT NULL COMMENT '设置部门是否有效，1-有效，0-无效',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `DEPT_LEVEL` decimal(16,0) DEFAULT NULL COMMENT '部门级别',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Table structure for table `sys_dept_tl` */

CREATE TABLE `sys_dept_tl` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_LANGUAGE_CODE` varchar(50) NOT NULL COMMENT '语言代码',
  `DEPT_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `DEPT_DESC` varchar(240) DEFAULT NULL COMMENT '部门描述',
  `DEPT_PLACE` varchar(100) DEFAULT NULL COMMENT '办公地点',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `SYS_DEPT_ID` varchar(50) NOT NULL COMMENT 'SYS_DEPT表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门多语言表';

/*Table structure for table `sys_dynamic_datasource` */

CREATE TABLE `sys_dynamic_datasource` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `DATASOURCE_NAME` varchar(100) NOT NULL COMMENT '数据源名称，不能重复',
  `JDBCURL` text NOT NULL COMMENT '数据库连接的jdbc字符串',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `USERNAME` varchar(100) NOT NULL COMMENT '用户名',
  `PROPERTIES` longtext COMMENT '保存数据库连接属性的字符串',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `DRIVERCLASS` text NOT NULL COMMENT '驱动类名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于保存系统中的动态数据源定义';

/*Table structure for table `sys_excel_import_history` */

CREATE TABLE `sys_excel_import_history` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `FILE_URL` varchar(255) DEFAULT NULL COMMENT '错误文件路径',
  `SUCCESS_COUNT` decimal(8,0) DEFAULT NULL COMMENT '成功数',
  `TOTAL_COUNT` decimal(8,0) DEFAULT NULL COMMENT '总数',
  `FAIL_COUNT` decimal(8,0) DEFAULT NULL COMMENT '失败数',
  `SYS_LOOKUP_CODE` varchar(255) DEFAULT NULL COMMENT '表的通用代码LOOKUPCODE',
  `SYS_LOOKUP_NAME` varchar(255) DEFAULT NULL COMMENT '表的通用代码LOOKUPNAME',
  `SYS_LOOKUP_TYPE` varchar(255) DEFAULT NULL COMMENT '表的通用代码LOOKUPTYPE',
  `SYS_APPLICATION` varchar(32) DEFAULT NULL COMMENT '应用ID',
  `SYS_LANGUAGE_CODE` varchar(32) DEFAULT NULL COMMENT '多语言CODE',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `OPETATOR_PERSON` varchar(255) DEFAULT NULL COMMENT '导入人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='excel导入历史记录';

/*Table structure for table `sys_extend_metadata` */

CREATE TABLE `sys_extend_metadata` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TABLE_NAME` varchar(200) NOT NULL COMMENT '表名称，数据库中包含attribute扩展字段的表名称',
  `COL_NAME` varchar(200) NOT NULL COMMENT '源表中扩展列名称，一般以ATTRIBUTE开头',
  `DISPLAY_NAME` varchar(200) NOT NULL COMMENT '在界面上的显示名称',
  `DISPLAY_TIP` text COMMENT '在界面上的提示信息',
  `DISPLAY_TYPE` varchar(200) NOT NULL COMMENT '在界面上的显示类型，类型有：字符型、日期型、数字型',
  `DISPLAY_LENGTH` decimal(10,0) DEFAULT NULL COMMENT '最大显示长度，不能大于源表中扩展列的字段长度',
  `DISPLAY_COMMON_SELECTOR` varchar(200) DEFAULT NULL COMMENT '设置该字段引用哪个公共选择框，比如部门选择框、角色选择框等，界面要根据不同的选择框动态为该字段设置选择对话框',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `DISPLAY_COMMON_SELECTOR_TYPE` decimal(1,0) DEFAULT NULL COMMENT '该字段扩展类型0:自定义,1:系统代码,2:扩展控件:3:扩展javascript方法',
  `COL_IS_NULL` decimal(1,0) DEFAULT NULL COMMENT '该字段是否允许为空',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扩展属性描述表。本表保存其他表中的扩展属性的名称、提示信息、展现类型、长度等定义信息，用于实现动态字段扩展功能。';

/*Table structure for table `sys_filter_config` */

CREATE TABLE `sys_filter_config` (
  `ID` varchar(32) NOT NULL,
  `FILTER_CLASS_NAME` varchar(200) NOT NULL COMMENT '过滤器类名',
  `FILTER_ENABLE_FLG` varchar(1) NOT NULL COMMENT '过滤器启用标志',
  `VERSION` decimal(16,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_group` */

CREATE TABLE `sys_group` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `GROUP_PARENT_ID` varchar(50) DEFAULT NULL COMMENT '父群组ID',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '群组类型',
  `BELONG_TO` varchar(50) DEFAULT NULL COMMENT '群组归属',
  `VALID_FLAG` varchar(1) NOT NULL COMMENT '设置群组是否有效，1-有效，0-无效',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '所属应用系统ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组表';

/*Table structure for table `sys_group_tl` */

CREATE TABLE `sys_group_tl` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_GROUP_ID` varchar(50) DEFAULT NULL COMMENT '群组ID',
  `SYS_LANGUAGE_CODE` varchar(50) DEFAULT NULL COMMENT '语言代码',
  `GROUP_NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `GROUP_DESC` varchar(240) DEFAULT NULL COMMENT '描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组多语言表';

/*Table structure for table `sys_job` */

CREATE TABLE `sys_job` (
  `ID_` varchar(150) NOT NULL COMMENT '主键',
  `NAME_` varchar(50) NOT NULL COMMENT '此Job的名称，要求具有描述性，如“月度销售统计任务”',
  `GROUP_` varchar(50) NOT NULL COMMENT '为了便于管理，将每个Job分组管理，要求具有描述性，如“报表组”。如果未指定，则框架提供一个默认的Group名称。',
  `PROGRAM_` varchar(100) NOT NULL COMMENT '此Job执行时调用的程序。由一个资源表达式定义，如spring:myBean#myFunction。',
  `CRON_` varchar(200) NOT NULL COMMENT '一个Cron表达式，用于定义Job的运行时机。如0 0 30 * * ?',
  `STATUS_` varchar(1) NOT NULL COMMENT '定时任务的当前状态，可能值为“未调度”、“暂停”、“运行中”',
  `DESCRIPTION_` varchar(200) DEFAULT NULL COMMENT '对此Job的描述。如“月度审计任务，将在每个月的第一天凌晨1点开始，审计上个月的销售情况，由XXX负责”',
  `CREATE_USER_` varchar(50) NOT NULL COMMENT '此条记录的创建人',
  `CREATE_DATE_` datetime NOT NULL COMMENT '此条记录的创建时间',
  `UPDATE_USER_` varchar(50) DEFAULT NULL COMMENT '此条记录的更新人',
  `UPDATE_DATE_` datetime DEFAULT NULL COMMENT '此条记录的更新日期',
  `TYPE_` varchar(20) DEFAULT NULL COMMENT 'spring,clazz,quartzClazz,sql,sp,jar',
  `LAST_STATE_` varchar(2) DEFAULT NULL COMMENT '最后一次执行的完成状态(S:成功，F:失败)',
  `COMPANY_ID_` varchar(45) DEFAULT NULL COMMENT '公司系统编号',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务任务定义表，用于定义一个定时任务的信息，包括名称、组、运行的程序、运行时机等。';

/*Table structure for table `sys_job_calendar` */

CREATE TABLE `sys_job_calendar` (
  `ID_` varchar(50) NOT NULL COMMENT '主键',
  `NAME_` varchar(20) NOT NULL COMMENT '日历名称，用于标识日历对象，同时也作为Quartz的Calendar对象的名称。',
  `DESCRIPTION_` varchar(200) DEFAULT NULL COMMENT '日历描述，描述此日历的作用，如“法定节假日”',
  `CREATE_USER_` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_DATE_` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_USER_` varchar(50) DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE_` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日历表，定义一些日历对象，用于排除某些天。';

/*Table structure for table `sys_job_calendar_date` */

CREATE TABLE `sys_job_calendar_date` (
  `ID_` varchar(50) NOT NULL COMMENT '主键',
  `JOB_CALENDAR_ID_` varchar(50) NOT NULL COMMENT '任务日历主键，表示这一天属于哪一日历。',
  `EXCLUDED_DATE_` datetime NOT NULL COMMENT '被排除的天，在当天任务将不执行。',
  `DESCRIPTION_` varchar(200) DEFAULT NULL COMMENT '描述这一天为什么要被排除。',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务日历中的天，与任务日历表为多对一关系。定义了哪些天需要被排除。';

/*Table structure for table `sys_job_calendar_job` */

CREATE TABLE `sys_job_calendar_job` (
  `ID_` varchar(50) NOT NULL COMMENT '主键',
  `JOB_ID_` varchar(50) NOT NULL COMMENT '任务主键',
  `JOB_CALENDAR_ID_` varchar(50) NOT NULL COMMENT '任务日历主键',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='此表用于维护任务与任务日历的一对多关系。';

/*Table structure for table `sys_job_heartbeat` */

CREATE TABLE `sys_job_heartbeat` (
  `ID_` varchar(50) NOT NULL,
  `INSTANCE_NAME_` varchar(50) NOT NULL COMMENT '实例名，代表一个运行定时器的应用的实例。',
  `PRIORITY_` decimal(10,0) NOT NULL COMMENT '运行实例的优先级，通过拆解Preference中的job.servers定义获取。',
  `LAST_BEAT_TIME_` datetime NOT NULL COMMENT '最后跳动时间，同时也代表了当前实例的存活时间，通过对比此值判断实例的运行状态。',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务心跳表，以指定的时间间隔，不断向此表中更新心跳信息。';

/*Table structure for table `sys_job_history` */

CREATE TABLE `sys_job_history` (
  `ID_` varchar(50) NOT NULL COMMENT '主键',
  `JOB_ID_` varchar(50) NOT NULL COMMENT '关联的定时任务的主键',
  `START_DATE_` datetime NOT NULL COMMENT '任务的开始时间',
  `END_DATE_` datetime DEFAULT NULL COMMENT '定时任务的结束时间',
  `END_STATUS_` varchar(1) NOT NULL COMMENT '定时任务的结束状态，共两种（S代表成功，F代表失败）',
  `MESSAGE_` varchar(250) DEFAULT NULL COMMENT '额外信息',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务历史记录表，用于记录每个定时任务的开始时间、完成时间、完成状况（失败或成功，失败时产生的消息）等';

/*Table structure for table `sys_job_variables` */

CREATE TABLE `sys_job_variables` (
  `ID_` varchar(50) NOT NULL COMMENT '变量主键 : 变量主键',
  `MASTER_ID_` varchar(50) DEFAULT NULL COMMENT '主表主键 : 主表主键',
  `MODULE_` varchar(100) DEFAULT NULL COMMENT '隶属模块名称 : 隶属模块名称',
  `TYPE_` varchar(10) DEFAULT NULL COMMENT '类型 : 类型',
  `NAME_` varchar(50) DEFAULT NULL COMMENT '变量名称 : 变量名称',
  `DATA_TYPE_` varchar(20) DEFAULT NULL COMMENT '变量数据类型 : 变量数据类型',
  `VALUE_` varchar(100) DEFAULT NULL COMMENT '变量默认值 : 变量默认值',
  `META1_` varchar(100) DEFAULT NULL COMMENT '备用字段1 : 备用字段1',
  `META2_` varchar(100) DEFAULT NULL COMMENT '备用字段2 : 备用字段2',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时器变量';

/*Table structure for table `sys_language` */

CREATE TABLE `sys_language` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `LANGUAGE_CODE` varchar(50) NOT NULL COMMENT '语言代码\r\n比如：ZH,EN,GB',
  `LANGUAGE_NAME` varchar(50) NOT NULL COMMENT '语言名称',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序索引值',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `IS_SYSTEM_DEFAULT` decimal(1,0) DEFAULT NULL COMMENT '是否是系统默认的语言',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多语言配置表';

/*Table structure for table `sys_log` */

CREATE TABLE `sys_log` (
  `ID` varchar(50) NOT NULL,
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  `SYSLOG_USERNAME` varchar(50) DEFAULT NULL,
  `SYSLOG_TIME` datetime DEFAULT NULL,
  `SYSLOG_IP` varchar(50) DEFAULT NULL,
  `SYSLOG_OP` varchar(50) DEFAULT NULL,
  `SYSLOG_MODULE` varchar(50) DEFAULT NULL,
  `SYSLOG_TYPE` varchar(50) DEFAULT NULL,
  `SYSLOG_IS_GD` varchar(1) DEFAULT NULL,
  `SYSLOG_RESULT` varchar(10) DEFAULT NULL,
  `SYSLOG_CONTENT` longtext,
  `SYSLOG_TABLE` varchar(50) DEFAULT NULL,
  `SYSLOG_FORMID` varchar(50) DEFAULT NULL,
  `SYSLOG_SECRETLEVEL` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(50) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LAST_UPDATE_IP` varchar(50) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `SYSLOG_TITLE` text,
  `SYSLOG_USERNAME_ZH` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_log_archived` */

CREATE TABLE `sys_log_archived` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `ARCHIVE_DATE` datetime DEFAULT NULL COMMENT '归档时间',
  `ARCHIVE_NAME` text COMMENT '归档名称',
  `ARCHIVE_TABLE_NAME` text COMMENT '归档表名称',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `SYS_APP_ID` varchar(32) DEFAULT NULL COMMENT '归档所属系统',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_lookup` */

CREATE TABLE `sys_lookup` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_LOOKUP_TYPE_ID` varchar(50) DEFAULT NULL COMMENT '通用代码ID',
  `DISPLAY_ORDER` decimal(16,0) DEFAULT NULL COMMENT '显示顺序',
  `LOOKUP_CODE` text COMMENT '明细代码',
  `VALID_FLAG` varchar(1) DEFAULT NULL COMMENT '是否有效\r\n1代表有效,0代表无效 默认为1',
  `SYSTEM_FLAG` varchar(1) DEFAULT NULL COMMENT '是否是系统初始值\r\nY 是 N 否',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用代码选项表';

/*Table structure for table `sys_lookup_hiberarchy` */

CREATE TABLE `sys_lookup_hiberarchy` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `LOOKUP_TYPE` varchar(50) DEFAULT NULL COMMENT '代码类型',
  `SYSTEM_FLAG` varchar(1) DEFAULT NULL COMMENT '是否为系统初始\r\nY 是 N 否',
  `VALID_FLAG` varchar(1) DEFAULT NULL COMMENT '是否有效\r\n1代表有效,0代表无效 默认为1',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `PARENT_ID` varchar(32) NOT NULL COMMENT '父ID',
  `TYPE_KEY` varchar(255) DEFAULT NULL COMMENT '数据编码',
  `TYPE_VALUE` varchar(255) NOT NULL COMMENT '数据值',
  `ORDER_BY` decimal(65,30) NOT NULL COMMENT '排序',
  `REMARK` text COMMENT '备注',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `SYS_LANGUAGE_CODE` varchar(255) DEFAULT NULL COMMENT '多语言 ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用代码类型表 扩展支持多级层次';

/*Table structure for table `sys_lookup_tl` */

CREATE TABLE `sys_lookup_tl` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_LOOKUP_ID` varchar(50) DEFAULT NULL COMMENT '通用代码明细ID',
  `SYS_LANGUAGE_CODE` varchar(50) DEFAULT NULL COMMENT '语言代码',
  `LOOKUP_NAME` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用代码选项多语言表';

/*Table structure for table `sys_lookup_type` */

CREATE TABLE `sys_lookup_type` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `LOOKUP_TYPE` varchar(50) DEFAULT NULL COMMENT '代码类型',
  `SYSTEM_FLAG` varchar(1) DEFAULT NULL COMMENT '是否为系统初始\r\nY 是 N 否',
  `VALID_FLAG` varchar(1) DEFAULT NULL COMMENT '是否有效\r\n1代表有效,0代表无效 默认为1',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `BELONG_MODULE` varchar(50) DEFAULT NULL COMMENT '所属模块',
  `USAGE_MODIFIER` varchar(1) DEFAULT NULL COMMENT '0 公有可用     1私有可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用代码类型表';

/*Table structure for table `sys_lookup_type_tl` */

CREATE TABLE `sys_lookup_type_tl` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_LOOKUP_TYPE_ID` varchar(50) DEFAULT NULL COMMENT 'SYS_LOOKUP_TYPE_Id value',
  `SYS_LANGUAGE_CODE` varchar(50) DEFAULT NULL COMMENT '语言代码\r\n对应SYS_LANGUAGE表中获LANGUAGE_CODE的值',
  `LOOKUP_TYPE_NAME` varchar(100) DEFAULT NULL COMMENT '代码名称',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用代码类型多语言表';

/*Table structure for table `sys_menu` */

CREATE TABLE `sys_menu` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_APPLICATION_ID` varchar(50) NOT NULL COMMENT '应用ID',
  `CODE` varchar(50) NOT NULL COMMENT '菜单CODE',
  `IMAGE` varchar(100) DEFAULT NULL COMMENT '图标引用地址',
  `URL` text COMMENT '路径',
  `PARENT_ID` varchar(50) NOT NULL COMMENT '父菜单',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '菜单类型  0  根节点，\r\n1功能菜单，\r\n2门户小页\r\n ',
  `CSS` text COMMENT '样式\r\n针对门户小页',
  `HEIGHT` varchar(50) DEFAULT NULL COMMENT '高度\r\n针对门户小页',
  `SCROLL` varchar(1) DEFAULT NULL COMMENT '是否有滚动条\r\n针对门户小页',
  `REFRESH` varchar(50) DEFAULT NULL COMMENT '刷新频率\r\n针对门户小页',
  `ISCLOSE` varchar(1) DEFAULT NULL COMMENT '是否有关闭按钮\r\n针对门户小页\r\n',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态\r\n1启用，0禁用',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `MENU_GROUP` varchar(255) DEFAULT NULL COMMENT '菜单所属分组',
  `ISSHOWTITLE` varchar(1) DEFAULT NULL COMMENT '是否显示标题',
  `ISDRAG` varchar(1) DEFAULT NULL COMMENT '是否允许拖拽',
  `MOREURL` text COMMENT '其它跳转URL',
  `IS_ROBBIN` varchar(1) DEFAULT NULL,
  `MENU_VIEW` text,
  `ICON_CLASS` text COMMENT 'bootstrap图标',
  `ISMENU` varchar(1) DEFAULT NULL COMMENT '是否为菜单还是资源',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Table structure for table `sys_menu_tl` */

CREATE TABLE `sys_menu_tl` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_MENU_ID` varchar(50) NOT NULL COMMENT '菜单ID',
  `SYS_LANGUAGE_CODE` varchar(50) NOT NULL COMMENT '语言代码',
  `NAME` varchar(100) NOT NULL COMMENT '显示名称',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `COMMENTS` text COMMENT '菜单的描述信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单多语言表';

/*Table structure for table `sys_message` */

CREATE TABLE `sys_message` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `TITLE` varchar(255) NOT NULL COMMENT '消息的标题',
  `CONTENT` text COMMENT '消息的内容，可以含有HTML标签',
  `URL_ADDRESS` text COMMENT '打开该消息时转向的地址，一般为相对路径',
  `SEND_USER` varchar(50) NOT NULL COMMENT '消息发送人',
  `SEND_DATE` datetime NOT NULL COMMENT '发送日期',
  `RECV_USER` varchar(50) NOT NULL COMMENT '消息接收人',
  `RECV_DATE` datetime NOT NULL COMMENT '接收日期',
  `IS_READED` varchar(1) DEFAULT NULL COMMENT '消息是否已经被打开。1-已打开，0-未打开',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `URL_TYPE` varchar(2) DEFAULT NULL COMMENT 'URL类型，是URL就是1,是js方法，就是0。',
  `AUTO_DISAPPEAR` varchar(2) DEFAULT NULL COMMENT '是否自动消失',
  `DISAPPEAR_DATE` datetime DEFAULT NULL COMMENT '到期时间',
  `SEND_DEPTID` varchar(50) DEFAULT NULL COMMENT '发送人部门ID',
  `SYS_APPLICATION_ID` varchar(32) DEFAULT NULL COMMENT '所属应用ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息表，用于在系统中保存提示消息';

/*Table structure for table `sys_org` */

CREATE TABLE `sys_org` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ORG_CODE` varchar(100) NOT NULL COMMENT '组织代码',
  `PARENT_ORG_ID` varchar(100) NOT NULL COMMENT '父组织ID',
  `ORDER_BY` decimal(16,0) NOT NULL COMMENT '排序',
  `POST` varchar(50) DEFAULT NULL COMMENT '邮编',
  `TEL` varchar(50) DEFAULT NULL COMMENT '电话',
  `FAX` varchar(50) DEFAULT NULL COMMENT '传真',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮件',
  `WORK_CALENDAR_ID` varchar(50) DEFAULT NULL COMMENT '工作日历',
  `VALID_FLAG` varchar(1) NOT NULL COMMENT '设置组织是否有效，1-有效，0-无效',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `RESPONSIBLE_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '该组织下负责部门ID，这个部门不一定组织的下级部门，也可以是部门的子部门，组织和对外接口部门是一对一的关系。\r\n\r\n用于在流程引擎中和多项目管理中确定一个组织的负责部门',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多组织表';

/*Table structure for table `sys_org_tl` */

CREATE TABLE `sys_org_tl` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `SYS_LANGUAGE_CODE` varchar(50) NOT NULL COMMENT '语言代码',
  `ORG_NAME` varchar(100) NOT NULL COMMENT '名称',
  `ORG_DESC` varchar(240) DEFAULT NULL COMMENT '描述',
  `ORG_PLACE` varchar(100) DEFAULT NULL COMMENT '办公地点',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构多语言表';

/*Table structure for table `sys_password_configure` */

CREATE TABLE `sys_password_configure` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用id',
  `CONFIGURE_KEY` varchar(50) DEFAULT NULL COMMENT '密码规则代码',
  `CONFIGURE_NAME` varchar(100) DEFAULT NULL COMMENT '密码规则中文名称',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密码规则表';

/*Table structure for table `sys_password_templet` */

CREATE TABLE `sys_password_templet` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `TEMPLET_KEY` varchar(50) DEFAULT NULL COMMENT '密码规则KEY',
  `TEMPLET_VALUE` varchar(100) DEFAULT NULL COMMENT '密码规则值',
  `TEMPLET_STATE` varchar(1) DEFAULT NULL COMMENT '是否有效\r\n1有效，0无效， 默认1',
  `TEMPLET_TYPE` varchar(50) DEFAULT NULL COMMENT '密码模板类型（级别）',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密码模板配置表';

/*Table structure for table `sys_password_templet_level` */

CREATE TABLE `sys_password_templet_level` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `KEY` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `CODE` varchar(100) DEFAULT NULL COMMENT '模板编码',
  `USER_LEVEL_CODE` varchar(50) DEFAULT NULL COMMENT '人员密级\r\n关联数据字典',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密码模板表';

/*Table structure for table `sys_permission_access` */

CREATE TABLE `sys_permission_access` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `RESOURCE_ID` varchar(50) NOT NULL COMMENT '权限资源ID',
  `TYPE` decimal(1,0) DEFAULT NULL COMMENT '权限控制类型：3，全局；2，跨部门领导；1，部门领导；0，个人',
  `ACCESS_USER` longtext COMMENT '可以访问的用户',
  `ACCESS_ROLE` longtext COMMENT '可以访问的角色',
  `ACCESS_DEPT` longtext COMMENT '可以访问的部门',
  `ACCESS_GROUP` longtext COMMENT '可以访问的群组',
  `ACCESS_POSITION` longtext COMMENT '可以访问的岗位',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ISSQL` varchar(1) DEFAULT NULL COMMENT '1：sql语句，2：sql条件',
  `PRESQL` longtext COMMENT '前置SQL',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限分配表';

/*Table structure for table `sys_permission_leaddept` */

CREATE TABLE `sys_permission_leaddept` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USER_ID` varchar(50) NOT NULL COMMENT '员工id',
  `DEPT_ID` longtext COMMENT '所管部门id',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '最后修改ip',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据权限：领导业务部门表';

/*Table structure for table `sys_permission_resource` */

CREATE TABLE `sys_permission_resource` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(50) DEFAULT NULL COMMENT '对应的菜单页面id',
  `DATAGRID` text COMMENT '菜单中datagrid控件id',
  `DATASET` text COMMENT 'datagrid对应的dataset',
  `DATATYPE` text COMMENT 'dataset中的datatype',
  `METADATA` text COMMENT 'datatype中对应dbm文件中的compositeTable的id',
  `SQL` longtext COMMENT '资源过滤前置SQL',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '权限资源状态',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `IS_SECRET` varchar(1) DEFAULT NULL COMMENT '是否启用密级过滤，1：启用，0：不启用',
  `HAVE_SECRET` varchar(1) DEFAULT NULL COMMENT '是否拥有密级控制，0：没有，1：有',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统数据权限过滤资源表';

/*Table structure for table `sys_portal` */

CREATE TABLE `sys_portal` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_USER_ID` varchar(50) DEFAULT NULL COMMENT '当前登陆人',
  `LAYOUT_KEY` varchar(50) DEFAULT NULL COMMENT '布局key值',
  `LAYOUT_CONTENT` text COMMENT '布局内容',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` text COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` text COMMENT '扩展字段',
  `CHANNEL_ID` varchar(32) DEFAULT NULL COMMENT '频道表ID',
  `BLOCK_PROPERTY` text COMMENT '板块属性',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='portal表';

/*Table structure for table `sys_portlet_config` */

CREATE TABLE `sys_portlet_config` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户id',
  `LAYOUT_EXTENDS` text COMMENT '角色名称，以","分割',
  `LAYOUT` varchar(50) DEFAULT NULL COMMENT '布局',
  `ORDER_BY` decimal(16,0) NOT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(50) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LAST_UPDATE_IP` varchar(50) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `CONTENT` text,
  `NAME` varchar(50) DEFAULT NULL COMMENT '模版名称',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` text COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` text COMMENT '扩展字段',
  `ROLE_ID` text COMMENT '角色id集合，以","分割',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  `USAGE_MODIFIER` varchar(1) DEFAULT NULL COMMENT '0 公有可用     1私有可用',
  `INDEX_URL` text COMMENT '首页地址',
  `INDEX_TYPE` varchar(1) DEFAULT NULL COMMENT '首页类型0系统首页1自定义首页',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_portlet_menu` */

CREATE TABLE `sys_portlet_menu` (
  `ID` varchar(32) NOT NULL,
  `PORTLET_CONFIG_ID` varchar(32) NOT NULL,
  `PARENT_ID` varchar(32) NOT NULL,
  `TEXT` varchar(100) DEFAULT NULL,
  `BEFORE_ID` varchar(32) DEFAULT NULL,
  `AFTER_ID` varchar(32) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATED_BY` varchar(50) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) NOT NULL,
  `LAST_UPDATE_IP` varchar(50) NOT NULL,
  `VERSION` decimal(16,0) NOT NULL,
  `MENU_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='12';

/*Table structure for table `sys_position` */

CREATE TABLE `sys_position` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `POSITION_CODE` varchar(50) NOT NULL COMMENT '职位代码',
  `ORDER_BY` decimal(16,0) NOT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位表';

/*Table structure for table `sys_position_tl` */

CREATE TABLE `sys_position_tl` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_POSITION_ID` varchar(50) NOT NULL COMMENT '岗位表的主键',
  `SYS_LANGUAGE_CODE` varchar(50) NOT NULL COMMENT '语言代码',
  `POSITION_NAME` varchar(100) NOT NULL COMMENT '名称',
  `POSITION_DESC` varchar(240) DEFAULT NULL COMMENT '描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位多语言表';

/*Table structure for table `sys_profile_option` */

CREATE TABLE `sys_profile_option` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `PROFILE_OPTION_CODE` varchar(50) DEFAULT NULL COMMENT '配置文件代码',
  `PROFILE_OPTION_NAME` varchar(100) DEFAULT NULL COMMENT '配置文件名称',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `YN_MULTI_VALUE` varchar(1) DEFAULT NULL COMMENT '是否多值\r\nY 是 N 否',
  `USER_CHANGEABLE_FLAG` varchar(1) DEFAULT NULL COMMENT '用户可更新标识\r\nY代表可以，N代表不可以，默认为Y',
  `USER_VISIBLE_FLAG` varchar(1) DEFAULT NULL COMMENT '用户可查看标识\r\nY代表可以，N代表不可以，默认为Y',
  `SITE_ENABLED_FLAG` varchar(1) DEFAULT NULL COMMENT '地点层可见\r\nY代表可以，N代表不可以，默认为Y',
  `APP_ENABLED_FLAG` varchar(1) DEFAULT NULL COMMENT '应用程序层可见\r\nY代表可以，N代表不可以，默认为Y',
  `ROLE_ENABLED_FLAG` varchar(1) DEFAULT NULL COMMENT '角色层可见\r\nY代表可以，N代表不可以，默认为Y',
  `USER_ENABLED_FLAG` varchar(1) DEFAULT NULL COMMENT '用户层可见\r\nY代表可以，N代表不可以，默认为Y',
  `DEPT_ENABLED_FLAG` varchar(1) DEFAULT NULL COMMENT '部门启用标记\r\nY代表可以，N代表不可以，默认为Y',
  `SQL_VALIDATION` text COMMENT 'SQL校验\r\n用于配置文件选项的值列表的SQL验证',
  `VALID_FLAG` varchar(1) DEFAULT NULL COMMENT '状态\r\n1代表有效,0代表无效 默认为1',
  `SYSTEM_FLAG` varchar(1) DEFAULT NULL COMMENT '是否为系统初始\r\nY 是 N 否',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `USAGE_MODIFIER` varchar(1) DEFAULT NULL COMMENT '0 公有可用     1私有可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置管理表';

/*Table structure for table `sys_profile_option_value` */

CREATE TABLE `sys_profile_option_value` (
  `ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `SYS_PROFILE_OPTIONS_ID` varchar(50) DEFAULT NULL COMMENT '配置文件ID\r\n从SYS_PROFILE_OPTIONS表中获得ID的值',
  `PROFILE_LEVEL_CODE` varchar(1) DEFAULT NULL COMMENT '级别编号\r\n1、地点 2、应用 3、角色 4、用户 5、组织',
  `LEVEL_VALUE` varchar(50) DEFAULT NULL COMMENT '级别值\r\n地点ID、应用ID、角色ID、用户ID、组织ID',
  `PROFILE_OPTION_VALUE` text COMMENT '配置文件选项值\r\n对应PROFILE比表SQL的ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置文件选项表';

/*Table structure for table `sys_resource` */

CREATE TABLE `sys_resource` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `KEY` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `VALUE` text COMMENT '资源值',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '是全局表单还是节点表单，global，activity',
  `RESOURCE_DESC` text COMMENT '描述',
  `PARENT_ID` varchar(50) DEFAULT NULL COMMENT '父资源ID',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源，用于对资源进行权限控制';

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(50) NOT NULL COMMENT '角色代码',
  `ROLE_GROUP` varchar(50) DEFAULT NULL COMMENT '角色组，标示字段，用于区分不同的角色组，便于查询和分类',
  `ROLE_TYPE` varchar(1) NOT NULL COMMENT '角色类型，0代表系统内置角色，不能删除\r\n1代表由用户添加的角色',
  `SYS_APPLICATION_ID` varchar(50) NOT NULL COMMENT '应用ID',
  `ROLE_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '角色所属的部门，可选。用来表示该角色只在该部门内生效\r\n这是一个标示属性，进行权限判断时不会按照这个字段进行判断\r\n比如：生产管理部下的生产计划员',
  `ROLE_DESC` varchar(240) DEFAULT NULL COMMENT '描述',
  `VALID_FLAG` varchar(1) NOT NULL COMMENT '设置角色是否有效，1-有效，0-无效',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `USAGE_MODIFIER` varchar(1) DEFAULT NULL COMMENT '0 公有可用     1私有可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

/*Table structure for table `sys_secret_relation` */

CREATE TABLE `sys_secret_relation` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `WORD_SECRET` varchar(10) NOT NULL COMMENT '文档密级',
  `USER_SECRET` varchar(10) NOT NULL COMMENT '人员密级',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(32) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(20) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档密级与人员密级关系表';

/*Table structure for table `sys_sso_config` */

CREATE TABLE `sys_sso_config` (
  `ID` varchar(32) NOT NULL,
  `SSO_TYPE` varchar(30) DEFAULT NULL COMMENT '单点类型，如cas',
  `SSO_CONFIG` text COMMENT '单点配置，xml格式',
  `SSO_ENABLE_FLG` varchar(1) DEFAULT NULL COMMENT '单点启用标志，1 启用',
  `VERSION` decimal(16,0) DEFAULT NULL,
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `NAME` varchar(50) NOT NULL COMMENT '姓名',
  `NAME_EN` varchar(50) DEFAULT NULL COMMENT '英文名',
  `NO` varchar(50) NOT NULL COMMENT '用户编号',
  `LOGIN_NAME` varchar(50) NOT NULL COMMENT '登录名',
  `LOGIN_PASSWORD` varchar(50) NOT NULL COMMENT '登录密码',
  `SECRET_LEVEL` varchar(50) NOT NULL COMMENT '密级',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
  `SEX` varchar(2) DEFAULT NULL COMMENT '性别',
  `NATION` varchar(100) DEFAULT NULL COMMENT '民族',
  `BIRTH_ADDRESS` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `POLITY` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `WORK_DATE` datetime DEFAULT NULL COMMENT '工作日期',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '职称',
  `DEGREE` varchar(50) DEFAULT NULL COMMENT '学历',
  `EDUCATION` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `MOBILE` varchar(50) DEFAULT NULL COMMENT '手机',
  `OFFICE_TEL` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `FAX` varchar(50) DEFAULT NULL COMMENT '传真',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮件',
  `WORK_SPACE` varchar(100) DEFAULT NULL COMMENT '工作地点',
  `ROOM_NO` varchar(50) DEFAULT NULL COMMENT '房间号',
  `HOME_ADDRESS` varchar(100) DEFAULT NULL COMMENT '家庭地址',
  `HOME_ZIP` varchar(50) DEFAULT NULL COMMENT '家庭邮编',
  `HOME_TEL` varchar(50) DEFAULT NULL COMMENT '家庭电话',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '类型',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '1:正常的用户状态3:无效用户状态',
  `REMARK` varchar(240) DEFAULT NULL COMMENT '描述',
  `LANGUAGE_CODE` varchar(50) DEFAULT NULL COMMENT '默认语言',
  `LAST_LOGIN_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '上次登录部门',
  `LAST_PASSWORD_DATE` datetime DEFAULT NULL COMMENT '上次修改密码时间',
  `LAST_LOGIN_DATE` datetime DEFAULT NULL COMMENT '上次登录时间',
  `PASSWORD_DAYS` decimal(16,0) DEFAULT NULL COMMENT '密码有效天数',
  `LOGIN_FAIL_TIMES` decimal(16,0) DEFAULT NULL COMMENT '密码登录失败次数',
  `USER_LOCKED` varchar(1) DEFAULT NULL COMMENT '0:没有锁定 1：密码超时没修改锁定2：输错次数过多被锁定，超时自动解锁3：完全锁定',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `LOGIN_FAIL_FIRST_TIME` datetime DEFAULT NULL COMMENT '第一次登录失败时间',
  `UNIT_CODE` varchar(255) DEFAULT NULL COMMENT '集团统一编码',
  `PHOTO` longblob COMMENT '照片',
  `SIGN` longblob COMMENT '签名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户标';

/*Table structure for table `sys_user_dept_position` */

CREATE TABLE `sys_user_dept_position` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门ID',
  `POSITION_ID` varchar(50) NOT NULL COMMENT '职位ID',
  `IS_CHIEF_DEPT` varchar(1) NOT NULL COMMENT '是否是主部门',
  `IS_MANAGER` varchar(1) DEFAULT NULL COMMENT '是否是部门主管',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门岗位关系表';

/*Table structure for table `sys_user_group` */

CREATE TABLE `sys_user_group` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `GROUP_ID` varchar(50) NOT NULL COMMENT '群组ID',
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户群组关系表';

/*Table structure for table `sys_user_limit_ip` */

CREATE TABLE `sys_user_limit_ip` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `USER_LIMIT_USER_ID` varchar(32) DEFAULT NULL COMMENT '受到限人ID',
  `LIMIT_TYPE_IP_TYPE` varchar(32) NOT NULL COMMENT '0：IP点，1：IP段',
  `LIMIT_USER_TYPE` varchar(32) NOT NULL COMMENT '分为用户不能访问；用户只能访问',
  `USER_LIMIT_IP_FROM` varchar(50) NOT NULL COMMENT '开始IP',
  `USER_LIMIT_IP_END` varchar(50) DEFAULT NULL COMMENT '结束IP',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限制用户登陆IP';

/*Table structure for table `sys_user_lock_log` */

CREATE TABLE `sys_user_lock_log` (
  `ID` varchar(50) NOT NULL,
  `SYS_USER_NAME` varchar(50) NOT NULL,
  `LOCK_IP` varchar(50) DEFAULT NULL,
  `LOCK_DATE` datetime DEFAULT NULL,
  `LOCK_CONTENT` text,
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL,
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL,
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `VERSION` decimal(16,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_old_password` */

CREATE TABLE `sys_user_old_password` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `USER_PASSWORD` varchar(255) NOT NULL COMMENT '用户密码',
  `USER_MODIFY_DATE` datetime NOT NULL COMMENT '分为用户不能访问；用户只能访问',
  `MANAGER_FLAG` varchar(50) NOT NULL COMMENT '用户修改日期',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户历史密码表，保存用户以前的密码';

/*Table structure for table `sys_user_relation` */

CREATE TABLE `sys_user_relation` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USER1_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `USER2_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `RELATION` varchar(50) NOT NULL COMMENT '关系',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织ID',
  `VALID_FLAG` varchar(1) NOT NULL COMMENT '关系是否有效，1-有效，0-无效',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关系表';

/*Table structure for table `sys_user_role` */

CREATE TABLE `sys_user_role` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `SYS_ROLE_ID` varchar(50) NOT NULL COMMENT '角色ID',
  `SYS_USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*Table structure for table `sys_word` */

CREATE TABLE `sys_word` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `WORD_CODE` text NOT NULL COMMENT '正文代码',
  `WORD_NAME` text NOT NULL COMMENT '正文名称',
  `WORD_TYPE` varchar(10) NOT NULL COMMENT '正文类型',
  `WORD_BODY` longblob NOT NULL COMMENT '正文',
  `WORD_BAK` longblob COMMENT '正文副本',
  `WORD_STATE` varchar(10) NOT NULL COMMENT '正文状态',
  `WORD_LOCK_USER` varchar(50) DEFAULT NULL COMMENT '正文锁定用户',
  `WORD_LOCK_TIME` datetime DEFAULT NULL COMMENT '正文锁定时间',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(32) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(20) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='正文表';

/*Table structure for table `sys_word_seal` */

CREATE TABLE `sys_word_seal` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `SEAL_NAME` text NOT NULL COMMENT '印章名称',
  `SEAL_BODY` longblob COMMENT '印章文件',
  `SEAL_ADMINS` text COMMENT '访问印章的角色，以逗号分隔',
  `REMARK` text COMMENT '印章描述',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='印章表';

/*Table structure for table `sys_word_templet` */

CREATE TABLE `sys_word_templet` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `TEMPLET_NAME` text NOT NULL COMMENT '模板名称',
  `TEMPLET_BODY` longblob COMMENT '模板',
  `TEMPLET_TYPE` varchar(10) NOT NULL COMMENT '模板类型:1为正文模板，2为红头模板',
  `TEMPLET_VERSION` decimal(16,0) DEFAULT NULL COMMENT '模板版本',
  `TEMPLET_STATE` varchar(10) NOT NULL COMMENT '是否有效',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '模板排序',
  `FLOW_KEY` text COMMENT '所属流程KEY',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(32) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(20) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='正文模板表';

/*Table structure for table `sys_word_templet_detail` */

CREATE TABLE `sys_word_templet_detail` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `TEMPLET_ID` varchar(50) NOT NULL COMMENT '模板ID',
  `TEMPLET_TABLE` varchar(100) NOT NULL COMMENT '模板对应表',
  `TEMPLET_COLUMN` varchar(100) NOT NULL COMMENT '模板字段',
  `TEMPLET_COLUMN_TYPE` varchar(100) NOT NULL COMMENT '模板字段类型',
  `TEMPLET_COLUMN_NAME` text NOT NULL COMMENT '模板字段名称',
  `TEMPLET_COLUMN_NOTE` text COMMENT '模板字段备注',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(32) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(32) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(20) NOT NULL COMMENT '最后更新IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='正文模板同步域表';

/*Table structure for table `sys_work_calendar` */

CREATE TABLE `sys_work_calendar` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `CALENDAR_NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `CALENDAR_DEFINE` longtext COMMENT '定义文件',
  `CALENDAR_DESC` varchar(240) DEFAULT NULL COMMENT '描述',
  `LAST_UPDATE_DATE` datetime NOT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) NOT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) NOT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) NOT NULL COMMENT '版本',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作日历表';

/*Table structure for table `sys_work_hand` */

CREATE TABLE `sys_work_hand` (
  `ID` varchar(50) NOT NULL COMMENT '主键ID',
  `HAND_REGISTER_USER_ID` varchar(50) DEFAULT NULL COMMENT '登记人',
  `HAND_REGISTER_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '登记人部门',
  `HAND_REGISTER_ORG_ID` varchar(50) DEFAULT NULL COMMENT '登记人组织',
  `WORK_OWNER_ID` varchar(50) DEFAULT NULL COMMENT '移交人',
  `WORK_OWNER_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '移交人部门',
  `WORK_OWNER_ORG_ID` varchar(50) DEFAULT NULL COMMENT '移交人组织',
  `RECEPT_USER_ID` varchar(50) DEFAULT NULL COMMENT '接收人',
  `RECEPT_DEPT_ID` varchar(50) DEFAULT NULL COMMENT '接收人部门',
  `RECEPT_ORG_ID` varchar(50) DEFAULT NULL COMMENT '接受人组织',
  `HAND_REASON` text COMMENT '移交原因',
  `HAND_DATE` datetime DEFAULT NULL COMMENT '登记日期',
  `BACK_DATE` datetime DEFAULT NULL COMMENT '预计返回日期',
  `HAND_EFFECTIVE_DATE` datetime DEFAULT NULL COMMENT '生效日期',
  `VALID_FLAG` varchar(2) DEFAULT NULL COMMENT '是否有效',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `CREATION_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL COMMENT '修改IP',
  `VERSION` decimal(16,0) DEFAULT NULL COMMENT '版本',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作移交表';

/*Table structure for table `sys_workbench_menu` */

CREATE TABLE `sys_workbench_menu` (
  `ID` varchar(50) NOT NULL,
  `SYSUSERID` varchar(200) DEFAULT NULL,
  `SYSMENUID` varchar(200) DEFAULT NULL,
  `ALIASMENUNAME` varchar(200) DEFAULT NULL,
  `ICONCLASS` varchar(200) DEFAULT NULL,
  `SORT` decimal(65,30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_workbench_portlet_config` */

CREATE TABLE `sys_workbench_portlet_config` (
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `USERID` varchar(100) DEFAULT NULL COMMENT '用户id',
  `LAYOUT_EXTENDS` text COMMENT '角色名称，以","分割',
  `LAYOUT` varchar(50) DEFAULT NULL COMMENT '布局',
  `ORDER_BY` decimal(16,0) DEFAULT NULL COMMENT '排序',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(50) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_IP` varchar(50) DEFAULT NULL,
  `VERSION` decimal(16,0) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL COMMENT '模版名称',
  `ATTRIBUTE_01` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_02` text COMMENT '扩展字段',
  `ATTRIBUTE_03` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_04` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_05` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_06` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_07` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_08` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_09` varchar(255) DEFAULT NULL COMMENT '扩展字段',
  `ATTRIBUTE_10` text COMMENT '扩展字段',
  `ROLE_ID` text COMMENT '角色id集合，以","分割',
  `SYS_APPLICATION_ID` varchar(50) DEFAULT NULL,
  `INDEX_URL` text COMMENT '首页地址',
  `INDEX_TYPE` varchar(1) DEFAULT NULL COMMENT '首页类型0系统首页1自定义首页',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_document` */

CREATE TABLE `t_document` (
  `ID` text NOT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `FORMNAME` text,
  `OWNER` text,
  `AUDITDATE` datetime DEFAULT NULL,
  `AUTHOR` text,
  `AUTHOR_DEPT_INDEX` text,
  `CREATED` datetime DEFAULT NULL,
  `ISSUBDOC` decimal(1,0) DEFAULT NULL,
  `FORMID` text,
  `ISTMP` decimal(1,0) DEFAULT NULL,
  `VERSIONS` decimal(10,0) DEFAULT NULL,
  `SORTID` text,
  `APPLICATIONID` text,
  `STATELABEL` text,
  `INITIATOR` text,
  `AUDITUSER` text,
  `AUDITORNAMES` longtext,
  `LASTFLOWOPERATION` text,
  `PARENT` text,
  `STATE` text,
  `STATEINT` decimal(10,0) DEFAULT NULL,
  `LASTMODIFIER` text,
  `DOMAINID` text,
  `AUDITORLIST` longtext,
  `MAPPINGID` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_upload` */

CREATE TABLE `t_upload` (
  `ID` text,
  `NAME` text,
  `IMGBINARY` longblob,
  `FIELDID` text,
  `TYPE` text,
  `FILESIZE` decimal(10,0) DEFAULT NULL,
  `USERID` text,
  `MODIFYDATE` text,
  `PATH` text,
  `FOLDERPATH` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bpm_actinst_console_v` */

DROP TABLE IF EXISTS `bpm_actinst_console_v`;

/*!50001 CREATE TABLE  `bpm_actinst_console_v`(
 `dbId` varchar(50) ,
 `pdId` varchar(255) ,
 `entryId` varchar(50) ,
 `TYPE` varchar(255) ,
 `executionId` varchar(255) ,
 `activityName` varchar(255) ,
 `startTime` datetime ,
 `endTime` datetime ,
 `duration` decimal(19,0) ,
 `activityAlias` varchar(255) 
)*/;

/*Table structure for table `bpm_client_hist_procinst_v` */

DROP TABLE IF EXISTS `bpm_client_hist_procinst_v`;

/*!50001 CREATE TABLE  `bpm_client_hist_procinst_v`(
 `DBID_` varchar(50) ,
 `ID_` varchar(255) ,
 `PROCDEFID_` varchar(255) ,
 `START_` datetime ,
 `END_` datetime ,
 `STATE_` varchar(255) ,
 `FORMID_` varchar(50) ,
 `TITLE_` varchar(255) ,
 `USERID_` varchar(255) ,
 `DEPTID_` varchar(255) ,
 `BUSINESSSTATE_` varchar(255) ,
 `ACTIVITYALIAS_` text ,
 `LAST_UPDATE_DATE_` datetime 
)*/;

/*Table structure for table `bpm_client_hist_task_v` */

DROP TABLE IF EXISTS `bpm_client_hist_task_v`;

/*!50001 CREATE TABLE  `bpm_client_hist_task_v`(
 `DBID_` varchar(50) ,
 `EXECUTION_` varchar(255) ,
 `OUTCOME_` varchar(255) ,
 `ASSIGNEE_` varchar(255) ,
 `STATE_` varchar(255) ,
 `CREATE_` datetime ,
 `END_` datetime ,
 `TASK_ORDER_` varchar(255) ,
 `OPEN_` datetime ,
 `TAKE_STATE_` varchar(255) ,
 `PROCINST_` varchar(50) ,
 `TASK_TYPE_` varchar(255) ,
 `TASK_B_ID_` varchar(255) ,
 `TASK_TITLE_` varchar(255) ,
 `TASK_SENDUSER_` varchar(255) ,
 `TASK_SENDDEPT_` varchar(255) ,
 `FORM_` varchar(255) ,
 `PROCESS_DEF_NAME_` varchar(255) ,
 `TASK_FINISHED_` varchar(255) ,
 `TASK_STATE_` varchar(255) ,
 `TASK_NAME_` varchar(255) ,
 `HISACTINST_` varchar(50) ,
 `WORKHAND_TYPE_` varchar(255) ,
 `WORKHAND_USER_` varchar(255) ,
 `APP_ID_` varchar(255) ,
 `ASSIGNEE_DEPT_` varchar(255) ,
 `LAST_UPDATE_DATE_` datetime 
)*/;

/*Table structure for table `bpm_def_dept_v` */

DROP TABLE IF EXISTS `bpm_def_dept_v`;

/*!50001 CREATE TABLE  `bpm_def_dept_v`(
 `id` varchar(50) ,
 `dept_code` varchar(100) ,
 `dept_name` varchar(100) ,
 `parent_dept_id` varchar(50) ,
 `org_id` varchar(50) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_group_v` */

DROP TABLE IF EXISTS `bpm_def_group_v`;

/*!50001 CREATE TABLE  `bpm_def_group_v`(
 `id` varchar(50) ,
 `group_name` varchar(100) ,
 `group_parent_id` varchar(50) ,
 `type` varchar(1) ,
 `org_id` varchar(50) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_org_v` */

DROP TABLE IF EXISTS `bpm_def_org_v`;

/*!50001 CREATE TABLE  `bpm_def_org_v`(
 `id` varchar(50) ,
 `org_code` varchar(100) ,
 `org_name` varchar(100) ,
 `parent_org_id` varchar(100) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_position_v` */

DROP TABLE IF EXISTS `bpm_def_position_v`;

/*!50001 CREATE TABLE  `bpm_def_position_v`(
 `id` varchar(50) ,
 `position_code` varchar(50) ,
 `position_name` varchar(100) ,
 `org_id` varchar(50) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_role_v` */

DROP TABLE IF EXISTS `bpm_def_role_v`;

/*!50001 CREATE TABLE  `bpm_def_role_v`(
 `id` varchar(50) ,
 `role_code` varchar(50) ,
 `role_name` varchar(50) ,
 `role_group` varchar(50) ,
 `role_type` varchar(1) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_user_dept_position_v` */

DROP TABLE IF EXISTS `bpm_def_user_dept_position_v`;

/*!50001 CREATE TABLE  `bpm_def_user_dept_position_v`(
 `id` varchar(50) ,
 `user_id` varchar(50) ,
 `dept_id` varchar(50) ,
 `position_id` varchar(50) ,
 `is_chief_dept` varchar(1) ,
 `is_manager` varchar(1) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_def_user_v` */

DROP TABLE IF EXISTS `bpm_def_user_v`;

/*!50001 CREATE TABLE  `bpm_def_user_v`(
 `id` varchar(50) ,
 `name` varchar(50) ,
 `login_name` varchar(50) ,
 `login_password` varchar(50) ,
 `status` varchar(2) ,
 `order_by` decimal(16,0) 
)*/;

/*Table structure for table `bpm_entry_console_v` */

DROP TABLE IF EXISTS `bpm_entry_console_v`;

/*!50001 CREATE TABLE  `bpm_entry_console_v`(
 `entryId` varchar(50) ,
 `entryName` varchar(255) ,
 `defineName` varchar(255) ,
 `state` varchar(255) ,
 `userId` varchar(255) ,
 `deptId` varchar(255) ,
 `startDate` datetime ,
 `endDate` datetime ,
 `keyone` varchar(255) ,
 `deployment` varchar(50) ,
 `executionId` varchar(255) ,
 `pdId` varchar(255) ,
 `duration` decimal(19,0) ,
 `formId` varchar(50) ,
 `LOCKED_STATUS_` varchar(30) 
)*/;

/*Table structure for table `bpm_entry_v` */

DROP TABLE IF EXISTS `bpm_entry_v`;

/*!50001 CREATE TABLE  `bpm_entry_v`(
 `DBID_` varchar(50) ,
 `DBVERSION_` decimal(10,0) ,
 `ID_` varchar(255) ,
 `PROCDEFID_` varchar(255) ,
 `KEY_` varchar(255) ,
 `START_` datetime ,
 `END_` datetime ,
 `DURATION_` decimal(19,0) ,
 `STATE_` varchar(255) ,
 `ENDACTIVITY_` varchar(255) ,
 `NEXTIDX_` decimal(10,0) ,
 `FORMID_` varchar(50) ,
 `USERID_` varchar(255) ,
 `DEPTID_` varchar(255) ,
 `activityname_` varchar(255) ,
 `activityalias_` varchar(255) ,
 `businessstate_` varchar(255) 
)*/;

/*Table structure for table `bpm_hist_procinst_v` */

DROP TABLE IF EXISTS `bpm_hist_procinst_v`;

/*!50001 CREATE TABLE  `bpm_hist_procinst_v`(
 `DBID_` varchar(50) ,
 `DBVERSION_` decimal(10,0) ,
 `ID_` varchar(255) ,
 `PROCDEFID_` varchar(255) ,
 `KEY_` varchar(255) ,
 `START_` datetime ,
 `END_` datetime ,
 `DURATION_` decimal(19,0) ,
 `STATE_` varchar(255) ,
 `ENDACTIVITY_` varchar(255) ,
 `NEXTIDX_` decimal(10,0) ,
 `FORMID_` varchar(50) ,
 `TITLE_` varchar(255) ,
 `USERID_` varchar(255) ,
 `DEPTID_` varchar(255) ,
 `POSITIONID_` varchar(50) 
)*/;

/*Table structure for table `bpm_hist_task_analys_v` */

DROP TABLE IF EXISTS `bpm_hist_task_analys_v`;

/*!50001 CREATE TABLE  `bpm_hist_task_analys_v`(
 `DBID_` varchar(50) ,
 `DBVERSION_` decimal(10,0) ,
 `EXECUTION_` varchar(255) ,
 `OUTCOME_` varchar(255) ,
 `ASSIGNEE_` varchar(255) ,
 `PRIORITY_` decimal(10,0) ,
 `STATE_` varchar(255) ,
 `CREATE_` datetime ,
 `END_` datetime ,
 `DURATION_` decimal(19,0) ,
 `NEXTIDX_` decimal(10,0) ,
 `SUPERTASK_` varchar(50) ,
 `TASK_ORDER_` varchar(255) ,
 `OPEN_` datetime ,
 `TAKE_STATE_` varchar(255) ,
 `PROCINST_` varchar(50) ,
 `DESCR_` longtext ,
 `SUSPHISTSTATE_` varchar(255) ,
 `TASK_TYPE_` varchar(255) ,
 `TASK_B_ID_` varchar(255) ,
 `TASK_TITLE_` varchar(255) ,
 `TASK_SENDUSER_` varchar(255) ,
 `TASK_SENDDEPT_` varchar(255) ,
 `FORM_` varchar(255) ,
 `PROCESS_DEF_NAME_` varchar(255) ,
 `TASK_FINISHED_` varchar(255) ,
 `TASK_STATE_` varchar(255) ,
 `TASK_NAME_` varchar(255) ,
 `HISACTINST_` varchar(50) ,
 `WORKHAND_TYPE_` varchar(255) ,
 `WORKHAND_USER_` varchar(255) ,
 `APP_ID_` varchar(255) ,
 `ASSIGNEE_DEPT_` varchar(255) ,
 `ASSIGNEE_POSITION_` varchar(50) 
)*/;

/*Table structure for table `bpm_hist_task_v` */

DROP TABLE IF EXISTS `bpm_hist_task_v`;

/*!50001 CREATE TABLE  `bpm_hist_task_v`(
 `DBID_` varchar(50) ,
 `DBVERSION_` decimal(10,0) ,
 `EXECUTION_` varchar(255) ,
 `OUTCOME_` varchar(255) ,
 `ASSIGNEE_` varchar(255) ,
 `PRIORITY_` decimal(10,0) ,
 `STATE_` varchar(255) ,
 `CREATE_` datetime ,
 `END_` datetime ,
 `DURATION_` decimal(19,0) ,
 `NEXTIDX_` decimal(10,0) ,
 `SUPERTASK_` varchar(50) ,
 `TASK_ORDER_` varchar(255) ,
 `OPEN_` datetime ,
 `TAKE_STATE_` varchar(255) ,
 `PROCINST_` varchar(50) ,
 `DESCR_` longtext ,
 `SUSPHISTSTATE_` varchar(255) ,
 `TASK_TYPE_` varchar(255) ,
 `TASK_B_ID_` varchar(255) ,
 `TASK_TITLE_` varchar(255) ,
 `TASK_SENDUSER_` varchar(255) ,
 `TASK_SENDDEPT_` varchar(255) ,
 `FORM_` varchar(255) ,
 `PROCESS_DEF_NAME_` varchar(255) ,
 `TASK_FINISHED_` varchar(255) ,
 `TASK_STATE_` varchar(255) ,
 `TASK_NAME_` varchar(255) ,
 `HISACTINST_` varchar(50) ,
 `WORKHAND_TYPE_` varchar(255) ,
 `WORKHAND_USER_` varchar(255) ,
 `APP_ID_` varchar(255) ,
 `ASSIGNEE_DEPT_` varchar(255) ,
 `LOCKED_STATUS_` varchar(30) ,
 `ASSIGNEE_POSITION_` varchar(50) 
)*/;

/*Table structure for table `bpm_process_deploy_v` */

DROP TABLE IF EXISTS `bpm_process_deploy_v`;

/*!50001 CREATE TABLE  `bpm_process_deploy_v`(
 `DBID_` varchar(32) ,
 `KEY_` varchar(100) ,
 `NAME_` varchar(100) ,
 `DESC_` text ,
 `APP_` varchar(100) ,
 `DEF_ID_` varchar(50) ,
 `DEF_TYPE_` varchar(1) ,
 `XML_` longblob ,
 `VERSION_` decimal(19,0) 
)*/;

/*Table structure for table `sys_dept_org_position_v` */

DROP TABLE IF EXISTS `sys_dept_org_position_v`;

/*!50001 CREATE TABLE  `sys_dept_org_position_v`(
 `id` varchar(50) ,
 `user_id` varchar(50) ,
 `user_name` varchar(50) ,
 `dept_id` varchar(50) ,
 `dept_name` varchar(100) ,
 `org_id` varchar(50) ,
 `position_id` varchar(50) ,
 `position_name` varchar(100) ,
 `is_chief_dept` varchar(1) 
)*/;

/*Table structure for table `sys_dept_v` */

DROP TABLE IF EXISTS `sys_dept_v`;

/*!50001 CREATE TABLE  `sys_dept_v`(
 `id` varchar(50) ,
 `dept_code` varchar(100) ,
 `parent_dept_id` varchar(50) ,
 `org_id` varchar(50) ,
 `order_by` decimal(16,0) ,
 `post` varchar(50) ,
 `tel` varchar(50) ,
 `fax` varchar(50) ,
 `email` varchar(50) ,
 `dept_alias` varchar(50) ,
 `valid_flag` varchar(1) ,
 `work_calendar_id` varchar(50) ,
 `dept_name` varchar(100) ,
 `dept_desc` varchar(240) ,
 `dept_place` varchar(100) 
)*/;

/*Table structure for table `sys_lookup_v` */

DROP TABLE IF EXISTS `sys_lookup_v`;

/*!50001 CREATE TABLE  `sys_lookup_v`(
 `id` varchar(50) ,
 `lookup_type` varchar(50) ,
 `sys_application_id` varchar(50) ,
 `lookup_type_name` varchar(100) ,
 `lookup_code` text ,
 `lookup_id` varchar(50) ,
 `lookup_name` varchar(100) 
)*/;

/*Table structure for table `sys_org_v` */

DROP TABLE IF EXISTS `sys_org_v`;

/*!50001 CREATE TABLE  `sys_org_v`(
 `ID` varchar(50) ,
 `ORG_CODE` varchar(100) ,
 `PARENT_ORG_ID` varchar(100) ,
 `ORDER_BY` decimal(16,0) ,
 `POST` varchar(50) ,
 `TEL` varchar(50) ,
 `FAX` varchar(50) ,
 `EMAIL` varchar(50) ,
 `WORK_CALENDAR_ID` varchar(50) ,
 `VALID_FLAG` varchar(1) ,
 `responsible_dept_id` varchar(50) ,
 `org_name` varchar(100) ,
 `org_desc` varchar(240) ,
 `org_place` varchar(100) 
)*/;

/*Table structure for table `sys_position_v` */

DROP TABLE IF EXISTS `sys_position_v`;

/*!50001 CREATE TABLE  `sys_position_v`(
 `ID` varchar(50) ,
 `ORG_ID` varchar(50) ,
 `POSITION_CODE` varchar(50) ,
 `ORDER_BY` decimal(16,0) ,
 `LAST_UPDATE_DATE` datetime ,
 `LAST_UPDATED_BY` varchar(50) ,
 `CREATION_DATE` datetime ,
 `CREATED_BY` varchar(50) ,
 `LAST_UPDATE_IP` varchar(50) ,
 `VERSION` decimal(16,0) ,
 `ATTRIBUTE_01` varchar(255) ,
 `ATTRIBUTE_02` varchar(255) ,
 `ATTRIBUTE_03` varchar(255) ,
 `ATTRIBUTE_04` varchar(255) ,
 `ATTRIBUTE_05` varchar(255) ,
 `ATTRIBUTE_06` varchar(255) ,
 `ATTRIBUTE_07` varchar(255) ,
 `ATTRIBUTE_08` varchar(255) ,
 `ATTRIBUTE_09` varchar(255) ,
 `ATTRIBUTE_10` varchar(255) ,
 `position_name` varchar(100) ,
 `position_desc` varchar(240) 
)*/;

/*View structure for view bpm_actinst_console_v */

/*!50001 DROP TABLE IF EXISTS `bpm_actinst_console_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_actinst_console_v` AS select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext1` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext2` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext3` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext4` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext5` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext6` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext7` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) union all select `t2`.`DBID_` AS `dbId`,`t1`.`PROCDEFID_` AS `pdId`,`t2`.`HPROCI_` AS `entryId`,`t2`.`TYPE_` AS `TYPE`,`t2`.`EXECUTION_` AS `executionId`,`t2`.`ACTIVITY_NAME_` AS `activityName`,`t2`.`START_` AS `startTime`,`t2`.`END_` AS `endTime`,`t2`.`DURATION_` AS `duration`,`t2`.`ALIAS_` AS `activityAlias` from (`bpm_hist_procinst` `t1` left join `bpm_hist_actinst_ext8` `t2` on((`t1`.`DBID_` = `t2`.`HPROCI_`))) */;

/*View structure for view bpm_client_hist_procinst_v */

/*!50001 DROP TABLE IF EXISTS `bpm_client_hist_procinst_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_client_hist_procinst_v` AS select `sys_bpm_hist_procinst`.`DBID_` AS `DBID_`,`sys_bpm_hist_procinst`.`ID_` AS `ID_`,`sys_bpm_hist_procinst`.`PROCDEFID_` AS `PROCDEFID_`,`sys_bpm_hist_procinst`.`START_` AS `START_`,`sys_bpm_hist_procinst`.`END_` AS `END_`,`sys_bpm_hist_procinst`.`STATE_` AS `STATE_`,`sys_bpm_hist_procinst`.`FORMID_` AS `FORMID_`,`sys_bpm_hist_procinst`.`TITLE_` AS `TITLE_`,`sys_bpm_hist_procinst`.`USERID_` AS `USERID_`,`sys_bpm_hist_procinst`.`DEPTID_` AS `DEPTID_`,`sys_bpm_hist_procinst`.`BUSINESSSTATE_` AS `BUSINESSSTATE_`,`sys_bpm_hist_procinst`.`ACTIVITYALIAS_` AS `ACTIVITYALIAS_`,`sys_bpm_hist_procinst`.`LAST_UPDATE_DATE_` AS `LAST_UPDATE_DATE_` from `sys_bpm_hist_procinst` */;

/*View structure for view bpm_client_hist_task_v */

/*!50001 DROP TABLE IF EXISTS `bpm_client_hist_task_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_client_hist_task_v` AS select `sys_bpm_hist_task`.`DBID_` AS `DBID_`,`sys_bpm_hist_task`.`EXECUTION_` AS `EXECUTION_`,`sys_bpm_hist_task`.`OUTCOME_` AS `OUTCOME_`,`sys_bpm_hist_task`.`ASSIGNEE_` AS `ASSIGNEE_`,`sys_bpm_hist_task`.`STATE_` AS `STATE_`,`sys_bpm_hist_task`.`CREATE_` AS `CREATE_`,`sys_bpm_hist_task`.`END_` AS `END_`,`sys_bpm_hist_task`.`TASK_ORDER_` AS `TASK_ORDER_`,`sys_bpm_hist_task`.`OPEN_` AS `OPEN_`,`sys_bpm_hist_task`.`TAKE_STATE_` AS `TAKE_STATE_`,`sys_bpm_hist_task`.`PROCINST_` AS `PROCINST_`,`sys_bpm_hist_task`.`TASK_TYPE_` AS `TASK_TYPE_`,`sys_bpm_hist_task`.`TASK_B_ID_` AS `TASK_B_ID_`,`sys_bpm_hist_task`.`TASK_TITLE_` AS `TASK_TITLE_`,`sys_bpm_hist_task`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`sys_bpm_hist_task`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`sys_bpm_hist_task`.`FORM_` AS `FORM_`,`sys_bpm_hist_task`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`sys_bpm_hist_task`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`sys_bpm_hist_task`.`TASK_STATE_` AS `TASK_STATE_`,`sys_bpm_hist_task`.`TASK_NAME_` AS `TASK_NAME_`,`sys_bpm_hist_task`.`HISACTINST_` AS `HISACTINST_`,`sys_bpm_hist_task`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`sys_bpm_hist_task`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`sys_bpm_hist_task`.`APP_ID_` AS `APP_ID_`,`sys_bpm_hist_task`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,`sys_bpm_hist_task`.`LAST_UPDATE_DATE_` AS `LAST_UPDATE_DATE_` from `sys_bpm_hist_task` */;

/*View structure for view bpm_def_dept_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_dept_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_dept_v` AS select `t`.`ID` AS `id`,`t`.`DEPT_CODE` AS `dept_code`,`tl`.`DEPT_NAME` AS `dept_name`,`t`.`PARENT_DEPT_ID` AS `parent_dept_id`,`t`.`ORG_ID` AS `org_id`,`t`.`ORDER_BY` AS `order_by` from (`sys_dept` `t` left join `sys_dept_tl` `tl` on((`t`.`ID` = `tl`.`SYS_DEPT_ID`))) where ((`t`.`VALID_FLAG` = '1') and (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN')) */;

/*View structure for view bpm_def_group_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_group_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_group_v` AS select `t`.`ID` AS `id`,`tl`.`GROUP_NAME` AS `group_name`,`t`.`GROUP_PARENT_ID` AS `group_parent_id`,`t`.`TYPE` AS `type`,`t`.`ORG_ID` AS `org_id`,`t`.`ORDER_BY` AS `order_by` from (`sys_group` `t` left join `sys_group_tl` `tl` on((`t`.`ID` = `tl`.`SYS_GROUP_ID`))) where ((`t`.`VALID_FLAG` = '1') and (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN')) */;

/*View structure for view bpm_def_org_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_org_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_org_v` AS select `t`.`ID` AS `id`,`t`.`ORG_CODE` AS `org_code`,`tl`.`ORG_NAME` AS `org_name`,`t`.`PARENT_ORG_ID` AS `parent_org_id`,`t`.`ORDER_BY` AS `order_by` from (`sys_org` `t` left join `sys_org_tl` `tl` on((`t`.`ID` = `tl`.`SYS_ORG_ID`))) where ((`t`.`VALID_FLAG` = '1') and (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN')) */;

/*View structure for view bpm_def_position_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_position_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_position_v` AS select `t`.`ID` AS `id`,`t`.`POSITION_CODE` AS `position_code`,`tl`.`POSITION_NAME` AS `position_name`,`t`.`ORG_ID` AS `org_id`,`t`.`ORDER_BY` AS `order_by` from (`sys_position` `t` left join `sys_position_tl` `tl` on((`t`.`ID` = `tl`.`SYS_POSITION_ID`))) where (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN') */;

/*View structure for view bpm_def_role_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_role_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_role_v` AS select `t`.`ID` AS `id`,`t`.`ROLE_CODE` AS `role_code`,`t`.`ROLE_NAME` AS `role_name`,`t`.`ROLE_GROUP` AS `role_group`,`t`.`ROLE_TYPE` AS `role_type`,`t`.`ORDER_BY` AS `order_by` from `sys_role` `t` where (`t`.`VALID_FLAG` = '1') */;

/*View structure for view bpm_def_user_dept_position_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_user_dept_position_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_user_dept_position_v` AS select `t`.`ID` AS `id`,`t`.`USER_ID` AS `user_id`,`t`.`DEPT_ID` AS `dept_id`,`t`.`POSITION_ID` AS `position_id`,`t`.`IS_CHIEF_DEPT` AS `is_chief_dept`,`t`.`IS_MANAGER` AS `is_manager`,`t`.`ORDER_BY` AS `order_by` from `sys_user_dept_position` `t` */;

/*View structure for view bpm_def_user_v */

/*!50001 DROP TABLE IF EXISTS `bpm_def_user_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_def_user_v` AS select `t`.`ID` AS `id`,`t`.`NAME` AS `name`,`t`.`LOGIN_NAME` AS `login_name`,`t`.`LOGIN_PASSWORD` AS `login_password`,`t`.`STATUS` AS `status`,`t`.`ORDER_BY` AS `order_by` from `sys_user` `t` */;

/*View structure for view bpm_entry_console_v */

/*!50001 DROP TABLE IF EXISTS `bpm_entry_console_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_entry_console_v` AS select `b`.`DBID_` AS `entryId`,`b`.`TITLE_` AS `entryName`,`p`.`OBJNAME_` AS `defineName`,`b`.`STATE_` AS `state`,`b`.`USERID_` AS `userId`,`b`.`DEPTID_` AS `deptId`,`b`.`START_` AS `startDate`,`b`.`END_` AS `endDate`,`p`.`KEY_` AS `keyone`,`p`.`DEPLOYMENT_` AS `deployment`,`b`.`ID_` AS `executionId`,`p`.`STRINGVAL_` AS `pdId`,`b`.`DURATION_` AS `duration`,`b`.`FORMID_` AS `formId`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `b`.`DBID_`)) AS `LOCKED_STATUS_` from (`bpm_hist_procinst` `b` left join `bpm_deployprop` `p` on((`b`.`PROCDEFID_` = `p`.`STRINGVAL_`))) */;

/*View structure for view bpm_entry_v */

/*!50001 DROP TABLE IF EXISTS `bpm_entry_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_entry_v` AS select `t1`.`DBID_` AS `DBID_`,`t1`.`DBVERSION_` AS `DBVERSION_`,`t1`.`ID_` AS `ID_`,`t1`.`PROCDEFID_` AS `PROCDEFID_`,`t1`.`KEY_` AS `KEY_`,`t1`.`START_` AS `START_`,`t1`.`END_` AS `END_`,`t1`.`DURATION_` AS `DURATION_`,`t1`.`STATE_` AS `STATE_`,`t1`.`ENDACTIVITY_` AS `ENDACTIVITY_`,`t1`.`NEXTIDX_` AS `NEXTIDX_`,`t1`.`FORMID_` AS `FORMID_`,`t1`.`USERID_` AS `USERID_`,`t1`.`DEPTID_` AS `DEPTID_`,`t2`.`ACTIVITYNAME_` AS `activityname_`,`t2`.`ACTIVITYALIAS_` AS `activityalias_`,`t1`.`BUSINESSSTATE_` AS `businessstate_` from (`bpm_hist_procinst` `t1` left join `bpm_execution` `t2` on((`t1`.`DBID_` = `t2`.`INSTANCE_`))) */;

/*View structure for view bpm_hist_procinst_v */

/*!50001 DROP TABLE IF EXISTS `bpm_hist_procinst_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_hist_procinst_v` AS select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`ID_` AS `ID_`,`t`.`PROCDEFID_` AS `PROCDEFID_`,`t`.`KEY_` AS `KEY_`,`t`.`START_` AS `START_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`STATE_` AS `STATE_`,`t`.`ENDACTIVITY_` AS `ENDACTIVITY_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`FORMID_` AS `FORMID_`,`t`.`TITLE_` AS `TITLE_`,`t`.`USERID_` AS `USERID_`,`t`.`DEPTID_` AS `DEPTID_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`USERID_`) and (`s`.`DEPT_ID` = `t`.`DEPTID_`))) AS `POSITIONID_` from `bpm_hist_procinst` `t` */;

/*View structure for view bpm_hist_task_analys_v */

/*!50001 DROP TABLE IF EXISTS `bpm_hist_task_analys_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_hist_task_analys_v` AS select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext1` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext2` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext3` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext4` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext5` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext6` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext7` `t` union all select `t`.`DBID_` AS `DBID_`,`t`.`DBVERSION_` AS `DBVERSION_`,`t`.`EXECUTION_` AS `EXECUTION_`,`t`.`OUTCOME_` AS `OUTCOME_`,`t`.`ASSIGNEE_` AS `ASSIGNEE_`,`t`.`PRIORITY_` AS `PRIORITY_`,`t`.`STATE_` AS `STATE_`,`t`.`CREATE_` AS `CREATE_`,`t`.`END_` AS `END_`,`t`.`DURATION_` AS `DURATION_`,`t`.`NEXTIDX_` AS `NEXTIDX_`,`t`.`SUPERTASK_` AS `SUPERTASK_`,`t`.`TASK_ORDER_` AS `TASK_ORDER_`,`t`.`OPEN_` AS `OPEN_`,`t`.`TAKE_STATE_` AS `TAKE_STATE_`,`t`.`PROCINST_` AS `PROCINST_`,`t`.`DESCR_` AS `DESCR_`,`t`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`t`.`TASK_TYPE_` AS `TASK_TYPE_`,`t`.`TASK_B_ID_` AS `TASK_B_ID_`,`t`.`TASK_TITLE_` AS `TASK_TITLE_`,`t`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`t`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`t`.`FORM_` AS `FORM_`,`t`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`t`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`t`.`TASK_STATE_` AS `TASK_STATE_`,`t`.`TASK_NAME_` AS `TASK_NAME_`,`t`.`HISACTINST_` AS `HISACTINST_`,`t`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`t`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`t`.`APP_ID_` AS `APP_ID_`,`t`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `t`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `t`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext8` `t` */;

/*View structure for view bpm_hist_task_v */

/*!50001 DROP TABLE IF EXISTS `bpm_hist_task_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_hist_task_v` AS select `task`.`DBID_` AS `DBID_`,`task`.`DBVERSION_` AS `DBVERSION_`,`task`.`EXECUTION_` AS `EXECUTION_`,`task`.`OUTCOME_` AS `OUTCOME_`,`task`.`ASSIGNEE_` AS `ASSIGNEE_`,`task`.`PRIORITY_` AS `PRIORITY_`,`task`.`STATE_` AS `STATE_`,`task`.`CREATE_` AS `CREATE_`,`task`.`END_` AS `END_`,`task`.`DURATION_` AS `DURATION_`,`task`.`NEXTIDX_` AS `NEXTIDX_`,`task`.`SUPERTASK_` AS `SUPERTASK_`,`task`.`TASK_ORDER_` AS `TASK_ORDER_`,`task`.`OPEN_` AS `OPEN_`,`task`.`TAKE_STATE_` AS `TAKE_STATE_`,`task`.`PROCINST_` AS `PROCINST_`,`task`.`DESCR_` AS `DESCR_`,`task`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task`.`TASK_TYPE_` AS `TASK_TYPE_`,`task`.`TASK_B_ID_` AS `TASK_B_ID_`,`task`.`TASK_TITLE_` AS `TASK_TITLE_`,`task`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task`.`FORM_` AS `FORM_`,`task`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task`.`TASK_STATE_` AS `TASK_STATE_`,`task`.`TASK_NAME_` AS `TASK_NAME_`,`task`.`HISACTINST_` AS `HISACTINST_`,`task`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task`.`APP_ID_` AS `APP_ID_`,`task`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task` `task` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task1`.`DBID_` AS `DBID_`,`task1`.`DBVERSION_` AS `DBVERSION_`,`task1`.`EXECUTION_` AS `EXECUTION_`,`task1`.`OUTCOME_` AS `OUTCOME_`,`task1`.`ASSIGNEE_` AS `ASSIGNEE_`,`task1`.`PRIORITY_` AS `PRIORITY_`,`task1`.`STATE_` AS `STATE_`,`task1`.`CREATE_` AS `CREATE_`,`task1`.`END_` AS `END_`,`task1`.`DURATION_` AS `DURATION_`,`task1`.`NEXTIDX_` AS `NEXTIDX_`,`task1`.`SUPERTASK_` AS `SUPERTASK_`,`task1`.`TASK_ORDER_` AS `TASK_ORDER_`,`task1`.`OPEN_` AS `OPEN_`,`task1`.`TAKE_STATE_` AS `TAKE_STATE_`,`task1`.`PROCINST_` AS `PROCINST_`,`task1`.`DESCR_` AS `DESCR_`,`task1`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task1`.`TASK_TYPE_` AS `TASK_TYPE_`,`task1`.`TASK_B_ID_` AS `TASK_B_ID_`,`task1`.`TASK_TITLE_` AS `TASK_TITLE_`,`task1`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task1`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task1`.`FORM_` AS `FORM_`,`task1`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task1`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task1`.`TASK_STATE_` AS `TASK_STATE_`,`task1`.`TASK_NAME_` AS `TASK_NAME_`,`task1`.`HISACTINST_` AS `HISACTINST_`,`task1`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task1`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task1`.`APP_ID_` AS `APP_ID_`,`task1`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task1`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task1`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task1`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext1` `task1` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task1`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task3`.`DBID_` AS `DBID_`,`task3`.`DBVERSION_` AS `DBVERSION_`,`task3`.`EXECUTION_` AS `EXECUTION_`,`task3`.`OUTCOME_` AS `OUTCOME_`,`task3`.`ASSIGNEE_` AS `ASSIGNEE_`,`task3`.`PRIORITY_` AS `PRIORITY_`,`task3`.`STATE_` AS `STATE_`,`task3`.`CREATE_` AS `CREATE_`,`task3`.`END_` AS `END_`,`task3`.`DURATION_` AS `DURATION_`,`task3`.`NEXTIDX_` AS `NEXTIDX_`,`task3`.`SUPERTASK_` AS `SUPERTASK_`,`task3`.`TASK_ORDER_` AS `TASK_ORDER_`,`task3`.`OPEN_` AS `OPEN_`,`task3`.`TAKE_STATE_` AS `TAKE_STATE_`,`task3`.`PROCINST_` AS `PROCINST_`,`task3`.`DESCR_` AS `DESCR_`,`task3`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task3`.`TASK_TYPE_` AS `TASK_TYPE_`,`task3`.`TASK_B_ID_` AS `TASK_B_ID_`,`task3`.`TASK_TITLE_` AS `TASK_TITLE_`,`task3`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task3`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task3`.`FORM_` AS `FORM_`,`task3`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task3`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task3`.`TASK_STATE_` AS `TASK_STATE_`,`task3`.`TASK_NAME_` AS `TASK_NAME_`,`task3`.`HISACTINST_` AS `HISACTINST_`,`task3`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task3`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task3`.`APP_ID_` AS `APP_ID_`,`task3`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task3`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task3`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task3`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext3` `task3` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task3`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task4`.`DBID_` AS `DBID_`,`task4`.`DBVERSION_` AS `DBVERSION_`,`task4`.`EXECUTION_` AS `EXECUTION_`,`task4`.`OUTCOME_` AS `OUTCOME_`,`task4`.`ASSIGNEE_` AS `ASSIGNEE_`,`task4`.`PRIORITY_` AS `PRIORITY_`,`task4`.`STATE_` AS `STATE_`,`task4`.`CREATE_` AS `CREATE_`,`task4`.`END_` AS `END_`,`task4`.`DURATION_` AS `DURATION_`,`task4`.`NEXTIDX_` AS `NEXTIDX_`,`task4`.`SUPERTASK_` AS `SUPERTASK_`,`task4`.`TASK_ORDER_` AS `TASK_ORDER_`,`task4`.`OPEN_` AS `OPEN_`,`task4`.`TAKE_STATE_` AS `TAKE_STATE_`,`task4`.`PROCINST_` AS `PROCINST_`,`task4`.`DESCR_` AS `DESCR_`,`task4`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task4`.`TASK_TYPE_` AS `TASK_TYPE_`,`task4`.`TASK_B_ID_` AS `TASK_B_ID_`,`task4`.`TASK_TITLE_` AS `TASK_TITLE_`,`task4`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task4`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task4`.`FORM_` AS `FORM_`,`task4`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task4`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task4`.`TASK_STATE_` AS `TASK_STATE_`,`task4`.`TASK_NAME_` AS `TASK_NAME_`,`task4`.`HISACTINST_` AS `HISACTINST_`,`task4`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task4`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task4`.`APP_ID_` AS `APP_ID_`,`task4`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task4`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task4`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task4`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext4` `task4` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task4`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task5`.`DBID_` AS `DBID_`,`task5`.`DBVERSION_` AS `DBVERSION_`,`task5`.`EXECUTION_` AS `EXECUTION_`,`task5`.`OUTCOME_` AS `OUTCOME_`,`task5`.`ASSIGNEE_` AS `ASSIGNEE_`,`task5`.`PRIORITY_` AS `PRIORITY_`,`task5`.`STATE_` AS `STATE_`,`task5`.`CREATE_` AS `CREATE_`,`task5`.`END_` AS `END_`,`task5`.`DURATION_` AS `DURATION_`,`task5`.`NEXTIDX_` AS `NEXTIDX_`,`task5`.`SUPERTASK_` AS `SUPERTASK_`,`task5`.`TASK_ORDER_` AS `TASK_ORDER_`,`task5`.`OPEN_` AS `OPEN_`,`task5`.`TAKE_STATE_` AS `TAKE_STATE_`,`task5`.`PROCINST_` AS `PROCINST_`,`task5`.`DESCR_` AS `DESCR_`,`task5`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task5`.`TASK_TYPE_` AS `TASK_TYPE_`,`task5`.`TASK_B_ID_` AS `TASK_B_ID_`,`task5`.`TASK_TITLE_` AS `TASK_TITLE_`,`task5`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task5`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task5`.`FORM_` AS `FORM_`,`task5`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task5`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task5`.`TASK_STATE_` AS `TASK_STATE_`,`task5`.`TASK_NAME_` AS `TASK_NAME_`,`task5`.`HISACTINST_` AS `HISACTINST_`,`task5`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task5`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task5`.`APP_ID_` AS `APP_ID_`,`task5`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task5`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task5`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task5`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext5` `task5` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task5`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task6`.`DBID_` AS `DBID_`,`task6`.`DBVERSION_` AS `DBVERSION_`,`task6`.`EXECUTION_` AS `EXECUTION_`,`task6`.`OUTCOME_` AS `OUTCOME_`,`task6`.`ASSIGNEE_` AS `ASSIGNEE_`,`task6`.`PRIORITY_` AS `PRIORITY_`,`task6`.`STATE_` AS `STATE_`,`task6`.`CREATE_` AS `CREATE_`,`task6`.`END_` AS `END_`,`task6`.`DURATION_` AS `DURATION_`,`task6`.`NEXTIDX_` AS `NEXTIDX_`,`task6`.`SUPERTASK_` AS `SUPERTASK_`,`task6`.`TASK_ORDER_` AS `TASK_ORDER_`,`task6`.`OPEN_` AS `OPEN_`,`task6`.`TAKE_STATE_` AS `TAKE_STATE_`,`task6`.`PROCINST_` AS `PROCINST_`,`task6`.`DESCR_` AS `DESCR_`,`task6`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task6`.`TASK_TYPE_` AS `TASK_TYPE_`,`task6`.`TASK_B_ID_` AS `TASK_B_ID_`,`task6`.`TASK_TITLE_` AS `TASK_TITLE_`,`task6`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task6`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task6`.`FORM_` AS `FORM_`,`task6`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task6`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task6`.`TASK_STATE_` AS `TASK_STATE_`,`task6`.`TASK_NAME_` AS `TASK_NAME_`,`task6`.`HISACTINST_` AS `HISACTINST_`,`task6`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task6`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task6`.`APP_ID_` AS `APP_ID_`,`task6`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task6`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task6`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task6`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext6` `task6` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task6`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task7`.`DBID_` AS `DBID_`,`task7`.`DBVERSION_` AS `DBVERSION_`,`task7`.`EXECUTION_` AS `EXECUTION_`,`task7`.`OUTCOME_` AS `OUTCOME_`,`task7`.`ASSIGNEE_` AS `ASSIGNEE_`,`task7`.`PRIORITY_` AS `PRIORITY_`,`task7`.`STATE_` AS `STATE_`,`task7`.`CREATE_` AS `CREATE_`,`task7`.`END_` AS `END_`,`task7`.`DURATION_` AS `DURATION_`,`task7`.`NEXTIDX_` AS `NEXTIDX_`,`task7`.`SUPERTASK_` AS `SUPERTASK_`,`task7`.`TASK_ORDER_` AS `TASK_ORDER_`,`task7`.`OPEN_` AS `OPEN_`,`task7`.`TAKE_STATE_` AS `TAKE_STATE_`,`task7`.`PROCINST_` AS `PROCINST_`,`task7`.`DESCR_` AS `DESCR_`,`task7`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task7`.`TASK_TYPE_` AS `TASK_TYPE_`,`task7`.`TASK_B_ID_` AS `TASK_B_ID_`,`task7`.`TASK_TITLE_` AS `TASK_TITLE_`,`task7`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task7`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task7`.`FORM_` AS `FORM_`,`task7`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task7`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task7`.`TASK_STATE_` AS `TASK_STATE_`,`task7`.`TASK_NAME_` AS `TASK_NAME_`,`task7`.`HISACTINST_` AS `HISACTINST_`,`task7`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task7`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task7`.`APP_ID_` AS `APP_ID_`,`task7`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task7`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task7`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task7`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext7` `task7` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task7`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task8`.`DBID_` AS `DBID_`,`task8`.`DBVERSION_` AS `DBVERSION_`,`task8`.`EXECUTION_` AS `EXECUTION_`,`task8`.`OUTCOME_` AS `OUTCOME_`,`task8`.`ASSIGNEE_` AS `ASSIGNEE_`,`task8`.`PRIORITY_` AS `PRIORITY_`,`task8`.`STATE_` AS `STATE_`,`task8`.`CREATE_` AS `CREATE_`,`task8`.`END_` AS `END_`,`task8`.`DURATION_` AS `DURATION_`,`task8`.`NEXTIDX_` AS `NEXTIDX_`,`task8`.`SUPERTASK_` AS `SUPERTASK_`,`task8`.`TASK_ORDER_` AS `TASK_ORDER_`,`task8`.`OPEN_` AS `OPEN_`,`task8`.`TAKE_STATE_` AS `TAKE_STATE_`,`task8`.`PROCINST_` AS `PROCINST_`,`task8`.`DESCR_` AS `DESCR_`,`task8`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task8`.`TASK_TYPE_` AS `TASK_TYPE_`,`task8`.`TASK_B_ID_` AS `TASK_B_ID_`,`task8`.`TASK_TITLE_` AS `TASK_TITLE_`,`task8`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task8`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task8`.`FORM_` AS `FORM_`,`task8`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task8`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task8`.`TASK_STATE_` AS `TASK_STATE_`,`task8`.`TASK_NAME_` AS `TASK_NAME_`,`task8`.`HISACTINST_` AS `HISACTINST_`,`task8`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task8`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task8`.`APP_ID_` AS `APP_ID_`,`task8`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task8`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task8`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task8`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext8` `task8` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task8`.`PROCINST_` = `procinst`.`DBID_`))))) union all select `task2`.`DBID_` AS `DBID_`,`task2`.`DBVERSION_` AS `DBVERSION_`,`task2`.`EXECUTION_` AS `EXECUTION_`,`task2`.`OUTCOME_` AS `OUTCOME_`,`task2`.`ASSIGNEE_` AS `ASSIGNEE_`,`task2`.`PRIORITY_` AS `PRIORITY_`,`task2`.`STATE_` AS `STATE_`,`task2`.`CREATE_` AS `CREATE_`,`task2`.`END_` AS `END_`,`task2`.`DURATION_` AS `DURATION_`,`task2`.`NEXTIDX_` AS `NEXTIDX_`,`task2`.`SUPERTASK_` AS `SUPERTASK_`,`task2`.`TASK_ORDER_` AS `TASK_ORDER_`,`task2`.`OPEN_` AS `OPEN_`,`task2`.`TAKE_STATE_` AS `TAKE_STATE_`,`task2`.`PROCINST_` AS `PROCINST_`,`task2`.`DESCR_` AS `DESCR_`,`task2`.`SUSPHISTSTATE_` AS `SUSPHISTSTATE_`,`task2`.`TASK_TYPE_` AS `TASK_TYPE_`,`task2`.`TASK_B_ID_` AS `TASK_B_ID_`,`task2`.`TASK_TITLE_` AS `TASK_TITLE_`,`task2`.`TASK_SENDUSER_` AS `TASK_SENDUSER_`,`task2`.`TASK_SENDDEPT_` AS `TASK_SENDDEPT_`,`task2`.`FORM_` AS `FORM_`,`task2`.`PROCESS_DEF_NAME_` AS `PROCESS_DEF_NAME_`,`task2`.`TASK_FINISHED_` AS `TASK_FINISHED_`,`task2`.`TASK_STATE_` AS `TASK_STATE_`,`task2`.`TASK_NAME_` AS `TASK_NAME_`,`task2`.`HISACTINST_` AS `HISACTINST_`,`task2`.`WORKHAND_TYPE_` AS `WORKHAND_TYPE_`,`task2`.`WORKHAND_USER_` AS `WORKHAND_USER_`,`task2`.`APP_ID_` AS `APP_ID_`,`task2`.`ASSIGNEE_DEPT_` AS `ASSIGNEE_DEPT_`,(select `n`.`STATUS_` from `bpm_cross_net_instance` `n` where (`n`.`ID_` = `task2`.`PROCINST_`)) AS `LOCKED_STATUS_`,(select `s`.`POSITION_ID` from `sys_user_dept_position` `s` where ((`s`.`USER_ID` = `task2`.`ASSIGNEE_`) and (`s`.`DEPT_ID` = `task2`.`ASSIGNEE_DEPT_`))) AS `ASSIGNEE_POSITION_` from `bpm_hist_task_ext2` `task2` where (not(exists(select 1 from `bpm_hist_procinst` `procinst` where ((`procinst`.`STATE_` = 'suspended') and (`task2`.`PROCINST_` = `procinst`.`DBID_`))))) */;

/*View structure for view bpm_process_deploy_v */

/*!50001 DROP TABLE IF EXISTS `bpm_process_deploy_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bpm_process_deploy_v` AS select `a`.`DBID_` AS `DBID_`,`a`.`KEY_` AS `KEY_`,`a`.`NAME_` AS `NAME_`,`a`.`DESC_` AS `DESC_`,`a`.`APP_` AS `APP_`,`c`.`DBID_` AS `DEF_ID_`,'2' AS `DEF_TYPE_`,`c`.`BLOB_VALUE_` AS `XML_`,`d`.`LONGVAL_` AS `VERSION_` from ((`bpm_process_def` `a` join `bpm_lob` `c`) join `bpm_deployprop` `d`) where ((`c`.`NAME_` like `a`.`KEY_`) and (`c`.`DEPLOYMENT_` = `d`.`DEPLOYMENT_`) and (`d`.`KEY_` = 'pdversion')) order by `a`.`KEY_` */;

/*View structure for view sys_dept_org_position_v */

/*!50001 DROP TABLE IF EXISTS `sys_dept_org_position_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_dept_org_position_v` AS select `t`.`ID` AS `id`,`t`.`USER_ID` AS `user_id`,`su`.`NAME` AS `user_name`,`t`.`DEPT_ID` AS `dept_id`,`sd`.`dept_name` AS `dept_name`,`sd`.`org_id` AS `org_id`,`t`.`POSITION_ID` AS `position_id`,`sp`.`position_name` AS `position_name`,`t`.`IS_CHIEF_DEPT` AS `is_chief_dept` from (((`sys_user_dept_position` `t` join `sys_user` `su` on((`t`.`USER_ID` = `su`.`ID`))) join `sys_dept_v` `sd` on((`t`.`DEPT_ID` = `sd`.`id`))) join `sys_position_v` `sp` on((`t`.`POSITION_ID` = `sp`.`ID`))) */;

/*View structure for view sys_dept_v */

/*!50001 DROP TABLE IF EXISTS `sys_dept_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_dept_v` AS select `t`.`ID` AS `id`,`t`.`DEPT_CODE` AS `dept_code`,`t`.`PARENT_DEPT_ID` AS `parent_dept_id`,`t`.`ORG_ID` AS `org_id`,`t`.`ORDER_BY` AS `order_by`,`t`.`POST` AS `post`,`t`.`TEL` AS `tel`,`t`.`FAX` AS `fax`,`t`.`EMAIL` AS `email`,`t`.`DEPT_ALIAS` AS `dept_alias`,`t`.`VALID_FLAG` AS `valid_flag`,`t`.`WORK_CALENDAR_ID` AS `work_calendar_id`,`tl`.`DEPT_NAME` AS `dept_name`,`tl`.`DEPT_DESC` AS `dept_desc`,`tl`.`DEPT_PLACE` AS `dept_place` from (`sys_dept` `t` left join `sys_dept_tl` `tl` on((`t`.`ID` = `tl`.`SYS_DEPT_ID`))) where ((`t`.`VALID_FLAG` = '1') and (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN')) */;

/*View structure for view sys_lookup_v */

/*!50001 DROP TABLE IF EXISTS `sys_lookup_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_lookup_v` AS select `t`.`ID` AS `id`,`t`.`LOOKUP_TYPE` AS `lookup_type`,`t`.`SYS_APPLICATION_ID` AS `sys_application_id`,`sl`.`LOOKUP_TYPE_NAME` AS `lookup_type_name`,`slu`.`LOOKUP_CODE` AS `lookup_code`,`slu`.`ID` AS `lookup_id`,`slut`.`LOOKUP_NAME` AS `lookup_name` from (((`sys_lookup_type` `t` left join `sys_lookup_type_tl` `sl` on((`t`.`ID` = `sl`.`SYS_LOOKUP_TYPE_ID`))) left join `sys_lookup` `slu` on((`t`.`ID` = `slu`.`SYS_LOOKUP_TYPE_ID`))) left join `sys_lookup_tl` `slut` on((`slut`.`SYS_LOOKUP_ID` = `slu`.`ID`))) where ((`sl`.`SYS_LANGUAGE_CODE` = 'zh_CN') and (`slut`.`SYS_LANGUAGE_CODE` = 'zh_CN') and (`t`.`VALID_FLAG` = '1') and (`slu`.`VALID_FLAG` = '1')) */;

/*View structure for view sys_org_v */

/*!50001 DROP TABLE IF EXISTS `sys_org_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_org_v` AS select `t`.`ID` AS `ID`,`t`.`ORG_CODE` AS `ORG_CODE`,`t`.`PARENT_ORG_ID` AS `PARENT_ORG_ID`,`t`.`ORDER_BY` AS `ORDER_BY`,`t`.`POST` AS `POST`,`t`.`TEL` AS `TEL`,`t`.`FAX` AS `FAX`,`t`.`EMAIL` AS `EMAIL`,`t`.`WORK_CALENDAR_ID` AS `WORK_CALENDAR_ID`,`t`.`VALID_FLAG` AS `VALID_FLAG`,`t`.`RESPONSIBLE_DEPT_ID` AS `responsible_dept_id`,`tl`.`ORG_NAME` AS `org_name`,`tl`.`ORG_DESC` AS `org_desc`,`tl`.`ORG_PLACE` AS `org_place` from (`sys_org` `t` left join `sys_org_tl` `tl` on((`t`.`ID` = `tl`.`SYS_ORG_ID`))) where ((`t`.`VALID_FLAG` = '1') and (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN')) */;

/*View structure for view sys_position_v */

/*!50001 DROP TABLE IF EXISTS `sys_position_v` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sys_position_v` AS select `t`.`ID` AS `ID`,`t`.`ORG_ID` AS `ORG_ID`,`t`.`POSITION_CODE` AS `POSITION_CODE`,`t`.`ORDER_BY` AS `ORDER_BY`,`t`.`LAST_UPDATE_DATE` AS `LAST_UPDATE_DATE`,`t`.`LAST_UPDATED_BY` AS `LAST_UPDATED_BY`,`t`.`CREATION_DATE` AS `CREATION_DATE`,`t`.`CREATED_BY` AS `CREATED_BY`,`t`.`LAST_UPDATE_IP` AS `LAST_UPDATE_IP`,`t`.`VERSION` AS `VERSION`,`t`.`ATTRIBUTE_01` AS `ATTRIBUTE_01`,`t`.`ATTRIBUTE_02` AS `ATTRIBUTE_02`,`t`.`ATTRIBUTE_03` AS `ATTRIBUTE_03`,`t`.`ATTRIBUTE_04` AS `ATTRIBUTE_04`,`t`.`ATTRIBUTE_05` AS `ATTRIBUTE_05`,`t`.`ATTRIBUTE_06` AS `ATTRIBUTE_06`,`t`.`ATTRIBUTE_07` AS `ATTRIBUTE_07`,`t`.`ATTRIBUTE_08` AS `ATTRIBUTE_08`,`t`.`ATTRIBUTE_09` AS `ATTRIBUTE_09`,`t`.`ATTRIBUTE_10` AS `ATTRIBUTE_10`,`tl`.`POSITION_NAME` AS `position_name`,`tl`.`POSITION_DESC` AS `position_desc` from (`sys_position` `t` left join `sys_position_tl` `tl` on((`t`.`ID` = `tl`.`SYS_POSITION_ID`))) where (`tl`.`SYS_LANGUAGE_CODE` = 'zh_CN') */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
