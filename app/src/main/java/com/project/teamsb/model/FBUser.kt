package com.project.teamsb.model

data class FBUser(
    val id: String,
    val email: String,
    val nickname: String
) {
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "id" to this.id,
            "email" to this.email,
            "nickname" to this.nickname
        )
    }

}

