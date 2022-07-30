package com.dominikwieczynski.issuetracker.ui.screens.project_list

import com.dominikwieczynski.issuetracker.model.Project

data class ProjectListUiState(var projects: MutableList<Project> = mutableListOf(),
                              var listFetched: Boolean = false)
{



}
