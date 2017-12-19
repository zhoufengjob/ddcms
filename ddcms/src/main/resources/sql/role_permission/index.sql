###角色列表分页查询 带模糊查询功能
#sql("deleteByRid")
DELETE FROM dms_role_permission WHERE dms_role_permission.rid = ?
#end