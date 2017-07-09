package fgh.weixin.test;

import java.util.ArrayList;
import java.util.List;

import fgh.weixin.message.qy.NewsMessage;
import fgh.weixin.message.qy.QyArticle;
import fgh.weixin.message.qy.QyNews;
import fgh.weixin.util.QyWeixinApiUtil;
import fgh.weixin.util.WeixinConstant;

public class Test {

//	@org.junit.Test
	public void testSendNewsMsg() {
		NewsMessage msg = new NewsMessage();
		msg.setAgentid(43);
		msg.setMsgtype(WeixinConstant.QY_MSG_TYPE_NEWS);// 图文消息
		msg.setSafe("0");// 固定0
		msg.setTouser("2615");// 要发送给的用户 多个用户用|分隔

		QyArticle article = new QyArticle();
		article.setDescription("您收到一个新的商机,请尽快处理");
		article.setTitle("您收到一个新的商机");
		article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8185896a64308655&redirect_uri=http://wx.huaxiapawn.com/hx-sales-web/clue/query&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");

		List<QyArticle> articles = new ArrayList<QyArticle>();
		articles.add(article);
		QyNews qyNews = new QyNews();
		qyNews.setArticles(articles);
		msg.setNews(qyNews);

		// News news = News();
		QyWeixinApiUtil.sendMsg2Agent(msg);// 调用发送方法
	}
}
