package com.dominikwieczynski.issuetracker.model

data class Issue(var id : String = "", var name: String = "", var description: String = "", var label: String = "")
{
    enum class IssueLabel{
        BUG, ENHANCEMENT

    }
}