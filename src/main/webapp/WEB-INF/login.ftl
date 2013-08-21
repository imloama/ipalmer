<#include "/layout/header.ftl">	
    <div class="container">
      <form class="form-signin" method="post" action="index">
        <h2 class="form-signin-heading">项目管理系统PMS</h2>
        <input type="text" class="input-block-level" placeholder="请输入用户名" id="name" name="name" value="<#if user??>${user.name!}</#if>">
        <input type="password" class="input-block-level" placeholder="请输入密码" id="password" name="password" value="<#if user??>${user.password!}</#if>">
        <label class="error login-error-msg">
        	${loginErrorMsg!}
        </label>
        <!--
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> 记住我
        </label>-->
        <button class="btn btn-primary" type="submit">登陆</button>
      </form>

    </div> <!-- /container -->
    
<#include "/layout/footer.ftl">
  
