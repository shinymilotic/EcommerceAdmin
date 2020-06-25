$(document).ready(function(){

  // jQuery methods go here...
	$("#deleteButton").click(function() {
		$("#deleteUserForm").submit();

		
	});
	
	$("#deleteUserForm").submit(function() {
		var checkboxes = $("input[name=userCheckboxes]:checked");
		var form = $(this);
		if(checkboxes.length == 0) {
      	  return false;
        }
		checkboxes.each(function() {
			var user = "<input type=\"hidden\" name=\"userCheckboxes\" value='" + $(this).val() +"' />"
			form.append(user);
			
		})
		
		return true;
	});
});