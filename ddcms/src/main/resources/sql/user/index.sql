###更具帐号查询用户
#sql("getUserByAccount")
  SELECT * FROM dms_user WHERE dms_user.account = ?
#end


###用户列表分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_user
   WHERE (
   dms_user.name LIKE #para(searchKey)
   OR
   dms_user.nike_name LIKE #para(searchKey)
   OR
   dms_user.account LIKE #para(searchKey)
   OR
   dms_user.email LIKE #para(searchKey)
   OR
   dms_user.phone LIKE #para(searchKey)
   )
   AND
   dms_user.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
#end