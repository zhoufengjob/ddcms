###更具帐号查询用户
#sql("getUserByAccount")
  SELECT * FROM dms_user WHERE dms_user.name = ?
#end


###用户列表分页查询 带模糊查询功能
#sql("getPage")
  SELECT
    dms_user.id,
    dms_user.name,
    dms_user.email,
    dms_user.nike_name,
    IFNULL(dms_user.phone,'暂无') AS phone,
    dms_user.create_time,
    dms_user.is_open

  FROM dms_user
   WHERE (
     dms_user.name LIKE #para(searchKey)
     OR
     dms_user.nike_name LIKE #para(searchKey)
     OR
     dms_user.email LIKE #para(searchKey)
     OR
     dms_user.phone LIKE #para(searchKey)
   )
   AND
   dms_user.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)

  #if(sortName!=null)
   ORDER BY #(sortName)
   #(sortOrder)
  #else
   ORDER BY create_time
   DESC
  #end

#end