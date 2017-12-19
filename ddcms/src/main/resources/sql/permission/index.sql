###角色列表分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_permission
   WHERE (
   dms_permission.name LIKE #para(searchKey)
   OR
   dms_permission.key LIKE #para(searchKey)
   )
   AND
   dms_permission.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
#end