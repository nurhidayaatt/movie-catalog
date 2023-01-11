package com.nurhidayaatt.core.data.source

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nurhidayaatt.core.data.source.local.LocalDataSource
import com.nurhidayaatt.core.data.source.local.entity.MovieEntity
import com.nurhidayaatt.core.data.source.local.entity.TvShowEntity
import com.nurhidayaatt.core.data.source.remote.RemoteDataSource
import com.nurhidayaatt.core.data.source.remote.network.ApiResponse
import com.nurhidayaatt.core.data.source.remote.response.movie.MovieResponse
import com.nurhidayaatt.core.data.source.remote.response.tvshow.TvShowResponse
import com.nurhidayaatt.core.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val fakeMovieRepository = FakeMainRepository(remote, local)

    private val sortType: String = "DATE"

    private lateinit var movieRemote: Flow<ApiResponse<List<MovieResponse>>>
    private val movieLocal: Flow<List<MovieEntity>> = DataDummy.generateDummyMovieEntity()
    private val movie = DataDummy.generateDummyMovie()

    private lateinit var tvShowRemote: Flow<ApiResponse<List<TvShowResponse>>>
    private val tvShowLocal: Flow<List<TvShowEntity>> = DataDummy.generateDummyTvShowEntity()
    private val tvShow = DataDummy.generateDummyTvShow()

    @Test
    fun getAllMovie_ApiResponseSuccess() {
        runTest {
            movieRemote = DataDummy.generateDummyMovieResponse()

            `when`(local.getAllMovie(sortType)).thenReturn(movieLocal)
            lenient().`when`(remote.getAllMovie()).thenReturn(movieRemote)
            val outputFlow = fakeMovieRepository.getAllMovie(sortType).take(2).toList()

            verify(local, times(2)).getAllMovie(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(movie)
        }
    }

    @Test
    fun getAllMovie_ApiResponseEmpty() {
        runTest {
            movieRemote = flow { emit(ApiResponse.Empty()) }

            `when`(local.getAllMovie(sortType)).thenReturn(movieLocal)
            lenient().`when`(remote.getAllMovie()).thenReturn(movieRemote)
            val outputFlow = fakeMovieRepository.getAllMovie(sortType).take(2).toList()

            verify(local, times(2)).getAllMovie(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(movie)
        }
    }

    @Test
    fun getAllMovie_ApiResponseError() {
        runTest {
            movieRemote = flow { emit(ApiResponse.Error("")) }

            `when`(local.getAllMovie(sortType)).thenReturn(movieLocal)
            lenient().`when`(remote.getAllMovie()).thenReturn(movieRemote)
            val outputFlow = fakeMovieRepository.getAllMovie(sortType).take(2).toList()

            verify(local, times(2)).getAllMovie(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(movie)
        }
    }

    @Test
    fun getMovieFavorite_success() {
        runTest  {
            `when`(local.getMovieFavorite()).thenReturn(movieLocal)
            val outputFlow = fakeMovieRepository.getMovieFavorite().take(2).catch {
                assertThat(it is Exception).isTrue()
            }.toList()
            verify(local).getMovieFavorite()
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(movie)
        }
    }

    @Test
    fun getMovieFavorite_failed() {
        runTest {
            `when`(local.getMovieFavorite()).thenThrow()
            val outputFlow = fakeMovieRepository.getMovieFavorite().take(2).toList()
            verify(local).getMovieFavorite()
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1] is Resource.Error).isTrue()
        }
    }

    @Test
    fun getAllTvShow_ApiResponseSuccess() {
        runTest {
            tvShowRemote = DataDummy.generateDummyTvShowResponse()

            `when`(local.getAllTvShow(sortType)).thenReturn(tvShowLocal)
            lenient().`when`(remote.getAllTvShow()).thenReturn(tvShowRemote)
            val outputFlow = fakeMovieRepository.getAllTvShow(sortType).take(2).toList()

            verify(local, times(2)).getAllTvShow(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(tvShow)
        }
    }

    @Test
    fun getAllTvShow_ApiResponseEmpty() {
        runTest {
            tvShowRemote = flow { emit(ApiResponse.Empty()) }

            `when`(local.getAllTvShow(sortType)).thenReturn(tvShowLocal)
            lenient().`when`(remote.getAllTvShow()).thenReturn(tvShowRemote)
            val outputFlow = fakeMovieRepository.getAllTvShow(sortType).take(2).toList()

            verify(local, times(2)).getAllTvShow(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(tvShow)
        }
    }

    @Test
    fun getAllTvShow_ApiResponseError() {
        runTest {
            tvShowRemote = flow { emit(ApiResponse.Error("")) }

            `when`(local.getAllTvShow(sortType)).thenReturn(tvShowLocal)
            lenient().`when`(remote.getAllTvShow()).thenReturn(tvShowRemote)
            val outputFlow = fakeMovieRepository.getAllTvShow(sortType).take(2).toList()

            verify(local, times(2)).getAllTvShow(sortType)
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(tvShow)
        }
    }

    @Test
    fun getTvShowFavorite_success() {
        runTest  {
            `when`(local.getTvShowFavorite()).thenReturn(tvShowLocal)
            val outputFlow = fakeMovieRepository.getTvShowFavorite().take(2).catch {
                assertThat(it is Exception).isTrue()
            }.toList()
            verify(local).getTvShowFavorite()
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1].data).isEqualTo(tvShow)
        }
    }

    @Test
    fun getTvShowFavorite_failed() {
        runTest {
            `when`(local.getTvShowFavorite()).thenThrow()
            val outputFlow = fakeMovieRepository.getTvShowFavorite().take(2).toList()
            verify(local).getTvShowFavorite()
            assertThat(outputFlow[0] is Resource.Loading).isTrue()
            assertThat(outputFlow[1] is Resource.Error).isTrue()
        }
    }
}