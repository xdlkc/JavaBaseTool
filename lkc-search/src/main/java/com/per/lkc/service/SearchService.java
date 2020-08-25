package com.per.lkc.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.per.lkc.vo.SearchVO;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/1/15.
 *
 * @author zhangjunbo.
 */
@Service
public class SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void search(SearchVO searchVO) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        Set<String> authSet = Collections.emptySet();
        sourceBuilder.from(1);
        sourceBuilder.size(2);
        sourceBuilder.query(getSearchQuery(searchVO, authSet));

        SearchRequest request = new SearchRequest();
        request.indices("doc");
        request.types("doc");
        request.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QueryBuilder getSearchQuery(SearchVO searchVO, Set<String> authSet) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchVO.getQ(), getMatchQueryStrings()).operator(Operator.OR));
        return boolQueryBuilder;
    }

    /**
     * 获取检索字段
     *
     * @return
     */
    private String[] getMatchQueryStrings() {
        return new String[]{"content", "title"};
    }

    /**
     * 获取高亮字段
     *
     * @return
     */
    private Field[] getHighlightFields() {
        return new Field[]{new Field("content"), new Field("title")};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入随机数：");
        String paramString = sc.nextLine();
        StringBuffer localStringBuffer = new StringBuffer();
        int j = paramString.length();
        int i = 0;
        while (i < j) {
            localStringBuffer.append(paramString.charAt(i) ^ 0x9);
            i += 1;
        }
        String pass = localStringBuffer.toString();
        System.out.println("密码是: ");
        System.out.println(pass);
    }
}
