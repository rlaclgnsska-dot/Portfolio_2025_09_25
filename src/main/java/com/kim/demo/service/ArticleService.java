package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.ArticleDao;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Article;
import com.kim.demo.vo.Board;
import com.kim.demo.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService (ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Article writeArticle(String title, String body, int memberId, int boardId) {
		return articleDao.writeArticle(title, body, memberId, boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}

	public List<Article> getArticles(int boardId, int limitStart, int itemsInApage, String searchType, String searchKeyword) {
		return articleDao.getArticles(boardId, limitStart, itemsInApage,  searchType, searchKeyword);
	}
	
	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public int getArticlesCnt(int boardId, String searchType, String searchKeyword) {
		return articleDao.getArticlesCnt(boardId, searchType, searchKeyword);
	}

	public void increaseHitCount(int id) {
		articleDao.increaseHitCount(id);
	}

}
