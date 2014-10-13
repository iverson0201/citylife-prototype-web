package com.citylife.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.citylife.backend.domain.news.News;
import com.citylife.backend.service.SearchSevice;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月8日 下午3:53:57
 * 
 */
@Controller
@RequestMapping("/")
public class SearchController {

	@Autowired
	private SearchSevice searchService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public ModelAndView search(@RequestParam("q") String query) {
		List<News> articles = searchService.searchsNews(query);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.addObject("articles", articles);
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/create")
	public ModelAndView createInitialData() {
		searchService.builderSearchIndex();
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "文章索引已创建成功!");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("about");
		return mv;
	}
	
}
