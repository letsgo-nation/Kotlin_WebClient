package com.won.study.blog.service

import com.won.study.blog.dto.BlogDto
import com.won.study.blog.entity.Wordcount
import com.won.study.blog.repository.Wordrepositories
import com.won.study.core.exception.InvalidInputException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService(
        val wordRepository: Wordrepositories
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

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
                .header("Authorization", "KakaoAK $restApiKey")
                .retrieve()
                .bodyToMono<String>()

        val result = response.block()

        val lowQuery: String = blogDto.query.lowercase() //  dto 값을 소문자로 처리.
        val word: Wordcount = wordRepository.findById(lowQuery).orElse(Wordcount(lowQuery))
        // 레포지토리에서 값 겁색. 값이 없다면 새로운 Wordcount를 만들어서 word에 전달
        word.cnt++

        wordRepository.save(word)

        return result
    }

    fun searchWordRank(): List<Wordcount> = wordRepository.findTop10ByOrderByCntDesc()

}
