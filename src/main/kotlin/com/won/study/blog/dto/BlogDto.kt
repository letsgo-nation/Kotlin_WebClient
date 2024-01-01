package com.won.study.blog.dto

import com.won.study.core.annotation.ValidEnum
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BlogDto(
        val query: String,
        val sort: String,
        val page: Int,
        val size: Int
)