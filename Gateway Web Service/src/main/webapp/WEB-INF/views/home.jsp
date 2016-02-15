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
	href="./resources/font-awesome/css/font-awesome.min.css" />


<script type="text/javascript"
	src="./resources/bootstrap/js/bootstrap.min.js"></script>
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
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Submit Job</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">

							<form class="form-horizontal" role="form"  method="post" action="./submitjob">
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Job-name:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="jobName"
											placeholder="Enter name for job">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">IU Email:</label>
									<div class="col-sm-10">
										<input type="email" class="form-control" id="email"
											placeholder="Enter IU email">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Node count:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="nodes"
											placeholder="Enter node count">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Max memory:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="maxMemory"
											placeholder="Enter max memory">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Wall Time:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="wallTime"
											placeholder="Enter wall time">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Core count:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="cores"
											placeholder="Enter core count">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="userInputFile">Input file:</label>
									<div class="col-sm-10">
										<input
										type="file" id="userInputFile" placeholder="Select input file">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="checkbox" id="sendMail">
											<label><input type="checkbox" checked="checked"> Send notification</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default" >Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>	
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Job Status Monitor</h3>
							<span class="pull-right clickable"><i
								class="glyphicon glyphicon-chevron-up"></i></span>
						</div>
						<div class="panel-body">Monitor job here</div>
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
</style>

		<script type="text/javascript">
    jQuery(function ($) {
        $('.panel-heading span.clickable').on("click", function (e) {
            if ($(this).hasClass('panel-collapsed')) {
                // expand the panel
                $(this).parents('.panel').find('.panel-body').slideDown();
                $(this).removeClass('panel-collapsed');
                $(this).find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
            }
            else {
                // collapse the panel
                $(this).parents('.panel').find('.panel-body').slideUp();
                $(this).addClass('panel-collapsed');
                $(this).find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
            }
        });
    });
</script>

<script type="text/javascript">

$(function() {
	//twitter bootstrap script
	 $("button#submit").click(function(){
	         $.ajax({
	     type: "POST",
	 url: "process.php",
	 data: $('form.contact').serialize(),
	         success: function(msg){
	                 $("#thanks").html(msg)
	        $("#form-content").modal('hide'); 
	         },
	 error: function(){
	 alert("failure");
	 }
	       });
	 });
	});

</script>

		<!-- Collapsible Panels - END -->

	</div>
</body>
</html>

</body>
</html>
