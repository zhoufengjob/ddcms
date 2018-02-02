##根据用户ID删除对应角色
#sql("deleteByUID")
  DELETE FROM dms_user_role WHERE dms_user_role.uid = ?
#end


##根据用户ID查询对应角色
#sql("findFirstByUID")
  SELECT * FROM dms_user_role WHERE dms_user_role.uid = #para(uid)
#end



