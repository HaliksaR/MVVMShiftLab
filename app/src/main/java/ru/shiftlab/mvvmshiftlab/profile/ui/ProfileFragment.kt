package ru.shiftlab.mvvmshiftlab.profile.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.shiftlab.mvvmshiftlab.R
import ru.shiftlab.mvvmshiftlab.databinding.ProfileFragmentBinding
import ru.shiftlab.mvvmshiftlab.profile.viewmodel.ProfileViewModel
import ru.shiftlab.mvvmshiftlab.profile.viewmodel.ProfileViewModelFactory
import ru.shiftlab.mvvmshiftlab.profile.network.ProfileApi


class ProfileFragment : Fragment() {


    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory =
            ProfileViewModelFactory(
                application
            )

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel



        return binding.root
    }


}
