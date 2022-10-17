<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="NewsAPI" value="/api/news" />
<c:url var="UploadFiles" value="/api/news/uploadFiles" />
<c:url var="editNewsURL" value="/admin/news/edit" />
<c:url var="NewsURL" value="/admin/news/list" />
<html>
<head>
<title>Chỉnh sửa bài viết</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="#">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">
  								${message}
							</div>
						</c:if>
						
						<form:form class="form-horizontal" role="form" id="formSubmit"
							modelAttribute="model" enctype="multipart/form-data">
							
							<div class="form-group">
							  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Thể loại</label>
							  <div class="col-sm-9">
								   <form:select path="categoryCode" id="categoryCode">
								   		<form:option value="" label="-- Chọn thể loại --"/>
								   		<form:options items="${categories}"/>
								   </form:select>
							  </div>
						
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên bài viết</label>
								<div class="col-sm-9">
									<form:input path="title" cssClass="col-xs-10 col-sm-5"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Ảnh đại diện</label>
								<div class="col-sm-9">
									<input type="file" cssClass="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>
									
								</div>
							</div>
							
							<div class="form-group">
							  <label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
							  <div class="col-sm-9">
							  	<form:textarea path="shortDescription" rows="5" cols="10" id="shortDescription" cssClass="form-control"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <label for="content" class="col-sm-3 control-label no-padding-right">Nội dung</label>
							  <div class="col-sm-9">
							  	<form:textarea path="content" rows="5" cols="10" id="content" cssClass="form-control"/>
							  </div>
							</div>
							
							<form:hidden path="id" id="newId"/>
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
								
									<c:if test="${not empty model.id }">
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i>
												Cập nhật bài viết
										</button>
									</c:if>
									
									<c:if test="${empty model.id }">
										<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
											<i class="ace-icon fa fa-check bigger-110"></i>
												Thêm bài viết
										</button>
									</c:if>
	
									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
													Hủy
									</button>
								</div>		
						</div>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	var editor = '';
	$(document).ready(function(){
		editor = CKEDITOR.replace( 'content');
	});
	
	
		var files = [];
		var forms = new FormData();
		$('#thumbnail').change(function (e) {
			files = e.target.files;
			forms.append("thumbnail",files[0]);
			
			$.ajax({
	            url: '${UploadFiles}',
	            type: 'POST',
	            data: forms,
	            contentType: false,
	            processData: false,
	            enctype: "multipart/form-data",
	            success: function () {
	            	
	            },
	            error: function (error) {
	            	
	            }
	        });
		});
	
	
		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function (i, v) {
				data[""+v.name+""] = v.value;  
	        });
			data["content"] = editor.getData();
			/*  
				if($('#thumbnail').get(0).files[0].name != null) {
				data['thumbnail'] = $('#thumbnail').get(0).files[0].name;
			}
			*/

			var id = $('#newId').val();
			if(id == ""){
				addNews(data);
			}else {
				editNews(data);
			}
		});
		
		function addNews(data) {
			$.ajax({
	            url: '${NewsAPI}',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            dataType: 'json',
	            success: function (result) {
	            	window.location.href = "${editNewsURL}?id="+result.id+"&message=insert_success";
	            },
	            error: function (error) {
	            	window.location.href = "${NewsURL}?page=1&limit=2&message=error_system";
	            }
	        });
		}
		
		function editNews(data) {
			$.ajax({
	            url: '${NewsAPI}',
	            type: 'PUT',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            dataType: 'json',
	            success: function (result) {
	            	window.location.href = "${editNewsURL}?id="+result.id+"&message=update_success";
	            },
	            error: function (error) {
	            	window.location.href = "${editNewsURL}?id="+result.id+"&message=error_system";
	            }
	        });
		}
	</script>
</body>
</html>
