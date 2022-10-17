<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Trang chủ</title>

</head>

<body>

	<div class="container">

		<!-- Heading Row -->
		<div class="row align-items-center my-5">
			<div class="col-lg-7">
				<img class="img-fluid rounded mb-4 mb-lg-0"
					src="${hotNews.thumbnail}" alt="">
			</div>
			<!-- /.col-lg-8 -->
			<div class="col-lg-5">
				<h1 class="font-weight-light">${hotNews.title }</h1>
				<p>${hotNews.shortDescription}</p>
				<a class="btn btn-primary" href="<c:url value='/news?id=${hotNews.id}'/>">Call to Action!</a>
			</div>
			<!-- /.col-md-4 -->
		</div>
		<!-- /.row -->

		<!-- Call to Action Well -->
		
		<div class="card text-white bg-secondary my-5 py-4 text-center">
			<div class="card-body">
				<p class="text-white m-0">This call to action card is a great
					place to showcase some important information or display a clever
					tagline!</p>
			</div>
		</div>

		<!-- Content Row -->
		<div class="row">
			<c:forEach var="item" items="${model.listResult}" >
				<div class="col-md-4 mb-5">
					<div class="card h-100">
					<img class="card-img-top" src="${item.thumbnail}" alt="">
						<div class="card-body">
							<h2 class="card-title">${item.title}</h2>
							<p class="card-text">${item.shortDescription }</p>
						</div>
						<div class="card-footer">
							<a href="<c:url value='/news?id=${item.id}'/>" class="btn btn-primary btn-sm">More Info</a>
						</div>
					</div>
			</div>
			</c:forEach>
			<!-- /.col-md-4 -->
			<!-- 
				<div class="col-md-4 mb-5">
				<div class="card h-100">
					<div class="card-body">
						<h2 class="card-title">Card Two</h2>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Quod tenetur ex natus at dolorem enim! Nesciunt
							pariatur voluptatem sunt quam eaque, vel, non in id dolore
							voluptates quos eligendi labore.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary btn-sm">More Info</a>
					</div>
				</div>
			</div>
			 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->


</body>

</html>