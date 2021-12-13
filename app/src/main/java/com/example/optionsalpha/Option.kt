package com.example.optionsalpha

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties("selection")
class Option : Serializable{
    val contract : String = ""
}