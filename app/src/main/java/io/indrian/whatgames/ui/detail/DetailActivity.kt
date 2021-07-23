package io.indrian.whatgames.ui.detail

import io.indrian.whatgames.databinding.ActivityDetailBinding
import io.indrian.whatgames.ui.base.BaseActivity

class DetailActivity : BaseActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    override fun setupBinding() {
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        with(binding) {
            imageBack.setOnClickListener { finish() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}