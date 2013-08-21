<#--导航条-->


 <!-- Navbar
    ================================================== -->
    
     <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">项目管理系统</a>
          <div class="nav-collapse collapse">
           <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown">
                        <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${user.name!}
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">配置</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="sign-in.html">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
            <ul class="nav">
              <li class="active"><a href="#">首页</a></li>
              <li class="dropdown">
              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">项目<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${ctx}/projects">项目管理</a></li>
                  <li class="divider"></li>
                  <li><a href="#">项目1</a></li>
                  <li><a href="#">项目2</a></li>
                  <li><a href="#">项目3</a></li>
                  <li class="divider"></li>
                  <!--<li class="nav-header">Nav header</li>-->
                  <li><a href="#">项目4</a></li>
                  <li><a href="#">项目5</a></li>
                </ul>
              </li>
              <li><a href="#contact">任务</a></li>
            
              <li class="dropdown">
              	<a class="dropdown-toggle" data-toggle="dropdown">权限管理<b class="caret"></b></a>
              	 <ul class="dropdown-menu">
                  <li><a href="${ctx}/users">人员管理</a></li>
                  <li><a href="roles">角色管理</a></li>
                  <li><a href="#">资源管理</a></li>
                  <li><a href="#">菜单管理</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
