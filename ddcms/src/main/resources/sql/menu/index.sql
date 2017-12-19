###获取菜单类型
#sql("getMenuType")
  SELECT * FROM dms_menu_type WHERE dms_menu_type.id=?
#end

###获取子菜单
#sql("getSubDmsMenu")
  select * from dms_menu where dms_menu.super_id=?
#end

###菜单列表分页查询
#sql("getPage")
  SELECT dms_menu.name,
         IFNULL(super.name,'顶级') as sname,
         dms_menu.id,
         dms_menu.create_time,
         dms_menu.is_open,
         dms_menu.serial_num,
         IFNULL(dms_menu.url,'无') as url

  FROM dms_menu
    LEFT JOIN dms_menu AS super ON   dms_menu.super_id = super.id
    WHERE (dms_menu.`name` LIKE #para(searchKey) OR dms_menu.url LIKE #para(searchKey)) AND dms_menu.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
    ORDER by super.name
#end