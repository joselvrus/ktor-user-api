package com.joselvrus.data.dto.validation

fun List<Pair<String, String?>>.isTextEmpty() = mapNotNull { (name, value) ->
    if (value.isNullOrBlank()) {
        "$name field is mandatory"
    } else {
        null
    }
}
