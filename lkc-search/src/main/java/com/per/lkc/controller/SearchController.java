package com.per.lkc.controller;

import com.per.lkc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/1/15.
 *
 * @author zhangjunbo.
 */
@RequestMapping("/search")
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

}
