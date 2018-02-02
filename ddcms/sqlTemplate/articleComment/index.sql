###ArticleComment分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_article_comment
   WHERE (
    dms_article_comment.name LIKE #para(searchKey)
   )
   AND
   dms_article_comment.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)

  #if(sortName!=null)
   ORDER BY dms_article_comment.#(sortName)
   dms_article_comment.#(sortOrder)
  #else
   ORDER BY dms_article_comment.create_time
   DESC
  #end
#end