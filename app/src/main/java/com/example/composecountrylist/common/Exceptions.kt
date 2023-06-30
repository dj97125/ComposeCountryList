package com.example.composecountrylist.common

class NullResponseException(
    message: String = "Response is null"
) : Exception(message)

class FailedResponseException(
    message: String = "Error: failure in the response"
) : Exception(message)

class FailedInternetException(
    message: String = "Error: failure in the internet conection"
) : Exception(message)

class FailedCache(
    message: String = "Error: failure in the cache"
) : Exception(message)


