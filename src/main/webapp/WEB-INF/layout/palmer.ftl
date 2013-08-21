<#--宏命令的一些用法-->
<#--==========1     显示表格宏    其中接收的table对象是来自后台com.palmer.view.component.Table==================-->
<#macro ptable table>
	<table id="${(table.name)!""}" class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<#list table.colModel as cm>
					<td <#if cm.show==false>style="display:none"</#if>>
						${cm.title}
					</td>
					<#--${(cm.show)?string("true","false")}-->	
				</#list>
				
				<#if table.editable==true>
					<#if table.deletable==true>
						<#assign t_oper = true>
						<td>操作</td>
					</#if>
				</#if>	
			</tr>
		</thead>
		<tbody>
			<#list table.page.content as model>
				<tr>
					<#list table.columnNames as colname>
						<td id='${table.colModel[colname_index]["name"]}' <#if table.colModel[colname_index]["show"]==false>style="display:none"</#if>>
							${model[colname]!""}
						</td>
					</#list>
					<#if t_oper??>
						<td id="operation">
							<#if table.editable==true>
								<a id="edit${(table.name)!""}btn" href="#" class="btn btn-small btn-primary edit${(table.name)!""}btn" type="button">编辑</a>
							</#if>
							<#if table.deletable==true>
								<a href="#" p-target='${ctx}/${table.URL}/delete/${model.id}' class="btn btn-small btn-primary p-delete" type="button">删除</a>
							</#if>
						</td>
					</#if>
				</tr>
			</#list>				
		</tbody>
	</table>
	<#if table.addable==true>
		<a href="#myModal${(table.name)!""}" role="button" class="btn btn-small btn-primary pull-left" data-toggle="modal">新增</a>
	</#if>
	<#if (table.page)??>
		<div class="pagination pagination-right">
		  <ul>
		  	<#assign pageNumbers = table.page.getTotalPages()>
		  	<#assign pageNum = table.page.getNumber()>
		  
		  	<#--当只有一页时-->
		  	<#if pageNumbers==1>
		  		<li class="disabled"><a href="#">前一页</a></li>
		  		<li class="active"><a href="#">1</a></li>
		  		<li class="disabled"><a href="#">后一页</a></li>
		  	<#else>
		  	<#--如果有多页的情况-->
		  		<#if pageNum==1>
		  			<li class="disabled"><a href="#">前一页</a></li>
		  		<#else>
		  			<li><a href="${ctx}/${table.URL}/${pageNum-1}">前一页</a></li>
		  		</#if>
		  		
		  		<#if pageNumbers lt 5>
		  			<#list 1..pageNumbers as i>
		  				<#if pageNum==i>
		  					<li class="active"><a href="${ctx}/${table.URL}/${i}">${i}</a></li>
		  				<#else>
		  					<li><a href="${ctx}/${table.URL}/${i}">${i}</a></li>
		  				</#if>
		  			</#list>
		  		<#else>
		  			<#list pageNum..(pageNumb+5) as i>
		  				<#if pageNum==i>
		  					<li class="active"><a href="${ctx}/${table.URL}/${i}">${i}</a></li>
		  				<#else>
		  					<li><a href="${ctx}/${table.URL}/${i}">${i}</a></li>
		  				</#if>
		  			</#list>
		  		</#if>
		  		
		  		<#if page.isLastPage()>
		  			<li class="disabled"><a href="#">后一页</a></li>
		  		<#else>
		  			<li><a href="${ctx}/${table.URL}/${pageNum+1}">后一页</a></li>
		  		</#if>
		  	</#if>
		  </ul>
		</div>
	</#if>
	
	<#--=====================新增用的form============================-->
	<div id="myModal${(table.name)!""}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">新增${(table.title)!""}</h3>
	  </div>
	  <div class="modal-body">
	  	<p>
		  <form id="${(table.name)!"addForm"}" class="form-horizontal ${(table.name)!"addForm"}">
	    	<fieldset>
	    		<#list table.colModel as cm>
	    			<#if cm.show==true>
	    			<div class="control-group">
		          		<label class="control-label" for="input01">${(cm.title)!""}：</label>
		          		<div class="controls">
		            		<#if cm.type=="text">
		            			<input id="${cm.name}" name="${cm.name}" type="text" placeholder="" class="input-xlarge">
		          			</#if>
		          		</div>
		        	</div>
		        	</#if>
	    		</#list>
		    </fieldset>
		   </form>
		  </p> 
		</div>
		<div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    <button id="save${(table.name)!"addForm"}" class="btn btn-primary" p-data="{'form':'${(table.name)!"addForm"}'}">保存</button>
		</div>
	</div>
	<script type="text/javascript">
		var options = {
			url		:	'${ctx}/${table.URL}/add',
			type	:	'POST',
			dataType:	'json',
			success	:	formSuccess,
			error	:	formError	
		};
		function formSuccess(data){
			if(data.type==1){
				$("#msg-success").modal('toggle');
				$("#msg-close").click(function(){
					$("#${(table.name)!"addForm"}").resetForm();
				});
				$("#myModal${(table.name)!""}").modal('hide');
				window.location.reload();
			}else{
				$("#msg-fail").modal('show');	
			}
			
		};
		function formError(){
			$("#msg-fail").modal('show');
		};
		$(document).ready(function(){
			$("#save${(table.name)!"addForm"}").click(function(){
					$("#${(table.name)!"addForm"}").ajaxSubmit(options);
				});
		});
	</script>
	<#--====================新增用到的form结束 =========================================-->
	
	<#--=====================修改用的form====================================-->
	<div id="editmodal${(table.name)!""}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="editmyModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="editmyModalLabel">修改${(table.title)!""}</h3>
	  </div>
	  <div class="modal-body">
	  	<p>
		  <form id="edit${(table.name)!"Form"}" class="form-horizontal edit${(table.name)!"Form"}">
	    	<fieldset>
	    		<#list table.colModel as cm>
	    			<#if cm.show==true>
	    			<div class="control-group">
		          		<label class="control-label" for="input01">${(cm.title)!""}：</label>
		          		<div class="controls">
		            		<#if cm.type=="text">
		            			<input id="${cm.name}" p-type="edit" name="${cm.name}" type="text" placeholder="" class="input-xlarge">
		          			</#if>
		          		</div>
		        	</div>
		        	<#else>
		        		<div class="control-group" style="display:none">
		          		<label class="control-label" for="input01">${(cm.title)!""}：</label>
		          		<div class="controls">
		            		<#if cm.type=="text">
		            			<input id="${cm.name}" p-type="edit" name="${cm.name}" type="text" placeholder="" class="input-xlarge">
		          			</#if>
		          		</div>
		        	</div>
		        	</#if>
	    		</#list>
		    </fieldset>
		   </form>
		  </p> 
		</div>
		<div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		    <button id="save${(edit.name)!"editForm"}" class="btn btn-primary" p-data="{'form':'${(table.name)!"addForm"}'}">保存</button>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//点击编辑按钮时，给Modal赋值
			$(".edit${(table.name)!""}btn").each(function(){
				var _btn = $(this);
				$(this).click(function(){
					$("#edit${(table.name)!"Form"}").resetForm();
					$("#edit${(table.name)!"Form"}").find("[p-type=edit]").each(function(){
						var _id = $(this).attr("id");
						var v = _btn.parents("tr").find("#"+_id).eq(0).text();//.find("#"+$(this).attr("id)).eq(0).val();
						//alert(v.length);
						$(this).val($.trim(v));
						$("#editmodal${(table.name)!""}").modal("show");
					});
				});
			});
			
			//保存编辑内容时
			$("#save${(edit.name)!"editForm"}").click(function(){
				$("#edit${(table.name)!"Form"}").ajaxSubmit({
					url		:	'${ctx}/${table.URL}/edit',
					type	:	'POST',
					dataType:	'json',
					success	:	function(data/**保存更新内容成功后*/){
						if(data.type==1){
							$("#msg-success").modal('show');
							$("#edit${(table.name)!"Form"}").resetForm();
							$("#editmodal${(table.name)!""}").modal('hide');
							window.location.reload();
						}else{
							$("#msg-fail").modal('show');
						}
						
					},
					error	:	function(/**执行失败的时候*/){
						$("#msg-fail").modal('show');
					}	
				});
			});
			
			//支持删除方法
			$(".p-delete").each(function(){
				$(this).click(function(){
					alert($(this).attr("p-target"));
					_form = $("<form></form>");
			        _input1 = $("<input type='hidden' name='input1' />");
			        _input1.attr('value','p-delete');
			        _form.append(_input1);
			        _form.appendTo("body");
			        _form.css('display','none');
			        _form.ajaxSubmit({
			        	url:$(this).attr("p-target"),
			        	type:'POST',
			        	dataType:'json',
			        	success:function(data){
			        		if(data.type==1){
			        			$("#msg-success").modal('show');
				        		$(this).remove();
				        		window.location.reload();
			        		}else{
			        			$("#msg-fail").modal('show');
			        			$(this).remove();
			        		}
			        		
			        	},
			        	error:function(){
			        		$("#msg-fail").modal('show');
			        	}
			        	});
				});
			});
			
		});
	</script>
	<#--=====================form结束==========================-->
</#macro>