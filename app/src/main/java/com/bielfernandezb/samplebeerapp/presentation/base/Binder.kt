package com.bielfernandezb.samplebeerapp.presentation.base

internal interface Binder<T> {
    fun bind(data: T)
}