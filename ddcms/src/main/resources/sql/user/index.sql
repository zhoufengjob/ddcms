###更具帐号查询用户
#sql("getUserByAccount")
  SELECT * FROM dms_user WHERE dms_user.account = ?
#end
