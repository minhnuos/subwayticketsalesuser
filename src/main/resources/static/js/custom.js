$(document).ready(function(){
  
  			$(".detail-booking").click(function(event) {
			event.preventDefault();
			var id = $(this).attr("data-id");
			
			
			$.ajax({
			  type:"GET",
				url: "http://localhost:8081/api/detail-booking",
				data: {
					 id
					 
				},
				statusCode: {
				    200: function(data) {
				    let text = "";
				    	
				    	for(let i = 0 ; i < data.length;i++) {
				    		text += '<h5 style="color: #28a745;">'
				    		text += data[i].code;
				    		text += '</h5>'
				    		
				    		
				    	}
				      	Swal.fire({
							  title:'<h3 style="color:#faa61a;">Danh sách mã vé</h3>',
							  html:text,
							  width:600,
							  
							  
							})
						
				    }
				}
			});
	
	});
  		
  		var getQuantityCart = function() {
  			$.ajax({
				  	type:"GET",
					url: "http://localhost:8081/api/quantity-cart",
					statusCode: {
					    200: function(data) {
					    	$( "#cart" ).append( `<span id="quantity-cart">${data}</span>`);
					    }
					}
			})
  		}
  
  		getQuantityCart();
  
  
  		
  	  	$(".add-cart").click(function() {
			var id = $(this).attr("data-id");
			var day = $(this).attr("data-day");
			var stopPointStart = $(this).attr("data-start");
			var stopPointEnd = $(this).attr("data-end");
			var time = $(this).attr("data-time");
			var price = $(this).attr("data-price");
			var tour = $(this).attr("data-tour");
			
			$.ajax({
			  type:"POST",
				url: "http://localhost:8081/api/add-cart",
				data: {
					 id,
					 day,
					 tour,
					price,
					time,
					stopPointEnd,
					stopPointStart
				},
				statusCode: {
				    200: function(data) {
				    	
				      	Swal.fire({
						  position: 'center',
						  icon: 'success',
						  title: 'Thêm giỏ hàng thành công',
						  showConfirmButton: false,
						  timer: 1500
						}),
						getQuantityCart();
				    }
				}
			});
	
	});
	
	
	$(".btn-delete-cart").click(function() {
		var id = $(this).attr("data-id");
		var tour = $(this).attr("data-tour");
		var tr = $(this).parent().parent();
		Swal.fire({
		  title: 'Bạn có chắc?',
		  text: "Bạn muốn xóa vé này khỏi giỏ hàng!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Xóa',
		  cancelButtonText: 'Hủy'
		}).then((result) => {
			  if (result.isConfirmed) {
			    	$.ajax({
						  type:"POST",
							url: "http://localhost:8081/api/delete-cart",
							data: {
								 id,
								 tour
							},
							statusCode: {
							    200: function(data) {
										getQuantityCart();
										tr.remove();
										Swal.fire({
										  position: 'center',
										  icon: 'success',
										  title: 'Đã xóa',
										  showConfirmButton: false,
										  timer: 1500
										})
							    	}
							}
					});
			  }
		})
		
	
	});
	
	$('.qtybutton').on('click', function () {
		var $button = $(this);
		var oldValue = $button.parent().find('input').val();
		if ($button.hasClass('inc')) {
			var newVal = parseFloat(oldValue) + 1;
		} else {
			// Don't allow decrementing below zero
			if (oldValue > 1) {
				var newVal = parseFloat(oldValue) - 1;
			} else {
				newVal = 1;
			}
		}
		$button.parent().find('input').val(newVal);
		var id = $(this).attr("data-id");
		var quantity = $button.parent().find('input').val();
		var tour = $(this).attr("data-tour");
		var proSubtotal = $button.parent().find('input').parent().parent().parent().next().children('span');
		console.log("thay đổi");
		$.ajax({
		  type:"POST",
			url: "http://localhost:8081/api/change-quantity-cart",
			data: {
				 id,
				 
				 tour,
				quantity,
				
			},
			statusCode: {
			    200: function(data) {
			    	var total = data.price*data.quantity;
			    	proSubtotal.text(total);
			    }
			}
		});
		
	});
	
	
	$('#booking').click(function(event) {
	    event.preventDefault();
	    let name = $('#username').val();
	    let phone = $('#phone').val();
	    let address = $('#address').val();
	    if(username == '' || phone == '' || address == '' ) {
	        Swal.fire('Vui lòng nhập đầy đủ thông tin');
	    } else if($('#quantity-cart').text() == 0) {
	        Swal.fire('Giỏ hàng đang trống');
	    } else {
	        Swal.fire({
	            title: 'Bạn có chắc?',
	            text: "Bạn muốn đặt vé!",
	            showCancelButton: true,
	            confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',
	            confirmButtonText: 'Đặt vé',
	            cancelButtonText: 'Hủy'
	          }).then((result) => {
	                if (result.isConfirmed) {
	                    $.ajax({
	                        type:"POST",
	                      url: "http://localhost:8081/api/booking",
	                      data: {
	                           name,
	                           phone,
	                           address
	                          
	                      },
	                      statusCode: {
	                          200: function(data) {
	                              if(data.status) {
	                                  Swal.fire({
	                                        position: 'center',
	                                        icon: 'success',
	                                        title: `${data.message}`,
	                                        showConfirmButton: false,
	                                        timer: 1500
	                                  })
	                              } else {
	                                  Swal.fire({
	                                        position: 'center',
	                                        icon: 'warning',
	                                        title: `${data.message}`,
	                                        showConfirmButton: false,
	                                        timer: 1500
	                                  })
	                              }
	                          }
	                      }
	                    });
	                }
	          })
	    }
	    
	})


	
});
