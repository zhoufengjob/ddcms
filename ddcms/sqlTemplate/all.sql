#namespace("pub")
  #include("pub/index.sql");
#end

#namespace("user")
  #include("user/index.sql");
#end

#namespace("menu")
  #include("menu/index.sql");
#end

#namespace("master")
  #include("master/index.sql");
#end

#namespace("role")
  #include("role/index.sql");
#end

#namespace("user_role")
  #include("user_role/index.sql");
#end

#namespace("article")
  #include("article/index.sql");
#end

#namespace("log")
  #include("log/index.sql");
#end

#namespace("articleComment")
  #include("articleComment/index.sql");
#end

#namespace("articleType")
  #include("articleType/index.sql");
#end