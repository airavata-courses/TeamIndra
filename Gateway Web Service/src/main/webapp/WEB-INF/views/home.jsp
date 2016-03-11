<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Indra</title>
</head>
<body>

	<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Team Indra | Portal to schedule job and check job status</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="./resources/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/jquery.bootgrid.min.css" />
<link rel="stylesheet" type="text/css"
	href="./resources/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="./resources/bootstrap/css/customcss.css" />

<script type="text/javascript"
	src="./resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="./resources/bootstrap/js/jquery.bootgrid.fa.min.js"></script>
	<script type="text/javascript"
	src="./resources/bootstrap/js/jquery.bootgrid.min.js"></script>
</head>
<body>

	<div class="container">

		<div class="page-header">
			<h1>
				Team Indra <small>Portal to schedule job and check job
					status</small>
			</h1>
		</div>

		<!-- Collapsible Panels - START -->
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Submit Job</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">

							<form class="form-horizontal" role="form" id="submitJobForm"
								method="post" action="./submitjob">

								<div class="alert alert-success" id="result">
									<label id="result_success">Success</label>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="jobName">Job-name:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="jobName"
											placeholder="Enter name for job">
						  			</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">IU
										Email:</label>
									<div class="col-sm-10">
										<input type="email" class="form-control" id="email"
											placeholder="Enter IU email">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="nodes">Node
										count:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="nodes"
											placeholder="Enter node count">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="maxMemory">Max
										memory:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="maxMemory"
											placeholder="Enter max memory">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="wallTime">Wall
										Time:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="wallTime"
											placeholder="Enter wall time">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="cores">Core
										count:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="cores"
											placeholder="Enter core count">
									</div>
								</div>


								<!-- <div class="form-group">
									<label class="control-label col-sm-2" for="userInputFile">Input file:</label>
									<div class="col-sm-10">
										<input
										type="file" id="userInputFile" placeholder="Select input file">
									</div>
								</div> -->

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="checkbox" id="sendMail">
											<label><input type="checkbox" checked="checked">
												Send notification</label>
										</div>
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">Submit</button>
									</div>

								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Job Status Monitor</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">
						
						<table id="grid-basic" class="table table-condensed table-hover table-striped">
						    <thead>
						        <tr>
						            <th data-column-id="jobId">Job Id</th>
						            <th data-column-id="jobName">Job Name</th>
						             <th data-column-id="status">Status</th>
						             <th data-column-id="link">Download</th>
						            <!-- <th data-column-id="jobSubmitTime" data-order="desc">Submitted On</th> -->
						        </tr>
						    </thead>
							<tbody>							
							</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Cancel Job</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">
							<form class="form-search" role="form" id="jobcancel">
								<input type="text" class="form-control" id="cancel_jobId"
									placeholder="Enter Job ID">
								<div class="alert alert-success" id="cancel_result">
									<label id="cancel_result_success">Job successfully
										cancelled</label>
								</div>
								<button type="submit" class="btn btn-default">Cancel
									Job</button>
							</form>
						</div>
					</div>
				</div>
				
				
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Input File Upload</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">
							<form class="form-search" enctype="multipart/form-data" role="form" id="upload_file"  >
								<input type="file" name="file" class="form-control" id="fileLoader">
								<button type="submit" id="fileSubmit" class="btn btn-default">Upload
								</button>
								<div class="alert alert-success" id="file_upload_result">
									<label id="file_upload_result_success">Success</label>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style>
.panel-heading span {
	margin-top: -20px;
	font-size: 15px;
}

.row {
	margin-top: 40px;
	padding: 0 10px;
}

.clickable {
	cursor: pointer;
}

.btn-file {
	position: relative;
	overflow: hidden;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}
</style>

	<script type="text/javascript">
		jQuery(function($) {
			$('.panel-heading span.clickable').on(
					"click",
					function(e) {
						if ($(this).hasClass('panel-collapsed')) {
							// expand the panel
							$(this).parents('.panel').find('.panel-body')
									.slideDown();
							$(this).removeClass('panel-collapsed');
							$(this).find('i').removeClass(
									'glyphicon-chevron-down').addClass(
									'glyphicon-chevron-up');
						} else {
							// collapse the panel
							$(this).parents('.panel').find('.panel-body')
									.slideUp();
							$(this).addClass('panel-collapsed');
							$(this).find('i').removeClass(
									'glyphicon-chevron-up').addClass(
									'glyphicon-chevron-down');
						}
					});
		});
	</script>

	<script type="text/javascript">
		
	
		$(window).load(function() {
			$("#result").hide();
			$("#cancel_result").hide();
			$("#file_upload_result").hide();
			updateStatus();
		});

		$("#submitJobForm").submit(function(event) {
			// cancels the form submission
			event.preventDefault();
			submitForm();
		});
		
	  	$("#upload_file").submit(function(event) {
			// cancels the form submission
			event.preventDefault();
			uploadFile(event);
		});  

		$("#jobStatusForm").submit(function(event) {
			// cancels the form submission
			event.preventDefault();
			getStatus();
		});

		$("#jobcancel").submit(function(event) {
			// cancels the form submission
			event.preventDefault();
			cancelJob();
		});
/*
		function prepareUpload(event)
		{
		  files = event.target.files;
		  alert("File added");
		}
		*/
		
		function cancelJob() {
			// Initiate Variables With Form Content
			var jobId = $("#cancel_jobId").val();

			var jobStatus = {
				jobId : jobId
			};

			$.ajax({
				type : "GET",
				url : "./canceljob",
				contentType : "application/json",
				data : jobStatus,
				success : function(response) {
					if (response.success) {
						cancelFormSuccess();
					}
				}
			});
		}

		function submitForm() {
			// Initiate Variables With Form Content
			var jobName = $("#jobName").val();
			var email = $("#email").val();
			var nodes = $("#nodes").val();
			var maxMemory = $("#maxMemory").val();
			var wallTime = $("#wallTime").val();
			var cores = $("#cores").val();
			var userInputFile = $("#fileinput");

			var jobConfig = {
				jobName : jobName,
				email : email,
				nodes : nodes,
				maxMemory : maxMemory,
				wallTime : wallTime,
				cores : cores
			//userInputFile : userInputFile
			};

			$.ajax({
				type : "POST",
				url : "./submitjob",
				contentType : "application/json",
				data : JSON.stringify(jobConfig),
				success : function(response) {
					if (response.success) {
						formSuccess(response.message);
					}
				}
			});
		}

		function uploadFile() {
			
			//var datafields = $("upload_file");
			//alert(filename);
			var file = $('[name="file"]');
			var filename = $.trim(file.val());  	

				$.ajax({
					type : "POST",
					url : "./upload",
					enctype: 'multipart/form-data',
					data : new FormData(document.getElementById("upload_file")),
					processData : false,
					cache: false,
					contentType: false,
					success : function(response) {
						
							//formSuccess(response.message);
							uploadSuccess(response.message);
					
					},
				
				 error: function (xhr, ajaxOptions, thrownError) {
		                if (xhr.readyState == 0 || xhr.status == 0) {
		                    // not really an error
		                    return;
		                } else {
		                    alert("XHR Status = "+xhr.status);
		                    alert("Thrown Error = "+thrownError);
		                    alert("AjaxOptions = "+ajaxOptions)
		                }
		          }
				});
		}

		function formSuccess(message) {
			$("#result_success").text(message)
			$("#result").show();
			window.setTimeout(function() {
				$("#result").slideUp(500, function() {
					$("#result").hide();
				});
			}, 10000);
		}
		
		function uploadSuccess(message) {
			$("#file_upload_result_success").text(message)
			$("#file_upload_result").show();
			window.setTimeout(function() {
				$("#file_upload_result").slideUp(500, function() {
					$("#file_upload_result").hide();
				});
			}, 10000);
		}

		function cancelFormSuccess(message) {
			$("#cancel_result_success").text(message)
			$("#cancel_result").show();
			window.setTimeout(function() {
				$("#cancel_result").slideUp(500, function() {
					$("#cancel_result").hide();
				});
			}, 5000);
		}

		function getStatus() {
			// Initiate Variables With Form Content
			var jobId = $("#jobId").val();

			var jobStatus = {
				jobId : jobId
			};

			$.ajax({
				type : "GET",
				url : "./getjobstatus",
				contentType : "application/json",
				data : jobStatus,
				success : function(response) {
					if (response.success) {
						//formSuccess();
						$('#jobStatusText').val(response.message);
					}
				}
			});
		}
		
		function updateStatus() {

			 $.ajax({
				type : "GET",
				url : "./gejobstatusforuser",
				contentType : "application/json",
				success : function(response) {
					if (response.success) {
						
						$(response.data).each(function(index){
						    this.link = '<a href=' + '\"./downloadoutput?jobId=' + this.jobId + "\"" + '><img src="./resources/images/download.png" alt="download output" width="30" height="30" border="0"></a>';
						});
						
						$("#grid-basic").bootgrid().bootgrid("append", response.data);
					}
				}
			});  
		}
	</script>
	<!-- Collapsible Panels - END -->
	</div>
</body>
</html>

</body>
</html>
