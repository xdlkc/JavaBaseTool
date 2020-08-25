package com.per.lkc.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class IndexService {

    //创建索引
    public void luceneCreateIndex() throws Exception {
    }

    public void searchIndex(String fp) throws IOException {
        //指定索引库存放路径
        //E:\Lucene_index
        Directory directory = FSDirectory.open(Paths.get(new File(fp).getPath()));
        //创建indexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建indexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询
        Query query = new TermQuery(new Term("fileContent", "可爱"));
        //执行查询
        //参数一  查询对象    参数二  查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("查询结果的总数"+topDocs.totalHits);
        //遍历查询结果
        for (ScoreDoc scoreDoc: topDocs.scoreDocs){
            //scoreDoc.doc 属性就是doucumnet对象的id
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc.getField("fileName"));
            System.out.println(doc.getField("fileContent"));
            System.out.println(doc.getField("filePath"));
            System.out.println(doc.getField("fileSize"));
        }
        indexReader.close();
    }
}
