package com.example.lesson14.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import coil.load
import com.example.lesson14.R
import com.example.lesson14.ui.main.RetrofitInstance.searchImageApi
import com.example.lesson14.databinding.FragmentBlankBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BlankFragment : Fragment(R.layout.fragment_blank) {
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = BlankFragment()
    }

    private val viewModel: BlankViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchImageApi.getCatImage().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                val cat = response.body()?.first() ?: return
                val status = response.code()
                val imageView = requireActivity().findViewById<ImageView>(R.id.cat)
                imageView.load(cat.url)

            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Log.e("Network", "Something went wrong", t)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}




