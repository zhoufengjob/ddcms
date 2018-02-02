###管理员列表分页查询
#sql("getPage")
SELECT
      dms_user.id ,
      dms_user.name as uname,
      dms_user.email ,
      dms_user.nike_name ,
      dms_user.phone ,
      dms_user.create_time,
      dms_user.is_open,
      dms_role.name as rname

 FROM dms_user LEFT
  JOIN dms_user_role  ON  dms_user_role.uid = dms_user.id
  JOIN dms_role ON dms_role.id = dms_user_role.rid

 WHERE dms_user_role.rid = '1'
#if(sortName!=null)
   ORDER BY #(sortName)
   #(sortOrder)
  #else
   ORDER BY create_time
   DESC
  #end
#end