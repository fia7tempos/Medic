<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
<img src="../img/lights.jpg" id="bg_img" hidden="hidden"/>
<img src="../img/lights2.jpg" id="overlay_img" hidden="hidden"/>
<canvas id="title" width="800px" height="600px">
</canvas>


<script type="text/javascript">
var canvas =null;
var pic_op = 0.1;
var op_plus = true;
var ctx;
var bg_img = document.getElementById('bg_img');
var bg_overlay_img = document.getElementById('overlay_img');
window.onload = draw_screen();

function draw_screen(){
	canvas =  document.getElementById("title");
	ctx = canvas.getContext("2d");
	paint();
}

function paint(){
	ctx.clearRect(0,0,800,600);
	ctx.globalCompositeOperation = "screen";
	ctx.globalAlpha = 1.0;
	ctx.drawImage(bg_img,0,0);
	ctx.globalAlpha = pic_op;
	ctx.drawImage(bg_overlay_img,0,0);
}

function update_screen(){
	if(pic_op>=0.9) {
		op_plus = false; 
	}else if(pic_op<=0.1){
		op_plus = true;
	}
	if(op_plus){
		pic_op += 0.03;		
	}else{
		pic_op -= 0.03;
	}
	paint();
}

setInterval(update_screen,50);

</script>
</body>
</html>