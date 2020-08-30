package com.diagnal.test.services.mocks

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*

class MockHelperTest {

    @Test
    fun readFromAsset() {
        val helper = MockHelper();
        val string = helper.readFromAsset(
            InstrumentationRegistry.getInstrumentation().targetContext.assets,
            "API",
            "CONTENTLISTINGPAGE-PAGE1.json"
        )

        assertFalse(string.isNullOrEmpty())

    }

    @Test
    fun readFromAssets() {
        val helper = MockHelper();
        val array =
            helper.readFromAssets(InstrumentationRegistry.getInstrumentation().targetContext.assets)
        assertNotNull(array)
        assertTrue("coount", array.size > 0)
        for (str in array) {

            assertTrue(str.isNotEmpty())
        }
    }

    @Test
    fun getListStreams() {
        val helper = MockHelper();
        val array = helper.getListStreams(
            InstrumentationRegistry.getInstrumentation().targetContext.assets,
            "API"
        );
        assertNotNull(array)
        assertTrue("streams", array.size > 0)
    }

    @Test
    fun getNames() {
        val helper = MockHelper();
        val array = helper.getNames(
            InstrumentationRegistry.getInstrumentation().targetContext.assets,
            "API"
        );

        assertNotNull(array)
        assertTrue("coount", array.size > 0)
        for (str in array) {

            assertTrue(str.isNotEmpty())
        }

    }
}