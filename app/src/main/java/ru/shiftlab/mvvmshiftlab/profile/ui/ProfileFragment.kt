package ru.shiftlab.mvvmshiftlab.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.shiftlab.mvvmshiftlab.R
import ru.shiftlab.mvvmshiftlab.databinding.ProfileFragmentBinding
import ru.shiftlab.mvvmshiftlab.profile.viewmodel.ProfileViewModel
import ru.shiftlab.mvvmshiftlab.profile.viewmodel.ProfileViewModelFactory


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

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)


        binding.lifecycleOwner = this

        binding.viewModel = viewModel



        return binding.root
    }


}
