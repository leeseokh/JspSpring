<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.form-control2 {
  display: block;
  width: 100%;
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
<script src="/resources/ckeditor/ckeditor.js"></script>
<div id="body">
	
	<!-- Content Wrapper. Contains page content -->
  	<div class="content-wrapper">
		
		<!-- Content Header (Page header) -->
	    <div class="content-header">
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1 class="m-0">자유글 상세보기</h1>
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
	                <h3 class="card-title">자유게시글 상세보기 합니다.</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form action="/freeboard/update" method="post" id="cusFrm" 
	                enctype="multipart/form-data">
	                <input type="text" name="num" value="${param.rnum}" />
	                <div class="card-body">
	                  <div class="form-group">
	                    <label for="cusNm">고객 이름</label>
	                    <input type="text" class="form-control2" name="writer" id="writer" placeholder="이름을 입력하세요"
	                    value="${freeboardVo.writer}" readOnly="true">
	                  </div>
	                  <div class="form-group">
	                    <label for="cusPwd">글제목</label>
	                    <input type="text" class="form-control" name="title" id="title" placeholder="제목 입력하세요"
	                    value="${freeboardVo.title}"  readOnly="true">
	                  </div>
	                  <div class="form-group">
	                    <label for="addr1">글내용</label>
	                    <textarea class="form-control" name="content" id="content" placeholder="내용을 입력하세요"
	                    	cols="50" rows="10" style="display:none;">${freeboardVo.content}</textarea>
	                    <span id="spanContent">
	                    	${freeboardVo.content}
	                    </span>
	                  </div>	                  
	                  <div class="card">
	                    <label for="cusImage">첨부파일</label>
	                    <div class="card-body">
	                    	<c:if test="${freeboardVo.attachPath!=null}">
	                    		<a class="btn btn-app" onclick="fn_down('${freeboardVo.attachPath}')">
				                  <i class="fas fa-save"></i> Save
				                </a>
	                    	</c:if>
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
	                <div class="card-footer text-right" id="divFooter1" style="display:none;">
	                  <button type="button" class="btn btn-primary" id="btnSubmit">확인</button>
	                  <button type="button" class="btn btn-default" onclick="location.href='/freeboard/detail?rnum=${param.rnum}'">취소</button>
	                </div>
	                <div class="card-footer text-right" id="divFooter2">
	                  <button type="button" class="btn btn-primary" id="btnEdit"
	                  <c:if test="${sessionScope.empNum!=freeboardVo.writer}">disabled="false"</c:if>
	                  >수정</button>
	                  <button type="button" class="btn btn-default" id="btnDelete"
	                  <c:if test="${sessionScope.empNum!=freeboardVo.writer}">disabled="false"</c:if>
	                  >삭제</button>
	                  <button type="button" class="btn btn-primary" id="btnList" onclick="location.href='/freeboard/list'">목록</button>
	                </div>
	              </form>
	              <form id="reFrm" name="reFrm">
						<div class="card card-warning">
			              <div class="card-header">
			                <h3 class="card-title">댓글 목록</h3>
			              </div>
			              <div class="card-body">
			                <div class="row">
			                  <div class="col-2">
			                  	<input type="hidden" name="writer" value="${sessionScope.empNum}" />
			                  	<input type="hidden" name="pNum" value="${param.rnum}" />
			                    <input type="text" class="form-control" placeholder=""
			                    value="${sessionScope.nm}" readonly="readonly">
			                  </div>
			                  <div class="col-9">
			                    <input type="text" name="content" id="reContent" class="form-control" placeholder="댓글을 입력해주세요">
			                  </div>
			                  <button type="button" id="btnReSubmit" name="btnReSubmit">입력</button>
			                </div>
			              </div>
						</div>
			            <!-- /.card card-warning 끝 -->
					</form>
					<div class="card-body p-0">
		                <table class="table table-sm">
		                  <thead>
		                    <tr>
		                      <th style="width: 10px">#</th>
		                      <th>작성자</th>
		                      <th>댓글내용</th>
		                    </tr>
		                  </thead>
		                  <tbody id="tbody">
		                 <c:forEach var="reRow" items="${reList}" varStatus="stat">
		                    <tr>
		                      <td>${stat.count}</td>
		                      <td>${reRow.writer}</td>
		                      <td>${reRow.content}</td>
		                    </tr>
		                 </c:forEach>
		                  </tbody>
		                </table>
		              </div>
	            </div>
			</div>
		</section>
		<iframe id="ifrm" name="ifrm" style="width:1px;height:1px;display:none;"></iframe>
	</div>
</div>

<script type="text/javascript">
	//파일 다운로드(path는 /resource부터 시작하는 경로여야 함)
	function fn_down(path){
		var vIfrm = document.getElementById("ifrm");
		vIfrm.src = "/download?fileName=/resources" + path;
	}

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
		//댓글 달기 처리
		$("#btnReSubmit").on("click",function(){
			const writer = $("input[name=writer]").val();
			const pNum = $("input[name=pNum]").val();
			const content = $("#reContent").val();
			
			const data = {"writer":writer,"pNum":pNum,"content":content};
			console.log("data.writer : " + data.writer + ", data.pNum : " + data.pNum +
					", data.content : " + data.content);
			$.ajax({
				type:"post"
				,url:"/freeboard/reInsertPost"
				,data:JSON.stringify(data)	//보낼때(request)
				,dataType:"json"	//받을 때(response)
				,contentType:"application/json"
				,cache:false
				,success:function(data){
					console.log(data.num + ", " + data.writer + ", " + data.content);
					
					var lastNum = $("#tbody tr:last-of-type td:eq(0)").text();
					
					$("#tbody").append("<tr><td>"+(Number(lastNum)+1)+"</td><td>"+data.writer+
							"</td><td>"+data.content+"</td></tr>");
				}
			});
		});
		
		//글수정 버튼 클릭 시
		$("#btnEdit").on("click",function(){
			//ckeditor 활성화
			CKEDITOR.replace("content");
			//글내용 span가리기
			$("#spanContent").css("display","none");
			//고객 이름, 글제목 활성화
			$(".form-control").removeAttr("readonly");
			
			//divFooter1, 2처리
			$("#divFooter1").css("display","block");//확인/취소
			$("#divFooter2").css("display","none");//수정/삭제/목록
		});
		
		//파일 경로 보기
		$("#attach").on("change",handleImgFileSelect);

		$("#btnSubmit").click(function() {
			
			if($("#writer").val()==""){
				alert("이름을 입력하세요.");
				$("#writer").focus();
				return;
			}
			if($("#title").val()==""){
				alert("글 제목을 입력하세요.");
				$("#title").focus();
				return;
			}
// 			if($("#cusPwd").val()==""){
// 				alert("비밀번호를 입력하세요.");
// 				$("#cusPwd").focus();
// 				return;
// 			}
// 			if($("#zipcode").val()==""){
// 				alert("우편번호를 입력하세요.");
// 				$("#zipcode").focus();
// 				return;
// 			}
// 			if($("#addr1").val()==""){
// 				alert("주소를 입력하세요.");
// 				$("#addr1").focus();
// 				return;
// 			}
// 			if($("#addr2").val()==""){
// 				alert("상세 주소를 입력하세요.");
// 				$("#addr2").focus();
// 				return;
// 			}
// 			if($("#pne").val()==""){
// 				alert("연락처를 입력하세요.");
// 				$("#pne").focus();
// 				return;
// 			}
// 			var regExp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
// 			if(!regExp.test($("#pne").val())){
// 				alert("연락처는 010-0000-0000 형식으로 입력해주세요.");
// 				$("#pne").focus();
// 				return;
// 			}
// 			if(CKEDITOR.instances.cusDetail.getData()==""){
// 				alert("상세정보를 입력해주세요.");
// 				CKEDITOR.instances.cusDetail.focus();
// 				return;
// 			}
// 			if($("#cusImage").val()==""){
// 				alert("사진을 선택하세요.");
// 				$("#cusImage").focus();
// 				return;
// 			}
			
			$("#cusFrm").submit();
		});
	});
	
	
</script>