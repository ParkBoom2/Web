<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet" href="../../css/bootstrap.css">
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<!-- 헤드 레이아웃 include -->
	

	<!-- 세션에 정보가 없을 경우에만 로그인 창 보여주기 -->
	<%
		if (session.getAttribute("userID") == null) {
	%>

	<div class="container">
		<div class="row">
			<!-- 입력폼 그리드 -->
			<div class="col-lg-4">
				<form action="../togather/login.do?m=login" method="post">
					<!-- ID입력 -->
					<div class="input-group mt-3 mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">ID</span>
						</div>
						<input type="text" name="phone" class="form-control"
							placeholder="Input ID" aria-label="Input ID"
							aria-describedby="basic-addon1" required>
					</div>
					<!-- PW입력 -->
					<div class="input-group mb-2">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">PW</span>
						</div>
						<input type="password" name="pwd" class="form-control"
							placeholder="Input Password" aria-label="Input Password"
							aria-describedby="basic-addon1" required>
					</div>
					<!-- 자동 로그인 -->
					<div class="custom-control custom-checkbox mb-1">
						<input type="checkbox" class="custom-control-input"
							name="isAutoLogin" value="true" id="customCheck1"> <label
							class="custom-control-label" for="customCheck1">로그인 유지</label>
					</div>
					<!-- 로그인 버튼 -->
					<button type="submit" class="btn btn-dark btn-sm btn-block">
						로그인 하기</button>
				</form>

				<!-- 회원가입 버튼 -->
				<button type="button" class="btn btn-dark btn-sm btn-block my-1"
					onclick="location.href='/register'">회원가입 하기</button>

				<%
					}
				%>

				<!-- 회원가입 성공, 로그인 실패 등 메세지가 있으면 경고창 출력 -->
				<%
					Object obj = session.getAttribute("loginMsg");
					if (obj != null) {
						String msg = (String) obj;
				%>
				<div class="alert alert-danger text-center" role="alert"><%=msg%></div>
				<%
					// 출력한 메세지는 지워줌
						session.removeAttribute("loginMsg");
					}
				%>
			</div>

			<!-- 그림 넣을 그리드 -->
			<div class="col-lg-8"></div>
		</div>
	</div>


</body>
</html>
</body>
</html>