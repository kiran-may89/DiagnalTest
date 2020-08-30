package com.diagnal.test.models

sealed class NetWorkState
class  Loading:NetWorkState()
class  Success(val movies: List<Content>):NetWorkState()
class  Failed(val msg:String):NetWorkState();