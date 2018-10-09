var _is_stop = false;
$(function() {
	$('#sub-cat-btn').stop().mouseover(function() {
		$(this).find('.flow-sub-cat').stop().show('fast')
	}).mouseout(function() {
		$(this).find('.flow-sub-cat').stop().hide('fast')
	})
	$("#go_top").click(function() {
		$('html,body').animate({
			scrollTop : 0
		}, 500);
		return false;
	});

	$("#bigimg-box .window-right-box ul li").click(function() {
		var index = $("#bigimg-box .window-right-box ul li").index(this)
		setPic(index)
	})

	$('.has-sub').stop().mouseover(function() {
		$(this).addClass('active')
		$(this).find('.first-a').css({
			'color' : '#208ECF'
		})
		$(this).find('.sub-nav-box').stop().slideDown('fast')
	}).mouseout(function() {
		$(this).removeClass('active')
		$(this).find('.first-a').css({
			'color' : '#333'
		})
		$(this).find('.sub-nav-box').stop().slideUp('fast')
	})

	$(".nav-list a").stop().mouseover(function() {
		$(this).css({
			'color' : '#208ECF'
		})
	}).mouseout(function() {
		$(this).css({
			'color' : '#333'
		})
	})
	$('.big-pic-box,.left-btn,.right-btn').mouseover(function() {
		endInterval();
	}).mouseout(function() {
		if (_is_stop)
			return;
		startInterval();
	})
	$(".bigpic-box .play").click(function() {
		$(this).hide().siblings('.stop').show();
		startInterval();
		_is_stop = false
	})
	$(".bigpic-box .stop").click(function() {
		$(this).hide().siblings('.play').show();
		endInterval();
		_is_stop = true
	})
})

var timer = null;
function startInterval() {
	// timer = setInterval(lunbo,3000);
}
function endInterval() {
	// clearInterval(timer);
}

function lunbo(diect) {
	var obj = $("#bigimg-box .window-right-box ul li")

	var index = obj.index($(".active"))

	if (diect == 'pre') {
		index -= 1;
	} else {
		index += 1;
	}

	if (index == obj.length) {
		obj.eq(1).add('active').siblings('li').removeClass('active')
	}

	// 获取数据
	setPic(index)
	imageListClick(obj[index].id);
}
function setPic(i) {
	var obj = $("#bigimg-box .window-right-box ul li").eq(i);
	obj.siblings('li').removeClass('active');
	obj.addClass('active');

	var imgurl = obj.find('img').attr('src');
//	imgurl = imgurl.replace("MIN","YUAN");
	$("#bigimg-box .window-left-box .big-pic-box img").attr('src', imgurl);
}
$(function() {
	setInterval(set, 3000)
	function set() {
		$(".item").on('mouseover', function() {
			if ($(this).find('span .img-cat').text().length == 0) {
				$(this).find('.action-coll').stop().animate({
					top : '5px'
				}, 'fast')
				$(this).find('.img-cat').stop().animate({
					bottom : '5px'
				}, 'fast')
			}
		})
		$(".item").on('mouseout', function() {
			$(this).find('.action-coll').stop().animate({
				top : '-30px'
			}, 'fast')
			$(this).find('.img-cat').stop().animate({
				bottom : '-30px'
			}, 'fast')
		})
		$(".item").on('click', function() {
			$('#bigimg-box').show();
			endInterval();
			startInterval();
		})
	}
})
