###ArticleType分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_article_type
   WHERE (
    dms_article_type.name LIKE #para(searchKey)
   )
   AND
   dms_article_type.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)

  #if(sortName!=null)
   ORDER BY dms_article_type.#(sortName)
   dms_article_type.#(sortOrder)
  #else
   ORDER BY dms_article_type.create_time
   DESC
  #end
#end