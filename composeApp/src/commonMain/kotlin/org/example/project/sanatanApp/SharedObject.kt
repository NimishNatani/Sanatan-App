package org.example.project.sanatanApp

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SharedObject {
    val baseUrl = "http://10.14.3.138:8080/api"
}