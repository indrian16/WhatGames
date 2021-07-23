package io.indrian.whatgames.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.indrian.whatgames.adapter.GameAdapter
import io.indrian.whatgames.databinding.ActivitySearchBinding
import io.indrian.whatgames.ui.base.BaseActivity

class SearchActivity : BaseActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvSearch.adapter = GameAdapter()
    }

    override fun setupBinding() {
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        binding.imageBack.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun push(activity: AppCompatActivity) {
            activity.run {
                startActivity(
                    Intent(this, SearchActivity::class.java)
                )
            }
        }
    }
}