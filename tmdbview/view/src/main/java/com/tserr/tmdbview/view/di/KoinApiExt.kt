package com.tserr.tmdbview.view.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import kotlin.jvm.internal.Reflection


@OptIn(KoinApiExtension::class)
class TmdbViewModelFactory : ViewModelProvider.Factory, KoinComponent {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = getTmdbViewModel(modelClass)
}

@Suppress("UNCHECKED_CAST")
@OptIn(KoinApiExtension::class)
fun <T : ViewModel?> KoinComponent.getTmdbViewModel(modelClass: Class<T>): T =
    getKoin().get(Reflection.createKotlinClass(modelClass)) as T