<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.form-control2 {
/*   display: block; */
  width: 40%;
  height: calc(2.25rem + 2px);
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  box-shadow: inset 0 0 0 rgba(0, 0, 0, 0);
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
</style>

<div id="body">
	
	<!-- Content Wrapper. Contains page content -->
  	<div class="content-wrapper">
		
		<!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">자동차 등록</h1>
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="#">Home</a></li>
	              <li class="breadcrumb-item active">자동차 목록</li>
	            </ol>
	          </div><!-- /.col -->
	        </div><!-- /.row -->
	      </div><!-- /.container-fluid -->
	    </div>
	    <!-- /.content-header -->
		
		<!-- Main content -->
	    <section class="content">
	    	<div class="container-fluid">
				<div class="card card-primary">
	              <div class="card-header">
	                <h3 class="card-title">고객의 자동차를 등록합니다.</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form action="/car/insert" method="post" id="cusFrm" 
	                enctype="multipart/form-data">	                
	                <div class="card-body">
		                	고객 번호
		                <div class="row">
		                <input type="text" class="form-control2" name="cusNum" id="cusNum" 
		                	readonly="true" placeholder="고객 번호를 입력하세요">
		                &nbsp;&nbsp;<button type="button" class="btn btn-block btn-secondary" style="width:15%;"
		                onclick="fn_owin()">검색</button>
		                </div>
		                <br /><br />
		                <div style="float:right;clear:both;width:100%;">
		                	<span style="cursor:pointer;" id="adding">[추가]</span>&nbsp;
	                		<span style="cursor:pointer" id="deleting">[삭제]</span><br />
	                	</div>
	                	<div id="divAdd">
	                    </div>
	                </div>
	                <!-- /.card-body -->
	                <div class="card-footer text-right">
	                  <button type="button" class="btn btn-primary" id="btnSubmit">등록</button>
	                  <button type="button" class="btn btn-default" onclick="location.href='/cus/list'">취소</button>
	                </div>
	              </form>
	            </div>
			</div>
		</section>
	
	</div>
</div>
<script type="text/javascript">
function fn_owin(){
	window.open("/car/popup/cusList","owin","width=800,height=500");
}

//jquery
$(function(){
	//등록버튼 클릭 시 name값 보정 후 submit
	$("#btnSubmit").on("click",function(){
		//고객번호 체킹
		if($("#cusNum").val()==""){
			alert("고객을 검색해주세요");
			return;
		}
		
		//자동차 등록여부 체크
		var carCheck = 0;
		$("#divAdd div").each(function(index){
			carCheck++;
		});
		
		if(carCheck<1){
			alert("자동차를 등록해주세요");
			return;
		}
		
		var cnt = 0;
		var error = 0;
		//1. name값 보정
		$("#divAdd div").each(function(index){
			console.log(cnt);
			
			if($(this).find("input[name=carNum]").val()==""){alert("자동차번호를 입력해주세요");error=1;return;}
			$(this).find("input[name=carNum]").attr("name","carVo[" + cnt + "].carNum");
			$(this).find("input[id=carNum]").attr("id","carVo[" + cnt + "].carNum");
			
			if($(this).find("input[name=mk]").val()==""){alert("제조사를 입력해주세요");error=1;return;}
			$(this).find("input[name=mk]").attr("name","carVo[" + cnt + "].mk");
			$(this).find("input[id=mk]").attr("id","carVo[" + cnt + "].mk");
			
			if($(this).find("input[name=py]").val()==""){alert("연식을 입력해주세요");error=1;return;}
			$(this).find("input[name=py]").attr("name","carVo[" + cnt + "].py");
			$(this).find("input[id=py]").attr("id","carVo[" + cnt + "].py");
			
			if($(this).find("input[name=driDist]").val()==""){alert("주행거리를 입력해주세요");error=1;return;}
			$(this).find("input[name=driDist]").attr("name","carVo[" + cnt + "].driDist");
			$(this).find("input[id=driDist]").attr("id","carVo[" + cnt + "].driDist");
			
			cnt = cnt + 1;
		});
		
		//2. form을 submit
		if(error<1){
			$("#cusFrm").submit();
		}
	});
	
	//자동차 추가 클릭
	$("#adding").on("click",function(){
		var str = "<div style='float:left;clear:both;width:100%;margin-bottom:10px;'>"
				+"자동차 번호<input type='text' class='form-control2' name='carNum' id='carNum' placeholder='자동차 번호를 입력하세요'>"
	            +"제조사<input type='text' class='form-control2' name='mk' id='mk' placeholder='제조사를 입력하세요'>"
	        	+"<br />연식<input type='text' class='form-control2' name='py' id='py' placeholder='연식을 입력하세요'>"
	        	+"주행 거리<input type='text' class='form-control2' name='driDist' id='driDist' placeholder='주행 거리를 입력하세요'>"
	    		+"</div>";
		
		$("#divAdd").append(str);
	});
	
	//자동차 정보 삭제
	$("#deleting").on("click",function(){
		$("#divAdd > div:last").remove();
	});
});
</script>











