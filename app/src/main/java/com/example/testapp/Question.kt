package com.example.testapp

data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val firstChoice: String,
    val secondChoice: String,
    val thirdChoice: String,
    val fourthChoice: String,
    val correctChoice: Int,
)