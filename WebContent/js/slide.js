$(function(){    
	var str
	var target = [];
	var len=$(".image").length;
	for ( i = 0; i < len; i++)
	{
		target[i]="jt-"+(i+1);	
		
	}
    //change opacity
    $('.word').css({opacity: 0.85});
	var eleWid = 0;
	
	//var ele =document.getElementById("slideList").getElementsByTagName("li");

	var ele = $("#slideList li")
//	var img = $(".image img")
	var num = ele.length;
	for ( i=0; i<num; i++)
		{
			eleWid += parseInt(ele.eq(i).css("width").replace("px","")) + 10;
//			var imgH=parseInt(img.eq(i).css("width").replace("px",""));
//			img.eq(i).css("margin-top",-(imgH/2));
		}
	$("#slideList").css({"width":eleWid});
   //auto();  
    hookThumb(); 
    hookBtn();
	bighookBtn()
    

	var timer = null;
	var offset = 5000;
	var index = 0;
	var slideList=document.getElementById("slideList")
	
	//大图交替轮换
	function slideImage(i){
		var id = 'image_'+ target[i];
		//alert("slideImage id:"+id);
		$('#'+ id)
			.animate({opacity: 1}, 800, function(){
				$(this).find('.word').animate({height: 'show'}, 'slow');
			}).show()
			.siblings(':visible')
			.find('.word').animate({height: 'hide'},'fast',function(){
				$(this).parent().animate({opacity: 0}, 800).hide();
			});
	}
	//bind thumb a
	function hookThumb(){    
		$('#thumbs li a')
			.bind('click', function(){
				if (timer) {
					clearTimeout(timer);
				}                
				var id = this.id;            
				index = getIndex(id.substr(6));
				rechange(index);
				slideImage(index); 
				//timer = window.setTimeout(auto, offset);  
				this.blur();            
				return false;
			});
	}
	//bind next/prev img
	function hookBtn(){
		$('#thumbs a img').filter('#play_prev,#play_next')
			.bind('click', function(){
				var eleWrapWid = parseInt($("#slideListWrap").css("width").replace("px",""));
				var eleListML =parseInt($("#slideList").css("margin-left").replace("px",""));
				var id = this.id;
				if (id == 'play_prev') {

					if ( eleListML == 0 ){
						//alert("到第一屏了");
						}
					else{
						eleListML += eleWrapWid;
						}
				}else{

					if ( eleListML >= eleWid-eleWrapWid ){
						//alert("到最后一屏了");
						}
					else{
						eleListML -= eleWrapWid;
						}
				
				}
				slideList.style.marginLeft=eleListML+"px";
			});
	}

	//bigBtn
	function bighookBtn(){
		$('#bigpicarea p').filter('#big_play_prev,#big_play_next').bind('click', function(){
				if (timer){
					clearTimeout(timer);
				}
				var id = this.id;
				if (id == 'big_play_prev') {
					index--;
					if (index < 0) index = target.length;
					var line = index/7;
					if ( index%7 ==0 ){
						slideList.style.marginLeft =-(line-1) * 927 + "px";	
						}
					if(line<1) slideList.style.marginLeft = 0;
				}else{
					index++;
					if (index > target.length) index = 0;
					var line = index/7;
					if ( index%7 ==0 ){
						slideList.style.marginLeft = -(line * 927) + "px";	
						}
				}
				rechange(index);
				slideImage(index);
				//timer = window.setTimeout(auto, offset);
			});
	}
	
	//get index
	function getIndex(v){

		for(var i=0; i < target.length; i++){
			if (target[i] == v) return i;
			
		}
	}
	//current
	function rechange(loop){
		
		var id = 'thumb_'+ target[loop];
		$('#thumbs li a.current').removeClass('current');
		$('#'+ id).addClass('current');

	}

});