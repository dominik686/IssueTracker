package com.dominikwieczynski.issuetracker.model

data class User(var username: String = "", var projects: List<Project> = emptyList())
{

}
