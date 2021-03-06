package com.test.asyncnetworking

import android.util.Log
import com.nhaarman.mockito_kotlin.*
import com.test.asyncnetworking.common.CachedResult
import com.test.asyncnetworking.usecase.github.api.RepoApi
import com.test.asyncnetworking.usecase.github.db.RepoDao
import com.test.asyncnetworking.usecase.github.model.Repo
import com.test.asyncnetworking.usecase.github.repository.RepoRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.mock.Calls
import java.util.*

@RunWith(PowerMockRunner::class)
@PrepareForTest(
    Log::class
)
class RepoRepositoryTest {

    private lateinit var repository: RepoRepository
    private lateinit var repoApi: RepoApi
    private lateinit var repoDao: RepoDao
    private lateinit var result : CachedResult<List<Repo>>
    private val emptyList = ArrayList<Repo>()

    @Before
    fun setup() {
        mockStatic(Log::class.java)
        repoApi = mock()
        repoDao = mock()
        result = mock()
        repository = RepoRepository(repoApi, repoDao)
    }

    @Test
    fun emptyCache_emptyDataOnApi_returnsEmptyList() {
        // GIVEN
        whenever(repoDao.getAll()).thenReturn(emptyList())
        whenever(repoApi.getRepos(any(), any(), any())).thenReturn(Calls.response(emptyList))

        // WHEN
        runBlocking {
            repository.getRepos(result)
        }

        // THEN
        verify(result, never()).onFailure(any())
        verify(result, times(1)).onSuccess(emptyList(), true)
        verify(result, times(1)).onSuccess(emptyList(), false)
    }

    @Test
    fun emptyCache_dataOnApi_returnsData() {
        // GIVEN
        whenever(repoDao.getAll()).thenReturn(emptyList())
        val expectedList = arrayListOf(generateRepo(), generateRepo())
        whenever(repoApi.getRepos(any(), any(), any())).thenReturn(Calls.response(expectedList))

        // WHEN
        runBlocking {
            repository.getRepos(result)
        }

        // THEN
        verify(result, never()).onFailure(any())
        verify(result, times(1)).onSuccess(emptyList(), true)
        verify(result, times(1)).onSuccess(expectedList, false)
    }

    @Test
    fun cacheData_apiData_returnsBothData() {
        // GIVEN
        val expectedCacheList = arrayListOf(generateRepo(), generateRepo())
        whenever(repoDao.getAll()).thenReturn(expectedCacheList)
        val expectedApiList = arrayListOf(generateRepo(), generateRepo())
        whenever(repoApi.getRepos(any(), any(), any())).thenReturn(Calls.response(expectedApiList))

        // WHEN
        runBlocking {
            repository.getRepos(result)
        }

        // THEN
        verify(result, never()).onFailure(any())
        verify(result, times(1)).onSuccess(expectedCacheList, true)
        verify(result, times(1)).onSuccess(expectedApiList, false)
    }

    @Test
    fun cache_updatesWithApiData() {
        // GIVEN
        val expectedApiList = arrayListOf(generateRepo(), generateRepo())
        whenever(repoApi.getRepos(any(), any(), any())).thenReturn(Calls.response(expectedApiList))

        // WHEN
        runBlocking {
            repository.getRepos(result)
        }

        // THEN
        verify(repoDao, times(1)).insertAll(expectedApiList)
    }

    companion object {
        fun generateRepo() = Repo(name = UUID.randomUUID().toString())
    }
}
