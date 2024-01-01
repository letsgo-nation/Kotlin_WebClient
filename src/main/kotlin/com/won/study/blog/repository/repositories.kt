package com.won.study.blog.repository

import com.won.study.blog.entity.Wordcount
import org.springframework.data.repository.CrudRepository

interface Wordrepositories : CrudRepository<Wordcount, String>