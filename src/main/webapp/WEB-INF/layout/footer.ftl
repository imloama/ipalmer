  
  
  <#--==========================保存成功还是失败的提醒窗口，每个页面都 有======================================-->
  <div id="msg-success" class="modal hide fade msg-p">
	  <div class="modal-body">
	    <p>操作成功！</p>
	  </div>
	  <div class="modal-footer">
	    <a href="#" class="btn btn-primary" id="msg-close" data-dismiss="modal" aria-hidden="true">确定</a>
	  </div>
	</div>
	<div id="msg-fail" class="modal hide fade msg-p">
	  <div class="modal-body">
	    <p>操作失败！</p>
	  </div>
	  <div class="modal-footer">
	    <a href="#" class="btn btn-primary" id="msg-close" data-dismiss="modal" aria-hidden="true">确定</a>
	  </div>
	</div>
  
  <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="${ctx}/assets/js/bootstrap/js/bootstrap.js"></script>
	<script src="${ctx}/assets/js/jquery-plugin/jquery-placeholder.js"></script>
	<script src="${ctx}/assets/js/fullcalendar/fullcalendar.js"></script>
	<script src="${ctx}/assets/js/jquery.form.js"></script>
	
	<script type="text/javascript">
	$(function() {
	 $('input, textarea').placeholder();
	});
	</script>

  </body>
</html>