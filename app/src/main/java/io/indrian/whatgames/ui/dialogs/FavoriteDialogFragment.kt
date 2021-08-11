package io.indrian.whatgames.ui.dialogs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.indrian.core.utils.delayJob
import io.indrian.whatgames.R
import io.indrian.whatgames.databinding.FragmentFavoriteDialogBinding

private const val ARG_ANIM = "arg_anim"

class FavoriteDialogFragment : DialogFragment() {

    private var _binding: FragmentFavoriteDialogBinding? = null
    private val binding: FragmentFavoriteDialogBinding get() = _binding!!

    private var resourceAnim: String = "add_favorite_anim.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resourceAnim = it.getString(ARG_ANIM, "add_favorite_anim.json")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        isCancelable = false
        _binding = FragmentFavoriteDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.lottieLayout) {
            setAnimation(resourceAnim)
            playAnimation()
        }

        delayJob(800) {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window
        if (window != null) {

            val params = window.attributes
            params.width = resources.getDimensionPixelSize(R.dimen.favorite_dialog_size)
            params.height = resources.getDimensionPixelSize(R.dimen.favorite_dialog_size)

            window.attributes = params
            window.setGravity(Gravity.CENTER)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "FavoriteDialogFragment"

        fun newInstance(resourceAnim: String) = FavoriteDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ANIM, resourceAnim)
                }
            }
    }
}