###Log分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_log
   WHERE (
    dms_log.url LIKE #para(searchKey)
   )
   AND
   dms_log.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)

  #if(sortName!=null)
   ORDER BY dms_log.#(sortName)
   #(sortOrder)
  #else
   ORDER BY dms_log.create_time
   DESC
  #end
#end