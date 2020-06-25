$(document).ready(function(){

    // jQuery methods go here...
      $("#deleteButton").click(function() {
          $("#deleteProductForm").submit();
  
          
      });
      
      $("#deleteProductForm").submit(function() {
          var checkboxes = $("input[name=productCheckboxes]:checked");
          var form = $(this);
          if(checkboxes.length == 0) {
        	  return false;
          }
          checkboxes.each(function() {
              var user = "<input type=\"hidden\" name=\"productCheckboxes\" value='" + $(this).val() +"' />"
              form.append(user);
              
          })
          
          return true;
      });
  });