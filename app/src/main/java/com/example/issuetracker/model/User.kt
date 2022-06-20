package com.example.issuetracker.model

import com.example.issuetracker.model.ProjectPublic

data class User(var username: String = "", var projects: List<ProjectPublic> = emptyList())
{

}
