package tribore.rickandmorty.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tribore.rickandmorty.data.network.NetworkApi
import tribore.rickandmorty.data.repository.RepositoryImpl
import tribore.rickandmorty.domain.usecase.GetAllCharacterUseCase
import tribore.rickandmorty.domain.usecase.GetOneCharacterUseCase
import tribore.rickandmorty.util.Constant
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideGetMoshi() = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideGetRetrofit(moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideGetNetworkApi(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)


    @Provides
    @Singleton
    fun provideGetRepositoryImpl(networkApi: NetworkApi): RepositoryImpl {
        return RepositoryImpl(networkApi)
    }

    @Provides
    @Singleton
    fun provideGetImageUseCase(repo: RepositoryImpl): GetAllCharacterUseCase {
        return GetAllCharacterUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetOneCharacterUseCase(repo: RepositoryImpl): GetOneCharacterUseCase{
        return GetOneCharacterUseCase(repo)
    }

}