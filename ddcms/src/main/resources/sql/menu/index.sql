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
  SELECT * FROM dms_menu WHERE (dms_menu.`name` LIKE #para(searchKey) OR dms_menu.url LIKE #para(searchKey)) AND dms_menu.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
#end