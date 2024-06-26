package com.won.study.blog.controller

import com.won.study.blog.dto.BlogDto
import com.won.study.blog.entity.Wordcount
import com.won.study.blog.service.BlogService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/blog")
@RestController
class BlogController(
        val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto) : String? {
        val result = blogService.searchKakao(blogDto)
        return result
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<Wordcount> = blogService.searchWordRank()
}