<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/ddit.min.js"></script>

<div id="body">
	
	<!-- Content Wrapper. Contains page content -->
  	<div class="content-wrapper">
		
		<!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">고객 상세 정보</h1>
	          </div><!-- /.col -->
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="#">Home</a></li>
	              <li class="breadcrumb-item active">고객 관리 > 고객 상세 정보</li>
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
	                <h3 class="card-title">
	                	다수리 고객의 상세 정보 보기&nbsp;&nbsp;&nbsp;
	                	<c:if test="${param.message=='childfound'}">
	                	<font color="red" size="3pt">고객 정보를 삭제할 수 없습니다.</font>
	                	</c:if>
	                </h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form action="/cus/update" method="post" id="cusFrm" 
	                enctype="multipart/form-data">
	                <input type="hidden" name="cusNum" value="${cusVo.cusNum}" />
	                <div class="card-body">
	                  <div class="form-group">
	                    <label for="cusNm">고객 이름</label>
	                    <input type="text" class="form-control" name="cusNm" id="cusNm" value="${cusVo.cusNm}">
	                  </div>
	                  <div class="form-group">
	                    <label for="cusPwd">비밀번호</label>
	                    <input type="password" class="form-control" name="pwd" id="cusPwd" value="${cusVo.pwd}">
	                  </div>
	                  <div class="form-group">
	                    <label for="zipcode">우편번호</label>
	                    <table width="500">
	                    <tr>
	                    <td>
	                    	<input type="text" class="form-control" name="zipcode" id="zipcode" value="${cusVo.zipcode}">
	                    </td>
	                    <td>
	                    	<button type="button" class="form-control" onclick="openHomeSearch()">우편번호 검색</button>
	                    </td>
	                    </tr>
	                    </table>
	                  </div>
	                  <div class="form-group">
	                    <label for="addr1">주소</label>
	                    <input type="text" class="form-control" name="addr1" id="addr1" value="${cusVo.addr1}">
	                  </div>
	                  <div class="form-group">
	                    <label for="addr2">상세 주소</label>
	                    <input type="text" class="form-control" name="addr2" id="addr2" value="${cusVo.addr2}">
	                  </div>
	                  <div class="form-group">
	                    <label for="pne">연락처</label>
	                    <input type="text" class="form-control" name="pne" id="pne" value="${cusVo.pne}">
	                  </div>
	                  <div class="form-group">
	                    <label for="cusDetail">상세 정보</label>
	                    <span id="spanDetail1" style="display:none;">
	                  		<textarea name="cusDetail" id="cusDetail">${cusVo.cusDetail}</textarea>
	                  	</span>
	                  	<span id="spanDetail2">
	                  		<p>${cusVo.cusDetail}</p>
	                  	</span> 
	                  </div>
	                  <div class="form-group">
	                    <label for="cusImage">사진</label>
	                    <div class="img_wrap">
	                    	<img id="img" src="/resources/${cusVo.cusImageUpload}" />
	                    </div>
	                    <div class="input-group">
	                      <div class="custom-file">
	                        <input type="file" class="custom-file-input" id="cusImage" name="cusImage">
	                        <label class="custom-file-label" for="cusImage" id="cusImageLabel">사진을 선택하세요</label>
	                      </div>
	                      <input type="checkbox" name="cusImageCheck" id="cusImageCheck" style="display:none;" />
	                    </div>
	                  </div>
	                </div>
	                <!-- /.card-body -->
	                <div class="card-footer text-right" id="divFooter1" style="display:none;">
	                  <button type="button" class="btn btn-primary" id="btnSubmit">확인</button>
	                  <button type="button" class="btn btn-default" onclick="location.href='/cus/detail?cusNum=${param.cusNum}'">취소</button>
	                </div>
	                <div class="card-footer text-right" id="divFooter2">
	                  <button type="button" class="btn btn-primary" id="btnDown">파일다운로드</button>
	                  <button type="button" class="btn btn-primary" id="btnEdit"
	                  <c:if test="${sessionScope.adminYn!='Y'}">disabled="false"</c:if>
	                  >수정</button>
	                  <button type="button" class="btn btn-default" id="btnDelete"
	                  <c:if test="${sessionScope.adminYn!='Y'}">disabled="false"</c:if>
	                  >삭제</button>
	                  <button type="button" class="btn btn-primary" id="btnList" onclick="location.href='/cus/list'">목록</button>
	                </div>
	              </form>
	              <form method="post" action="/cus/delete" id="frmDelete" name="frmDelete">
	              	<input type="hidden" name="cusNum" value="${cusVo.cusNum}" />
	              </form>
	            </div>
			</div>
		</section>
	<iframe id="ifrm" name="ifrm" style="width:1px;height:1px;display:none;"></iframe>
	경로 : <%=application.getRealPath("/")%>
	</div>
</div>

<script type="text/javascript">
	//e : 이미지가 change된 이벤트
	function handleImgFileSelect(e){
		//이미지 수정 여부를 체크하는 체크박스에 체크 처리
		$("#cusImageCheck").prop("checked",true);
		
		//파일 객체에 이미지 파일을 담음
		var files = e.target.files;
		//멀티 파일이라면 배열로 처리
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){
			//파일 객체의 타입이 이미지인지 체킹
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			
			//파일을 sel_file변수에 넣음
			sel_file = f;
			//파일을 읽는 reader 객체 생성
			var reader = new FileReader();	//1)
			reader.onload = function(e){	//3)
				//e.target.result : 해당 파일의 명(경로 포함)
				//<img id='img' /> => <img id='img' src='base64형태의 이미지자체' />
				$("#img").attr("src", e.target.result);
				$("#cusImageLabel").text(e.target.result);
			}
			//f : filesArr파일 배열에 들었는 파일 객체 자체
			reader.readAsDataURL(f);	//2) f(이미지파일객체를) 다 읽으면 => onload 됐다라고 인식 -> 3)이동
		}); //end for
		
	}

	$(function() {
		//파일 다운로드
		$("#btnDown").on("click",function(){
			alert($("#img").attr("src"));
			
			var vSrc = $("#img").attr("src");
			
			//성공
			//location.href="/download?fileName=" + vSrc;
			
			$("#ifrm").attr("src","/download?fileName=" + vSrc);
			
// 			$.ajax({
// 				type:"GET",
// 				url:"/download?fileName=" + vSrc,
// 				data:{},
// 				success:function(data){
					
// 				}
// 			});
		});
		
		CKEDITOR.replace("cusDetail");
		//처음 상세보기 화면일 경우 입력란을 읽기전용으로 처리
		$(".form-control").prop("readonly",true);
		
		//수정버튼 클릭 시 처리
		$("#btnEdit").on("click",function(){
			//입력란의 읽기전용을 해제
			$(".form-control").prop("readonly",false);
			//상세정보 영역 처리
			$("#spanDetail1").css("display","block");	//ckeditor
			$("#spanDetail2").css("display","none");	//p태그
			//버튼 영역 처리
			$("#divFooter1").css("display","block");	//확인 취소
			$("#divFooter2").css("display","none");		//수정 삭제 목록
		});
		
		//이미지 미리보기 처리
		$("#cusImage").on("change",handleImgFileSelect);

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
			}
			if(CKEDITOR.instances.cusDetail.getData()==""){
				alert("상세정보를 입력해주세요.");
				CKEDITOR.instances.cusDetail.focus();
				return;
			}
// 			if($("#cusImage").val()==""){
// 				alert("사진을 선택하세요.");
// 				$("#cusImage").focus();
// 				return;
// 			}
			
			$("#cusFrm").submit();
		});//end btnSubmit
		
		//삭제 버튼 클릭 후 고객 정보 삭제 처리
		$("#btnDelete").on("click",function(){
			var input = confirm("삭제하시겠습니까?");
			
			if(input){//true
				$("#frmDelete").attr("action","/cus/delete").submit();
			}else{//false
				return;
			}
		});
	});
	
	
</script>








