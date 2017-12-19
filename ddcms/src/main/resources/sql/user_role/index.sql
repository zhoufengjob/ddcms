##根据用户ID删除对应角色
#sql("deleteByUID")
  DELETE FROM dms_user_role WHERE dms_user_role.uid = ?
#end


