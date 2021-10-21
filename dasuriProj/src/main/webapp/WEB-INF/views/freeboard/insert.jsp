<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/resources/ckeditor/ckeditor.js"></script>
<div id="body">
	
	<!-- Content Wrapper. Contains page content -->
  	<div class="content-wrapper">
		
		<!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">자유글 등록</h1>
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="#">Home</a></li>
	              <li class="breadcrumb-item active">자유게시판 목록</li>
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
	                <h3 class="card-title">자유게시글을 등록합니다.</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form action="/freeboard/insert" method="post" id="cusFrm" 
	                enctype="multipart/form-data">
	                <div class="card-body">
	                  <div class="form-group">
	                    <label for="cusNm">고객 이름</label>
	                    <input type="text" class="form-control" name="writer" id="writer" placeholder="이름을 입력하세요"
	                    value="${sessionScope.nm}" readOnly="true">
	                  </div>
	                  <div class="form-group">
	                    <label for="cusPwd">글제목</label>
	                    <input type="text" class="form-control" name="title" id="title" placeholder="제목 입력하세요">
	                  </div>
	                  <div class="form-group">
	                    <label for="addr1">글내용</label>
	                    <textarea class="form-control" name="content" id="content" placeholder="내용을 입력하세요"
	                    	cols="50" rows="10"></textarea>
	                  </div>	                  
	                  <div class="form-group">
	                    <label for="cusImage">첨부파일</label>
	                    <div class="img_wrap">
	                    	<img id="img" />
	                    </div>
	                    <div class="input-group">
	                      <div class="custom-file">
	                        <input type="file" class="custom-file-input" id="attach" name="attach">
	                        <label class="custom-file-label" for="attach" id="cusImageLabel">파일을 선택하세요</label>
	                      </div>
	                    </div>
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
	//e : 이미지가 change된 이벤트
	function handleImgFileSelect(e){
		//파일 객체에 파일을 담음
		var files = e.target.files;
		//멀티 파일이라면 배열로 처리
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){			
			//파일을 sel_file변수에 넣음
			sel_file = f;
			//파일을 읽는 reader 객체 생성
			var reader = new FileReader();	//1)
			reader.onload = function(e){	//3)
				//e.target.result : 해당 파일의 명(경로 포함)
				//<img id='img' /> => <img id='img' src='base64형태의 이미지자체' />
				$("#cusImageLabel").text(e.target.result);
			}
			//f : filesArr파일 배열에 들었는 파일 객체 자체
			reader.readAsDataURL(f);	//2) f(이미지파일객체를) 다 읽으면 => onload 됐다라고 인식 -> 3)이동
		}); //end for
		
	}

	$(function() {
		CKEDITOR.replace("content");
		
		//파일 경로 보기
		$("#attach").on("change",handleImgFileSelect);

		$("#btnSubmit").click(function() {
			
			if($("#cusNm").val()==""){
				alert("이름을 입력하세요.");
				$("#cusNm").focus();
				return;
			}
			if($("#cusPwd").val()==""){
				alert("비밀번호를 입력하세요.");
				$("#cusPwd").focus();
				return;
			}
			if($("#zipcode").val()==""){
				alert("우편번호를 입력하세요.");
				$("#zipcode").focus();
				return;
			}
			if($("#addr1").val()==""){
				alert("주소를 입력하세요.");
				$("#addr1").focus();
				return;
			}
			if($("#addr2").val()==""){
				alert("상세 주소를 입력하세요.");
				$("#addr2").focus();
				return;
			}
			if($("#pne").val()==""){
				alert("연락처를 입력하세요.");
				$("#pne").focus();
				return;
			}
			var regExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
			if(!regExp.test($("#pne").val())){
				alert("연락처는 010-0000-0000 형식으로 입력해주세요.");
				$("#pne").focus();
				return;
			} */
			if(CKEDITOR.instances.cusDetail.getData()==""){
				alert("상세정보를 입력해주세요.");
				CKEDITOR.instances.cusDetail.focus();
				return;
			}
			if($("#cusImage").val()==""){
				alert("사진을 선택하세요.");
				$("#cusImage").focus();
				return;
			}
			
			$("#cusFrm").submit();
		});
	});
	
	
</script>