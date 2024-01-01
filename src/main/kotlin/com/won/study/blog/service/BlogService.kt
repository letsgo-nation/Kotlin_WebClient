package com.won.study.blog.service

import com.won.study.blog.dto.BlogDto
import com.won.study.core.exception.InvalidInputException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService {
    fun searchKakao(blogDto: BlogDto): String? {
        return "SearchKakao"
    }
}