$(document).ready(function(){

    // jQuery methods go here...
      $("#deleteButton").click(function() {
          $("#deleteOrderForm").submit();
  
          
      });
      
      $("#deleteOrderForm").submit(function() {
          var checkboxes = $("input[name=orderCheckboxes]:checked");
          var form = $(this);
          if(checkboxes.length == 0) {
        	  return false;
          }
          checkboxes.each(function() {
              var order = "<input type=\"hidden\" name=\"orderCheckboxes\" value='" + $(this).val() +"' />"
              form.append(order);
          })
          
          return true;
      });
  });