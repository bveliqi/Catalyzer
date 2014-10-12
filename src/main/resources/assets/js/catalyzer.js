/**
 * 
 */
$(function() {
	function showSuccess(element, msg) {
		element.find(".notify-success").text(msg);
		element.find("input").hide();
		element.find("textarea").hide();
		element.find(".supportProject").hide();
		element.find(".participateProject").hide();
	}
	
	function showError(element, msg) {
		element.find(".notify-fail").text(msg);
	}
	
	function reset(element) {
		element.find(".notify-success").empty();
		element.find(".notify-fail").empty();
		element.find("input").show();
		element.find(".supportProject").show();
	}
	
	function compare(a, b) {
		var percentA = Math.floor(100 * (a.points / a.pointsThreshold));
		var percentB = Math.floor(100 * (b.points / b.pointsThreshold));
		
		console.log("comparing " + percentA + " and " + percentB);
		if (percentA < percentB)
		    return 1;
		if (percentA > percentB)
		    return -1;
		return 0;
	}
	
	function loadProjects() {
		$.get("/project/", function(data) {
			
			$(".projects").empty();
			
			data.sort(compare);
			
			$.each(data, function(index, project) {
				
				console.log("Adding project " + project.name);
				var percent = Math.floor(100 * (project.points / project.pointsThreshold));
				
				if (percent > 100) {
					percent = 100;
				}
				
				var startDate = project.startDate;
				console.log("Start date: " + startDate + " => " + new Date(startDate));
				
				var participantsHtml = "";
				$.get("/project/" + project.id, function(result) {
					for (var i = 0; i < result["applyingUsers"].length; i++) {
						var user = result["applyingUsers"][i];
						participantsHtml += '<div class="col-md-1">' +
						'  <img class="supporters-img" src="img/avatar' + user["avatar"] + '.jpg" />' +
						'</div>';
					}
					
					var html = '<div class="row gap">' +
					'		<div class="col-md-4">' +
					'			<img src="' + project.photoUrl +
					'" class="project-img" />' +
					'			<div class="progress-bar-container">' +
					'				<div class="progress-bar">' +
					'					<div class="progress-bar-progress" style="width: ' + percent + '%"></div>' +
					'				</div>' +
					'				<button class="btn btn-primary btn-vote" data-toggle="modal" data-target="#votingModal' + project.id + '">Vote</button>' +
					'				<div class="progress-indicator">' + percent + ' %' + 
					'</div>' +
					'			</div>' +
					'		</div>' +
					'		<div class="col-md-6">' +
					'			<div class="row">' +
					'				<h4 class="projects-heading">' + project.name + '</h4>' +
					'				<p class="text-muted">' + project.motivation + '</p>' +
					'			</div>' +
					'			<div class="row">' +
					'				<div class="col-md-2 no-padding-left">date</div>' +
					'				<div class="col-md-6 no-padding-left">' + new Date(startDate) + '</div>' +
					'			</div>' +
					'			<div class="row">' +
					'				<div class="col-md-2 no-padding-left">status</div>' +
					'				<div class="col-md-6 no-padding-left">' + project.status + '</div>' +
					'			</div>' +
					'		</div>' +
					'		<div class="col-md-2">' +
					'			<h4 class="projects-supporters">Participants</h4>' +
					participantsHtml +
					'			<br/><br/><div class="row">' +
									'<button class="btn btn-primary" style="margin-left: 20px;" data-target="#participateModal' + project.id + '" data-toggle="modal">Participate</button>' +
									'<a href="project.html#' + project.id + '" class="btn btn-info" style="margin-left: 20px;">More info</a>' +
								'</div>' +
							'</div>' +
						'</div>' +
						'<div class="modal" id="votingModal' + project.id + '">' +
							'<div class="modal-dialog">' +
							'	<div class="modal-content">' +
							'		<div class="modal-header">' +
							'			<button type="button" class="close closeSupportProject" data-dismiss="modal">' +
							'				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
							'			</button>' +
							'			<h4 class="modal-title">Support this project</h4>' +
							'		</div>' +
							'		<div class="modal-body">' +
							'			<div class="note">Support the project by giving points. You gain points by visiting' +
							'			the web page, by following or participating in projects or by' +
							'			adding suggestions, questions or notes to projects.</div>' +
							'			<div class="centered">' +
							'				<div class="notify-success"></div>' +
							'				<div class="notify-fail"></div>' +
							'				<input type="text" placeholder="10pts" class="large-input">' +
							'				<button class="btn btn-primary supportProject" data-id="' + project.id + '"><img src="img/piggy_bank.png" width="30px">Tip</button>' +
							'			</div>' +
							'		</div>' +
							'		<div class="modal-footer">' +
							'			<button type="button" class="btn btn-default btn-vote-modal closeSupportProject" data-dismiss="modal" data-id="' + project.id + '">Close</button>' +
							'		</div>' +
							'	</div>' +
							'</div>' +
						'</div>' +
						'<div class="modal" id="participateModal' + project.id + '">' +
							'<div class="modal-dialog">' +
							'	<div class="modal-content">' +
							'		<div class="modal-header">' +
							'			<button type="button" class="close closeParticipateProject" data-dismiss="modal">' +
							'				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>' +
							'			</button>' +
							'			<h4 class="modal-title">Participate in the project</h4>' +
							'		</div>' +
							'		<div class="modal-body">' +
							'			<div class="note">Join the activity and have a positive impact on the world</div>' +
							'			<textarea class="reason" placeholder="Say something about yourself"></textarea>' +
							'			<div class="centered">' +
							'				<div class="notify-success"></div>' +
							'				<div class="notify-fail"></div>' +
							'			</div>' +
							'		</div>' +
							'		<div class="modal-footer">' +
							'			<button type="button" class="btn btn-primary btn-vote-modal participateProject" data-id="' + project.id + '">Participate</button>' +
							'			<button type="button" class="btn btn-default btn-vote-modal closeParticipateProject" data-dismiss="modal" data-id="' + project.id + '">Close</button>' +
							'		</div>' +
							'	</div>' +
							'</div>' +
						'</div>';
						
						$(".projects").append(html);
				});
				

			});
		});
	}
	
	loadProjects();
	
	$(document).on("click", ".supportProject", function() {
		console.log("Clicked support project");
		var id = $(this).attr("data-id");
		var points = $(this).parent().find("input").val();
		console.log("Upvoting with " + points + " points");
		
		$.post("/project/" + id + "/upvote/" + points, function(data) {
			showSuccess($("#votingModal" + id), "Your karma points where transmitted");
		}, function (err) {
			showError($("#votingModal" + id), "Something went wrong :(");
		});
	});
	
	$(document).on("click", ".closeSupportProject", function() {
		var id = $(this).attr("data-id");
		reset($("#votingModal" + id));
		loadProjects();
	});

	$(document).on("click", ".participateProject", function() {
		
		var id = $(this).attr("data-id");
		var reason = $("#participateModal" + id).find(".reason").text();
		console.log("Participating in project " + id);
		
		var userid = currentUser;
		console.log("Userid: " + userid);
		
		var participation = {"projectId":id, "userId":"100000000", "role":"participant", "reason": reason, "state": "applying"};
		
		var request = $.ajax({
            type        :   "POST",
            url         :   "/participation/",
            data        :   JSON.stringify(participation),
            contentType :   "application/json"
		}).done(function(data) {
			showSuccess($("#participateModal" + id), "You joined successfully");
		}).fail(function(data) {
			if (data.status === 201) {
				showSuccess($("#participateModal" + id), "You joined successfully");
			} else {
				showError($("#participateModal" + id), "Something went wrong :(");
			}
		});
		
	});
	
});