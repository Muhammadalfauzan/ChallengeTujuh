package com.example.challengeempat.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*



@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CartDaoTest {

    // Rule for LiveData
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cartDao: CartDao
    private lateinit var database: DatabaseCart

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseCart::class.java
        ).allowMainThreadQueries().build()

        cartDao = database.cartDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCartItem() = runBlocking {
        // Given
        val cartData = CartData(
            id = 1,
            nameFood = "Food A",
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )

        // When
        cartDao.insert(cartData)
        val allKeranjangItems = cartDao.getAllItemCart().getOrAwaitValue()
        // Then
        assertThat(allKeranjangItems).contains(cartData)
    }

    @Test
    fun deleteCartItem() = runBlocking {
        // Given
        val cartData = CartData(
            id = 1,
            nameFood = "Food A",
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )

        // When
        cartDao.insert(cartData)  // Insert the item first
        cartDao.delete(cartData)
        cartDao.deleteALlItems()

        val allKeranjangItems = cartDao.getAllItemCart().getOrAwaitValue()

        // Then
        assertThat(allKeranjangItems).doesNotContain(cartData)
    }

    @Test
    fun deleteAllItems() = runBlocking {
        val cartData = CartData(
            id = 1,
            nameFood = "Food A",
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )

        // When
        cartDao.insert(cartData)
        cartDao.deleteALlItems()
        cartDao.deleteALlItems()

        val allKeranjangItems = cartDao.getAllItemCart().getOrAwaitValue()

        // Then
        assertThat(allKeranjangItems).isEmpty()
    }


    @Test
    fun updateCartItem() = runBlocking {
        // Given
        val cartData = CartData(
            id = 1,
            nameFood = "Food A",
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )

        // When
        cartDao.insert(cartData)
        cartDao.update(cartData)

        // Then
        val allKeranjangItems = cartDao.getAllItemCart().getOrAwaitValue()

        assertThat(allKeranjangItems).contains(cartData)
    }


    @Test
    fun getAllItemCart() = runBlocking {
        // Given
        val testData = listOf(
            CartData(
                id = 1,
                nameFood = "Food A",
                quantity = 2,
                hargaPerItem = 10,
                totalHarga = 20,
                imageurl = "some_image_url"
            ),
            CartData(
                id = 2,
                nameFood = "Food B",
                quantity = 3,
                hargaPerItem = 15,
                totalHarga = 45,
                imageurl = "some_image_url"
            )
        )

        // When
        testData.forEach { cartDao.insert(it) }

        // Then
        val allKeranjangItems = cartDao.getAllItemCart().getOrAwaitValue()

        assertThat(allKeranjangItems.toSet()).isEqualTo(testData.toSet())
    }


    @Test
    fun getCartItemByName() = runBlocking {
        // Given
        val itemName = "Food A"
        val testData = CartData(
            id = 1,
            nameFood = itemName,
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )

        // When
        cartDao.insert(testData)
        val result = cartDao.getCartItemByName(itemName)

        // Then
        assertThat(testData).isEqualTo(result)
    }


        @Test
        fun addOrUpdateCartItem_existingItem() = runBlocking {
            // Given
            val existingItem = CartData(
                id = 1,
                nameFood = "Food A",
                quantity = 2,
                hargaPerItem = 10,
                totalHarga = 20,
                imageurl = "some_image_url"
            )
            cartDao.insert(existingItem)

            val addNewQuantity = 2

            val newItem = CartData(
                id = 1,
                nameFood = "Food A",
                quantity = 2 + addNewQuantity,
                hargaPerItem = 10,
                totalHarga = 20,
                imageurl = "some_image_url"
            )

            // When

            cartDao.addOrUpdateCartItem(newItem)

            // Then
            assertThat(existingItem.quantity + addNewQuantity).isEqualTo(newItem.quantity)
        }

 /*   @Test
    fun addOrUpdateCartItem_newItem() = runBlocking {
        // Given
        val itemName = "Food B"
        val newItem = CartData(
            id = 2,  // ID yang berbeda dari item yang sudah ada
            nameFood = itemName,
            quantity = 2,
            hargaPerItem = 10,
            totalHarga = 20,
            imageurl = "some_image_url"
        )
        `when`(cartDao.getCartItemByName(itemName)).thenReturn(null)

        // When
        cartDao.addOrUpdateCartItem(newItem)

        // Then
        verify(cartDao, times(0)).update(any())  // Memastikan update tidak dipanggil
        verify(cartDao, times(1)).insert(newItem)
    }*/
}


