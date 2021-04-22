package com.serenadehl.ninepatchdemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serenadehl.ninepatchdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val density = resources.displayMetrics.density
        val densityDpi = resources.displayMetrics.densityDpi
        binding.tvParams.text = "density=${density}, densityDpi=${densityDpi}"

        //从drawable加载
        binding.vDrawable.setBackgroundResource(R.drawable.test_drawable)

        //从assets加载
        //1倍
        loadBitmap(DisplayMetrics.DENSITY_MEDIUM, "test_assets_1bei.png", binding.vAssets1)
        //2倍
        loadBitmap(DisplayMetrics.DENSITY_XHIGH, "test_assets_2bei.png", binding.vAssets2)
        //3倍
        loadBitmap(DisplayMetrics.DENSITY_XXHIGH, "test_assets_3bei.png", binding.vAssets3)
    }


    private fun loadBitmap(density: Int, fileName: String, view: View) {
        val inputStream = assets.open(fileName)
//        val option = BitmapFactory.Options()
//        option.inDensity = density
//        val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        bitmap.density = density
        inputStream.close()
        val drawable = NinePatchBuilder(resources, bitmap)
            .addXRegion(0.3F, 0.2F)
            .addYRegion(0F, 1F)
            .build()
        view.background = drawable
    }
}