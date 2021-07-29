package io.indrian.core.data.source.remote

import io.indrian.core.data.source.remote.network.ApiResponse
import io.indrian.core.data.source.remote.network.ApiService
import io.indrian.core.data.source.remote.response.ListGameResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @ExperimentalCoroutinesApi
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    @MockK
//    lateinit var apiService: ApiService
//    private lateinit var remoteDataSource: RemoteDataSource
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//        MockKAnnotations.init(this, relaxed = true)
//        remoteDataSource = RemoteDataSource(apiService)
//    }
//
//    @After
//    fun tearDown() = run {
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//        unmockkAll()
//    }
//
//    @Test
//    fun getGames() = testDispatcher.runBlockingTest {
//        coEvery { apiService.getGamesReleased() } returns ListGameResponse(
//            gameResponses = FakeRemoteDataSource.getGames()
//        )
//
//        remoteDataSource.getGamesReleased().collect {
//            assertThat(it, CoreMatchers.instanceOf(ApiResponse.Success::class.java))
//            val response = (it as ApiResponse.Success).data
//            assertEquals(true, response.isNotEmpty())
//            assertEquals("Shadow of the Colossus  (2018)", response.first().name)
//        }
//        coVerify { apiService.getGamesReleased() }
//    }
//
//    @Test
//    fun searchGames() = testDispatcher.runBlockingTest {
//        coEvery { apiService.searchGames("test") } returns ListGameResponse(
//            gameResponses = FakeRemoteDataSource.getGames()
//        )
//
//        remoteDataSource.searchGames("test").collect {
//            assertThat(it, CoreMatchers.instanceOf(ApiResponse.Success::class.java))
//            val response = (it as ApiResponse.Success).data
//            assertEquals(true, response.isNotEmpty())
//            assertEquals("Shadow of the Colossus  (2018)", response.first().name)
//        }
//        coVerify { apiService.searchGames("test")}
//    }
//
//    @Test
//    fun getGameDetails() = testDispatcher.runBlockingTest {
//        coEvery { apiService.getGameDetails(1234) } returns FakeRemoteDataSource.getGameDetails()
//
//        remoteDataSource.getGameDetails(1234).collect {
//            assertThat(it, CoreMatchers.instanceOf(ApiResponse.Success::class.java))
//
//            val response = (it as ApiResponse.Success).data
//            assertEquals("Shadow of the Colossus  (2018)", response.name)
//        }
//    }
}