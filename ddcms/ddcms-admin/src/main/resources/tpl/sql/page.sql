#("###")#(upClassName)分页查询 带模糊查询功能
#("#")sql("getPage")
  SELECT * FROM #(tableMeta.name)
   WHERE (
    #(tableMeta.name).name LIKE #("#")para(searchKey)
   )
   AND
   #(tableMeta.name).create_time BETWEEN #("#")para(dateStartStr) AND #("#")para(dateEndStr)

  #("#")if(sortName!=null)
   ORDER BY #(tableMeta.name).#("#")(sortName)
   #(tableMeta.name).#("#")(sortOrder)
  #("#")else
   ORDER BY #(tableMeta.name).create_time
   DESC
  #("#")end
#("#")end