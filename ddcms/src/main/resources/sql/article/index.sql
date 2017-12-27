###文章分页查询 带模糊查询功能
#sql("getPage")
  SELECT * FROM dms_article
   WHERE (
   dms_article.title LIKE #para(searchKey)
   )
   AND
   dms_article.create_time BETWEEN #para(dateStartStr) AND #para(dateEndStr)
#end


###文章分页查询 带模糊查询功能
#sql("getUser")
  SELECT * FROM dms_user
  WHERE dms_user.id = ?
#end



###文章分页查询 带模糊查询功能
#sql("findByUrl")
  SELECT * FROM dms_article
  WHERE dms_article.url = ?
#end
