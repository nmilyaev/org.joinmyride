$(document).ready(function () {
  //your code here
  $('.delete').click(function (){
       var answer = confirm("Are you sure you want to delete this entry?");
          if (answer) {
             return true;
          }else{
             return false;
          }
    });
});
