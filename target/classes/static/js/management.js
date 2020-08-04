$(document).ready(function(){
	console.log("start");
  getCompanies();

	$('.company-table').on('click', '.delete-btn', function(){
  	var id = $(this).attr('id');
    deleteCompany(id);
	});
});

function getCompanyById() {

}

function deleteCompany(id) {
$.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/company/delete',
        data: {"companyId": id}, //TODO
        dataType: 'text',
        success: function (response) {
  	      console.log("response: " + response);
  	      getCompanies();
        },
        error:function(xhr, textStatus, errorThrown){
          console.log("1111111delete error"); // 資料有被刪除，但跳到這行
  	      console.log("textStatus: " + textStatus);
  	      console.log("errorThrown: " + errorThrown);
          alert("發生錯誤: " + xhr.status + " " + xhr.statusText);
          getCompanies();
        }
      });
}

function getCompanies() {
	$('.company-info').empty();
	$.ajax({
		type: 'GET',
    url: 'http://localhost:8080/api/company/all',
    dataType: 'json',
    success: function (response) {
      $.each(response, function(index, element) {
    	  $('.company-table').append(
    	   "<tr class='row-data'"
    	   + "><th class='company-id' id='" + element.companyId + "'>" + element.companyId
    	   + "</td><td class='company-name'>" + element.companyName
    	   + "</td><td>" + element.unifiedBusinessNumber
    	   + "</td><td>" + element.phoneNumber
    	   + "</td><td>" + element.completedBy
    	   + "</td><td>" + element.updateDate
    	   + "</td>"
    	   + "<td><button type='button' class='btn btn-primary edit-btn' id='" + element.companyId + "'>編輯</button></td>"
    	   + "<td><button type='button' class='btn btn-secondary delete-btn' id='" + element.companyId + "'>刪除</button></td>"
    	   +"</tr>"
//  	      $('<tr>'),
//  	        $('<td>',
//  	          {text: element.companyId, id: element.companyId, class: "company-id"}),
//  	        $('<td>',
//  	          {text: element.companyName}),
//  	        $('<td>',
//              {text: element.unifiedBusinessNumber}),
//  	        $('<td>',
//             {text: element.phoneNumber}),
//            $('<td>',
//              {text: element.completedBy}),
//            $('<td>',
//              {text: element.updateDate}),
//            $('<td><button type="button" class="btn btn-primary edit-btn">編輯</button></td>'),
//            $('<td><button type="button" class="btn btn-secondary delete-btn">刪除</button></td>')
    		);
    	});
    },
    error:function(xhr){
      alert("發生錯誤: " + xhr.status + " " + xhr.statusText);
    }
  });
}

