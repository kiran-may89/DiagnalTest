package com.diagnal.test.services.mocks

import android.content.res.AssetManager
import java.io.File
import java.io.InputStream


class MockHelper () {
    fun readFromAsset(assetManager: AssetManager, folder:String, file: String):String{
        val json = assetManager.open(File(folder,file).path).bufferedReader().readText()
        return json
    }
    fun readFromAssets(assetManager: AssetManager): List<String> {

        val string = ArrayList<String>()
        val streams: List<InputStream> =
            getListStreams(
                assetManager,
                "API"
            )
        for (stream in streams) {

            string.add(stream.bufferedReader().readText())
        }


        return string;


    }

    fun getListStreams(assetManager: AssetManager, folder: String): List<InputStream> {
        val names =
            getNames(
                assetManager,
                folder
            )
        val list = ArrayList<InputStream>();
        for (name in names) {
            val path = File(folder,name).path
            list.add(assetManager.open(path))
        }
        return list;

    }
    fun readFromAssetIndex(assetManager: AssetManager, position:Int,folder: String):String{
        val names = getNames(assetManager, folder)
        return readFromAsset(assetManager,folder,names[position])

    }

    fun getNames(assetManager: AssetManager, folder: String): Array<String> {

        val paths = assetManager.list(folder) ?: arrayOf<String>()

        return paths

    }
}