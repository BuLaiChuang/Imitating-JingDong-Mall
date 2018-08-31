package com.taotao.test;

/*
 * solr的简单测试
 */
public class MyTest {

    //往solr里边添加索引可和文档库，，添加
    @Test
    public void demo1() throws Exception{
		/*
		 * 要用java代码连接solr的索引库，首先要有solrJ.jar包
		 * 服务要调用solrJ
		 */
        //1、第一步本来是将数据库的数据导入，这里测试先不用导入
        //先随便导入几个,ctrl+t可以查看这个类可以实现的呢些类
        //这里的bastURl==》
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.154:8080/solr");
        //添加了一个文档库对象--存域名+域值的
        SolrInputDocument doc = new SolrInputDocument();

        //添加一个域，但是他必须是在solrhome的schema.xml文件里边定义过
        doc.addField("id", "001");
        doc.addField("item_title", "蜡烛");
        doc.addField("item_price", "144");

        solrServer.add(doc);

        solrServer.commit();
    }
    //要修改的话直接写要修改的名称

    //删除
    @Test
    public void demo2() throws Exception{
		/*
		 * 要用java代码连接solr的索引库，首先要有solrJ.jar包
		 * 服务要调用solrJ
		 */
        //1、第一步本来是将数据库的数据导入，这里测试先不用导入
        //先随便导入几个,ctrl+t可以查看这个类可以实现的呢些类
        //这里的bastURl==》
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.154:8080/solr");
        //添加了一个文档库对象--存域名+域值的
        SolrInputDocument doc = new SolrInputDocument();
        solrServer.deleteById("001");

        solrServer.commit();
    }
}
