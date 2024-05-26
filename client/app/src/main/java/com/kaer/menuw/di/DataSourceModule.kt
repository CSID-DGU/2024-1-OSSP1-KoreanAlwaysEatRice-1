package com.kaer.menuw.di

import com.kaer.menuw.data.datasource.IngredientDataSource
import com.kaer.menuw.data.datasource.impl.IngredientDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun providesIngredientDataSource(dataSourceImpl: IngredientDataSourceImpl): IngredientDataSource
}