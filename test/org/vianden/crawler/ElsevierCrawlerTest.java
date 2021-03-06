package org.vianden.crawler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.vianden.model.Publisher;
import org.vianden.model.Paper;

public class ElsevierCrawlerTest {
	
	private static AbstractCrawler crawler = null;
	private static Paper paper = null;
	private static String tAbstract = null;
	private static String tKeywords = null;
	private static String tPdfurl = null;
	private static String tPages = null;
	private static String tAuthors = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//read test case
		Properties pp = new Properties();
		pp.load(IEEECrawlerTest.class.getClassLoader().getResourceAsStream("org/vianden/crawler/CrawlTestCase.properties"));
		tAbstract=pp.getProperty("abstractElsevier");
		tKeywords=pp.getProperty("keywordsElsevier");
		tPdfurl=pp.getProperty("pdfurlElsevier");
		tPages=pp.getProperty("pagesElsevier");
		tAuthors=pp.getProperty("authorsElsevier");
		
		//construct paper
		String urlElsevier = pp.getProperty("urlElsevier");
		paper = new Paper();
		paper.setDoi(urlElsevier);
		paper.setPublisher(Publisher.ELSEVIER);
		
		//initialize crawler
		crawler = new ElsevierCrawler(paper);
	}

	@Test
	public void testCrawl() throws IOException {
		crawler.commonCrawl();
		crawler.crawl();
		crawler.finishCrawl();
		
		assertEquals(tAbstract, paper.getAbstract());
		assertEquals(tKeywords, paper.getKeywords());
		assertEquals(tPdfurl, paper.getPdfUrl());
		assertEquals(tPages, paper.getPages());
		assertEquals(tAuthors, paper.getAuthors().toString());
	}

}