package com.dominikwieczynski.issuetracker.ui.screens.project_list

import com.dominikwieczynski.issuetracker.model.ProjectPublic

data class ProjectListUiState(var projects: MutableList<ProjectPublic> = mutableListOf(), var dialogOpen : Boolean = false
, var listFetched: Boolean = false)
{



}
