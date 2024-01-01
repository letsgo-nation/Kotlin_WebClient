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
        val webClient: WebClient = WebClient
                .builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
        val response = webClient
                .get()
                .uri { it.path("/v2/search/blog")
                        .queryParam("query", blogDto.query)
                        .queryParam("sort", blogDto.sort)
                        .queryParam("page", blogDto.page)
                        .queryParam("size", blogDto.size)
                        .build() }
                .header("Authorization", "KakaoAK 93d2da970f3cfb2e2cab759c1554afe1")
                .retrieve()
                .bodyToMono<String>()
        val result = response.block()
        return result
    }
}
