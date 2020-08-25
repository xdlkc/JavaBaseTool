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
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class IndexTest {
    public static final String INDEX_FP = "/Users/ZhangJunbo/Code/JavaBaseTool/lkc-search/src/main/resources/index";


    @Test
    public void luceneCreateIndex() throws IOException {
        String docFp = "/Users/ZhangJunbo/Code/JavaBaseTool/lkc-search/src/main/resources/doc";
        //指定索引存放的位置
        //E:\Lucene_index
        Directory directory = FSDirectory.open(Paths.get(new File(INDEX_FP).getPath()));
        System.out.println("pathname" + Paths.get(new File(INDEX_FP).getPath()));
        //创建一个分词器
        //        StandardAnalyzer analyzer = new StandardAnalyzer();
        //        CJKAnalyzer cjkAnalyzer = new CJKAnalyzer();
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
        //创建indexwriterConfig(参数分词器)
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(smartChineseAnalyzer);
        //创建indexwrite 对象(文件对象，索引配置对象)
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //原始文件
        File file = new File(docFp);

        for (File f : file.listFiles()) {
            //文件名
            String fileName = f.getName();
            //文件内容
            String fileContent = FileUtils.readFileToString(f, "UTF8");
            System.out.println(fileContent);
            //文件路径
            String path = f.getPath();
            //文件大小
            long fileSize = FileUtils.sizeOf(f);

            //创建文件域名
            //域的名称 域的内容 是否存储
            Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
            Field fileContentField = new TextField("fileContent", fileContent, Field.Store.YES);
            Field filePathField = new TextField("filePath", path, Field.Store.YES);
            Field fileSizeField = new TextField("fileSize", fileSize + "", Field.Store.YES);

            //创建Document 对象
            Document indexableFields = new Document();
            indexableFields.add(fileNameField);
            indexableFields.add(fileContentField);
            indexableFields.add(filePathField);
            indexableFields.add(fileSizeField);
            //创建索引，并写入索引库
            indexWriter.addDocument(indexableFields);

        }

        //关闭indexWriter
        indexWriter.close();
    }

    @Test
    public void searchIndex() throws IOException {
        //指定索引库存放路径
        //E:\Lucene_index
        Directory directory = FSDirectory.open(Paths.get(new File(INDEX_FP).getPath()));
        //创建indexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建indexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询
        Query query = new TermQuery(new Term("fileContent", "可爱"));
        //执行查询
        //参数一  查询对象    参数二  查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("查询结果的总数" + topDocs.totalHits);
        //遍历查询结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
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