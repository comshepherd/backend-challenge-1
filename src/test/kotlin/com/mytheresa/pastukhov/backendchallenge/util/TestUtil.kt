package com.mytheresa.pastukhov.backendchallenge.util

class TestUtil {

    companion object {
        fun readFileUsingGetResource(fileName: String) = this::class.java.getResource(fileName).readText(Charsets.UTF_8)
    }

}