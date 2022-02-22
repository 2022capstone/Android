package com.android.r.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <B : ViewBinding>(@LayoutRes private val layoutResId: Int) : Fragment(), LifecycleOwner{

    //추상클래스 이거 상속받으면 프래그먼트에서 따로 지정안해도 binding 할 수 있음
    // <> 안에는 viewbinding ex: FragmentJoinBinding, ()안에는 R.layout.~
    //abstract val viewModel : VM
    protected lateinit var binding: B
    protected lateinit var navController: NavController
    companion object{
        var selectedDate : String? = null

    }

    protected open fun initStartView() {}

    protected open fun initDataBinding() {}

    protected open fun initAfterBinding() {}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        initStartView()
        initAfterBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}