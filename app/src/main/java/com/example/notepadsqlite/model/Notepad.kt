package com.example.notepadsqlite.model

import java.io.Serializable

class Notepad : Serializable {
    var title: String = ""
    var description: String = ""
    var id: Int? = 0

    constructor(title: String, description: String, id: Int?) {
        this.title = title
        this.description = description
        this.id = id
    }

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }

}
