package demo.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.rest.model.Article;

@Controller
public class ArticleController {

	@RequestMapping(value = "/articles/{articleId}", produces = "text/html")
	public String view(@PathVariable Long articleId,
			HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("article", getArticle(articleId, request));
		return "articles/view";
	}

	@RequestMapping(value = "/articles/{articleId}", produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public Article view(@PathVariable Long articleId, HttpServletRequest request) {
		return getArticle(articleId, request);
	}

	private Article getArticle(Long id, HttpServletRequest request) {
		String linkHref = ServletUriComponentsBuilder.fromRequest(request)
				.build().encode().toString(); // demo for uribuilder
		
		Article article = new Article();
		article.setArticleId(id);
		article.setTitle("Test Article");

		Link link = new Link(linkHref);
		article.add(link);

		return article;
	}
}
