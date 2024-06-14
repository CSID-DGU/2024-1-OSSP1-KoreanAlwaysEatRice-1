package com.kaer.menuw.di

import com.kaer.menuw.data.datasource.InfoDataSource
import com.kaer.menuw.data.datasource.IngredientDataSource
import com.kaer.menuw.data.datasource.LogInDataSource
import com.kaer.menuw.data.datasource.MenuDataSource
import com.kaer.menuw.data.datasource.impl.InfoDataSourceImpl
import com.kaer.menuw.data.datasource.impl.IngredientDataSourceImpl
import com.kaer.menuw.data.datasource.impl.LogInDataSourceImpl
import com.kaer.menuw.data.datasource.impl.MenuDataSourceImpl
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

    @Singleton
    @Binds
    abstract fun providesMenuDataSource(dataSourceImpl: MenuDataSourceImpl): MenuDataSource

    @Singleton
    @Binds
    abstract fun providesLogInDataSource(dataSourceImpl: LogInDataSourceImpl): LogInDataSource

    @Singleton
    @Binds
    abstract fun providesInfoDataSource(dataSourceImpl: InfoDataSourceImpl): InfoDataSource
}