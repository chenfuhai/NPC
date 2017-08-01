var indexdatal = 
[
	{ text: '文章管理',icon:"/ligerStatic/icon/email.png",isexpand:true,children: [
		{url:"manager?coName='领导致词'",text:"领导致词"},
        {url:"manager?coName='公告通知'",text:"公告通知"},
        {url:"manager?coName='图片新闻'",text:"图片新闻"},
        {url:"manager?coName='代表风采'",text:"代表风采"},
        {url:"manager?coName='工作动态'",text:"工作动态"},
        {url:"manager?coName='议事决定'",text:"议事决定"},
        {url:"manager?coName='人大信箱'",text:"人大信箱"}

	]}
];

var indexdatar =
[
	{ isexpand: "false", text: "导入excel"
	
	},
	{ isexpand: "false", text: "导出excel"
		
	},
];


//管理员功能列表
var managedatal =
[
	{ isexpand: "false", text: "用户管理",icon:"/ligerStatic/images/37.png",children:[
		{url:"User_insert.html",text:"添加用户",icon:"/ligerStatic/images/Employee_insert.png"},
		{url:"User_select.html",text:"查看用户",icon:"/ligerStatic/images/Employee_select.png"},
		{url:"User_update.html",text:"修改用户",icon:"/ligerStatic/images/Employee_update.png"}
	]},
	{ isexpand: "false", text: "栏目管理",icon:"/ligerStatic/images/power.png",children:[
		{url:"Power_insert.html",text:"添加权限",icon:"/ligerStatic/images/Power_insert.png"},
		{url:"Power_select.html",text:"查看权限",icon:"/ligerStatic/images/Power_select.png"},
		{url:"Power_update.html",text:"修改权限",icon:"/ligerStatic/images/Power_update.png"}
	]},
    { isexpand: "false", text: "权限管理",icon:"/ligerStatic/images/power.png",children:[
        {url:"Power_insert.html",text:"添加权限",icon:"/ligerStatic/images/Power_insert.png"},
        {url:"Power_select.html",text:"查看权限",icon:"/ligerStatic/images/Power_select.png"},
        {url:"Power_update.html",text:"修改权限",icon:"/ligerStatic/images/Power_update.png"}
    ]}
];

var managedatar =
[
	{ isexpand: "false", text: "数据库备份",icon:"/ligerStatic/images/database.png"
	
	},
	{ isexpand: "false", text: "数据库恢复",icon:"/ligerStatic/images/database.png"
		
	},
];
