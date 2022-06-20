package com.example.issuetracker.ui.screens.project_list

import com.example.issuetracker.model.ProjectPublic

data class ProjectListUiState(var projects: MutableList<ProjectPublic> = mutableListOf(), var dialogOpen : Boolean = false)
{



}
