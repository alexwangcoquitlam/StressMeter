package com.example.stressmeter

class Record(timestamp: String, score: Int) : java.io.Serializable{
    private var score = 0
    private var timestamp: String

    init {
        this.score = score
        this.timestamp = timestamp
    }

    fun getRecord(): Int {
        return score
    }

    fun setRecord(score: Int) {
        this.score = score
    }

    fun getTimestamp(): String {
        return timestamp
    }

    fun setTimestamp(timestamp: String) {
        this.timestamp = timestamp
    }
}