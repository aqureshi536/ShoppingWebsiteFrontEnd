
<div id="divCarousel" >
					<div id="productCarousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="productCarousel" data-slide-to="0" class="active"></li>
							<li data-target="productCarousel" data-slide-to="1"></li>
							<li data-target="productCarousel" data-slide-to="2"></li>
							<li data-target="productCarousel" data-slide-to="3"></li>
							<li data-target="productCarousel" data-slide-to="4"></li>
						</ol>

						<div class="carousel-inner" role="listbox">
							<div class="item active	"><img src="resources/images/bigsale1.jpg" class="img-responsive" alt="" style="height:500px;width:1300px;"></div>
							<div class="item"><img src="resources/images/curtainhome1.jpg" alt="" class="img-responsive" style="height:500px;width:1300px;"></div>
							<div class="item"><img src="resources/images/bed3.jpg" alt="" class="img-responsive" style="height:500px;width:1300px;"></div>
							<div class="item"><img src="resources/images/chair4.jpg" alt=""  class="img-responsive" style="height:500px;width:1300px;"></div>
							<div class="item"><img src="resources/images/cab5.jpg" alt="" class="img-responsive" style="height:500px;width:1300px;"></div>
						</div>
						<a class="left carousel-control" href="#productCarousel" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#productCarousel" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div><!--div carousel  -->
				</div>
				<div  ng-controller="productController">
				<div  class="productMainDiv">
					<div class="row">
					<h2 id="productMainTitle">Top Beds</h2>
						<div ng-repeat="bed in beds | filter : searchProduct.name" >
							<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" id="productDiv" >
								<img ng-src="/resources/images/{{bed.image}}" class="img-rounded img-thumbnail" alt="" style="height:120px;width:170px;"/>
								<h2 id="pricePara"><b>{{bed.price}}</b></p>
								<h4>{{bed.name}}</h4>
								<h5>{{bed.brand}}</h5>
							</div>
						</div>
					</div>
				</div><!--div bed controller  -->
				<div class="productMainDiv" >
					<div class="row">
					<h2 style="border-left:6px #3bb300 solid" id="productMainTitle">Top Sofas</h2>
						<div ng-repeat="sofa in sofas" >
							<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12" id="productDiv" >
								<img ng-src="resources/images/{{sofa.image}}" class="img-rounded img-thumbnail" alt="" style="height:120px;width:170px;"/>
								<p id="pricePara"><b>{{sofa.price}}</b></p>
								<h4>{{sofa.name}}</h4>
								<h5>{{sofa.brand}}</h5>
							</div>
						</div>
					</div>
				</div><!--div sofa controller  -->
				</div>
			
	