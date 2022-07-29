package com.example.aoppart3chapter06.chatdetail

data class ChatItem(
    val senderId: String,
    val message: String,

) {
    constructor(): this("", "")
}
