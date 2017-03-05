<#escape x as x?html>
					<aside class="col-md-4">
						<div class="widget widget-recent-posts">
							<h3 class="widget-title">最近发布</h3>
							<ul>
<#list articlesForMenu as article>
								<li><a href="article/${article.uuid}">${article.title}</a></li>
</#list>
							</ul>
						</div>

						<div class="widget widget-archives">
							<h3 class="widget-title">归档</h3>
							<ul>
<#list archivesForMenu as archive>
								<li><a href="#">${archive.monthF}</a></li>
</#list>
							</ul>
						</div>

						<div class="widget widget-category">
							<h3 class="widget-title">分类</h3>
							<ul>
<#list categoriesForMenu as category>
								<li><a href="#">${category.categoryName}</a></li>
</#list>
							</ul>
						</div>
					</aside>
</#escape>