###角色列表分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_role
   WHERE (
   dms_role.name LIKE #para(searchKey)
   OR
   dms_role.key LIKE #para(searchKey)
   )
   AND
   dms_role.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
#end