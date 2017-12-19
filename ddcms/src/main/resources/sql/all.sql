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

#namespace("permission")
  #include("permission/index.sql");
#end
#namespace("role_permission")
  #include("role_permission/index.sql");
#end