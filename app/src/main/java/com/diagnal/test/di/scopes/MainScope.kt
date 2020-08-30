package com.diagnal.test.di.scopes

import java.lang.annotation.Documented
import javax.inject.Scope

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MainScope