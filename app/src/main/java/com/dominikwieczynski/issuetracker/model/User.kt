package com.dominikwieczynski.issuetracker.model

data class User(var UID : String = "", var username: String = "", var projects: List<Project> = emptyList())
{

}
