/**
 * 
 */
$(function() {
	function showSuccess(element, msg) {
		element.parent().find(".notify-success").text(msg);
		element.parent().find("input").hide();
		element.hide();
	}
	
	function showError(element, msg) {
		element.parent().find(".notify-fail").text(msg);
	}
	
	function reset(element) {
		var supportProject = element.parent().parent().find(".supportProject");
		supportProject.parent().find(".notify-success").empty();
		supportProject.parent().find(".notify-fail").empty();
		supportProject.parent().find("input").show();
		supportProject.show();
	}
	
	$(".closeSupportProject").click(function() {
		reset($(this));
	});
	
	$(".supportProject").click(function() {
		
		var id = 1;
		var points = $(this).parent().find("input").val();
		console.log("Upvoting with " + points + " points");
		
//		$.post("/project/" + id + "/upvote/" + points, function(data) {
//			showSuccess($(this), "Your karma points where transmitted");
//		}, function (err) {
			showError($(this), "Something went wrong :(");
//		});
	});
});