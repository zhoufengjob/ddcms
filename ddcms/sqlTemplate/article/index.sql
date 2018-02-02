###文章分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_article
   WHERE (
   dms_article.title LIKE #para(searchKey)
   )
   AND
   dms_article.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)

  #if(sortName!=null)
   ORDER BY #(sortName)
   #(sortOrder)
  #else
   ORDER BY create_time
   DESC 
  #end
#end


###获取文章用户
#sql("getUser")
  SELECT * FROM dms_user
  WHERE dms_user.id = ?
#end


###根据URL 查找文章
#sql("findByUrl")
  SELECT * FROM dms_article
  WHERE dms_article.url = ?
#end

###获取所有置顶文章
#sql("getTopArticleList")
  SELECT *
  FROM  dms_article
  WHERE dms_article.is_top = 'on'
  GROUP BY dms_article.create_time
#end


###根据用户id获取所有文章
#sql("findByUId")
  SELECT *
  FROM  dms_article
  WHERE dms_article.uid = ?
  GROUP BY dms_article.create_time
#end
