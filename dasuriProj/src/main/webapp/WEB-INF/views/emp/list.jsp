<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function(){
	$("#makePdf").on("click",function(){
		$.ajax({
			url:'/emp/pdfDown',
			data:'',
			success:function(data){
				console.log("data : " + data);
				if(data=="ok"){
					window.open("/resources/pdf/empList.pdf","owin","width=700,height=700");
				}else{
					alert("pdf생성에 실패했습니다.");
				}
			}
		});
	});
});
</script>
<div class="card" style="margin-top:5%;">
              <div class="card-header">
                <h3 class="card-title">다수리 직원 목록</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
	              <div class="card-body" style="text-align:right;padding:0px;">
<!-- 	              	<a class="btn btn-app" onclick="javascript:location.href='/emp/pdfDown'"> -->
	              	<a class="btn btn-app" id="makePdf">
	                  <i class="fas fa-save"></i> Pdf다운로드
	                </a>
	              </div>
                <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4">
                <div class="row">
                <div class="col-sm-12 col-md-6"></div>
                <div class="col-sm-12 col-md-6"></div>
                </div>
                <div class="row">
                <div class="col-sm-12">
                <table id="example2" class="table table-bordered table-hover dataTable dtr-inline" role="grid" aria-describedby="example2_info">
                  <thead>
                  <tr role="row">
                  <th class="sorting sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">번호</th>
                  <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">이름</th>
                  <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">주소</th>
                  <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending">연락처</th>
                  <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending">월급</th>
                  </tr>
                  </thead>
                  <tbody>
<c:forEach var="empVo" items="${list}" varStatus="status">
                  <tr class="odd" <c:if test="${sessionScope.empNum!=null}">onclick="javascript:location.href='/emp/detail/${empVo.empNum}'"</c:if>
                  	style="cursor:pointer;" title="${empVo.nm}" alt="${empVo.nm}">
                    <td class="dtr-control sorting_1" tabindex="0">${empVo.rnum}</td>
                    <td>${empVo.nm}</td>
                    <td>${empVo.addr}</td>
                    <td>${empVo.pne}</td>
                    <td>${empVo.sal}</td>
                  </tr>
</c:forEach>
                  </tbody>
                </table></div></div><div class="row"><div class="col-sm-12 col-md-5"><div class="dataTables_info" id="example2_info" role="status" aria-live="polite">
                Showing ${entriesMap.startNum} to ${entriesMap.endNum} of ${entriesMap.totalNum} entries
                </div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                <ul class="pagination">
                <!-- Previous 시작 -->
                <c:if test="${paging.startPage>5}">
                	<li class="paginate_button page-item previous" id="example2_previous">
                </c:if>
                <c:if test="${paging.startPage<=5}">
                	<li class="paginate_button page-item previous disabled" id="example2_previous">
                </c:if>
                		<a href="/emp/list?currentPage=${paging.startPage-5}" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                	</li>
                <!-- Previous 시작 -->
                <!-- 페이지 번호 시작 -->
                <c:forEach var="pNo" begin="${paging.startPage}" end="${paging.endPage}" varStatus="stat">
                	<c:if test="${param.currentPage==pNo}">
                		<li class="paginate_button page-item active">
                	</c:if>
                	<c:if test="${param.currentPage!=pNo}">
                		<li class="paginate_button page-item">
                	</c:if>
                		<a href="/emp/list?currentPage=${pNo}" aria-controls="example2" data-dt-idx="${pNo}" tabindex="0" class="page-link">${pNo}</a>
                	</li>
                </c:forEach>
                <!-- 페이지 번호 끝 -->
                <!-- Next 시작 -->
                <c:if test="${paging.endPage<paging.totalPages}">
                	<li class="paginate_button page-item next" id="example2_next">
                </c:if>
                <c:if test="${paging.endPage>=paging.totalPages}">
                	<li class="paginate_button page-item next disabled" id="example2_next">
                </c:if>
                		<a href="/emp/list?currentPage=${paging.startPage+5}" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
                	</li>
                <!-- Next 끝 -->
                </ul>
                </div>
                </div>
                </div>
                </div>
              </div>
              <!-- /.card-body -->
              <div style="width:100%;">
              	<div style="float:left;width:70%;"></div>
              	<div style="float:right;width:100px;">
              		<button class="btn btn-block btn-primary" type="button"
              		 onclick="javascript:location.href='/emp/insert'">직원등록</button>
              	</div>
              </div>
            </div>
           
            
            
            
            
            
            
            
            
            