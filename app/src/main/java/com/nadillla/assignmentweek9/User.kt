package com.nadillla.assignmentweek9

import java.io.Serializable

data class User(val name: String, var email: String,var hp :String,var address :String,var job :String, var key: String? = null): Serializable {
    constructor() : this("", "", "", "", "","")
}