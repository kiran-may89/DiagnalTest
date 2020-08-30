package com.diagnal.test.di.scopes

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelScope (val value: KClass<out ViewModel>)